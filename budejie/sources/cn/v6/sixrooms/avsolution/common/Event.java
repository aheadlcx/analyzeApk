package cn.v6.sixrooms.avsolution.common;

public class Event {
    public float f100 = 0.0f;
    public int i32 = 0;
    public long i64 = 0;
    public int type = 0;

    public void setEvent(int i, int i2, long j, float f) {
        this.type = i;
        this.i32 = i2;
        this.i64 = j;
        this.f100 = f;
    }
}
