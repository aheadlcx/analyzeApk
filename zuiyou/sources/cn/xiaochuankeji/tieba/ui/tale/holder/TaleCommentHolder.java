package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.recommend.widget.MultiDraweeView;
import cn.xiaochuankeji.tieba.ui.tale.TaleDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleThumbUsersActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.e;
import rx.j;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class TaleCommentHolder extends b {
    @BindView
    WebImageView avatar;
    private TaleComment c;
    @BindView
    MultiDraweeView image;
    @BindView
    ImageView iv_owner;
    @BindView
    CommentItemUpDownView like;
    @BindView
    AppCompatTextView name;
    @BindView
    ExpandableTextView review;
    @BindView
    AppCompatTextView time;

    public TaleCommentHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(final TaleComment taleComment, int i) {
        this.c = taleComment;
        if (taleComment.type == 1) {
            this.itemView.setBackgroundColor(a.a().a((int) R.color.CB_1));
        } else {
            this.itemView.setBackgroundColor(a.a().a((int) R.color.CB));
        }
        this.name.setText(d.b(taleComment.author.name));
        this.time.setText(h.a(taleComment.ct * 1000));
        this.avatar.setWebImage(b.a(taleComment.author.id, taleComment.author.avatar));
        this.avatar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TaleCommentHolder b;

            public void onClick(View view) {
                MemberDetailActivity.a(this.b.itemView.getContext(), taleComment.author.id);
            }
        });
        TaleDetail f = ((TaleDetailActivity) this.itemView.getContext()).f(taleComment.articleId);
        if (f == null || f.mid != taleComment.mid) {
            this.iv_owner.setVisibility(4);
        } else {
            this.iv_owner.setVisibility(0);
        }
        this.like.a(taleComment.liked, taleComment.likeCnt, new CommentItemUpDownView.a(this) {
            final /* synthetic */ TaleCommentHolder b;

            public void a(boolean z) {
                TaleThumbUsersActivity.a(this.b.itemView.getContext(), taleComment.id, 2, z, FFmpegMediaMetadataRetriever.METADATA_KEY_COMMENT);
            }

            public void a(int i, int i2, boolean z) {
                if (z) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", Long.valueOf(taleComment.id));
                    jSONObject.put("op", i == 1 ? "up" : "down");
                    jSONObject.put("value", Integer.valueOf(1));
                    taleComment.liked = i;
                    TaleComment taleComment = taleComment;
                    taleComment.likeCnt = (i == 1 ? 1 : 0) + taleComment.likeCnt;
                    ((TaleService) c.b(TaleService.class)).taleCommentThumb(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((EmptyJson) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            g.b(th.getMessage());
                        }

                        public void a(EmptyJson emptyJson) {
                        }
                    });
                }
            }
        });
        final List list = taleComment.imgs;
        if (list == null || list.isEmpty()) {
            this.image.setOnItemClickListener(null);
            this.image.setVisibility(8);
        } else {
            final List arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(b.b(((Long) list.get(i2)).longValue()).b());
            }
            this.image.setVisibility(0);
            this.image.setImageUris(arrayList);
            this.image.setOnItemClickListener(new MultiDraweeView.a(this) {
                final /* synthetic */ TaleCommentHolder d;

                public void a(int i, Rect rect) {
                    if (i >= arrayList.size() || i < 0) {
                        org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.d(taleComment));
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        arrayList.add(cn.xiaochuankeji.tieba.background.a.f().a(Type.kCommentOriginImg, ((Long) list.get(i2)).longValue()));
                    }
                    MediaBrowseActivity.a(this.d.itemView.getContext(), i, null, arrayList, false, EntranceType.CommentImage);
                }

                public void a() {
                    if ((cn.xiaochuankeji.tieba.background.a.g().c() == taleComment.mid ? 1 : null) != null) {
                        this.d.a(taleComment);
                    }
                }
            });
        }
        HashMap e = ((TaleDetailActivity) this.itemView.getContext()).e();
        f fVar = (f) e.get(Long.valueOf(taleComment.id));
        if (fVar == null) {
            fVar = new f();
            e.put(Long.valueOf(taleComment.id), fVar);
        }
        this.review.setTextMaxLine(8);
        final TaleAuthor taleAuthor = taleComment.originAuthor;
        if (taleAuthor != null) {
            this.review.setVisibility(0);
            this.review.a(taleComment.txt, d.b(taleAuthor.name), fVar);
        } else if (TextUtils.isEmpty(taleComment.txt)) {
            this.review.setVisibility(8);
        } else {
            this.review.setVisibility(0);
            this.review.a(taleComment.txt, null, fVar);
        }
        this.review.setOnSingleClickListener(new ExpandableTextView.c(this) {
            final /* synthetic */ TaleCommentHolder c;

            public void a(boolean z) {
                if (!z) {
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.d(taleComment));
                } else if (taleAuthor != null) {
                    MemberDetailActivity.a(this.c.itemView.getContext(), taleAuthor.id);
                }
            }
        });
        this.review.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ TaleCommentHolder b;

            public boolean onLongClick(View view) {
                if (!(cn.xiaochuankeji.tieba.background.a.g().c() == taleComment.mid)) {
                    return false;
                }
                this.b.a(taleComment);
                return true;
            }
        });
        this.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TaleCommentHolder b;

            public void onClick(View view) {
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.d(taleComment));
            }
        });
        this.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ TaleCommentHolder b;

            public boolean onLongClick(View view) {
                if (!(cn.xiaochuankeji.tieba.background.a.g().c() == taleComment.mid)) {
                    return false;
                }
                this.b.a(taleComment);
                return true;
            }
        });
    }

    private void a(final TaleComment taleComment) {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.itemView.getContext(), new SDBottomSheet.b(this) {
            final /* synthetic */ TaleCommentHolder b;

            public void a(int i) {
                if (i == 6) {
                    d.a(taleComment.txt);
                    g.a("复制成功");
                }
                if (i == 9) {
                    cn.xiaochuankeji.tieba.ui.widget.f.a("提示", "确定要删除？", (Activity) this.b.itemView.getContext(), new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("id", Long.valueOf(taleComment.id));
                                jSONObject.put("from", "article");
                                ((TaleService) c.b(TaleService.class)).deleteComments(jSONObject).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public /* synthetic */ void onNext(Object obj) {
                                        a((EmptyJson) obj);
                                    }

                                    public void onCompleted() {
                                    }

                                    public void onError(Throwable th) {
                                        g.a(th.getMessage());
                                    }

                                    public void a(EmptyJson emptyJson) {
                                        org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.c(taleComment));
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        SDBottomSheet.c cVar = new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6);
        SDBottomSheet.c cVar2 = new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9);
        arrayList.add(cVar);
        arrayList.add(cVar2);
        sDBottomSheet.a(arrayList, null);
        sDBottomSheet.b();
    }

    @l(a = ThreadMode.MAIN)
    public void updateCommentState(cn.xiaochuankeji.tieba.b.e eVar) {
        if (this.c.id == eVar.b) {
            if (eVar.a == 3) {
                if (this.like != null) {
                    this.like.a();
                }
            } else if (eVar.a == 2) {
                if (this.like != null) {
                    this.like.a();
                }
                if (this.c != null) {
                    this.c.liked = 0;
                }
            }
        }
    }

    public void a() {
        super.a();
        org.greenrobot.eventbus.c.a().a(this);
    }

    public void b() {
        super.b();
        org.greenrobot.eventbus.c.a().c(this);
    }
}
