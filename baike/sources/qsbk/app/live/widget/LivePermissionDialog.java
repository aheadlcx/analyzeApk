package qsbk.app.live.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public class LivePermissionDialog extends BaseDialog {
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private SimpleDraweeView g;

    public LivePermissionDialog(Context context) {
        super(context);
    }

    protected int c() {
        return R.layout.dialog_live_permission;
    }

    protected void d() {
        this.c = (TextView) a(R.id.tv_permission_camera);
        this.d = (TextView) a(R.id.tv_permission_microphone);
        this.e = (TextView) a(R.id.tv_permission_location);
        this.f = (TextView) a(R.id.tv_start_live);
        this.g = (SimpleDraweeView) a(R.id.iv_avatar);
    }

    protected void e() {
        Drawable drawable;
        if (PermissionChecker.checkSelfPermission(getContext(), "android.permission.CAMERA") == 0) {
            drawable = ContextCompat.getDrawable(this.a, R.drawable.live_permission_dialog_affirm);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.c.setBackgroundResource(R.drawable.bg_corner_1bd66c);
            this.c.setTextColor(this.a.getResources().getColor(R.color.white));
            this.c.setCompoundDrawables(drawable, null, null, null);
        } else {
            drawable = ContextCompat.getDrawable(this.a, R.drawable.live_permission_dialog_camera);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.c.setBackgroundResource(R.drawable.bg_corner_1dp_line_41364f);
            this.c.setTextColor(Color.parseColor("#FF41364F"));
            this.c.setCompoundDrawables(drawable, null, null, null);
        }
        if (PermissionChecker.checkSelfPermission(getContext(), "android.permission.RECORD_AUDIO") == 0) {
            drawable = ContextCompat.getDrawable(this.a, R.drawable.live_permission_dialog_affirm);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.d.setBackgroundResource(R.drawable.bg_corner_1bd66c);
            this.d.setTextColor(this.a.getResources().getColor(R.color.white));
            this.d.setCompoundDrawables(drawable, null, null, null);
        } else {
            drawable = ContextCompat.getDrawable(this.a, R.drawable.live_permission_dialog_mic);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.d.setBackgroundResource(R.drawable.bg_corner_1dp_line_41364f);
            this.d.setTextColor(Color.parseColor("#FF41364F"));
            this.d.setCompoundDrawables(drawable, null, null, null);
        }
        if (PermissionChecker.checkSelfPermission(getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0 || PermissionChecker.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            drawable = ContextCompat.getDrawable(this.a, R.drawable.live_permission_dialog_affirm);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.e.setBackgroundResource(R.drawable.bg_corner_1bd66c);
            this.e.setTextColor(this.a.getResources().getColor(R.color.white));
            this.e.setCompoundDrawables(drawable, null, null, null);
        } else {
            drawable = ContextCompat.getDrawable(this.a, R.drawable.live_permission_dialog_location);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.e.setBackgroundResource(R.drawable.bg_corner_1dp_line_41364f);
            this.e.setTextColor(Color.parseColor("#FF41364F"));
            this.e.setCompoundDrawables(drawable, null, null, null);
        }
        if (PermissionChecker.checkSelfPermission(getContext(), "android.permission.CAMERA") == 0 && PermissionChecker.checkSelfPermission(getContext(), "android.permission.RECORD_AUDIO") == 0) {
            this.f.setBackgroundResource(R.drawable.bg_corner_fddb2e);
            this.f.setTextColor(Color.parseColor("#FF793D00"));
        } else {
            this.f.setBackgroundResource(R.drawable.bg_corner_d8d8d8);
            this.f.setTextColor(Color.parseColor("#FFB0ABB7"));
        }
        this.c.setOnClickListener(new hc(this));
        this.d.setOnClickListener(new hd(this));
        this.e.setOnClickListener(new he(this));
        User user = AppUtils.getInstance().getUserInfoProvider().getUser();
        if (user != null) {
            AppUtils.getInstance().getImageProvider().loadAvatar(this.g, user.headurl);
        }
    }

    protected int a() {
        return 17;
    }

    private void j() {
        try {
            getContext().startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", getContext().getPackageName(), null)));
        } catch (Exception e) {
        }
    }
}
