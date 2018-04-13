package qsbk.app.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.model.LiveRoom;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.nearby.ui.Shake2FanSomeone.FanModel;
import qsbk.app.nearby.ui.Shake2FanSomeone.Shake2FanSomeoneListener;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;

public abstract class BaseNearByUserFragment extends BaseNearbyFragment implements Shake2FanSomeoneListener {
    public static final String DIALOG_KEY_CLEAR_POSITION = "clear_positon";
    public static final String DIALOG_KEY_INFOCOMPLETE = "infocomplete";
    public static final String DIALOG_KEY_NEARBYLIST = "nearbylist";
    public static final String DIALOG_KEY_REQ_LOCATION = "location";
    public static final int REQ_INFO_COMPLETE = 3;
    public static final int REQ_LOCATION_SERVICE = 2;
    protected NearbyAdapter a;
    protected PtrLayout b;
    protected ListView c;
    protected View d;
    protected NearbySelectView e;
    protected String f = null;
    protected int g;

    public class NearbyAdapter extends BaseImageAdapter {
        public static final int TYPE_COUNT = 2;
        public static final int TYPE_NEARBY_LIVEROOM = 1;
        public static final int TYPE_NEARBY_USER = 0;
        final /* synthetic */ BaseNearByUserFragment a;

        public NearbyAdapter(BaseNearByUserFragment baseNearByUserFragment, ArrayList<Object> arrayList, Activity activity) {
            this.a = baseNearByUserFragment;
            super(arrayList, activity);
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            if (getItem(i) instanceof NearbyUser) {
                return 0;
            }
            return 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (getItemViewType(i) == 0) {
                b bVar;
                if (view == null) {
                    View view2 = (RelativeLayout) this.n.inflate(R.layout.layout_new_nearby_item, viewGroup, false);
                    b bVar2 = new b();
                    bVar2.a = (RelativeLayout) view2.findViewById(R.id.nearby_backgroud);
                    bVar2.b = (ImageView) view2.findViewById(R.id.avatar);
                    bVar2.c = (RelativeLayout) view2.findViewById(R.id.name_lin);
                    bVar2.d = (TextView) view2.findViewById(R.id.name);
                    bVar2.e = (TextView) view2.findViewById(R.id.login_info);
                    bVar2.f = (LinearLayout) view2.findViewById(R.id.gender_age);
                    bVar2.g = (ImageView) view2.findViewById(R.id.gender);
                    bVar2.h = (TextView) view2.findViewById(R.id.age);
                    bVar2.i = (TextView) view2.findViewById(R.id.astrology);
                    bVar2.j = (TextView) view2.findViewById(R.id.latest_dynamic);
                    bVar2.k = view2.findViewById(R.id.divider);
                    view2.setTag(bVar2);
                    bVar = bVar2;
                    view = view2;
                } else {
                    bVar = (b) view.getTag();
                }
                NearbyUser nearbyUser = (NearbyUser) getItem(i);
                this.a.a(nearbyUser.userId, nearbyUser.userIcon, bVar.b);
                if (UIHelper.isNightTheme()) {
                    bVar.d.setTextColor(this.a.getResources().getColor(R.color.nearby_name_text_color_night));
                    bVar.e.setTextColor(this.a.getResources().getColor(R.color.nearby_login_info_text_color_night));
                    bVar.j.setTextColor(this.a.getResources().getColor(R.color.nearby_latestDynamic_night));
                    bVar.k.setBackgroundColor(this.a.getResources().getColor(R.color.nearby_nearby_line_night));
                    bVar.a.setBackgroundColor(this.a.getResources().getColor(R.color.nearby_nearby_backgroud_night));
                } else {
                    bVar.d.setTextColor(this.a.getResources().getColor(R.color.nearby_name_text_color));
                    bVar.e.setTextColor(this.a.getResources().getColor(R.color.nearby_login_info_text_color));
                    bVar.j.setTextColor(this.a.getResources().getColor(R.color.nearby_latestDynamic));
                    bVar.k.setBackgroundColor(this.a.getResources().getColor(R.color.nearby_nearby_line));
                    bVar.a.setBackgroundColor(this.a.getResources().getColor(R.color.nearby_nearby_backgroud));
                }
                bVar.d.setText(nearbyUser.userName);
                bVar.e.setText(nearbyUser.getDistanceStr() + " | " + nearbyUser.getTimePost());
                bVar.h.setText(nearbyUser.age + "");
                if (TextUtils.isEmpty(nearbyUser.astrology)) {
                    bVar.i.setVisibility(4);
                } else {
                    bVar.i.setText(nearbyUser.astrology.substring(0, 2));
                }
                if (nearbyUser.gender.equals("F")) {
                    bVar.f.setVisibility(0);
                    bVar.i.setVisibility(0);
                    if (UIHelper.isNightTheme()) {
                        bVar.g.setImageResource(R.drawable.pinfo_female_dark);
                        bVar.h.setTextColor(this.k.getResources().getColor(R.color.age_female));
                        bVar.i.setTextColor(this.k.getResources().getColor(R.color.age_female));
                    } else {
                        bVar.f.setBackgroundResource(R.drawable.pinfo_female_bg);
                        bVar.i.setBackgroundResource(R.drawable.near_pinfo_astrology_bg);
                        bVar.g.setImageResource(R.drawable.pinfo_female);
                    }
                } else if (nearbyUser.gender.equals("M")) {
                    bVar.f.setVisibility(0);
                    bVar.i.setVisibility(0);
                    if (UIHelper.isNightTheme()) {
                        bVar.g.setImageResource(R.drawable.pinfo_male_dark);
                        bVar.h.setTextColor(this.k.getResources().getColor(R.color.age_male));
                        bVar.i.setTextColor(this.k.getResources().getColor(R.color.age_male));
                    } else {
                        bVar.f.setBackgroundResource(R.drawable.pinfo_man_bg);
                        bVar.i.setBackgroundResource(R.drawable.near_pinfo_astrology_bg);
                        bVar.g.setImageResource(R.drawable.pinfo_male);
                    }
                } else {
                    bVar.i.setVisibility(4);
                    bVar.f.setVisibility(4);
                }
                if (TextUtils.isEmpty(nearbyUser.recentCircle)) {
                    bVar.j.setVisibility(4);
                } else {
                    bVar.j.setVisibility(0);
                    bVar.j.setText(nearbyUser.recentCircle);
                }
                bVar.a.setOnClickListener(new k(this, nearbyUser));
            } else {
                a aVar;
                if (view == null) {
                    view = this.n.inflate(R.layout.layout_nearby_live_item, viewGroup, false);
                    a aVar2 = new a(view);
                    view.setTag(aVar2);
                    aVar = aVar2;
                } else {
                    aVar = (a) view.getTag();
                }
                LiveRoom liveRoom = (LiveRoom) getItem(i);
                if (!(liveRoom == null || liveRoom.author == null)) {
                    b(aVar.b, liveRoom.author.headurl);
                    aVar.c.setText(liveRoom.author.name);
                    aVar.d.setText(liveRoom.author.intro);
                    aVar.e.setText(liveRoom.distance);
                    LevelHelper.setLevelText(aVar.f, liveRoom.author.level);
                    TextView textView = aVar.g;
                    int i2 = (liveRoom.author == null || TextUtils.isEmpty(liveRoom.author.badge)) ? 8 : 0;
                    textView.setVisibility(i2);
                    aVar.g.setText(liveRoom.author.badge);
                }
                aVar.a.setOnClickListener(new l(this, liveRoom));
            }
            return view;
        }

