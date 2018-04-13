package qsbk.app.live.ui.bag;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter.ViewHolder;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMarketDataRecord;

public class MarketAdapter extends BaseRecyclerViewAdapter {
    private MarketItemClickListener c;

    public interface MarketItemClickListener {
        void onItemClick(LiveMarketDataRecord liveMarketDataRecord);
    }

    public MarketAdapter(Context context, List<LiveMarketDataRecord> list) {
        super(context, list);
    }

    public void setMarketItemClickListener(MarketItemClickListener marketItemClickListener) {
        this.c = marketItemClickListener;
    }

    protected int b(int i) {
        return R.layout.item_market;
    }

    protected void a(int i, ViewHolder viewHolder, int i2, Object obj) {
        LiveMarketDataRecord liveMarketDataRecord = (LiveMarketDataRecord) obj;
        AppUtils.getInstance().getImageProvider().loadWebpImage((SimpleDraweeView) viewHolder.getView(R.id.iv_cover), liveMarketDataRecord.c);
        viewHolder.setText(R.id.tv_name, liveMarketDataRecord.n);
        viewHolder.setText(R.id.tv_desc, liveMarketDataRecord.d);
        if (liveMarketDataRecord.o == 1) {
            viewHolder.setText(R.id.tv_price, liveMarketDataRecord.m + "钻石起");
            viewHolder.setText(R.id.tv_buy, this.a.getString(R.string.buy));
            viewHolder.getView(R.id.tv_buy).setBackgroundResource(R.drawable.bg_bag_equip);
            viewHolder.getView(R.id.tv_buy).setOnClickListener(new k(this, obj));
            ((TextView) viewHolder.getView(R.id.tv_price)).setTextColor(Color.parseColor("#FCBC0E"));
        } else if (liveMarketDataRecord.o == 3) {
            viewHolder.setText(R.id.tv_price, liveMarketDataRecord.m + "钻石起");
            viewHolder.setText(R.id.tv_buy, this.a.getString(R.string.renew));
            viewHolder.getView(R.id.tv_buy).setBackgroundResource(R.drawable.bg_bag_renew);
            viewHolder.getView(R.id.tv_buy).setOnClickListener(new l(this, obj));
            ((TextView) viewHolder.getView(R.id.tv_price)).setTextColor(Color.parseColor("#FCBC0E"));
        } else if (liveMarketDataRecord.o == 2) {
            viewHolder.setText(R.id.tv_price, R.string.bag_expect);
            viewHolder.setText(R.id.tv_buy, this.a.getString(R.string.limit_open));
            viewHolder.getView(R.id.tv_buy).setBackgroundResource(R.drawable.bg_bag_limit_open);
            viewHolder.getView(R.id.tv_buy).setClickable(false);
            ((TextView) viewHolder.getView(R.id.tv_price)).setTextColor(Color.parseColor("#9B4CFF"));
        } else {
            viewHolder.setText(R.id.tv_price, R.string.bag_expect);
            viewHolder.setText(R.id.tv_buy, this.a.getString(R.string.not_open));
            viewHolder.getView(R.id.tv_buy).setBackgroundResource(R.drawable.bg_bag_not_open);
            viewHolder.getView(R.id.tv_buy).setClickable(false);
            ((TextView) viewHolder.getView(R.id.tv_price)).setTextColor(Color.parseColor("#9B4CFF"));
        }
        viewHolder.getView(R.id.iv_cover).setOnClickListener(new m(this, liveMarketDataRecord));
    }
}
