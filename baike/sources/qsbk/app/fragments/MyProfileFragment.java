package qsbk.app.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.ActionBarUserSettingNavi;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.ManageQiuShiAndDynamicActivity;
import qsbk.app.activity.MedalListActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.NearByActivity;
import qsbk.app.activity.NearByGroupActivity;
import qsbk.app.activity.QiuYouActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.GameWebViewActivity;
import qsbk.app.im.IMTimer;
import qsbk.app.im.MessageCountManager;
import qsbk.app.im.OfficialMsgDetailActivity;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.EventWindow;
import qsbk.app.model.FoundFragementItem.Duobao;
import qsbk.app.model.FoundFragementItem.FoundChicken;
import qsbk.app.model.FoundFragementItem.FoundGame;
import qsbk.app.model.FoundFragementItem.Game;
import qsbk.app.model.PunchInfo;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.TipsManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.GameItemView;
import qsbk.app.widget.PunchCardCell;

public class MyProfileFragment extends StatisticFragment {
    public static final String DUOBAO_HAS_TIPS = "found_duobao_has_tips";
    public static Duobao duobao;
    public static boolean duobaoHasTips = false;
    public static int newFans = SharePreferenceUtils.getSharePreferencesIntValue(MessageCountManager.NEWFANS_COUNT);
    public static int newVis = 0;
    public static int todayVis = 0;
    public static int totalVis = 0;
    private c A;
    private c B;
    private c C;
    private final BroadcastReceiver a = new gf(this);
    private Toolbar b;
    private ListView c;
    private g d;
    private List<c> e = new ArrayList();
    private e f;
    private f g;
    private LocalBroadcastManager h;
    private boolean i = true;
    private final BroadcastReceiver j = new gj(this);
    private final BroadcastReceiver k = new gk(this);
    private boolean l = false;
    private ImageView m;
    private boolean n = false;
    private String o = null;
    private String p = null;
    private int q;
    private boolean r = false;
    private ImageView s;
    private SplashAdItem t;
    private DataSource<CloseableReference<CloseableImage>> u;
    private d v;
    private c w;
    private c x;
    private c y;
    private c z;

    public interface IGetView {
        View getView(c cVar, View view, ViewGroup viewGroup);
    }

    private static class a {
        LinearLayout a;
        RelativeLayout b;
        TextView c;
        ImageView d;
        TextView e;
        View f;
        View g;

        public a(View view) {
            this.a = (LinearLayout) view.findViewById(R.id.found_game_lin);
            this.b = (RelativeLayout) view.findViewById(R.id.found_game_first_rel);
            this.c = (TextView) view.findViewById(R.id.found_game_description);
            this.d = (ImageView) view.findViewById(R.id.found_game_image);
            this.e = (TextView) view.findViewById(R.id.found_game_name);
            this.f = view.findViewById(R.id.found_game_view);
            this.g = view.findViewById(R.id.tips);
        }
    }

    private static class b {
        LinearLayout a;
        ImageView b;
        ImageView c;
        ImageView d;
        ImageView e;
        TextView f;
        TextView g;
        TextView h;
        TextView i;
        ImageView j;
        ImageView k;
        RelativeLayout l;
        RelativeLayout m;

        public b(View view) {
            this.a = (LinearLayout) view.findViewById(R.id.found_head_lin);
            this.b = (ImageView) view.findViewById(R.id.found_ic_nearby);
            this.c = (ImageView) view.findViewById(R.id.found_ic_group);
            this.d = (ImageView) view.findViewById(R.id.found_ic_store);
            this.e = (ImageView) view.findViewById(R.id.found_ic_chicken);
            this.f = (TextView) view.findViewById(R.id.found_head_nearby);
            this.g = (TextView) view.findViewById(R.id.found_head_group);
            this.h = (TextView) view.findViewById(R.id.found_head_store);
            this.i = (TextView) view.findViewById(R.id.found_head_cross);
            this.j = (ImageView) view.findViewById(R.id.chicken_new);
            this.k = (ImageView) view.findViewById(R.id.duobao_new);
            this.l = (RelativeLayout) view.findViewById(R.id.found_ic_chicken_rel);
            this.m = (RelativeLayout) view.findViewById(R.id.found_ic_duobao_rel);
        }
    }

