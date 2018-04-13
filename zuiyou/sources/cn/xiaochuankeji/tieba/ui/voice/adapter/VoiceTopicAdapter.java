package cn.xiaochuankeji.tieba.ui.voice.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.TopicJson;
import cn.xiaochuankeji.tieba.ui.base.c;
import cn.xiaochuankeji.tieba.ui.base.c.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoiceTopicAdapter extends c<TopicJson> {

    class VoiceTopicViewHolder extends a {
        final /* synthetic */ VoiceTopicAdapter b;
        @BindView
        WebImageView iv_cover;
        @BindView
        TextView tv_count;
        @BindView
        TextView tv_title;

        public VoiceTopicViewHolder(VoiceTopicAdapter voiceTopicAdapter, View view) {
            this.b = voiceTopicAdapter;
            super(voiceTopicAdapter, view);
            ButterKnife.a(this, view);
        }
    }

    public class VoiceTopicViewHolder_ViewBinding implements Unbinder {
        private VoiceTopicViewHolder b;

        @UiThread
        public VoiceTopicViewHolder_ViewBinding(VoiceTopicViewHolder voiceTopicViewHolder, View view) {
            this.b = voiceTopicViewHolder;
            voiceTopicViewHolder.iv_cover = (WebImageView) b.b(view, R.id.iv_cover, "field 'iv_cover'", WebImageView.class);
            voiceTopicViewHolder.tv_title = (TextView) b.b(view, R.id.tv_title, "field 'tv_title'", TextView.class);
            voiceTopicViewHolder.tv_count = (TextView) b.b(view, R.id.tv_count, "field 'tv_count'", TextView.class);
        }

        @CallSuper
        public void a() {
            VoiceTopicViewHolder voiceTopicViewHolder = this.b;
            if (voiceTopicViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            voiceTopicViewHolder.iv_cover = null;
            voiceTopicViewHolder.tv_title = null;
            voiceTopicViewHolder.tv_count = null;
        }
    }

    public VoiceTopicAdapter(Context context) {
        super(context);
    }

    public void a(ViewHolder viewHolder, int i) {
        VoiceTopicViewHolder voiceTopicViewHolder = (VoiceTopicViewHolder) viewHolder;
        final TopicJson topicJson = (TopicJson) this.c.get(i);
        voiceTopicViewHolder.iv_cover.setWebImage(cn.xiaochuankeji.tieba.background.f.b.c(topicJson.cover, false));
        voiceTopicViewHolder.tv_count.setText(topicJson.partners + "个" + topicJson.atts_title);
        voiceTopicViewHolder.tv_title.setText(topicJson.topic);
        voiceTopicViewHolder.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VoiceTopicAdapter b;

            public void onClick(View view) {
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.voice.a.a(topicJson));
            }
        });
    }

    public a a(ViewGroup viewGroup, int i) {
        return new VoiceTopicViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_voice_topic, viewGroup, false));
    }

    public int a(int i) {
        return 0;
    }
}
