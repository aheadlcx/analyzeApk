package com.bdj.picture.edit.d;

import android.app.Activity;
import android.content.Intent;
import android.widget.HorizontalScrollView;
import com.bdj.picture.edit.EditPictureActivity;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.mosaic.MosaicActivity;
import com.bdj.picture.edit.sticker.StickerActivity;
import com.bdj.picture.edit.widget.b;
import com.bdj.picture.edit.widget.c;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;
import java.util.Map;

public class a implements com.bdj.picture.edit.e.a {
    protected com.bdj.picture.edit.widget.c.a a;
    protected HorizontalScrollView b;
    protected Activity c;
    protected Map<BVType, c> d = null;

    public a(Activity activity, HorizontalScrollView horizontalScrollView) {
        this.c = activity;
        this.b = horizontalScrollView;
        this.d = new HashMap();
    }

    public void a(BVType bVType) {
        c cVar = (c) this.d.get(bVType);
        if (cVar == null) {
            switch (a$1.a[bVType.ordinal()]) {
                case 1:
                    MobclickAgent.onEvent(this.c, "E06-A01", "滤镜按钮");
                    cVar = new b(this.c);
                    this.d.put(bVType, cVar);
                    break;
                case 2:
                    MobclickAgent.onEvent(this.c, "E06-A01", "气泡按钮");
                    cVar = new com.bdj.picture.edit.widget.a(this.c);
                    if (this.a != null) {
                        cVar.a(this.a);
                    }
                    this.d.put(bVType, cVar);
                    break;
                case 3:
                    MobclickAgent.onEvent(this.c, "E06-A01", "旋转按钮");
                    ((EditPictureActivity) this.c).b();
                    break;
                case 4:
                    MobclickAgent.onEvent(this.c, "E06-A01", "马赛克按钮");
                    this.c.startActivityForResult(new Intent(this.c, MosaicActivity.class), 3);
                    break;
                case 5:
                    MobclickAgent.onEvent(this.c, "E06-A01", "贴纸按钮");
                    this.c.startActivityForResult(new Intent(this.c, StickerActivity.class), 2);
                    break;
            }
        }
        if (cVar != null) {
            cVar.a(this.b, bVType);
        }
    }

    public void a(com.bdj.picture.edit.widget.c.a aVar) {
        this.a = aVar;
    }
}
