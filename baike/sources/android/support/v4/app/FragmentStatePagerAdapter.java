package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import qsbk.app.core.model.User;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private final FragmentManager a;
    private FragmentTransaction b = null;
    private ArrayList<SavedState> c = new ArrayList();
    private ArrayList<Fragment> d = new ArrayList();
    private Fragment e = null;

    public abstract Fragment getItem(int i);

    public FragmentStatePagerAdapter(FragmentManager fragmentManager) {
        this.a = fragmentManager;
    }

    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() == -1) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.d.size() > i) {
            Fragment fragment = (Fragment) this.d.get(i);
            if (fragment != null) {
                return fragment;
            }
        }
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        Fragment item = getItem(i);
        if (this.c.size() > i) {
            SavedState savedState = (SavedState) this.c.get(i);
            if (savedState != null) {
                item.setInitialSavedState(savedState);
            }
        }
        while (this.d.size() <= i) {
            this.d.add(null);
        }
        item.setMenuVisibility(false);
        item.setUserVisibleHint(false);
        this.d.set(i, item);
        this.b.add(viewGroup.getId(), item);
        return item;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Object saveFragmentInstanceState;
        Fragment fragment = (Fragment) obj;
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        while (this.c.size() <= i) {
            this.c.add(null);
        }
        ArrayList arrayList = this.c;
        if (fragment.isAdded()) {
            saveFragmentInstanceState = this.a.saveFragmentInstanceState(fragment);
        } else {
            saveFragmentInstanceState = null;
        }
        arrayList.set(i, saveFragmentInstanceState);
        this.d.set(i, null);
        this.b.remove(fragment);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.e) {
            if (this.e != null) {
                this.e.setMenuVisibility(false);
                this.e.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            this.e = fragment;
        }
    }

    public void finishUpdate(ViewGroup viewGroup) {
        if (this.b != null) {
            this.b.commitNowAllowingStateLoss();
            this.b = null;
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public Parcelable saveState() {
        Bundle bundle = null;
        if (this.c.size() > 0) {
            bundle = new Bundle();
            Parcelable[] parcelableArr = new SavedState[this.c.size()];
            this.c.toArray(parcelableArr);
            bundle.putParcelableArray("states", parcelableArr);
        }
        Parcelable parcelable = bundle;
        for (int i = 0; i < this.d.size(); i++) {
            Fragment fragment = (Fragment) this.d.get(i);
            if (fragment != null && fragment.isAdded()) {
                if (parcelable == null) {
                    parcelable = new Bundle();
                }
                this.a.putFragment(parcelable, User.FEMALE + i, fragment);
            }
        }
        return parcelable;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.c.clear();
            this.d.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.c.add((SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith(User.FEMALE)) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment fragment = this.a.getFragment(bundle, str);
                    if (fragment != null) {
                        while (this.d.size() <= parseInt) {
                            this.d.add(null);
                        }
                        fragment.setMenuVisibility(false);
                        this.d.set(parseInt, fragment);
                    } else {
                        Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }
}
