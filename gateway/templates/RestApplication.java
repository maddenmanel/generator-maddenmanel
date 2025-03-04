package <%=packageName%>.<%=baseName%>;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"<%=packageName%>"})
@ImportResource(value = {"classpath:spring/spring-config.xml"})
public class RestApplication extends SpringBootServletInitializer{

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(RestApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }
}
