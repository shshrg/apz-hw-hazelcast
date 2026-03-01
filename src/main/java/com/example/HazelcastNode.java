package com.example;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Map;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastNode" -e
public class HazelcastNode {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<String, String> distributedMap = hzInstance.getMap("distributed-map");
        
        System.out.println("New node started.");
        System.out.println("Number of nodes: " + hzInstance.getCluster().getMembers().size());
    }
}
