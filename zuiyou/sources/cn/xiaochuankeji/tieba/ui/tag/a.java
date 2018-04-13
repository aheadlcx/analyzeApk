package cn.xiaochuankeji.tieba.ui.tag;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.LongSparseArray;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTagList;
import com.alibaba.fastjson.JSON;
import com.iflytek.cloud.SpeechConstant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.greenrobot.eventbus.c;
import rx.j;

public class a {
    private static final String[] a = new String[]{"推荐", "视频", "图文"};
    private ArrayList<NavigatorTag> b = new ArrayList();
    private LongSparseArray<Integer> c = new LongSparseArray();
    private long d;
    private int e = -1;

    public a() {
        p();
    }

    public ArrayList<NavigatorTag> a() {
        return this.b;
    }

    public void a(ArrayList<NavigatorTag> arrayList) {
        this.b.clear();
        this.b.addAll(arrayList);
        NavigatorTagList navigatorTagList = new NavigatorTagList();
        navigatorTagList.list = arrayList;
        b(navigatorTagList);
    }

    public void a(long j) {
        this.d = j;
    }

    public int b() {
        for (int i = 0; i < this.b.size(); i++) {
            if (this.d == ((NavigatorTag) this.b.get(i)).id) {
                return i;
            }
        }
        return 0;
    }

    public static void c() {
        a(false);
    }

    public static void a(final boolean z) {
        new cn.xiaochuankeji.tieba.api.tag.a().a().a(rx.f.a.c()).b(new j<NavigatorTagList>() {
            public /* synthetic */ void onNext(Object obj) {
                a((NavigatorTagList) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(NavigatorTagList navigatorTagList) {
                if (navigatorTagList != null && navigatorTagList.list != null && !navigatorTagList.list.isEmpty()) {
                    NavigatorTag navigatorTag;
                    ArrayList n = a.o();
                    ArrayList arrayList = new ArrayList();
                    if (n != null) {
                        Iterator it = n.iterator();
                        while (it.hasNext()) {
                            navigatorTag = (NavigatorTag) it.next();
                            if (navigatorTag != null) {
                                arrayList.add(Long.valueOf(navigatorTag.id));
                            }
                        }
                    }
                    ArrayList arrayList2 = navigatorTagList.list;
                    for (int i = 0; i < arrayList2.size(); i++) {
                        navigatorTag = (NavigatorTag) arrayList2.get(i);
                        if (!arrayList.contains(Long.valueOf(navigatorTag.id))) {
                            navigatorTag.isNew = true;
                        }
                    }
                    if (z) {
                        cn.xiaochuankeji.tieba.background.a.q().b(arrayList2);
                        c.a().d(new cn.xiaochuankeji.tieba.ui.homepage.a());
                    }
                    a.b(navigatorTagList);
                }
            }
        });
    }

    public void b(ArrayList<NavigatorTag> arrayList) {
        Collection arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NavigatorTag navigatorTag = (NavigatorTag) it.next();
            if (h(navigatorTag)) {
                arrayList2.add(navigatorTag);
            }
        }
        this.b.clear();
        this.b.addAll(arrayList2);
    }

    private static void b(final NavigatorTagList navigatorTagList) {
        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable() {
            public void run() {
                if (navigatorTagList != null && navigatorTagList.list != null && navigatorTagList.list.size() > 0) {
                    Editor edit = AppController.instance().getCommonPreference().edit();
                    edit.putString("navtag_list", JSON.toJSONString(navigatorTagList));
                    edit.apply();
                }
            }
        });
    }

    private static ArrayList<NavigatorTag> o() {
        ArrayList<NavigatorTag> arrayList;
        int i;
        NavigatorTag navigatorTag;
        SharedPreferences commonPreference = AppController.instance().getCommonPreference();
        if (commonPreference.contains("navtag_list")) {
            try {
                NavigatorTagList navigatorTagList = (NavigatorTagList) JSON.parseObject(commonPreference.getString("navtag_list", "navtag_list"), NavigatorTagList.class);
                if (navigatorTagList != null) {
                    arrayList = navigatorTagList.list;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    if (arrayList.isEmpty()) {
                        for (i = 0; i < 3; i++) {
                            navigatorTag = new NavigatorTag();
                            navigatorTag.name = a[i];
                            navigatorTag.id = (long) (i + 1);
                            navigatorTag.type = "content";
                            if (i == 0) {
                                navigatorTag.ename = "index";
                                navigatorTag.frozen = 1;
                                navigatorTag.action_info.filter = SpeechConstant.PLUS_LOCAL_ALL;
                            }
                            if (i == 1) {
                                navigatorTag.ename = "index-video";
                                navigatorTag.action_info.filter = "video";
                            }
                            if (i == 2) {
                                navigatorTag.ename = "index-imgtxt";
                                navigatorTag.action_info.filter = "imgtxt";
                            }
                            arrayList.add(navigatorTag);
                        }
                    }
                    return arrayList;
                }
            } catch (Exception e) {
                arrayList = null;
            }
        }
        arrayList = null;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (arrayList.isEmpty()) {
            for (i = 0; i < 3; i++) {
                navigatorTag = new NavigatorTag();
                navigatorTag.name = a[i];
                navigatorTag.id = (long) (i + 1);
                navigatorTag.type = "content";
                if (i == 0) {
                    navigatorTag.ename = "index";
                    navigatorTag.frozen = 1;
                    navigatorTag.action_info.filter = SpeechConstant.PLUS_LOCAL_ALL;
                }
                if (i == 1) {
                    navigatorTag.ename = "index-video";
                    navigatorTag.action_info.filter = "video";
                }
                if (i == 2) {
                    navigatorTag.ename = "index-imgtxt";
                    navigatorTag.action_info.filter = "imgtxt";
                }
                arrayList.add(navigatorTag);
            }
        }
        return arrayList;
    }

