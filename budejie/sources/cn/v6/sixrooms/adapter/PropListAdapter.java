package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.PropBean;
import cn.v6.sixrooms.engine.PropActionEngine;
import cn.v6.sixrooms.engine.PropActionEngine.CallBack;
import cn.v6.sixrooms.widgets.phone.switchbutton.SwitchButton;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;
import java.util.ArrayList;
import java.util.List;

public class PropListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<PropBean> a = new ArrayList();
    private Context b;
    private LayoutInflater c;
    private PropActionEngine d;
    private CallBack e;

    public PropListAdapter(Context context, CallBack callBack) {
        this.b = context;
        this.e = callBack;
        this.c = (LayoutInflater) this.b.getSystemService("layout_inflater");
        this.d = new PropActionEngine(this.e);
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PropListAdapter$b propListAdapter$b;
        PropBean propBean = (PropBean) this.a.get(i);
        int typeTag = propBean.getTypeTag();
        if (view == null) {
            PropListAdapter$b propListAdapter$b2 = new PropListAdapter$b();
            view = this.c.inflate(R.layout.list_item_get_myxprop, null);
            propListAdapter$b2.b = (SwitchButton) view.findViewById(R.id.prop_item_action);
            propListAdapter$b2.a = (ImageView) view.findViewById(R.id.prop_item_draw);
            propListAdapter$b2.c = (TextView) view.findViewById(R.id.prop_item_name);
            propListAdapter$b2.d = (TextView) view.findViewById(R.id.prop_item_etm);
            propListAdapter$b2.e = (LinearLayout) view.findViewById(R.id.prop_item_listitem_area);
            propListAdapter$b2.f = (TextView) view.findViewById(R.id.prop_item_listitem_alias);
            propListAdapter$b2.g = (TextView) view.findViewById(R.id.prop_item_listitem_type);
            propListAdapter$b2.h = (ImageView) view.findViewById(R.id.prop_item_arrow);
            view.setTag(propListAdapter$b2);
            propListAdapter$b = propListAdapter$b2;
        } else {
            propListAdapter$b = (PropListAdapter$b) view.getTag();
        }
        propListAdapter$b.a.setImageResource(propBean.getPropImgId());
        if (typeTag == 3 || typeTag == 6 || typeTag == 4) {
            propListAdapter$b.e.setVisibility(0);
            propListAdapter$b.h.setVisibility(0);
            propListAdapter$b.f.setText(propBean.getGuard_alias());
            propListAdapter$b.d.setText("有效期至 " + propBean.getGuard_etm());
            if (typeTag == 3) {
                propListAdapter$b.g.setText("守护对象");
            } else if (typeTag == 6) {
                propListAdapter$b.g.setText("抢星对象");
            } else if (typeTag == 4) {
                propListAdapter$b.g.setText("管理对象");
            }
        } else {
            propListAdapter$b.e.setVisibility(8);
            propListAdapter$b.h.setVisibility(8);
            propListAdapter$b.d.setText("有效期至 " + propBean.getEtm());
        }
        propListAdapter$b.c.setText(propBean.getTitle());
        if (typeTag == 2) {
            propListAdapter$b.b.setVisibility(0);
            CharSequence state = propBean.getState();
            if (!TextUtils.isEmpty(state)) {
                if ("1".equals(state)) {
                    propListAdapter$b.b.setChecked(true, false);
                } else if ("0".equals(state)) {
                    propListAdapter$b.b.setChecked(false, false);
                }
            }
            propListAdapter$b.b.setOnCheckedChangeListener(new f(this, propBean));
        } else {
            propListAdapter$b.b.setVisibility(8);
        }
        return view;
    }

    public long getHeaderId(int i) {
        return (long) ((PropBean) this.a.get(i)).getTag().charAt(0);
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        PropListAdapter$a propListAdapter$a;
        if (view == null) {
            PropListAdapter$a propListAdapter$a2 = new PropListAdapter$a();
            view = this.c.inflate(R.layout.prop_list_header, null);
            propListAdapter$a2.a = (TextView) view.findViewById(R.id.tv_gift_tag);
            view.setTag(propListAdapter$a2);
            propListAdapter$a = propListAdapter$a2;
        } else {
            propListAdapter$a = (PropListAdapter$a) view.getTag();
        }
        propListAdapter$a.a.setText(((PropBean) this.a.get(i)).getTag());
        return view;
    }

    public void setDataChanged(List<PropBean> list) {
        if (list != null && list.size() != 0) {
            this.a.clear();
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }
}
