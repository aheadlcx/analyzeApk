package com.budejie.www.g;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.ShowBigPicture;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;

public class b$c implements OnClickListener {
    Bundle a;
    final /* synthetic */ b b;

    public b$c(b bVar, Bundle bundle) {
        this.b = bVar;
        this.a = bundle;
    }

    public void onClick(View view) {
        if (an.a(b.a(this.b))) {
            boolean booleanValue;
            if (view != null) {
                try {
                    booleanValue = ((Boolean) view.getTag(R.string.bigimg)).booleanValue();
                } catch (Exception e) {
                    booleanValue = false;
                }
            } else {
                booleanValue = false;
            }
            if (booleanValue) {
                ListItemObject listItemObject = (ListItemObject) view.getTag(R.string.img_tag);
                Intent intent = new Intent(b.a(this.b), ShowBigPicture.class);
                if (this.a == null) {
                    intent.putExtra("imgPath", listItemObject.getImgUrl());
                    intent.putExtra("listItemObject", listItemObject);
                } else {
                    intent.putExtra("imgPath", an.b(listItemObject.getImgUrl()));
                }
                intent.putExtra("download_uri", listItemObject.getDownloadImageUris());
                if (listItemObject.isIs_ad()) {
                    b.a(this.b, listItemObject.getAd_url());
                    return;
                }
                intent.putExtra("isgif", listItemObject.getIs_gif());
                intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, listItemObject.getWidth());
                intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, listItemObject.getHeight());
                intent.putExtra("isLocal", listItemObject.getIfLocal());
                b.a(this.b).startActivity(intent);
                return;
            }
            return;
        }
        b.a(this.b, an.a(b.a(this.b), b.a(this.b).getString(R.string.nonet), -1));
        b.b(this.b).show();
    }
}
