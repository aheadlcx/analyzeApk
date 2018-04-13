package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.CircleTopic;
import qsbk.app.widget.CircleTopicMoreCell;
import qsbk.app.widget.CircleTopicTextBgCell;
import qsbk.app.widget.CircleTopicThreeImageCell;
import qsbk.app.widget.qiuyoucircle.UnknownCell.EmptyView;

public class TopicFragment extends BaseFragment {
    private CircleTopic a;

    public static TopicFragment newInstance(CircleTopic circleTopic) {
        TopicFragment topicFragment = new TopicFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.CIRCLE_TOPIC.getTypeValue(), circleTopic);
        topicFragment.setArguments(bundle);
        return topicFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (CircleTopic) getArguments().getSerializable(bo.CIRCLE_TOPIC.getTypeValue());
        if (this.a == null) {
            return null;
        }
        View cellView;
        switch (this.a.type) {
            case 1:
                CircleTopicTextBgCell circleTopicTextBgCell = new CircleTopicTextBgCell();
                circleTopicTextBgCell.performCreate(0, viewGroup, this.a);
                cellView = circleTopicTextBgCell.getCellView();
                cellView.setTag(circleTopicTextBgCell);
                circleTopicTextBgCell.performUpdate(0, viewGroup, this.a);
                return cellView;
            case 2:
                CircleTopicMoreCell circleTopicMoreCell = new CircleTopicMoreCell();
                circleTopicMoreCell.performCreate(0, viewGroup, this.a);
                cellView = circleTopicMoreCell.getCellView();
                cellView.setTag(circleTopicMoreCell);
                circleTopicMoreCell.performUpdate(0, viewGroup, this.a);
                return cellView;
            case 3:
                CircleTopicThreeImageCell circleTopicThreeImageCell = new CircleTopicThreeImageCell();
                circleTopicThreeImageCell.performCreate(0, viewGroup, this.a);
                cellView = circleTopicThreeImageCell.getCellView();
                cellView.setTag(cellView);
                circleTopicThreeImageCell.performUpdate(0, viewGroup, this.a);
                return cellView;
            default:
                return new EmptyView(getContext());
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
