package com.huel.wsq.xgms.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Jedis 单例工具类
 * @author admin
 * @date 2018/3/8
 */
public class JedisUtil {
    private final static Logger LOG = LoggerFactory.getLogger(JedisUtil.class);
    /**
     * Jedis 单例对象
     */
    private volatile static Jedis instance;
    /**
     * redis 配置资源
     */
    private static Properties properties;

    private JedisUtil(){}

    /**
     * 加载Redis的配置
     */
    static {
        loadResources();
    }

    /**
     * 单例创建
     * @return Jedis 实例
     */
    public static Jedis getInstance(){
        if (instance == null){
            synchronized (JedisUtil.class){
                if (instance == null) {
                    LOG.debug("开始创建单例Jedis实例, begin Time ： {}" , System.currentTimeMillis());
                    if (properties == null){
                        loadResources();
                    }
                    instance = new Jedis(properties.getProperty("redis.ip"), Integer.parseInt(properties.getProperty("redis.port")));
                    instance.auth(properties.getProperty("redis.password"));
                    LOG.info("Jedis实例对象 : redis.ip:{}, redis.port:{}" , instance.getClient().getHost(), instance.getClient().getPort());
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("创建单例Jedis实例完成，EndTime ： {}" , System.currentTimeMillis());
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 加载 Redis 配置
     */
    private static void loadResources(){
        LOG.debug("加载Redis资源配置, BeginTime ：{}" , System.currentTimeMillis());
        properties = new Properties();
        InputStream in = JedisUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Redis资源配置为：Properties:{}" , JSON.toJSONString(properties));
        if (LOG.isDebugEnabled()) {
            LOG.debug("加载Redis资源配置完成, EndTime ：{}", System.currentTimeMillis());
        }
    }
}
