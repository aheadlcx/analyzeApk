package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class LiveRecommendAdapter extends Adapter<ViewHolder> {
    private List<CommonVideo> a = new ArrayList();
    private Context b;
    private final int c;
    private final int d;
    private float e;
    private OnItemClickListener f;

    public static class ItemLiveViewHolder extends ViewHolder {
        public SimpleDraweeView mImage;
        public TextView mNumTv;
        public TextView mUserName;

        public ItemLiveViewHolder(View view) {
            super(view);
            this.mImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
            this.mUserName = (TextView) view.findViewById(R.id.tv_username);
            this.mNumTv = (TextView) view.findViewById(R.id.tv_num);
        }
    }

    public LiveRecommendAdapter(Context context, List<CommonVideo> list) {
        this.b = context;
        this.a = list;
        this.c = WindowUtils.getScreenWidth() / 2;
        this.e = 1.3333334f;
        this.d = (int) (((float) this.c) * this.e);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemLiveViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_live_recommend_video, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CommonVideo commonVideo = (CommonVideo) this.a.get(i);
        ItemLiveViewHolder itemLiveViewHolder = (ItemLiveViewHolder) viewHolder;
        itemLiveViewHolder.mImage.setAspectRatio(0.9f);
        if (TextUtils.isEmpty(commonVideo.getThumbUrl())) {
            itemLiveViewHolder.itemView.setVisibility(4);
            return;
        }
        itemLiveViewHolder.itemView.setVisibility(0);
        itemLiveViewHolder.mNumTv.setText(this.b.getString(R.string.num_people, new Object[]{commonVideo.getVisitorsCount()}));
        AppUtils.getInstance().getImageProvider().loadImage(itemLiveViewHolder.mImage, commonVideo.getThumbUrl());
        if (commonVideo.author != null) {
            itemLiveViewHolder.mUserName.setText(commonVideo.author.name);
        }
        itemLiveViewHolder.itemView.setOnClickListener(new x(this, i));
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f = onItemClickListener;
    }
}
