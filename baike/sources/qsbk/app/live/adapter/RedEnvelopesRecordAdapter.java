package qsbk.app.live.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter.ViewHolder;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveRedEnvelopesRecord;

public class RedEnvelopesRecordAdapter extends BaseRecyclerViewAdapter {
    public RedEnvelopesRecordAdapter(Context context, List<LiveRedEnvelopesRecord> list) {
        super(context, list);
    }

    protected int b(int i) {
        return R.layout.live_red_envelopes_record_item;
    }

    protected void a(int i, ViewHolder viewHolder, int i2, Object obj) {
        LiveRedEnvelopesRecord liveRedEnvelopesRecord = (LiveRedEnvelopesRecord) obj;
        ((TextView) viewHolder.getView(R.id.tv_diamond)).setText(String.valueOf(liveRedEnvelopesRecord.coin));
        ((TextView) viewHolder.getView(R.id.tv_name)).setText(liveRedEnvelopesRecord.user.n);
        AppUtils.getInstance().getImageProvider().loadAvatar((ImageView) viewHolder.getView(R.id.iv_avatar), liveRedEnvelopesRecord.user.av);
    }
}
