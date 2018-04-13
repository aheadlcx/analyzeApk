package qsbk.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.android.phone.mrpc.core.Headers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.model.Banner;
import qsbk.app.model.LiveRoom;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.LiveBannerCell;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.ptr.AlphaAnimOffsetListener;

public class BaseLiveTabFragment extends BaseFragment implements PtrListener {
    public static final int REQUEST_CODE_LIVE = 1001;
    protected int a = 1;
    protected ArrayList<LiveRoom> b;
    protected PtrLayout c;
    protected ListView d;
    private final long e = 60000;
    private HttpTask f;
    private ArrayList<Object> g;
    private LiveRoomAdapter h;
    private TipsHelper i;
    private ProgressBar j;
    private long k = -1;
    private String l = getClass().getSimpleName();
    private View m;
    private ArrayList<Banner> n = new ArrayList();
    private LiveBannerPackage o = new LiveBannerPackage(this);

    public static class DoubleLiveRoom {
        public LiveRoom left;
        public LiveRoom right;

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof DoubleLiveRoom)) {
                DoubleLiveRoom doubleLiveRoom = (DoubleLiveRoom) obj;
                if (Util.equals(this.left, doubleLiveRoom.left) && Util.equals(this.right, doubleLiveRoom.right)) {
                    return true;
                }
            }
            return false;
        }
    }

    public class DoubleLiveRoomCell extends BaseCell {
        final /* synthetic */ BaseLiveTabFragment a;
        private LinearLayout b;
        private LiveRoomCell c;
        private LiveRoomCell d;
        private int e;

        public DoubleLiveRoomCell(BaseLiveTabFragment baseLiveTabFragment) {
            this.a = baseLiveTabFragment;
        }

        public DoubleLiveRoom getItem() {
            return (DoubleLiveRoom) super.getItem();
        }

        public void onCreate() {
            this.e = UIHelper.dip2px(getContext(), 3.0f);
            this.b = new LinearLayout(getContext());
            this.b.setOrientation(0);
            setCellView(this.b);
            this.c = new LiveRoomCell(this.a);
            this.d = new LiveRoomCell(this.a);
            DoubleLiveRoom item = getItem();
            this.c.performCreate(this.q, this.b, item.left);
            this.d.performCreate(this.q, this.b, item.right);
            View cellView = this.c.getCellView();
            View cellView2 = this.d.getCellView();
            View view = new View(getContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            this.b.addView(cellView, layoutParams);
            this.b.addView(view, new LinearLayout.LayoutParams(this.e, 1));
            this.b.addView(cellView2, layoutParams);
            cellView.setOnClickListener(new f(this));
            cellView2.setOnClickListener(new g(this));
        }

        public void onUpdate() {
            if (this.q == 0) {
                this.b.setPadding(this.e, this.e, this.e, this.e);
            } else {
                this.b.setPadding(this.e, 0, this.e, this.e);
            }
            DoubleLiveRoom item = getItem();
            this.c.performUpdate(this.q, this.b, item.left);
            this.d.performUpdate(this.q, this.b, item.right);
        }
    }

    public class LiveBannerPackage {
        final /* synthetic */ BaseLiveTabFragment a;
        private LiveBannerCell b;
        public ArrayList<Banner> banners;
        public int pos;

        public LiveBannerPackage(BaseLiveTabFragment baseLiveTabFragment, ArrayList<Banner> arrayList) {
            this.a = baseLiveTabFragment;
            this.banners = arrayList;
        }

        public LiveBannerPackage(BaseLiveTabFragment baseLiveTabFragment) {
            this.a = baseLiveTabFragment;
        }
    }

    public class LiveRoomAdapter extends BaseImageAdapter {
        final /* synthetic */ BaseLiveTabFragment a;
        private View b;

        public LiveRoomAdapter(BaseLiveTabFragment baseLiveTabFragment, ArrayList<Object> arrayList, Activity activity) {
            this.a = baseLiveTabFragment;
            super(arrayList, activity);
        }

        public void setView(View view) {
            this.b = view;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            Object item = getItem(i);
            if (item instanceof DoubleLiveRoom) {
                return 0;
            }
            if (item instanceof LiveBannerPackage) {
                return 1;
            }
            return super.getItemViewType(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    DoubleLiveRoomCell doubleLiveRoomCell;
                    if (view == null || !(view.getTag() instanceof DoubleLiveRoomCell)) {
                        doubleLiveRoomCell = new DoubleLiveRoomCell(this.a);
                        doubleLiveRoomCell.performCreate(i, viewGroup, getItem(i));
                        view = doubleLiveRoomCell.getCellView();
                        view.setTag(doubleLiveRoomCell);
                    } else {
                        doubleLiveRoomCell = (DoubleLiveRoomCell) view.getTag();
                    }
                    doubleLiveRoomCell.performUpdate(i, viewGroup, getItem(i));
                    break;
                case 1:
                    LiveBannerCell liveBannerCell;
                    if (view == null || !(view.getTag() instanceof LiveBannerCell)) {
                        LiveBannerCell liveBannerCell2 = new LiveBannerCell(this.b);
                        liveBannerCell2.performCreate(i, viewGroup, getItem(i));
                        view = liveBannerCell2.getCellView();
                        view.setTag(liveBannerCell2);
                        liveBannerCell = liveBannerCell2;
                    } else {
                        liveBannerCell = (LiveBannerCell) view.getTag();
                    }
                    Object item = getItem(i);
                    if (item instanceof LiveBannerPackage) {
                        ((LiveBannerPackage) item).b = liveBannerCell;
                    }
                    liveBannerCell.performUpdate(i, viewGroup, getItem(i));
                    break;
            }
            return view;
        }
    }

    public class LiveRoomCell extends BaseCell {
        final /* synthetic */ BaseLiveTabFragment a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private ImageView g;
        private ImageView h;
        private int i;

        public LiveRoomCell(BaseLiveTabFragment baseLiveTabFragment) {
            this.a = baseLiveTabFragment;
        }

        protected Drawable a(Context context) {
            return TileBackground.getBackgroud(context, BgImageType.ARTICLE);
        }

        void a(int i) {
            this.i = i;
        }

        public void onCreate() {
            setCellView((int) R.layout.item_live_room);
            this.b = (ImageView) findViewById(R.id.image);
            this.c = (TextView) findViewById(R.id.title);
            this.d = (TextView) findViewById(R.id.user_name);
            this.e = (TextView) findViewById(R.id.viewer_num);
            this.f = (TextView) findViewById(R.id.tv_location);
            this.g = (ImageView) findViewById(R.id.dec_view);
            this.h = (ImageView) findViewById(R.id.iv_gaming);
        }

        public void onUpdate() {
            LiveRoom liveRoom = (LiveRoom) getItem();
            if (liveRoom == null) {
                getCellView().setVisibility(4);
                return;
            }
            getCellView().setVisibility(0);
            displayImage(this.b, liveRoom.thumbnail_url);
            this.c.setText(liveRoom.content);
            this.d.setText(liveRoom.author != null ? liveRoom.author.name : "");
            if (liveRoom.is_follow) {
                this.e.setText(String.format("%s人", new Object[]{liveRoom.visitors_count}));
            } else {
                this.e.setText(String.format("%s人", new Object[]{liveRoom.visitors_count}));
            }
            if (TextUtils.isEmpty(liveRoom.location)) {
                this.f.setVisibility(8);
            } else {
                this.f.setVisibility(0);
                this.f.setText(liveRoom.location);
            }
            if (liveRoom.author == null || liveRoom.author.list_dec_url == null) {
                this.g.setVisibility(8);
            } else {
                this.g.setVisibility(0);
                displayImage(this.g, liveRoom.author.list_dec_url, null, null);
            }
            this.h.setVisibility(0);
            switch (liveRoom.game_id) {
                case 1:
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_hlnb_night : R.drawable.ic_hlnb);
                    return;
                case 2:
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_ypdx_night : R.drawable.ic_game_ypdx);
                    return;
                case 3:
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_mgdz_night : R.drawable.ic_game_mgdz);
                    return;
                case 4:
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_ffl_night : R.drawable.ic_game_ffl);
                    return;
                case 5:
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_cjzp_night : R.drawable.ic_game_cjzp);
                    return;
                default:
                    if (liveRoom.isMicMode()) {
                        int i;
                        ImageView imageView = this.h;
                        if (UIHelper.isNightTheme()) {
                            i = R.drawable.ic_game_mic_night;
                        } else {
                            i = R.drawable.ic_game_mic;
                        }
                        imageView.setImageResource(i);
                        return;
                    }
                    this.h.setImageResource(0);
                    this.h.setVisibility(8);
                    return;
            }
        }

        public void onClick() {
            super.onClick();
            LivePullLauncher.from(this.a.getParentFragment()).with((LiveRoom) getItem()).withStSource(this.a.a()).withTabIndex(this.i).launch(1001);
            getCellView().setEnabled(false);
            getCellView().postDelayed(new h(this), 1000);
        }
    }

    public int getLayoutId() {
        return R.layout.layout_ptr_listview;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.m == null) {
            a(layoutInflater, viewGroup);
        } else {
            ViewParent parent = this.m.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.m);
            }
        }
        return this.m;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof LiveTabsFragment)) {
            this.c.setOnScrollOffsetListener(new AlphaAnimOffsetListener(((LiveTabsFragment) parentFragment).getActivityNotification()));
        }
    }

    public void onStart() {
        super.onStart();
        c();
        loadLiveRooms();
    }

    protected void a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.m = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        this.c = (PtrLayout) this.m.findViewById(R.id.ptr);
        this.d = (ListView) this.m.findViewById(R.id.listview);
        this.c.setRefreshEnable(true);
        this.c.setLoadMoreEnable(false);
        this.c.setPtrListener(this);
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.i = new TipsHelper(this.m.findViewById(R.id.tips));
        this.h = new LiveRoomAdapter(this, this.g, getActivity());
        this.h.setView(this.c);
        this.d.setAdapter(this.h);
        this.j = (ProgressBar) this.m.findViewById(R.id.progress);
        if (!UIHelper.isNightTheme()) {
            this.c.setBackgroundColor(-1);
            this.m.setBackgroundColor(-1);
        }
    }

    public void onRefresh() {
        if (this.f != null) {
            this.f.cancel(true);
            this.f = null;
        }
        c();
        this.a = 1;
        loadLiveRooms();
    }

    public void onRoomClosed(String str) {
        if (this.b != null && !this.b.isEmpty()) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                LiveRoom liveRoom = (LiveRoom) it.next();
                if (liveRoom.stream_id.equals(str)) {
                    this.b.remove(liveRoom);
                    updateDoubleList();
                    this.h.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    public void onFollowStateChange(String str, boolean z) {
        if (this.b != null && !this.b.isEmpty()) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                LiveRoom liveRoom = (LiveRoom) it.next();
                if (liveRoom.stream_id.equals(str) && liveRoom.is_follow != z) {
                    liveRoom.is_follow = z;
                    this.h.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    public void onLoadMore() {
        loadLiveRooms();
    }

    public String getUrl() {
        return String.format(Constants.LIVE_ALL, new Object[]{Integer.valueOf(30), Integer.valueOf(this.a)});
    }

    protected String a() {
        return LivePullLauncher.STSOURCE_livelist;
    }

    public void fillData(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("lives");
        boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
        if (this.a == 1) {
            if (jSONArray.length() > 0) {
                this.g.clear();
                this.b.clear();
                this.n.clear();
                if (this.o.b != null) {
                    this.o.b.notifyDataSetChanged();
                }
            }
            this.c.refreshDone();
        } else {
            this.c.loadMoreDone(true);
        }
        int length = jSONArray.length();
        if (this.a == 1 && length == 0 && getUserVisibleHint()) {
            this.g.clear();
            this.b.clear();
            this.n.clear();
            if (this.o.b != null) {
                this.o.b.notifyDataSetChanged();
            }
            this.h.notifyDataSetChanged();
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, getDataEmptyTip(), Integer.valueOf(0)).show();
            return;
        }
        int i;
        for (i = 0; i < length; i++) {
            LiveRoom parse = LiveRoom.parse(jSONArray.optJSONObject(i));
            if (!(parse == null || this.b.contains(parse))) {
                this.b.add(parse);
            }
        }
        if (this.a == 1) {
            jSONArray = jSONObject.optJSONArray("banners");
            if (jSONArray != null && jSONArray.length() > 0) {
                for (i = 0; i < jSONArray.length(); i++) {
                    Banner banner = new Banner();
                    banner.parseFromJSONObject(jSONArray.optJSONObject(i));
                    this.n.add(banner);
                }
            }
            this.o.banners = this.n;
            b();
            if (this.o.b != null) {
                this.o.b.notifyDataSetChanged();
            }
        }
        updateDoubleList();
        this.h.notifyDataSetChanged();
        if (this.a == 1) {
            this.d.setSelection(0);
        }
        i = jSONObject.optInt(Headers.REFRESH);
        if (this.a == 1 && i > 0 && getUserVisibleHint()) {
            ToastAndDialog.makePositiveToast(getActivity(), String.format("为您刷新了%s条直播", new Object[]{Integer.valueOf(i)}), Integer.valueOf(0)).show();
        }
        this.a++;
        if (z) {
            this.c.setLoadMoreEnable(true);
        } else {
            this.c.setLoadMoreEnable(false);
        }
    }

    private void b() {
        if (this.n.size() != 0) {
            if (this.g.contains(this.o)) {
                this.g.remove(this.o);
                this.g.add(0, this.o);
                return;
            }
            this.g.add(0, this.o);
        }
    }

    public void updateDoubleList() {
        DoubleLiveRoom doubleLiveRoom = new DoubleLiveRoom();
        int size = this.b.size();
        if (this.g.contains(this.o)) {
            this.g.clear();
            this.g.add(0, this.o);
        } else {
            this.g.clear();
        }
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            if (i != 0) {
                doubleLiveRoom.left = (LiveRoom) this.b.get(i2);
                i = 0;
            } else {
                doubleLiveRoom.right = (LiveRoom) this.b.get(i2);
                if (this.g.contains(doubleLiveRoom)) {
                    i = 1;
                } else {
                    this.g.add(doubleLiveRoom);
                    doubleLiveRoom = new DoubleLiveRoom();
                    i = 1;
                }
            }
        }
        if (i == 0) {
            this.g.add(doubleLiveRoom);
        }
    }

    public void refresh() {
        if (this.d != null) {
            this.d.setSelection(0);
            if (this.c.isRefreshing()) {
                this.c.refreshDone();
            }
            this.c.refresh();
        }
    }

    private void c() {
        if ((this.b == null || this.b.isEmpty()) && this.j != null) {
            this.j.setVisibility(0);
        }
    }

    private void d() {
        if (this.j != null) {
            this.j.setVisibility(8);
        }
    }

    public void clearImageMemory() {
        List arrayList = new ArrayList(this.g.size());
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            if (this.g.get(i) instanceof DoubleLiveRoom) {
                DoubleLiveRoom doubleLiveRoom = (DoubleLiveRoom) this.g.get(i);
                if (doubleLiveRoom.left != null) {
                    arrayList.add(doubleLiveRoom.left.thumbnail_url);
                }
                if (doubleLiveRoom.right != null) {
                    arrayList.add(doubleLiveRoom.right.thumbnail_url);
                }
            }
        }
        FrescoImageloader.evictFromMemoryCache(arrayList);
    }

    public void onDestroy() {
        super.onDestroy();
        clearImageMemory();
    }

    public void loadLiveRooms() {
        this.i.hide();
        String url = getUrl();
        this.f = new HttpTask(url, url, new e(this));
        this.f.execute(new Void[0]);
    }

    public String getDataEmptyTip() {
        if (isAdded()) {
            return getResources().getString(R.string.has_no_new_live);
        }
        return "";
    }

    public void onResume() {
        super.onResume();
        if (this.k != -1 && Math.abs(System.currentTimeMillis() - this.k) >= 60000) {
            refresh();
        } else if (this.d != null && this.c != null) {
            this.c.refreshDone(true);
        }
    }

    public void onPause() {
        super.onPause();
        this.k = System.currentTimeMillis();
    }
}
