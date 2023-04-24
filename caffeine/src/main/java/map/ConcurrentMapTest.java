package map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentMapTest {


    private static final int THREAD_COUNT = 10;
    private static final int OPERATION_COUNT = 10;

    public static void main(String[] args) {
        System.out.println("start");
        Map<Integer, String> map = new HashMap<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                for (int j = 0; j < OPERATION_COUNT; j++) {
                    int key = (int) (Math.random() * 1000);
                    String value = "value-" + j;
                    map.put(key, value);
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {

            Thread.yield();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Size: " + map.size() + ", Time: " + (endTime - startTime) + "ms");
    }

    @Test
    public void testConcurrentHashMap() throws InterruptedException {
        final int THREAD_COUNT = 100;
        final int TEST_COUNT = 10000;

        ThreadGroup threadGroup = new ThreadGroup("TestGroup");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(threadGroup, () -> {
                for (int j = 0; j < TEST_COUNT; j++) {
                    map.putIfAbsent(String.valueOf(j % 10), 1);
                }
            }).start();
        }

        while (threadGroup.activeCount() > 0) {
            Thread.sleep(100);
        }

        // 所有线程执行完毕
        assertEquals(10000 * 10, map.size());
    }

    @Test
    public void testHashMap() throws InterruptedException {
        final int THREAD_COUNT = 100;
        final int TEST_COUNT = 100;

        ThreadGroup threadGroup = new ThreadGroup("TestGroup");
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(threadGroup, () -> {
                for (int j = 0; j < TEST_COUNT; j++) {
                    map.put(UUID.randomUUID().toString(), 1);
                }
            }).start();
        }

        while (threadGroup.activeCount() > 0) {
            Thread.sleep(100);
        }
        // 所有线程执行完毕
//        assertEquals(THREAD_COUNT * TEST_COUNT, map.size());
        System.out.println(map.size());
    }

    @Test
    public void deadLoop() throws InterruptedException {
        ThreadGroup test = new ThreadGroup("test");
        HashMap<String, String> hashMap = new HashMap();
        for (int i = 0; i < 100000; i++) {
            new Thread(test, () -> {
                hashMap.put(UUID.randomUUID().toString(), "1");
            }).start();
        }
        while(test.activeCount() > 0){
            Thread.sleep(100);
        }
        System.out.println(hashMap.size());
    }

}
