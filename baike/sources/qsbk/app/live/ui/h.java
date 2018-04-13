package qsbk.app.live.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;
import qsbk.app.live.ui.family.FamilyDetailActivity;

class h implements OnClickListener {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ g b;

    h(g gVar, JSONObject jSONObject) {
        this.b = gVar;
        this.a = jSONObject;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("familyId", this.a.optLong("fid"));
        intent.putExtra("familyName", this.a.optString("n"));
        intent.putExtra("familyAvatar", this.a.optString(CustomButton.POSITION_RIGHT));
        intent.putExtra("familyBadge", this.a.optString(CustomButton.POSITION_BOTTOM));
        intent.setClass(this.b.b, FamilyDetailActivity.class);
        this.b.b.startActivity(intent);
    }
}
