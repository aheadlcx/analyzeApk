package com.budejie.www.activity.label;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.label.a.a;
import com.budejie.www.h.c;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchItem$TypeEnum;
import java.util.ArrayList;
import java.util.List;

public class LabelMemberHeaderView extends RelativeLayout {
    private Context a;
    private TextView b;
    private AsyncImageView c;
    private RecyclerView d;
    private RelativeLayout e;
    private a f;
    private List<SearchItem> g;
    private View h;
    private String i;
    private LabelMemberHeaderView$a j;
    private String k;

    public LabelMemberHeaderView(Context context) {
        this(context, null);
    }

    public LabelMemberHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LabelMemberHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        a();
    }

    public void setThemeId(String str) {
        this.k = str;
    }

    private void a() {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.label_member_header_view, null);
        View findViewById = inflate.findViewById(R.id.moderator_header_view);
        View findViewById2 = inflate.findViewById(R.id.label_moderator_layout);
        ((TextView) findViewById2.findViewById(R.id.moderator_text_view)).setText(R.string.label_manito_text);
        findViewById2.findViewById(R.id.moderator_standard_layout).setVisibility(8);
        this.h = inflate.findViewById(R.id.deputy_moderator_header_view);
        this.c = (AsyncImageView) findViewById.findViewById(R.id.moderator_image_view);
        this.b = (TextView) findViewById.findViewById(R.id.moderator_name_text_view);
        findViewById2 = this.h.findViewById(R.id.deputy_moderator_header_layout);
        this.d = (RecyclerView) this.h.findViewById(R.id.deputy_moderator_recycler_view);
        ((TextView) findViewById2.findViewById(R.id.moderator_text_view)).setText(R.string.label_deputy_moderator_text);
        View view = (RelativeLayout) findViewById2.findViewById(R.id.moderator_standard_layout);
        findViewById2 = (RelativeLayout) findViewById.findViewById(R.id.moderator_header_layout).findViewById(R.id.moderator_standard_layout);
        this.e = (RelativeLayout) findViewById.findViewById(R.id.moderator_layout);
        b();
        addView(inflate);
        a(view);
        a(findViewById2);
    }

    private void a(View view) {
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LabelMemberHeaderView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.i)) {
                    new Builder(this.a.a, R.style.dialog).setTitle(R.string.label_moderator_standard_text).setMessage(this.a.i).setPositiveButton(R.string.label_know_text, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                }
            }
        });
    }

    private void b() {
        this.d.setLayoutManager(new GridLayoutManager(this.a, 1, 0, false));
    }

    public void setHeadListener(LabelMemberHeaderView$a labelMemberHeaderView$a) {
        this.j = labelMemberHeaderView$a;
    }

    private void c() {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (this.f == null) {
            this.f = new a(this.a, this.g, this.k);
            this.d.setAdapter(this.f);
            return;
        }
        this.f.notifyDataSetChanged();
    }

    public void setStandardText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.i = str;
        }
    }

    public void a(List<SearchItem> list, SearchItem searchItem) {
        if (list != null) {
            if (searchItem != null && h.a(this.a, searchItem)) {
                SearchItem searchItem2 = new SearchItem();
                searchItem2.setType(SearchItem$TypeEnum.ADD_MODERATOR.getValue());
                list.add(searchItem2);
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.clear();
            this.g.addAll(list);
            setModeratorData(searchItem);
            c();
        }
    }

    public void setModeratorData(final SearchItem searchItem) {
        this.h.setVisibility(!com.budejie.www.goddubbing.c.a.a(this.g) ? 0 : 8);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LabelMemberHeaderView b;

            public void onClick(View view) {
                if (searchItem != null) {
                    com.budejie.www.util.a.a(this.b.a, searchItem.getId());
                } else if (this.b.j != null) {
                    this.b.j.a();
                }
            }
        });
        if (searchItem == null) {
            this.c.setImageResource(c.a().b(R.attr.label_apply_moderator_theme));
            this.b.setText(R.string.label_apply_moderator);
            return;
        }
        this.c.setPostAvatarImage(searchItem.getProfileImageLarge());
        this.b.setText(searchItem.getUsername());
    }
}
