package cn.tatagou.sdk.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.view.a;

public class AutoPullAbleListView extends ListView implements b {
    private AutoPullAbleListView$a a;
    private TextView b;
    private int c = 0;
    private boolean d = true;
    private boolean e = true;
    private boolean f = true;
    private String g;
    private a h;
    private a i = new a(this) {
        final /* synthetic */ AutoPullAbleListView a;

        {
            this.a = r1;
        }

        public void onStopScroll(boolean z, int i, int i2) {
            super.onStopScroll(z, i, i2);
            if (i == i2) {
                this.a.a();
            }
            if (this.a.h != null) {
                this.a.h.onStopScroll(z, i, i2);
            }
        }

        public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
            super.onScrollList(absListView, i, i2, z);
            if (this.a.h != null) {
                this.a.h.onScrollList(absListView, i, i2, z);
            }
        }
    };

    public void setFinishText(String str) {
        this.g = str;
    }

    public void setPullDownFlag(boolean z) {
        this.e = z;
    }

    public void setLoadDataFlag(boolean z) {
        this.f = z;
    }

    public AutoPullAbleListView(Context context) {
        super(context);
        a(context);
    }

    public AutoPullAbleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AutoPullAbleListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ttg_load_more_auto_bottom, null);
        this.b = (TextView) inflate.findViewById(R.id.ttg_loadstate_tv);
        addFooterView(inflate, null, false);
        setOnScrollListener(this.i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.d = false;
                break;
            case 1:
                this.d = true;
                a();
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a() {
        if (reachBottom() && this.a != null && this.c != 1 && this.d && this.f) {
            this.a.onLoad(this);
            changeState(1);
        }
    }

    public void changeState(int i) {
        this.c = i;
        switch (i) {
            case 0:
                if (this.b != null) {
                    this.b.setText(R.string.pullup_to_load);
                    this.b.setTextSize(14.0f);
                    return;
                }
                return;
            case 1:
                if (this.b != null) {
                    this.b.setText(R.string.more);
                    this.b.setTextSize(14.0f);
                    return;
                }
                return;
            case 2:
                if (this.g == null) {
                    this.g = getResources().getString(R.string.ttg_icon_pull_data);
                }
                if (this.b != null) {
                    this.b.setText(this.g);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void finishLoading(int i) {
        changeState(i);
    }

    public boolean canPullDown() {
        if (getCount() == 0) {
            return this.e;
        }
        if (!this.e || getFirstVisiblePosition() != 0 || getChildAt(0) == null || getChildAt(0).getTop() < 0) {
            return false;
        }
        return this.e;
    }

    public void setOnLoadListener(AutoPullAbleListView$a autoPullAbleListView$a) {
        this.a = autoPullAbleListView$a;
    }

    public void setOnLoadScrollListener(a aVar) {
        this.h = aVar;
    }

    public boolean reachBottom() {
        if (getCount() == 0) {
            return true;
        }
        if (getLastVisiblePosition() == getCount() - 1) {
            View childAt = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
            if (childAt != null && childAt.getTop() < getMeasuredHeight()) {
                return true;
            }
        }
        return false;
    }
}
