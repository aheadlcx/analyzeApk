package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuJson;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.c;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;
import java.util.ArrayList;
import java.util.List;

public class a extends e<d> {
    private List<UgcVideoDanmakuJson> a = new ArrayList();
    private LayoutInflater l;
    private Context m;
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b.a n;
    private c o;
    private int p;
    private boolean q = false;

    public class a extends d {
        final /* synthetic */ a a;
        private UgcVideoDanmakuJson b;
        private View c;
        private View d;
        private View e;
        private View f;
        private WebImageView g;
        private TextView h;
        private TextView i;
        private TextView j;
        private TextView o;
        private TextView p;
        private TextView q;

        class a extends ClickableSpan {
            final /* synthetic */ a a;
            private long b;

            public a(a aVar, long j) {
                this.a = aVar;
                this.b = j;
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#dd2864"));
                textPaint.setUnderlineText(false);
            }

            public void onClick(View view) {
                MemberDetailActivity.a(this.a.a.m, this.b);
            }
        }

        public a(final a aVar, View view) {
            this.a = aVar;
            super(view);
            this.c = view.findViewById(R.id.vGodFlag);
            this.d = view.findViewById(R.id.vHate);
            this.e = view.findViewById(R.id.vLike);
            this.f = view.findViewById(R.id.vSubContainer);
            this.g = (WebImageView) view.findViewById(R.id.wivAvatar);
            this.h = (TextView) view.findViewById(R.id.tvName);
            this.i = (TextView) view.findViewById(R.id.tvLikeCount);
            this.j = (TextView) view.findViewById(R.id.tvContent);
            this.o = (TextView) view.findViewById(R.id.tvFirstSub);
            this.p = (TextView) view.findViewById(R.id.tvSecondSub);
            this.q = (TextView) view.findViewById(R.id.tvTips);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a();
                }
            });
            this.j.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a();
                }
            });
            view.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ a b;

                public boolean onLongClick(View view) {
                    this.b.a.o.b(this.b.b);
                    return true;
                }
            });
            this.o.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.o.a(this.b.b);
                }
            });
            this.p.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.o.a(this.b.b);
                }
            });
        }

        private void a() {
            if (this.a.p != 2 || cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) this.a.m, "media_browser", 8)) {
                this.a.o.a(this.b);
            }
        }

        public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson, int i) {
            int i2;
            if (this.a.p == 2) {
                if (i == 0) {
                    d().setBackgroundColor(Color.parseColor("#00ffffff"));
                } else {
                    d().setBackgroundColor(Color.parseColor("#b3212121"));
                }
            }
            this.b = ugcVideoDanmakuJson;
            View view = this.c;
            if (1 == ugcVideoDanmakuJson.isGod) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
            if (ugcVideoDanmakuJson.liked == 0) {
                this.e.setSelected(false);
                this.d.setSelected(false);
                this.i.setTextColor(Color.parseColor("#ffffff"));
            } else if (1 == ugcVideoDanmakuJson.liked) {
                this.e.setSelected(true);
                this.d.setSelected(false);
                this.i.setTextColor(Color.parseColor("#56c6fa"));
            } else if (-1 == ugcVideoDanmakuJson.liked) {
                this.e.setSelected(false);
                this.d.setSelected(true);
                this.i.setTextColor(Color.parseColor("#e84766"));
            }
            this.i.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(ugcVideoDanmakuJson.likes));
            this.g.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(ugcVideoDanmakuJson.member.getId(), ugcVideoDanmakuJson.member.getAvatarId()));
            this.h.setText(ugcVideoDanmakuJson.member.getNickName());
            a(this.j, ugcVideoDanmakuJson);
            i2 = ugcVideoDanmakuJson.subInfos.size();
            if (i2 > 2) {
                i2 = 2;
            }
            if (ugcVideoDanmakuJson.subCount <= 0 || i2 <= 0) {
                this.f.setVisibility(8);
            } else {
                if (ugcVideoDanmakuJson.subCount > 2) {
                    this.q.setText("查看" + ugcVideoDanmakuJson.subCount + "条回复");
                    this.q.setVisibility(0);
                } else {
                    this.q.setVisibility(8);
                }
                this.f.setVisibility(0);
                if (i2 == 1) {
                    this.o.setVisibility(0);
                    this.p.setVisibility(8);
                    b(this.o, (UgcVideoDanmakuJson) ugcVideoDanmakuJson.subInfos.get(0));
                } else if (i2 == 2) {
                    this.o.setVisibility(0);
                    this.p.setVisibility(0);
                    b(this.o, (UgcVideoDanmakuJson) ugcVideoDanmakuJson.subInfos.get(0));
                    b(this.p, (UgcVideoDanmakuJson) ugcVideoDanmakuJson.subInfos.get(1));
                }
            }
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) this.a.a.m, "media_browser", 12)) {
                        return;
                    }
                    if (this.a.b.liked == 0) {
                        this.a.a.n.a(true, this.a.b.id);
                    } else if (this.a.b.liked == 1) {
                        this.a.a.n.a(this.a.b.id);
                    }
                }
            });
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) this.a.a.m, "media_browser", 14)) {
                        return;
                    }
                    if (this.a.b.liked == 0) {
                        this.a.a.n.a(false, this.a.b.id);
                    } else if (this.a.b.liked == -1) {
                        this.a.a.n.a(this.a.b.id);
                    }
                }
            });
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    MemberDetailActivity.a(this.a.a.m, this.a.b.member.getId());
                }
            });
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    MemberDetailActivity.a(this.a.a.m, this.a.b.member.getId());
                }
            });
        }

        private void a(TextView textView, UgcVideoDanmakuJson ugcVideoDanmakuJson) {
            if (ugcVideoDanmakuJson.sinfo == null) {
                textView.setText(ugcVideoDanmakuJson.text);
                return;
            }
            String nickName = ugcVideoDanmakuJson.sinfo.member.getNickName();
            if (TextUtils.isEmpty(nickName)) {
                textView.setText(ugcVideoDanmakuJson.text);
                return;
            }
            String str = "回复";
            CharSequence spannableString = new SpannableString(str + nickName + "：" + ugcVideoDanmakuJson.text);
            int length = nickName.length();
            if (length > spannableString.length()) {
                length = spannableString.length();
            }
            spannableString.setSpan(new a(this, ugcVideoDanmakuJson.sinfo.member.getId()), str.length(), length + str.length(), 33);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private void b(TextView textView, UgcVideoDanmakuJson ugcVideoDanmakuJson) {
            CharSequence charSequence;
            long id = ugcVideoDanmakuJson.member.getId();
            String nickName = ugcVideoDanmakuJson.member.getNickName();
            Object nickName2 = ugcVideoDanmakuJson.sinfo != null ? ugcVideoDanmakuJson.sinfo.member.getNickName() : null;
            String str = ugcVideoDanmakuJson.text;
            if (TextUtils.isEmpty(nickName2)) {
                charSequence = nickName + ": " + str;
            } else {
                charSequence = nickName + " 回复 " + nickName2 + "：" + str;
            }
            CharSequence spannableString = new SpannableString(charSequence);
            int length = nickName.length();
            if (length > spannableString.length()) {
                length = spannableString.length();
            }
            spannableString.setSpan(new a(this, id), 0, length, 33);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    class b extends d {
        final /* synthetic */ a a;

        public b(final a aVar, View view) {
            this.a = aVar;
            super(view);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.o.a();
                }
            });
        }
    }

    public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
        return a(viewGroup);
    }

    public /* synthetic */ ViewHolder c(View view) {
        return b(view);
    }

    public /* synthetic */ ViewHolder d(View view) {
        return a(view);
    }

    public /* synthetic */ ViewHolder g(View view) {
        return e(view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public a(Context context, int i, c cVar) {
        this.m = context;
        this.l = LayoutInflater.from(this.m);
        this.p = i;
        this.o = cVar;
        this.n = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b.a(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.a.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(boolean z, long j) {
                for (int i = 0; i < this.a.a.size(); i++) {
                    UgcVideoDanmakuJson ugcVideoDanmakuJson = (UgcVideoDanmakuJson) this.a.a.get(i);
                    if (ugcVideoDanmakuJson.id == j) {
                        if (z) {
                            ugcVideoDanmakuJson.likes++;
                            ugcVideoDanmakuJson.liked = 1;
                        } else {
                            ugcVideoDanmakuJson.likes--;
                            ugcVideoDanmakuJson.liked = -1;
                        }
                        this.a.notifyItemChanged(i);
                    }
                }
            }

            public void a(long j) {
                for (int i = 0; i < this.a.a.size(); i++) {
                    UgcVideoDanmakuJson ugcVideoDanmakuJson = (UgcVideoDanmakuJson) this.a.a.get(i);
                    if (ugcVideoDanmakuJson.id == j) {
                        if (ugcVideoDanmakuJson.liked == 1) {
                            ugcVideoDanmakuJson.likes--;
                            ugcVideoDanmakuJson.liked = 0;
                        } else if (ugcVideoDanmakuJson.liked == -1) {
                            ugcVideoDanmakuJson.likes++;
                            ugcVideoDanmakuJson.liked = 0;
                        }
                        this.a.notifyItemChanged(i);
                    }
                }
            }
        });
    }

    public void a(List<UgcVideoDanmakuJson> list, boolean z) {
        this.a = list;
        this.q = z;
        notifyDataSetChanged();
    }

    public void a(long j) {
        this.n.b(j);
    }

    public d a(View view) {
        return new d(view);
    }

    public d a(ViewGroup viewGroup) {
        return new a(this, this.l.inflate(R.layout.view_item_danmaku, null));
    }

    public int a() {
        if (this.q) {
            return this.a.size() + 1;
        }
        return this.a.size();
    }

    public int getItemViewType(int i) {
        if (this.q && i == this.a.size()) {
            return 5;
        }
        return super.getItemViewType(i);
    }

    public void a(d dVar, int i) {
        if (getItemViewType(i) == 0) {
            ((a) dVar).a((UgcVideoDanmakuJson) this.a.get(i), i);
        }
    }

    public d b(View view) {
        return null;
    }

    public d e(View view) {
        return new b(this, this.l.inflate(R.layout.danmaku_item_query_all, null));
    }
}
