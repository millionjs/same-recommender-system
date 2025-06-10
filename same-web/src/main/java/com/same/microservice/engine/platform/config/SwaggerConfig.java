package com.same.microservice.engine.platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Data
@ConfigurationProperties(prefix = "swagger")
@SpringBootConfiguration
public class SwaggerConfig {

    private List<String> serverList;

    @Bean
    public OpenAPI customOpenAPI() {

        // 联系人信息(contact)，构建API的联系人信息，用于描述API开发者的联系信息，包括名称、URL、邮箱等
        // name：文档的发布者名称 url：文档发布者的网站地址，一般为企业网站 email：文档发布者的电子邮箱
        Contact contact = new Contact()
                .name("Johnny")                             // 作者名称
                .email("lianjing@million.dev")                   // 作者邮箱
//                .url("")  // 介绍作者的URL地址
                .extensions(new HashMap<String, Object>()); // 使用Map配置信息（如key为"name","email","url"）

        // 授权许可信息(license)，用于描述API的授权许可信息，包括名称、URL等；假设当前的授权信息为Apache 2.0的开源标准
        License license = new License()
                .name("Apache 2.0")                         // 授权名称
                .url("https://www.apache.org/licenses/LICENSE-2.0.html")    // 授权信息
                .extensions(new HashMap<String, Object>());// 使用Map配置信息（如key为"name","url","identifier"）

        //创建Api帮助文档的描述信息、联系人信息(contact)、授权许可信息(license)
        Info info = new Info()
                .title("Same API 入口Swagger3.0")      // Api接口文档标题（必填）
                .description("Same平台Swagger3.0")     // Api接口文档描述
                .version("1.0")                                  // Api接口版本
                .termsOfService("https://example.com/")            // Api接口的服务条款地址
                .license(license)                                  // 设置联系人信息
                .contact(contact);                                 // 授权许可信息
        OpenAPI openAPI =  new OpenAPI()
                .openapi("3.0.1")
                .servers(serverList.stream().map(item->new Server().url(item)).collect(Collectors.toList()))
                .info(info);
        return openAPI;       // 配置Swagger3.0描述信息
    }
}