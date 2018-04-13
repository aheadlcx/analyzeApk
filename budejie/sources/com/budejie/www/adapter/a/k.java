package com.budejie.www.adapter.a;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.c.c$a;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.h.c;
import java.util.ArrayList;
import java.util.List;

public class k extends BaseAdapter {
    Activity a;
    LayoutInflater b;
    private List<SuggestedFollowsListItem> c = new ArrayList();
    private c$a d;

    class a {
        public RelativeLayout a;
        public AsyncImageView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public TextView f;
        public View g;
        public TextView h;
        public CheckBox i;
        public ImageView j;
        public RelativeLayout k;
        final /* synthetic */ k l;

        a(k kVar) {
            this.l = kVar;
        }
    }

    public k(Activity activity, c$a c_a) {
        this.a = activity;
        this.d = c_a;
        this.b = LayoutInflater.from(activity);
    }

    public void a(List<SuggestedFollowsListItem> list) {
        this.c.clear();
        for (int i = 0; i < list.size(); i++) {
            this.c.add(list.get(i));
        }
        notifyDataSetInvalidated();
    }

    public void b(List<SuggestedFollowsListItem> list) {
        for (int i = 0; i < list.size(); i++) {
            this.c.add(list.get(i));
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.b.inflate(R.layout.suggested_follows_item_layout, null);
            aVar.a = (RelativeLayout) view.findViewById(R.id.sfLayout);
            aVar.b = (AsyncImageView) view.findViewById(R.id.sfPictureIV);
            aVar.c = (TextView) view.findViewById(R.id.sfNickname);
            aVar.d = (TextView) view.findViewById(R.id.sfInfo);
            aVar.e = (TextView) view.findViewById(R.id.cancel_btn);
            aVar.f = (TextView) view.findViewById(R.id.add_btn);
            aVar.g = view.findViewById(R.id.divider_h_view1);
            aVar.h = (TextView) view.findViewById(R.id.titleTV);
            aVar.i = (CheckBox) view.findViewById(R.id.addCheckBox);
            aVar.j = (ImageView) view.findViewById(R.id.sfSocialIcon);
            aVar.k = (RelativeLayout) view.findViewById(R.id.sfContentLayout);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        final SuggestedFollowsListItem suggestedFollowsListItem = (SuggestedFollowsListItem) this.c.get(i);
        if (suggestedFollowsListItem.title_type != 1) {
            aVar.h.setVisibility(8);
            aVar.a.setVisibility(0);
            aVar.c.setVisibility(0);
            aVar.d.setVisibility(0);
            CharSequence charSequence = suggestedFollowsListItem.screen_name;
            CharSequence charSequence2 = suggestedFollowsListItem.social_name;
            String str = suggestedFollowsListItem.fans_count;
            if (suggestedFollowsListItem.plat_flag == 0) {
                aVar.j.setVisibility(0);
                aVar.j.setImageResource(c.a().b(R.attr.ic_follows_sinafriend));
            } else if (suggestedFollowsListItem.plat_flag == 1) {
                aVar.j.setVisibility(0);
                aVar.j.setImageResource(R.drawable.ic_follows_tencentfriend);
            } else if (suggestedFollowsListItem.plat_flag == 2) {
                aVar.j.setVisibility(8);
            }
            str = suggestedFollowsListItem.header;
            aVar.b.setOnClickListener(null);
            aVar.k.setOnClickListener(null);
            aVar.b.setAsyncCacheImage(str, R.drawable.head_portrait);
            if (suggestedFollowsListItem.title_type == 2) {
                if (TextUtils.isEmpty(charSequence)) {
                    aVar.c.setText("");
                } else {
                    aVar.c.setText(charSequence);
                }
                if (TextUtils.isEmpty(charSequence2)) {
                    aVar.d.setText("");
                } else {
                    aVar.d.setText(charSequence2);
                }
                if (suggestedFollowsListItem.is_follow == 0) {
                    aVar.f.setVisibility(0);
                    aVar.e.setVisibility(8);
                } else if (suggestedFollowsListItem.is_follow == 1) {
                    aVar.f.setVisibility(8);
                    aVar.e.setVisibility(0);
                }
                aVar.i.setVisibility(8);
                aVar.f.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k c;

                    public void onClick(View view) {
                        this.c.d.a(suggestedFollowsListItem, i);
                    }
                });
                aVar.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k c;

                    public void onClick(View view) {
                        this.c.d.b(suggestedFollowsListItem, i);
                    }
                });
                aVar.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k b;

                    public void onClick(View view) {
                        this.b.d.a(view, suggestedFollowsListItem);
                    }
                });
                aVar.k.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k b;

                    public void onClick(View view) {
                        this.b.d.a(view, suggestedFollowsListItem);
                    }
                });
            } else if (suggestedFollowsListItem.title_type == 3) {
                aVar.c.setVisibility(8);
                aVar.d.setVisibility(8);
                aVar.j.setVisibility(8);
                if (TextUtils.isEmpty(charSequence2)) {
                    aVar.c.setText("");
                } else {
                    aVar.c.setVisibility(0);
                    aVar.c.setText(charSequence2);
                }
                if (suggestedFollowsListItem.plat_flag == 3) {
                    aVar.d.setVisibility(0);
                    aVar.d.setText(suggestedFollowsListItem.phone);
                }
                aVar.f.setVisibility(8);
                aVar.e.setVisibility(8);
                aVar.i.setVisibility(0);
                aVar.i.setChecked(suggestedFollowsListItem.isAdd);
                aVar.i.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k c;

                    public void onClick(View view) {
                        this.c.d.a(((CheckBox) view).isChecked(), suggestedFollowsListItem, i);
                    }
                });
            }
        } else if (!TextUtils.isEmpty(suggestedFollowsListItem.title)) {
            aVar.h.setVisibility(0);
            aVar.a.setVisibility(8);
            aVar.h.setText(suggestedFollowsListItem.title);
        }
        return view;
    }
}
