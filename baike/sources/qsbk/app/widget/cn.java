package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class cn implements OnClickListener {
    final /* synthetic */ LoadingLayout a;

    cn(LoadingLayout loadingLayout) {
        this.a = loadingLayout;
    }

    public void onClick(View view) {
        if (LoadingLayout.a(this.a) && LoadingLayout.b(this.a) != null) {
            this.a.onLoading();
            LoadingLayout.b(this.a).onLoadingClick();
        }
    }
}
