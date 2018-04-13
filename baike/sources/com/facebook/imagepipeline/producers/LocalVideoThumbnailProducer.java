package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "VideoThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    private java.lang.String getLocalFilePath(com.facebook.imagepipeline.request.ImageRequest r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0015 in list [B:18:0x0062]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r8 = this;
        r7 = 0;
        r6 = 1;
        r5 = 0;
        r1 = r9.getSourceUri();
        r0 = com.facebook.common.util.UriUtil.isLocalFileUri(r1);
        if (r0 == 0) goto L_0x0016;
    L_0x000d:
        r0 = r9.getSourceFile();
        r5 = r0.getPath();
    L_0x0015:
        return r5;
    L_0x0016:
        r0 = com.facebook.common.util.UriUtil.isLocalContentUri(r1);
        if (r0 == 0) goto L_0x0015;
    L_0x001c:
        r0 = android.os.Build.VERSION.SDK_INT;
        r2 = 19;
        if (r0 < r2) goto L_0x0073;
    L_0x0022:
        r0 = "com.android.providers.media.documents";
        r2 = r1.getAuthority();
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0073;
    L_0x002e:
        r0 = android.provider.DocumentsContract.getDocumentId(r1);
        r1 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        r3 = "_id=?";
        r4 = new java.lang.String[r6];
        r2 = ":";
        r0 = r0.split(r2);
        r0 = r0[r6];
        r4[r7] = r0;
    L_0x0042:
        r0 = r8.mContentResolver;
        r2 = new java.lang.String[r6];
        r6 = "_data";
        r2[r7] = r6;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x0066;
    L_0x0050:
        r0 = r1.moveToFirst();	 Catch:{ all -> 0x006c }
        if (r0 == 0) goto L_0x0066;	 Catch:{ all -> 0x006c }
    L_0x0056:
        r0 = "_data";	 Catch:{ all -> 0x006c }
        r0 = r1.getColumnIndexOrThrow(r0);	 Catch:{ all -> 0x006c }
        r5 = r1.getString(r0);	 Catch:{ all -> 0x006c }
        if (r1 == 0) goto L_0x0015;
    L_0x0062:
        r1.close();
        goto L_0x0015;
    L_0x0066:
        if (r1 == 0) goto L_0x0015;
    L_0x0068:
        r1.close();
        goto L_0x0015;
    L_0x006c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0072;
    L_0x006f:
        r1.close();
    L_0x0072:
        throw r0;
    L_0x0073:
        r4 = r5;
        r3 = r5;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.getLocalFilePath(com.facebook.imagepipeline.request.ImageRequest):java.lang.String");
    }

    public LocalVideoThumbnailProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        ProducerListener listener = producerContext.getListener();
        String id = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final ProducerListener producerListener = listener;
        final String str = id;
        final Runnable anonymousClass1 = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, listener, PRODUCER_NAME, id) {
            protected void onSuccess(CloseableReference<CloseableImage> closeableReference) {
                super.onSuccess(closeableReference);
                producerListener.onUltimateProducerReached(str, LocalVideoThumbnailProducer.PRODUCER_NAME, closeableReference != null);
            }

            protected void onFailure(Exception exception) {
                super.onFailure(exception);
                producerListener.onUltimateProducerReached(str, LocalVideoThumbnailProducer.PRODUCER_NAME, false);
            }

            protected CloseableReference<CloseableImage> getResult() throws Exception {
                String access$000 = LocalVideoThumbnailProducer.this.getLocalFilePath(imageRequest);
                if (access$000 == null) {
                    return null;
                }
                Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(access$000, LocalVideoThumbnailProducer.calculateKind(imageRequest));
                if (createVideoThumbnail != null) {
                    return CloseableReference.of(new CloseableStaticBitmap(createVideoThumbnail, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0));
                }
                return null;
            }

            protected Map<String, String> getExtraMapOnSuccess(CloseableReference<CloseableImage> closeableReference) {
                return ImmutableMap.of(LocalVideoThumbnailProducer.CREATED_THUMBNAIL, String.valueOf(closeableReference != null));
            }

            protected void disposeResult(CloseableReference<CloseableImage> closeableReference) {
                CloseableReference.closeSafely((CloseableReference) closeableReference);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                anonymousClass1.cancel();
            }
        });
        this.mExecutor.execute(anonymousClass1);
    }

    private static int calculateKind(ImageRequest imageRequest) {
        if (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) {
            return 1;
        }
        return 3;
    }
}
