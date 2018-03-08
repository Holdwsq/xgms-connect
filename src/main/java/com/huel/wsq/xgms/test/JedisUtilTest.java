package com.huel.wsq.xgms.test;

import com.huel.wsq.xgms.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadFactory;

/**
 * @author admin
 * @description
 * @date 2018/3/8
 */
public class JedisUtilTest {
    public static void main(String... args){
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JedisUtil.getInstance();
                }
            }).start();
        }
    }
}
