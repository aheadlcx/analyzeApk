package cn.xiaochuankeji.tieba.ui.tale.viewmodel;

import android.arch.lifecycle.o;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.tale.FollowPostDetailJson;
import cn.xiaochuankeji.tieba.json.tale.TaleCommentListJson;
import cn.xiaochuankeji.tieba.json.tale.TaleRepliesJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.tale.TaleDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.b;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import rx.a.b.a;
import rx.e;

public class TaleDetailModel extends o {
    private b a;
    private a b;
    private String c;
    private boolean d = true;
    private long e;
    private int f;
    private long g;
    private long h;

    public void a(b bVar, String str, long j) {
        this.a = bVar;
        this.e = j;
    }

    public void a(int i) {
        this.f = i;
    }

    public void b() {
        this.c = null;
        this.h = 0;
    }

    public void a(TaleDetailActivity taleDetailActivity, String str, long j, long j2) {
        this.g = j;
        this.h = j2;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(this.e));
        jSONObject.put("from", str);
        final TaleDetailActivity taleDetailActivity2 = taleDetailActivity;
        final String str2 = str;
        final long j3 = j;
        final long j4 = j2;
        ((TaleService) c.b(TaleService.class)).detail(jSONObject).a(a.a()).a(new e<FollowPostDetailJson>(this) {
            final /* synthetic */ TaleDetailModel e;

            public /* synthetic */ void onNext(Object obj) {
                a((FollowPostDetailJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                g.a(th.getMessage());
                if (this.e.b != null) {
                    this.e.b.a(false, "网络不给力哦~", 0, false);
                }
            }

            public void a(FollowPostDetailJson followPostDetailJson) {
                TaleDetail taleDetail = followPostDetailJson.detail;
                this.e.a.c();
                if (taleDetailActivity2 != null) {
                    taleDetailActivity2.b(taleDetail);
                }
                this.e.a(taleDetail, str2);
                this.e.c = null;
                this.e.a.b = null;
                if (taleDetail.commentCnt > 0) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("comment_id", Long.valueOf(j3));
                    jSONObject.put("start_id", Long.valueOf(j4));
                    this.e.b(true, jSONObject);
                } else if (this.e.b != null) {
                    this.e.b.a(false, "", 0, false);
                }
            }
        });
    }

