package <%=packageName%>.<%=baseName%>.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Value("${swagger2.enable}")
  private boolean swagger2Enable;

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .enable(swagger2Enable)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("<%=packageName%>.<%=baseName%>.controller"))
      .paths(PathSelectors.any())
      .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("<%= systemName %>")
      .description("<%= systemName %> API 操作文档")
//      .termsOfServiceUrl("https://unionstars.github.io/")
//      .contact(new Contact("", "https://unionstars.github.io/", "zhangxuegang@gmail.com"))
      .version("1.0")
      .build();
  }
}
