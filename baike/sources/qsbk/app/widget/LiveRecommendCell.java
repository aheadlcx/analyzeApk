package qsbk.app.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.model.Live;
import qsbk.app.model.LivePackage;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;

public class LiveRecommendCell extends BaseCell {
    private ViewPager a;
    private LivePagerAdapter b;
    private String c;
    private boolean d;
    private boolean e = false;

    public class LivePagerAdapter extends PagerAdapter {
        final /* synthetic */ LiveRecommendCell a;

        public LivePagerAdapter(LiveRecommendCell liveRecommendCell) {
            this.a = liveRecommendCell;
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            this.a.e = false;
        }

        public int getItemPosition(Object obj) {
            if (this.a.e) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        public int getCount() {
            return (this.a.getItem().lives.size() * 2) * 10000;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(this.a.getContext()).inflate(R.layout.live_recommend_item, null, false);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.live_icon);
            TextView textView = (TextView) inflate.findViewById(R.id.live_num);
            TextView textView2 = (TextView) inflate.findViewById(R.id.live_des);
            TextView textView3 = (TextView) inflate.findViewById(R.id.live_name);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_gaming);
            inflate.setOnClickListener(new cl(this, inflate));
            Live live = (Live) this.a.getItem().lives.get(i % this.a.getItem().lives.size());
            if (TextUtils.isEmpty(live.image)) {
                imageView.setImageDrawable(TileBackground.getBackgroud(imageView.getContext(), BgImageType.ARTICLE));
            } else {
                this.a.displayImage(imageView, live.image);
            }
            int i2 = R.drawable.live_online_count;
            if (live.isFollow) {
                i2 = R.drawable.live_online_count_follow;
            }
            textView.setText(live.liveNo + "");
            textView.setCompoundDrawablesWithIntrinsicBounds(this.a.getContext().getResources().getDrawable(i2), null, null, null);
            if (TextUtils.isEmpty(live.liveDes)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                textView2.setText(live.liveDes);
            }
            if (live.author == null) {
                textView3.setVisibility(8);
            } else if (TextUtils.isEmpty(live.author.name)) {
                textView3.setVisibility(8);
            } else {
                textView3.setVisibility(0);
                textView3.setText(live.author.name);
            }
            imageView2.setVisibility(0);
            switch (live.gameId) {
                case 1:
                    imageView2.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_hlnb_night : R.drawable.ic_hlnb);
                    break;
                case 2:
                    imageView2.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_ypdx_night : R.drawable.ic_game_ypdx);
                    break;
                case 3:
                    imageView2.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_mgdz_night : R.drawable.ic_game_mgdz);
                    break;
                case 4:
                    imageView2.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_ffl_night : R.drawable.ic_game_ffl);
                    break;
                case 5:
                    imageView2.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_game_cjzp_night : R.drawable.ic_game_cjzp);
                    break;
                default:
                    imageView2.setImageResource(0);
                    imageView2.setVisibility(8);
                    break;
            }
            viewGroup.addView(inflate);
            return inflate;
        }
    }

    public LiveRecommendCell(String str, boolean z) {
        this.c = str;
        this.d = z;
    }

    private static void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                StatService.onEvent(QsbkApp.mContext, "live_qiushi_" + str, "click");
                StatSDK.onEvent(QsbkApp.mContext, "live_qiushi_" + str, "click");
                return;
            }
            StatService.onEvent(QsbkApp.mContext, "live_qiuyouquan_" + str, "click");
            StatSDK.onEvent(QsbkApp.mContext, "live_qiuyouquan_" + str, "click");
        }
    }

    protected Drawable a(Context context) {
        if (this.s == null) {
            this.s = TileBackground.getBackgroud(context, BgImageType.ARTICLE);
        }
        return this.s;
    }

    public void onCreate() {
        setCellView((int) R.layout.layout_article_item_type_live_recomment);
        this.a = (ViewPager) findViewById(R.id.pager);
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        int screenWidth = DeviceUtils.getScreenWidth(getContext());
        if (layoutParams != null) {
            layoutParams.width = (int) (((double) screenWidth) * 0.7d);
            layoutParams.height = layoutParams.width;
            layoutParams.setMargins((int) (((double) screenWidth) * 0.15d), 0, (int) (((double) screenWidth) * 0.15d), 0);
            this.a.setLayoutParams(layoutParams);
        }
        this.a.setPageMargin(UIHelper.dip2px(getContext(), 8.0f));
        this.a.setOffscreenPageLimit(2);
        this.a.setOnPageChangeListener(new ck(this));
        this.b = new LivePagerAdapter(this);
        this.a.setAdapter(this.b);
    }

    public LivePackage getItem() {
        return (LivePackage) super.getItem();
    }

    public void onUpdate() {
        this.e = true;
        this.b.notifyDataSetChanged();
        LivePackage item = getItem();
        int i = item.pos;
        ArrayList arrayList = item.lives;
        this.a.setCurrentItem((i % arrayList.size()) + (arrayList.size() * 10000), false);
    }

    public void onClick() {
        super.onClick();
        ArrayList arrayList = getItem().lives;
        Live live = (Live) arrayList.get(this.a.getCurrentItem() % arrayList.size());
        if (live.liveId > 0) {
            LivePullLauncher.from(getContext()).doLaunch(0, live);
        } else {
            long j;
            LivePullLauncher with = LivePullLauncher.from(getContext()).with(live.liveId + "");
            if (live.author != null) {
                j = live.author.origin;
            } else {
                j = 0;
            }
            with.withSource(j).launch(0);
        }
        a(this.c, this.d);
        SubscribeReportHelper.report(QsbkApp.mContext, getItem().hashCode());
    }
}
