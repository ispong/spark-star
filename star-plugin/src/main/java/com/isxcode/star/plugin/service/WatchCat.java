package com.isxcode.star.plugin.service;

public class WatchCat implements Runnable {

    private final long timeout;

    private final long watchThreadId;

    public WatchCat(final long timeout, final long watchThreadId) {
        this.timeout = timeout;
        this.watchThreadId = watchThreadId;
    }

    public synchronized void start() {

        final Thread t = new Thread(this, "WATCH_CAT");
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void run() {

        System.out.println(System.currentTimeMillis() + "==" + "开启守护进程" + Thread.currentThread().getId());
        final long startTime = System.currentTimeMillis();
        boolean isWaiting;
        synchronized (this) {
            long timeLeft = timeout - (System.currentTimeMillis() - startTime);
            isWaiting = timeLeft > 0;
            while (isWaiting) {
                try {
                    wait(timeLeft);
                } catch (final InterruptedException ignored) {
                }
                timeLeft = timeout - (System.currentTimeMillis() - startTime);
                isWaiting = timeLeft > 0;
            }
        }

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                if (watchThreadId == threads[i].getId()) {
                    System.out.println(System.currentTimeMillis() + "==" + "关闭被守护进程" + threads[i].getId());
                    threads[i].interrupt();
                }
            }
            group = group.getParent();
        }

        System.out.println(System.currentTimeMillis() + "==" + "关闭守护进程" + Thread.currentThread().getId());
        Thread.currentThread().interrupt();
    }
}
