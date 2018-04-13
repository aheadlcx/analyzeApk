package qsbk.app.im;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.Switch;

public class OfficialSubscribeListAdapter extends BaseAdapter {
    private static final String b = OfficialSubscribeListAdapter.class.getSimpleName();
    List<OfficialSubscribeListItem> a = new LinkedList();
    private Activity c = null;

    private static class a {
        ImageView a;
        TextView b;
        Switch c;
        View d;

        private a() {
        }
    }

    public OfficialSubscribeListAdapter(Activity activity) {
        this.c = activity;
    }

    public int getCount() {
        return this.a.size();
    }

    public OfficialSubscribeListItem getItem(int i) {
        return (OfficialSubscribeListItem) this.a.get(i);
    }

    public void checkChange(int i) {
        ((OfficialSubscribeListItem) this.a.get(i)).isSubscribe = !((OfficialSubscribeListItem) this.a.get(i)).isSubscribe;
        notifyDataSetChanged();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void clear() {
        if (this.a != null) {
            this.a.clear();
        }
    }

    public void addItem(List<OfficialSubscribeListItem> list) {
        this.a.addAll(list);
    }

    public void replaceItem(List<OfficialSubscribeListItem> list) {
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
    }

    public boolean removeItem(OfficialSubscribeListItem officialSubscribeListItem, boolean z) {
        boolean remove = this.a.remove(officialSubscribeListItem);
        if (remove && z) {
            notifyDataSetChanged();
        }
        return remove;
    }

    public OfficialSubscribeListItem removeItem(int i, boolean z) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        OfficialSubscribeListItem officialSubscribeListItem = (OfficialSubscribeListItem) this.a.remove(i);
        if (officialSubscribeListItem == null || !z) {
            return officialSubscribeListItem;
        }
        notifyDataSetChanged();
        return officialSubscribeListItem;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.official_subscribe_list_item, null);
            a aVar2 = new a();
            aVar2.a = (ImageView) view.findViewById(R.id.avatar);
            aVar2.b = (TextView) view.findViewById(R.id.name);
            aVar2.c = (Switch) view.findViewById(R.id.subscribe);
            aVar2.d = view.findViewById(R.id.mask);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setImageResource(UIHelper.getDefaultAvatar());
        OfficialSubscribeListItem item = getItem(i);
        if (!TextUtils.isEmpty(item.icon)) {
            FrescoImageloader.displayAvatar(aVar.a, QsbkApp.absoluteUrlOfMediumUserIcon(item.icon, item.id));
        }
        if (!TextUtils.isEmpty(item.name)) {
            aVar.b.setText(item.name);
        }
        aVar.c.setClickable(false);
        aVar.c.setChecked(item.isSubscribe);
        if (item.canCancel) {
            aVar.d.setBackgroundColor(this.c.getResources().getColor(R.color.transparent));
        } else {
            aVar.d.setBackgroundColor(this.c.getResources().getColor(UIHelper.isNightTheme() ? R.color.alpha_black_mask_50_percent : R.color.alpha_white_mask_50_percent));
        }
        aVar.d.setOnClickListener(new ip(this, item, i));
        return view;
    }

    private void a(String str, String str2, Map<String, Object> map, int i) {
        HttpTask httpTask = new HttpTask(str, str2, new iq(this, i));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }
}
