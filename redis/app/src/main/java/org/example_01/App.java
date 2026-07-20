package org.example_01;

import redis.clients.jedis.Jedis;

import java.util.logging.Logger;

public class App {

    private final static Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("redis://localhost:6379")) {
            jedis.set("key", "value");
            String value = jedis.get("key");
            System.out.printf("value from redis: %s\n", value);
            jedis.del("key");
            value = jedis.get("key");
            System.out.printf("value from redis after deleting: %s\n", value);
            System.out.println(value);
        } catch (Exception e) {
            LOGGER.severe("Could not connect to Redis: " + e.getMessage());
        }
    }

}
