package com.high.currency07;

import java.util.concurrent.Callable;

public class PrizeThread implements Callable {
    Object obj = new Object();
    boolean flag = false;

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        synchronized (obj) {
            if(!flag) {
                flag = true;
                return Thread.currentThread().getName() + "抽奖成功！";
            } else {
                return Thread.currentThread().getName() + "抽奖失败！";
            }
        }
    }
}
