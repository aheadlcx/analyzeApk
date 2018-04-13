package qsbk.app.activity;

import qsbk.app.model.Article;
import qsbk.app.utils.QiushiArticleBus.OnArticleVoteStateChangeListener;

class ze implements OnArticleVoteStateChangeListener {
    final /* synthetic */ OthersQiuShiActivity a;

    ze(OthersQiuShiActivity othersQiuShiActivity) {
        this.a = othersQiuShiActivity;
    }

    public void onVoteStateChange(Article article, int i, int i2) {
        switch (i) {
            case -1:
                if (i2 != 1) {
                    if (i2 == 0) {
                        this.a.a = this.a.a + 1;
                        break;
                    }
                }
                this.a.a = this.a.a + 2;
                break;
                break;
            case 0:
                if (i2 != 1) {
                    if (i2 == -1) {
                        this.a.a = this.a.a - 1;
                        this.a.a = Math.max(0, this.a.a);
                        break;
                    }
                }
                this.a.a = this.a.a + 1;
                break;
                break;
            case 1:
                if (i2 != -1) {
                    if (i2 == 0) {
                        this.a.a = this.a.a - 1;
                        this.a.a = Math.max(0, this.a.a);
                        break;
                    }
                }
                this.a.a = this.a.a - 2;
                this.a.a = Math.max(0, this.a.a);
                break;
                break;
        }
        this.a.setQiushiSmileCount(this.a.a);
    }
}
