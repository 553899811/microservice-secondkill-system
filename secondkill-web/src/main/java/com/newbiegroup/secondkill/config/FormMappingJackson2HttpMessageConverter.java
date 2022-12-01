package com.newbiegroup.secondkill.config;


import com.fasterxml.jackson.databind.JavaType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URLDecoder;

/**
 * 适配form提交json
 **/
public class FormMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	/** form-data前缀 */
    private static final String DEFAULT_FORMDAT_APREFIX = "form_data=";


    /**
     * <pre>
     * 1.避免json convert 处理二进制流，导致乱码问题
     * 2.二进制流数据使用ByteArrayHttpMessageConverter 来处理
     * </pre>
     * @param clazz Class<?>
     * @param mediaType MediaType
     * @return
     */
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return !clazz.equals(byte[].class);
    }


    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
    	//非form提交走父类
        if (!isFormHead(inputMessage)) {
            return super.read(type, contextClass, inputMessage);
        }
        JavaType javaType = getJavaType(type, contextClass);
        return readJavaType(javaType, inputMessage);
    }

    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
        try {
            //修改
            byte[] bytes = inputStream2Bytes(inputMessage.getBody());
            String decode = URLDecoder.decode(new String(bytes), "UTF-8");
          //去掉多余=号
            if (StringUtils.isNotBlank(decode) && decode.startsWith(DEFAULT_FORMDAT_APREFIX)) {
                decode = decode.replaceFirst(DEFAULT_FORMDAT_APREFIX, "");
            }
            return this.objectMapper.readValue(decode, javaType);
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("I/O error while reading input message", ex);
        }
    }

    private byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int offset = 0;
        while ((offset = in.read(buff)) > 0) {
            outputStream.write(buff, 0, offset);
        }
        return outputStream.toByteArray();
    }

    private boolean isFormHead(HttpInputMessage httpInputMessage) {
        MediaType contentType = httpInputMessage.getHeaders().getContentType();
        return MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType);
    }
}
