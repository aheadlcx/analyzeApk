package qsbk.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import qsbk.app.model.CircleTopic;
import qsbk.app.widget.CircleTopicCell;

public class CircleTopicListAdapter extends BaseImageAdapter<CircleTopic> {
    public CircleTopicListAdapter(ArrayList<CircleTopic> arrayList, Activity activity) {
        super(arrayList, activity);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CircleTopicCell circleTopicCell;
        CircleTopic circleTopic = (CircleTopic) getItem(i);
        if (view == null) {
            circleTopicCell = new CircleTopicCell();
            circleTopicCell.performCreate(i, viewGroup, circleTopic);
            view = circleTopicCell.getCellView();
            view.setTag(circleTopicCell);
        } else {
            circleTopicCell = (CircleTopicCell) view.getTag();
        }
        circleTopicCell.performUpdate(i, viewGroup, circleTopic);
        view.setOnClickListener(new bc(this, circleTopic));
        return circleTopicCell.getCellView();
    }
}
