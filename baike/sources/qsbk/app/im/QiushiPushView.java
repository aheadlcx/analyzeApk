package qsbk.app.im;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.SingleArticle;
import qsbk.app.activity.SingleArticleLevel;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;

public class QiushiPushView extends RelativeLayout {
    private ChatMsg a;
    private Article b;
    private int c;
    private String d;
    private String e;
    private TextView f;
    private TextView g;
    private TextView h;
    private ImageView i;
    private ImageView j;

    public interface Jump {
        public static final String JUMP_ARTICLE = "article";
        public static final String JUMP_COMMENT = "comment";
    }

    private static class a implements OnClickListener {
        private Article a;
        private int b;

        a(Article article, int i) {
            this.a = article;
            this.b = i;
        }

        public void onClick(View view) {
            if (this.a != null) {
                Context context = view.getContext();
                Intent intent = new Intent(context, this.b > 0 ? SingleArticleLevel.class : SingleArticle.class);
                try {
                    intent.putExtra("FROM_MSG", true);
                    intent.putExtra("ARTICLEJSON", this.a.toJSONObject().toString());
                    intent.putExtra(SingleArticleLevel.COMMENT_FLOOR, this.b);
                    context.startActivity(intent);
                } catch (Exception e) {
                }
            }
        }
    }

    public QiushiPushView(Context context) {
        super(context);
    }

    public QiushiPushView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QiushiPushView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a() {
        if (this.f == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.im_qiushi_push_item, this, true);
            this.f = (TextView) findViewById(R.id.tv_chatcontent);
            this.g = (TextView) findViewById(R.id.tv_chattitle);
            this.h = (TextView) findViewById(R.id.article_content);
            this.i = (ImageView) findViewById(R.id.article_image);
            this.j = (ImageView) findViewById(R.id.play);
        }
    }

    private void a(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private void b() {
        if (this.b != null) {
            a();
            if (this.b != null) {
                a(this.g, 8);
                this.f.setText(this.d);
                if (this.b.isVideoArticle()) {
                    this.j.setImageResource(R.drawable.im_qiushi_push_play);
                    a(this.j, 0);
                    a(this.h, 8);
                    a(this.i, 0);
                    a(this.i, this.b.absPicPath);
                } else if (this.b.isWordsOnly()) {
                    this.j.setImageDrawable(null);
                    a(this.j, 8);
                    a(this.h, 0);
                    a(this.i, 8);
                    this.i.setImageDrawable(null);
                    this.h.setText(this.b.content);
                } else {
                    this.j.setImageDrawable(null);
                    a(this.j, 8);
                    a(this.h, 8);
                    a(this.i, 0);
                    a(this.i, QsbkApp.absoluteUrlOfSmallContentImage(this.b.id, this.b.image));
                }
            }
            if ("article".equals(this.e)) {
                setOnClickListener(new a(this.b, 0));
            } else if ("comment".equals(this.e)) {
                setOnClickListener(new a(this.b, this.c));
            } else {
                setOnClickListener(null);
            }
        }
    }

    private void a(ImageView imageView, String str) {
        if (imageView != null && str != null) {
            this.i.setImageDrawable(null);
            FrescoImageloader.displayImage(this.i, str, TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE));
        }
    }

    public void setData(ChatMsg chatMsg) {
        if (chatMsg != this.a) {
            this.a = chatMsg;
            if (this.a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.a.data);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("jump_data");
                    this.e = jSONObject.getString("jump");
                    this.d = jSONObject.optString("d");
                    if ("article".equals(this.e) || "comment".equals(this.e)) {
                        this.b = new Article(jSONObject2);
                        this.c = jSONObject2.optInt("jump_to_level");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            b();
        }
    }
}
