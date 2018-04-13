package qsbk.app.activity;

class aap implements Runnable {
    final /* synthetic */ RandomDoorActivity a;

    aap(RandomDoorActivity randomDoorActivity) {
        this.a = randomDoorActivity;
    }

    public void run() {
        this.a.openUsers();
    }
}
