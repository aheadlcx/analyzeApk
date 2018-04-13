package jp.co.cyberagent.android.gpuimage;

class GPUImage$1 implements Runnable {
    final /* synthetic */ GPUImage a;

    GPUImage$1(GPUImage gPUImage) {
        this.a = gPUImage;
    }

    public void run() {
        synchronized (GPUImage.a(this.a)) {
            GPUImage.a(this.a).e();
            GPUImage.a(this.a).notify();
        }
    }
}
