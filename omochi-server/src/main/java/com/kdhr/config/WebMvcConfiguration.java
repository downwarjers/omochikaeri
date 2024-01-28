package com.kdhr.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.kdhr.interceptor.JwtTokenAdminInterceptor;
import com.kdhr.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * 配置類，註冊web層相關組件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 註冊自訂攔截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("開始註冊自訂攔截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }

    /**
     * 透過knife4j產生介面文檔
     *
     * @return
     */
    @Bean
    public Docket docket() {
        log.info("透過knife4j產生介面文檔");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("OMOCHI外送專案介面文件")
                .version("2.0")
                .description("OMOCHI外送專案介面文件")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kdhr"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 設定靜態資源映射
     *
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("設定靜態資源映射");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 新增消息轉換器處理JSON日期時間格式
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //新增消息轉換器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //為該消息轉換器新增對象轉換器，可將JAVA Object To JSON
        converter.setObjectMapper(new JacksonObjectMapper());

        converters.add(0, converter);
    }
}