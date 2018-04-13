package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.widget.AudioPlayView;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.List;

public class a extends Adapter {
    private b a;
    private c b;
    private List<RoomDataBean> c;
    private Context d;

    interface b {
        void a(long j);
    }

    interface c {
        void onClick(AudioDataBean audioDataBean, AudioPlayView audioPlayView);
    }

    private class a extends ViewHolder {
        final /* synthetic */ a a;
        private TextView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private AudioPlayView f;
        private WebImageView g;
        private View h;

        a(a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (TextView) view.findViewById(R.id.hollow_normal_item_reply);
            this.c = (TextView) view.findViewById(R.id.hollow_normal_item_info_text);
            this.d = (TextView) view.findViewById(R.id.hollow_normal_item_name);
            this.e = (TextView) view.findViewById(R.id.hollow_normal_item_info);
            this.f = (AudioPlayView) view.findViewById(R.id.hollow_normal_item_play);
            this.g = (WebImageView) view.findViewById(R.id.hollow_normal_item_emotion);
            this.h = view.findViewById(R.id.hollow_normal_item_bottom);
        }

        @SuppressLint({"SetTextI18n"})
        void a(final RoomDataBean roomDataBean, boolean z) {
            this.b.setText(e.a(roomDataBean.msgCount) + "回复");
            this.d.setText("花名：" + roomDataBean.member.name);
            this.g.setImageURI(cn.xiaochuankeji.tieba.ui.hollow.util.c.c(roomDataBean.emotion.imageId));
            this.h.setVisibility(z ? 8 : 0);
            if (roomDataBean.audio == null || (TextUtils.isEmpty(roomDataBean.audio.url) && roomDataBean.audio.dur == 0)) {
                this.f.setVisibility(8);
                this.e.setVisibility(8);
                this.c.setVisibility(0);
                this.c.setText(roomDataBean.subject);
                return;
            }
            this.f.setVisibility(0);
            this.e.setVisibility(0);
            this.c.setVisibility(8);
            this.e.setText(roomDataBean.subject);
            this.f.setPlayDuration(roomDataBean.audio.dur);
            this.f.a(true, 1.0f, 0.7f);
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.b.onClick(roomDataBean.audio, this.b.f);
                }
            });
        }
    }

    a(Context context) {
        this.d = context;
    }

    void a(List<RoomDataBean> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    void b(List<RoomDataBean> list) {
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    void a(long j, d dVar) {
        boolean z = false;
        for (int i = 0; i < this.c.size(); i++) {
            if (((RoomDataBean) this.c.get(i)).id == j) {
                this.c.remove(i);
                break;
            }
        }
        if (this.c == null || this.c.size() == 0) {
            z = true;
        }
        if (dVar != null) {
            dVar.a(z);
        }
        notifyDataSetChanged();
    }

    void a(RoomDataBean roomDataBean) {
        if (!this.c.contains(roomDataBean)) {
            this.c.add(0, roomDataBean);
            notifyDataSetChanged();
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(this.d).inflate(R.layout.layout_hollow_item_normal, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RoomDataBean roomDataBean = (RoomDataBean) this.c.get(i);
        ((a) viewHolder).a(roomDataBean, i == this.c.size() + -1);
        viewHolder.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                HollowDetailActivity.a(this.b.d, roomDataBean, "my_xroom");
            }
        });
        viewHolder.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ a b;

            public boolean onLongClick(View view) {
                this.b.a.a(roomDataBean.id);
                return true;
            }
        });
    }

    public int getItemCount() {
        return this.c == null ? 0 : this.c.size();
    }

    public int getItemViewType(int i) {
        return 0;
    }

    RoomDataBean a(AudioDataBean audioDataBean) {
        for (RoomDataBean roomDataBean : this.c) {
            if (roomDataBean.audio.a(audioDataBean)) {
                return roomDataBean;
            }
        }
        return null;
    }

    void a(c cVar) {
        this.b = cVar;
    }

    void a(b bVar) {
        this.a = bVar;
    }
}
