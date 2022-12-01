package com.newbiegroup.secondkill.controller;

import com.newbiegroup.secondkill.common.constants.CacheConstants;
import com.newbiegroup.secondkill.common.exception.BizBusinessError;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.common.response.WebResponse;
import com.newbiegroup.secondkill.common.utils.EncodeUtil;
import com.newbiegroup.secondkill.enums.RegisterModeEnum;
import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.param.UserRegisterParam;
import com.newbiegroup.secondkill.service.OtpCodeService;
import com.newbiegroup.secondkill.service.UserInfoService;
import com.newbiegroup.secondkill.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import static com.newbiegroup.secondkill.common.constants.CacheConstants.USER_REGISTER_OPT_CODE_CACHE_KEY;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/17 16:08
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OtpCodeService otpService;
    @Autowired
    private CacheManager cacheUtils;
    /**
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @RequestParam(name = "telphone") String telPhone,
     * @RequestParam(name = "email") String email,
     * @RequestParam(name = "otpCode") String otpCode,
     * @RequestParam(name = "name") String name,
     * @RequestParam(name = "gender") Integer gender,
     * @RequestParam(name = "age") Integer age,
     * @RequestParam(name = "password") String password
     * @desc 用户注册接口
     */
    @PostMapping(value = "/register")
    public WebResponse register(@RequestBody UserRegisterParam param) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String telPhone = param.getTelPhone();
        String key = String.format(USER_REGISTER_OPT_CODE_CACHE_KEY, telPhone);
        String inSessionOptCode = cacheUtils.get(key);
        if (StringUtils.isBlank(inSessionOptCode)) {
            throw new BusinessException(BizBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码已失效，请重新发送");
        }
        if (!StringUtils.equals(param.getOtpCode(), inSessionOptCode)) {
            throw new BusinessException(BizBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不匹配");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(param, userVO);
        userVO.setRegisterMode(RegisterModeEnum.BY_PHONE.getCode());
        userVO.setEncrptPassword(EncodeUtil.encodeByMd5(param.getPassword()));
        userInfoService.register(userVO);
        return WebResponse.success();
    }

    @GetMapping(value = "/getotp")
    public WebResponse getOtp(@RequestParam(name = "telPhone") String telPhone) {
        String optCodeFromSession = otpService.getOtp(telPhone);
        /**
         * 异步 MQ发送邮件
         */
        return WebResponse.success(optCodeFromSession);
    }

    //用户登陆接口
    @PostMapping(value = "/login")
    public WebResponse login(@RequestParam("telPhone") String telPhone,
                             @RequestParam("password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isBlank(telPhone) || StringUtils.isBlank(password)) {
            throw new BusinessException("登录信息不得为空");
        }
        UserVO userVO = userInfoService.validateLogin(telPhone, password);
        //将登陆凭证加入到用户登陆成功的redis中;
        Long userId = userVO.getUserId();
        String key = String.format(CacheConstants.USER_IS_LOGIN_CACHE_KEY, userId);
        cacheUtils.setEx(key, userVO.getTelPhone(), 60 * 1000, TimeUnit.SECONDS);
        return WebResponse.success(userVO);
    }
}
