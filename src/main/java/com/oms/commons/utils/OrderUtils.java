package com.oms.commons.utils;

import com.wah.doraemon.security.exception.UtilsException;
import com.wah.doraemon.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class OrderUtils{

    public static String createId(String uuid){
        if(StringUtils.isBlank(uuid)){
            throw new UtilsException("UUID不能为空");
        }

        long id        = new BigInteger(uuid, 16).longValue() % 1000;
        long timestamp = new Date().getTime();
        long di        = timestamp % 1000000000000l;
        int  min       = 1;
        int  max       = 9;

        Random random = new Random();
        int r1 = random.nextInt(max) % (max - min + 1) + min;
        int r2 = random.nextInt(max) % (max - min + 1) + min;
        int r3 = random.nextInt(max) % (max - min + 1) + min;

        String prefix   = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(r3);
        String diString = String.valueOf(di);

        return (prefix + diString + id).replaceAll("-", "");
    }

    public static void main(String[] args){
        Thread thread_1 = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println(createId(IDUtils.uuid32()));
            }
        });

        Thread thread_2 = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println(createId(IDUtils.uuid32()));
            }
        });

        Thread thread_3 = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println(createId(IDUtils.uuid32()));
            }
        });

        Thread thread_4 = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println(createId(IDUtils.uuid32()));
            }
        });

        Thread thread_5 = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println(createId(IDUtils.uuid32()));
            }
        });

        thread_1.start();
        thread_2.start();
        thread_3.start();
        thread_4.start();
        thread_5.start();
    }
}
