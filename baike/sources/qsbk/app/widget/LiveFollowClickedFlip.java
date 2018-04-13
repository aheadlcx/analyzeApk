package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.LiveRoom;

public class LiveFollowClickedFlip extends RelativeLayout {
    ViewFlipper a;
    TextView b;
    TextView c;
    List<LiveRoom> d;

    public LiveFollowClickedFlip(Context context) {
        super(context);
    }

    public LiveFollowClickedFlip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LiveFollowClickedFlip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_live_clicked_flip, this);
        this.a = (ViewFlipper) inflate.findViewById(R.id.live_avatar);
        this.b = (TextView) inflate.findViewById(R.id.title);
        this.c = (TextView) inflate.findViewById(R.id.sub_title);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up_in);
        loadAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up_out);
        loadAnimation2.setInterpolator(new AccelerateDecelerateInterpolator());
        if (this.a != null) {
            this.a.setInAnimation(loadAnimation);
            this.a.setOutAnimation(loadAnimation2);
        }
    }

    public void setLiveRooms(List<LiveRoom> list) {
        this.d = list;
        a();
    }

    private void a() {
        if (this.d == null || this.d.size() <= 0) {
            this.a.stopFlipping();
            this.a.removeAllViews();
            return;
        }
        this.b.setText(this.d.size() + "位已粉主播");
        this.c.setText("正在直播");
        this.a.stopFlipping();
        this.a.removeAllViews();
        for (LiveRoom liveRoom : this.d) {
            this.a.addView(a(liveRoom.author.headurl));
        }
        if (this.a.getChildCount() > 1) {
            this.a.startFlipping();
        }
    }

    private ImageView a(String str) {
        ImageView simpleDraweeView = new SimpleDraweeView(getContext());
        FrescoImageloader.displayAvatar(simpleDraweeView, str);
        simpleDraweeView.setLayoutParams(new LayoutParams(-1, -1));
        return simpleDraweeView;
    }
}
