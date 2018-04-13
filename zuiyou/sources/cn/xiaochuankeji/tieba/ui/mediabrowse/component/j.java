package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.d;

public class j extends LinearLayout {
    private ImageView a = ((ImageView) findViewById(R.id.ivFlag));
    private TextView b = ((TextView) findViewById(R.id.tvPosition));
    private TextView c = ((TextView) findViewById(R.id.tvDuration));
    private ProgressBar d = ((ProgressBar) findViewById(R.id.progressBar));
    private long e;

    public j(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_video_progress, this);
    }

    public void setDuration(long j) {
        this.e = j;
        this.c.setText("/" + d.a(j));
    }

    public boolean a() {
        return this.e > 0;
    }

    public void a(int i, boolean z) {
        this.b.setText(d.a((long) i));
        if (0 != this.e) {
            this.d.setProgress((int) (((((float) i) * 1.0f) / (((float) this.e) * 1.0f)) * 100.0f));
        }
        this.a.setSelected(!z);
    }
}