    private static class c {
        static int a = 0;
        static int b = 1;
        static int c = 2;
        static int d = 3;
        static int e = 4;
        static int f = 5;
        static int g = 6;
        static int h = 7;
        static int i = 8;
        static int j = -1;
        public FoundChicken chicken;
        public Duobao duobao;
        public FoundGame game;
        public Game gameDetails;
        String k;
        String l;
        int m = j;
        int n;
        boolean o;
        int p = 0;
        int q = 0;
        int r = 0;
        int s = 0;

        c(int i, String str, String str2, int i2, boolean z, int i3, int i4, int i5) {
            this.q = i3;
            this.r = i4;
            this.s = i5;
            this.k = str2;
            this.l = str;
            this.m = i;
            this.n = i2;
            this.o = z;
        }

        c(int i, String str, String str2, int i2, boolean z) {
            this.k = str2;
            this.l = str;
            this.m = i;
            this.n = i2;
            this.o = z;
        }

        c(int i, String str, String str2, int i2, boolean z, FoundChicken foundChicken, Duobao duobao) {
            this.k = str2;
            this.l = str;
            this.m = i;
            this.n = i2;
            this.o = z;
            this.chicken = foundChicken;
            this.duobao = duobao;
        }

        c(int i, String str, String str2, int i2, boolean z, FoundGame foundGame) {
            this.k = str2;
            this.l = str;
            this.m = i;
            this.n = i2;
            this.o = z;
            this.game = foundGame;
        }

        c(int i, String str, String str2, int i2, boolean z, Game game) {
            this.k = str2;
            this.l = str;
            this.m = i;
            this.n = i2;
            this.o = z;
            this.gameDetails = game;
        }

        c(int i, String str, String str2, int i2, boolean z, int i3) {
            this.k = str2;
            this.l = str;
            this.m = i;
            this.n = i2;
            this.o = z;
            this.p = i3;
        }

        public static c newInstance(int i, String str, String str2, int i2, boolean z) {
            return new c(i, str, str2, i2, z);
        }

        public static c newInstance(int i, String str, String str2, int i2, boolean z, FoundChicken foundChicken, Duobao duobao) {
            return new c(i, str, str2, i2, z, foundChicken, duobao);
        }

        public static c newInstance(int i, String str, String str2, int i2, boolean z, FoundGame foundGame) {
            return new c(i, str, str2, i2, z, foundGame);
        }

        public static c newInstance(int i, String str, String str2, int i2, boolean z, Game game) {
            return new c(i, str, str2, i2, z, game);
        }

        public static c newInstance(int i, String str, String str2, int i2, boolean z, int i3, int i4, int i5) {
            return new c(i, str, str2, i2, z, i3, i4, i5);
        }

        public static c newInstance(int i, String str, String str2, int i2, boolean z, int i3) {
            return new c(i, str, str2, i2, z, i3);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (this.m == cVar.m && this.n == cVar.m && this.l == cVar.l) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "Item [iconUrl=" + this.k + ", description=" + this.l + ", id=" + this.m + ", type=" + this.n + "]";
        }
    }

    private static class d extends c {
        PunchInfo t;

        d(PunchInfo punchInfo) {
            super(c.j, "", "", c.g, false);
            this.t = punchInfo;
        }
    }

    private class e extends BroadcastReceiver {
        final /* synthetic */ MyProfileFragment a;

        private e(MyProfileFragment myProfileFragment) {
            this.a = myProfileFragment;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.i = true;
        }
    }

    private class f extends BroadcastReceiver {
        final /* synthetic */ MyProfileFragment a;

        private f(MyProfileFragment myProfileFragment) {
            this.a = myProfileFragment;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.i = true;
        }
    }

    private class g extends BaseAdapter {
        SparseArray<IGetView> a = new SparseArray();
        final /* synthetic */ MyProfileFragment b;

