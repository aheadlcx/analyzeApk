package qsbk.app.widget.qbnews.recommend;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;
import qsbk.app.slide.SlideActivity;

class d implements OnClickListener {
    final /* synthetic */ ThreeImageNewsRecommendCell a;

    d(ThreeImageNewsRecommendCell threeImageNewsRecommendCell) {
        this.a = threeImageNewsRecommendCell;
    }

    public void onClick(View view) {
        Context baseContext;
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            baseContext = ((ContextWrapper) context).getBaseContext();
        } else {
            baseContext = context;
        }
        if (baseContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) baseContext;
        }
        if (baseContext instanceof SlideActivity) {
            SlideActivity slideActivity = (SlideActivity) baseContext;
            slideActivity.setResult(SlideActivity.MORE_NEWS);
            slideActivity.finish();
        }
    }
}
