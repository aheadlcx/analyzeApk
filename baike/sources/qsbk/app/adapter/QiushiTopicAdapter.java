package qsbk.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import qsbk.app.model.QiushiTopic;
import qsbk.app.widget.QiushiTopicCell;
import qsbk.app.widget.QiushiTopicCell.OnSubcribeListener;
import qsbk.app.widget.qiuyoucircle.UnknownCell.EmptyView;

public class QiushiTopicAdapter extends BaseImageAdapter {
    private OnSubcribeListener a;
    private boolean b;
    private boolean c;

    public QiushiTopicAdapter(ArrayList<Object> arrayList, Activity activity, boolean z, boolean z2) {
        super(arrayList, activity);
        this.b = z;
        this.c = z2;
    }

    public QiushiTopicAdapter(ArrayList<Object> arrayList, Activity activity, boolean z, OnSubcribeListener onSubcribeListener, boolean z2) {
        super(arrayList, activity);
        this.b = z;
        this.c = z2;
        this.a = onSubcribeListener;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case 0:
                QiushiTopicCell qiushiTopicCell;
                QiushiTopic qiushiTopic = (QiushiTopic) getItem(i);
                if (view == null) {
                    qiushiTopicCell = new QiushiTopicCell(this.b, this.a);
                    qiushiTopicCell.performCreate(i, viewGroup, qiushiTopic);
                    view = qiushiTopicCell.getCellView();
                    view.setTag(qiushiTopicCell);
                } else {
                    qiushiTopicCell = (QiushiTopicCell) view.getTag();
                }
                qiushiTopicCell.performUpdate(i, viewGroup, qiushiTopic);
                view.setOnClickListener(new de(this, qiushiTopic));
                return view;
            default:
                return new EmptyView(viewGroup.getContext());
        }
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        if (this.m.get(i) instanceof QiushiTopic) {
            return 0;
        }
        return 1;
    }
}
