package qsbk.app.activity;

class tl implements Runnable {
    final /* synthetic */ MemberManagerActivity a;

    tl(MemberManagerActivity memberManagerActivity) {
        this.a = memberManagerActivity;
    }

    public void run() {
        this.a.h.notifyDataSetChanged();
    }
}
