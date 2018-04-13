package qsbk.app.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.fragments.CheckInListFragment.CheckInInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;

public class CheckInTopAdapter extends BaseImageAdapter {

    class a extends BaseCell {
        final /* synthetic */ CheckInTopAdapter a;
        private View b;
        public TextView mAgeView;
        public ImageView mAvatarView;
        public View mDivider;
        public View mGenderAndAgeView;
        public ImageView mGenderView;
        public TextView mNameView;
        public ImageView mRankImageView;
        public TextView mRankView;
        public TextView mSequenceView;
        public TextView mStatusView;

        a(CheckInTopAdapter checkInTopAdapter) {
            this.a = checkInTopAdapter;
        }

        public void onCreate() {
            setCellView((int) R.layout.layout_item_check_in_top);
            this.b = findViewById(R.id.content_container);
            this.mRankView = (TextView) findViewById(R.id.ranking_num);
            this.mRankImageView = (ImageView) findViewById(R.id.ranking_img);
            this.mAvatarView = (ImageView) findViewById(R.id.avatar);
            this.mNameView = (TextView) findViewById(R.id.name);
            this.mGenderAndAgeView = findViewById(R.id.gender_age);
            this.mGenderView = (ImageView) findViewById(R.id.gender);
            this.mAgeView = (TextView) findViewById(R.id.age);
            this.mStatusView = (TextView) findViewById(R.id.status);
            this.mSequenceView = (TextView) findViewById(R.id.sequence);
            this.mDivider = findViewById(R.id.divider);
        }

        public void onUpdate() {
            CheckInInfo checkInInfo = (CheckInInfo) getItem();
            this.mRankImageView.setVisibility(0);
            if (checkInInfo.rank == 1) {
                this.mRankView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                this.mRankImageView.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_levle_gold_night : R.drawable.group_level_gold);
                this.mRankView.setVisibility(0);
            } else if (checkInInfo.rank == 2) {
                this.mRankView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                this.mRankImageView.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_levle_silver_night : R.drawable.group_level_silver);
                this.mRankView.setVisibility(0);
            } else if (checkInInfo.rank == 3) {
                this.mRankView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                this.mRankImageView.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_levle_copper_night : R.drawable.group_level_copper);
                this.mRankView.setVisibility(0);
            } else if (checkInInfo.rank > 3) {
                this.mRankImageView.setVisibility(4);
                this.mRankView.setBackgroundResource(R.drawable.topic_rank_default_bg);
                this.mRankView.setText(String.format("%d", new Object[]{Integer.valueOf(checkInInfo.rank)}));
                this.mRankView.setVisibility(0);
            } else {
                this.mRankImageView.setVisibility(4);
                this.mRankView.setText("未上榜");
            }
            this.b.setOnClickListener(new ag(this, checkInInfo));
            this.mNameView.setText(checkInInfo.user.userName);
            this.mAgeView.setText(checkInInfo.user.age + "");
            if (checkInInfo.user.gender.equals("F")) {
                this.mGenderAndAgeView.setVisibility(0);
                if (UIHelper.isNightTheme()) {
                    this.mGenderView.setImageResource(R.drawable.pinfo_female_dark);
                    this.mAgeView.setTextColor(this.a.k.getResources().getColor(R.color.age_female));
                } else {
                    this.mGenderAndAgeView.setBackgroundResource(R.drawable.pinfo_female_bg);
                    this.mGenderView.setImageResource(R.drawable.pinfo_female);
                }
            } else if (checkInInfo.user.gender.equals("M")) {
                this.mGenderAndAgeView.setVisibility(0);
                if (UIHelper.isNightTheme()) {
                    this.mGenderView.setImageResource(R.drawable.pinfo_male_dark);
                    this.mAgeView.setTextColor(this.a.k.getResources().getColor(R.color.age_male));
                } else {
                    this.mGenderAndAgeView.setBackgroundResource(R.drawable.pinfo_man_bg);
                    this.mGenderView.setImageResource(R.drawable.pinfo_male);
                }
            } else {
                this.mGenderAndAgeView.setVisibility(4);
            }
            if (checkInInfo.isSigned) {
                this.mStatusView.setText("已签到 | " + checkInInfo.signDate);
            } else {
                this.mStatusView.setText("未签到");
            }
            this.mSequenceView.setText("连续签到" + checkInInfo.days + "天");
            FrescoImageloader.displayAvatar(this.mAvatarView, QsbkApp.absoluteUrlOfMediumUserIcon(checkInInfo.user.userIcon, checkInInfo.user.userId));
            LayoutParams layoutParams;
            if (!Relationship.MYSELF.equals(checkInInfo.user.relationship) || this.q > 0) {
                layoutParams = this.mDivider.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, UIHelper.dip2px(this.a.k, 1.0f));
                } else {
                    layoutParams.height = UIHelper.dip2px(this.a.k, 1.0f);
                }
                this.mDivider.setLayoutParams(layoutParams);
                return;
            }
            layoutParams = this.mDivider.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, UIHelper.dip2px(this.a.k, 8.0f));
            } else {
                layoutParams.height = UIHelper.dip2px(this.a.k, 8.0f);
            }
            this.mDivider.setLayoutParams(layoutParams);
        }
    }

    public CheckInTopAdapter(ArrayList arrayList, FragmentActivity fragmentActivity) {
        super(arrayList, fragmentActivity);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            aVar.performCreate(i, viewGroup, null);
            view = aVar.getCellView();
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.performUpdate(i, viewGroup, getItem(i));
        return view;
    }
}
