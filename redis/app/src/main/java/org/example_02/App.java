package org.example_02;

import com.github.javafaker.Faker;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

public class App {

    private final static Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Faker faker = new Faker();
        final int len = 10_001;
        try(Jedis jedis = new Jedis("redis://localhost:6379")) {
            String result;
            final long start = System.currentTimeMillis();
            for (int i = 1; i < len; ++i) {
                System.out.println("Saving key: " + i);
                result = jedis.set("key:" + i, faker.ancient().hero());
                System.out.println(result);
            }
            final long end = System.currentTimeMillis();
            System.out.println(Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end)).getNano() / 1_000_000 + " ms");

            for (int i = 1; i < len; ++i) {
                System.out.println(jedis.get("key:" + i));
            }
            System.out.printf("DB size: %d\n", jedis.dbSize());
            jedis.flushAll();
            System.out.printf("DB size: %d\n", jedis.dbSize());
        } catch (RuntimeException e) {
            LOGGER.severe("Could not connect to Redis: " + e.getMessage());
        }

    }

}
