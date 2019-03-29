package com.paulandcode.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static com.paulandcode.common.Constant.DEFAULT_DATE_FORMAT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.TEXT_HTML;

/**
 * 配置FastJson
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/29 20:38
 */
@Configuration
public class FastJsonConfig {
    /**
     * 配置FastJson
     *
     * @return org.springframework.boot.autoconfigure.http.HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        // 添加FastJSON的配置信息
        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig =
                new com.alibaba.fastjson.support.config.FastJsonConfig();
        // 日期格式化, 只能使@ResponseBody的日期格式化, 不能使JSON.toJsonString()格式化
        // 这里定义的是全局日期格式化, 但是会覆盖@JSONField的日期格式化
        // 本项目中重写了com.alibaba.fastjson.serializer.SerializeFilterable源码后,
        // @JSONField的日期格式化会覆盖全局日期格式化
        fastJsonConfig.setDateFormat(DEFAULT_DATE_FORMAT);
        // 格式化输出
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 消除对同一对象循环引用的问题
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        converter.setFastJsonConfig(fastJsonConfig);

        // MappingJacksonHttpMessageConverter会默认设置contentType为application/json,
        // 在IE9下返回会出现提示下载的现象, 出现这种情况可以手动指定头信息为"text/html"
        List<MediaType> fastMediaTypes = new ArrayList<>();
        // 这里顺序不能写反, 一定要先写text/html, 否则IE下会出现下载提示.
        fastMediaTypes.add(TEXT_HTML);
        fastMediaTypes.add(APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);
        return new HttpMessageConverters(converter);
    }
}
