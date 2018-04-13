package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.News;
import qsbk.app.widget.qbnews.recommend.OneImageNewsRecommendCell;

@Deprecated
public class NewsRecommendOneImage extends BaseFragment {
    private News a;
    private int b;

    public static NewsRecommendOneImage newInstance(News news, int i) {
        NewsRecommendOneImage newsRecommendOneImage = new NewsRecommendOneImage();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.NEWS.getTypeValue(), news);
        bundle.putInt("position", i);
        newsRecommendOneImage.setArguments(bundle);
        return newsRecommendOneImage;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (News) getArguments().getSerializable(bo.NEWS.getTypeValue());
        this.b = getArguments().getInt("position");
        if (this.a == null) {
            return null;
        }
        OneImageNewsRecommendCell oneImageNewsRecommendCell = new OneImageNewsRecommendCell();
        oneImageNewsRecommendCell.performCreate(this.b, viewGroup, this.a);
        oneImageNewsRecommendCell.performUpdate(this.b, viewGroup, this.a);
        return oneImageNewsRecommendCell.getCellView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
