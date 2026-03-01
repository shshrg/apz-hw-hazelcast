package com.example;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastNodeQueue" -e
public class HazelcastNodeQueue {
    public static void main(String[] args) {
        Config config = new Config();

        QueueConfig qConfig = config.getQueueConfig("bounded-queue");
        qConfig.setMaxSize(10);

        Hazelcast.newHazelcastInstance(config);
        
        System.out.println("New node with bounded queue (10 elements) started.");
    }
}
