package com.example;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastIncrementClient" -e
public class HazelcastIncrementClient {
    public static void main(String[] args) {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IMap<String, Integer> map = client.getMap("map-2");
        
        map.putIfAbsent("key", 0);
        for (int k = 0; k < 10_000; k++) {
            var value = map.get("key");
            value ++;
            map.put("key", value);
        }

        System.out.println("Finished 10000 increments.");

        client.shutdown();
    }
}
