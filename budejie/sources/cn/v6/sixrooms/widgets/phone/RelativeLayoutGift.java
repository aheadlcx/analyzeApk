package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.animation.GiftAnimationManager$CallBackGiftStatus;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import cn.v6.sixrooms.view.interfaces.OnRoomTypeChangeListener;
import cn.v6.sixrooms.view.interfaces.RoomTypeable;
import cn.v6.sixrooms.widgets.phone.SurfaceViewGift.InterfaceSurfaceAnimation;

public class RelativeLayoutGift extends RelativeLayout implements OnClickListener, GiftAnimationManager$CallBackGiftStatus, OnRoomTypeChangeListener {
    private SurfaceViewGift a = null;
    private View b;
    private View c;
    private View d;
    private View e;
    private int f = -1;

    public RelativeLayoutGift(Context context) {
        super(context);
        a(context);
    }

    public RelativeLayoutGift(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public RelativeLayoutGift(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        if (this.a == null) {
            this.d = LayoutInflater.from(context).inflate(R.layout.phone_gift_animation, this);
            this.e = this.d.findViewById(R.id.rela_gift_animation);
            this.a = (SurfaceViewGift) this.d.findViewById(R.id.surface_gift);
            this.a.addAnimationCallBack(this);
            this.b = this.d.findViewById(R.id.img_animation_promt);
            this.c = this.d.findViewById(R.id.bt_close_animation);
            this.c.setOnClickListener(this);
        }
    }

    public void setRoomTypeable(RoomTypeable roomTypeable) {
        this.a.setRoomTypeable(roomTypeable);
        setRoomType(roomTypeable.getRoomType());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bt_close_animation) {
            this.c.setVisibility(8);
            this.b.setVisibility(8);
            SharedPreferencesUtils.setGiftShown(getContext());
            this.a.closeCurrentAnimation();
        }
    }

    public void onAnimationShow() {
        if (SharedPreferencesUtils.isFirstShowGift(getContext())) {
            this.b.setVisibility(0);
        }
        this.c.setVisibility(0);
    }

    public void closeAllAnimation() {
        this.a.clearGiftAnimations();
        onAllAnimationDismiss();
    }

    public void onAllAnimationDismiss() {
        this.b.setVisibility(8);
        this.c.setVisibility(8);
    }

    public InterfaceSurfaceAnimation getInterfaceSurfaceAnimation() {
        return this.a.getInterfaceSurfaceAnimation();
    }

    public void setRoomType(int i) {
        if (this.f != i) {
            this.f = i;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.e.getLayoutParams();
            if (i == 2 || i == 3) {
                marginLayoutParams.rightMargin = getContext().getResources().getDimensionPixelSize(R.dimen.land_gift_close_margin_right);
                marginLayoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R.dimen.land_gift_close_margin_bottom);
            } else if (i == 4) {
                marginLayoutParams.rightMargin = getContext().getResources().getDimensionPixelSize(R.dimen.ori_gift_close_margin_right);
                marginLayoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R.dimen.ori_gift_close_margin_bottom);
            } else {
                marginLayoutParams.rightMargin = getContext().getResources().getDimensionPixelSize(R.dimen.gift_close_margin_right);
                marginLayoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R.dimen.gift_close_margin_bottom);
            }
            this.e.setLayoutParams(marginLayoutParams);
            this.e.invalidate();
        }
    }

    public void onRoomTypeChange(int i) {
        setRoomType(i);
    }
}
