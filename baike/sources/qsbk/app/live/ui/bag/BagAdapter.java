package qsbk.app.live.ui.bag;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter.ViewHolder;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveBagDataRecord;

public class BagAdapter extends BaseRecyclerViewAdapter {
    private ItemClickListener c;

    public interface ItemClickListener {
        void onClick(View view);

        void onRefresh();
    }

    public BagAdapter(Context context, List<LiveBagDataRecord> list) {
        super(context, list);
    }

    public void setClickItem(ItemClickListener itemClickListener) {
        this.c = itemClickListener;
    }

    protected int b(int i) {
        if (i == 0) {
            return R.layout.item_bag;
        }
        return R.layout.item_bag_tail;
    }

    public int getItemCount() {
        return this.b.size() + 1;
    }

    protected int a(int i) {
        if (i >= this.b.size()) {
            return 1;
        }
        return 0;
    }

    protected void a(int i, ViewHolder viewHolder, int i2, Object obj) {
        LiveBagDataRecord liveBagDataRecord = (LiveBagDataRecord) obj;
        if (liveBagDataRecord.i == 0) {
            ((SimpleDraweeView) viewHolder.getView(R.id.iv_cover)).setImageResource(R.drawable.live_bag_new);
            viewHolder.setText(R.id.tv_name, "当前无特效");
            viewHolder.setText(R.id.tv_days, "商城即可购买");
            ((TextView) viewHolder.getView(R.id.tv_days)).setTextColor(Color.parseColor("#FCBC0E"));
            viewHolder.setText(R.id.tv_desc, "点击购买按钮前往商城");
            viewHolder.setText(R.id.equip, "购买");
            viewHolder.getView(R.id.equip).setOnClickListener(new b(this));
            return;
        }
        if (liveBagDataRecord.t == 0) {
            AppUtils.getInstance().getImageProvider().loadWebpImage((SimpleDraweeView) viewHolder.getView(R.id.iv_cover), liveBagDataRecord.c);
            viewHolder.setText(R.id.tv_name, liveBagDataRecord.n);
            viewHolder.setText(R.id.tv_days, "剩" + liveBagDataRecord.d + "天");
            ((TextView) viewHolder.getView(R.id.tv_days)).setTextColor(Color.parseColor("#FCBC0E"));
            viewHolder.setText(R.id.tv_desc, liveBagDataRecord.s);
        } else {
            ((SimpleDraweeView) viewHolder.getView(R.id.iv_cover)).setImageResource(R.drawable.live_bag_default);
            viewHolder.setText(R.id.tv_name, "默认进场特效");
            viewHolder.setText(R.id.tv_days, "升级即可获得");
            ((TextView) viewHolder.getView(R.id.tv_days)).setTextColor(Color.parseColor("#9B4CFF"));
            viewHolder.setText(R.id.tv_desc, "永久，根据当前等级显示");
        }
        if (liveBagDataRecord.a) {
            viewHolder.getView(R.id.equip).setBackgroundResource(R.drawable.bg_bag_not_equip);
            viewHolder.setText(R.id.equip, "已装备");
            ((TextView) viewHolder.getView(R.id.equip)).setTextColor(Color.parseColor("#606060"));
            viewHolder.getView(R.id.equip).setClickable(false);
            return;
        }
        viewHolder.getView(R.id.equip).setBackgroundResource(R.drawable.bg_bag_equip);
        viewHolder.setText(R.id.equip, "装备");
        ((TextView) viewHolder.getView(R.id.equip)).setTextColor(-1);
        viewHolder.getView(R.id.equip).setOnClickListener(new c(this, liveBagDataRecord));
    }

    private void a(long j) {
        NetRequest.getInstance().get(UrlConstants.LIVE_BAG_EQUIP, new e(this, j));
    }
}
