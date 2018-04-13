package qsbk.app.live.adapter;

import android.content.Context;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter.ViewHolder;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

public class LiveLevelBoxAdapter extends BaseRecyclerViewAdapter {
    public LiveLevelBoxAdapter(Context context, List<GiftData> list) {
        super(context, list);
    }

    protected int a(int i) {
        return 0;
    }

    protected int b(int i) {
        return R.layout.item_level_gift_box_gift;
    }

    protected void a(int i, ViewHolder viewHolder, int i2, Object obj) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) viewHolder.getView(R.id.level_gift_icon);
        ((TextView) viewHolder.getView(R.id.level_gift_num)).setText(((GiftData) obj).gn);
        if (i2 == 0) {
            simpleDraweeView.setImageResource(R.drawable.level_gift_heart);
        } else if (i2 == 1) {
            simpleDraweeView.setImageResource(R.drawable.level_gift_diamond);
        } else {
            AppUtils.getInstance().getImageProvider().loadGift(simpleDraweeView, ((GiftData) obj).ig);
        }
    }
}
