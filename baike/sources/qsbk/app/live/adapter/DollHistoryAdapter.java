package qsbk.app.live.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.DateUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveDollHistoryData;

public class DollHistoryAdapter extends Adapter<ViewHolder> {
    private Activity a;
    private ArrayList<LiveDollHistoryData> b;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public SimpleDraweeView avatar;
        public TextView name;
        public TextView time;

        public ViewHolder(View view) {
            super(view);
            this.avatar = (SimpleDraweeView) view.findViewById(R.id.avatar);
            this.name = (TextView) view.findViewById(R.id.name);
            this.time = (TextView) view.findViewById(R.id.time);
        }
    }

    public DollHistoryAdapter(Activity activity, ArrayList<LiveDollHistoryData> arrayList) {
        this.a = activity;
        this.b = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.a).inflate(R.layout.live_doll_history_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        LiveDollHistoryData liveDollHistoryData = (LiveDollHistoryData) this.b.get(i);
        if (liveDollHistoryData != null) {
            AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder.avatar, liveDollHistoryData.Avatar);
            viewHolder.name.setText(liveDollHistoryData.UserName);
            viewHolder.time.setText(DateUtil.getAccuracyTimePostStr(liveDollHistoryData.CreateAtTs));
            viewHolder.itemView.setOnClickListener(new e(this, liveDollHistoryData));
        }
    }

    public int getItemCount() {
        return this.b != null ? this.b.size() : 0;
    }
}
