package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import c.a.d.a.a;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.favorite.c;
import cn.xiaochuankeji.tieba.background.favorite.e;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.n;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu;
import cn.xiaochuankeji.tieba.ui.widget.f;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONObject;

public class FavoritePostListActivity extends n implements b {
    private e g;
    private Favorite h;
    private TextView i;
    private PostQueryListView j;

    protected void j() {
        this.j.h();
    }

    protected QueryListView k() {
        this.j = new PostQueryListView(this);
        this.j.f();
        return this.j;
    }

    protected void c() {
        super.c();
        this.i = new TextView(this);
        this.i.setTextColor(getResources().getColor(R.color.empty_content_notify));
        this.i.setGravity(17);
        this.i.setCompoundDrawablesWithIntrinsicBounds(null, a.a().b(R.drawable.empty_tip_reported_post), null, null);
        this.i.setCompoundDrawablePadding(cn.xiaochuankeji.tieba.ui.utils.e.a(20.0f));
        this.i.setText("空空如也，寂寞如雪~");
        this.i.setTextSize((float) ((int) (getResources().getDimension(R.dimen.text_size_15) / cn.htjyb.c.a.f(this))));
        this.i.setVisibility(8);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rootView);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        relativeLayout.addView(this.i, layoutParams);
    }

    protected String v() {
        return null;
    }

    protected void w() {
        this.f.setTitle(this.h.getName() + "(" + this.h.getPostCount() + ")");
        this.j.f();
        this.j.a(this.g, true);
        this.f.setOptionImg(a.a().d(R.drawable.ic_nav_more));
    }

    protected boolean a(Bundle bundle) {
        this.h = (Favorite) getIntent().getSerializableExtra("key_favorite");
        if (this.h == null) {
            return false;
        }
        this.g = new e(this.h.getId());
        this.g.registerOnQueryFinishListener(this);
        return true;
    }

    public void s() {
        View optionImageView = this.a.getOptionImageView();
        SDPopupMenu sDPopupMenu = new SDPopupMenu(this, optionImageView, SDPopupMenu.a(optionImageView), new SDPopupMenu.b(this) {
            final /* synthetic */ FavoritePostListActivity a;

            {
                this.a = r1;
            }

            public void b(int i) {
                if (1 == i) {
                    this.a.x();
                } else if (2 == i) {
                    this.a.y();
                }
            }
        });
        sDPopupMenu.a("编辑", 1);
        sDPopupMenu.a("删除", 2, true);
        sDPopupMenu.b();
    }

    private void x() {
        CreateOrEditFavoriteActivity.a(this, this.h.getId(), this.h.getName(), 100);
    }

    private void y() {
        f.a("提示", "删除收藏夹后该收藏夹中的内容也会被一并删除", this, new f.a(this) {
            final /* synthetic */ FavoritePostListActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    new c(this.a.h.getId(), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onResponse(Object obj, Object obj2) {
                            a((JSONObject) obj, obj2);
                        }

                        public void a(JSONObject jSONObject, Object obj) {
                            g.a("删除成功");
                            this.a.a.finish();
                            cn.xiaochuankeji.tieba.background.favorite.f.a().a(this.a.a.h.getId());
                        }
                    }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onErrorResponse(XCError xCError, Object obj) {
                            g.a(xCError.getMessage());
                        }
                    }).execute();
                }
            }
        }).setConfirmTip("删除");
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (-1 == i2 && 100 == i) {
            String string = intent.getExtras().getString("edit_complete_name");
            if (string != null) {
                this.h.setName(string);
                this.a.setTitle(string + "(" + this.h.getPostCount() + ")");
            }
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (!z) {
            return;
        }
        if (this.g.itemCount() == 0) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(8);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_POST_CANCEL_FAVORED) {
            this.g.remove((AbstractPost) (Post) messageEvent.getData());
            int postCount = this.h.getPostCount() - 1;
            if (postCount <= 0) {
                this.i.setVisibility(0);
                postCount = 0;
            }
            this.a.setTitle(this.h.getName() + "(" + postCount + ")");
            cn.xiaochuankeji.tieba.background.favorite.f.a().a(this.h.getId(), postCount);
        }
    }
}
