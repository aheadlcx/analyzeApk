package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.SmileyVo;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.SmilyEncUtils;
import java.util.List;

public class ExpressionGroup extends LinearLayout {
    private DeleteSmileyListener a;
    private OnItemClickListener b;

    public interface DeleteSmileyListener {
        void onTouchAction(int i);
    }

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public ExpressionGroup(Context context) {
        super(context);
    }

    public ExpressionGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setData(List<SmileyVo> list) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LinearLayout linearLayout = (LinearLayout) getChildAt(i);
            int childCount2 = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount2; i2++) {
                int i3 = (childCount2 * i) + i2;
                ImageView imageView = (ImageView) linearLayout.getChildAt(i2);
                String fileName = ((SmileyVo) list.get(i3)).getFileName();
                if (fileName != null) {
                    SmilyEncUtils.getInstance().getAsyncSmilyByFileName(fileName, imageView, new a(this), getScale());
                    imageView.setOnClickListener(new b(this, i3));
                } else {
                    imageView.setImageResource(((SmileyVo) list.get(i3)).getResID());
                    imageView.setOnClickListener(new c(this));
                    imageView.setOnTouchListener(new d(this));
                }
            }
        }
    }

    public void setDeleteSmileyListener(DeleteSmileyListener deleteSmileyListener) {
        this.a = deleteSmileyListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
    }

    public float getScale() {
        return ((float) Math.round((float) ((DisPlayUtil.getHeight(V6Coop.getInstance().getContext()) * 100) / 1280))) / 100.0f;
    }
}
