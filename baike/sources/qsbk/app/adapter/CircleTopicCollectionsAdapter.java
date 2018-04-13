package qsbk.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.model.CircleTopicEpisode;
import qsbk.app.widget.BaseCell;

public class CircleTopicCollectionsAdapter extends BaseImageAdapter {

    class a extends BaseCell {
        TextView a;
        TextView b;
        ImageView c;
        final /* synthetic */ CircleTopicCollectionsAdapter d;

        a(CircleTopicCollectionsAdapter circleTopicCollectionsAdapter) {
            this.d = circleTopicCollectionsAdapter;
        }

        public void onCreate() {
            setCellView((int) R.layout.cell_circle_topic_episode);
            this.a = (TextView) findViewById(R.id.title);
            this.b = (TextView) findViewById(R.id.desc);
            this.c = (ImageView) findViewById(R.id.image);
        }

        public void onUpdate() {
            CircleTopicEpisode circleTopicEpisode = (CircleTopicEpisode) getItem();
            this.a.setText(circleTopicEpisode.title);
            this.b.setText(circleTopicEpisode.episode);
            displayImage(this.c, circleTopicEpisode.picUrl);
            getCellView().setOnClickListener(new bb(this, circleTopicEpisode));
        }
    }

    public CircleTopicCollectionsAdapter(ArrayList<CircleTopicEpisode> arrayList, Activity activity) {
        super(arrayList, activity);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        CircleTopicEpisode circleTopicEpisode = (CircleTopicEpisode) getItem(i);
        if (view == null) {
            aVar = new a(this);
            aVar.performCreate(i, viewGroup, circleTopicEpisode);
            view = aVar.getCellView();
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.performUpdate(i, viewGroup, circleTopicEpisode);
        return view;
    }
}
