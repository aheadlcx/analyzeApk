package qsbk.app.live.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.live.R;

public class GiftGridView extends GridView {
    private List<GiftData> a;
    private a b;
    private List<OnGiftItemClickListener> c = new ArrayList();
    private int d = -1;

    public interface OnGiftItemClickListener {
        void onGiftItemClick(int i, GiftData giftData);
    }

    private class a extends BaseAdapter {
        final /* synthetic */ GiftGridView a;

        private a(GiftGridView giftGridView) {
            this.a = giftGridView;
        }

        public int getCount() {
            return this.a.a.size();
        }

        public Object getItem(int i) {
            return this.a.a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        private int a() {
            return R.layout.live_gift_grid_item;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(a(), null);
            }
            b a = b.a(view);
            GiftData giftData = (GiftData) this.a.a.get(i);
            view.setOnClickListener(new dw(this, giftData, a, i));
            a.b.setSelected(giftData.selected);
            ImageView imageView = a.b;
            int i2 = (giftData.selected || giftData.cb) ? 0 : 8;
            imageView.setVisibility(i2);
            if (!TextUtils.isEmpty(giftData.gn)) {
                a.c.setText(giftData.gn);
            }
            if (giftData.s == 2) {
                a.h.setText(String.valueOf(ConfigInfoUtil.instance().getGiftCountById(giftData.gd)));
                a.g.setVisibility(0);
                a.d.setVisibility(8);
            } else {
                a.g.setVisibility(8);
                a.d.setVisibility(0);
            }
            if (giftData.s != 1 || TextUtils.isEmpty(giftData.desc)) {
                a.f.setVisibility(0);
                a.e.setText(String.valueOf(giftData.pr));
            } else {
                a.f.setVisibility(8);
                a.e.setText(String.valueOf(giftData.desc));
            }
            AppUtils.getInstance().getImageProvider().loadGift(a.a, giftData.ig);
            if (!(giftData.selected || giftData.mAnimator == null || !giftData.mAnimator.isRunning())) {
                giftData.mAnimator = null;
            }
            return view;
        }
    }

    private static class b {
        ImageView a;
        ImageView b;
        TextView c;
        View d;
        TextView e;
        View f;
        View g;
        TextView h;

        public b(View view) {
            this.a = (ImageView) view.findViewById(R.id.live_image);
            this.b = (ImageView) view.findViewById(R.id.live_checkbox);
            this.c = (TextView) view.findViewById(R.id.live_name);
            this.d = view.findViewById(R.id.ll_price);
            this.e = (TextView) view.findViewById(R.id.live_price);
            this.f = view.findViewById(R.id.live_price_diamond);
            this.g = view.findViewById(R.id.ll_free_num);
            this.h = (TextView) view.findViewById(R.id.live_gift_free_num);
        }

        static b a(View view) {
            b bVar = new b(view);
            view.setTag(view);
            return bVar;
        }
    }

    public GiftGridView(Context context) {
        super(context);
    }

    public GiftGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GiftGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public GiftGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setData(List<GiftData> list) {
        if (this.a != null) {
            this.a.clear();
        }
        this.a = list;
        if (this.b != null) {
            this.b.notifyDataSetChanged();
            return;
        }
        this.b = new a();
        setAdapter(this.b);
    }

    public void addOnGiftItemClickListener(OnGiftItemClickListener onGiftItemClickListener) {
        this.c.add(onGiftItemClickListener);
    }

    public void clearGiftCheck() {
        if (this.a != null && this.d < this.a.size()) {
            if (this.d >= 0) {
                ((GiftData) this.a.get(this.d)).selected = false;
                if (((GiftData) this.a.get(this.d)).mAnimator != null) {
                    ((GiftData) this.a.get(this.d)).mAnimator.cancel();
                    ((GiftData) this.a.get(this.d)).mAnimator = null;
                }
            } else {
                for (GiftData giftData : this.a) {
                    giftData.selected = false;
                    if (giftData.mAnimator != null) {
                        giftData.mAnimator.cancel();
                        giftData.mAnimator = null;
                    }
                }
            }
        }
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }
}
