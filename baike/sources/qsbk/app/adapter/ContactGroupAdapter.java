package qsbk.app.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.UIHelper;

public class ContactGroupAdapter extends Adapter<a> {
    public static final int NORMAL = 0;
    public static final int SEARCH = 1;
    ArrayList<Object> a;
    private int b = 0;

    class a extends ViewHolder {
        public View divider;
        final /* synthetic */ ContactGroupAdapter m;
        public ImageView mAvatar;
        public TextView mDescriptionTV;
        public TextView mDistanceTV;
        public TextView mLevelTV;
        public TextView mMemberNumTV;
        public TextView mNameTV;
        public TextView mOwner;
        public TextView mStatusTV;

        public a(ContactGroupAdapter contactGroupAdapter, View view) {
            this.m = contactGroupAdapter;
            super(view);
            this.mAvatar = (ImageView) view.findViewById(R.id.avatar);
            this.mNameTV = (TextView) view.findViewById(R.id.name);
            this.mOwner = (TextView) view.findViewById(R.id.owner);
            this.mLevelTV = (TextView) view.findViewById(R.id.level);
            this.mMemberNumTV = (TextView) view.findViewById(R.id.info);
            this.mDescriptionTV = (TextView) view.findViewById(R.id.description);
            this.mDistanceTV = (TextView) view.findViewById(R.id.distance);
            this.mStatusTV = (TextView) view.findViewById(R.id.status);
            this.divider = view.findViewById(R.id.divider);
        }

        public void onBind(GroupBriefInfo groupBriefInfo) {
            int i;
            int i2 = -5066062;
            int i3 = -12171438;
            this.itemView.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
            if (TextUtils.isEmpty(groupBriefInfo.icon)) {
                this.mAvatar.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(this.mAvatar, groupBriefInfo.icon);
            }
            this.mNameTV.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            this.divider.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
            this.mNameTV.setText(groupBriefInfo.name);
            this.mOwner.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_owner_bg_night : R.drawable.group_owner_bg);
            TextView textView = this.mOwner;
            if (UIHelper.isNightTheme()) {
                i = -5000269;
            } else {
                i = -1;
            }
            textView.setTextColor(i);
            this.mOwner.setVisibility(groupBriefInfo.isOwner ? 0 : 8);
            this.mLevelTV.setText(groupBriefInfo.level + "");
            textView = this.mLevelTV;
            if (UIHelper.isNightTheme()) {
                i = -5066062;
            } else {
                i = -1;
            }
            textView.setTextColor(i);
            this.mLevelTV.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_info_level_bg_night : R.drawable.group_info_level_bg);
            textView = this.mDescriptionTV;
            if (UIHelper.isNightTheme()) {
                i = -12171438;
            } else {
                i = -6908266;
            }
            textView.setTextColor(i);
            this.mDescriptionTV.setText(groupBriefInfo.description);
            textView = this.mMemberNumTV;
            if (UIHelper.isNightTheme()) {
                i = -12171438;
            } else {
                i = -6908266;
            }
            textView.setTextColor(i);
            if (this.m.b == 1) {
                this.mMemberNumTV.setText(groupBriefInfo.memberNum + "人");
                if (groupBriefInfo.distance < 1000) {
                    this.mDistanceTV.setText(groupBriefInfo.distance + "m");
                } else {
                    this.mDistanceTV.setText((groupBriefInfo.distance / 1000) + "km");
                }
                this.mDistanceTV.setVisibility(0);
                TextView textView2 = this.mDistanceTV;
                if (!UIHelper.isNightTheme()) {
                    i3 = -6908266;
                }
                textView2.setTextColor(i3);
                textView2 = this.mStatusTV;
                if (!UIHelper.isNightTheme()) {
                    i2 = -1;
                }
                textView2.setTextColor(i2);
                switch (groupBriefInfo.joinStatus) {
                    case 0:
                        this.mStatusTV.setVisibility(4);
                        return;
                    case 1:
                        this.mStatusTV.setText("已申请");
                        this.mStatusTV.setBackgroundResource(R.drawable.group_applied);
                        this.mStatusTV.setVisibility(0);
                        return;
                    case 2:
                        this.mStatusTV.setText("已加入");
                        this.mStatusTV.setBackgroundResource(R.drawable.group_joined);
                        this.mStatusTV.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }
            this.mMemberNumTV.setText(groupBriefInfo.memberNum + "人 | " + groupBriefInfo.location.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
        }
    }

    public ContactGroupAdapter(ArrayList<Object> arrayList, int i) {
        this.b = i;
        this.a = arrayList;
    }

    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_item, viewGroup, false));
    }

    public void onBindViewHolder(a aVar, int i) {
        Object obj = this.a.get(i);
        if (obj instanceof GroupBriefInfo) {
            aVar.onBind((GroupBriefInfo) obj);
        }
    }

    public int getItemCount() {
        return this.a.size();
    }
}
