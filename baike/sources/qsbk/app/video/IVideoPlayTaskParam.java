package qsbk.app.video;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.model.Article;

public interface IVideoPlayTaskParam {
    public static final String SCENARIO_AUDIT_NATIVE = "audit_native";
    public static final String SCENARIO_MANAGE_MYCONTENT = "manage_mycontent";
    public static final String SCENARIO_SINGLE_ARTICLE = "single_article";

    public static class SimpleVideoPlayTaskParam implements IVideoPlayTaskParam {
        private View a;
        private Article b;
        private View c;
        private TextView d;
        private ProgressBar e;
        private String f;

        public SimpleVideoPlayTaskParam(View view, Article article, View view2, TextView textView, ProgressBar progressBar, String str) {
            this.b = article;
            this.a = view;
            this.d = textView;
            this.c = view2;
            this.e = progressBar;
            this.f = str;
        }

        public View getCoverIamge() {
            return this.a;
        }

        public View getPlayBtn() {
            return this.c;
        }

        public Article getArticle() {
            return this.b;
        }

        public TextView getLoopText() {
            return this.d;
        }

        public String getScenario() {
            return this.f;
        }

        public ProgressBar getProgressBar() {
            return this.e;
        }
    }

    Article getArticle();

    View getCoverIamge();

    TextView getLoopText();

    View getPlayBtn();

    ProgressBar getProgressBar();

    String getScenario();
}
