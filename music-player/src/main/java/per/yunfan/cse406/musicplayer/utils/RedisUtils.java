package per.yunfan.cse406.musicplayer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public final class RedisUtils {

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(RedisUtils.class);

    /**
     * Jedis connection pool object
     */
    private static final JedisPool JEDIS = init();

    /**
     * Utility class may not create an instance
     */
    private RedisUtils() {
    }

    /**
     * Initialize Jedis connection pool object
     *
     * @return Jedis connection pool object
     */
    private static JedisPool init() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(true);
        try {
            Properties props = new Properties();
            props.load(JDBCUtils.class.getResourceAsStream("/redis.properties"));
            String host = props.getProperty("redis.host");
            int port = Integer.parseInt(props.getProperty("redis.port"));
            return new JedisPool(jedisPoolConfig, host, port, 2000);
        } catch (IOException e) {
            LOG.error("Initialize Redis service failure, Could not load redis.properties file.", e);
        } catch (NumberFormatException e) {
            LOG.error("Initialize Redis service failure, the port in redis.properties is not a number.", e);
        }
        LOG.warn("Load redis.properties failure, use localhost and 6379 as the property");
        return new JedisPool(jedisPoolConfig, "localhost", 6379, 2000);
    }

    /**
     * Set values to redis
     *
     * @param key   Key
     * @param value Value
     * @return Storage state
     */
    public static <K, V> String set(K key, V value) {
        SetParams params = new SetParams()
                .ex(3600);
        Jedis jedis = JEDIS.getResource();
        String state = jedis.set(String.valueOf(key), String.valueOf(value), params);
        jedis.close();
        return state;
    }

    /**
     * Get value from redis by key
     *
     * @param key Key
     * @return value
     */
    public static <K> Optional<String> get(K key) {
        Jedis jedis = JEDIS.getResource();
        String value = jedis.get(String.valueOf(key));
        jedis.close();
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }

}
