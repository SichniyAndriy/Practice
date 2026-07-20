package org.example_04;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.RedisClient;

import java.util.UUID;


public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class.getCanonicalName());

    public static void main(String[] args) {
        Faker faker = new Faker();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try (
                RedisClient redis = RedisClient.builder()
                .hostAndPort(new HostAndPort("localhost", 6379))
                .build()
        ) {
            final int len = 100;
            for (int i = 0; i < len; ++i) {
                User user = createRandomUser(faker);
                saveUserToRedisJson(redis, mapper, user, i + 1);
            }
            System.out.println("DB size: " + redis.dbSize());
            redis.flushAll();
            System.out.println("DB size: " + redis.dbSize());
        } catch (RuntimeException | JsonProcessingException e) {
            LOGGER.debug("Could not connect to Redis: {}", e.getMessage());
        }
        System.out.println("Done");
    }

    private static User createRandomUser(Faker faker) {
        return User.builder()
                .id(UUID.randomUUID())
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .age(faker.random().nextInt(15, 75))
                .build();
    }

    private static void saveUserToRedisJson(RedisClient redis, ObjectMapper mapper, User user, int index)
            throws JsonProcessingException
    {
        String jsonObj = mapper.writeValueAsString(user);
        System.out.println(jsonObj);
        LOGGER.info("Saving user: {}", index);
        String result = redis.jsonSet("user:" + index, jsonObj);
        LOGGER.info("Result: {}", result);
    }

}
