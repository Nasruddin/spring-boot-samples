package com.javatab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by nasir on 23/12/15.
 */
@Configuration
@EnableRedisHttpSession
public class EmbeddedRedisConfiguration {

    private static RedisServer redisServer;

    public JedisConnectionFactory connectionFactory() throws IOException {
        redisServer = new RedisServer(Protocol.DEFAULT_PORT);
        redisServer.start();
        return new JedisConnectionFactory();
    }

    @PreDestroy
    public void destory() {
        redisServer.stop();
    }
}
