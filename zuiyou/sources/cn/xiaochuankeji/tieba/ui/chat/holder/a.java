package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public abstract class a extends ViewHolder {
    protected XSession a;
    protected cn.xiaochuankeji.tieba.ui.chat.adapter.a b;
    cn.xiaochuankeji.tieba.ui.chat.a.c c;

    protected class a implements rx.b.b<View> {
        final /* synthetic */ a a;
        private cn.xiaochuankeji.tieba.push.data.a b;
        private Context c;

        public /* synthetic */ void call(Object obj) {
            a((View) obj);
        }

        public a(a aVar, cn.xiaochuankeji.tieba.push.data.a aVar2, Context context) {
            this.a = aVar;
            this.b = aVar2;
            this.c = context;
        }

        public void a(View view) {
            Activity a = cn.xiaochuankeji.tieba.background.utils.b.a(this.c);
            if (a != null) {
                new cn.xiaochuankeji.tieba.ui.chat.a.a(a, new cn.xiaochuankeji.tieba.ui.chat.a.a.a(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                    }

                    public void b() {
                    }

                    public void c() {
                        if (this.a.a.b != null) {
                            this.a.a.b.b(this.a.b);
                        }
                    }

                    public void d() {
                    }
                }).a(this.a.a, this.b, view);
            }
        }
    }

    protected class b implements rx.b.b<Void> {
        final /* synthetic */ a a;
        private long b;
        private long c;
        private String d;
        private int e;

        public /* synthetic */ void call(Object obj) {
            a((Void) obj);
        }

        public b(a aVar, int i, long j, long j2, String str) {
            this.a = aVar;
            this.b = j;
            this.c = j2;
            this.d = str;
            this.e = i;
        }

        public void a(Void voidR) {
            if (this.e == 1 && this.b > 1999) {
                Member member = new Member(this.b);
                member.setAvatarID(this.c);
                member.setName(this.d);
                if (this.a.itemView != null && this.a.itemView.getContext() != null) {
                    MemberDetailActivity.a(this.a.itemView.getContext(), this.b, 0, 3, 0);
                }
            }
        }
    }

    protected class c implements rx.b.b<Void> {
        final /* synthetic */ a a;
        private String b;
        private String c;

        public /* synthetic */ void call(Object obj) {
            a((Void) obj);
        }

        public c(a aVar, String str, String str2) {
            this.a = aVar;
            this.b = str;
            this.c = str2;
        }

        public void a(Void voidR) {
            if (!TextUtils.isEmpty(this.c) && this.a.itemView != null && this.a.itemView.getContext() != null) {
                WebActivity.a(this.a.itemView.getContext(), cn.xiaochuan.jsbridge.b.a(this.b, this.c));
            }
        }
    }

    public abstract void a(cn.xiaochuankeji.tieba.push.data.a aVar, int i);

    public a(@NonNull View view) {
        super(view);
        ButterKnife.a(this, this.itemView);
    }

    public a(ViewGroup viewGroup, @LayoutRes int i) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    public static a a(ViewGroup viewGroup, int i, XSession xSession, cn.xiaochuankeji.tieba.ui.chat.a.c cVar, cn.xiaochuankeji.tieba.ui.chat.adapter.a aVar) {
        a imageHolder;
        switch (i) {
            case R.layout.view_item_chat_image:
                imageHolder = new ImageHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_link:
                imageHolder = new LinkHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_post:
                imageHolder = new PostHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_self_image:
                imageHolder = new SelfImageHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_self_txt:
                imageHolder = new SelfTextHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_self_voice:
                imageHolder = new SelfVoiceHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_tale:
                imageHolder = new TaleHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_timeline:
                imageHolder = new TimeHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_topic:
                imageHolder = new TopicHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_txt:
                imageHolder = new TextHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_ugc:
                imageHolder = new UgcHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_user:
                imageHolder = new UserHolder(viewGroup, i);
                break;
            case R.layout.view_item_chat_voice:
                imageHolder = new VoiceHolder(viewGroup, i);
                break;
            default:
                imageHolder = new UnSupportHolder(viewGroup, i);
                break;
        }
        imageHolder.a(xSession);
        imageHolder.a(cVar);
        imageHolder.a(aVar);
        return imageHolder;
    }

    private void a(cn.xiaochuankeji.tieba.ui.chat.a.c cVar) {
        this.c = cVar;
    }

    private void a(cn.xiaochuankeji.tieba.ui.chat.adapter.a aVar) {
        this.b = aVar;
    }

    protected void a(cn.xiaochuankeji.tieba.push.data.a aVar, int i, WebImageView webImageView) {
        cn.xiaochuankeji.tieba.push.data.a a = a(i);
        if (a == null || a.a != aVar.a) {
            webImageView.setVisibility(0);
            if (aVar.a == cn.xiaochuankeji.tieba.background.a.g().c()) {
                aVar.c = cn.xiaochuankeji.tieba.background.a.g().q().getAvatarID();
            }
            webImageView.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(aVar.a, aVar.c));
            return;
        }
        webImageView.setVisibility(4);
    }

    protected void a(final WebImageView webImageView, String str, final long j, String str2, float f, float f2) {
        Resources resources = webImageView.getResources();
        boolean equalsIgnoreCase = "gif".equalsIgnoreCase(str);
        RectF a = cn.xiaochuankeji.tieba.background.f.b.a(f, f2);
        LayoutParams layoutParams = webImageView.getLayoutParams();
        layoutParams.width = (int) i.a(resources, a.width());
        layoutParams.height = (int) i.a(resources, a.height());
        webImageView.setLayoutParams(layoutParams);
        if (j > 0) {
            webImageView.setData(a(equalsIgnoreCase, j));
            a((View) webImageView, new rx.b.b<Void>(this) {
                final /* synthetic */ a c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    ArrayList arrayList = new ArrayList();
                    LinkedList a = this.c.b.a();
                    int i = 0;
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        cn.xiaochuankeji.tieba.push.data.a aVar = (cn.xiaochuankeji.tieba.push.data.a) a.get(i2);
                        if (aVar.g == 2) {
                            Object a2 = this.c.a(aVar.f);
                            if (a2 instanceof JSONObject) {
                                JSONObject jSONObject = (JSONObject) a2;
                                long longValue = jSONObject.getLongValue("id");
                                cn.htjyb.b.a a3 = this.c.a("gif".equalsIgnoreCase(jSONObject.getString("fmt")), longValue);
                                if (longValue == j) {
                                    i = arrayList.size();
                                }
                                arrayList.add(a3);
                            }
                        }
                    }
                    MediaBrowseActivity.a(webImageView.getContext(), i, null, arrayList, false, EntranceType.Chat);
                }
            });
        } else if (!TextUtils.isEmpty(str2)) {
            webImageView.setImagePath(str2);
        }
    }

    private cn.htjyb.b.a a(boolean z, long j) {
        Type type;
        if (z) {
            type = Type.kGif;
        } else {
            type = Type.kCommentOriginImg;
        }
        return cn.xiaochuankeji.tieba.background.a.f().a(type, j);
    }

    protected void a(View view, rx.b.b<Void> bVar) {
        com.jakewharton.a.b.a.a(view).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a((rx.b.b) bVar);
    }

    protected void b(final View view, final rx.b.b<View> bVar) {
        final GestureDetector gestureDetector = new GestureDetector(view.getContext(), new SimpleOnGestureListener(this) {
            final /* synthetic */ a c;

            public void onLongPress(MotionEvent motionEvent) {
                if (bVar != null) {
                    bVar.call(view);
                }
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (view != null) {
                    view.performClick();
                }
                return true;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }
        });
        view.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ a b;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    protected Object a(String str) {
        try {
            str = JSON.parseObject(str);
        } catch (Exception e) {
            com.izuiyou.a.a.b.c(str);
            e.printStackTrace();
        }
        return str;
    }

    private void a(XSession xSession) {
        this.a = xSession;
    }

    protected void a(long j, View view) {
        int a = (int) i.a(view.getResources(), ((((float) j) / 60000.0f) * 89.0f) + 80.0f);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = a;
        view.setLayoutParams(layoutParams);
    }

    protected cn.xiaochuankeji.tieba.push.data.a a(int i) {
        if (i == 0 || this.b == null) {
            return null;
        }
        return this.b.a(i - 1);
    }
}
