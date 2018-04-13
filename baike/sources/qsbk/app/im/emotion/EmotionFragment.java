package qsbk.app.im.emotion;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import qsbk.app.R;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;

@Deprecated
public class EmotionFragment extends BaseFragment {
    private EmotionGridView a;
    private List<ChatMsgEmotionData> b;
    private OnEmotionItemClickListener c;

    public void setOnEmotionItemClickListener(OnEmotionItemClickListener onEmotionItemClickListener) {
        this.c = onEmotionItemClickListener;
        if (this.a != null) {
            this.a.setOnEmotionItemClickListener(onEmotionItemClickListener);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnEmotionItemClickListener) {
            this.c = (OnEmotionItemClickListener) activity;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.emotion_grid, null);
        this.a = (EmotionGridView) inflate.findViewById(R.id.grid);
        if (this.b != null) {
            this.a.setData(this.b);
        }
        if (this.c != null) {
            setOnEmotionItemClickListener(this.c);
        }
        return inflate;
    }

    public void setData(List<ChatMsgEmotionData> list) {
        this.b = list;
        if (this.a != null) {
            this.a.setData(list);
        }
    }
}
