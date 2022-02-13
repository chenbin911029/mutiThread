package com.thread.ch01.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 实现一个数据库的连接池
 */
public class DBPool {
    //数据库连接池的容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0;i < initialSize;i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    //在mills时间内还拿不到数据库连接，返回一个null
    public Connection fetchConn(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                //mills < 0 表示没有超时时间，一直等待
                while(pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long overtime = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = overtime - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    //放回数据库连接
    public void releaseConn(Connection conn) {
        if (conn == null) {
            return;
        }
        synchronized (pool) {
            pool.addLast(conn);
            pool.notifyAll();
        }
    }
}
