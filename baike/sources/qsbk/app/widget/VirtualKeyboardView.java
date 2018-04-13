package qsbk.app.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;

public class VirtualKeyboardView extends RelativeLayout {
    Context a;
    private GridView b;
    private ArrayList<Key> c;
    private RelativeLayout d;
    private OnKeyPressListener e;

    public static class Key {
        public static final int DEL = -1;
        public static final int DOT = -2;
        public String desc;
        public int value;

        public Key(String str, int i) {
            this.desc = str;
            this.value = i;
        }
    }

    class KeyBoardAdapter extends BaseAdapter {
        final /* synthetic */ VirtualKeyboardView a;
        private Context b;
        private ArrayList<Key> c;

        public final class ViewHolder {
            final /* synthetic */ KeyBoardAdapter a;
            public TextView btnKey;
            public RelativeLayout imgDelete;

            public ViewHolder(KeyBoardAdapter keyBoardAdapter) {
                this.a = keyBoardAdapter;
            }
        }

        public KeyBoardAdapter(VirtualKeyboardView virtualKeyboardView, Context context, ArrayList<Key> arrayList) {
            this.a = virtualKeyboardView;
            this.b = context;
            this.c = arrayList;
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = View.inflate(this.b, R.layout.grid_item_virtual_keyboard, null);
                viewHolder = new ViewHolder(this);
                viewHolder.btnKey = (TextView) view.findViewById(R.id.btn_keys);
                viewHolder.imgDelete = (RelativeLayout) view.findViewById(R.id.imgDelete);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (i == 9) {
                viewHolder.imgDelete.setVisibility(4);
                viewHolder.btnKey.setVisibility(0);
                viewHolder.btnKey.setText(((Key) this.c.get(i)).desc);
                viewHolder.btnKey.setBackgroundColor(Color.parseColor("#e0e0e0"));
            } else if (i == 11) {
                viewHolder.btnKey.setBackgroundResource(R.drawable.keyboard_delete_img);
                viewHolder.imgDelete.setVisibility(0);
                viewHolder.btnKey.setVisibility(4);
            } else {
                viewHolder.imgDelete.setVisibility(4);
                viewHolder.btnKey.setVisibility(0);
                viewHolder.btnKey.setText(((Key) this.c.get(i)).desc);
            }
            return view;
        }
    }

    public interface OnKeyPressListener {
        void onKeyPressed(Key key);
    }

    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        View inflate = View.inflate(context, R.layout.layout_virtual_keyboard, null);
        this.c = new ArrayList();
        this.d = (RelativeLayout) inflate.findViewById(R.id.layoutBack);
        this.b = (GridView) inflate.findViewById(R.id.gv_keybord);
        a();
        b();
        addView(inflate);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public int getMeasureWrapContentHeight() {
        return (this.b.getMeasuredHeight() + this.d.getMeasuredHeight()) + 2;
    }

    public RelativeLayout getLayoutBack() {
        return this.d;
    }

    public ArrayList<Key> getValueList() {
        return this.c;
    }

    private void a() {
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                this.c.add(new Key(i + "", i));
            } else if (i == 10) {
                this.c.add(new Key(".", -2));
            } else if (i == 11) {
                this.c.add(new Key("0", 0));
            } else if (i == 12) {
                this.c.add(new Key("", -1));
            }
        }
    }

    private void b() {
        this.b.setAdapter(new KeyBoardAdapter(this, this.a, this.c));
        this.b.setOnItemClickListener(new fd(this));
    }

    public void setOnKeypressListener(OnKeyPressListener onKeyPressListener) {
        this.e = onKeyPressListener;
    }
}