        class a implements IGetView {
            final /* synthetic */ g a;

            public a(g gVar) {
                this.a = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(this.a.b.getActivity()).inflate(R.layout.my_profile_item_avatar, viewGroup, false);
                }
                ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                if (cVar.k == null || cVar.k.length() == 0 || cVar.k.contains("drawable://")) {
                    imageView.setImageResource(UIHelper.getDefaultAvatar());
                } else {
                    FrescoImageloader.displayAvatar(imageView, cVar.k);
                }
                ((TextView) view.findViewById(R.id.title)).setText(cVar.l);
                if (UIHelper.isNightTheme()) {
                    view.setBackgroundResource(R.color.popupmenu_bg_dark);
                } else {
                    view.setBackgroundResource(R.drawable.profile_popupmenu_item_bg_day);
                }
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.vistor_lin);
                TextView textView = (TextView) view.findViewById(R.id.total_vistor);
                TextView textView2 = (TextView) view.findViewById(R.id.new_vistor);
                TextView textView3 = (TextView) view.findViewById(R.id.today_vistor);
                if (QsbkApp.currentUser != null) {
                    linearLayout.setVisibility(0);
                    textView.setVisibility(0);
                    textView.setText("总访客  " + cVar.q);
                    if (cVar.s > 0) {
                        textView2.setVisibility(0);
                        textView2.setText(MqttTopic.SINGLE_LEVEL_WILDCARD + cVar.s);
                    } else {
                        textView2.setVisibility(4);
                    }
                    textView3.setVisibility(0);
                    textView3.setText("今日  " + cVar.r);
                } else {
                    linearLayout.setVisibility(8);
                }
                return view;
            }
        }

        abstract class b implements IGetView {
            final /* synthetic */ g a;

            abstract int a();

            b(g gVar) {
                this.a = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                if (view != null) {
                    return view;
                }
                view = MyProfileFragment.b(this.a.b.getActivity(), -1, a());
                view.setBackgroundDrawable(null);
                return view;
            }
        }

        class c implements IGetView {
            final /* synthetic */ g a;

            c(g gVar) {
                this.a = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                h hVar;
                if (view == null) {
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.found_game_view_item, null);
                    hVar = new h(this.a.b, view);
                    view.setTag(hVar);
                } else {
                    hVar = (h) view.getTag();
                }
                Game game = cVar.gameDetails;
                hVar.a.setRatingViewScore(game.starStore);
                hVar.a.setDesText(game.description);
                hVar.a.setOnClickListener(new gs(this, game));
                hVar.a.isShowGift(!TextUtils.isEmpty(game.showGift));
                hVar.a.isShowDivider(game.shouldShowDivider);
                hVar.a.setGameDownloadText(game.act);
                hVar.a.setIconUrl(game.image);
                hVar.a.setName(game.name);
                hVar.a.setPlayNumText(game.showPlayersNum);
                hVar.a.setGiftDes(game.showGift);
                return view;
            }
        }

        class d implements IGetView {
            final /* synthetic */ g a;

            d(g gVar) {
                this.a = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                if (view == null || !(view.getTag() instanceof a)) {
                    view = LayoutInflater.from(this.a.b.getActivity()).inflate(R.layout.found_game_item, viewGroup, false);
                    view.setTag(new a(view));
                } else {
                    a aVar = (a) view.getTag();
                }
                view.setOnClickListener(new gt(this, cVar.game));
                return view;
            }
        }

        class e implements IGetView {
            final /* synthetic */ g a;

            e(g gVar) {
                this.a = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                b bVar;
                if (view == null || !(view.getTag() instanceof b)) {
                    view = LayoutInflater.from(this.a.b.getActivity()).inflate(R.layout.found_head_item, viewGroup, false);
                    bVar = new b(view);
                    view.setTag(bVar);
                } else {
                    bVar = (b) view.getTag();
                }
                if (UIHelper.isNightTheme()) {
                    bVar.a.setBackgroundResource(R.color.popupmenu_bg_dark);
                    UIHelper.imageViewFilter(bVar.b);
                    UIHelper.imageViewFilter(bVar.c);
                    UIHelper.imageViewFilter(bVar.d);
                    UIHelper.imageViewFilter(bVar.e);
                    bVar.b.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_nearby_night));
                    bVar.c.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_group_night));
                    bVar.d.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_store_night));
                    bVar.e.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_chicken_night));
                    bVar.f.setTextColor(-12171438);
                    bVar.i.setTextColor(-12171438);
                    bVar.g.setTextColor(-12171438);
                    bVar.h.setTextColor(-12171438);
                } else {
                    bVar.a.setBackgroundResource(R.drawable.profile_popupmenu_item_bg_day);
                    bVar.b.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_nearby));
                    bVar.c.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_group));
                    bVar.d.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_store));
                    bVar.e.setImageDrawable(UIHelper.getDrawable(R.drawable.found_ic_chicken));
                    bVar.f.setTextColor(-12894910);
                    bVar.i.setTextColor(-12894910);
                    bVar.g.setTextColor(-12894910);
                    bVar.h.setTextColor(-12894910);
                }
                if (FoundFragment.foundChickenHasTips) {
                    bVar.j.setVisibility(0);
                } else {
                    bVar.j.setVisibility(4);
                }
                OnClickListener guVar = new gu(this);
                bVar.b.setOnClickListener(guVar);
                bVar.f.setOnClickListener(guVar);
                guVar = new gv(this);
                bVar.c.setOnClickListener(guVar);
                bVar.g.setOnClickListener(guVar);
                if (cVar.chicken != null) {
                    if (cVar.chicken.show) {
                        bVar.i.setVisibility(0);
                    } else {
                        bVar.i.setVisibility(8);
                    }
                    guVar = new gw(this, cVar, bVar);
                    bVar.e.setOnClickListener(guVar);
                    bVar.i.setOnClickListener(guVar);
                } else {
                    bVar.i.setVisibility(8);
                    bVar.i.setOnClickListener(null);
                }
                if (cVar.duobao == null) {
                    bVar.k.setVisibility(8);
                    bVar.h.setVisibility(8);
                    bVar.m.setVisibility(8);
                } else if (cVar.duobao.show) {
                    if (MyProfileFragment.duobaoHasTips) {
                        bVar.k.setVisibility(8);
                    } else {
                        bVar.k.setVisibility(0);
                    }
                    bVar.h.setVisibility(0);
                    bVar.m.setVisibility(0);
                    guVar = new gx(this, cVar, bVar);
                    bVar.d.setOnClickListener(guVar);
                    bVar.h.setOnClickListener(guVar);
                    if (TextUtils.isEmpty(cVar.duobao.title)) {
                        bVar.h.setText("糗商城");
                    } else {
                        bVar.h.setText(cVar.duobao.title);
                    }
                } else {
                    bVar.k.setVisibility(8);
                    bVar.h.setVisibility(8);
                    bVar.m.setVisibility(8);
                }
                return view;
            }
        }

        class f extends b {
            final /* synthetic */ g b;

            f(g gVar) {
                this.b = gVar;
                super(gVar);
            }

            int a() {
                return Math.round(this.b.b.getActivity().getResources().getDisplayMetrics().density * 8.0f);
            }
        }

        class g implements IGetView {
            final /* synthetic */ g a;

            g(g gVar) {
                this.a = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(this.a.b.getActivity()).inflate(R.layout.my_profile_item_normal, viewGroup, false);
                }
                int intValue = cVar.k.contains("drawable://") ? Integer.valueOf(cVar.k.replace("drawable://", "")).intValue() : 0;
                if (intValue != 0) {
                    ((ImageView) view.findViewById(R.id.icon)).setImageResource(intValue);
                }
                ((TextView) view.findViewById(R.id.title)).setText(cVar.l);
                if (UIHelper.isNightTheme()) {
                    view.setBackgroundResource(R.color.popupmenu_bg_dark);
                } else {
                    view.setBackgroundResource(R.drawable.profile_popupmenu_item_bg_day);
                }
                if (cVar.o) {
                    view.findViewById(R.id.tips).setVisibility(0);
                } else {
                    view.findViewById(R.id.tips).setVisibility(8);
                }
                TextView textView = (TextView) view.findViewById(R.id.new_fans_tips);
                if (cVar.p > 0) {
                    textView.setVisibility(0);
                    textView.setText(cVar.p > 99 ? "99+" : cVar.p + "");
                    textView.setTextColor(this.a.b.getResources().getColor(UIHelper.getNewMessageTipsTextColor()));
                    textView.setBackgroundResource(UIHelper.getNewMessageTips());
                } else {
                    textView.setVisibility(8);
                }
                return view;
            }
        }

        class h implements IGetView {
            PunchCardCell a = new PunchCardCell();
            final /* synthetic */ g b;

            h(g gVar) {
                this.b = gVar;
            }

            public View getView(c cVar, View view, ViewGroup viewGroup) {
                d dVar = (d) cVar;
                this.a.performCreate(0, viewGroup, dVar.t);
                this.a.performUpdate(0, viewGroup, dVar.t);
                return this.a.getCellView();
            }
        }

        class i extends b {
            final /* synthetic */ g b;

            i(g gVar) {
                this.b = gVar;
                super(gVar);
            }

            int a() {
                return this.b.b.getActivity().getResources().getDimensionPixelSize(R.dimen.g_divider_small);
            }
        }

        g(MyProfileFragment myProfileFragment) {
            this.b = myProfileFragment;
            this.a.put(c.b, new a(this));
            this.a.put(c.d, new f(this));
            this.a.put(c.a, new g(this));
            this.a.put(c.c, new i(this));
            this.a.put(c.e, new e(this));
            this.a.put(c.f, new d(this));
            this.a.put(c.g, new h(this));
            this.a.put(c.h, new c(this));
            a();
        }

        private void a() {
        }

        public int getCount() {
            return this.b.e != null ? this.b.e.size() : 0;
        }

        public boolean isEnabled(int i) {
            int itemViewType = getItemViewType(i);
            return (itemViewType == c.d || itemViewType == c.c) ? false : true;
        }

        public int getViewTypeCount() {
            return c.i;
        }

        public int getItemViewType(int i) {
            return ((c) this.b.e.get(i)).n;
        }

        public Object getItem(int i) {
            return this.b.e.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 0;
            c cVar = (c) this.b.e.get(i);
            IGetView iGetView = (IGetView) this.a.get(getItemViewType(i));
            if (iGetView instanceof a) {
                String absoluteUrlOfMediumUserIcon;
                int i3;
                cVar.l = QsbkApp.currentUser != null ? QsbkApp.currentUser.userName : "登录 / 注册";
                if (QsbkApp.currentUser != null) {
                    absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(QsbkApp.currentUser.userIcon, QsbkApp.currentUser.userId);
                } else {
                    absoluteUrlOfMediumUserIcon = "drawable://" + UIHelper.getDefaultAvatar();
                }
                cVar.k = absoluteUrlOfMediumUserIcon;
                if (QsbkApp.currentUser != null) {
                    i3 = MyProfileFragment.totalVis;
                } else {
                    i3 = 0;
                }
                cVar.q = i3;
                if (QsbkApp.currentUser != null) {
                    i3 = MyProfileFragment.todayVis;
                } else {
                    i3 = 0;
                }
                cVar.r = i3;
                if (QsbkApp.currentUser != null) {
                    i2 = MyProfileFragment.newVis;
                }
                cVar.s = i2;
            }
            if (iGetView != null) {
                return iGetView.getView(cVar, view, viewGroup);
            }
            return null;
        }
    }

    class h {
        GameItemView a;
        final /* synthetic */ MyProfileFragment b;

        public h(MyProfileFragment myProfileFragment, View view) {
            this.b = myProfileFragment;
            this.a = (GameItemView) view.findViewById(R.id.game);
        }
    }

    private static View b(Context context, int i, int i2) {
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(i, i2));
        return view;
    }

    private static void d(Activity activity) {
        activity.startActivity(new Intent(activity, NearByActivity.class));
    }

    private static void c(Activity activity, String str, String str2) {
        String str3;
        if (str.contains("?")) {
            str3 = str + "&uuid=" + DeviceUtils.getAndroidId();
        } else {
            str3 = str + "?uuid=" + DeviceUtils.getAndroidId();
        }
        Intent intent = new Intent(activity, OfficialMsgDetailActivity.class);
        intent.putExtra("url", str3);
        intent.putExtra("title", str2);
        activity.startActivity(intent);
    }

    private static void e(Activity activity) {
        NearByGroupActivity.launch(activity);
    }

    private static void f(Activity activity) {
        LoginPermissionClickDelegate.startLoginActivity(activity);
    }

    private static void b(Activity activity, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "糗百货";
        }
        SimpleWebActivity.launch((Context) activity, str, str2);
    }

    private static void d(Activity activity, String str, String str2) {
        if (QsbkApp.currentUser != null) {
            if (str.contains("?")) {
                str = str + "&token=" + QsbkApp.currentUser.token;
            } else {
                str = str + "?token=" + QsbkApp.currentUser.token;
            }
        }
        Intent intent = new Intent(activity, GameWebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("title", str2);
        activity.startActivity(intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_profile, viewGroup, false);
        this.s = (ImageView) inflate.findViewById(R.id.activity_notification);
        this.s.setOnClickListener(new gl(this));
        if (!k()) {
            SplashAdManager instance = SplashAdManager.instance();
            instance.doTaskOnLoaded(new gm(this));
            instance.loadSplashAd();
        }
        this.c = (ListView) inflate.findViewById(R.id.list);
        this.m = (ImageView) inflate.findViewById(R.id.award_image);
        this.b = (Toolbar) inflate.findViewById(R.id.toolbar_my);
        this.b.setTitle("个人中心");
        this.b.setLogo(UIHelper.getActionBarQiushiIC());
        this.d = new g(this);
        this.c.setAdapter(this.d);
        a();
        c();
        return inflate;
    }

    public void onStart() {
        super.onStart();
        b();
    }

    public void onResume() {
        super.onResume();
        if (this.i) {
            j();
            a();
            this.i = false;
        } else if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public void getVistorInfo() {
        if (!this.l) {
            this.l = true;
            new SimpleHttpTask(Constants.TOTAL_VISTOR_URL, new go(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void startGetFoundGameAndChicken() {
        SharePreferenceUtils.setSharePreferencesValue("found_timestamp", new Date().getTime());
        new SimpleHttpTask(Constants.FOUND_GET_GAME_AND_CHICKEN, new gp(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void getFoundInfoFromLocal() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("found_chicken_and_game");
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            try {
                JSONObject jSONObject = new JSONObject(sharePreferencesValue);
                FoundGame foundGame = new FoundGame(jSONObject.optJSONObject(EventWindow.JUMP_GAME));
                FoundChicken foundChicken = new FoundChicken(jSONObject.optJSONObject("video"));
                Duobao duobao = new Duobao(jSONObject.optJSONObject(EventWindow.JUMP_DUOBAO));
                FoundFragment.foundStaticGame = foundGame;
                FoundFragment.foundStaticChicken = foundChicken;
                duobao = duobao;
                a();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a() {
        int i;
        String absoluteUrlOfMediumUserIcon;
        int i2;
        duobaoHasTips = SharePreferenceUtils.getSharePreferencesBoolValue(DUOBAO_HAS_TIPS);
        FoundFragment.foundGameHasTips = SharePreferenceUtils.getSharePreferencesBoolValue(FoundFragment.FOUND_GAME_HAS_TIPS);
        FoundFragment.foundChickenHasTips = SharePreferenceUtils.getSharePreferencesBoolValue(FoundFragment.FOUND_CHICKEN_HAS_TIPS);
        if (QsbkApp.currentUser != null) {
            i = 1;
        } else {
            boolean z = false;
        }
        this.e.clear();
        this.e.add(c.newInstance(c.j, "", "", c.d, false));
        if (i != 0) {
            absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(QsbkApp.currentUser.userIcon, QsbkApp.currentUser.userId);
        } else {
            absoluteUrlOfMediumUserIcon = "drawable://" + UIHelper.getDefaultAvatar();
        }
        if (i != 0) {
            i2 = totalVis;
        } else {
            i2 = 0;
        }
        totalVis = i2;
        if (i != 0) {
            i2 = todayVis;
        } else {
            i2 = 0;
        }
        todayVis = i2;
        if (i != 0) {
            i2 = newVis;
        } else {
            i2 = 0;
        }
        newVis = i2;
        this.w = c.newInstance(1, i != 0 ? QsbkApp.currentUser.userName : "登录 / 注册", absoluteUrlOfMediumUserIcon, c.b, false, totalVis, todayVis, newVis);
        if (this.e.contains(this.w)) {
            this.e.remove(this.w);
        }
        this.e.add(this.w);
        this.e.add(c.newInstance(c.j, "", "", c.d, false));
        this.e.add(c.newInstance(c.j, "", "", c.d, false));
        this.x = c.newInstance(13, "糗百钱袋", "drawable://" + UIHelper.getWalletIcon(), c.a, false);
        this.e.add(this.x);
        this.e.add(c.newInstance(c.j, "", "", c.d, false));
        this.y = c.newInstance(2, "管理糗事和动态", "drawable://" + UIHelper.getManageMyQiushiIcon(), c.a, false);
        this.e.add(this.y);
        this.e.add(c.newInstance(c.j, "", "", c.c, false));
        boolean z2 = SharePreferenceUtils.getSharePreferencesBoolValue("medal_tips") && i != 0;
        this.r = z2;
        this.z = c.newInstance(12, "我的勋章", "drawable://" + UIHelper.getMyMedalIcon(), c.a, this.r);
        this.e.add(this.z);
        this.e.add(c.newInstance(c.j, "", "", c.d, false));
        if (FoundFragment.foundStaticGame != null && FoundFragment.foundStaticGame.show) {
            this.A = c.newInstance(11, "", "", c.f, false, FoundFragment.foundStaticGame);
            if (!this.e.contains(this.A)) {
                this.e.add(this.A);
            }
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                if (((c) it.next()).n == 14) {
                    it.remove();
                }
            }
            if (!(FoundFragment.foundStaticGame == null || TextUtils.isEmpty(FoundFragment.foundStaticGame.link) || !FoundFragment.foundStaticGame.show)) {
                for (i2 = 0; i2 < FoundFragment.foundStaticGame.games.size(); i2++) {
                    boolean z3;
                    Game game = (Game) FoundFragment.foundStaticGame.games.get(i2);
                    game.pos = i2;
                    if (i2 != FoundFragment.foundStaticGame.games.size() - 1) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    game.shouldShowDivider = z3;
                    this.e.add(c.newInstance(14, "", "", c.h, false, game));
                }
                this.e.add(c.newInstance(c.j, "", "", c.d, false));
            }
            this.e.add(c.newInstance(c.j, "", "", c.d, false));
        }
        this.B = c.newInstance(5, UIHelper.isNightTheme() ? "日间模式" : "夜间模式", "drawable://" + UIHelper.getThemeModeIcon(), c.a, false);
        this.e.add(this.B);
        this.e.add(c.newInstance(c.j, "", "", c.d, false));
        this.C = c.newInstance(7, "设置", "drawable://" + UIHelper.getSettingIcon(), c.a, TipsManager.shouldShowSecurityBind(getActivity()));
        this.e.add(this.C);
        this.c.setOnItemClickListener(new gq(this));
        if (i != 0) {
            getVistorInfo();
        }
        this.d.notifyDataSetChanged();
        if (FoundFragment.foundStaticChicken == null || FoundFragment.foundStaticGame == null) {
            startGetFoundGameAndChicken();
        }
    }

    private void b() {
        PunchCardCell.getPunchCard(new gr(this));
    }

    private void c() {
        if (this.n) {
            this.m.setVisibility(8);
            this.m.setOnClickListener(new gg(this));
            ViewGroup.LayoutParams layoutParams = this.m.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -2;
                layoutParams.height = -2;
                this.m.requestLayout();
            }
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.p), getContext().getApplicationContext()).subscribe(new gh(this), UiThreadImmediateExecutorService.getInstance());
            return;
        }
        this.m.setVisibility(8);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f = new e();
        this.g = new f();
        this.h = LocalBroadcastManager.getInstance(activity);
        this.h.registerReceiver(this.f, new IntentFilter(MainActivity.ACTION_QB_LOGIN));
        this.h.registerReceiver(this.g, new IntentFilter(MainActivity.ACTION_QB_LOGOUT));
        this.h.registerReceiver(this.j, new IntentFilter(TipsManager.SHOW_SECURITY_BIND));
        this.h.registerReceiver(this.k, new IntentFilter(InfoCompleteActivity.ACTION_CHANGE_USERINFO));
        this.h.registerReceiver(this.a, new IntentFilter(MainActivity.ACTION_NEW_FANS));
    }

    public void onDetach() {
        this.h.unregisterReceiver(this.f);
        this.h.unregisterReceiver(this.g);
        this.h.unregisterReceiver(this.j);
        this.h.unregisterReceiver(this.a);
        this.h.unregisterReceiver(this.k);
        super.onDetach();
    }

    public void onDestroy() {
        super.onDestroy();
        j();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.u != null && !this.u.isClosed()) {
            this.u.close();
        }
    }

    private void d() {
        if (QsbkApp.currentUser == null) {
            e();
        } else {
            startActivity(new Intent(getActivity(), MyInfoActivity.class));
        }
    }

    private void e() {
        startActivity(new Intent(getActivity(), ActionBarLoginActivity.class));
    }

    private void a(boolean z) {
        if (QsbkApp.currentUser == null) {
            e();
            return;
        }
        Intent intent = new Intent(getActivity(), ManageQiuShiActivity.class);
        intent.putExtra("isFromCollect", z);
        startActivity(intent);
    }

    private void f() {
        if (QsbkApp.currentUser == null) {
            e();
        } else {
            startActivity(new Intent(getActivity(), ManageQiuShiAndDynamicActivity.class));
        }
    }

    private void g() {
        if (QsbkApp.currentUser == null) {
            MedalListActivity.launch(getActivity(), "-1", true, MedalListActivity.MEDAL_FROM);
        } else {
            MedalListActivity.launch(getActivity(), QsbkApp.currentUser.userId, true, MedalListActivity.MEDAL_FROM);
        }
    }

    private void b(boolean z) {
        if (QsbkApp.currentUser == null) {
            e();
            return;
        }
        Intent intent = new Intent(getActivity(), QiuYouActivity.class);
        intent.putExtra("has_new_fans", z);
        startActivity(intent);
    }

    private void h() {
        UIHelper.toggleTheme();
        FeedsAd.getInstance().refreshThem();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).reload();
        }
    }

    private void i() {
        startActivity(new Intent(getActivity(), ActionBarUserSettingNavi.class));
    }

    private void j() {
        if (this.e != null) {
            this.e.clear();
        }
    }

    private boolean k() {
        SplashAdGroup group = SplashAdManager.instance().getGroup();
        if (group == null) {
            this.s.setVisibility(8);
            return false;
        }
        SplashAdItem activityItem = group.getActivityItem(SplashAdItem.TAB_ME);
        int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
        if (activityItem == null || activityItem.startTime > correctTime || correctTime >= activityItem.endTime) {
            this.s.setVisibility(8);
        } else {
            this.t = activityItem;
            this.s.setVisibility(8);
            this.u = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(activityItem.picUrl), QsbkApp.mContext);
            this.u.subscribe(new gi(this, activityItem), UiThreadImmediateExecutorService.getInstance());
        }
        return true;
    }
}
