package com.example;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastIncrementPessimisticClient" -e
public class HazelcastIncrementPessimisticClient {
    public static void main(String[] args) {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IMap<String, Integer> map = client.getMap("map-pessimistic");
        
        map.putIfAbsent("key", 0);

        long start = System.currentTimeMillis();

        for (int k = 0; k < 10_000; k++) {
            map.lock("key");
            try {
                var value = map.get("key");
                value ++;
                map.put("key", value);
            } finally {
                map.unlock("key");
            }
            
        }

        long end = System.currentTimeMillis();

        client.shutdown();

        System.out.println("Finished 10000 increments.");
        System.out.println("Time: " + (end - start) + "ms");

    }
}
