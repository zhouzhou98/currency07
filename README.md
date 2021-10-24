###多人多轮次抽奖 
案例需求:实现多人多轮次抽奖 

难点: 实现抽奖奖品的唯一性  
解决方案: 由于涉及到奖品结果的返回，使用callable，由于涉及到奖品的唯一性，使用synchronized实现唯一性。  
代码:  
抽奖线程
```java
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
```  
测试线程:  
```java
package com.high.currency07;

import java.util.concurrent.FutureTask;


public class Test {

    public static void main(String[] args) throws Exception{
        int max=10,min=1;
        Thread  tha;
        Thread  thb;
        Thread  thc;
        Thread  thd;
        Thread  the;
        Thread  thf;
        for(int i=1;i<21;i++) {
            PrizeThread thread = new PrizeThread();
            FutureTask<String> ftA = new FutureTask<String>(thread);
            FutureTask<String> ftB = new FutureTask<String>(thread);
            FutureTask<String> ftC = new FutureTask<String>(thread);
            FutureTask<String> ftD = new FutureTask<String>(thread);
            FutureTask<String> ftE = new FutureTask<String>(thread);
            FutureTask<String> ftF = new FutureTask<String>(thread);

            tha= new Thread(ftA, "用户A");
            tha.setPriority((int)(Math.random()*(max-min)+min));
            tha.start();

            thb= new Thread(ftB, "用户B");
            thb.setPriority((int)(Math.random()*(max-min)+min));
            thb.start();

            thc= new Thread(ftC, "用户C");
            thc.setPriority((int)(Math.random()*(max-min)+min));
            thc.start();

            thd= new Thread(ftD, "用户D");
            thd.setPriority((int)(Math.random()*(max-min)+min));
            thd.start();

            the= new Thread(ftE, "用户E");
            the.setPriority((int)(Math.random()*(max-min)+min));
            the.start();

            thf= new Thread(ftF, "用户F");
            thf.setPriority((int)(Math.random()*(max-min)+min));
            thf.start();

            System.out.println("=====第"+i+"轮抽奖开始====");
            System.out.println(ftA.get());
            System.out.println(ftB.get());
            System.out.println(ftC.get());
            System.out.println(ftD.get());
            System.out.println(ftE.get());
            System.out.println(ftF.get());
            System.out.println("=====第"+i+"轮抽奖结束====");
            Thread.sleep(500);
        }
    }

}
```  
结果:  
```java
=====第1轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖成功！
用户F抽奖失败！
=====第1轮抽奖结束====
=====第2轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第2轮抽奖结束====
=====第3轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖成功！
用户E抽奖失败！
用户F抽奖失败！
=====第3轮抽奖结束====
=====第4轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第4轮抽奖结束====
=====第5轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第5轮抽奖结束====
=====第6轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第6轮抽奖结束====
=====第7轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖成功！
=====第7轮抽奖结束====
=====第8轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖成功！
用户E抽奖失败！
用户F抽奖失败！
=====第8轮抽奖结束====
=====第9轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖成功！
用户E抽奖失败！
用户F抽奖失败！
=====第9轮抽奖结束====
=====第10轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第10轮抽奖结束====
=====第11轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第11轮抽奖结束====
=====第12轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第12轮抽奖结束====
=====第13轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第13轮抽奖结束====
=====第14轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第14轮抽奖结束====
=====第15轮抽奖开始====
用户A抽奖成功！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第15轮抽奖结束====
=====第16轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖成功！
=====第16轮抽奖结束====
=====第17轮抽奖开始====
用户A抽奖失败！
用户B抽奖成功！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第17轮抽奖结束====
=====第18轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖成功！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖失败！
=====第18轮抽奖结束====
=====第19轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖成功！
用户E抽奖失败！
用户F抽奖失败！
=====第19轮抽奖结束====
=====第20轮抽奖开始====
用户A抽奖失败！
用户B抽奖失败！
用户C抽奖失败！
用户D抽奖失败！
用户E抽奖失败！
用户F抽奖成功！
=====第20轮抽奖结束====
```