package qsbk.app.im;

import android.app.Activity;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.JoinedGroupGetter;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;

public class ContactListAdapter extends BaseAdapter {
    public static final String BAOJIE_ID = "22336773";
    private static final String b = ContactListAdapter.class.getSimpleName();
    List<ContactListItem> a = new LinkedList();
    private Activity c = null;
    private String d = null;
    private int e;

    private static class a {
        SimpleDraweeView a;
        TextView b;
        TextView c;
        TextView d;
        View e;
        ImageView f;
        TextView g;
        ImageView h;

        public a(View view) {
            this.c = (TextView) view.findViewById(R.id.lastMessage);
            this.a = (SimpleDraweeView) view.findViewById(R.id.userhead);
            this.b = (TextView) view.findViewById(R.id.userName);
            this.d = (TextView) view.findViewById(R.id.unread);
            this.e = view.findViewById(R.id.red_point);
            this.f = (ImageView) view.findViewById(R.id.stateImage);
            this.g = (TextView) view.findViewById(R.id.lastMessageDate);
            this.h = (ImageView) view.findViewById(R.id.certification);
        }

        static a a(View view) {
            Object tag = view.getTag();
            if (tag == null) {
                tag = new a(view);
                view.setTag(tag);
            }
            return (a) tag;
        }
    }

    public ContactListAdapter(Activity activity, String str) {
        this.c = activity;
        this.d = str;
        this.e = activity.getResources().getDimensionPixelSize(R.dimen.im_contact_list_ic_size);
    }

    public int getCount() {
        return this.a.size();
    }

    public ContactListItem getItem(int i) {
        return (ContactListItem) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void clear() {
        if (this.a != null) {
            this.a.clear();
        }
    }

    public void addItem(List<ContactListItem> list) {
        this.a.addAll(list);
    }

    public void replaceItem(List<ContactListItem> list) {
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
    }

    public void setAllUnreadToZero() {
        for (int i = 0; i < this.a.size(); i++) {
            ((ContactListItem) this.a.get(i)).unreadCount = 0;
        }
    }

    public boolean removeItem(ContactListItem contactListItem, boolean z) {
        boolean remove = this.a.remove(contactListItem);
        if (remove && z) {
            notifyDataSetChanged();
        }
        return remove;
    }

    public ContactListItem removeItem(int i, boolean z) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        ContactListItem contactListItem = (ContactListItem) this.a.remove(i);
        if (contactListItem == null || !z) {
            return contactListItem;
        }
        notifyDataSetChanged();
        return contactListItem;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CharSequence charSequence;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.im_contact_list_item, viewGroup, false);
        }
        a a = a.a(view);
        ContactListItem item = getItem(i);
        if (item.type == 1 || (item.isUser() && TextUtils.equals(item.id, BAOJIE_ID))) {
            a.h.setVisibility(0);
        } else {
            a.h.setVisibility(8);
        }
        boolean isOpen = GroupMsgUtils.isOpen(item.id, true);
        if (item.mLastContent != null) {
            charSequence = item.mLastContent;
            if (item.mimeType == 100) {
                a.c.setText(charSequence);
                if (item.type == 3) {
                    a(a.f, 6, item.inout);
                } else {
                    a(a.f, 6, item.inout);
                }
            } else {
                if (item.isGroupMsg()) {
                    Object obj = item.lastMsgFromId;
                    Object obj2 = item.lastMsgFromName;
                    if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2))) {
                        obj = RemarkManager.getRemark(obj);
                        if (!TextUtils.isEmpty(obj) && item.isGroupMsg()) {
                            charSequence = RemarkManager.replaceFirst(charSequence, obj2, obj);
                        }
                    }
                }
                if (item.atMsgId != 0) {
                    String str = UIHelper.isNightTheme() ? "#41426f" : "#ff6000";
                    String str2 = "[有人@我]";
                    if (item.atType == 1) {
                        str2 = "[有全体消息]";
                    }
                    a.c.setText(Html.fromHtml("<font color=" + str + ">" + str2 + "</font>" + charSequence));
                } else if (isOpen || item.unreadCount <= 1) {
                    a.c.setText(charSequence);
                } else {
                    a.c.setText(("[" + (item.unreadCount >= 100 ? "99+" : Integer.valueOf(item.unreadCount)) + "条]") + charSequence);
                }
                if (item.type == 3) {
                    a(a.f, -1, 1);
                } else {
                    a(a.f, item.status, item.inout);
                }
            }
        } else {
            a.c.setText("");
            a(a.f, -1, 1);
        }
        if (item.isGroupMsg() && TextUtils.isEmpty(item.name)) {
            try {
                JoinedGroupGetter.getJoinedGroup(Integer.valueOf(item.id).intValue(), new bn(this, item, a));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (item.isUser()) {
            charSequence = RemarkManager.getRemark(item.id);
            if (TextUtils.isEmpty(charSequence)) {
                a.b.setText(item.name);
            } else {
                a.b.setText(charSequence);
            }
        } else {
            a.b.setText(item.name);
        }
        if (item.unreadCount == 0) {
            a.d.setVisibility(4);
            a.e.setVisibility(4);
        } else if (isOpen) {
            a.e.setVisibility(4);
            a.d.setVisibility(0);
            if (item.unreadCount <= 99) {
                a.d.setText(item.unreadCount + "");
            } else {
                a.d.setText("99+");
            }
        } else {
            a.e.setVisibility(0);
            a.d.setVisibility(4);
        }
        a.g.setText(item.getFormatTime());
        a(a.a, item.icon, item.id);
        return view;
    }

    private void a(ImageView imageView, String str, String str2) {
        if (str == null || str.indexOf("http://") == -1) {
            str = QsbkApp.absoluteUrlOfMediumUserIcon(str, str2);
        } else {
            int indexOf = str.indexOf("?");
            if (indexOf != -1) {
                str = str.substring(0, indexOf) + String.format("?imageView2/2/w/%s/q/80/format/webp", new Object[]{Integer.valueOf(this.e)});
            }
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(imageView, str);
        }
    }

    public void onMsgStateChange(long j, int i) {
        Object obj;
        for (ContactListItem contactListItem : this.a) {
            if (contactListItem.msgId == j && contactListItem.status != i) {
                contactListItem.status = i;
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            notifyDataSetChanged();
        }
    }

    private void a(ImageView imageView, int i, int i2) {
        if (i2 == 2) {
            imageView.setVisibility(0);
            int sendStatusRes = UIHelper.getSendStatusRes(i);
            if (sendStatusRes <= 0) {
                imageView.setVisibility(8);
                return;
            } else {
                imageView.setImageResource(sendStatusRes);
                return;
            }
        }
        imageView.setVisibility(8);
    }
}
