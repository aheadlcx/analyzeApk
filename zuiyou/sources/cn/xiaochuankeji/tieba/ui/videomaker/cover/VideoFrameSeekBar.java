package cn.xiaochuankeji.tieba.ui.videomaker.cover;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.common.medialib.g;
import cn.xiaochuankeji.tieba.common.medialib.h;
import cn.xiaochuankeji.tieba.common.medialib.j;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.c;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.d;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoFrameSeekBar extends FrameLayout {
    private a a;
    private ImageView[] b;
    private SeekBar c;
    private int d;
    private b e;
    private j f;
    private d g;

    public interface a {
        void a(int i);
    }

    private static class b extends Drawable {
        private GradientDrawable a = new GradientDrawable();
        private Drawable b;
        private Bitmap c;
        private Rect d;

        public b(Context context) {
            Resources resources = context.getResources();
            this.a.setStroke(e.a(2.0f), resources.getColor(R.color.recorder_main_color));
            this.b = resources.getDrawable(R.drawable.select_video_frame_indicator);
            this.d = new Rect();
        }

        public void a(Bitmap bitmap) {
            this.c = bitmap;
            invalidateSelf();
        }

        public boolean a() {
            return this.c == null;
        }

        public void draw(@NonNull Canvas canvas) {
            if (this.c != null) {
                Rect bounds = this.a.getBounds();
                int width = (int) (((float) this.c.getWidth()) / ((((float) bounds.width()) * 1.0f) / ((float) bounds.height())));
                int height = (this.c.getHeight() - width) / 2;
                this.d.set(0, height, this.c.getWidth(), width + height);
                canvas.drawBitmap(this.c, this.d, bounds, null);
            }
            this.a.draw(canvas);
            this.b.draw(canvas);
        }

        public void setAlpha(@IntRange(from = 0, to = 255) int i) {
            this.a.setAlpha(i);
            this.b.setAlpha(i);
        }

        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            this.a.setColorFilter(colorFilter);
            this.b.setColorFilter(colorFilter);
        }

        public int getOpacity() {
            return 0;
        }

        public int getIntrinsicWidth() {
            return e.a(45.0f);
        }

        public int getIntrinsicHeight() {
            return e.a(96.0f);
        }

        public void setBounds(int i, int i2, int i3, int i4) {
            super.setBounds(i, i2, i3, i4);
            this.a.setBounds(i, i2, i3, e.a(60.0f) + i2);
            int intrinsicWidth = (((i3 - i) - this.b.getIntrinsicWidth()) / 2) + i;
            int intrinsicHeight = i4 - this.b.getIntrinsicHeight();
            this.b.setBounds(intrinsicWidth, intrinsicHeight, this.b.getIntrinsicWidth() + intrinsicWidth, this.b.getIntrinsicHeight() + intrinsicHeight);
        }
    }

    public VideoFrameSeekBar(Context context) {
        super(context);
    }

    public VideoFrameSeekBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoFrameSeekBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(Context context, String str, String str2, final int i) {
        int i2;
        try {
            this.g = d.a(getContext(), new JSONObject(str2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f = new j(str);
        this.f.a(new h(this) {
            final /* synthetic */ VideoFrameSeekBar a;

            {
                this.a = r1;
            }

            public void a(int i, g gVar) {
                c a = this.a.g.a(i);
                gVar.a = a.a();
                gVar.b = a.b();
            }
        });
        this.f.a(new cn.xiaochuankeji.tieba.common.medialib.j.b(this) {
            final /* synthetic */ VideoFrameSeekBar b;

            public void a(j jVar) {
                if (jVar.a() > 0) {
                    this.b.c.setEnabled(true);
                    this.b.c.setMax(jVar.a());
                    this.b.c.setProgress(i);
                    this.b.b();
                    return;
                }
                cn.xiaochuankeji.tieba.background.utils.g.a("缩略图获取失败");
            }
        });
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        addView(linearLayout, new LayoutParams(e.a(45.0f) * 6, e.a(60.0f)));
        this.b = new ImageView[6];
        for (i2 = 0; i2 < 6; i2++) {
            View imageView = new ImageView(context);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
            layoutParams.weight = 1.0f;
            linearLayout.addView(imageView, layoutParams);
            this.b[i2] = imageView;
        }
        this.c = new SeekBar(context);
        addView(this.c, new LayoutParams(-1, -1));
        this.c.setBackgroundDrawable(null);
        this.c.setProgressDrawable(null);
        this.e = new b(context);
        this.c.setThumb(this.e);
        i2 = this.e.getIntrinsicWidth() / 2;
        this.c.setPadding(i2, 0, i2, 0);
        this.c.setEnabled(false);
        this.c.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ VideoFrameSeekBar a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z && this.a.a != null) {
                    this.a.d = -1;
                    this.a.a.a(i);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    private void b() {
        int i = 0;
        int a = this.f.a() / 5;
        int i2 = 0;
        while (i < 6) {
            this.f.a(i2, e.a(45.0f), new cn.xiaochuankeji.tieba.common.medialib.j.a(this) {
                final /* synthetic */ VideoFrameSeekBar b;

                public void a(int i, Bitmap bitmap) {
                    this.b.b[i].setImageBitmap(bitmap);
                }
            });
            i2 += a;
            i++;
        }
    }

    public int getProgress() {
        if (this.d != this.c.getProgress()) {
            return -1;
        }
        if (this.e.a()) {
            return -2;
        }
        return this.d;
    }

    public void a(int i, Bitmap bitmap) {
        this.d = i;
        this.e.a(bitmap);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(e.a(45.0f) * 6, 1073741824), MeasureSpec.makeMeasureSpec(e.a(96.0f), 1073741824));
    }

    public void a() {
        this.f.b();
    }
}
