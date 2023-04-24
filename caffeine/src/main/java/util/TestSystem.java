package util;

import org.junit.jupiter.api.Test;

/**
 * @author Mr.Wu
 * @create 2023-04-23 18:00
 */
public class TestSystem {

    @Test
    public void testBin(){
        System.out.println("apple".hashCode());
        System.out.println("apple".hashCode() >>> 16);
        System.out.println("apple".hashCode() ^ "apple".hashCode() >>> 16);
    }
}
