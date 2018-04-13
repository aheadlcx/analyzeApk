package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;

class ks implements OnClickListener {
    final /* synthetic */ FillGroupInfoActivity a;

    ks(FillGroupInfoActivity fillGroupInfoActivity) {
        this.a = fillGroupInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.f()) {
            Intent intent = new Intent();
            intent.setClass(this.a.getApplicationContext(), FinishGroupActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("groupName", this.a.c.getText().toString().trim());
            bundle.putString("groupLocationCity", this.a.f);
            bundle.putString("groupLocationDistrict", this.a.g);
            bundle.putString("groupIntruduction", this.a.d.getText().toString().trim());
            bundle.putString("imgUrlStr", this.a.mImageUri.toString());
            bundle.putDouble(ParamKey.LATITUDE, this.a.h.doubleValue());
            bundle.putDouble(ParamKey.LONGITUDE, this.a.i.doubleValue());
            intent.putExtras(bundle);
            this.a.startActivity(intent);
        }
    }
}
