package qsbk.app.share.message.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.ContactListItem;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;

public class RecentChatQiuyouAdapter extends BaseAdapter {
    private Context a;
    private List<ContactListItem> b = new ArrayList();

    class a {
        final /* synthetic */ RecentChatQiuyouAdapter a;
        public ImageView mAvatarIV;
        public TextView mNameTV;

        a(RecentChatQiuyouAdapter recentChatQiuyouAdapter) {
            this.a = recentChatQiuyouAdapter;
        }
    }

    public RecentChatQiuyouAdapter(Context context) {
        this.a = context;
    }

    public RecentChatQiuyouAdapter(Context context, List<ContactListItem> list) {
        this.a = context;
        this.b = list;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void setDatas(List<ContactListItem> list) {
        this.b = list;
    }

    public void replaceItem(List<ContactListItem> list) {
        this.b.clear();
        this.b.addAll(list);
        notifyDataSetChanged();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.recent_chats_qiuyou, null);
            aVar.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
            aVar.mNameTV = (TextView) view.findViewById(R.id.name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        ContactListItem contactListItem = (ContactListItem) this.b.get(i);
        String str;
        if (contactListItem.isGroupMsg()) {
            str = contactListItem.icon;
            aVar.mNameTV.setText(contactListItem.name);
            Object obj = str;
        } else {
            str = QsbkApp.absoluteUrlOfMediumUserIcon(contactListItem.icon, contactListItem.id);
            CharSequence remark = RemarkManager.getRemark(contactListItem.id);
            String str2;
            if (TextUtils.isEmpty(remark)) {
                aVar.mNameTV.setText(contactListItem.name);
                str2 = str;
            } else {
                aVar.mNameTV.setText(remark);
                str2 = str;
            }
        }
        if (TextUtils.isEmpty(obj)) {
            aVar.mAvatarIV.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(aVar.mAvatarIV, obj);
        }
        return view;
    }
}
