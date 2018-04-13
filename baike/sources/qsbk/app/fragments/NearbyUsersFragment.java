package qsbk.app.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.NearByActivity.OnOptionMenuSelectedCallBack;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.fragments.BaseNearByUserFragment.NearbyAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.LiveRoom;
import qsbk.app.model.RssArticle.Type;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class NearbyUsersFragment extends BaseNearByUserFragment implements OnOptionMenuSelectedCallBack, LocationCallBack, PtrListener {
    private static final String q = NearbyUsersFragment.class.getSimpleName();
    List<LiveRoom> o = new ArrayList();
    ArrayList<Object> p = new ArrayList();
    private int r = -1;
    private int s = -1;
    private int t = 1;
    private int u = 1;
    private View v = null;
    private int w = 0;
    private int x;

    public static NearbyUsersFragment newInstance() {
        return new NearbyUsersFragment();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.v = null;
    }

    public void onFillContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, viewGroup, false);
        this.b = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.c = (ListView) inflate.findViewById(R.id.listview);
        viewGroup.addView(inflate);
        this.d = viewGroup.findViewById(R.id.main_content);
        this.b.setLoadMoreEnable(false);
        this.b.setPtrListener(this);
        this.p = new ArrayList();
        this.a = new NearbyAdapter(this, this.p, getActivity());
        this.c.setAdapter(this.a);
        this.c.setOnItemClickListener(new hi(this));
        if (NearbyEngine.instance().isNearbyNoMoreWarnInfoComplete()) {
            this.w = 1;
        }
    }

    protected void b() {
        super.b();
    }

    private void a(boolean z, String str) {
        if (NearbyEngine.instance().isNearbyNoMoreWarnInfoComplete()) {
            this.w = 1;
            b(z);
            return;
        }
        Builder negativeButton = new Builder(getActivity()).setTitle(R.string.nearby_pop_title).setPositiveButton(R.string.nearby_infocomplete_pop_btn_ok, new hk(this)).setNegativeButton(R.string.nearby_infocomplete_pop_btn_deny, new hj(this));
        this.v = getActivity().getLayoutInflater().inflate(R.layout.layout_nearby_info_notify, null);
        ((TextView) this.v.findViewById(R.id.textView)).setText(str);
        AlertDialog create = negativeButton.create();
        create.setView(this.v);
        create.show();
    }

    private void f() {
        Intent intent = new Intent(getActivity(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        startActivityForResult(intent, 3);
    }

    public void reportLocationEvent(String str) {
        StatService.onEvent(getActivity(), "location", str);
    }

    private void a(JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            c();
            if (this.t == 1) {
                this.a.clear();
                this.b.refreshDone();
            } else {
                this.b.loadMoreDone(true);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray(Type.NEARBY);
            b(optJSONArray);
            if (jSONObject.optInt("has_more") == 1) {
                this.b.setLoadMoreEnable(true);
            } else {
                this.b.setLoadMoreEnable(false);
            }
            if (optJSONArray == null || optJSONArray.length() == 0) {
                if (NearbySelectView.GENDER_ALL.equals(NearbyEngine.instance().getLocalFilterSex()) && NearbyEngine.instance().getLocalFilterTime() == NearbySelectView.TIME_3DAY) {
                    show_restart_with_msg("找不到符合筛选条件的用户，去试试任意门吧！");
                } else if (this.t == 1) {
                    show_restart_with_msg("找不到符合筛选条件的用户，请修改筛选条件之后再试！");
                } else {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "没有更多附近的人啦，到别处玩玩吧！", Integer.valueOf(0)).show();
                }
            }
            this.t++;
            return;
        }
        c();
        if (this.t == 1) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "找不到附近的人，请重新点击刷新", Integer.valueOf(0)).show();
            show_restart();
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, QsbkApp.mContext.getString(R.string.network_error_retry), Integer.valueOf(0)).show();
    }

    private void b(boolean z) {
        e();
        if (HttpUtils.netIsAvailable()) {
            if (this.f == null) {
                this.f = NearbyEngine.instance().getLocalFilterSex();
                this.g = NearbyEngine.instance().getLocalFilterTime();
                LogUtil.e("mFilterSex ===" + this.f + "mFilterTime ====" + this.g);
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
            LocationHelper locationHelper2 = this.l;
            if (!TextUtils.isEmpty(LocationHelper.getDistrict())) {
                locationHelper2 = this.l;
                if (!TextUtils.isEmpty(LocationHelper.getCity())) {
                    str = InfoCompleteActivity.DOT_FORMAT;
                    r2 = new Object[2];
                    LocationHelper locationHelper3 = this.l;
                    r2[0] = LocationHelper.getCity();
                    LocationHelper locationHelper4 = this.l;
                    r2[1] = LocationHelper.getDistrict();
                    str = String.format(str, r2);
                    QsbkApp.currentUser.haunt = str;
                    hashMap.put("haunt", str);
                }
            }
            hashMap.put("stroll", Integer.valueOf(this.w));
            DeviceUtils.addDeviceInfoToParam(hashMap);
            hashMap.put(ParamKey.PAGE, Integer.valueOf(this.t));
            SimpleHttpTask hmVar = new hm(this, Constants.URL_FETCH, new hl(this, z));
            hmVar.setMapParams(hashMap);
            hmVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
    }

    private void g() {
        this.x = -1;
        this.o.clear();
        try {
            HttpClient intentce = HttpClient.getIntentce();
            String str = Constants.LIVE_NEARBY;
            r3 = new Object[2];
            LocationHelper locationHelper = this.l;
            r3[0] = String.valueOf(LocationHelper.getLatitude());
            locationHelper = this.l;
            r3[1] = String.valueOf(LocationHelper.getLongitude());
            JSONObject jSONObject = new JSONObject(intentce.get(String.format(str, r3)));
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("lives");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        LiveRoom parse = LiveRoom.parse(optJSONArray.getJSONObject(i));
                        if (parse != null) {
                            this.o.add(parse);
                        }
                    }
                }
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
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
        h();
    }

    private void h() {
        if (this.o.size() > 0) {
            int i = i();
            while (i < this.p.size() && this.o.size() > 0) {
                a(i);
                i = i();
            }
        }
    }

    private int i() {
        if (this.x < 3) {
            return 3;
        }
        return this.x + 9;
    }

    private void a(int i) {
        if (this.p.size() >= i) {
            this.p.add(i, (LiveRoom) this.o.remove(0));
            this.x = i;
        }
        this.a.notifyDataSetChanged();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 3) {
            if (i2 == -1) {
                this.w = 1;
                init();
            } else {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您必须先完善您的个人信息才能使用加粉功能。", Integer.valueOf(0)).show();
                show_restart();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onLocateDone() {
        this.t = 1;
        b(false);
    }

    public void onSelectUser() {
        showUserTypeSelectDialog();
    }

    public void onClearLocation() {
        clearLocation();
    }

    public void onLoadMore() {
        b(true);
    }

    public void onRefresh() {
        this.t = 1;
        b(false);
    }

    protected void e() {
        this.n = true;
        if ((this.a == null || this.a.getCount() == 0) && this.h != null) {
            this.h.setVisibility(0);
        }
    }
}
