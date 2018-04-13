package qsbk.app.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LiveBannerViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiushibaike.statsdk.StatSDK;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.R;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.fragments.BaseLiveTabFragment.LiveBannerPackage;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.live.widget.DotView;
import qsbk.app.model.Banner;

public class LiveBannerCell extends BaseCell {
    private LiveBannerViewPager a;
    private LiveBannerAdapter b;
    private DotView c;
    private PtrLayout d;

    public class LiveBannerAdapter extends PagerAdapter {
        final /* synthetic */ LiveBannerCell a;

        public LiveBannerAdapter(LiveBannerCell liveBannerCell) {
            this.a = liveBannerCell;
        }

        public int getCount() {
            return (this.a.getItem().banners.size() * 2) * 10000;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(this.a.getContext()).inflate(R.layout.banner_view, null, false);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.image);
            int size = i % this.a.getItem().banners.size();
            Banner banner = (Banner) this.a.getItem().banners.get(size);
            Intent intent = new Intent();
            FrescoImageloader.displayImage(simpleDraweeView, banner.url);
            viewGroup.addView(inflate, -1, -1);
            inflate.setOnClickListener(new cj(this, banner, intent, size));
            return inflate;
        }
    }

    public LiveBannerCell(View view) {
        if (view instanceof PtrLayout) {
            this.d = (PtrLayout) view;
        }
    }

    private static void c(Intent intent, Banner banner, Context context) {
        intent.setClass(context, WebActivity.class);
        intent.putExtra("link", banner.redirect_url);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        AppUtils.getInstance().getAppContext().startActivity(intent);
    }

    private static void b(Intent intent, Banner banner, Context context, int i) {
        LivePullLauncher.from(context).with(banner.redirect_id + "").with(String.valueOf(banner.redirect_source), true).withStSource(LivePullLauncher.STSOURCE_livebanner).withTabIndex(i).launch(0);
    }

    private static void d(Intent intent, Banner banner, Context context) {
        String l = Long.toString(banner.platform_id);
        MyInfoActivity.launch(context, l, MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(9, l, "来自直播"));
    }

    private static String b(Banner banner) {
        String str = "";
        if ("web".equalsIgnoreCase(banner.redirect_type)) {
            return banner.redirect_url;
        }
        if ("live".equalsIgnoreCase(banner.redirect_type) || "user".equalsIgnoreCase(banner.redirect_type)) {
            return banner.redirect_source + "_" + banner.redirect_id;
        }
        return str;
    }

    public void onCreate() {
        setCellView((int) R.layout.live_banner_container);
        this.c = (DotView) findViewById(R.id.live_banner_dot_view);
        this.c.setDotNormal(R.drawable.banner_dot_normal);
        this.c.setDotSelected(R.drawable.banner_dot_selected);
        this.c.setLeftMargin(WindowUtils.dp2Px(2));
        this.c.setDotHeight(WindowUtils.dp2Px(4));
        this.c.setDotWide(WindowUtils.dp2Px(8));
        this.a = (LiveBannerViewPager) findViewById(R.id.live_banner_view_pager);
        this.a.getLayoutParams().height = (int) (((double) WindowUtils.getScreenWidth()) * ((Banner) getItem().banners.get(0)).ratio);
        this.a.setOffscreenPageLimit(2);
        this.a.setOnPageChangeListener(new ci(this));
        this.a.bindView(this.d);
        this.b = new LiveBannerAdapter(this);
        this.a.setAdapter(this.b);
    }

    public LiveBannerPackage getItem() {
        return (LiveBannerPackage) super.getItem();
    }

    public void notifyDataSetChanged() {
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }

    public void onUpdate() {
        this.b.notifyDataSetChanged();
        LiveBannerPackage item = getItem();
        int i = item.pos;
        ArrayList arrayList = item.banners;
        this.a.setCurrentItem((arrayList.size() * 10000) + (i % arrayList.size()), false);
        this.c.setDotCount(getItem().banners.size());
        this.c.setSelectedDot(i % arrayList.size());
    }

    public void onClick() {
        super.onClick();
        ArrayList arrayList = getItem().banners;
        int currentItem = this.a.getCurrentItem() % arrayList.size();
        Banner banner = (Banner) arrayList.get(currentItem);
        Intent intent = new Intent();
        if ("web".equalsIgnoreCase(banner.redirect_type)) {
            c(intent, banner, getContext());
        } else if ("live".equalsIgnoreCase(banner.redirect_type)) {
            b(intent, banner, getContext(), currentItem);
        } else if ("user".equalsIgnoreCase(banner.redirect_type)) {
            d(intent, banner, getContext());
        }
    }

    private void a() {
        int size = getItem().banners.size();
        if (size > 0) {
            Banner banner = (Banner) getItem().banners.get(this.a.getCurrentItem() % size);
            StatSDK.onEvent(getContext(), "banner_event", banner.redirect_type, b(banner), "show", "");
        }
    }
}
