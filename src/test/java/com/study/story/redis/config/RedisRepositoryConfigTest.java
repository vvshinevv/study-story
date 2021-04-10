package com.study.story.redis.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

@SpringBootTest
@ActiveProfiles("local")
public class RedisRepositoryConfigTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() {
        final String key = "testSortedSet";

        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();


        operations.add(key, "m1", 1);
        operations.add(key, "m2", 5);
        operations.add(key, "m3", 10);
        operations.add(key, "m4", 10);
        operations.add(key, "m5", 10);
        operations.add(key, "m6", 20);


        System.out.println("rank = " + operations.rank(key, "m3"));
        System.out.println("rank = " + operations.rank(key, "m4"));

        Double score = operations.score(key, "m5");
        System.out.println("score = " + score);
        if (score != null) {

            Set<String> members = operations.rangeByScore(key, score, score);
            System.out.println("members = " + members);

            assert members != null;
            String first = members.stream().sorted().findFirst().get();
            Long rank = operations.rank(key, first);
            System.out.println("temp = " + rank);
        }

        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushAll();
            return null;
        });
    }
}

