package cn.xiaochuankeji.tieba.ui.discovery.moretopic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.xiaochuankeji.tieba.background.topic.Category;
import cn.xiaochuankeji.tieba.ui.a.a;
import java.util.ArrayList;

public class c extends FragmentPagerAdapter {
    private ArrayList<Category> a;

    public c(FragmentManager fragmentManager, ArrayList<Category> arrayList) {
        super(fragmentManager);
        this.a = arrayList;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return new a();
        }
        return b.a(((Category) this.a.get(i)).categoryId);
    }

    public int getCount() {
        return this.a.size();
    }
}
