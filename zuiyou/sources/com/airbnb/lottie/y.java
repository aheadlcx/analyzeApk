package com.airbnb.lottie;

import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class y extends d {
    private final az c;
    @Nullable
    private final j d;

    y(az azVar, @Nullable ay ayVar, @Nullable ShapeStroke shapeStroke, @Nullable be beVar, @Nullable j jVar, Callback callback) {
        super(callback);
        this.c = azVar;
        this.d = jVar;
        a(ayVar, shapeStroke, beVar);
    }

    private void a(ay ayVar, ShapeStroke shapeStroke, be beVar) {
        if (this.d != null) {
            a(this.d.f());
        }
        List arrayList = new ArrayList(this.c.a());
        Collections.reverse(arrayList);
        j jVar = null;
        be beVar2 = beVar;
        ShapeStroke shapeStroke2 = shapeStroke;
        ay ayVar2 = ayVar;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof j) {
                jVar = (j) obj;
            } else if (obj instanceof ShapeStroke) {
                shapeStroke2 = (ShapeStroke) obj;
            } else if (obj instanceof ay) {
                ayVar2 = (ay) obj;
            } else if (obj instanceof be) {
                beVar2 = (be) obj;
            } else if (obj instanceof bd) {
                a(new bc((bd) obj, ayVar2, shapeStroke2, beVar2, jVar, getCallback()));
            } else if (obj instanceof au) {
                a(new at((au) obj, ayVar2, shapeStroke2, beVar2, jVar, getCallback()));
            } else if (obj instanceof p) {
                a(new u((p) obj, ayVar2, shapeStroke2, beVar2, jVar, getCallback()));
            } else if (obj instanceof PolystarShape) {
                a(new ar((PolystarShape) obj, ayVar2, shapeStroke2, beVar2, jVar, getCallback()));
            } else if (obj instanceof az) {
                a(new y((az) obj, ayVar2, shapeStroke2, beVar2, jVar, getCallback()));
            }
        }
    }
}
