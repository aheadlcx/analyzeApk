package cn.xiaochuankeji.tieba.ui.homepage.banner;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.json.UgcEventJson;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.marshalchen.ultimaterecyclerview.d;

public class c extends d {
    private WebImageView a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private EventVideoScrollView f;
    private UgcEventJson g;

    public c(View view) {
        super(view);
        this.a = (WebImageView) view.findViewById(R.id.picEvent);
        this.b = view.findViewById(R.id.videoInfoView);
        this.c = (TextView) view.findViewById(R.id.tvTitle);
        this.d = (TextView) view.findViewById(R.id.tvTotal);
        this.e = (TextView) view.findViewById(R.id.tvDesc);
        this.f = (EventVideoScrollView) view.findViewById(R.id.event_video_scroll_view);
    }

    public void a(UgcEventJson ugcEventJson) {
        this.g = ugcEventJson;
        if (this.g.type.equals("h5")) {
            this.a.setVisibility(0);
            this.b.setVisibility(8);
            this.a.setWebImage(b.b((long) this.g.img.id));
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    WebActivity.a(this.a.e(), cn.xiaochuan.jsbridge.b.a("跟拍活动", this.a.g.url));
                }
            });
        } else if (this.g.type.equals("post")) {
            this.a.setVisibility(8);
            this.b.setVisibility(0);
            Moment moment = ugcEventJson.momentJson;
            this.c.setText(moment.eventTitle);
            if (TextUtils.isEmpty(moment.eventDesc)) {
                this.e.setVisibility(8);
            } else {
                this.e.setVisibility(0);
                this.e.setText(moment.eventDesc);
            }
            this.d.setText(String.valueOf(moment.followCount + 1));
            if (moment.ugcVideos.size() > 0) {
                this.f.a(moment, "activity");
            }
        }
    }
}
