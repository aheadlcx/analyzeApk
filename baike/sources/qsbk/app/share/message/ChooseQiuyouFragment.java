package qsbk.app.share.message;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import qsbk.app.R;
import qsbk.app.activity.SearchQiuYouActivity;
import qsbk.app.fragments.FansFragment;
import qsbk.app.fragments.FriendsFragment;
import qsbk.app.fragments.MyGroupFragment;
import qsbk.app.fragments.QiuYouFragment;
import qsbk.app.fragments.StatisticFragment;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PagerSlidingTabStrip;

public class ChooseQiuyouFragment extends StatisticFragment {
    private static final String a = ChooseQiuyouFragment.class.getSimpleName();
    private ArrayList<Fragment> b = new ArrayList();
    private FragmentManager c;
    private QiushiPagerAdapter d;
    private BroadcastReceiver e = new a(this);
    private Bundle f;
    private PagerSlidingTabStrip g;
    private ViewPager h;
    private boolean i = false;

    public final class QiushiPagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ ChooseQiuyouFragment a;
        private ArrayList<Fragment> b = new ArrayList();
        private String[] c;

        public QiushiPagerAdapter(ChooseQiuyouFragment chooseQiuyouFragment, FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            this.a = chooseQiuyouFragment;
            super(fragmentManager);
            this.b = arrayList;
        }

        public QiushiPagerAdapter(ChooseQiuyouFragment chooseQiuyouFragment, FragmentManager fragmentManager, ArrayList<Fragment> arrayList, String[] strArr) {
            this.a = chooseQiuyouFragment;
            super(fragmentManager);
            this.b = arrayList;
            this.c = strArr;
        }

        public CharSequence getPageTitle(int i) {
            return this.c[i];
        }

        public Fragment getItem(int i) {
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return this.c.length;
        }
    }

    public static ChooseQiuyouFragment newInstance(Bundle bundle) {
        if (!(bundle == null || bundle.getBoolean("fromAt"))) {
            bundle.putBoolean("shared", true);
        }
        ChooseQiuyouFragment chooseQiuyouFragment = new ChooseQiuyouFragment();
        chooseQiuyouFragment.setArguments(bundle);
        return chooseQiuyouFragment;
    }

    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.e, new IntentFilter(SearchQiuYouActivity.INTENT_FILTER_SHARED_OK));
        super.onCreate(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.search_qiuyou, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (UIHelper.isNightTheme()) {
            menu.findItem(R.id.action_search).setIcon(R.drawable.add_qiuyou_ic_search_dark);
        } else {
            menu.findItem(R.id.action_search).setIcon(R.drawable.add_qiuyou_ic_search);
        }
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_search) {
            return super.onOptionsItemSelected(menuItem);
        }
        b();
        return true;
    }

    private void b() {
        int size = this.b.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            Fragment fragment = (Fragment) this.b.get(i);
            if (fragment instanceof QiuYouFragment) {
                Collection cacheData = ((QiuYouFragment) fragment).getCacheData();
                if (cacheData != null && cacheData.size() > 0) {
                    arrayList.addAll(cacheData);
                }
            }
        }
        SearchQiuYouActivity.launch4Shared(getActivity(), arrayList, this.f);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DebugUtil.debug(a, "onCreateView ");
        this.f = getArguments();
        View inflate = layoutInflater.inflate(R.layout.choose_qiuyou_fragment, null);
        this.c = getChildFragmentManager();
        c();
        this.g = (PagerSlidingTabStrip) inflate.findViewById(R.id.tabs);
        this.h = (ViewPager) inflate.findViewById(R.id.pager);
        if (this.i) {
            this.d = new QiushiPagerAdapter(this, this.c, this.b, new String[]{d(), getFollowerFromResource()});
        } else {
            this.d = new QiushiPagerAdapter(this, this.c, this.b, new String[]{d(), getFollowerFromResource(), "群"});
        }
        this.h.setAdapter(this.d);
        this.h.setOffscreenPageLimit(2);
        this.g.setViewPager(this.h, new b(this));
        this.g.setSelectedTabTextColor(UIHelper.getSelectedTabTextColor());
        this.h.setCurrentItem(0);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        DebugUtil.debug(a, "onActivityCreated ");
        super.onActivityCreated(bundle);
    }

    private void c() {
        this.i = this.f.getBoolean("fromAt", false);
        if (this.i) {
            this.b.add(new FriendsFragment(this.f));
            this.b.add(new FansFragment(this.f));
            return;
        }
        this.b.add(new FriendsFragment(this.f));
        this.b.add(new FansFragment(this.f));
        this.b.add(MyGroupFragment.newInstance(this.f, 2));
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.e);
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private String d() {
        return getResources().getString(R.string.friends);
    }

    public String getFollowerFromResource() {
        return getResources().getString(R.string.follower);
    }
}
