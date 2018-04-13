package antistatic.spinnerwheel;

class c implements Runnable {
    final /* synthetic */ AbstractWheel a;

    c(AbstractWheel abstractWheel) {
        this.a = abstractWheel;
    }

    public void run() {
        this.a.invalidateItemsLayout(false);
    }
}
