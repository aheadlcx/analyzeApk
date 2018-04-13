package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.m;
import java.util.List;

public class ScrollAdapter extends PagerAdapter {
    private static final String a = ScrollAdapter.class.getSimpleName();
    private List<ImageView> b;
    private Activity c;
    private Handler d;
    private Runnable e;
    private double f = 0.0d;
    private List<Special> g;
    private String h;

    public ScrollAdapter(Activity activity, List<ImageView> list, Handler handler, Runnable runnable, List<Special> list2, String str) {
        this.c = activity;
        this.b = list;
        this.d = handler;
        this.e = runnable;
        this.g = list2;
        this.h = str;
    }

    public void setItem(List<ImageView> list, Handler handler, Runnable runnable, List<Special> list2) {
        this.b = list;
        this.d = handler;
        this.e = runnable;
        this.g = list2;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size() > 1 ? Integer.MAX_VALUE : this.b.size();
        } else {
            return 0;
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
    }

    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        View view;
        Throwable th;
        View view2 = null;
        try {
            view = (View) this.b.get(i % this.b.size());
            try {
                ViewGroup viewGroup2 = (ViewGroup) view.getParent();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(view);
                }
                viewGroup.addView((View) this.b.get(i % this.b.size()));
            } catch (Throwable e) {
                Throwable th2 = e;
                view2 = view;
                th = th2;
                Log.d(a, " instantiateItem srollAdapter 滚屏图片.", th);
                view = view2;
                if (view != null) {
                    view.setOnTouchListener(new OnTouchListener(this) {
                        final /* synthetic */ ScrollAdapter b;

                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            switch (motionEvent.getAction()) {
                                case 0:
                                    this.b.f = (double) motionEvent.getRawX();
                                    this.b.d.removeCallbacksAndMessages(null);
                                    break;
                                case 1:
                                    if (((double) motionEvent.getRawX()) - this.b.f <= 11.0d && this.b.b != null) {
                                        this.b.a(i % this.b.b.size());
                                    }
                                    this.b.d.postDelayed(this.b.e, 3000);
                                    break;
                                case 3:
                                    this.b.d.postDelayed(this.b.e, 3000);
                                    break;
                            }
                            return true;
                        }
                    });
                }
                return view;
            }
        } catch (Exception e2) {
            th = e2;
            Log.d(a, " instantiateItem srollAdapter 滚屏图片.", th);
            view = view2;
            if (view != null) {
                view.setOnTouchListener(/* anonymous class already generated */);
            }
            return view;
        }
        if (view != null) {
            view.setOnTouchListener(/* anonymous class already generated */);
        }
        return view;
    }

    private void a(int i) {
        if (this.g != null) {
            if (this.g.size() <= 2) {
                i %= 2;
            }
            Special special = (Special) this.g.get(i);
            if ("BANNER".equals(special.getIsBanner()) || "ITEMS".equals(special.getIsBanner())) {
                m.openSpecialList(this.c, special.getId(), special.getMarker(), this.h, special.getTitle(), null);
            } else if ("URL".equals(special.getIsBanner()) || "TBURL".equals(special.getIsBanner())) {
                m.openUrlH5(this.c, special);
            } else if ("ITEM".equals(special.getIsBanner())) {
                b.spStatEvent(special.getId(), special.getMarker(), null);
                Item item = special.getItem();
                if (item != null) {
                    m.openGoodsDetails(this.c, item, special.getId(), this.h, "HOME", item.getTaobaoType());
                }
            }
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            super.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
