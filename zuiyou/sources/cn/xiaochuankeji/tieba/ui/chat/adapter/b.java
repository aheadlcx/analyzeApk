package cn.xiaochuankeji.tieba.ui.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.push.data.c;
import cn.xiaochuankeji.tieba.ui.chat.holder.NotifyHolder;
import cn.xiaochuankeji.tieba.ui.hollow.detail.HollowDetailActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity.SubcommentFilter;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity.CommentFilter;
import cn.xiaochuankeji.tieba.ui.tale.TaleDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleListActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.b.g;
import rx.e;

public class b extends Adapter<NotifyHolder> {
    private LinkedList<c> a = new LinkedList();

    private class a extends ClickableSpan {
        final /* synthetic */ b a;
        private long b;
        private String c;

        a(b bVar, long j, String str) {
            this.a = bVar;
            this.b = j;
            this.c = str;
        }

        public void onClick(View view) {
            MemberDetailActivity.a(view.getContext(), this.b);
        }

        public void updateDrawState(TextPaint textPaint) {
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((NotifyHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public NotifyHolder a(ViewGroup viewGroup, int i) {
        return new NotifyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_notify, viewGroup, false));
    }

    public void a(NotifyHolder notifyHolder, int i) {
        final c cVar = (c) this.a.get(i);
        final Context context = notifyHolder.itemView.getContext();
        JSONArray jSONArray = cVar.z;
        if (jSONArray.size() > 0) {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            final long longValue = jSONObject.getLongValue("id");
            notifyHolder.avatar.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(longValue, jSONObject.getLongValue("avatar")));
            com.jakewharton.a.b.a.a(notifyHolder.avatar).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
                final /* synthetic */ b c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    MemberDetailActivity.a(context, longValue);
                }
            });
        }
        if (cVar.b == 30 || cVar.b == 130) {
            notifyHolder.avatar.setImageResource(R.drawable.avatar_tree);
            com.jakewharton.a.b.a.a(notifyHolder.avatar).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
                final /* synthetic */ b c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    HollowDetailActivity.a(context, cVar.a, "my_xmsg");
                }
            });
        }
        if (cVar.b == 131) {
            notifyHolder.avatar.setImageResource(R.drawable.avatar_tree);
            com.jakewharton.a.b.a.a(notifyHolder.avatar).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
                final /* synthetic */ b c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    HollowDetailActivity.a(context, cVar.c, cVar.a);
                }
            });
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int size;
        int i2;
        if (!cVar.a() || jSONArray.isEmpty()) {
            CharSequence charSequence;
            size = jSONArray.size();
            int i3 = 0;
            int i4 = 0;
            while (i3 < size) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                long longValue2 = jSONObject2.getLongValue("id");
                String string = jSONObject2.getString("name");
                if (string != null) {
                    int length = spannableStringBuilder.length();
                    i2 = i4 + 1;
                    if (i2 > 2) {
                        spannableStringBuilder.append(" 等人");
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(c.a.d.a.a.a().a((int) R.color.CT_5)), length, spannableStringBuilder.length(), 33);
                        break;
                    }
                    SpannableStringBuilder append = spannableStringBuilder.append(String.valueOf(string));
                    charSequence = (size <= 1 || i3 != 0) ? " " : "、";
                    append.append(charSequence);
                    spannableStringBuilder.setSpan(new a(this, longValue2, string), length, spannableStringBuilder.length(), 17);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(c.a.d.a.a.a().a((int) R.color.CT_1)), length, spannableStringBuilder.length(), 33);
                    i4 = i2;
                }
                i3++;
            }
            i2 = spannableStringBuilder.length();
            if (cVar.p) {
                charSequence = " [视频]";
            } else if (cVar.n) {
                charSequence = " [语音]";
            } else if (cVar.o) {
                charSequence = " [图片]";
            } else {
                charSequence = cVar.q;
            }
            if (!TextUtils.isEmpty(charSequence) && charSequence.trim().length() > 0) {
                if (size > 0) {
                    spannableStringBuilder.append(": ");
                }
                spannableStringBuilder.append(charSequence);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(c.a.d.a.a.a().a((int) R.color.CT_5)), i2, spannableStringBuilder.length(), 33);
            }
        } else {
            jSONObject = jSONArray.getJSONObject(0);
            longValue = jSONObject.getLongValue("id");
            Object string2 = jSONObject.getString("name");
            int length2 = spannableStringBuilder.length();
            size = string2.length() + length2;
            spannableStringBuilder.append(string2);
            spannableStringBuilder.setSpan(new a(this, longValue, string2), length2, size, 17);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(c.a.d.a.a.a().a((int) R.color.CT_1));
            i2 = spannableStringBuilder.length();
            spannableStringBuilder.setSpan(foregroundColorSpan, length2, i2, 33);
            if (cVar.p) {
                spannableStringBuilder.append(": [视频]");
            } else if (cVar.n) {
                spannableStringBuilder.append(": [语音]");
            } else if (cVar.o) {
                spannableStringBuilder.append(": [图片]");
            } else if (!TextUtils.isEmpty(cVar.q) && cVar.q.trim().length() > 0) {
                spannableStringBuilder.append(": ").append(cVar.q);
            }
            if (spannableStringBuilder.length() > i2) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(c.a.d.a.a.a().a((int) R.color.CT_5)), i2, spannableStringBuilder.length(), 33);
            }
        }
        if (cVar.b == 131) {
            spannableStringBuilder.clear();
            spannableStringBuilder.append("有人赞了你的回复");
        }
        if (cVar.b == 130 && cVar.d == 0) {
            spannableStringBuilder.clear();
            spannableStringBuilder.append("有人给了你爱的抱抱");
        }
        notifyHolder.main.setText(spannableStringBuilder);
        notifyHolder.main.setMovementMethod(cn.xiaochuankeji.tieba.ui.widget.a.a());
        com.jakewharton.a.b.a.a(notifyHolder.itemView).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
            final /* synthetic */ b c;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                cn.xiaochuankeji.tieba.push.b.b.a(cVar.j, cVar.a);
                d.a().d();
                this.c.a(context, cVar);
                this.c.a();
                h.a(context, "zy_event_message_tab_notice", "点击提醒");
            }
        });
        com.jakewharton.a.b.a.b(notifyHolder.itemView).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
            final /* synthetic */ b c;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                SDEditSheet sDEditSheet = new SDEditSheet((Activity) context, new cn.xiaochuankeji.tieba.ui.widget.SDEditSheet.a(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        if (i == 1) {
                            cn.xiaochuankeji.tieba.push.b.b.b(cVar.j, cVar.a);
                            this.a.c.a();
                            d.a().d();
                        }
                    }
                }, "提醒");
                sDEditSheet.a("删除", 1, true);
                sDEditSheet.b();
            }
        });
        b(notifyHolder, cVar);
        a(notifyHolder, cVar);
    }

    private void a(Context context, c cVar) {
        if (cVar.b == 20) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.add(Long.valueOf(cVar.d));
            TaleListActivity.a(context, "notify", cVar.c, jSONArray.toJSONString());
        } else if (cVar.b == 30 || cVar.b == 130) {
            HollowDetailActivity.a(context, cVar.a, "my_xmsg");
        } else if (cVar.b == 131) {
            HollowDetailActivity.a(context, cVar.c, cVar.a);
        } else if (cVar.b == 21) {
            TaleDetailActivity.a(context, "notify", cVar.c, 1, cVar.d, 0);
        } else if (cVar.b == 22) {
            TaleDetailActivity.a(context, "notify", cVar.c, 2, cVar.d, cVar.g);
        } else if (cVar.b == 14 || cVar.b == 15) {
            if (cVar.d > 0) {
                UgcVideoActivity.a(context, cVar.d, "other");
            } else {
                UgcVideoActivity.b(context, cVar.c, "other");
            }
        } else if (cVar.b == 15) {
            if (cVar.h > 0) {
                UgcVideoActivity.a(context, cVar.d, "other", cVar.h, 1);
            } else {
                UgcVideoActivity.a(context, cVar.d, "other");
            }
        } else if (cVar.b == 18 || cVar.b == 19) {
            if (cVar.h != 0) {
                int i = cVar.b == 18 ? 2 : 3;
                if (0 == cVar.d) {
                    UgcVideoActivity.b(context, cVar.c, "other", cVar.h, i);
                } else {
                    UgcVideoActivity.a(context, cVar.d, "other", cVar.h, i);
                }
            }
        } else if (!cVar.a() || cVar.d <= 0) {
            PostDetailActivity.a(context, new Post(cVar.c), 0, null, "", EntranceType.Notify);
        } else if (cVar.g > 0) {
            InnerCommentDetailActivity.a(context, cVar.c, cVar.g, 0, new SubcommentFilter(cVar.d, cVar.g, 2), EntranceType.Notify);
        } else {
            PostDetailActivity.a(context, new Post(cVar.c), 1, new CommentFilter(cVar.d, 2), "", EntranceType.Notify);
        }
    }

    private void a(NotifyHolder notifyHolder, c cVar) {
        int i = 8;
        if (cVar.i > 0) {
            notifyHolder.thumb.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(cVar.i, false));
            notifyHolder.thumb.setVisibility(0);
        } else if (cVar.f > 0) {
            notifyHolder.thumb.setVisibility(0);
            notifyHolder.thumb.setImageResource(R.drawable.notice_vote_bg);
        } else {
            boolean z;
            int i2;
            notifyHolder.thumb.setVisibility(8);
            if (TextUtils.isEmpty(cVar.r)) {
                z = false;
            } else {
                z = true;
            }
            TextView textView = notifyHolder.brief;
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            textView.setVisibility(i2);
            if (z) {
                notifyHolder.brief.setText(cVar.r);
            }
        }
        if (cVar.l == 1) {
            notifyHolder.brief_type.setImageResource(R.drawable.ic_notice_video);
            notifyHolder.brief_type.setVisibility(0);
        } else if (cVar.l == 2) {
            notifyHolder.brief_type.setImageResource(R.drawable.ic_notice_voice);
            notifyHolder.brief_type.setVisibility(0);
        } else if (cVar.b == 51) {
            notifyHolder.brief_type.setImageResource(R.drawable.icon_voice_notify);
            notifyHolder.brief_type.setVisibility(0);
        } else {
            notifyHolder.brief_type.setVisibility(8);
        }
        WebImageView webImageView = notifyHolder.ugc_tag;
        if (cVar.A) {
            i = 0;
        }
        webImageView.setVisibility(i);
        if (cVar.A) {
            notifyHolder.ugc_tag.setImageResource(c.a.d.a.a.a().d(R.drawable.img_ugcvideo_follow_flag));
        }
        if (cVar.b == 21 || cVar.b == 20 || cVar.b == 22) {
            notifyHolder.ugc_tag.setVisibility(0);
            notifyHolder.ugc_tag.setImageResource(c.a.d.a.a.a().d(R.drawable.notice_gentie_tag));
        }
        if (cVar.b == 30 || cVar.b == 130 || cVar.b == 131) {
            notifyHolder.ugc_tag.setVisibility(0);
            notifyHolder.ugc_tag.setImageResource(c.a.d.a.a.a().d(R.drawable.notice_tree_tag));
        }
    }

    private void b(final NotifyHolder notifyHolder, c cVar) {
        float f = 0.5f;
        notifyHolder.main.setAlpha(cVar.m ? 0.5f : 1.0f);
        View view = notifyHolder.layout_right;
        if (!cVar.m) {
            f = 1.0f;
        }
        view.setAlpha(f);
        if (cVar.m) {
            notifyHolder.icon_group.setVisibility(8);
            return;
        }
        notifyHolder.icon_group.setVisibility(0);
        if (cVar.s > 0) {
            notifyHolder.likes.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(cVar.s));
            notifyHolder.likes.setVisibility(0);
            c.a.b.a(notifyHolder.likes, cVar.b == 130 ? R.drawable.notice_hug : R.drawable.icon_remind_like, 0, 0, 0);
        } else {
            notifyHolder.likes.setVisibility(8);
        }
        if (cVar.t > 0) {
            notifyHolder.ugc.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(cVar.t));
            notifyHolder.ugc.setVisibility(0);
        } else {
            notifyHolder.ugc.setVisibility(8);
        }
        if (cVar.u > 0) {
            notifyHolder.vote.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(cVar.u));
            notifyHolder.vote.setVisibility(0);
        } else {
            notifyHolder.vote.setVisibility(8);
        }
        if (cVar.v > 0) {
            notifyHolder.review.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(cVar.v));
            notifyHolder.review.setVisibility(0);
            c.a.b.a(notifyHolder.review, cVar.b == 20 ? R.drawable.notice_gentie : R.drawable.icon_remind_comment, 0, 0, 0);
        } else {
            notifyHolder.review.setVisibility(8);
        }
        if (cVar.w > 0) {
            notifyHolder.danmaku.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(cVar.w));
            notifyHolder.danmaku.setVisibility(0);
        } else {
            notifyHolder.danmaku.setVisibility(8);
        }
        if (cVar.x > 0) {
            notifyHolder.share.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(cVar.x));
            notifyHolder.share.setVisibility(0);
        } else {
            notifyHolder.share.setVisibility(8);
        }
        notifyHolder.more.setVisibility(4);
        if (notifyHolder.icon_group.getVisibility() == 0) {
            notifyHolder.icon_group.post(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    int measuredWidth = notifyHolder.icon_group.getMeasuredWidth();
                    int childCount = notifyHolder.icon_group.getChildCount();
                    int measuredWidth2 = measuredWidth - notifyHolder.icon_group.getChildAt(childCount - 1).getMeasuredWidth();
                    measuredWidth = 0;
                    for (int i = 0; i < childCount; i++) {
                        View childAt = notifyHolder.icon_group.getChildAt(i);
                        if (childAt.getVisibility() == 0) {
                            measuredWidth += childAt.getMeasuredWidth();
                            if (measuredWidth >= measuredWidth2) {
                                measuredWidth = i;
                                break;
                            }
                        }
                    }
                    measuredWidth = -1;
                    if (measuredWidth > 1 && measuredWidth != -1) {
                        while (measuredWidth < childCount - 1) {
                            notifyHolder.icon_group.getChildAt(measuredWidth).setVisibility(8);
                            measuredWidth++;
                        }
                        notifyHolder.icon_group.getChildAt(childCount - 1).setVisibility(0);
                    }
                }
            });
        }
    }

    public void a() {
        rx.d.a(Long.valueOf(cn.xiaochuankeji.tieba.background.a.g().c())).d(new g<Long, List<c>>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Long) obj);
            }

            public List<c> a(Long l) {
                return cn.xiaochuankeji.tieba.push.b.b.a(l.longValue());
            }
        }).d(new g<List<c>, cn.xiaochuankeji.tieba.push.a.a<c>>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((List) obj);
            }

            public cn.xiaochuankeji.tieba.push.a.a<c> a(List<c> list) {
                DiffResult diffResult;
                if (this.a.a.isEmpty()) {
                    diffResult = null;
                } else {
                    diffResult = DiffUtil.calculateDiff(new cn.xiaochuankeji.tieba.push.a.b(this.a.a, list));
                }
                return new cn.xiaochuankeji.tieba.push.a.a(diffResult, list);
            }
        }).b(rx.f.a.c()).c(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<cn.xiaochuankeji.tieba.push.a.a<c>>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((cn.xiaochuankeji.tieba.push.a.a) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(cn.xiaochuankeji.tieba.push.a.a<c> aVar) {
                DiffResult diffResult = aVar.a;
                Collection collection = aVar.b;
                if (diffResult != null) {
                    diffResult.dispatchUpdatesTo(new ListUpdateCallback(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void onInserted(int i, int i2) {
                            this.a.a.notifyItemRangeInserted(i, i2);
                        }

                        public void onRemoved(int i, int i2) {
                            this.a.a.notifyItemRangeRemoved(i, i2);
                            if (i != 0) {
                                this.a.a.notifyItemRangeChanged(i - 1, i2, Boolean.FALSE);
                            }
                        }

                        public void onMoved(int i, int i2) {
                            this.a.a.notifyItemMoved(i, i2);
                        }

                        public void onChanged(int i, int i2, Object obj) {
                            this.a.a.notifyItemRangeChanged(i, i2, obj);
                        }
                    });
                }
                this.a.a.clear();
                this.a.a.addAll(collection);
                this.a.notifyDataSetChanged();
            }
        });
    }

    public int getItemCount() {
        return this.a.size();
    }
}
