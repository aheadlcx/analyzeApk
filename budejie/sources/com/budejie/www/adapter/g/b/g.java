package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.view.TabPageIndicator;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.h.c;
import com.budejie.www.http.i;
import com.budejie.www.util.ac;
import java.util.ArrayList;
import java.util.List;

public class g extends a<ListItemObject> {
    public g(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_history_multi_video, viewGroup);
        ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        final TabPageIndicator tabPageIndicator = (TabPageIndicator) inflate.findViewById(R.id.tab_page_indicator);
        final List list = (List) ((ListItemObject) this.c).getmMultiHistoryData();
        final int size = (list.size() % 3 > 0 ? 1 : 0) + (list.size() / 3);
        tabPageIndicator.setPointResource(c.a().b(R.attr.history_post_indicator_icon));
        tabPageIndicator.a(size);
        viewPager.setAdapter(new PagerAdapter(this) {
            final /* synthetic */ g c;
            private SparseArray<View> d = new SparseArray();

            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            public int getCount() {
                return size;
            }

            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) this.d.get(i));
            }

            public Object instantiateItem(ViewGroup viewGroup, int i) {
                View inflate;
                View view = (View) this.d.get(i);
                if (view == null) {
                    inflate = View.inflate(this.c.a, R.layout.post_history_multi_video_page_layout, null);
                    this.d.put(i, inflate);
                    ListView listView = (ListView) inflate;
                    final List arrayList = new ArrayList();
                    int i2 = i * 3;
                    while (i2 < (i + 1) * 3 && i2 < list.size()) {
                        arrayList.add(list.get(i2));
                        i2++;
                    }
                    listView.setAdapter(new BaseAdapter(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public View getView(int i, View view, ViewGroup viewGroup) {
                            int parseInt;
                            CharSequence charSequence;
                            ListItemObject listItemObject = (ListItemObject) arrayList.get(i);
                            View inflate = View.inflate(this.b.c.a, R.layout.post_history_multi_video_item_layout, null);
                            ((AsyncImageView) inflate.findViewById(R.id.image)).setPostImage(listItemObject.getImgUrl());
                            ((TextView) inflate.findViewById(R.id.content)).setText(listItemObject.getContent());
                            try {
                                parseInt = Integer.parseInt(listItemObject.getVideotime());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                parseInt = 0;
                            }
                            ((TextView) inflate.findViewById(R.id.duration)).setText(ac.a((long) (parseInt * 1000)));
                            TextView textView = (TextView) inflate.findViewById(R.id.play_time);
                            if (TextUtils.isEmpty(listItemObject.getPlaycount())) {
                                charSequence = "";
                            } else {
                                charSequence = this.b.c.a.getString(R.string.play_time, new Object[]{listItemObject.getPlaycount()});
                            }
                            textView.setText(charSequence);
                            return inflate;
                        }

                        public long getItemId(int i) {
                            return (long) i;
                        }

                        public Object getItem(int i) {
                            return arrayList.get(i);
                        }

                        public int getCount() {
                            return arrayList.size();
                        }
                    });
                    listView.setOnItemClickListener(new OnItemClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            ListItemObject listItemObject = (ListItemObject) adapterView.getItemAtPosition(i);
                            view.setTag(listItemObject);
                            if (this.a.c.a instanceof com.budejie.www.adapter.e.a) {
                                ((com.budejie.www.adapter.e.a) this.a.c.a).e(view, listItemObject);
                            }
                        }
                    });
                } else {
                    inflate = view;
                }
                viewGroup.addView(inflate);
                return inflate;
            }
        });
        viewPager.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ g b;

            public void onPageSelected(int i) {
                tabPageIndicator.setSelectIndicator(i);
                i.a(this.b.a.getString(R.string.track_event_see_god_post), "用户滑动历史神贴");
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        return inflate;
    }

    public void c() {
    }

    public void onClick(View view) {
    }
}
