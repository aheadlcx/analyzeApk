package cn.tatagou.sdk.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import cn.tatagou.sdk.fragment.TtgTabFragment;
import cn.tatagou.sdk.pojo.AppCate;
import java.util.List;

public class CatAdapter extends FragmentStatePagerAdapter {
    private static final String b = CatAdapter.class.getSimpleName();
    public TtgTabFragment a;
    private List<AppCate> c;
    private String d;
    private Context e;

    public List<AppCate> getmAppCatsList() {
        return this.c;
    }

    public void setItem(List<AppCate> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    public CatAdapter(Context context, FragmentManager fragmentManager, List<AppCate> list, String str) {
        super(fragmentManager);
        this.c = list;
        this.d = str;
        this.e = context;
    }

    public void setSpecialId(String str) {
        this.d = str;
    }

    public Fragment getItem(int i) {
        AppCate appCate = (AppCate) this.c.get(i);
        String id = appCate != null ? appCate.getId() : "1";
        if (!"1".equals(id)) {
            this.d = null;
        }
        return TtgTabFragment.newInstance(this.d, id);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    public int getCount() {
        return this.c.size();
    }

    public CharSequence getPageTitle(int i) {
        return ((AppCate) this.c.get(i)).getName();
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        this.a = (TtgTabFragment) obj;
        super.setPrimaryItem(viewGroup, i, obj);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            super.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
