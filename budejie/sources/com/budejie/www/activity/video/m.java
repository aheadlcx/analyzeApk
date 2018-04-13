package com.budejie.www.activity.video;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import java.util.List;

public class m extends Fragment {
    private GridView a;
    private n b = null;
    private List<ListItemObject> c;
    private OnItemClickListener d;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.c = (List) getArguments().getSerializable("recommend_data_key");
        Log.d("RecommendVideoFragment", "mRecommendDatas.size()" + this.c.size());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recommend_video_fragment_layout, viewGroup, false);
        this.a = (GridView) inflate.findViewById(R.id.gridview);
        this.b = new n(getActivity(), this.c);
        this.a.setAdapter(this.b);
        this.a.setOnItemClickListener(this.d);
        return inflate;
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }
}
