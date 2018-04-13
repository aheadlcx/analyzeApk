package com.opensource.svgaplayer;

import com.opensource.svgaplayer.proto.FrameEntity;
import com.opensource.svgaplayer.proto.SpriteEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/opensource/svgaplayer/SVGAVideoSpriteEntity;", "", "obj", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "Lcom/opensource/svgaplayer/proto/SpriteEntity;", "(Lcom/opensource/svgaplayer/proto/SpriteEntity;)V", "frames", "", "Lcom/opensource/svgaplayer/SVGAVideoSpriteFrameEntity;", "getFrames", "()Ljava/util/List;", "imageKey", "", "getImageKey", "()Ljava/lang/String;", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAVideoSpriteEntity {
    @Nullable
    private final String a;
    @NotNull
    private final List<SVGAVideoSpriteFrameEntity> b;

    @Nullable
    public final String getImageKey() {
        return this.a;
    }

    @NotNull
    public final List<SVGAVideoSpriteFrameEntity> getFrames() {
        return this.b;
    }

    public SVGAVideoSpriteEntity(@NotNull JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "obj");
        this.a = jSONObject.optString("imageKey");
        List arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("frames");
        if (optJSONArray != null) {
            IntRange until = e.until(0, optJSONArray.length());
            int first = until.getFirst();
            int last = until.getLast();
            if (first <= last) {
                int i = first;
                while (true) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        SVGAVideoSpriteFrameEntity sVGAVideoSpriteFrameEntity = new SVGAVideoSpriteFrameEntity(optJSONObject);
                        if (sVGAVideoSpriteFrameEntity.getShapes().isEmpty()) {
                            first = 0;
                        } else {
                            first = 1;
                        }
                        if (first != 0) {
                            SVGAVideoShapeEntity sVGAVideoShapeEntity = (SVGAVideoShapeEntity) v.first(sVGAVideoSpriteFrameEntity.getShapes());
                            if (sVGAVideoShapeEntity != null) {
                                if (sVGAVideoShapeEntity.isKeep() && arrayList.size() > 0) {
                                    sVGAVideoSpriteFrameEntity.setShapes(((SVGAVideoSpriteFrameEntity) v.last(arrayList)).getShapes());
                                }
                            }
                        }
                        arrayList.add(sVGAVideoSpriteFrameEntity);
                    }
                    if (i == last) {
                        break;
                    }
                    i++;
                }
            }
        }
        this.b = v.toList(arrayList);
    }

    public SVGAVideoSpriteEntity(@NotNull SpriteEntity spriteEntity) {
        List list;
        Intrinsics.checkParameterIsNotNull(spriteEntity, "obj");
        this.a = spriteEntity.imageKey;
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = (SVGAVideoSpriteFrameEntity) null;
        List list2 = spriteEntity.frames;
        if (list2 != null) {
            Iterable<FrameEntity> iterable = list2;
            Collection arrayList = new ArrayList(r.collectionSizeOrDefault(iterable, 10));
            for (FrameEntity frameEntity : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(frameEntity, "it");
                SVGAVideoSpriteFrameEntity sVGAVideoSpriteFrameEntity = new SVGAVideoSpriteFrameEntity(frameEntity);
                if ((!((Collection) sVGAVideoSpriteFrameEntity.getShapes()).isEmpty() ? 1 : null) != null) {
                    SVGAVideoShapeEntity sVGAVideoShapeEntity = (SVGAVideoShapeEntity) v.first(sVGAVideoSpriteFrameEntity.getShapes());
                    if (sVGAVideoShapeEntity != null) {
                        if (sVGAVideoShapeEntity.isKeep()) {
                            SVGAVideoSpriteFrameEntity sVGAVideoSpriteFrameEntity2 = (SVGAVideoSpriteFrameEntity) objectRef.element;
                            if (sVGAVideoSpriteFrameEntity2 != null) {
                                sVGAVideoSpriteFrameEntity.setShapes(sVGAVideoSpriteFrameEntity2.getShapes());
                            }
                        }
                    }
                }
                objectRef.element = sVGAVideoSpriteFrameEntity;
                arrayList.add(sVGAVideoSpriteFrameEntity);
            }
            list = (List) arrayList;
        } else {
            list = q.emptyList();
        }
        this.b = list;
    }
}
