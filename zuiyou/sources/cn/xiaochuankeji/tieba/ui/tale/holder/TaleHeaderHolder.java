package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import java.util.Locale;

public class TaleHeaderHolder extends b {
    private TaleComment c;
    @BindView
    AppCompatTextView theme_count;
    @BindView
    AppCompatTextView theme_title;

    public TaleHeaderHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        this.c = taleComment;
        this.theme_title.setText(String.valueOf(taleComment.title));
        this.theme_count.setText(String.format(Locale.SIMPLIFIED_CHINESE, "%d 跟帖 >", new Object[]{Long.valueOf(taleComment.count)}));
    }
}
