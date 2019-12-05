package  <%=packageName%>.<%=baseName%>.configration;

import com.wangyin.rediscluster.springcache.R2mSpringCache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public R2mSpringCache r2mCache() {
        R2mSpringCache r2mSpringCache = new R2mSpringCache();
        r2mSpringCache.setKeyPrefix("<%= systemName %>");
        return r2mSpringCache;
    }
}
