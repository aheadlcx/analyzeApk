package cn.v6.sixrooms.hall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import java.util.List;

final class LocationAdapter extends Adapter<ViewHolder> {
    private Context a;
    private List<ProvinceNumBean> b;
    private a c;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements OnClickListener {
        final /* synthetic */ LocationAdapter a;
        private TextView b;
        private ImageView c;
        private RelativeLayout d;

        public ViewHolder(LocationAdapter locationAdapter, View view) {
            this.a = locationAdapter;
            super(view);
            this.b = (TextView) view.findViewById(R.id.locationTv);
            this.c = (ImageView) view.findViewById(R.id.locationIv);
            this.d = (RelativeLayout) view.findViewById(R.id.locationRl);
            this.d.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (view.getId() == R.id.locationRl && this.a.c != null) {
                ProvinceNumBean provinceNumBean = (ProvinceNumBean) this.d.getTag();
                if (provinceNumBean != null) {
                    a a = this.a.c;
                    getLayoutPosition();
                    a.a(provinceNumBean);
                }
            }
        }
    }

    interface a {
        void a(@NonNull ProvinceNumBean provinceNumBean);
    }

    public final /* synthetic */ void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        ProvinceNumBean provinceNumBean = (ProvinceNumBean) this.b.get(i);
        viewHolder2.b.setText(provinceNumBean.getTitle());
        viewHolder2.c.setSelected(provinceNumBean.isSelect());
        viewHolder2.d.setTag(provinceNumBean);
    }

    LocationAdapter(@NonNull Context context, List<ProvinceNumBean> list, a aVar) {
        this.a = context;
        this.b = list;
        this.c = aVar;
    }

    public final int getItemCount() {
        return this.b == null ? 0 : this.b.size();
    }

    public final /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(this.a).inflate(R.layout.phone_item_location, viewGroup, false));
    }
}
