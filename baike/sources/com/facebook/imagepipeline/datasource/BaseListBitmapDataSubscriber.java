package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.List;

public abstract class BaseListBitmapDataSubscriber extends BaseDataSubscriber<List<CloseableReference<CloseableImage>>> {
    public void onNewResultImpl(com.facebook.datasource.DataSource<java.util.List<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>>> r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Incorrect nodes count for selectOther: B:30:0x0076 in [B:19:0x0050, B:30:0x0076, B:31:0x0007]
	at jadx.core.utils.BlockUtils.selectOther(BlockUtils.java:53)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:64)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r1 = 0;
        r0 = r6.isFinished();
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        r0 = r6.getResult();
        r0 = (java.util.List) r0;
        if (r0 != 0) goto L_0x0014;
    L_0x0010:
        r5.onNewResultListImpl(r1);
        goto L_0x0007;
    L_0x0014:
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0045 }
        r1 = r0.size();	 Catch:{ all -> 0x0045 }
        r2.<init>(r1);	 Catch:{ all -> 0x0045 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0045 }
    L_0x0021:
        r1 = r3.hasNext();	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x005f;	 Catch:{ all -> 0x0045 }
    L_0x0027:
        r1 = r3.next();	 Catch:{ all -> 0x0045 }
        r1 = (com.facebook.common.references.CloseableReference) r1;	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x005a;	 Catch:{ all -> 0x0045 }
    L_0x002f:
        r4 = r1.get();	 Catch:{ all -> 0x0045 }
        r4 = r4 instanceof com.facebook.imagepipeline.image.CloseableBitmap;	 Catch:{ all -> 0x0045 }
        if (r4 == 0) goto L_0x005a;	 Catch:{ all -> 0x0045 }
    L_0x0037:
        r1 = r1.get();	 Catch:{ all -> 0x0045 }
        r1 = (com.facebook.imagepipeline.image.CloseableBitmap) r1;	 Catch:{ all -> 0x0045 }
        r1 = r1.getUnderlyingBitmap();	 Catch:{ all -> 0x0045 }
        r2.add(r1);	 Catch:{ all -> 0x0045 }
        goto L_0x0021;
    L_0x0045:
        r1 = move-exception;
        r2 = r0.iterator();
    L_0x004a:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0076;
    L_0x0050:
        r0 = r2.next();
        r0 = (com.facebook.common.references.CloseableReference) r0;
        com.facebook.common.references.CloseableReference.closeSafely(r0);
        goto L_0x004a;
    L_0x005a:
        r1 = 0;
        r2.add(r1);	 Catch:{ all -> 0x0045 }
        goto L_0x0021;	 Catch:{ all -> 0x0045 }
    L_0x005f:
        r5.onNewResultListImpl(r2);	 Catch:{ all -> 0x0045 }
        r1 = r0.iterator();
    L_0x0066:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0007;
    L_0x006c:
        r0 = r1.next();
        r0 = (com.facebook.common.references.CloseableReference) r0;
        com.facebook.common.references.CloseableReference.closeSafely(r0);
        goto L_0x0066;
    L_0x0076:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.datasource.BaseListBitmapDataSubscriber.onNewResultImpl(com.facebook.datasource.DataSource):void");
    }

    protected abstract void onNewResultListImpl(List<Bitmap> list);
}
