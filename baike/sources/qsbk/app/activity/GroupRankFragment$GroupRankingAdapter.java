package qsbk.app.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.UIHelper;

public class GroupRankFragment$GroupRankingAdapter extends BaseImageAdapter {
    final /* synthetic */ GroupRankFragment a;

    public GroupRankFragment$GroupRankingAdapter(GroupRankFragment groupRankFragment, ArrayList<Object> arrayList) {
        this.a = groupRankFragment;
        super(arrayList, groupRankFragment.getActivity());
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        GroupRankFragment$a groupRankFragment$a;
        int i2;
        int i3;
        TextView textView;
        int i4 = -1;
        int i5 = -11215958;
        int i6 = -12171438;
        if (view == null) {
            groupRankFragment$a = new GroupRankFragment$a(this.a);
            view = this.n.inflate(R.layout.group_ranking_item, viewGroup, false);
            groupRankFragment$a.rankingNum = (TextView) view.findViewById(R.id.ranking_num);
            groupRankFragment$a.avatar = (ImageView) view.findViewById(R.id.avatar);
            groupRankFragment$a.name = (TextView) view.findViewById(R.id.name);
            groupRankFragment$a.mOwner = view.findViewById(R.id.owner);
            groupRankFragment$a.level = (TextView) view.findViewById(R.id.level);
            groupRankFragment$a.info = (TextView) view.findViewById(R.id.info);
            groupRankFragment$a.description = (TextView) view.findViewById(R.id.description);
            groupRankFragment$a.divider = view.findViewById(R.id.divider);
            view.setTag(groupRankFragment$a);
        } else {
            groupRankFragment$a = (GroupRankFragment$a) view.getTag();
        }
        if (UIHelper.isNightTheme()) {
            i2 = -14803421;
        } else {
            i2 = -1;
        }
        view.setBackgroundColor(i2);
        groupRankFragment$a.name.setTextColor(UIHelper.isNightTheme() ? -9802637 : -12894910);
        groupRankFragment$a.divider.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
        GroupBriefInfo groupBriefInfo = (GroupBriefInfo) getItem(i);
        groupRankFragment$a.mOwner.setVisibility(groupBriefInfo.isOwner ? 0 : 8);
        int i7 = groupBriefInfo.rank;
        if (i7 == GroupRankFragment.e(this.a)) {
            TextView textView2 = groupRankFragment$a.name;
            if (UIHelper.isNightTheme()) {
                i3 = -5486263;
            } else {
                i3 = -11215958;
            }
            textView2.setTextColor(i3);
            textView = groupRankFragment$a.rankingNum;
            if (UIHelper.isNightTheme()) {
                i5 = -12171438;
            }
            textView.setTextColor(i5);
        } else {
            groupRankFragment$a.rankingNum.setTextColor(UIHelper.isNightTheme() ? -12171438 : -10263970);
            groupRankFragment$a.name.setTextColor(UIHelper.isNightTheme() ? -12171438 : Color.rgb(59, 61, 66));
        }
        if (i7 == 1) {
            groupRankFragment$a.rankingNum.setText("");
            TextView textView3 = groupRankFragment$a.rankingNum;
            if (UIHelper.isNightTheme()) {
                i3 = R.drawable.group_levle_gold_night;
            } else {
                i3 = R.drawable.group_level_gold;
            }
            textView3.setBackgroundResource(i3);
        } else if (i7 == 2) {
            groupRankFragment$a.rankingNum.setText("");
            groupRankFragment$a.rankingNum.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_silver_night : R.drawable.group_level_silver);
        } else if (i7 == 3) {
            groupRankFragment$a.rankingNum.setText("");
            groupRankFragment$a.rankingNum.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_copper_night : R.drawable.group_level_copper);
        } else {
            groupRankFragment$a.rankingNum.setBackgroundColor(0);
            groupRankFragment$a.rankingNum.setText(i7 + "");
        }
        groupRankFragment$a.name.setText(groupBriefInfo.name);
        if (TextUtils.isEmpty(groupBriefInfo.icon)) {
            groupRankFragment$a.avatar.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            b(groupRankFragment$a.avatar, groupBriefInfo.icon);
        }
        groupRankFragment$a.level.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_info_level_bg_night : R.drawable.group_info_level_bg);
        textView = groupRankFragment$a.level;
        if (UIHelper.isNightTheme()) {
            i4 = -5066062;
        }
        textView.setTextColor(i4);
        groupRankFragment$a.level.setText(groupBriefInfo.level + "");
        textView = groupRankFragment$a.info;
        if (UIHelper.isNightTheme()) {
            i4 = -12171438;
        } else {
            i4 = -6908266;
        }
        textView.setTextColor(i4);
        TextView textView4 = groupRankFragment$a.description;
        if (!UIHelper.isNightTheme()) {
            i6 = -6908266;
        }
        textView4.setTextColor(i6);
        groupRankFragment$a.info.setText(groupBriefInfo.memberNum + "人" + " | " + groupBriefInfo.location.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
        groupRankFragment$a.description.setText("周活跃人次 : " + groupBriefInfo.active);
        return view;
    }
}
