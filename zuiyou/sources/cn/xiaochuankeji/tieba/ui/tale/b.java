package cn.xiaochuankeji.tieba.ui.tale;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class b extends Adapter<cn.xiaochuankeji.tieba.ui.tale.holder.b> {
    public boolean a;
    public String b;
    public OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onClick(final View view) {
            Object tag = view.getTag();
            if (tag instanceof a) {
                final int indexOf = this.a.f.indexOf(tag);
                if (indexOf >= 0) {
                    final ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < this.a.f.size(); i++) {
                        a aVar = (a) this.a.f.get(i);
                        arrayList.add(a("gif".equalsIgnoreCase(aVar.f), aVar.c));
                    }
                    PermissionItem permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE");
                    permissionItem.runIgnorePermission = false;
                    permissionItem.settingText = "设置";
                    permissionItem.deniedMessage = "开启以下权限才能正常浏览图片和视频";
                    permissionItem.needGotoSetting = true;
                    cn.xiaochuankeji.aop.permission.a.a(view.getContext()).a(permissionItem, new e(this) {
                        final /* synthetic */ AnonymousClass1 d;

                        public void permissionGranted() {
                            MediaBrowseActivity.a(view.getContext(), indexOf, null, arrayList, false, EntranceType.PostDetail);
                        }

                        public void permissionDenied() {
                            g.a("开启以下权限才能正常浏览图片和视频");
                        }
                    });
                }
            }
        }

        private cn.htjyb.b.a a(boolean z, long j) {
            Type type;
            if (z) {
                type = Type.kGif;
            } else {
                type = Type.kCommentOriginImg;
            }
            return cn.xiaochuankeji.tieba.background.a.f().a(type, j);
        }
    };
    private LinkedList<TaleComment> d = new LinkedList();
    private ArrayList<Long> e = new ArrayList();
    private List<a> f = new ArrayList();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((cn.xiaochuankeji.tieba.ui.tale.holder.b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public /* synthetic */ void onViewAttachedToWindow(ViewHolder viewHolder) {
        a((cn.xiaochuankeji.tieba.ui.tale.holder.b) viewHolder);
    }

    public /* synthetic */ void onViewDetachedFromWindow(ViewHolder viewHolder) {
        b((cn.xiaochuankeji.tieba.ui.tale.holder.b) viewHolder);
    }

    public cn.xiaochuankeji.tieba.ui.tale.holder.b a(ViewGroup viewGroup, int i) {
        return cn.xiaochuankeji.tieba.ui.tale.holder.b.a(viewGroup, i, this);
    }

    public void a(cn.xiaochuankeji.tieba.ui.tale.holder.b bVar, int i) {
        bVar.a(i);
        bVar.a(a(i), i);
    }

    public void a(cn.xiaochuankeji.tieba.ui.tale.holder.b bVar) {
        super.onViewAttachedToWindow(bVar);
        bVar.a();
    }

    public void b(cn.xiaochuankeji.tieba.ui.tale.holder.b bVar) {
        super.onViewDetachedFromWindow(bVar);
        bVar.b();
    }

    public TaleComment a(int i) {
        return (TaleComment) this.d.get(i);
    }

    public int getItemCount() {
        return this.d.size();
    }

    public int getItemViewType(int i) {
        return a(i).layoutType;
    }

    public LinkedList<TaleComment> a() {
        return this.d;
    }

    public void a(TaleComment taleComment) {
        int size = this.d.size();
        this.d.add(taleComment);
        notifyItemInserted(size);
    }

    public void b(int i) {
        this.d.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.d.size() - i);
    }

    public void b(TaleComment taleComment) {
        this.d.remove(taleComment);
        notifyDataSetChanged();
    }

    public void a(List<TaleComment> list) {
        int i = 0;
        int i2 = 0;
        while (i2 < this.d.size()) {
            if (((TaleComment) this.d.get(i2)).layoutType == R.layout.item_tale_detail_comment_empty) {
                break;
            }
            i2++;
        }
        i2 = -1;
        if (i2 >= 0) {
            b(i2);
        }
        Collection arrayList = new ArrayList();
        int size = this.d.size();
        while (i < list.size()) {
            TaleComment taleComment = (TaleComment) list.get(i);
            if (!this.e.contains(Long.valueOf(taleComment.id))) {
                arrayList.add(taleComment);
            }
            i++;
        }
        this.d.addAll(arrayList);
        notifyItemRangeChanged(size, this.d.size() - size);
    }

    public void b(List<TaleComment> list) {
        Collection arrayList = new ArrayList();
        for (int i = 0; i < this.d.size(); i++) {
            TaleComment taleComment = (TaleComment) this.d.get(i);
            if (!(taleComment.layoutType == R.layout.item_tale_detail_comment || taleComment.layoutType == R.layout.item_tale_detail_comment_empty)) {
                arrayList.add(taleComment);
            }
        }
        arrayList.addAll(list);
        this.e.clear();
        this.d.clear();
        this.d.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void b() {
        int i = 0;
        while (i < this.d.size()) {
            if (((TaleComment) this.d.get(i)).layoutType == R.layout.item_tale_detail_social) {
                break;
            }
            i++;
        }
        i = -1;
        if (i >= 0) {
            notifyItemChanged(i);
        }
    }

    public void c(TaleComment taleComment) {
        int i = 0;
        while (i < this.d.size()) {
            if (((TaleComment) this.d.get(i)).layoutType == R.layout.item_tale_detail_comment) {
                break;
            }
            i++;
        }
        i = -1;
        if (i > 0) {
            this.d.add(i, taleComment);
            notifyItemInserted(i);
            notifyItemRangeChanged(i, this.d.size() - i);
        } else {
            List arrayList = new ArrayList();
            arrayList.add(taleComment);
            a(arrayList);
        }
        this.e.add(Long.valueOf(taleComment.id));
    }

    public void c() {
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public void c(List<a> list) {
        this.f.clear();
        this.f.addAll(list);
    }
}
