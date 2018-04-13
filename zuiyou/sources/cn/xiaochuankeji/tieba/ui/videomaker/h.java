package cn.xiaochuankeji.tieba.ui.videomaker;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements Parcelable {
    public static final Creator<h> CREATOR = new Creator<h>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public h a(Parcel parcel) {
            return new h(parcel);
        }

        public h[] a(int i) {
            return new h[i];
        }
    };
    public final String a;
    public final e b;
    public final e c;
    public final String d;
    public final ArrayList<k> e;
    public final long f;
    public final long g;
    public final UgcVideoMusicJson h;
    public final Topic i;

    public static class a {
        String a;
        e b;
        e c;
        String d;
        ArrayList<k> e = new ArrayList();
        long f;
        long g;
        UgcVideoMusicJson h;
        Topic i;

        a(h hVar) {
            this.a = hVar.a;
            this.b = hVar.b;
            this.c = hVar.c;
            this.d = hVar.d;
            this.e.addAll(hVar.e);
            this.f = hVar.f;
            this.g = hVar.g;
            this.h = hVar.h;
            this.i = hVar.i;
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(e eVar) {
            this.b = eVar;
            return this;
        }

        public a b(e eVar) {
            this.c = eVar;
            return this;
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public a a(ArrayList<k> arrayList) {
            this.e.clear();
            this.e.addAll(arrayList);
            return this;
        }

        public a a(long j) {
            this.f = j;
            return this;
        }

        public a b(long j) {
            this.g = j;
            return this;
        }

        public a a(UgcVideoMusicJson ugcVideoMusicJson) {
            this.h = ugcVideoMusicJson;
            return this;
        }

        public a a(Topic topic) {
            this.i = topic;
            return this;
        }

        public h a() {
            return new h(this);
        }
    }

    public h(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = new ArrayList();
        this.e.addAll(aVar.e);
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
    }

    public h(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject.optString("video_path");
        if (jSONObject.has("audio_path")) {
            this.b = new e(jSONObject.getString("audio_path"), 100);
        } else if (jSONObject.has("record_sound")) {
            this.b = new e(jSONObject.getJSONObject("record_sound"));
        } else {
            this.b = null;
        }
        if (jSONObject.has("bgm_path")) {
            this.c = new e(jSONObject.getString("bgm_path"), 100);
        } else if (jSONObject.has("bgm_sound")) {
            this.c = new e(jSONObject.getJSONObject("bgm_sound"));
        } else {
            this.c = null;
        }
        this.d = jSONObject.optString("cover_path");
        this.e = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("video_parts");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.e.add(new k(jSONArray.getJSONObject(i)));
        }
        this.f = jSONObject.optLong("draft_id");
        this.g = jSONObject.optLong("owner_id");
        this.h = (UgcVideoMusicJson) JSON.parseObject(jSONObject.optString("music_info"), UgcVideoMusicJson.class);
        if (jSONObject.has("topic")) {
            this.i = new Topic(jSONObject.getJSONObject("topic"));
        } else {
            this.i = null;
        }
    }

    protected h(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (e) parcel.readParcelable(e.class.getClassLoader());
        this.c = (e) parcel.readParcelable(e.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.createTypedArrayList(k.CREATOR);
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = (UgcVideoMusicJson) parcel.readParcelable(UgcVideoMusicJson.class.getClassLoader());
        this.i = (Topic) parcel.readParcelable(Topic.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeParcelable(this.c, i);
        parcel.writeString(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeParcelable(this.h, i);
        parcel.writeParcelable(this.i, i);
    }

    public int describeContents() {
        return 0;
    }

    public a a() {
        return new a(this);
    }

    public void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("video_path", this.a);
        if (this.b != null) {
            jSONObject.put("record_sound", this.b.a());
        }
        if (this.c != null) {
            jSONObject.put("bgm_sound", this.c.a());
        }
        jSONObject.put("cover_path", this.d);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            jSONArray.put(((k) it.next()).a());
        }
        jSONObject.put("video_parts", jSONArray);
        jSONObject.put("draft_id", this.f);
        jSONObject.put("owner_id", this.g);
        jSONObject.put("music_info", JSON.toJSONString(this.h));
        if (this.i != null) {
            jSONObject.put("topic", this.i.serializeTo());
        }
    }

    public h a(long j, String str) throws JSONException {
        a a = a();
        a.a(j);
        File file = new File(this.a);
        File file2 = new File(str, file.getName());
        if (!file.equals(file2)) {
            file.renameTo(file2);
            a.a(file2.getAbsolutePath());
        }
        if (this.b != null) {
            file = new File(this.b.a);
            file2 = new File(str, file.getName());
            if (!file.equals(file2)) {
                file.renameTo(file2);
                a.a(new e(file2.getAbsolutePath(), this.b.b));
            }
        }
        if (this.c != null) {
            file = new File(this.c.a);
            file2 = new File(str, file.getName());
            if (!file.equals(file2)) {
                b.a(file, file2);
                a.b(new e(file2.getAbsolutePath(), this.c.b));
            }
        }
        file = new File(this.d);
        file2 = new File(str, file.getName());
        if (!file.equals(file2)) {
            file.renameTo(file2);
            a.b(file2.getAbsolutePath());
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            String str2;
            k kVar = (k) it.next();
            file2 = new File(kVar.a);
            File file3 = new File(str, file2.getName());
            if (!file2.equals(file3)) {
                file2.renameTo(file3);
            }
            if (TextUtils.isEmpty(kVar.b)) {
                file2 = null;
            } else {
                File file4 = new File(kVar.b);
                file2 = new File(str, file4.getName());
                if (!file4.equals(file2)) {
                    file4.renameTo(file2);
                }
            }
            String absolutePath = file3.getAbsolutePath();
            if (file2 == null) {
                str2 = null;
            } else {
                str2 = file2.getAbsolutePath();
            }
            k kVar2 = new k(absolutePath, str2, kVar.d);
            kVar2.c = kVar.c;
            arrayList.add(kVar2);
            a.a(arrayList);
        }
        return a.a();
    }

    public boolean b() {
        return this.g == 0;
    }

    public String c() {
        if (this.c != null) {
            return this.c.a;
        }
        if (this.b != null) {
            return this.b.a;
        }
        return null;
    }

    public h a(UgcVideoMusicJson ugcVideoMusicJson, String str) {
        e eVar;
        a a = a();
        if (TextUtils.isEmpty(str)) {
            eVar = null;
        } else {
            eVar = new e(str, this.c == null ? 100 : this.c.b);
        }
        return a.b(eVar).a(ugcVideoMusicJson).a();
    }

    public List<StickerTrace> d() {
        List<StickerTrace> arrayList = new ArrayList();
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                k kVar = (k) it.next();
                if (!(kVar.c == null || kVar.c.isEmpty())) {
                    arrayList.addAll(kVar.c);
                }
            }
        }
        return arrayList;
    }
}
