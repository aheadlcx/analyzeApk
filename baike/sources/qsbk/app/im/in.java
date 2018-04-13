package qsbk.app.im;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class in implements OnItemClickListener {
    final /* synthetic */ OfficialSubscribeListActivity a;

    in(OfficialSubscribeListActivity officialSubscribeListActivity) {
        this.a = officialSubscribeListActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent(this.a, OfficialInfoActivity.class);
        OfficialSubscribeListItem officialSubscribeListItem = (OfficialSubscribeListItem) adapterView.getAdapter().getItem(i);
        intent.putExtra("uid", officialSubscribeListItem.id);
        intent.putExtra("name", officialSubscribeListItem.name);
        intent.putExtra("avatar", officialSubscribeListItem.icon);
        this.a.startActivity(intent);
    }
}