        public boolean contains(NearbyUser nearbyUser) {
            return this.m.contains(nearbyUser);
        }

        public boolean add(NearbyUser nearbyUser) {
            boolean add = this.m.add(nearbyUser);
            if (add) {
                notifyDataSetChanged();
            }
            return add;
        }

        public boolean addAll(List<NearbyUser> list) {
            boolean addAll = this.m.addAll(list);
            if (addAll) {
                notifyDataSetChanged();
            }
            return addAll;
        }

        public NearbyUser getBindedUser(int i) {
            if (i < 0 || i >= this.m.size()) {
                return null;
            }
            return (NearbyUser) this.m.get(i);
        }

        public NearbyUser remove(int i) {
            NearbyUser nearbyUser = (NearbyUser) this.m.remove(i);
            if (nearbyUser != null) {
                notifyDataSetChanged();
            }
            return nearbyUser;
        }

        public boolean remove(NearbyUser nearbyUser) {
            boolean remove = this.m.remove(nearbyUser);
            if (remove) {
                notifyDataSetChanged();
            }
            return remove;
        }

        public void clear() {
            this.m.clear();
            notifyDataSetChanged();
        }
    }

    private static class a {
        RelativeLayout a;
        ImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;

        public a(View view) {
            this.a = (RelativeLayout) view.findViewById(R.id.nearby_backgroud);
            this.b = (ImageView) view.findViewById(R.id.avatar);
            this.c = (TextView) view.findViewById(R.id.name);
            this.d = (TextView) view.findViewById(R.id.latest_dynamic);
            this.e = (TextView) view.findViewById(R.id.location);
            this.f = (TextView) view.findViewById(R.id.level);
            this.g = (TextView) view.findViewById(R.id.family);
        }
    }

    private static class b {
        RelativeLayout a;
        ImageView b;
        RelativeLayout c;
        TextView d;
        TextView e;
        LinearLayout f;
        ImageView g;
        TextView h;
        TextView i;
        TextView j;
        View k;

        private b() {
        }
    }

    protected static List<NearbyUser> a(JSONArray jSONArray) {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            NearbyUser nearbyUser = new NearbyUser();
            nearbyUser.parseJson(optJSONObject);
            arrayList.add(nearbyUser);
        }
        return arrayList;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onServerSuccess(FanModel fanModel) {
        if (!this.m) {
        }
    }

    public void onServerFailed(FanModel fanModel, int i, String str) {
        if (!this.m) {
        }
    }

    public void onLocalSuccess(FanModel fanModel) {
    }

    public void showUserTypeSelectDialog() {
        Builder builder = new Builder(getActivity());
        builder.setPositiveButton(R.string.dialog_btn_confirm, new j(this)).setNegativeButton(R.string.dialog_btn_cancel, new i(this));
        AlertDialog create = builder.create();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_nearby_user_select, null);
        create.setView(inflate);
        this.e = new NearbySelectView(inflate);
        LogUtil.d("getLocalFilterSex:" + NearbyEngine.instance().getLocalFilterSex());
        LogUtil.d("getLocalFilterTime:" + NearbyEngine.instance().getLocalFilterTime());
        this.e.setGender(NearbyEngine.instance().getLocalFilterSex());
        this.e.setTimeInMinute(NearbyEngine.instance().getLocalFilterTime());
        create.show();
        create.setCanceledOnTouchOutside(true);
    }

    private void a(String str, int i) {
        if (!str.equals(this.f) || this.g != i) {
            this.f = str;
            this.g = i;
            NearbyEngine.instance().setLocalFilterSex(this.f);
            NearbyEngine.instance().setLocalFilterLastLogin(this.g);
            init();
        }
    }

    private void a(String str, String str2, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }
}
