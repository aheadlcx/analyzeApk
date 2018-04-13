package cn.xiaochuankeji.tieba.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.htjyb.b.a.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.net.a.b;
import cn.xiaochuankeji.tieba.background.post.e;
import cn.xiaochuankeji.tieba.background.post.t;
import cn.xiaochuankeji.tieba.background.post.u;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import org.greenrobot.eventbus.c;
import org.json.JSONObject;
import rx.j;

public class LikedUsersActivity extends h {
    private long d;
    private long e;
    private int f;
    private boolean g;
    private String h;
    private int i;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a j = new cn.xiaochuankeji.tieba.api.ugcvideo.a();

    public static class a {
        public long a;
    }

    public static void a(Context context, long j, long j2, boolean z, int i, String str, int i2) {
        Intent intent = new Intent(context, LikedUsersActivity.class);
        intent.putExtra("kFirstId", j);
        intent.putExtra("kSecondId", j2);
        intent.putExtra("kLikeState", z);
        intent.putExtra("kType", i);
        intent.putExtra("from", str);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, i2);
        context.startActivity(intent);
    }

    public static void a(Context context, long j, boolean z, int i) {
        a(context, j, z, i, null, 0);
    }

    public static void a(Context context, long j, boolean z, int i, String str, int i2) {
        Intent intent = new Intent(context, LikedUsersActivity.class);
        intent.putExtra("kFirstId", j);
        intent.putExtra("kLikeState", z);
        intent.putExtra("kType", i);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("from", str);
        }
        if (i2 != 0) {
            ((Activity) context).startActivityForResult(intent, i2);
        } else {
            context.startActivity(intent);
        }
    }

    protected int a() {
        return R.layout.activity_post_liked_users;
    }

    protected void i_() {
        Bundle extras = getIntent().getExtras();
        this.a.setTitle("顶过的人");
        this.d = extras.getLong("kFirstId");
        this.e = extras.getLong("kSecondId");
        this.f = extras.getInt("kType");
        this.h = extras.getString("from", "");
        this.i = extras.getInt(NotificationCompat.CATEGORY_STATUS, 0);
        this.g = extras.getBoolean("kLikeState");
        a(new a());
        if (this.g) {
            this.a.setOptionText("取消顶");
        } else {
            this.a.setOptionText("取消踩");
        }
    }

    public d<Member> j() {
        if (1 == this.f) {
            return new e(this.d, 0);
        }
        if (2 == this.f) {
            return new e(this.d, this.e);
        }
        if (3 == this.f) {
            return new u(this.d, 1);
        }
        if (4 == this.f) {
            return new u(this.d, 2);
        }
        if (5 == this.f) {
            return new t(this.d, this.h, this.g);
        }
        return null;
    }

    public void t() {
        super.t();
    }

    public void s() {
        k();
        this.a.getOptionText().setEnabled(false);
    }

    private void k() {
        if (1 == this.f) {
            if (this.g) {
                new cn.xiaochuankeji.tieba.background.post.h(this.d, null, new b<JSONObject>(this) {
                    final /* synthetic */ LikedUsersActivity a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_CANCEL_LIKE);
                        messageEvent.setData(Long.valueOf(this.a.d));
                        c.a().d(messageEvent);
                        c.a().d(new cn.xiaochuankeji.tieba.ui.my.liked.a(this.a.d));
                        g.b("取消成功");
                        this.a.finish();
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ LikedUsersActivity a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        g.b("取消失败");
                        this.a.a.getOptionText().setEnabled(true);
                    }
                }).execute();
            } else {
                new cn.xiaochuankeji.tieba.background.post.g(this.d, null, new b<JSONObject>(this) {
                    final /* synthetic */ LikedUsersActivity a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_CANCEL_LIKE);
                        messageEvent.setData(Long.valueOf(this.a.d));
                        c.a().d(messageEvent);
                        g.b("取消成功");
                        this.a.finish();
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ LikedUsersActivity a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        g.b("取消失败");
                        this.a.a.getOptionText().setEnabled(true);
                    }
                }).execute();
            }
        } else if (2 == this.f) {
            if (this.g) {
                cn.xiaochuankeji.tieba.background.review.g.a().b(this.d, this.e, this.h, this.i, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                    final /* synthetic */ LikedUsersActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z, String str) {
                        if (z) {
                            this.a.a(this.a.e);
                            g.b("取消成功");
                            this.a.finish();
                            return;
                        }
                        g.b(str);
                        this.a.a.getOptionText().setEnabled(true);
                    }
                });
            } else {
                cn.xiaochuankeji.tieba.background.review.g.a().d(this.d, this.e, this.h, this.i, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                    final /* synthetic */ LikedUsersActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z, String str) {
                        if (z) {
                            this.a.a(this.a.e);
                            g.b("取消成功");
                            this.a.finish();
                            return;
                        }
                        g.b(str);
                        this.a.a.getOptionText().setEnabled(true);
                    }
                });
            }
        } else if (3 == this.f) {
            v();
        } else if (4 == this.f) {
            w();
        } else if (5 == this.f) {
            com.alibaba.fastjson.JSONObject jSONObject = new com.alibaba.fastjson.JSONObject();
            jSONObject.put("from", this.h);
            jSONObject.put("id", Long.valueOf(this.d));
            jSONObject.put("op", this.g ? "up" : "down");
            jSONObject.put("value", Integer.valueOf(-1));
            ((TaleService) cn.xiaochuankeji.tieba.network.c.b(TaleService.class)).taleThumb(jSONObject).a(rx.a.b.a.a()).a(new rx.e<EmptyJson>(this) {
                final /* synthetic */ LikedUsersActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if (th instanceof ClientErrorException) {
                        g.b(th.getMessage());
                    } else {
                        g.b("取消失败");
                    }
                    this.a.a.getOptionText().setEnabled(true);
                }

                public void a(EmptyJson emptyJson) {
                    g.b("取消成功");
                    this.a.setResult(0, new Intent());
                    this.a.finish();
                }
            });
        }
    }

    private void v() {
        this.j.d(this.d, this.g, this.h).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ LikedUsersActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.b(th.getMessage());
                } else {
                    g.b("取消失败");
                }
                this.a.a.getOptionText().setEnabled(true);
            }

            public void a(EmptyJson emptyJson) {
                g.b("取消成功");
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_UGC_CANCEL_LIKE);
                messageEvent.setData(Long.valueOf(this.a.d));
                c.a().d(messageEvent);
                a aVar = new a();
                aVar.a = this.a.d;
                c.a().d(aVar);
                Intent intent = new Intent();
                intent.putExtra("ugcVideoId", this.a.d);
                this.a.setResult(-1, intent);
                this.a.finish();
            }
        });
    }

    private void w() {
        this.j.c(this.d, this.g, this.h).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ LikedUsersActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.b(th.getMessage());
                } else {
                    g.b("取消失败");
                }
                this.a.a.getOptionText().setEnabled(true);
            }

            public void a(EmptyJson emptyJson) {
                g.b("取消成功");
                a aVar = new a();
                aVar.a = this.a.d;
                c.a().d(aVar);
                Intent intent = new Intent();
                intent.putExtra("ugcVideoId", this.a.d);
                this.a.setResult(-1, intent);
                this.a.finish();
            }
        });
    }

    private void a(long j) {
        cn.xiaochuankeji.tieba.background.d.b bVar = new cn.xiaochuankeji.tieba.background.d.b();
        bVar.a(j);
        c.a().d(bVar);
    }
}
