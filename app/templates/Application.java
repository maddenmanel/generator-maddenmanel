package

<%=packageName%>.<%=baseName%>;

import com.jd.spring.boot.jsf.annotation.EnableJsf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"<%=packageName%>"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableRetry(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJsf
public class Application extends SpringBootServletInitializer {

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Application.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
