package qsbk.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class IMMoreAdapter extends BaseAdapter {

    class a {
        ImageView a;
        TextView b;
        final /* synthetic */ IMMoreAdapter c;

        public a(IMMoreAdapter iMMoreAdapter, View view) {
            this.c = iMMoreAdapter;
            this.a = (ImageView) view.findViewById(R.id.img);
            this.b = (TextView) view.findViewById(R.id.title);
        }
    }

    public int getCount() {
        return 2;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_im_more_item, null);
            a aVar2 = new a(this, view);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        switch (i) {
            case 0:
                aVar.a.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_more_img_dark : R.drawable.ic_more_img_light);
                aVar.b.setText("相册");
                break;
            case 1:
                aVar.a.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_more_laisee_dark : R.drawable.ic_more_laisee_light);
                aVar.b.setText("红包");
                break;
        }
        return view;
    }
}
