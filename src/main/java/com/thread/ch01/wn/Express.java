package com.thread.ch01.wn;

public class Express {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 变化公里数，然后通知wait状态的线程，并处理公里数变化后的逻辑
     */
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
    }

    /**
     * 变化地点，然后通知处于wait状态并需要处理地点的线程进行处理逻辑
     */
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm() {
        while(this.km <= 100) {
            try {
                wait();
                System.out.println("check km thread["+Thread.currentThread().getId()
                        +"] is be notifed.");
            } catch(InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("the km is"+this.km+",I will change db.");
    }

    public synchronized void waitSite() {
        while(CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("check site thread["+Thread.currentThread().getId()
                        +"] is be notifed.");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("the site is"+this.site+",I will call user.");
    }
}
