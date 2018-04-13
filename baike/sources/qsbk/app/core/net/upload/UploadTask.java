package qsbk.app.core.net.upload;

public class UploadTask {
    private long a;
    private int b;
    private String c;

    public UploadTask(long j) {
        this.a = j;
    }

    public UploadTask(long j, String str) {
        this.a = j;
        this.c = str;
    }

    public long getId() {
        return this.a;
    }

    public String getContent() {
        return this.c;
    }

    public void setState(int i) {
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.a != ((UploadTask) obj).a) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.a ^ (this.a >>> 32));
    }

    public boolean isUploading() {
        return this.b == 1;
    }

    public boolean isWaiting() {
        return this.b == 0;
    }

    public boolean isSuccessed() {
        return this.b == 2;
    }

    public boolean isFailed() {
        return this.b == 3;
    }

    public int getState() {
        return this.b;
    }
}
