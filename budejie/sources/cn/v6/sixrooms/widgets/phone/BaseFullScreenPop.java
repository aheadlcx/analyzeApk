package cn.v6.sixrooms.widgets.phone;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.DensityUtil;

public abstract class BaseFullScreenPop extends PopupWindow {
    private static final String a = BaseFullScreenPop.class.getSimpleName();
    protected float ICON_PADDING_DP;
    protected final float ICON_WIDTH_DP = 35.0f;
    protected float ITEM_MARGINRIGHT_DP;
    protected float ITEM_WIDTH_DP;
    protected final float POP_ARROW_HEIGHT_DP = 6.0f;
    protected final float POP_ARROW_WIDTH_DP = 12.0f;
    protected float POP_HEIGHT_DP;
    protected final float POP_MARGIN_TOP_DP = 6.0f;
    protected int POP_WIDTH_DP;
    private final int b;
    private View c;
    private FrameLayout d;
    public View iv_arrow;
    protected BaseRoomActivity mBaseRoomActivity;
    protected boolean mIsLive;
    protected WrapRoomInfo mWrapRoomInfo;

    abstract View a();

    public WrapRoomInfo getmWrapRoomInfo() {
        return this.mWrapRoomInfo;
    }

    public void setmWrapRoomInfo(WrapRoomInfo wrapRoomInfo) {
        this.mWrapRoomInfo = wrapRoomInfo;
    }

    public BaseFullScreenPop(BaseRoomActivity baseRoomActivity, int i, int i2, int i3, float f, WrapRoomInfo wrapRoomInfo, OnDismissListener onDismissListener, boolean z) {
        super(baseRoomActivity);
        this.b = i;
        this.mBaseRoomActivity = baseRoomActivity;
        this.mWrapRoomInfo = wrapRoomInfo;
        this.mIsLive = z;
        this.POP_WIDTH_DP = i2;
        this.POP_HEIGHT_DP = (float) i3;
        this.ICON_PADDING_DP = f;
        this.ITEM_MARGINRIGHT_DP = 5.0f;
        this.ITEM_WIDTH_DP = (this.ICON_PADDING_DP * 2.0f) + 35.0f;
        setWidth(DensityUtil.dip2px((float) this.POP_WIDTH_DP));
        setHeight(DensityUtil.dip2px(this.POP_HEIGHT_DP + 6.0f));
        setAnimationStyle(16973826);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(V6Coop.getInstance().getContext().getResources().getColor(R.color.transparent)));
        setFocusable(true);
        this.c = View.inflate(this.mBaseRoomActivity, R.layout.pop_full_screen_base, null);
        this.iv_arrow = this.c.findViewById(R.id.iv_arrow);
        this.d = (FrameLayout) this.c.findViewById(R.id.fl_content);
        this.d.addView(a());
        initData();
        setContentView(this.c);
        setOnDismissListener(onDismissListener);
    }

    public void showAsDropDown(View view, int i) {
        if (view == null || i < 0) {
            Log.e(a, "非法参数异常");
            return;
        }
        if (this.mIsLive) {
            if (this.mBaseRoomActivity.getResources().getConfiguration().orientation == 2) {
                this.ICON_PADDING_DP = 7.5f;
                this.ITEM_WIDTH_DP = (this.ICON_PADDING_DP * 2.0f) + 35.0f;
            } else {
                this.ICON_PADDING_DP = 5.0f;
                this.ITEM_WIDTH_DP = (this.ICON_PADDING_DP * 2.0f) + 35.0f;
            }
        }
        float f = (((((float) this.POP_WIDTH_DP) + (this.ITEM_WIDTH_DP * ((float) (((i * 2) - (this.b * 2)) + 1)))) / 2.0f) - this.ITEM_MARGINRIGHT_DP) + 10.0f;
        float f2 = (((float) this.POP_WIDTH_DP) - 12.0f) / 2.0f;
        int i2 = -DensityUtil.dip2px(((((float) this.POP_WIDTH_DP) - (this.ICON_PADDING_DP * 2.0f)) - 35.0f) / 2.0f);
        int i3 = -DensityUtil.dip2px(6.0f - this.ICON_PADDING_DP);
        if (f > 0.0f) {
            f2 += f;
            i2 -= DensityUtil.dip2px(f);
        }
        this.iv_arrow.setPadding(DensityUtil.dip2px(f2), 0, 0, 0);
        super.showAsDropDown(view, i2, i3);
    }

    @Deprecated
    public void showAsDropDown(View view) {
        super.showAsDropDown(view);
    }

    @Deprecated
    public void showAsDropDown(View view, int i, int i2) {
        super.showAsDropDown(view, i, i2);
    }

    @SuppressLint({"NewApi"})
    @Deprecated
    public void showAsDropDown(View view, int i, int i2, int i3) {
        super.showAsDropDown(view, i, i2, i3);
    }

    @Deprecated
    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
    }

    protected void initData() {
    }
}
