package cn.v6.sixrooms.hall;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import cn.v6.sixrooms.constants.CommonStrs;
import java.util.ArrayList;
import java.util.List;

public class SixRoomsPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> a;

    SixRoomsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull List<String> list) {
        super(fragmentManager);
        this.a = new ArrayList(list.size());
        for (String str : list) {
            if (CommonStrs.TYPE_LOCATION.equals(str)) {
                this.a.add(HostsNearbyFragment.newInstance());
            } else {
                this.a.add(HostsFragment.newInstance(str));
            }
        }
    }

    public Fragment getItem(int i) {
        return (Fragment) this.a.get(i);
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
    }
}
