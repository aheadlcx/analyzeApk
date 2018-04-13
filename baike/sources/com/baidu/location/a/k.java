package com.baidu.location.a;

class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void run() {
        if (this.a.c != null) {
            this.a.c.unregisterListener(j.d, this.a.c.getDefaultSensor(6));
        }
    }
}
