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
        try (
                RedisClient redis = RedisClient.builder()
                .hostAndPort(new HostAndPort("localhost", 6379)).build()
        ) {
            final int len = 100;
            for (int i = 0; i < len; ++i) {
                User user = User.builder()
                        .id(UUID.randomUUID())
                        .firstName(faker.name().firstName())
                        .secondName(faker.name().lastName())
                        .username(faker.name().username())
                        .email(faker.internet().emailAddress())
                        .age(faker.random().nextInt(15, 75))
                        .build();
                String jsonObj = mapper.writeValueAsString(user);
                System.out.println(jsonObj);
                LOGGER.info("Saving user: {}", (i + 1));
                String result = redis.jsonSet("user:" + (i + 1), jsonObj);
                LOGGER.info("Result: {}", result);
            }
            System.out.println("DB size: " + redis.dbSize());
            redis.flushAll();
            System.out.println("DB size: " + redis.dbSize());
        } catch (RuntimeException | JsonProcessingException e) {
            LOGGER.debug("Could not connect to Redis: {}", e.getMessage());
        }
        System.out.println("Done");

    }

}
