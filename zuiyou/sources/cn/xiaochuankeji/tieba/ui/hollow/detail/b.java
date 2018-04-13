package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.widget.AudioPlayView;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import java.util.HashMap;
import java.util.List;

public class b extends Adapter {
    private a a;
    private b b;
    private HashMap<Long, Boolean> c = new HashMap();
    private List<MsgDataBean> d;
    private Context e;

    interface a {
        void a(long j);
    }

    interface b {
        void onClick(AudioDataBean audioDataBean, AudioPlayView audioPlayView);
    }

    private class c extends ViewHolder {
        final /* synthetic */ b a;
        private MultipleLineEllipsisTextView b;
        private TextView c;
        private TextView d;
        private AudioPlayView e;
        private View f;
        private View g;

        c(b bVar, View view) {
            this.a = bVar;
            super(view);
            this.b = (MultipleLineEllipsisTextView) view.findViewById(R.id.hollow_reply_item_info);
            this.c = (TextView) view.findViewById(R.id.hollow_reply_item_title_text);
            this.d = (TextView) view.findViewById(R.id.hollow_reply_item_name);
            this.e = (AudioPlayView) view.findViewById(R.id.hollow_reply_item_play);
            this.f = view.findViewById(R.id.hollow_reply_play_holder);
            this.g = view.findViewById(R.id.hollow_reply_item_bottom);
            this.b.setMaxLines(8);
        }

        @SuppressLint({"SetTextI18n"})
        void a(final MsgDataBean msgDataBean, boolean z) {
            int i;
            this.c.setText("来自树洞：" + (msgDataBean.room == null ? "" : msgDataBean.room.subject));
            View view = this.g;
            if (z) {
                i = 8;
            } else {
                i = 0;
            }
            view.setVisibility(i);
            this.d.setText("花名：" + msgDataBean.member.name);
            if (msgDataBean.text == null || msgDataBean.text.isEmpty()) {
                this.b.setVisibility(8);
                this.b.setOnExpandableTextViewListener(null);
            } else {
                this.b.setVisibility(0);
                this.b.a(msgDataBean.text, this.a.c, msgDataBean.id, -6709587, 1);
                this.b.setOnExpandableTextViewListener(new cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView.c(this) {
                    final /* synthetic */ c b;

                    public void onClick() {
                        HollowDetailActivity.a(this.b.a.e, msgDataBean.room_id, "my_xmsg");
                    }

                    public void a() {
                        this.b.a.a.a(msgDataBean.id);
                    }
                });
            }
            if (msgDataBean.audio != null) {
                this.f.setVisibility(0);
                this.e.setPlayDuration(msgDataBean.audio.dur);
                this.e.a(true, 1.0f, 0.7f);
                this.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        this.b.a.b.onClick(msgDataBean.audio, this.b.e);
                    }
                });
                return;
            }
            this.f.setVisibility(8);
            this.e.setOnClickListener(null);
        }
    }

    @SuppressLint({"UseSparseArrays"})
    b(Context context) {
        this.e = context;
    }

    void a(List<MsgDataBean> list) {
        this.d = list;
        notifyDataSetChanged();
    }

    void b(List<MsgDataBean> list) {
        this.d.addAll(list);
        notifyDataSetChanged();
    }

    void a(MsgDataBean msgDataBean) {
        this.d.add(0, msgDataBean);
        notifyDataSetChanged();
    }

    void a(long j, d dVar) {
        boolean z = false;
        for (int i = 0; i < this.d.size(); i++) {
            if (((MsgDataBean) this.d.get(i)).id == j) {
                this.d.remove(i);
                break;
            }
        }
        if (this.d == null || this.d.size() == 0) {
            z = true;
        }
        if (dVar != null) {
            dVar.a(z);
        }
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(this, LayoutInflater.from(this.e).inflate(R.layout.layout_hollow_item_reply, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final MsgDataBean msgDataBean = (MsgDataBean) this.d.get(i);
        ((c) viewHolder).a((MsgDataBean) this.d.get(i), i == this.d.size() + -1);
        viewHolder.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                HollowDetailActivity.a(this.b.e, msgDataBean.room_id, "my_xroom");
            }
        });
        viewHolder.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ b b;

            public boolean onLongClick(View view) {
                this.b.a.a(msgDataBean.id);
                return true;
            }
        });
    }

    public int getItemCount() {
        return this.d == null ? 0 : this.d.size();
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public MsgDataBean a(AudioDataBean audioDataBean) {
        for (MsgDataBean msgDataBean : this.d) {
            if (msgDataBean.audio.a(audioDataBean)) {
                return msgDataBean;
            }
        }
        return null;
    }

    void a(b bVar) {
        this.b = bVar;
    }

    void a(a aVar) {
        this.a = aVar;
    }
}
