package com.newbiegroup.secondkill.service.impl;

import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.service.OtpCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.newbiegroup.secondkill.common.constants.CacheConstants.USER_REGISTER_OPT_CODE_CACHE_KEY;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 15:16
 */
@Slf4j
@Service
public class OtpCodeServiceImpl implements OtpCodeService {

    @Autowired
    private CacheManager redisTemplate;

    @Override
    public String getOtp(String telPhone) {
        log.info("getOpt,telPhone:{}", telPhone);
        String existOpt = (String) redisTemplate.get(telPhone);
        if (StringUtils.isNotBlank(existOpt)) {
            return existOpt;
        } else {
            //随机数生产规则
            Random random = new Random();
            int randomInt = random.nextInt(9999);
            randomInt += 10000;
            existOpt = String.valueOf(randomInt);
            String key = String.format(USER_REGISTER_OPT_CODE_CACHE_KEY, telPhone);
            redisTemplate.setEx(key, existOpt, 5, TimeUnit.MINUTES);
            return existOpt;
        }
    }
}
