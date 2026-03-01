package com.example;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastMapWriteClient" -e
public class HazelcastMapWriteClient {
    public static void main(String[] args) {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IMap<Integer, String> map = client.getMap("distributed-map");
        for (int i = 0; i < 1000; i++) {
            map.put(i, "val-"+i);
        }
        System.out.println("1000 elements added to map.");
        System.out.println("Current map size: " + map.size());

        client.shutdown();
    }
}
