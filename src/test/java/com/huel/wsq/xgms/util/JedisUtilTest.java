package com.huel.wsq.xgms.util;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * JedisUtil 工具类测试
 * @author wsq
 * @date 2018/3/8
 */
public class JedisUtilTest {
    @Test
    public void testJedisInstance(){
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    JedisUtil.getInstance();
                }
            });
            thread.start();
        }
    }
}
