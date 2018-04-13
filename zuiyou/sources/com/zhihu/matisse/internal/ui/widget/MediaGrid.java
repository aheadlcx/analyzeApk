package com.zhihu.matisse.internal.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;

public class MediaGrid extends SquareFrameLayout implements OnClickListener, OnLongClickListener {
    public CheckView mCheckView;
    private ImageView mGifTag;
    private OnMediaGridClickListener mListener;
    private Item mMedia;
    private PreBindInfo mPreBindInfo;
    private ImageView mThumbnail;
    private ThumbnailProvider mThumbnailProvider;
    private TextView mVideoDuration;
    private OnMediaLongClickListener onMediaLongClickListener;

    public interface OnMediaGridClickListener {
        void onCheckViewClicked(CheckView checkView, Item item, ViewHolder viewHolder);

        void onThumbnailClicked(ImageView imageView, Item item, ViewHolder viewHolder);
    }

    public interface ThumbnailProvider {
        String provideThumbnailPath(Item item);
    }

    public interface OnMediaLongClickListener {
        void onLongClick(Item item);
    }

    public static class PreBindInfo {
        boolean mCheckViewCountable;
        Drawable mPlaceholder;
        int mResize;
        ViewHolder mViewHolder;

        public PreBindInfo(int i, Drawable drawable, boolean z, ViewHolder viewHolder) {
            this.mResize = i;
            this.mPlaceholder = drawable;
            this.mCheckViewCountable = z;
            this.mViewHolder = viewHolder;
        }
    }

    public MediaGrid(Context context) {
        super(context);
        init(context);
    }

    public MediaGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.media_grid_content, this, true);
        this.mThumbnail = (ImageView) findViewById(R.id.media_thumbnail);
        this.mCheckView = (CheckView) findViewById(R.id.check_view);
        this.mGifTag = (ImageView) findViewById(R.id.gif);
        this.mVideoDuration = (TextView) findViewById(R.id.video_duration);
        this.mThumbnail.setOnClickListener(this);
        this.mThumbnail.setOnLongClickListener(this);
        this.mCheckView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.mListener == null) {
            return;
        }
        if (view == this.mThumbnail) {
            this.mListener.onThumbnailClicked(this.mThumbnail, this.mMedia, this.mPreBindInfo.mViewHolder);
        } else if (view == this.mCheckView) {
            this.mListener.onCheckViewClicked(this.mCheckView, this.mMedia, this.mPreBindInfo.mViewHolder);
        }
    }

    public void preBindMedia(ThumbnailProvider thumbnailProvider, PreBindInfo preBindInfo) {
        this.mThumbnailProvider = thumbnailProvider;
        this.mPreBindInfo = preBindInfo;
    }

    public void bindMedia(Item item) {
        this.mMedia = item;
        setGifTag();
        initCheckView();
        setImage();
        setVideoDuration();
    }

    public Item getMedia() {
        return this.mMedia;
    }

    private void setGifTag() {
        this.mGifTag.setVisibility(this.mMedia.isGif() ? 0 : 8);
    }

    private void initCheckView() {
        this.mCheckView.setCountable(this.mPreBindInfo.mCheckViewCountable);
    }

    public void setCheckEnabled(boolean z) {
        this.mCheckView.setEnabled(z);
    }

    public void setCheckedNum(int i) {
        this.mCheckView.setCheckedNum(i);
    }

    public void setChecked(boolean z) {
        this.mCheckView.setChecked(z);
    }

    private void setImage() {
        String str;
        CharSequence charSequence = this.mMedia.path;
        if (this.mMedia.isVideo()) {
            if (this.mThumbnailProvider != null) {
                charSequence = this.mThumbnailProvider.provideThumbnailPath(this.mMedia);
            }
            if (TextUtils.isEmpty(charSequence)) {
                str = this.mMedia.path;
                if (this.mMedia.isGif()) {
                    SelectionSpec.getInstance().imageEngine.a(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, Uri.parse("file://" + str));
                } else {
                    SelectionSpec.getInstance().imageEngine.b(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, Uri.parse("file://" + str));
                }
            }
        }
        CharSequence charSequence2 = charSequence;
        if (this.mMedia.isGif()) {
            SelectionSpec.getInstance().imageEngine.a(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, Uri.parse("file://" + str));
        } else {
            SelectionSpec.getInstance().imageEngine.b(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, Uri.parse("file://" + str));
        }
    }

    private void setVideoDuration() {
        if (this.mMedia.isVideo()) {
            this.mVideoDuration.setVisibility(0);
            this.mVideoDuration.setText(DateUtils.formatElapsedTime(this.mMedia.duration / 1000));
            return;
        }
        this.mVideoDuration.setVisibility(8);
    }

    public void setOnMediaGridClickListener(OnMediaGridClickListener onMediaGridClickListener) {
        this.mListener = onMediaGridClickListener;
    }

    public void setOnMediaLongClickListener(OnMediaLongClickListener onMediaLongClickListener) {
        this.onMediaLongClickListener = onMediaLongClickListener;
    }

    public void removeOnMediaGridClickListener() {
        this.mListener = null;
    }

    public boolean onLongClick(View view) {
        if (this.onMediaLongClickListener != null) {
            this.onMediaLongClickListener.onLongClick(this.mMedia);
        }
        return true;
    }
}
