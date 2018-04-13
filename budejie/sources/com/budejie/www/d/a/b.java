package com.budejie.www.d.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.RenderScript.RSMessageHandler;
import android.renderscript.ScriptIntrinsicBlur;

public class b {
    @TargetApi(18)
    public static Bitmap a(Context context, Bitmap bitmap, int i) throws RSRuntimeException {
        Allocation createFromBitmap;
        Throwable th;
        Allocation allocation;
        ScriptIntrinsicBlur scriptIntrinsicBlur = null;
        RenderScript create;
        try {
            create = RenderScript.create(context);
            try {
                create.setMessageHandler(new RSMessageHandler());
                createFromBitmap = Allocation.createFromBitmap(create, bitmap, MipmapControl.MIPMAP_NONE, 1);
            } catch (Throwable th2) {
                th = th2;
                allocation = null;
                createFromBitmap = null;
                if (create != null) {
                    create.destroy();
                }
                if (createFromBitmap != null) {
                    createFromBitmap.destroy();
                }
                if (allocation != null) {
                    allocation.destroy();
                }
                if (scriptIntrinsicBlur != null) {
                    scriptIntrinsicBlur.destroy();
                }
                throw th;
            }
            try {
                allocation = Allocation.createTyped(create, createFromBitmap.getType());
                try {
                    scriptIntrinsicBlur = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
                    scriptIntrinsicBlur.setInput(createFromBitmap);
                    scriptIntrinsicBlur.setRadius((float) i);
                    scriptIntrinsicBlur.forEach(allocation);
                    allocation.copyTo(bitmap);
                    if (create != null) {
                        create.destroy();
                    }
                    if (createFromBitmap != null) {
                        createFromBitmap.destroy();
                    }
                    if (allocation != null) {
                        allocation.destroy();
                    }
                    if (scriptIntrinsicBlur != null) {
                        scriptIntrinsicBlur.destroy();
                    }
                    return bitmap;
                } catch (Throwable th3) {
                    th = th3;
                    if (create != null) {
                        create.destroy();
                    }
                    if (createFromBitmap != null) {
                        createFromBitmap.destroy();
                    }
                    if (allocation != null) {
                        allocation.destroy();
                    }
                    if (scriptIntrinsicBlur != null) {
                        scriptIntrinsicBlur.destroy();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                allocation = null;
                if (create != null) {
                    create.destroy();
                }
                if (createFromBitmap != null) {
                    createFromBitmap.destroy();
                }
                if (allocation != null) {
                    allocation.destroy();
                }
                if (scriptIntrinsicBlur != null) {
                    scriptIntrinsicBlur.destroy();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            allocation = null;
            createFromBitmap = null;
            create = null;
            if (create != null) {
                create.destroy();
            }
            if (createFromBitmap != null) {
                createFromBitmap.destroy();
            }
            if (allocation != null) {
                allocation.destroy();
            }
            if (scriptIntrinsicBlur != null) {
                scriptIntrinsicBlur.destroy();
            }
            throw th;
        }
    }
}
