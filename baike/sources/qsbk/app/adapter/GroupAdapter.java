package qsbk.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.UIHelper;

public class GroupAdapter extends BaseImageAdapter {
    public static final int NORMAL = 0;
    public static final int SEARCH = 1;
    private int a = 0;

    class a {
        final /* synthetic */ GroupAdapter a;
        public View divider;
        public ImageView mAvatar;
        public TextView mDescriptionTV;
        public TextView mDistanceTV;
        public TextView mLevelTV;
        public TextView mMemberNumTV;
        public TextView mNameTV;
        public TextView mOwner;
        public TextView mStatusTV;

        a(GroupAdapter groupAdapter) {
            this.a = groupAdapter;
        }
    }

    public GroupAdapter(ArrayList<Object> arrayList, Activity activity, int i) {
        super(arrayList, activity);
        this.a = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        int i2;
        int i3;
        int i4 = -5066062;
        int i5 = -12171438;
        if (view == null) {
            aVar = new a(this);
            view = this.n.inflate(R.layout.group_item, viewGroup, false);
            aVar.mAvatar = (ImageView) view.findViewById(R.id.avatar);
            aVar.mNameTV = (TextView) view.findViewById(R.id.name);
            aVar.mOwner = (TextView) view.findViewById(R.id.owner);
            aVar.mLevelTV = (TextView) view.findViewById(R.id.level);
            aVar.mMemberNumTV = (TextView) view.findViewById(R.id.info);
            aVar.mDescriptionTV = (TextView) view.findViewById(R.id.description);
            aVar.mDistanceTV = (TextView) view.findViewById(R.id.distance);
            aVar.mStatusTV = (TextView) view.findViewById(R.id.status);
            aVar.divider = view.findViewById(R.id.divider);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (UIHelper.isNightTheme()) {
            i2 = -14803421;
        } else {
            i2 = -1;
        }
        view.setBackgroundColor(i2);
        GroupBriefInfo groupBriefInfo = (GroupBriefInfo) getItem(i);
        if (TextUtils.isEmpty(groupBriefInfo.icon)) {
            aVar.mAvatar.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            b(aVar.mAvatar, groupBriefInfo.icon);
        }
        aVar.mNameTV.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
        aVar.divider.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
        aVar.mNameTV.setText(groupBriefInfo.name);
        aVar.mOwner.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_owner_bg_night : R.drawable.group_owner_bg);
        TextView textView = aVar.mOwner;
        if (UIHelper.isNightTheme()) {
            i3 = -5000269;
        } else {
            i3 = -1;
        }
        textView.setTextColor(i3);
        aVar.mOwner.setVisibility(groupBriefInfo.isOwner ? 0 : 8);
        aVar.mLevelTV.setText(groupBriefInfo.level + "");
        textView = aVar.mLevelTV;
        if (UIHelper.isNightTheme()) {
            i3 = -5066062;
        } else {
            i3 = -1;
        }
        textView.setTextColor(i3);
        aVar.mLevelTV.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_info_level_bg_night : R.drawable.group_info_level_bg);
        textView = aVar.mDescriptionTV;
        if (UIHelper.isNightTheme()) {
            i3 = -12171438;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        aVar.mDescriptionTV.setText(groupBriefInfo.description);
        textView = aVar.mMemberNumTV;
        if (UIHelper.isNightTheme()) {
            i3 = -12171438;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        if (this.a == 1) {
            aVar.mMemberNumTV.setText(groupBriefInfo.memberNum + "人");
            if (groupBriefInfo.distance < 1000) {
                aVar.mDistanceTV.setText(groupBriefInfo.distance + "m");
            } else {
                aVar.mDistanceTV.setText((groupBriefInfo.distance / 1000) + "km");
            }
            aVar.mDistanceTV.setVisibility(0);
            TextView textView2 = aVar.mDistanceTV;
            if (!UIHelper.isNightTheme()) {
                i5 = -6908266;
            }
            textView2.setTextColor(i5);
            textView2 = aVar.mStatusTV;
            if (!UIHelper.isNightTheme()) {
                i4 = -1;
            }
            textView2.setTextColor(i4);
            switch (groupBriefInfo.joinStatus) {
                case 0:
                    aVar.mStatusTV.setVisibility(4);
                    break;
                case 1:
                    aVar.mStatusTV.setText("已申请");
                    aVar.mStatusTV.setBackgroundResource(R.drawable.group_applied);
                    aVar.mStatusTV.setVisibility(0);
                    break;
                case 2:
                    aVar.mStatusTV.setText("已加入");
                    aVar.mStatusTV.setBackgroundResource(R.drawable.group_joined);
                    aVar.mStatusTV.setVisibility(0);
                    break;
            }
        }
        aVar.mMemberNumTV.setText(groupBriefInfo.memberNum + "人 | " + groupBriefInfo.location.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
        return view;
    }
}
