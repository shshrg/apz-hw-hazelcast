package com.example;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastIncrementOptimisticClient" -e
public class HazelcastIncrementOptimisticClient {
    public static void main(String[] args) {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IMap<String, Integer> map = client.getMap("map-optimistic");
        
        map.putIfAbsent("key", 0);

        long start = System.currentTimeMillis();

        for (int k = 0; k < 10_000; k++) {
            while (true) {
                Integer oldValue = map.get("key");
                Integer newValue = oldValue + 1;

                if (map.replace("key", oldValue, newValue)) {
                    break;
                }
            }
        }

        long end = System.currentTimeMillis();

        client.shutdown();

        System.out.println("Finished 10000 increments.");
        System.out.println("Time: " + (end - start) + "ms");

    }
}
