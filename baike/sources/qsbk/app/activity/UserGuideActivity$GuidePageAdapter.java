package qsbk.app.activity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class UserGuideActivity$GuidePageAdapter extends PagerAdapter {
    final /* synthetic */ UserGuideActivity a;
    private List<View> b;

    public UserGuideActivity$GuidePageAdapter(UserGuideActivity userGuideActivity, List<View> list) {
        this.a = userGuideActivity;
        this.b = list;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView((View) this.b.get(i), 0);
        return this.b.get(i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.b.get(i));
    }
}
