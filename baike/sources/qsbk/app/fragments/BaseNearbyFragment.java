package qsbk.app.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity;
import qsbk.app.activity.base.BaseQiuyouquanFragment;
import qsbk.app.nearby.api.LocationCache;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.nearby.api.LocationHelper.LocationWarnUIProvider;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

public abstract class BaseNearbyFragment extends BaseQiuyouquanFragment implements LocationCallBack, LocationWarnUIProvider {
    private ViewGroup a;
    private String b = null;
    private View c = null;
    private boolean d = true;
    protected View h;
    protected LinearLayout i;
    protected LinearLayout j;
    protected TextView k;
    protected LocationHelper l = null;
    protected boolean m = false;
    protected boolean n = false;

    private final class a implements OnClickListener {
        final /* synthetic */ BaseNearbyFragment a;

        private a(BaseNearbyFragment baseNearbyFragment) {
            this.a = baseNearbyFragment;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.open_nearby:
                    this.a.init();
                    return;
                case R.id.id_btn_open_service:
                    this.a.l.openServiceSetting();
                    return;
                default:
                    return;
            }
        }
    }

    public abstract void onFillContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public abstract void onLocateDone();

    public void onResume() {
        super.onResume();
        this.m = true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.m = false;
        if (this.l != null) {
            this.l.distory();
        }
        this.l = null;
        this.i = null;
        this.j = null;
        this.c = null;
    }

    protected String a() {
        return getString(R.string.view_nearby_qiu_friends);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_nearby, viewGroup, false);
        OnClickListener aVar = new a();
        this.a = (ViewGroup) inflate.findViewById(R.id.main_content);
        onFillContentView(layoutInflater, this.a, bundle);
        this.i = (LinearLayout) inflate.findViewById(R.id.clear_layout);
        this.k = (TextView) inflate.findViewById(R.id.post_clear_msg);
        TextView textView = (TextView) this.i.findViewById(R.id.open_nearby);
        textView.setText(a());
        textView.setOnClickListener(aVar);
        this.j = (LinearLayout) inflate.findViewById(R.id.open_location_services);
        this.j.findViewById(R.id.id_btn_open_service).setOnClickListener(aVar);
        this.h = inflate.findViewById(R.id.id_loading_div);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.e("base near by fragment====");
        super.onViewCreated(view, bundle);
        init();
    }

    protected void b() {
        if (getActivity() != null) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, getResources().getString(R.string.msg_is_cleared), Integer.valueOf(0)).show();
        }
    }

    public void startLocate() {
        if (this.l == null) {
            this.l = new LocationHelper((Fragment) this);
        }
        LocationHelper locationHelper = this.l;
        if (LocationHelper.loadCache()) {
            onLocateDone();
            this.l.startLocate(null);
            return;
        }
        a(true);
        this.l.startLocate(this, this);
    }

    public void clearLocation() {
        new Builder(getActivity()).setCancelable(true).setTitle("清除地理位置").setItems(new String[]{"清除地理位置", "再逛逛"}, new m(this)).create().show();
    }

    private void a(int i) {
        View[] viewArr = new View[]{this.a, this.i, this.j};
        for (int i2 = 0; i2 < viewArr.length; i2++) {
            if (viewArr[i2] != null) {
                LogUtil.d("show ui inner:" + i2);
                if (i2 == i) {
                    viewArr[i2].setVisibility(0);
                } else {
                    viewArr[i2].setVisibility(8);
                }
            }
        }
    }

    protected void a(boolean z) {
        this.n = true;
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof BaseTabActivity) {
                ((BaseTabActivity) activity).showLoadingIfFocus(this);
            } else {
                activity.setProgressBarIndeterminateVisibility(true);
            }
        }
        if (z && this.h != null) {
            this.h.setVisibility(0);
        }
    }

    protected void c() {
        this.n = false;
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof BaseTabActivity) {
                ((BaseTabActivity) activity).hideLoadingIfFocus(this);
            } else {
                activity.setProgressBarIndeterminateVisibility(false);
            }
        }
        if (this.h != null) {
            this.h.setVisibility(8);
        }
    }

    public void show_restart() {
        show_restart_with_msg(null);
    }

    protected boolean d() {
        return true;
    }

    public void show_restart_with_msg(String str) {
        if (this.k != null) {
            if (TextUtils.isEmpty(str)) {
                this.k.setVisibility(8);
            } else {
                this.k.setText(str);
                this.k.setVisibility(0);
            }
            if (d()) {
                a(1);
            }
        }
    }

    public void init() {
        a(0);
        if (!this.d) {
            startLocate();
        } else if (NearbyEngine.instance().isNearbyNoMoreWarn()) {
            this.d = false;
            startLocate();
        } else {
            e();
        }
    }

    private void a(String str) {
        StatService.onEvent(getActivity(), "location", str);
    }

    private void e() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(R.string.nearby_pop_title).setPositiveButton(R.string.nearby_pop_btn_ok, new p(this)).setNegativeButton(R.string.nearby_pop_btn_deny, new o(this));
        AlertDialog create = builder.create();
        this.c = getActivity().getLayoutInflater().inflate(R.layout.layout_nearby_info_notify, null);
        create.setView(this.c);
        create.setOnCancelListener(new q(this));
        create.show();
        create.setCanceledOnTouchOutside(true);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.l != null) {
            this.l.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onShowServiceDisableWarn() {
        a(2);
        c();
    }

    public void onHideServiceDisableWarn() {
        a(0);
        a(true);
    }

    public void onLocateFail(int i) {
        c();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getResources().getString(R.string.get_location_error), Integer.valueOf(0)).show();
        show_restart();
    }

    public void onLocateDone(double d, double d2, String str, String str2) {
        LocationCache.getInstance().setLocation(new Location(0, d, d2, str, str2));
        c();
        onLocateDone();
    }

    public boolean isLoading() {
        return this.n;
    }
}
