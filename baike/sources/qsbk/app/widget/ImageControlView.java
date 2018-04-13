package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class ImageControlView extends LinearLayout {
    private OnOperationSelectListener a;
    public ImageView comment;
    public ImageView save;
    public ImageView share;
    public HighlightableImageButton support;
    public HighlightableImageButton unSupport;

    public interface OnOperationSelectListener {
        void onCommentSelect(View view);

        void onSaveSelect(View view);

        void onShareSelect(View view);

        void onSupportSelect(View view, boolean z);

        void onUnSupportSelect(View view, boolean z);
    }

    public ImageControlView(Context context) {
        this(context, null);
    }

    public ImageControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        if (this.support == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.imageviewer_control, this);
            this.support = (HighlightableImageButton) inflate.findViewById(R.id.support);
            this.support.setHighlighted(false);
            this.support.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_support));
            this.unSupport = (HighlightableImageButton) inflate.findViewById(R.id.unsupport);
            this.unSupport.setHighlighted(false);
            this.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_unsupport));
            this.comment = (ImageView) inflate.findViewById(R.id.comment);
            this.share = (ImageView) inflate.findViewById(R.id.share);
            this.save = (ImageView) inflate.findViewById(R.id.save);
        }
    }

    public void reset() {
        this.support.setEnabled(true);
        this.support.setClickable(true);
        this.support.setHighlighted(false);
        this.support.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_support));
        this.unSupport.setEnabled(true);
        this.unSupport.setClickable(true);
        this.unSupport.setHighlighted(false);
        this.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_unsupport));
    }

    public void setSupport() {
        if (this.unSupport.isHighlighted()) {
            this.unSupport.setEnabled(true);
            this.unSupport.setHighlighted(false);
            this.unSupport.setClickable(true);
            this.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_unsupport));
        }
        this.support.setEnabled(false);
        this.support.setHighlighted(true);
        this.support.setClickable(false);
        this.support.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_support_press));
    }

    public void setUnSupport() {
        if (this.support.isHighlighted()) {
            this.support.setEnabled(true);
            this.support.setHighlighted(false);
            this.support.setClickable(true);
            this.support.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_support));
        }
        this.unSupport.setEnabled(false);
        this.unSupport.setHighlighted(true);
        this.unSupport.setClickable(false);
        this.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_unsupport_press));
    }

    public void setDownloadOnly() {
        this.support.setVisibility(4);
        this.unSupport.setVisibility(4);
        this.comment.setVisibility(4);
        this.share.setVisibility(4);
        this.save.setVisibility(0);
    }

    public boolean isSupport() {
        return this.support.isHighlighted();
    }

    public boolean isUnsupport() {
        return this.unSupport.isHighlighted();
    }

    public void setOnOperationSelectListener(OnOperationSelectListener onOperationSelectListener) {
        this.a = onOperationSelectListener;
        if (this.support != null && this.a != null) {
            this.support.setOnClickListener(new cb(this));
            this.unSupport.setOnClickListener(new cc(this));
            this.comment.setOnClickListener(new cd(this));
            this.share.setOnClickListener(new ce(this));
            this.save.setOnClickListener(new cf(this));
        }
    }
}
