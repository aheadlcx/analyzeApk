package com.sprite.ads.internal.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sprite.ads.SpriteADServiceManager;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.web.SpriteAdActivity;

public class b {
    public void a(Context context, AdItem adItem) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(adItem.getUrl()));
        context.startActivity(intent);
    }

    public void a(AdItem adItem) {
        SpriteADServiceManager.getInstance().startDownload(adItem);
    }

    public void b(Context context, AdItem adItem) {
        Intent intent = new Intent(context, SpriteAdActivity.class);
        intent.putExtra("url", adItem.getUrl());
        context.startActivity(intent);
    }

    public void c(Context context, AdItem adItem) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(adItem.getUrl())));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
