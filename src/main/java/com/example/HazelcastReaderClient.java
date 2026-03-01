package com.example;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.collection.IQueue;
import java.lang.InterruptedException;
// mvn exec:java "-Dexec.mainClass=com.example.HazelcastReaderClient" -e

public class HazelcastReaderClient{
    public static void main(String[] args) throws InterruptedException{
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IQueue<Integer> queue = client.getQueue("bounded-queue");

        while (true) {
            Integer value = queue.take();
            System.out.println("Value read: "+ value);
        }
    }
}