    private void b(final boolean z, JSONObject jSONObject) {
        ((TaleService) c.b(TaleService.class)).getReplies(jSONObject).a(a.a()).a(new e<TaleRepliesJson>(this) {
            final /* synthetic */ TaleDetailModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((TaleRepliesJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                g.a(th.getMessage());
                TaleComment taleComment = new TaleComment();
                taleComment.layoutType = R.layout.item_tale_detail_more;
                taleComment.id = this.b.e;
                this.b.a.a(taleComment);
                if (z) {
                    if (this.b.b != null) {
                        this.b.b.a(false, "网络不给力哦~", 0, false);
                    }
                } else if (this.b.b != null) {
                    this.b.b.a(false, this.b.d, "网络不给力哦~");
                }
            }

            public void a(TaleRepliesJson taleRepliesJson) {
                TaleComment taleComment;
                this.b.c = taleRepliesJson.cursor;
                this.b.a.b = this.b.c;
                this.b.d = taleRepliesJson.more;
                List<TaleComment> list = taleRepliesJson.list;
                if (list != null) {
                    for (TaleComment taleComment2 : list) {
                        taleComment2.type = 1;
                    }
                }
                if (!z) {
                    if (this.b.b != null) {
                        this.b.b.a(true, this.b.d, "");
                    }
                    if (list != null) {
                        if (!this.b.d) {
                            taleComment2 = new TaleComment();
                            taleComment2.layoutType = R.layout.item_tale_detail_more;
                            taleComment2.id = this.b.e;
                            list.add(taleComment2);
                        }
                        this.b.a.a((List) list);
                    }
                } else if (list != null) {
                    if (taleRepliesJson.comment != null) {
                        list.add(0, taleRepliesJson.comment);
                    }
                    if (!this.b.d) {
                        taleComment2 = new TaleComment();
                        taleComment2.layoutType = R.layout.item_tale_detail_more;
                        taleComment2.id = this.b.e;
                        list.add(taleComment2);
                    }
                    this.b.a.b((List) list);
                    if (this.b.b != null) {
                        this.b.b.a(true, "", 0, taleRepliesJson.more);
                    }
                } else if (this.b.b != null) {
                    this.b.b.a(false, "", 0, taleRepliesJson.more);
                }
            }
        });
    }

    public void a(TaleDetailActivity taleDetailActivity, String str, long j) {
        this.h = j;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(this.e));
        jSONObject.put("from", str);
        final TaleDetailActivity taleDetailActivity2 = taleDetailActivity;
        final String str2 = str;
        final long j2 = j;
        ((TaleService) c.b(TaleService.class)).detail(jSONObject).a(a.a()).a(new e<FollowPostDetailJson>(this) {
            final /* synthetic */ TaleDetailModel d;

            public /* synthetic */ void onNext(Object obj) {
                a((FollowPostDetailJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                g.a(th.getMessage());
                if (this.d.b != null) {
                    this.d.b.a(false, "网络不给力哦~", 0, false);
                }
            }

            public void a(FollowPostDetailJson followPostDetailJson) {
                TaleDetail taleDetail = followPostDetailJson.detail;
                this.d.a.c();
                if (taleDetailActivity2 != null) {
                    taleDetailActivity2.b(taleDetail);
                }
                this.d.a(taleDetail, str2);
                this.d.c = null;
                this.d.a.b = null;
                if (taleDetail.commentCnt > 0) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", Long.valueOf(this.d.e));
                    if (this.d.f == 1) {
                        jSONObject.put("exclude_reply", Integer.valueOf(1));
                    }
                    jSONObject.put("start_id", Long.valueOf(j2));
                    this.d.a(true, jSONObject);
                } else if (this.d.b != null) {
                    this.d.b.a(false, "", 0, false);
                }
            }
        });
    }

    public void a(final boolean z, JSONObject jSONObject) {
        ((TaleService) c.b(TaleService.class)).getComments(jSONObject).a(a.a()).a(new e<TaleCommentListJson>(this) {
            final /* synthetic */ TaleDetailModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((TaleCommentListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                if (z) {
                    if (this.b.b != null) {
                        this.b.b.a(false, "网络不给力哦~", 0, false);
                    }
                } else if (this.b.b != null) {
                    this.b.b.a(false, this.b.d, "网络不给力哦~");
                }
            }

            public void a(TaleCommentListJson taleCommentListJson) {
                this.b.c = taleCommentListJson.cursor;
                this.b.a.b = this.b.c;
                this.b.d = taleCommentListJson.more;
                List list = taleCommentListJson.comments;
                TaleComment taleComment;
                if (!z) {
                    if (this.b.b != null) {
                        this.b.b.a(true, this.b.d, "");
                    }
                    if (list != null) {
                        if (!this.b.d && this.b.f == 1) {
                            taleComment = new TaleComment();
                            taleComment.layoutType = R.layout.item_tale_detail_more;
                            taleComment.id = this.b.e;
                            list.add(taleComment);
                        }
                        this.b.a.a(list);
                    }
                } else if (list != null) {
                    if (!this.b.d && this.b.f == 1) {
                        taleComment = new TaleComment();
                        taleComment.layoutType = R.layout.item_tale_detail_more;
                        taleComment.id = this.b.e;
                        list.add(taleComment);
                    }
                    this.b.a.b(list);
                    if (this.b.b != null) {
                        this.b.b.a(true, "", 0, taleCommentListJson.more);
                    }
                } else if (this.b.b != null) {
                    this.b.b.a(false, "", 0, taleCommentListJson.more);
                }
            }
        });
    }

    private void a(TaleDetail taleDetail, String str) {
        List arrayList = new ArrayList();
        TaleComment taleComment = new TaleComment();
        taleComment.layoutType = R.layout.board_author;
        taleComment.author = taleDetail.author;
        arrayList.add(taleComment);
        if (taleDetail.is_folded == 1) {
            taleComment = new TaleComment();
            taleComment.layoutType = R.layout.item_tale_detail_tip;
            arrayList.add(taleComment);
        }
        List arrayList2 = new ArrayList();
        JSONArray parseArray = JSONArray.parseArray(taleDetail.content);
        for (int i = 0; i < parseArray.size(); i++) {
            JSONObject jSONObject = parseArray.getJSONObject(i);
            String string = jSONObject.getString("type");
            TaleComment taleComment2 = new TaleComment();
            RichTextEditor.a aVar = new RichTextEditor.a();
            if ("txt".equals(string)) {
                aVar.a = "txt";
                aVar.b = jSONObject.getString("txt");
                taleComment2.layoutType = R.layout.board_text;
            } else if ("img".equals(string)) {
                aVar.a = "img";
                aVar.c = jSONObject.getLongValue("id");
                aVar.d = jSONObject.getIntValue("w");
                aVar.e = jSONObject.getIntValue("h");
                aVar.f = jSONObject.getString("fmt");
                arrayList2.add(aVar);
                if (((float) aVar.e) / ((float) aVar.d) > 1.7f) {
                    taleComment2.layoutType = R.layout.item_tale_detail_long_image;
                } else {
                    taleComment2.layoutType = R.layout.item_tale_detail_web_image;
                }
            } else {
                aVar.a = "unsup";
                taleComment2.layoutType = R.layout.board_unsupport;
            }
            taleComment2.tale = aVar;
            arrayList.add(taleComment2);
        }
        this.a.c(arrayList2);
        taleComment = new TaleComment();
        taleComment.layoutType = R.layout.item_tale_detail_social;
        taleComment.detail = taleDetail;
        arrayList.add(taleComment);
        taleComment = new TaleComment();
        taleComment.layoutType = R.layout.item_tale_detail_comment_text;
        taleComment.detail = taleDetail;
        arrayList.add(taleComment);
        this.a.a(arrayList);
        LinkedList a = this.a.a();
        int i2 = 0;
        while (i2 < a.size()) {
            if (((TaleComment) a.get(i2)).layoutType == R.layout.item_tale_detail_comment_empty) {
                break;
            }
            i2++;
        }
        i2 = -1;
        if (i2 == -1 && taleDetail.commentCnt < 1) {
            taleComment = new TaleComment();
            taleComment.layoutType = R.layout.item_tale_detail_comment_empty;
            taleComment.detail = taleDetail;
            this.a.a(taleComment);
        } else if (i2 != -1 && taleDetail.commentCnt > 0) {
            this.a.b(i2);
        }
    }

    public void c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(this.e));
        if (!TextUtils.isEmpty(this.c)) {
            jSONObject.put("cursor", this.c);
        }
        if (this.f == 1) {
            jSONObject.put("exclude_reply", Integer.valueOf(1));
        }
        jSONObject.put("start_id", Long.valueOf(this.h));
        a(false, jSONObject);
    }

    public void d() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("comment_id", Long.valueOf(this.g));
        jSONObject.put("start_id", Long.valueOf(this.h));
        if (!TextUtils.isEmpty(this.c)) {
            jSONObject.put("cursor", this.c);
        }
        b(false, jSONObject);
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
