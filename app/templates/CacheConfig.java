package  <%=packageName%>.<%=baseName%>.configration;

import com.wangyin.rediscluster.client.R2mClusterClient;
import com.wangyin.rediscluster.serializer.DefaultR2mObjectSerializer;
import com.wangyin.rediscluster.serializer.Serializer;
import com.wangyin.rediscluster.springcache.R2mCacheManager;
import com.wangyin.rediscluster.springcache.R2mSpringCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

  private static final Serializer SERIALIZER = new DefaultR2mObjectSerializer();

  @Autowired(required = false)
  private List<R2mSpringCache<?>> caches = new ArrayList<R2mSpringCache<?>>();

  @Resource
  private R2mClusterClient client1;

  @Bean
  @ConditionalOnMissingBean
  public R2mCacheManager cacheManager() {
    final R2mCacheManager cacheManager = new R2mCacheManager();
    for (R2mSpringCache cache : this.caches) {
      cache.setCacheClusterClient(client1);
      cache.setValueSerializer(SERIALIZER);
    }
    cacheManager.setCaches(caches);
    return cacheManager;
  }

  @Bean
  public R2mSpringCache r2mCache() {
    R2mSpringCache r2mSpringCache = new R2mSpringCache();
    r2mSpringCache.setKeyPrefix("<%= systemName %>");
    return r2mSpringCache;
  }
}
