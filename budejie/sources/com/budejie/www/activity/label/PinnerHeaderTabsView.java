package com.budejie.www.activity.label;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.R;
import com.budejie.www.util.i;

public class PinnerHeaderTabsView extends RelativeLayout {
    Handler a = new Handler(this) {
        final /* synthetic */ PinnerHeaderTabsView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (((Integer) message.obj).intValue() == 0) {
                this.a.c.setChecked(true);
            }
        }
    };
    private Context b;
    private RadioButton c;
    private a d;
    private int e;
    private int f = 0;
    private OnClickListener g = new OnClickListener(this) {
        final /* synthetic */ PinnerHeaderTabsView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.c && this.a.d != null) {
                this.a.d.a(StatisticCodeTable.HOT);
            }
        }
    };

    public interface a {
        void a(String str);
    }

    public PinnerHeaderTabsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        this.e = i.a().a(context);
        a();
    }

    private void a() {
        addView(LayoutInflater.from(this.b).inflate(R.layout.label_details_head_pinner, null), new LayoutParams(-1, -2));
        this.c = (RadioButton) findViewById(R.id.mRbHottest);
        this.c.setOnClickListener(this.g);
    }

    public void setPinnerHeadState(int i) {
        Message obtainMessage = this.a.obtainMessage();
        obtainMessage.obj = Integer.valueOf(i);
        this.a.sendMessage(obtainMessage);
    }

    public void setPinnerHeadClickListener(a aVar) {
        this.d = aVar;
    }
}
