package com.bdj.picture.edit.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.androidexlib.widget.asyncimage.AsyncImageView;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.b.c;
import com.bdj.picture.edit.util.f;
import com.bdj.picture.edit.widget.RoundProgressBar;
import com.bdj.picture.edit.widget.SquareLayout;
import java.io.File;
import java.util.List;

public class b extends BaseAdapter {
    private List<StickerItem> a;
    private LayoutInflater b;
    private Context c;

    private static class a {
        public SquareLayout a;
        public AsyncImageView b;
        public RoundProgressBar c;
        public View d;

        private a() {
        }
    }

    public b(List<StickerItem> list, Context context) {
        this.a = list;
        this.b = LayoutInflater.from(context);
        this.c = context;
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint({"NewApi"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        View inflate;
        a aVar;
        final StickerItem stickerItem = (StickerItem) this.a.get(i);
        if (view == null) {
            inflate = this.b.inflate(e.sticker_listview_item_gridview_item, null);
            aVar = null;
        } else {
            aVar = (a) view.getTag();
            inflate = view;
        }
        if (aVar == null) {
            final a aVar2 = new a();
            aVar2.a = (SquareLayout) inflate.findViewById(d.sl_container);
            aVar2.b = (AsyncImageView) inflate.findViewById(d.img_sticker);
            aVar2.c = (RoundProgressBar) inflate.findViewById(d.prb_round);
            aVar2.d = inflate.findViewById(d.v_ring);
            aVar2.b.setAsyncCacheImage(stickerItem.thumb_url);
            aVar2.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b c;

                public void onClick(View view) {
                    final c cVar = new c(this.c.c);
                    if (cVar.a(stickerItem.local_path)) {
                        this.c.a(cVar, stickerItem);
                        return;
                    }
                    cVar.a(stickerItem);
                    aVar2.b.setClickable(false);
                    File file = new File(com.bdj.picture.edit.util.e.a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    aVar2.c.setVisibility(0);
                    aVar2.d.setVisibility(0);
                    new net.tsz.afinal.b(this.c.c.getApplicationContext(), null).a(stickerItem.image_url, stickerItem.local_path, true, new net.tsz.afinal.a.a<File>(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public /* synthetic */ void onSuccess(Object obj) {
                            a((File) obj);
                        }

                        public void onStart() {
                        }

                        public void onLoading(long j, long j2) {
                            super.onLoading(j, j2);
                            aVar2.c.setMax((int) (j / 100));
                            aVar2.c.setProgress((int) (j2 / 100));
                        }

                        public void a(File file) {
                            super.onSuccess(file);
                            stickerItem.sub_category_name = "最近使用";
                            stickerItem.down_status = "1";
                            cVar.b(stickerItem);
                            aVar2.a.setBackgroundResource(com.bdj.picture.edit.a.c.bg_sticker_item);
                            aVar2.c.setVisibility(8);
                            aVar2.d.setVisibility(8);
                            aVar2.b.setClickable(true);
                            this.b.c.a(cVar, stickerItem);
                        }

                        public void onFailure(Throwable th, int i, String str) {
                            super.onFailure(th, i, str);
                            Log.e("", "ljj-->onFailure:" + th.toString() + "--" + i + "--" + str);
                            aVar2.c.setVisibility(8);
                            aVar2.d.setVisibility(8);
                            cVar.b(stickerItem.local_path);
                            File file = new File(stickerItem.local_path);
                            if (file.exists()) {
                                file.delete();
                            }
                            aVar2.b.setClickable(true);
                        }
                    });
                }
            });
            inflate.setTag(aVar2);
        }
        return inflate;
    }

    private void a(c cVar, StickerItem stickerItem) {
        if (com.bdj.picture.edit.util.e.a(this.c, stickerItem.local_path)) {
            Activity activity = (Activity) this.c;
            Intent intent = new Intent();
            intent.putExtra(f.c, stickerItem.local_path);
            intent.putExtra(f.d, stickerItem.id);
            activity.setResult(21, intent);
            activity.finish();
            return;
        }
        cVar.b(stickerItem.local_path);
    }
}
