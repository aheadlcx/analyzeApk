package cn.xiaochuankeji.tieba.background.upload;

import android.util.Log;
import cn.htjyb.netlib.d;
import cn.xiaochuankeji.tieba.background.h.h;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.utils.b.e;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    private a a;
    private ArrayList<LocalMedia> b;
    private final ArrayList<c> c;
    private b d;
    private int e;
    private boolean f;

    public interface a {
        void a(boolean z, ArrayList<c> arrayList, String str);
    }

    public static class b {
        public int a;
        public int b;
        public int c;
    }

    public static class c {
        public String a;
        public long b;
        public int c;
        public String d;

        public c(String str, long j, int i) {
            this.a = str;
            this.b = j;
            this.c = i;
        }
    }

    public void a() {
        int i = 0;
        while (i < this.b.size()) {
            if (!this.f) {
                boolean z;
                this.e = i;
                LocalMedia localMedia = (LocalMedia) this.b.get(i);
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    if (((c) it.next()).a.equals(localMedia.path)) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (z) {
                    i++;
                } else {
                    a(localMedia);
                    return;
                }
            }
            return;
        }
        if (!this.f) {
            Log.i("PublishPostActivity", "媒体资源上传完成.");
            this.a.a(true, this.c, null);
            if (org.greenrobot.eventbus.c.a().b(this)) {
                org.greenrobot.eventbus.c.a().c(this);
            }
        }
    }

    private void a(final LocalMedia localMedia) {
        if (1 == localMedia.type) {
            Log.i("PublishPostActivity", "上传视频,path: " + localMedia.path);
            e eVar = new e(localMedia, new cn.xiaochuankeji.tieba.background.utils.b.e.a(this) {
                final /* synthetic */ g b;

                public void a(boolean z, boolean z2, long j, String str, String str2) {
                    if (z) {
                        Log.i("PublishPostActivity", "视频上传完成,id:" + j);
                        c cVar = new c(localMedia.path, j, localMedia.type);
                        cVar.d = str;
                        this.b.c.add(cVar);
                        h.a().a(cn.xiaochuankeji.tieba.background.utils.d.a.a + str, localMedia.path);
                        if (z2) {
                            this.b.b();
                            return;
                        }
                        this.b.c();
                        this.b.a();
                        return;
                    }
                    Log.i("PublishPostActivity", "视频上传出错,error:" + str2);
                    this.b.a.a(false, null, str2);
                }
            });
            eVar.a(new cn.xiaochuankeji.tieba.background.utils.b.e.b(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void a(int i, int i2) {
                    this.a.a(i, i2);
                }
            });
            eVar.a();
            return;
        }
        Log.i("PublishPostActivity", "上传图片,path: " + localMedia.path);
        File file = new File(localMedia.path);
        File file2 = new File(cn.xiaochuankeji.tieba.background.a.e().q());
        if (localMedia.path.substring(localMedia.path.lastIndexOf(".") + 1).equals("gif") || !cn.htjyb.c.b.b.a(file, file2, 80, Integer.MAX_VALUE)) {
            file2 = file;
        }
        Collection arrayList = new ArrayList();
        arrayList.add(new cn.htjyb.netlib.b.b(file2, "file"));
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        String b = cn.xiaochuankeji.tieba.background.h.e.a().b(localMedia.path);
        if (b != null) {
            try {
                jSONObject.put("txt", b);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jSONObject.put("type", "post");
        cn.htjyb.netlib.g gVar = new cn.htjyb.netlib.g(cn.xiaochuankeji.tieba.background.utils.d.a.b("/upload/img"), cn.xiaochuankeji.tieba.background.a.d(), arrayList, jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ g b;

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    long optLong = dVar.c.c.optLong("id");
                    Log.i("PublishPostActivity", "图片上传完成,id:" + optLong);
                    c cVar = new c(localMedia.path, optLong, localMedia.type);
                    cVar.d = dVar.c.c.optString("uri");
                    this.b.c.add(cVar);
                    this.b.c();
                    this.b.a();
                    return;
                }
                Log.i("PublishPostActivity", "图片上传出错,error:" + dVar.c.c());
                this.b.a.a(false, null, dVar.c.c());
            }
        });
        gVar.a(new cn.htjyb.netlib.g.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.a(i, i2);
            }
        });
        gVar.a(0);
        gVar.b();
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_DUMMY_PROGRESS_OVER) {
            a();
        }
    }

    private void a(int i, int i2) {
        this.d.a = this.e;
        this.d.b = i;
        this.d.c = i2;
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_UPLOAD_PROGRESS);
        messageEvent.setData(this.d);
        org.greenrobot.eventbus.c.a().d(messageEvent);
    }

    private void b() {
        this.d.a = this.e;
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_SHOW_DUMMY_PROGRESS_AND_SHOW_NEXT);
        messageEvent.setData(this.d);
        org.greenrobot.eventbus.c.a().d(messageEvent);
    }

    private void c() {
        this.d.a = this.e;
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_SHOW_NEXT);
        messageEvent.setData(this.d);
        org.greenrobot.eventbus.c.a().d(messageEvent);
    }
}
