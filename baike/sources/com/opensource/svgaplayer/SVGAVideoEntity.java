package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import com.opensource.svgaplayer.proto.MovieEntity;
import com.opensource.svgaplayer.proto.MovieParams;
import com.opensource.svgaplayer.proto.SpriteEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import okio.ByteString;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010.\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u0007H\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u00100\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u0007H\u0002J\u0010\u00100\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R$\u0010\u0016\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR<\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R0\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\"0!@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010)\u001a\u00020(2\u0006\u0010\t\u001a\u00020(@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00061"}, d2 = {"Lcom/opensource/svgaplayer/SVGAVideoEntity;", "", "obj", "Lorg/json/JSONObject;", "cacheDir", "Ljava/io/File;", "(Lorg/json/JSONObject;Ljava/io/File;)V", "Lcom/opensource/svgaplayer/proto/MovieEntity;", "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;)V", "<set-?>", "", "FPS", "getFPS", "()I", "setFPS", "(I)V", "antiAlias", "", "getAntiAlias", "()Z", "setAntiAlias", "(Z)V", "frames", "getFrames", "setFrames", "Ljava/util/HashMap;", "", "Landroid/graphics/Bitmap;", "images", "getImages", "()Ljava/util/HashMap;", "setImages", "(Ljava/util/HashMap;)V", "", "Lcom/opensource/svgaplayer/SVGAVideoSpriteEntity;", "sprites", "getSprites", "()Ljava/util/List;", "setSprites", "(Ljava/util/List;)V", "Lcom/opensource/svgaplayer/SVGARect;", "videoSize", "getVideoSize", "()Lcom/opensource/svgaplayer/SVGARect;", "setVideoSize", "(Lcom/opensource/svgaplayer/SVGARect;)V", "resetImages", "", "resetSprites", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAVideoEntity {
    private boolean a = true;
    @NotNull
    private SVGARect b = new SVGARect(0.0d, 0.0d, 0.0d, 0.0d);
    private int c = 15;
    private int d;
    @NotNull
    private List<SVGAVideoSpriteEntity> e = q.emptyList();
    @NotNull
    private HashMap<String, Bitmap> f = new HashMap();
    private File g;

    public final boolean getAntiAlias() {
        return this.a;
    }

    public final void setAntiAlias(boolean z) {
        this.a = z;
    }

    @NotNull
    public final SVGARect getVideoSize() {
        return this.b;
    }

    public final int getFPS() {
        return this.c;
    }

    public final int getFrames() {
        return this.d;
    }

    @NotNull
    public final List<SVGAVideoSpriteEntity> getSprites() {
        return this.e;
    }

    @NotNull
    public final HashMap<String, Bitmap> getImages() {
        return this.f;
    }

    public SVGAVideoEntity(@NotNull JSONObject jSONObject, @NotNull File file) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "obj");
        Intrinsics.checkParameterIsNotNull(file, "cacheDir");
        this.g = file;
        JSONObject optJSONObject = jSONObject.optJSONObject("movie");
        if (optJSONObject != null) {
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("viewBox");
            if (optJSONObject2 != null) {
                this.b = new SVGARect(0.0d, 0.0d, optJSONObject2.optDouble(IndexEntry.COLUMN_NAME_WIDTH, 0.0d), optJSONObject2.optDouble(IndexEntry.COLUMN_NAME_HEIGHT, 0.0d));
            }
            this.c = optJSONObject.optInt("fps", 20);
            this.d = optJSONObject.optInt("frames", 0);
        }
        a(jSONObject);
        b(jSONObject);
    }

    public SVGAVideoEntity(@NotNull MovieEntity movieEntity, @NotNull File file) {
        float f = 0.0f;
        Intrinsics.checkParameterIsNotNull(movieEntity, "obj");
        Intrinsics.checkParameterIsNotNull(file, "cacheDir");
        this.g = file;
        MovieParams movieParams = movieEntity.params;
        if (movieParams != null) {
            Float f2 = movieParams.viewBoxWidth;
            double floatValue = (double) (f2 != null ? f2.floatValue() : 0.0f);
            f2 = movieParams.viewBoxHeight;
            if (f2 != null) {
                f = f2.floatValue();
            }
            this.b = new SVGARect(0.0d, 0.0d, floatValue, (double) f);
            Integer num = movieParams.fps;
            this.c = num != null ? num.intValue() : 20;
            num = movieParams.frames;
            this.d = num != null ? num.intValue() : 0;
        }
        a(movieEntity);
        b(movieEntity);
    }

    private final void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("images");
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                Object decodeFile;
                String str = (String) keys.next();
                SVGAVideoEntityKt.a.inPreferredConfig = Config.RGB_565;
                String str2 = this.g.getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + optJSONObject.get(str);
                if (new File(str2).exists()) {
                    decodeFile = BitmapFactory.decodeFile(str2, SVGAVideoEntityKt.a);
                } else {
                    decodeFile = null;
                }
                if (decodeFile != null) {
                    this.f.put(str, decodeFile);
                } else {
                    str2 = this.g.getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + ".png";
                    if (str2 != null) {
                        if (!new File(str2).exists()) {
                            str2 = null;
                        }
                        if (str2 != null) {
                            Bitmap decodeFile2 = BitmapFactory.decodeFile(str2, SVGAVideoEntityKt.a);
                            if (decodeFile2 != null) {
                                Bitmap bitmap = (Bitmap) this.f.put(str, decodeFile2);
                            }
                        }
                    }
                }
            }
        }
    }

    private final void a(MovieEntity movieEntity) {
        Map map = movieEntity.images;
        if (map != null) {
            Set<Entry> entrySet = map.entrySet();
            if (entrySet != null) {
                for (Entry entry : entrySet) {
                    String str = (String) entry.getKey();
                    SVGAVideoEntityKt.a.inPreferredConfig = Config.RGB_565;
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(((ByteString) entry.getValue()).toByteArray(), 0, ((ByteString) entry.getValue()).size(), SVGAVideoEntityKt.a);
                    if (decodeByteArray != null) {
                        this.f.put(str, decodeByteArray);
                    } else {
                        String utf8 = ((ByteString) entry.getValue()).utf8();
                        if (utf8 != null) {
                            Object decodeFile;
                            utf8 = this.g.getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + utf8;
                            if (new File(utf8).exists()) {
                                decodeFile = BitmapFactory.decodeFile(utf8, SVGAVideoEntityKt.a);
                            } else {
                                decodeFile = null;
                            }
                            Bitmap bitmap;
                            if (decodeFile != null) {
                                bitmap = (Bitmap) this.f.put(str, decodeFile);
                            } else {
                                utf8 = this.g.getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + ".png";
                                if (utf8 != null) {
                                    if (!new File(utf8).exists()) {
                                        utf8 = null;
                                    }
                                    if (utf8 != null) {
                                        bitmap = BitmapFactory.decodeFile(utf8, SVGAVideoEntityKt.a);
                                        if (bitmap != null) {
                                            bitmap = (Bitmap) this.f.put(str, bitmap);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private final void b(JSONObject jSONObject) {
        List arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("sprites");
        if (optJSONArray != null) {
            IntRange until = e.until(0, optJSONArray.length());
            int first = until.getFirst();
            int last = until.getLast();
            if (first <= last) {
                while (true) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(first);
                    if (optJSONObject != null) {
                        arrayList.add(new SVGAVideoSpriteEntity(optJSONObject));
                    }
                    if (first == last) {
                        break;
                    }
                    first++;
                }
            }
        }
        this.e = v.toList(arrayList);
    }

    private final void b(MovieEntity movieEntity) {
        List list;
        List list2 = movieEntity.sprites;
        if (list2 != null) {
            Iterable<SpriteEntity> iterable = list2;
            Collection arrayList = new ArrayList(r.collectionSizeOrDefault(iterable, 10));
            for (SpriteEntity spriteEntity : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(spriteEntity, "it");
                arrayList.add(new SVGAVideoSpriteEntity(spriteEntity));
            }
            list = (List) arrayList;
        } else {
            list = q.emptyList();
        }
        this.e = list;
    }
}
