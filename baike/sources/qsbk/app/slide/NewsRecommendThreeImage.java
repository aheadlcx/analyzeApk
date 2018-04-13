package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.News;
import qsbk.app.widget.qbnews.recommend.ThreeImageNewsRecommendCell;

@Deprecated
public class NewsRecommendThreeImage extends BaseFragment {
    private News a;
    private int b;

    public static NewsRecommendThreeImage newInstance(News news, int i) {
        NewsRecommendThreeImage newsRecommendThreeImage = new NewsRecommendThreeImage();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.NEWS.getTypeValue(), news);
        bundle.putInt("position", i);
        newsRecommendThreeImage.setArguments(bundle);
        return newsRecommendThreeImage;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (News) getArguments().getSerializable(bo.NEWS.getTypeValue());
        this.b = getArguments().getInt("position");
        if (this.a == null) {
            return null;
        }
        ThreeImageNewsRecommendCell threeImageNewsRecommendCell = new ThreeImageNewsRecommendCell();
        threeImageNewsRecommendCell.performCreate(this.b, viewGroup, this.a);
        threeImageNewsRecommendCell.performUpdate(this.b, viewGroup, this.a);
        return threeImageNewsRecommendCell.getCellView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
