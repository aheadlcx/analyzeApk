package qsbk.app.live.debug;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class LiveDebugListAdapter extends Adapter<ViewHolder> {
    private final int a = (WindowUtils.getScreenWidth() / 2);
    private final int b = ((int) (((float) this.a) / 0.75f));
    private List<CommonVideo> c;
    private Context d;

    public static class ItemLiveViewHolder extends ViewHolder {
        public ImageView ivGaming;
        public TextView mContent;
        public SimpleDraweeView mDecView;
        public SimpleDraweeView mImage;
        public TextView mLocation;
        public TextView mNumTv;
        public TextView mUserName;

        public ItemLiveViewHolder(View view) {
            super(view);
            this.mImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
            this.mLocation = (TextView) view.findViewById(R.id.tv_location);
            this.mContent = (TextView) view.findViewById(R.id.tv_content);
            this.mUserName = (TextView) view.findViewById(R.id.tv_username);
            this.mNumTv = (TextView) view.findViewById(R.id.tv_num);
            this.mDecView = (SimpleDraweeView) view.findViewById(R.id.dec_view);
            this.ivGaming = (ImageView) view.findViewById(R.id.iv_gaming);
        }
    }

    public LiveDebugListAdapter(Context context, List<CommonVideo> list) {
        this.d = context;
        this.c = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemLiveViewHolder(LayoutInflater.from(this.d).inflate(R.layout.item_live_video, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CommonVideo commonVideo = (CommonVideo) this.c.get(i);
        ItemLiveViewHolder itemLiveViewHolder = (ItemLiveViewHolder) viewHolder;
        itemLiveViewHolder.mImage.setAspectRatio(0.9f);
        itemLiveViewHolder.mLocation.setText(commonVideo.location);
        itemLiveViewHolder.mNumTv.setText(this.d.getString(R.string.num_people, new Object[]{commonVideo.getVisitorsCount()}));
        AppUtils.getInstance().getImageProvider().loadImage(itemLiveViewHolder.mImage, commonVideo.thumbnail_url);
        if (commonVideo.author != null) {
            itemLiveViewHolder.mUserName.setText(commonVideo.author.name);
        }
        itemLiveViewHolder.mContent.setText(commonVideo.getContent());
        itemLiveViewHolder.itemView.setOnClickListener(new a(this, i));
        if (commonVideo.game_id == 1) {
            itemLiveViewHolder.ivGaming.setVisibility(0);
            itemLiveViewHolder.ivGaming.setImageResource(R.drawable.ic_gaming_hlnb);
        } else if (commonVideo.game_id == 2) {
            itemLiveViewHolder.ivGaming.setVisibility(0);
            itemLiveViewHolder.ivGaming.setImageResource(R.drawable.ic_gaming_ypdx);
        } else if (commonVideo.game_id == 3) {
            itemLiveViewHolder.ivGaming.setVisibility(0);
            itemLiveViewHolder.ivGaming.setImageResource(R.drawable.ic_gaming_catanddog);
        } else if (commonVideo.game_id == 5) {
            itemLiveViewHolder.ivGaming.setVisibility(0);
            itemLiveViewHolder.ivGaming.setImageResource(R.drawable.ic_gaming_rolltable);
        } else if (commonVideo.game_id == 4) {
            itemLiveViewHolder.ivGaming.setVisibility(0);
            itemLiveViewHolder.ivGaming.setImageResource(R.drawable.ic_gaming_fanfanle);
        } else if (commonVideo.mic_status == 2 || commonVideo.mic_status == 3) {
            itemLiveViewHolder.ivGaming.setVisibility(0);
            itemLiveViewHolder.ivGaming.setImageResource(R.drawable.ic_mic_connecting);
        } else {
            itemLiveViewHolder.ivGaming.setVisibility(4);
        }
    }

    public int getItemCount() {
        return this.c.size();
    }
}
