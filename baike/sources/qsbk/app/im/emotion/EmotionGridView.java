package qsbk.app.im.emotion;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.R;
import qsbk.app.im.ChatMsgEmotionData;

public class EmotionGridView extends GridView {
    public static final int EMOTION_BIG = 0;
    public static final int EMOTION_SMALL = 1;
    private static final String a = EmotionGridView.class.getSimpleName();
    private int b = 0;
    private List<ChatMsgEmotionData> c;
    private a d;
    private OnEmotionItemClickListener e;

    public interface OnEmotionItemClickListener {
        void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData);
    }

    private class a extends BaseAdapter {
        final /* synthetic */ EmotionGridView a;

        private a(EmotionGridView emotionGridView) {
            this.a = emotionGridView;
        }

        public int getCount() {
            return this.a.c.size();
        }

        public Object getItem(int i) {
            return this.a.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        private int a() {
            return this.a.b == 1 ? R.layout.emotion_grid_item_small : R.layout.emotion_grid_item;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(a(), null);
            }
            b a = b.a(view);
            ChatMsgEmotionData chatMsgEmotionData = (ChatMsgEmotionData) this.a.c.get(i);
            view.setOnClickListener(new b(this, i, chatMsgEmotionData));
            if (this.a.b == 0) {
                a.b.setText(chatMsgEmotionData.name.replace("[", "").replace("]", ""));
            }
            a.a.setImageDrawable(a.a.getResources().getDrawable(chatMsgEmotionData.localResource));
            return view;
        }
    }

    private static class b {
        ImageView a;
        TextView b;

        public b(View view) {
            this.a = (ImageView) view.findViewById(R.id.image);
            this.b = (TextView) view.findViewById(R.id.text);
        }

        static b a(View view) {
            b bVar = new b(view);
            view.setTag(view);
            return bVar;
        }
    }

    public EmotionGridView(Context context) {
        super(context);
    }

    public EmotionGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EmotionGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public EmotionGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
    }

    public void setData(List<ChatMsgEmotionData> list) {
        if (this.c != null) {
            this.c.clear();
        }
        this.c = list;
        if (this.d != null) {
            this.d.notifyDataSetChanged();
            return;
        }
        this.d = new a();
        setAdapter(this.d);
    }

    public String getNameSpace() {
        if (this.c == null || this.c.size() <= 0) {
            return null;
        }
        return ((ChatMsgEmotionData) this.c.get(0)).namespace;
    }

    public void setEmotionType(int i) {
        this.b = i;
    }

    public void setOnEmotionItemClickListener(OnEmotionItemClickListener onEmotionItemClickListener) {
        this.e = onEmotionItemClickListener;
    }
}
