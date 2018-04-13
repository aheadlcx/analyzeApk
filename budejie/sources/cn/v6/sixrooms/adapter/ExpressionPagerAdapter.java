package cn.v6.sixrooms.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import cn.v6.sixrooms.widgets.phone.ExpressionGroup;
import cn.v6.sixrooms.widgets.phone.ExpressionKeyboard.PageSmily;
import java.util.ArrayList;

public class ExpressionPagerAdapter extends PagerAdapter {
    private ArrayList<PageSmily> a;

    public int getItemPosition(Object obj) {
        return -2;
    }

    public ExpressionPagerAdapter(ArrayList<PageSmily> arrayList) {
        this.a = arrayList;
    }

    public ArrayList<PageSmily> getExpressionViews() {
        return this.a;
    }

    public void setExpressionViews(ArrayList<PageSmily> arrayList) {
        this.a = arrayList;
    }

    public int getCount() {
        return this.a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ExpressionGroup expressionGroup = (ExpressionGroup) ((PageSmily) this.a.get(i)).getView();
        expressionGroup.setData(((PageSmily) this.a.get(i)).getSmilys());
        viewGroup.addView(expressionGroup);
        return expressionGroup;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (i < this.a.size()) {
            viewGroup.removeView(((PageSmily) this.a.get(i)).getView());
        }
    }
}
