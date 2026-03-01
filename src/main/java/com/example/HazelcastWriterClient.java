package com.example;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.collection.IQueue;
import java.lang.InterruptedException;

// mvn exec:java "-Dexec.mainClass=com.example.HazelcastWriterClient" -e
public class HazelcastWriterClient{
    public static void main(String[] args) throws InterruptedException{
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IQueue<Integer> queue = client.getQueue("bounded-queue");

        for (Integer i = 1; i <= 100; i++) {
            System.out.println("Attempting to add " + i);
            queue.put(i);
            System.out.println("Added " + i);
        }

        client.shutdown();
    }
}
