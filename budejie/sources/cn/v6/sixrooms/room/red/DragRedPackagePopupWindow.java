package cn.v6.sixrooms.room.red;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.animation.RotationYAnimation;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.widgets.phone.DragPopupWindow;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

public class DragRedPackagePopupWindow extends DragPopupWindow {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private TextView d;
    private SimpleDraweeView[] e = null;
    private int[] f = null;
    private int[] g = null;
    private int[] h = null;
    private String[] i = new String[]{"asset:///gif/get_red_package.gif", "asset:///gif/win_package50.gif", "asset:///gif/win_package100.gif"};
    private RotationYAnimation j = null;
    private int k = 400;
    private int l = 0;
    private int m = ErrorCode.SERVER_OPERATIONTYPEMISSED;
    private String n = null;
    private int o = 0;
    private boolean p = false;
    private IRedPackageItem q = null;
    private int r = 0;
    private int s = 30;
    private final int t = 1;
    private final int u = 2;
    private final int v = 3;
    private int w = 5000;
    private Context x;
    private int y = -1;
    private Handler z = new b(this);

    public interface IRedPackageItem {
        void clickPackage(String str);

        void onClickEnd(String str);
    }

    public void setScreentStyle(int i) {
        if (this.y != i) {
            this.y = i;
            switch (i) {
                case 2:
                case 3:
                case 4:
                    this.g = getYmoveArray();
                    this.f = getXmoveArray();
                    break;
                default:
                    this.g = new int[]{DisPlayUtil.getPlayerHeight(this.x) + DisPlayUtil.getStatusHeight(this.x), this.x.getResources().getDisplayMetrics().heightPixels};
                    this.f = new int[]{0, this.x.getResources().getDisplayMetrics().widthPixels};
                    break;
            }
            a(i);
        }
    }

    private void a() {
        this.z.removeMessages(3);
        this.z.sendEmptyMessageDelayed(3, (long) this.w);
    }

    protected static DragRedPackagePopupWindow getInstance(Context context, IRedPackageItem iRedPackageItem, int i, int i2, boolean z) {
        DragRedPackagePopupWindow dragRedPackagePopupWindow = new DragRedPackagePopupWindow(LayoutInflater.from(context).inflate(R.layout.red_package_info, null), -2, -2, i2, z);
        dragRedPackagePopupWindow.o = i;
        if (i == 1) {
            ((GenericDraweeHierarchy) dragRedPackagePopupWindow.e[0].getHierarchy()).setPlaceholderImage(R.drawable.pic_super_package);
        } else {
            ((GenericDraweeHierarchy) dragRedPackagePopupWindow.e[0].getHierarchy()).setPlaceholderImage(R.drawable.red_package_disable);
            dragRedPackagePopupWindow.r = dragRedPackagePopupWindow.s;
        }
        dragRedPackagePopupWindow.q = iRedPackageItem;
        return dragRedPackagePopupWindow;
    }

    public void setPackageID(String str) {
        this.n = str;
    }

    public void openLottery(String str) {
        this.n = str;
        this.r = this.s;
        this.z.removeMessages(3);
        showLottery();
    }

    public void showLottery() {
        this.b = true;
        this.o = 2;
        this.e[1].setAlpha(1.0f);
        b(this.e[1]);
        this.z.removeMessages(1);
        this.z.sendEmptyMessageDelayed(1, 1000);
    }

    public void updateTime(int i) {
        this.z.removeMessages(1);
        this.z.sendEmptyMessageDelayed(1, 1000);
        this.r = i;
        this.d.setText(i + "秒");
        if (this.r == 0) {
            a();
        }
    }

    public void setPackageEnd() {
        this.p = true;
    }

    public void setResult(int i) {
        switch (i) {
            case 1:
                this.e[2].setAlpha(1.0f);
                b(this.e[2]);
                b();
                return;
            case 2:
                this.e[3].setAlpha(1.0f);
                b(this.e[3]);
                b();
                return;
            case 3:
                this.p = true;
                d();
                return;
            default:
                return;
        }
    }

