package qsbk.app.video;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.UIHelper;

class u extends AsyncTask<Integer, Pair<Integer, Bitmap>, ArrayList<Bitmap>> {
    final /* synthetic */ int a;
    final /* synthetic */ long b;
    final /* synthetic */ MediaMetadataRetriever c;
    final /* synthetic */ VideoCropView d;

    u(VideoCropView videoCropView, int i, long j, MediaMetadataRetriever mediaMetadataRetriever) {
        this.d = videoCropView;
        this.a = i;
        this.b = j;
        this.c = mediaMetadataRetriever;
    }

    protected /* synthetic */ void b(Object[] objArr) {
        a((Pair[]) objArr);
    }

    protected void a() {
        int i = 5;
        super.a();
        int i2 = this.a;
        if (i2 <= 5) {
            i = i2;
        }
        i2 = this.d.j.getResources().getDimensionPixelSize(R.dimen.video_frame_length);
        this.d.l = (this.d.k - (i2 * i)) / 2;
        this.d.m = ((i * i2) + this.d.k) / 2;
        this.d.n = (int) ((3000 / this.b) * ((long) i2));
        LayoutParams layoutParams = (LayoutParams) this.d.f.getLayoutParams();
        layoutParams.leftMargin = this.d.l;
        this.d.f.setLayoutParams(layoutParams);
        this.d.d.mLeft = layoutParams.leftMargin;
        layoutParams = (LayoutParams) this.d.g.getLayoutParams();
        if (this.d.q < ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL) {
            layoutParams.leftMargin = this.d.m - this.d.j.getResources().getDimensionPixelSize(R.dimen.video_drag_width);
        } else {
            layoutParams.leftMargin = (i2 + this.d.l) + this.d.j.getResources().getDimensionPixelSize(R.dimen.video_drag_width);
        }
        this.d.g.setLayoutParams(layoutParams);
        this.d.e.mLeft = layoutParams.leftMargin;
        DebugUtil.debug(VideoCropView.a, "width:" + this.d.f.getWidth());
        this.d.c.addView(this.d.a(this.d.l));
        for (i = 0; i < this.a; i++) {
            View e = this.d.a(this.d.j.getResources().getDimensionPixelSize(R.dimen.video_frame_length));
            e.setTag(Integer.valueOf(i));
            e.setImageResource(UIHelper.getDefaultImageTileBackground());
            this.d.c.addView(e);
        }
        this.d.c.addView(this.d.a(this.d.l));
        this.d.g.getViewTreeObserver().addOnPreDrawListener(new v(this));
    }

    protected ArrayList<Bitmap> a(Integer[] numArr) {
        ArrayList<Bitmap> arrayList = new ArrayList();
        long j = 0;
        long j2 = ((long) this.a) * this.b;
        while (j < j2) {
            Bitmap frameAtTime = this.c.getFrameAtTime(1000 * j, 2);
            j += this.b;
            d(new Pair[]{new Pair(Integer.valueOf(arrayList.size()), frameAtTime)});
            arrayList.add(frameAtTime);
        }
        return arrayList;
    }

    protected void a(Pair<Integer, Bitmap>... pairArr) {
        super.b(pairArr);
        Pair pair = pairArr[0];
        View findViewWithTag = this.d.c.findViewWithTag(pair.first);
        if (findViewWithTag instanceof ImageView) {
            ((ImageView) findViewWithTag).setImageBitmap((Bitmap) pair.second);
        }
    }

    protected void a(ArrayList<Bitmap> arrayList) {
    }
}
