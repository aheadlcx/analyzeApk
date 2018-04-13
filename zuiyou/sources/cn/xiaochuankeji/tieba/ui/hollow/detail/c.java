package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.widget.AudioPlayView;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowDetailHugView;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.HashMap;
import java.util.List;

public class c extends Adapter {
    private e a;
    private d b;
    private HollowDetailActivity c;
    private View d;
    private HashMap<Long, Boolean> e;
    private List<MsgDataBean> f;
    private RoomDataBean g;
    private long h;
    private boolean i = false;

    interface e {
        void onClick(AudioDataBean audioDataBean, AudioPlayView audioPlayView);
    }

    interface d {
        void a(MsgDataBean msgDataBean);
    }

    private class a extends ViewHolder {
        final /* synthetic */ c a;

        a(c cVar, View view) {
            this.a = cVar;
            super(view);
        }

        void a() {
            this.itemView.setVisibility(this.a.i ? 0 : 8);
        }
    }

    private class b extends ViewHolder {
        final /* synthetic */ c a;
        private MultipleLineEllipsisTextView b;
        private HollowDetailHugView c;
        private AudioPlayView d;
        private WebImageView e;
        private TextView f;
        private TextView g;
        private View h;

        b(c cVar, View view) {
            this.a = cVar;
            super(view);
            this.d = (AudioPlayView) view.findViewById(R.id.hollow_detail_head_play);
            this.e = (WebImageView) view.findViewById(R.id.hollow_detail_head_emotion);
            this.f = (TextView) view.findViewById(R.id.hollow_detail_head_title);
            this.g = (TextView) view.findViewById(R.id.hollow_detail_head_num);
            this.h = view.findViewById(R.id.hollow_detail_head_info);
            this.c = (HollowDetailHugView) view.findViewById(R.id.hollow_detail_hug_view);
            this.b = (MultipleLineEllipsisTextView) view.findViewById(R.id.hollow_detail_head_title_text);
            this.b.setMaxLines(5);
        }

