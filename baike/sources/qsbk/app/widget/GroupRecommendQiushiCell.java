package qsbk.app.widget;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import java.util.Random;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.GroupRecommend.GroupItem;
import qsbk.app.model.GroupRecommend.GroupRecommendObserver;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;

public class GroupRecommendQiushiCell extends BaseCell implements GroupRecommendObserver {
    View a;
    ViewPager b;
    PagerAdapter c;
    int d;
    TextPaint e = new TextPaint();
    private int f;
    private boolean g = false;

    class a extends PagerAdapter {
        final /* synthetic */ GroupRecommendQiushiCell a;

        a(GroupRecommendQiushiCell groupRecommendQiushiCell) {
            this.a = groupRecommendQiushiCell;
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            this.a.g = false;
        }

        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public int getItemPosition(Object obj) {
            if (this.a.g) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_recommend_item, null);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.bg);
            SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) inflate.findViewById(R.id.avatar);
            TextView textView = (TextView) inflate.findViewById(R.id.group_name);
            TextView textView2 = (TextView) inflate.findViewById(R.id.content);
            Button button = (Button) inflate.findViewById(R.id.add);
            TextView textView3 = (TextView) inflate.findViewById(R.id.friend);
            GroupItem groupItem = (GroupItem) this.a.getItem().groups.get(i % this.a.getItem().groups.size());
            if (groupItem == null) {
                return inflate;
            }
            String str;
            CharSequence charSequence = "";
            String str2 = "";
            String str3 = "";
            BaseUserInfo baseUserInfo = null;
            if (!(groupItem == null || groupItem.members == null || groupItem.members.size() <= 0)) {
                baseUserInfo = (BaseUserInfo) groupItem.members.get(new Random().nextInt(groupItem.members.size()));
                charSequence = baseUserInfo == null ? "" : baseUserInfo.userName;
                str2 = baseUserInfo == null ? "" : QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
            }
            if (TextUtils.isEmpty(charSequence)) {
                textView3.setText("附近的人也在群里");
            } else {
                textView3.setText(TextUtils.ellipsize(charSequence, this.a.e, (float) this.a.d, TruncateAt.END) + "也在群里");
            }
            if (TextUtils.isEmpty(groupItem.icon)) {
                str = "";
            } else {
                str = QsbkApp.absoluteUrlOfGroupIcon(groupItem.icon);
            }
            this.a.displayAvatar(simpleDraweeView2, str2);
            this.a.displayImage(simpleDraweeView, str, TileBackground.getBackgroud(simpleDraweeView.getContext(), BgImageType.ARTICLE));
            textView2.setText(groupItem.desc);
            textView.setText(groupItem.name);
            switch (groupItem.joinStatus) {
                case 1:
                    button.setText("已申请");
                    button.setEnabled(false);
                    break;
                case 2:
                    button.setText("已加入");
                    button.setEnabled(false);
                    break;
                default:
                    button.setText("加入");
                    button.setEnabled(true);
                    break;
            }
            inflate.setOnClickListener(new bx(this, groupItem));
            simpleDraweeView2.setOnClickListener(new by(this, baseUserInfo));
            button.setOnClickListener(new bz(this, groupItem));
            viewGroup.addView(inflate);
            return inflate;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    public void onCreate() {
        setCellView((int) R.layout.cell_qiushi_group_recommend);
        this.a = findViewById(R.id.pager_container);
        this.b = (ViewPager) findViewById(R.id.pager);
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        int screenWidth = DeviceUtils.getScreenWidth(getContext());
        if (layoutParams != null) {
            layoutParams.width = (int) (((double) screenWidth) * 0.6d);
            layoutParams.height = (int) (((double) screenWidth) * 0.6d);
            layoutParams.setMargins((int) (((double) screenWidth) * 0.2d), 0, (int) (((double) screenWidth) * 0.2d), 0);
            this.b.setLayoutParams(layoutParams);
            this.f = layoutParams.width - UIHelper.dip2px(getContext(), 110.0f);
            if (this.d == 0) {
                this.d = UIHelper.dip2px(getContext(), 120.0f);
                String string = getContext().getResources().getString(R.string.in_group);
                this.e.setTextSize(UIHelper.sp2px(getContext(), 10.0f));
                this.d = Math.round(((float) this.f) - this.e.measureText(string));
            }
        }
        this.b.setPageMargin(UIHelper.dip2px(getContext(), 8.0f));
        this.c = new a(this);
        this.b.setAdapter(this.c);
        this.b.setOffscreenPageLimit(3);
        this.b.setCurrentItem(1073741823);
        this.a.setOnTouchListener(new bw(this));
        GroupRecommend.register(this);
    }

    public GroupRecommend getItem() {
        return (GroupRecommend) super.getItem();
    }

    public void onClick() {
        super.onClick();
        List list = getItem().groups;
        if (list != null && list.size() > 0) {
            GroupItem groupItem = (GroupItem) list.get(this.q);
            if (groupItem != null) {
                GroupInfoActivity.launch(getContext(), groupItem.toGroupInfo());
                SubscribeReportHelper.report(QsbkApp.mContext, getItem().hashCode());
            }
        }
    }

    public void onUpdate() {
        this.c.notifyDataSetChanged();
    }

    public void onNewGroupRecommend(GroupRecommend groupRecommend) {
        this.g = true;
    }
}
