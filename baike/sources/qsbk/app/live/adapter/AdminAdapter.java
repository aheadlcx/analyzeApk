package qsbk.app.live.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveAdmin;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.live.widget.AdminListDialog;

public class AdminAdapter extends Adapter<ViewHolder> {
    private AdminListDialog a;
    private Context b;
    private List<LiveAdmin> c;
    private long d;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ImageView ivAvatar;
        public TextView tvCancelAdmin;
        public TextView tvLv;
        public TextView tvName;

        public ViewHolder(View view) {
            super(view);
            this.ivAvatar = (ImageView) view.findViewById(R.id.avatar);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvLv = (TextView) view.findViewById(R.id.tv_lv);
            this.tvCancelAdmin = (TextView) view.findViewById(R.id.tv_cancel_admin);
        }
    }

    public AdminAdapter(AdminListDialog adminListDialog, Activity activity, List<LiveAdmin> list, long j) {
        this.a = adminListDialog;
        this.b = activity;
        this.c = list;
        this.d = j;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.live_admin_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        LiveAdmin liveAdmin = (LiveAdmin) this.c.get(i);
        AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder.ivAvatar, liveAdmin.a);
        viewHolder.tvName.setText(liveAdmin.n);
        LevelHelper.setLevelText(viewHolder.tvLv, liveAdmin.l);
        viewHolder.tvCancelAdmin.setOnClickListener(new a(this, liveAdmin));
    }

    private void a(LiveAdmin liveAdmin) {
        NetRequest.getInstance().post(UrlConstants.LIVE_ADMIN_CANCEL, new c(this, liveAdmin));
    }

    public int getItemCount() {
        return this.c != null ? this.c.size() : 0;
    }
}
