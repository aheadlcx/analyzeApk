package com.opensource.svgaplayer;

import android.widget.ImageView.ScaleType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001f\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\u00000\u000e2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/opensource/svgaplayer/SGVADrawer;", "", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "(Lcom/opensource/svgaplayer/SVGAVideoEntity;)V", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", "drawFrame", "", "frameIndex", "", "scaleType", "Landroid/widget/ImageView$ScaleType;", "requestFrameSprites", "", "Lcom/opensource/svgaplayer/SGVADrawer$SVGADrawerSprite;", "requestFrameSprites$library_release", "SVGADrawerSprite", "library_release"}, k = 1, mv = {1, 1, 6})
public class SGVADrawer {
    @NotNull
    private final SVGAVideoEntity a;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/opensource/svgaplayer/SGVADrawer$SVGADrawerSprite;", "", "imageKey", "", "frameEntity", "Lcom/opensource/svgaplayer/SVGAVideoSpriteFrameEntity;", "(Lcom/opensource/svgaplayer/SGVADrawer;Ljava/lang/String;Lcom/opensource/svgaplayer/SVGAVideoSpriteFrameEntity;)V", "getFrameEntity", "()Lcom/opensource/svgaplayer/SVGAVideoSpriteFrameEntity;", "getImageKey", "()Ljava/lang/String;", "library_release"}, k = 1, mv = {1, 1, 6})
    public final class SVGADrawerSprite {
        final /* synthetic */ SGVADrawer a;
        @Nullable
        private final String b;
        @NotNull
        private final SVGAVideoSpriteFrameEntity c;

        public SVGADrawerSprite(SGVADrawer sGVADrawer, @Nullable String str, @NotNull SVGAVideoSpriteFrameEntity sVGAVideoSpriteFrameEntity) {
            Intrinsics.checkParameterIsNotNull(sVGAVideoSpriteFrameEntity, "frameEntity");
            this.a = sGVADrawer;
            this.b = str;
            this.c = sVGAVideoSpriteFrameEntity;
        }

        @NotNull
        public final SVGAVideoSpriteFrameEntity getFrameEntity() {
            return this.c;
        }

        @Nullable
        public final String getImageKey() {
            return this.b;
        }
    }

    public SGVADrawer(@NotNull SVGAVideoEntity sVGAVideoEntity) {
        Intrinsics.checkParameterIsNotNull(sVGAVideoEntity, "videoItem");
        this.a = sVGAVideoEntity;
    }

    @NotNull
    public final SVGAVideoEntity getVideoItem() {
        return this.a;
    }

    @NotNull
    public final List<SVGADrawerSprite> requestFrameSprites$library_release(int i) {
        Collection arrayList = new ArrayList();
        for (SVGAVideoSpriteEntity sVGAVideoSpriteEntity : this.a.getSprites()) {
            Object obj;
            if (i >= sVGAVideoSpriteEntity.getFrames().size()) {
                obj = null;
            } else if (((SVGAVideoSpriteFrameEntity) sVGAVideoSpriteEntity.getFrames().get(i)).getAlpha() <= 0.0d) {
                obj = null;
            } else {
                SVGADrawerSprite sVGADrawerSprite = new SVGADrawerSprite(this, sVGAVideoSpriteEntity.getImageKey(), (SVGAVideoSpriteFrameEntity) sVGAVideoSpriteEntity.getFrames().get(i));
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return (List) arrayList;
    }

    public void drawFrame(int i, @NotNull ScaleType scaleType) {
        Intrinsics.checkParameterIsNotNull(scaleType, "scaleType");
    }
}
