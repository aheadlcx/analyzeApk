package com.baidu.mobstat;

class bp implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bm d;

    bp(bm bmVar, long j, String str, String str2) {
        this.d = bmVar;
        this.a = j;
        this.b = str;
        this.c = str2;
    }

    public void run() {
        bv.a().d();
        bs bsVar = new bs();
        bsVar.c = this.a;
        bsVar.a = this.b;
        bsVar.b = this.c;
        String a = this.d.a(this.b, this.c);
        if (this.d.a.get(a) != null) {
            db.b("EventStat: event_id[" + this.b + "] with label[" + this.c + "] is duplicated, older is removed");
        }
        this.d.a.put(a, bsVar);
        db.a("put a keyword[" + a + "] into durationlist");
    }
}