    public DragRedPackagePopupWindow(View view, int i, int i2, int i3, boolean z) {
        int i4;
        int i5;
        super(view, i, i2);
        this.x = view.getContext();
        a(i3);
        setScreentStyle(i3);
        View contentView = getContentView();
        this.e = new SimpleDraweeView[4];
        this.d = (TextView) contentView.findViewById(R.id.tv_info);
        this.e[0] = (SimpleDraweeView) contentView.findViewById(R.id.img_1);
        this.e[1] = (SimpleDraweeView) contentView.findViewById(R.id.img_2);
        this.e[2] = (SimpleDraweeView) contentView.findViewById(R.id.img_3);
        this.e[3] = (SimpleDraweeView) contentView.findViewById(R.id.img_4);
        this.e[1].setImageURI(Uri.parse(this.i[0]));
        this.e[2].setImageURI(Uri.parse(this.i[1]));
        this.e[3].setImageURI(Uri.parse(this.i[2]));
        for (i4 = 1; i4 < this.e.length; i4++) {
            this.e[i4].setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(this.i[i4 - 1])).setAutoPlayAnimations(true)).build());
        }
        this.j = new RotationYAnimation();
        this.j.setDuration((long) this.k);
        this.j.setAnimationListener(new c(this));
        int dimensionPixelSize = this.x.getResources().getDimensionPixelSize(R.dimen.package_width);
        int dimensionPixelSize2 = this.x.getResources().getDimensionPixelSize(R.dimen.package_height);
        if (z) {
            i5 = this.g[0] + (((this.g[1] - this.g[0]) - dimensionPixelSize2) / 2);
            i4 = this.f[0] + (((this.f[1] - this.f[0]) - dimensionPixelSize) / 2);
        } else {
            i5 = (int) (((double) this.g[0]) + (Math.random() * ((double) ((this.g[1] - this.g[0]) - dimensionPixelSize2))));
            i4 = (int) (((double) this.f[0]) + (Math.random() * ((double) ((this.f[1] - this.f[0]) - dimensionPixelSize))));
        }
        setPosition(new int[]{i4, i5, dimensionPixelSize, dimensionPixelSize2});
        i5 = (int) (0.5f * ((float) dimensionPixelSize));
        i4 = (int) (0.27d * ((double) dimensionPixelSize2));
        this.h = new int[]{(dimensionPixelSize - i5) / 2, i4, i5, i5};
    }

    private void a(int i) {
        int[] iArr;
        int[] iArr2;
        switch (i) {
            case 2:
            case 3:
                iArr = new int[]{0, this.x.getResources().getDisplayMetrics().heightPixels};
                iArr2 = new int[]{0, this.x.getResources().getDisplayMetrics().widthPixels};
                break;
            default:
                iArr = new int[]{DisPlayUtil.getStatusHeight(this.x), this.x.getResources().getDisplayMetrics().heightPixels};
                iArr2 = new int[]{0, this.x.getResources().getDisplayMetrics().widthPixels};
                break;
        }
        setYMoveArray(iArr);
        setXMoveArray(iArr2);
    }

    private void b() {
        if (this.a) {
            this.a = true;
            this.j.cancel();
        }
        this.l = 0;
        getContentView().startAnimation(this.j);
    }

    private static void b(SimpleDraweeView simpleDraweeView) {
        Animatable animatable = simpleDraweeView.getController().getAnimatable();
        if (animatable != null) {
            animatable.stop();
            animatable.start();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        if (this.c) {
            c();
            return true;
        }
        boolean onTouch = super.onTouch(view, motionEvent);
        if (!isMoveEvent() && (motionEvent.getAction() == 3 || motionEvent.getAction() == 1)) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (this.o) {
                case 2:
                    if (x < ((float) this.h[0]) || x > ((float) (this.h[0] + this.h[2])) || y < ((float) this.h[1]) || y > ((float) (this.h[1] + this.h[3]))) {
                        z = false;
                    }
                    if (z) {
                        d();
                        break;
                    }
                    break;
            }
        }
        return onTouch;
    }

    private void c() {
        getContentView().setVisibility(8);
        this.q.onClickEnd(this.n);
        dismiss();
    }

    private void d() {
        if (this.p) {
            this.c = true;
            this.z.removeCallbacks(null);
            this.z.removeMessages(1);
            this.z.removeMessages(2);
            this.z.removeMessages(3);
            this.e[0].setVisibility(0);
            ((GenericDraweeHierarchy) this.e[0].getHierarchy()).setPlaceholderImage(R.drawable.lose_package);
            this.d.setVisibility(8);
            this.z.removeCallbacks(null);
            for (int i = 1; i < this.e.length; i++) {
                this.e[i].setVisibility(8);
            }
            b();
        } else if (this.o == 2 && !TextUtils.isEmpty(this.n)) {
            this.e[1].setAlpha(0.0f);
            ((GenericDraweeHierarchy) this.e[0].getHierarchy()).setPlaceholderImage(R.drawable.red_package_disable);
            this.o = 3;
            this.q.clickPackage(this.n);
            this.z.sendEmptyMessageDelayed(2, (long) this.m);
        }
    }
}
