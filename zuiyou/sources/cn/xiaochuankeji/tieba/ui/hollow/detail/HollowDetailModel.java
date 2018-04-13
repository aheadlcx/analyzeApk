package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.background.utils.f;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowDetailJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowMsgJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowMsgListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MemberDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.List;
import org.greenrobot.eventbus.c;
import rx.b.g;
import rx.d;
import rx.d$a;
import rx.j;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;

public class HollowDetailModel extends o {
    private c a;
    private cn.xiaochuankeji.tieba.api.hollow.a b = new cn.xiaochuankeji.tieba.api.hollow.a();
    private long c;
    @SuppressLint({"StaticFieldLeak"})
    private Activity d;

    interface a {
        void a(Throwable th);

        void a(boolean z, String str);
    }

    interface b {
        void a(RoomDataBean roomDataBean, MemberDataBean memberDataBean, MemberDataBean memberDataBean2);
    }

    void a(c cVar) {
        this.a = cVar;
    }

    void a(long j, long j2, String str, final b bVar, final a aVar) {
        com.izuiyou.a.a.b.c("HollowPublishTest -> + roomId : " + j);
        this.c = j2;
        this.b.a(j, j2, str).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<HollowDetailJson>(this) {
            final /* synthetic */ HollowDetailModel c;

            public /* synthetic */ void call(Object obj) {
                a((HollowDetailJson) obj);
            }

            public void a(HollowDetailJson hollowDetailJson) {
                boolean z = true;
                com.izuiyou.a.a.b.c("HollowPublishTest -> + hollowDetailJson : " + JSON.toJSONString(hollowDetailJson));
                c a = this.c.a;
                List list = hollowDetailJson.roomMsgList;
                RoomDataBean roomDataBean = hollowDetailJson.room;
                boolean z2 = hollowDetailJson.roomMsgList == null || hollowDetailJson.roomMsgList.isEmpty();
                a.a(list, roomDataBean, z2);
                a aVar = aVar;
                if (hollowDetailJson.more != 1) {
                    z = false;
                }
                aVar.a(z, hollowDetailJson.nextCb);
                bVar.a(hollowDetailJson.room, hollowDetailJson.room.member, hollowDetailJson.member);
                this.c.a(hollowDetailJson.room);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                if (th instanceof ClientErrorException) {
                    aVar.a(th);
                } else {
                    aVar.a(new Throwable("网络错误"));
                }
                com.izuiyou.a.a.b.e("HollowPublishTest -> + throwable : " + th.getMessage());
            }
        });
    }

    void a(long j, String str, final a aVar) {
        this.b.a(j, str).d(new g<HollowMsgListJson, HollowMsgListJson>(this) {
            final /* synthetic */ HollowDetailModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((HollowMsgListJson) obj);
            }

            public HollowMsgListJson a(HollowMsgListJson hollowMsgListJson) {
                if (this.a.c != 0) {
                    for (MsgDataBean msgDataBean : hollowMsgListJson.msgList) {
                        if (msgDataBean.id == this.a.c) {
                            hollowMsgListJson.msgList.remove(msgDataBean);
                            break;
                        }
                    }
                }
                return hollowMsgListJson;
            }
        }).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<HollowMsgListJson>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void call(Object obj) {
                a((HollowMsgListJson) obj);
            }

            public void a(HollowMsgListJson hollowMsgListJson) {
                boolean z = true;
                com.izuiyou.a.a.b.c(" hollowMsgListJson : " + JSON.toJSONString(hollowMsgListJson));
                this.b.a.a(hollowMsgListJson.msgList);
                a aVar = aVar;
                if (hollowMsgListJson.more != 1) {
                    z = false;
                }
                aVar.a(z, hollowMsgListJson.next_cb);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                if (th instanceof ClientErrorException) {
                    aVar.a(th);
                } else {
                    aVar.a(new Throwable("网络错误"));
                }
                com.izuiyou.a.a.b.e(th);
            }
        });
    }

    void a(Activity activity, long j, String str, long j2, String str2, cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar) {
        this.d = activity;
        cn.xiaochuankeji.tieba.ui.widget.g.a(activity, "正在发表评论");
        if (str2 == null) {
            a(j, str, null, bVar);
            return;
        }
        final String str3 = str2;
        final long j3 = j;
        final String str4 = str;
        final long j4 = j2;
        final cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar2 = bVar;
        d.b(new d$a<String>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super String> jVar) {
                File file = new File(cn.xiaochuankeji.tieba.background.a.e().v(), "audio");
                if (!file.exists()) {
                    file.mkdir();
                }
                String absolutePath = new File(file, "final.aac").getAbsolutePath();
                FFmpegMainCaller.wavToAac(str3, absolutePath);
                jVar.onNext(absolutePath);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<String>(this) {
            final /* synthetic */ HollowDetailModel e;

            public /* synthetic */ void call(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                this.e.a(j3, str4, j4, str, bVar2);
            }
        });
    }

    private void a(long j, String str, long j2, String str2, cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar) {
        final long j3 = j2;
        final long j4 = j;
        final String str3 = str;
        final cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar2 = bVar;
        new f(str2, "aac", new cn.xiaochuankeji.tieba.background.utils.f.a(this) {
            final /* synthetic */ HollowDetailModel e;

            public void a(boolean z, String str, String str2) {
                if (z) {
                    AudioDataBean audioDataBean = new AudioDataBean();
                    audioDataBean.dur = j3;
                    audioDataBean.uri = str;
                    this.e.a(j4, str3, audioDataBean, bVar2);
                    return;
                }
                com.izuiyou.a.a.b.e("CreateMsg -> " + str2);
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.e.d);
                cn.xiaochuankeji.tieba.background.utils.g.a("语音上传失败，请重试");
            }
        }).a();
    }

    private void a(long j, String str, AudioDataBean audioDataBean, final cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar) {
        com.izuiyou.a.a.b.c("CreateMsg -> roomId : " + j + "  text : " + str + "  audioData : " + audioDataBean);
        this.b.a(j, System.currentTimeMillis(), str, audioDataBean).b(rx.f.a.a()).a(rx.a.b.a.a()).b(new j<HollowMsgJson>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((HollowMsgJson) obj);
            }

            public void onCompleted() {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.b.d);
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.b.d);
                com.izuiyou.a.a.b.e("CreateMsg -> " + th.getMessage());
                if (th instanceof ClientErrorException) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("发布失败：" + ((ClientErrorException) th).errMessage());
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("发布失败，请重试");
                }
                bVar.b();
            }

            public void a(HollowMsgJson hollowMsgJson) {
                com.izuiyou.a.a.b.c("CreateMsg -> " + JSON.toJSONString(hollowMsgJson));
                cn.xiaochuankeji.tieba.background.utils.g.a("评论发表成功");
                bVar.a();
                this.b.a.a(hollowMsgJson.message);
                RoomDataBean a = this.b.a.a();
                a.msgCount++;
                this.b.a(a);
                c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.b(hollowMsgJson.message));
                if (this.b.d instanceof HollowDetailActivity) {
                    ((HollowDetailActivity) this.b.d).e();
                }
            }
        });
    }

    void a(final Activity activity, final long j) {
        cn.xiaochuankeji.tieba.ui.widget.g.a(activity, "正在删除评论");
        this.b.b(j).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HollowDetailModel c;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(activity);
                this.c.a.a(j);
                cn.xiaochuankeji.tieba.background.utils.g.a("删除成功");
                c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.d(j));
                RoomDataBean a = this.c.a.a();
                a.msgCount--;
                this.c.a(a);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(activity);
                if (th instanceof ClientErrorException) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("删除失败" + th.getMessage());
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("操作失败，请重试");
                }
                com.izuiyou.a.a.b.e(th);
            }
        });
    }

    void b(final Activity activity, final long j) {
        cn.xiaochuankeji.tieba.ui.widget.g.a(activity, "正在删除树洞");
        this.b.a(j).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HollowDetailModel c;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                cn.xiaochuankeji.tieba.background.utils.g.a("删除成功");
                cn.xiaochuankeji.tieba.ui.widget.g.c(activity);
                activity.finish();
                c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.c(j));
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HollowDetailModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(activity);
                if (th instanceof ClientErrorException) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("删除失败" + th.getMessage());
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("操作失败，请重试");
                }
                com.izuiyou.a.a.b.e(th);
            }
        });
    }

    private void a(RoomDataBean roomDataBean) {
        c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.g(RoomDataBean.a(roomDataBean)));
    }
}
