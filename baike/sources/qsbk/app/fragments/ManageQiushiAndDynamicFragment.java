package qsbk.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.ManageQiuShiNewActivity;
import qsbk.app.activity.MyQiuYouDynamicActivity;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;

public class ManageQiushiAndDynamicFragment extends StatisticFragment {
    private ListView a;
    private b b;
    private List<a> c;

    public interface IGetView {
        View getView(a aVar, View view, ViewGroup viewGroup);
    }

    private static class a {
        static int a = 0;
        static int b = 1;
        static int c = 2;
        static int d = 3;
        static int e = -1;
        String f;
        String g;
        int h = e;
        int i;

        public a(int i, String str, String str2, int i2) {
            this.f = str2;
            this.g = str;
            this.h = i;
            this.i = i2;
        }

        public static a newInstance(int i, String str, String str2, int i2) {
            return new a(i, str, str2, i2);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof a) {
                return obj.toString().equals(toString());
            }
            return false;
        }

        public String toString() {
            return "Item [iconUrl=" + this.f + ", description=" + this.g + ", id=" + this.h + ", type=" + this.i + "]";
        }
    }

    private class b extends BaseAdapter {
        protected Drawable a;
        SparseArray<IGetView> b = new SparseArray();
        final /* synthetic */ ManageQiushiAndDynamicFragment c;

        abstract class a implements IGetView {
            final /* synthetic */ b a;

            abstract int a();

            a(b bVar) {
                this.a = bVar;
            }

            public View getView(a aVar, View view, ViewGroup viewGroup) {
                if (view != null) {
                    return view;
                }
                view = ManageQiushiAndDynamicFragment.b(this.a.c.getActivity(), -1, a());
                view.setBackgroundDrawable(null);
                return view;
            }
        }

        class b extends a {
            final /* synthetic */ b b;

            b(b bVar) {
                this.b = bVar;
                super(bVar);
            }

            int a() {
                return Math.round(this.b.c.getActivity().getResources().getDisplayMetrics().density * 8.0f);
            }
        }

        class c implements IGetView {
            final /* synthetic */ b a;

            c(b bVar) {
                this.a = bVar;
            }

            public View getView(a aVar, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(this.a.c.getActivity()).inflate(R.layout.my_profile_item_normal, viewGroup, false);
                }
                int intValue = aVar.f.contains("drawable://") ? Integer.valueOf(aVar.f.replace("drawable://", "")).intValue() : 0;
                if (intValue != 0) {
                    ((ImageView) view.findViewById(R.id.icon)).setImageResource(intValue);
                }
                ((TextView) view.findViewById(R.id.title)).setText(aVar.g);
                if (UIHelper.isNightTheme()) {
                    view.setBackgroundResource(R.color.popupmenu_bg_dark);
                } else {
                    view.setBackgroundResource(R.drawable.profile_popupmenu_item_bg_day);
                }
                return view;
            }
        }

        class d extends a {
            final /* synthetic */ b b;

            d(b bVar) {
                this.b = bVar;
                super(bVar);
            }

            int a() {
                return this.b.c.getActivity().getResources().getDimensionPixelSize(R.dimen.g_divider_small);
            }
        }

        b(ManageQiushiAndDynamicFragment manageQiushiAndDynamicFragment) {
            this.c = manageQiushiAndDynamicFragment;
            this.b.put(a.c, new b(this));
            this.b.put(a.a, new c(this));
            this.b.put(a.b, new d(this));
            b();
        }

        private void b() {
            this.a = a();
        }

        protected Drawable a() {
            return TileBackground.getBackgroud(this.c.getActivity(), BgImageType.ARTICLE);
        }

        public int getCount() {
            return this.c.c != null ? this.c.c.size() : 0;
        }

        public boolean isEnabled(int i) {
            int itemViewType = getItemViewType(i);
            return (itemViewType == a.c || itemViewType == a.b) ? false : true;
        }

        public int getViewTypeCount() {
            return a.d;
        }

        public int getItemViewType(int i) {
            return ((a) this.c.c.get(i)).i;
        }

        public Object getItem(int i) {
            return this.c.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar = (a) this.c.c.get(i);
            IGetView iGetView = (IGetView) this.b.get(getItemViewType(i));
            if (iGetView != null) {
                return iGetView.getView(aVar, view, viewGroup);
            }
            return null;
        }
    }

    private static View b(Context context, int i, int i2) {
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(i, i2));
        return view;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_qiushi_manager, viewGroup, false);
        this.a = (ListView) inflate.findViewById(R.id.list);
        a();
        return inflate;
    }

    public void onResume() {
        super.onResume();
    }

    private void a() {
        this.c = new ArrayList();
        this.c.add(a.newInstance(a.e, "", "", a.c));
        this.c.add(a.newInstance(1, "我的糗事", "drawable://" + UIHelper.getManageMyQiushiIcon(), a.a));
        this.c.add(a.newInstance(a.e, "", "", a.b));
        this.c.add(a.newInstance(2, "我的收藏", "drawable://" + UIHelper.getMyCollectIcon(), a.a));
        this.c.add(a.newInstance(a.e, "", "", a.b));
        this.c.add(a.newInstance(3, "我的参与", "drawable://" + UIHelper.getMyPaticpateIcon(), a.a));
        this.c.add(a.newInstance(a.e, "", "", a.c));
        this.c.add(a.newInstance(4, "我的动态", "drawable://" + UIHelper.getMyDynamicIcon(), a.a));
        this.a.setOnItemClickListener(new ff(this));
        this.b = new b(this);
        this.a.setAdapter(this.b);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void b() {
        startActivity(new Intent(getActivity(), ActionBarLoginActivity.class));
    }

    private void a(boolean z) {
        if (QsbkApp.currentUser == null) {
            b();
            return;
        }
        Intent intent = new Intent(getActivity(), ManageQiuShiActivity.class);
        intent.putExtra("isFromCollect", z);
        startActivity(intent);
    }

    private void a(Context context) {
        Intent intent = new Intent(context, MyQiuYouDynamicActivity.class);
        intent.putExtra("uid", QsbkApp.currentUser.userId);
        intent.putExtra("fromManage", true);
        startActivity(intent);
    }

    private void c() {
        if (QsbkApp.currentUser == null) {
            b();
        } else {
            startActivity(new Intent(getActivity(), ManageQiuShiNewActivity.class));
        }
    }
}
