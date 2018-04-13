package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SmileyVo;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.SmilyEncUtils;
import cn.v6.sixrooms.utils.SmilyEncUtils.ImageLoadingListener;
import java.util.List;

public class ExpressionItemAdapter extends BaseAdapter {
    public static final int ACTION_CLICK = 2;
    private Context a;
    private List<SmileyVo> b = null;
    private int c;
    private ImageLoadingListener d = new d(this);
    private DeleteSmileyListener e;

    public interface DeleteSmileyListener {
        void onTouchAction(int i);
    }

    static class a {
        ImageView a;
        RelativeLayout b;

        a() {
        }
    }

    public ExpressionItemAdapter(Context context, List<SmileyVo> list, int i) {
        this.a = context;
        this.b = list;
        this.c = i;
    }

    public int getCount() {
        return this.b.size();
    }

    public SmileyVo getItem(int i) {
        return (SmileyVo) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        SmileyVo item = getItem(i);
        if (view == null || !(view instanceof LinearLayout)) {
            switch (this.c) {
                case 0:
                    view = View.inflate(this.a, R.layout.phone_expression_page_item, null);
                    break;
                case 1:
                    view = View.inflate(this.a, R.layout.phone_expression_secondpage_item, null);
                    break;
            }
            a aVar2 = new a();
            aVar2.a = (ImageView) view.findViewById(R.id.imgage);
            aVar2.b = (RelativeLayout) view.findViewById(R.id.ll_item_bg);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (this.c == 0 && i == getCount() - 1) {
            aVar.b.setBackgroundColor(this.a.getResources().getColor(R.color.transparents));
            aVar.b.setOnTouchListener(new b(this));
            aVar.b.setOnClickListener(new c(this));
            aVar.a.setImageResource(item.getResID());
        } else {
            String fileName = item.getFileName();
            if (fileName != null) {
                if (DisPlayUtil.getWidth(V6Coop.getInstance().getContext()) == 1080) {
                    aVar.a.setImageBitmap(SmilyEncUtils.getInstance().getSmilyByFileName(fileName, 1.6f));
                } else if (DisPlayUtil.getWidth(V6Coop.getInstance().getContext()) < 720) {
                    aVar.a.setImageBitmap(SmilyEncUtils.getInstance().getSmilyByFileName(fileName, 0.8f));
                } else {
                    aVar.a.setImageBitmap(SmilyEncUtils.getInstance().getSmilyByFileName(fileName));
                }
            }
        }
        return view;
    }

    public void setDeleteSmileyListener(DeleteSmileyListener deleteSmileyListener) {
        this.e = deleteSmileyListener;
    }
}
