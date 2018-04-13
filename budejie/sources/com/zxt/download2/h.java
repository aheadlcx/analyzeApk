package com.zxt.download2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import java.util.List;

@SuppressLint({"ResourceAsColor"})
public class h extends ArrayAdapter<f> {
    private LayoutInflater a;
    private List<f> b;
    private Context c;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public h(Context context, int i, List<f> list) {
        super(context, i, list);
        this.b = list;
        this.a = LayoutInflater.from(context);
        this.c = context;
    }

    public int getCount() {
        return this.b.size();
    }

    public f a(int i) {
        return (f) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        h$a h_a;
        f fVar = (f) this.b.get(i);
        if (view == null) {
            view = this.a.inflate(R.layout.download_list_item, null);
            h_a = new h$a(this);
            h_a.a = (AsyncImageView) view.findViewById(R.id.thumbnail);
            h_a.b = (TextView) view.findViewById(R.id.title);
            h_a.d = (TextView) view.findViewById(R.id.size);
            h_a.e = (TextView) view.findViewById(R.id.speed);
            h_a.c = (TextView) view.findViewById(R.id.state);
            h_a.h = (ImageView) view.findViewById(R.id.ic_state);
            h_a.f = (ProgressBar) view.findViewById(R.id.progress);
            h_a.g = (ImageView) view.findViewById(R.id.DownLoadImageView);
            h_a.i = (LinearLayout) view.findViewById(R.id.MaskLayout);
            h_a.f.setMax(100);
            view.setTag(h_a);
        } else {
            h_a = (h$a) view.getTag();
        }
        h_a.b.setText(fVar.b());
        h_a.d.setText(a(fVar.e(), fVar.f()));
        if (fVar.a() != null) {
            h_a.a.setPostImage(fVar.a(), "");
        }
        h_a.e.setText(String.format(this.c.getString(R.string.download_only_speed), new Object[]{Integer.valueOf(fVar.h())}));
        h_a.f.setProgress(fVar.g());
        switch (h$1.a[((f) this.b.get(i)).i().ordinal()]) {
            case 1:
                h_a.h.setVisibility(8);
                h_a.g.setVisibility(0);
                h_a.c.setVisibility(0);
                h_a.i.setVisibility(0);
                h_a.f.setVisibility(0);
                h_a.e.setVisibility(8);
                h_a.c.setText("已暂停");
                h_a.g.setImageResource(R.drawable.download_pause);
                h_a.f.setProgressDrawable(this.c.getResources().getDrawable(R.drawable.download_progressbar_pause));
                break;
            case 2:
                h_a.h.setVisibility(8);
                h_a.g.setVisibility(8);
                h_a.c.setVisibility(0);
                h_a.i.setVisibility(0);
                h_a.f.setVisibility(8);
                h_a.e.setVisibility(8);
                h_a.c.setText(R.string.download_failed);
                break;
            case 3:
                h_a.h.setVisibility(8);
                h_a.g.setVisibility(0);
                h_a.c.setVisibility(0);
                h_a.i.setVisibility(0);
                h_a.f.setVisibility(0);
                h_a.e.setVisibility(0);
                h_a.f.setProgressDrawable(this.c.getResources().getDrawable(R.drawable.download_progressbar));
                h_a.g.setImageResource(R.drawable.download_play);
                h_a.c.setText(R.string.download_downloading);
                h_a.f.setIndeterminate(false);
                break;
            case 4:
                h_a.h.setVisibility(0);
                h_a.g.setVisibility(8);
                h_a.c.setVisibility(8);
                h_a.i.setVisibility(8);
                h_a.f.setVisibility(8);
                h_a.e.setVisibility(8);
                h_a.f.setProgress(100);
                h_a.f.setIndeterminate(false);
                h_a.c.setText(R.string.download_finished);
                h_a.h.setImageResource(R.drawable.downloaded_icon);
                h_a.d.setText(b(fVar.f()));
                break;
            case 5:
                h_a.h.setVisibility(8);
                h_a.g.setVisibility(0);
                h_a.c.setVisibility(0);
                h_a.i.setVisibility(0);
                h_a.f.setVisibility(0);
                h_a.e.setVisibility(8);
                h_a.f.setIndeterminate(false);
                h_a.c.setText(R.string.download_initial);
                h_a.g.setImageResource(R.drawable.download_play);
                h_a.f.setProgressDrawable(this.c.getResources().getDrawable(R.drawable.download_progressbar));
                break;
        }
        return view;
    }

    private String a(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(50);
        if ((((float) i) / 1024.0f) / 1024.0f < 1.0f) {
            stringBuilder.append(String.format("%1$.2f K / ", new Object[]{Float.valueOf(((float) i) / 1024.0f)}));
        } else {
            stringBuilder.append(String.format("%1$.2f M / ", new Object[]{Float.valueOf((((float) i) / 1024.0f) / 1024.0f)}));
        }
        if ((((float) i2) / 1024.0f) / 1024.0f < 1.0f) {
            stringBuilder.append(String.format("%1$.2f K ", new Object[]{Float.valueOf(((float) i2) / 1024.0f)}));
        } else {
            stringBuilder.append(String.format("%1$.2f M ", new Object[]{Float.valueOf((((float) i2) / 1024.0f) / 1024.0f)}));
        }
        return stringBuilder.toString();
    }

    private String b(int i) {
        StringBuilder stringBuilder = new StringBuilder(50);
        if ((((float) i) / 1024.0f) / 1024.0f < 1.0f) {
            stringBuilder.append(String.format("%1$.2f K ", new Object[]{Float.valueOf(((float) i) / 1024.0f)}));
        } else {
            stringBuilder.append(String.format("%1$.2f M ", new Object[]{Float.valueOf((((float) i) / 1024.0f) / 1024.0f)}));
        }
        return stringBuilder.toString();
    }
}
