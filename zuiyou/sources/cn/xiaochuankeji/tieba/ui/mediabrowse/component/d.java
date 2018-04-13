package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class d extends FragmentPagerAdapter {
    private final long a;
    private ArrayList<a> b;
    private ArrayList<a> c;
    private final ArrayList<c> d = new ArrayList();
    private Context e;
    private boolean f;

    public d(FragmentManager fragmentManager, Context context, long j, ArrayList<a> arrayList, ArrayList<a> arrayList2) {
        super(fragmentManager);
        this.e = context;
        this.a = j;
        this.b = arrayList;
        this.c = arrayList2;
        this.f = true;
    }

    public Fragment getItem(int i) {
        Fragment a;
        a aVar = (a) this.b.get(i);
        a aVar2 = this.c == null ? null : (a) this.c.get(i);
        if (aVar.getType() == Type.kVideo) {
            a = g.a(i, this.f, this.a, (a) this.b.get(i), aVar2);
        } else if (aVar.getType() == Type.kGif) {
            a = a.a(i, this.f, this.a, (a) this.b.get(i), aVar2);
        } else if (aVar.getType() == Type.kMP4) {
            a = i.a(i, this.f, this.a, (a) this.b.get(i), aVar2);
        } else {
            a = b.a(i, this.f, this.a, (a) this.b.get(i), aVar2);
        }
        if (this.f) {
            this.f = false;
        }
        return a;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        c cVar = (c) super.instantiateItem(viewGroup, i);
        this.d.add(cVar);
        return cVar;
    }

    public int getCount() {
        return this.b.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        c cVar = (c) obj;
        cVar.e();
        this.d.remove(cVar);
    }

    public ArrayList<c> a() {
        return this.d;
    }

    public void b() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((c) it.next()).e();
        }
        this.d.clear();
    }

    public void a(List<ServerVideo> list) {
        for (ServerVideo serverVideo : list) {
            if (serverVideo != null) {
                PictureImpl pictureImpl = new PictureImpl(null, Type.kPostPic228, serverVideo.imageId);
                PictureImpl pictureImpl2 = new PictureImpl(null, Type.kVideo, serverVideo.videoId);
                if (this.c != null && this.c.size() > 0) {
                    this.c.add(pictureImpl);
                }
                if (this.b != null) {
                    this.b.add(pictureImpl2);
                }
            }
        }
        notifyDataSetChanged();
    }
}
