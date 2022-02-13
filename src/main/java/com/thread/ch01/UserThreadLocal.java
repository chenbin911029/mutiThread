package com.thread.ch01;

public class UserThreadLocal {
    //结构是一个map
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        /**
         * Returns the current thread's "initial value" for this
         * @return the initial value for this thread-local
         */
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }

    /**
     * 测试线程：
     * 线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会相互影响
     */
    public static class TestThread implements Runnable {
        int id;
        public TestThread(int id) {
            this.id = id;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            Integer s = threadLocal.get();
            s = s + id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + ":"
                    + threadLocal.get());
            threadLocal.remove();
        }
    }

    public static void main(String[] args){
        UserThreadLocal test = new UserThreadLocal();
        test.StartThreadArray();
    }
}
