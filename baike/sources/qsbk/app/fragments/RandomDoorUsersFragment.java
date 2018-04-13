package qsbk.app.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ListView;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.RandomDoorActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.BaseNearByUserFragment.NearbyAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.RssArticle.Type;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.nearby.ui.Shake2FanSomeone.Shake2FanSomeoneListener;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class RandomDoorUsersFragment extends BaseNearByUserFragment implements Shake2FanSomeoneListener, PtrListener {
    public static final int EXHAUNT = -911;
    private static final String p = RandomDoorUsersFragment.class.getSimpleName();
    private static double q = 0.0d;
    private static double r = 0.0d;
    public boolean isLoading = false;
    protected TextView o;
    private int s = 1;
    private View t = null;
    private int u = 0;

    public static RandomDoorUsersFragment newInstance(double d, double d2) {
        RandomDoorUsersFragment randomDoorUsersFragment = new RandomDoorUsersFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("key_latitude", d2);
        bundle.putDouble("key_longitude", d);
        randomDoorUsersFragment.setArguments(bundle);
        return randomDoorUsersFragment;
    }

    public static RandomDoorUsersFragment newInstance() {
        return new RandomDoorUsersFragment();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.t = null;
    }

    public void onFillContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, viewGroup, false);
        this.b = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.c = (ListView) inflate.findViewById(R.id.listview);
        viewGroup.addView(inflate);
        this.d = viewGroup.findViewById(R.id.main_content);
        this.b.setLoadMoreEnable(false);
        this.b.setPtrListener(this);
        this.a = new NearbyAdapter(this, new ArrayList(), getActivity());
        this.c.setAdapter(this.a);
        if (NearbyEngine.instance().isNearbyNoMoreWarnInfoComplete()) {
            this.u = 1;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            r = arguments.getDouble("key_longitude", 0.0d);
            q = arguments.getDouble("key_latitude", 0.0d);
        }
    }

    public void onLocateDone() {
        this.s = 1;
        b(false);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.nearby, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_clear_location).setIcon(UIHelper.isNightTheme() ? R.drawable.random_door_refresh_dark : R.drawable.random_door_refresh);
        menu.findItem(R.id.action_select).setIcon(UIHelper.isNightTheme() ? R.drawable.bottombar_shaixuan_night : R.drawable.bottombar_shaixuan);
        menu.findItem(R.id.action_randomdoor).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_select:
                showUserTypeSelectDialog();
                return true;
            case R.id.action_clear_location:
                ((RandomDoorActivity) getActivity()).openStart(true);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void a(JSONObject jSONObject, boolean z, String str) {
        if (NearbyEngine.instance().isNearbyNoMoreWarnInfoComplete()) {
            this.u = 1;
            b(z);
            return;
        }
        Builder negativeButton = new Builder(getActivity()).setTitle(R.string.nearby_pop_title).setPositiveButton(R.string.nearby_infocomplete_pop_btn_ok, new kt(this)).setNegativeButton(R.string.nearby_infocomplete_pop_btn_deny, new ks(this));
        this.t = getActivity().getLayoutInflater().inflate(R.layout.layout_nearby_info_notify, null);
        ((TextView) this.t.findViewById(R.id.textView)).setText(str);
        AlertDialog create = negativeButton.create();
        create.setView(this.t);
        create.show();
    }

    private void f() {
        Intent intent = new Intent(getActivity(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        startActivityForResult(intent, 3);
    }

    public void show_restart() {
        show_restart_with_msg(null);
    }

    public void show_restart_with_msg(String str) {
        if (this.o != null) {
            if (TextUtils.isEmpty(str)) {
                this.o.setVisibility(8);
            } else {
                this.o.setText(str);
                this.o.setVisibility(0);
            }
            View findViewById = this.i.findViewById(R.id.open_nearby);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            a(1);
        }
    }

    private void a(int i) {
        View[] viewArr = new View[]{this.d, this.i};
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

    private void a(JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Type.NEARBY);
            if (this.s == 1) {
                this.a.clear();
                this.b.refreshDone();
            } else {
                this.b.loadMoreDone(true);
            }
            b(optJSONArray);
            if (jSONObject.optInt("has_more") == 1) {
                this.b.setLoadMoreEnable(true);
            } else {
                this.b.setLoadMoreEnable(false);
            }
            this.s++;
            if (optJSONArray != null && optJSONArray.length() != 0) {
                return;
            }
            if (NearbySelectView.GENDER_ALL.equals(NearbyEngine.instance().getLocalFilterSex()) && NearbyEngine.instance().getLocalFilterTime() == NearbySelectView.TIME_3DAY) {
                show_restart_with_msg("漫游地找不到符合筛选条件的用户，请点击右上角重新漫游！");
                return;
            } else {
                show_restart_with_msg("漫游地找不到符合筛选条件的用户，可以尝试修改筛选条件或者重新漫游！");
                return;
            }
        }
        c();
        show_restart_with_msg("漫游地找不到符合筛选条件的用户，请点击右上角重新漫游！");
    }

    private void b(boolean z) {
        e();
        this.isLoading = true;
        if (HttpUtils.netIsAvailable()) {
            if (this.f == null) {
                this.f = NearbyEngine.instance().getLocalFilterSex();
                this.g = NearbyEngine.instance().getLocalFilterTime();
            }
            Map hashMap = new HashMap();
            String str = ParamKey.LONGITUDE;
            LocationHelper locationHelper = this.l;
            hashMap.put(str, String.valueOf(LocationHelper.getLongitude()));
            str = ParamKey.LATITUDE;
            locationHelper = this.l;
            hashMap.put(str, String.valueOf(LocationHelper.getLatitude()));
            hashMap.put("gender", this.f);
            hashMap.put("time", Integer.valueOf(this.g));
            hashMap.put("wlng", Double.valueOf(r));
            hashMap.put("wlat", Double.valueOf(q));
            LocationHelper locationHelper2 = this.l;
            if (!TextUtils.isEmpty(LocationHelper.getDistrict())) {
                locationHelper2 = this.l;
                if (!TextUtils.isEmpty(LocationHelper.getCity())) {
                    str = InfoCompleteActivity.DOT_FORMAT;
                    r2 = new Object[2];
                    LocationHelper locationHelper3 = this.l;
                    r2[0] = LocationHelper.getCity();
                    locationHelper3 = this.l;
                    r2[1] = LocationHelper.getDistrict();
                    str = String.format(str, r2);
                    QsbkApp.currentUser.haunt = str;
                    hashMap.put("haunt", str);
                }
            }
            hashMap.put("stroll", Integer.valueOf(this.u));
            DeviceUtils.addDeviceInfoToParam(hashMap);
            hashMap.put(ParamKey.PAGE, Integer.valueOf(this.s));
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.URL_FETCH, new ku(this, z));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        this.isLoading = false;
    }

    private void a(String str) {
        Context activity = getActivity();
        ((RandomDoorActivity) activity).openStart(false);
        if (activity != null) {
            new Builder(activity).setMessage(str).setPositiveButton("不了", new kw(this)).setNegativeButton("好的", new kv(this, activity)).show();
        }
    }

    public void init() {
        super.init();
    }

    private void b(JSONArray jSONArray) {
        List a;
        if (jSONArray != null) {
            a = BaseNearByUserFragment.a(jSONArray);
        } else {
            a = new ArrayList();
        }
        List arrayList = new ArrayList(r0.size());
        for (NearbyUser nearbyUser : r0) {
            if (!this.a.contains(nearbyUser)) {
                arrayList.add(nearbyUser);
            }
        }
        this.a.addAll(arrayList);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 2) {
            if (!NearbyEngine.instance().isLocationServiceEnabled(getActivity())) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请打开定位服务来使用附近的人的功能", Integer.valueOf(0)).show();
            }
        } else if (i == 3) {
            if (i2 == -1) {
                this.u = 0;
                init();
            } else {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您必须先完善您的个人信息才能使用加粉功能", Integer.valueOf(0)).show();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return super.onCreateAnimation(i, z, i2);
    }

    public void onLoadMore() {
        b(true);
    }

    public void onRefresh() {
        this.s = 1;
        b(true);
    }

    protected void e() {
        this.n = true;
        if ((this.a == null || this.a.getCount() == 0) && this.h != null) {
            this.h.setVisibility(0);
        }
    }
}