        @SuppressLint({"SetTextI18n"})
        void a(final RoomDataBean roomDataBean, boolean z) {
            this.h.setVisibility(8);
            if (roomDataBean != null) {
                this.e.setImageURI(cn.xiaochuankeji.tieba.ui.hollow.util.c.c(roomDataBean.emotion.imageId));
                this.c.setRoomData(roomDataBean);
                if (roomDataBean.audio == null || (TextUtils.isEmpty(roomDataBean.audio.url) && roomDataBean.audio.dur == 0)) {
                    this.d.setVisibility(8);
                    this.f.setVisibility(8);
                    this.b.setVisibility(0);
                    this.b.a(roomDataBean.subject, this.a.e, roomDataBean.id, -1996488705, 6);
                } else {
                    this.d.setVisibility(0);
                    this.f.setVisibility(0);
                    this.b.setVisibility(8);
                    this.f.setText(roomDataBean.subject);
                    com.izuiyou.a.a.b.c("HeadItem click -> AudioData : " + roomDataBean.audio + "  audioPlayView : " + this.d);
                    this.d.setPlayDuration(roomDataBean.audio.dur);
                    this.d.a(true, 1.0f, 0.4f);
                    this.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            this.b.a.a.onClick(roomDataBean.audio, this.b.d);
                        }
                    });
                }
                if (z) {
                    this.h.setVisibility(0);
                    this.g.setText("" + cn.xiaochuankeji.tieba.ui.utils.e.a(this.a.h) + " 回复");
                }
            }
        }
    }

    private class c extends ViewHolder {
        final /* synthetic */ c a;
        private MultipleLineEllipsisTextView b;
        private AudioPlayView c;
        private TextView d;
        private ImageView e;
        private TextView f;
        private View g;
        private ImageView h;
        private TextView i;

        c(c cVar, View view) {
            this.a = cVar;
            super(view);
            this.b = (MultipleLineEllipsisTextView) view.findViewById(R.id.hollow_detail_reply_title);
            this.c = (AudioPlayView) view.findViewById(R.id.hollow_detail_reply_play);
            this.d = (TextView) view.findViewById(R.id.hollow_detail_reply_name);
            this.f = (TextView) view.findViewById(R.id.hollow_detail_reply_info);
            this.e = (ImageView) view.findViewById(R.id.hollow_detail_reply_sex);
            this.g = view.findViewById(R.id.anonymous_chat);
            this.h = (ImageView) view.findViewById(R.id.hollow_detail_like_icon);
            this.i = (TextView) view.findViewById(R.id.hollow_detail_like_count);
        }

        @SuppressLint({"SetTextI18n"})
        void a(final MsgDataBean msgDataBean) {
            this.f.setText(h.a(msgDataBean.createTime * 1000));
            this.d.setText(msgDataBean.member.name);
            c(msgDataBean);
            OnClickListener anonymousClass1 = new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.b.a.c, "hollow_detail", 12)) {
                        this.b.b(msgDataBean);
                    }
                }
            };
            this.h.setOnClickListener(anonymousClass1);
            this.i.setOnClickListener(anonymousClass1);
            if (msgDataBean.text == null || msgDataBean.text.isEmpty()) {
                this.b.setVisibility(8);
                this.b.setOnExpandableTextViewListener(null);
            } else {
                this.b.setVisibility(0);
                this.b.a(msgDataBean.text, this.a.e, msgDataBean.id, -6709587, 1);
                this.b.setOnExpandableTextViewListener(new cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView.c(this) {
                    final /* synthetic */ c b;

                    public void onClick() {
                    }

                    public void a() {
                        this.b.a.b.a(msgDataBean);
                    }
                });
            }
            if (msgDataBean.audio != null) {
                this.c.setVisibility(0);
                this.c.setPlayDuration(msgDataBean.audio.dur);
                this.c.a(true, 1.0f, 0.7f);
                this.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        com.izuiyou.a.a.b.c("NormalItem click -> AudioData : " + msgDataBean.audio + "  audioPlayView : " + this.b.c);
                        this.b.a.a.onClick(msgDataBean.audio, this.b.c);
                    }
                });
            } else {
                this.c.setVisibility(8);
            }
            switch (msgDataBean.member.gender) {
                case 1:
                    this.e.setImageResource(R.drawable.sexual_male);
                    break;
                case 2:
                    this.e.setImageResource(R.drawable.sexual_female);
                    break;
            }
            if (msgDataBean.self == 1) {
                this.e.setImageResource(R.drawable.sexual_me);
            }
            this.itemView.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ c b;

                public boolean onLongClick(View view) {
                    this.b.a.b.a(msgDataBean);
                    return true;
                }
            });
            if (msgDataBean.self == 1 || this.a.g == null) {
                this.g.setVisibility(4);
                return;
            }
            this.itemView.setOnLongClickListener(null);
            this.g.setVisibility(0);
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    if (this.b.a.g != null) {
                        com.izuiyou.a.a.b.c("x_room:" + this.b.a.g + "  x_member:" + msgDataBean.member);
                        Activity a = cn.xiaochuankeji.tieba.background.utils.b.a(view.getContext());
                        if (a != null) {
                            cn.xiaochuankeji.tieba.ui.chat.a.b.a(a, this.b.a.g, null, msgDataBean.member);
                        }
                    }
                }
            });
        }

        private void b(MsgDataBean msgDataBean) {
            int i;
            msgDataBean.likes += (long) (msgDataBean.liked == 1 ? -1 : 1);
            if (msgDataBean.liked == 1) {
                i = 0;
            } else {
                i = 1;
            }
            msgDataBean.liked = i;
            c(msgDataBean);
            if (msgDataBean.liked == 1) {
                cn.xiaochuankeji.tieba.api.hollow.a.a(msgDataBean.room_id, msgDataBean.id).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void call(Object obj) {
                        a((EmptyJson) obj);
                    }

                    public void a(EmptyJson emptyJson) {
                    }
                }, new rx.b.b<Throwable>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void call(Object obj) {
                        a((Throwable) obj);
                    }

                    public void a(Throwable th) {
                        cn.xiaochuankeji.tieba.ui.utils.e.a(th);
                    }
                });
            } else {
                cn.xiaochuankeji.tieba.api.hollow.a.b(msgDataBean.room_id, msgDataBean.id).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void call(Object obj) {
                        a((EmptyJson) obj);
                    }

                    public void a(EmptyJson emptyJson) {
                    }
                }, new rx.b.b<Throwable>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void call(Object obj) {
                        a((Throwable) obj);
                    }

                    public void a(Throwable th) {
                        cn.xiaochuankeji.tieba.ui.utils.e.a(th);
                    }
                });
            }
        }

        @SuppressLint({"SetTextI18n"})
        private void c(MsgDataBean msgDataBean) {
            this.h.setImageResource(msgDataBean.liked == 1 ? R.drawable.tree_comment_like_hl : R.drawable.tree_comment_like);
            this.i.setText("" + (msgDataBean.likes < 0 ? Integer.valueOf(0) : cn.xiaochuankeji.tieba.ui.utils.e.a(msgDataBean.likes)));
        }
    }

    @SuppressLint({"UseSparseArrays"})
    c(HollowDetailActivity hollowDetailActivity, View view) {
        this.c = hollowDetailActivity;
        this.d = view;
        this.e = new HashMap();
        b();
    }

    void a(List<MsgDataBean> list, RoomDataBean roomDataBean, boolean z) {
        this.g = roomDataBean;
        this.f = list;
        this.h = roomDataBean.msgCount;
        this.i = z;
        b();
    }

    void a(List<MsgDataBean> list) {
        if (this.f != null) {
            this.f.addAll(list);
            b();
        }
    }

    void a(MsgDataBean msgDataBean) {
        if (this.f != null) {
            this.f.add(0, msgDataBean);
            this.h++;
            b();
        }
    }

    void a(long j) {
        for (MsgDataBean msgDataBean : this.f) {
            if (msgDataBean.id == j) {
                this.f.remove(msgDataBean);
                this.h--;
                break;
            }
        }
        b();
    }

    private void b() {
        View view = this.d;
        int i = (this.f == null || this.f.isEmpty()) ? 8 : 0;
        view.setVisibility(i);
        notifyDataSetChanged();
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new b(this, LayoutInflater.from(this.c).inflate(R.layout.layout_hollow_detail_head, viewGroup, false));
            case 2:
                return new a(this, LayoutInflater.from(this.c).inflate(R.layout.layout_hollow_detail_empty, viewGroup, false));
            default:
                return new c(this, LayoutInflater.from(this.c).inflate(R.layout.layout_hollow_detail_normal, viewGroup, false));
        }
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 1:
                b bVar = (b) viewHolder;
                RoomDataBean roomDataBean = this.g;
                boolean z = this.f != null && this.f.size() > 0;
                bVar.a(roomDataBean, z);
                return;
            case 2:
                ((a) viewHolder).a();
                return;
            default:
                ((c) viewHolder).a((MsgDataBean) this.f.get(i - 1));
                return;
        }
    }

    public int getItemCount() {
        if (this.g == null) {
            return 1;
        }
        if (this.f == null || this.f.isEmpty()) {
            return 2;
        }
        return this.f.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        if (this.f == null || this.f.isEmpty()) {
            return 2;
        }
        return 0;
    }

    public long a(AudioDataBean audioDataBean) {
        for (MsgDataBean msgDataBean : this.f) {
            if (msgDataBean.audio != null && msgDataBean.audio.a(audioDataBean)) {
                return msgDataBean.id;
            }
        }
        return 0;
    }

    public RoomDataBean a() {
        return this.g;
    }

    void a(e eVar) {
        this.a = eVar;
    }

    void a(d dVar) {
        this.b = dVar;
    }
}