    private void p() {
        NavigatorTag navigatorTag;
        if (this.b.isEmpty()) {
            ArrayList o = o();
            if (o != null) {
                Iterator it = o.iterator();
                while (it.hasNext()) {
                    navigatorTag = (NavigatorTag) it.next();
                    if (h(navigatorTag)) {
                        this.b.add(navigatorTag);
                    }
                }
            }
        }
        int i = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            navigatorTag = (NavigatorTag) this.b.get(i2);
            if (navigatorTag.focus_weight > i) {
                this.e = i2;
                i = navigatorTag.focus_weight;
            }
        }
        c();
    }

    public int d() {
        return this.e;
    }

    private boolean h(NavigatorTag navigatorTag) {
        if (navigatorTag.id <= 0 || TextUtils.isEmpty(navigatorTag.name) || TextUtils.isEmpty(navigatorTag.ename) || TextUtils.isEmpty(navigatorTag.type)) {
            return false;
        }
        if (a(navigatorTag)) {
            return cn.xiaochuankeji.tieba.background.utils.c.a.c().a();
        }
        if (b(navigatorTag) || d(navigatorTag)) {
            return true;
        }
        if (c(navigatorTag)) {
            return cn.xiaochuankeji.tieba.background.utils.c.a.c().b();
        }
        if (e(navigatorTag)) {
            return true;
        }
        return false;
    }

    public boolean a(NavigatorTag navigatorTag) {
        return "tale".equalsIgnoreCase(navigatorTag.type);
    }

    public boolean b(NavigatorTag navigatorTag) {
        return "follow".equalsIgnoreCase(navigatorTag.type);
    }

    public boolean c(NavigatorTag navigatorTag) {
        return "voice".equalsIgnoreCase(navigatorTag.type);
    }

    public boolean d(NavigatorTag navigatorTag) {
        return "flow".equalsIgnoreCase(navigatorTag.type);
    }

    public boolean e(NavigatorTag navigatorTag) {
        return "content".equalsIgnoreCase(navigatorTag.type);
    }

    public boolean e() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            if (a((NavigatorTag) it.next())) {
                return cn.xiaochuankeji.tieba.background.utils.c.a.c().a();
            }
        }
        return false;
    }

    public boolean f() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            if (d((NavigatorTag) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean g() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            if (c((NavigatorTag) it.next())) {
                return cn.xiaochuankeji.tieba.background.utils.c.a.c().b();
            }
        }
        return false;
    }

    public int h() {
        for (int i = 0; i < this.b.size(); i++) {
            NavigatorTag navigatorTag = (NavigatorTag) this.b.get(i);
            if (e(navigatorTag) && "index".equalsIgnoreCase(navigatorTag.ename)) {
                return i;
            }
        }
        return -1;
    }

    public int i() {
        for (int i = 0; i < this.b.size(); i++) {
            if (a((NavigatorTag) this.b.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public int j() {
        for (int i = 0; i < this.b.size(); i++) {
            if (d((NavigatorTag) this.b.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public int k() {
        for (int i = 0; i < this.b.size(); i++) {
            if (c((NavigatorTag) this.b.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public boolean l() {
        for (int i = 5; i < this.b.size(); i++) {
            if (((NavigatorTag) this.b.get(i)).isNew) {
                return true;
            }
        }
        return false;
    }

    public void m() {
        SharedPreferences c = cn.xiaochuankeji.tieba.background.a.c();
        this.c.clear();
        for (int i = 0; i < this.b.size(); i++) {
            NavigatorTag navigatorTag = (NavigatorTag) this.b.get(i);
            String str = "_last_refresh_tab_" + navigatorTag.ename;
            long j = c.getLong(str, -1);
            navigatorTag.crumb = -1;
            if (j > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - j >= 21600000) {
                    c.edit().putLong(str, currentTimeMillis).apply();
                    navigatorTag.crumb = 1;
                }
            }
            this.c.put(navigatorTag.id, Integer.valueOf(navigatorTag.crumb));
        }
    }

    public void f(NavigatorTag navigatorTag) {
        if (navigatorTag != null) {
            this.c.put(navigatorTag.id, Integer.valueOf(-1));
        }
    }

    public void g(NavigatorTag navigatorTag) {
        if (navigatorTag != null) {
            cn.xiaochuankeji.tieba.background.a.c().edit().putLong("_last_refresh_tab_" + navigatorTag.ename, System.currentTimeMillis()).apply();
            this.c.put(navigatorTag.id, Integer.valueOf(-1));
        }
    }

    public int b(long j) {
        Integer num = (Integer) this.c.get(j);
        return num == null ? -1 : num.intValue();
    }
}
