package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class GameFanfanleProgressView extends FrameLayout {
    private View a;
    private ProgressBar b;
    private int c = 7;
    private int d = 10;
    private Handler e = new Handler();
    private int f = 0;
    private NumberAnimTextView g;
    private TextView h;

    public GameFanfanleProgressView(Context context) {
        super(context);
        a();
    }

    public GameFanfanleProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public GameFanfanleProgressView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = View.inflate(getContext(), R.layout.view_game_fanfanle_progress, this);
        this.b = (ProgressBar) this.a.findViewById(R.id.progress_bar);
        this.g = (NumberAnimTextView) this.a.findViewById(R.id.tv_love);
        this.h = (TextView) this.a.findViewById(R.id.tv_progress);
        this.b.setMax(this.c * this.d);
    }

    public void increase(int i, long j, long j2) {
        this.h.setText(i + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.c);
        if (i <= this.f) {
            this.b.setProgress(this.d * i);
            this.f = i;
            return;
        }
        int i2 = this.d * this.f;
        for (int i3 = i2; i3 <= this.d * i; i3++) {
            this.e.postDelayed(new bq(this, i3), (long) ((i3 - i2) * 80));
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.g, TRANSLATION_X, new float[]{(float) (((getWidth() - WindowUtils.dp2Px(166)) * this.f) / this.c), (float) (((getWidth() - WindowUtils.dp2Px(166)) * i) / this.c)});
        ofFloat.setDuration((long) (((this.d * i) - i2) * 80));
        ofFloat.start();
        this.f = i;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.e.removeCallbacksAndMessages(null);
    }

    public void setInitialState(int i, long j) {
        this.f = i;
        this.b.setProgress(this.d * i);
        this.h.setText("0/" + this.c);
    }
}
