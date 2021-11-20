package com.BenedictoMatthewJmartFA;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread {

    private boolean exitSignal = false;
    private Vector<T> objectPool;
    private Function<T, Boolean> routine;

    public ObjectPoolThread(String name, Function<T, Boolean> routine) {
        super(name);
        this.routine = routine;
    }

    public ObjectPoolThread(Function<T, Boolean> routine) {
        super();
        this.routine = routine;
    }

    public synchronized void add(T object) {
        objectPool.add(object);
    }

    public synchronized void exit() {
        exitSignal = true;
        interrupt();
    }

    public void run() {
        while (!Thread.interrupted() && !exitSignal) {
            if (objectPool.isEmpty()) {
                try {
                    Thread.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                for (T object : objectPool) {
                    if (routine.apply(object)) {
                        objectPool.remove(object);
                    }
                }
            }
        }
    }

    public int size() {
        return objectPool.size();
    }
}
