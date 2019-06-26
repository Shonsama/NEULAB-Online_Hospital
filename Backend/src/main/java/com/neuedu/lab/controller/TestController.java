package com.neuedu.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/test")
    public void connectRedis() {
        //string
        //redisTemplate.opsForValue().set("spring", "test");

        //hash
/*        Map<String, String> m = new HashMap<>();
        m.put("name", "feiyy");
        m.put("password", "123");
        redisTemplate.opsForHash().putAll("feiyy", m);

        //list
        redisTemplate.opsForList().leftPush("mylist", "item1");
        redisTemplate.opsForList().leftPush("mylist", "item2");

        //set
        redisTemplate.opsForSet().add("myset", "set1");
        redisTemplate.opsForSet().add("myset", "set2");

        //zset
        redisTemplate.opsForZSet().add("articles", "article001", 100);
        redisTemplate.opsForZSet().add("articles", "article002", 200);*/

        //get string
        //String getString = redisTemplate.opsForValue().get("spring");
        redisTemplate.opsForHash().put("functions","testKey","testValue");

        System.out.println(redisTemplate.opsForHash().get("functions","testKey"));
    }
}
