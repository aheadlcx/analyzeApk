package qsbk.app.core.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.R;
import qsbk.app.core.model.ShareItem;

public class ShareAdapter extends BaseAdapter {
    private final OnShareItemClickListener a;
    private Context b;
    private boolean c;
    private List<ShareItem> d;

    public interface OnShareItemClickListener {
        void onShareItemClicked(int i);
    }

    public ShareAdapter(Context context, List<ShareItem> list, boolean z, OnShareItemClickListener onShareItemClickListener) {
        this.b = context;
        this.d = list;
        this.c = z;
        this.a = onShareItemClickListener;
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        return this.d.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.item_share, null);
        }
        ShareItem shareItem = (ShareItem) this.d.get(i);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(shareItem.title);
        textView.setVisibility(this.c ? 0 : 8);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setImageResource(shareItem.iconResId);
        imageView.setSelected(shareItem.selected);
        view.setOnClickListener(new a(this, i));
        return view;
    }
}
