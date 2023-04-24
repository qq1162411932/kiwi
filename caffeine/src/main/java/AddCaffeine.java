import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 *  jdk 11
 * @author Mr.Wu
 * @create 2023-04-20 9:35
 */
public class AddCaffeine {

    String queryDatabase(){
        return "1";
    }

    @Test
    public void ManualLoading(){
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(5)
                .build();

        System.out.println(cache.getIfPresent("test"));
        //查找缓存 如果不存在则生成
        cache.get("test" , t -> queryDatabase() );
        System.out.println(cache.getIfPresent("test"));
    }

    @Test
    public void testPut(){
        int[] a =  new int[10];
        if((a[0] = 1) == 0){

        }
        System.out.println(a[0]);
    }
}

