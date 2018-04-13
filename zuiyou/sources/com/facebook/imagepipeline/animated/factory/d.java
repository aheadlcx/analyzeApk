package com.facebook.imagepipeline.animated.factory;

import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.d.e;

public class d {
    private static boolean a;
    private static c b = null;

    public static c a(f fVar, e eVar) {
        if (!a) {
            try {
                b = (c) Class.forName("com.facebook.imagepipeline.animated.factory.AnimatedFactoryImplSupport").getConstructor(new Class[]{f.class, e.class}).newInstance(new Object[]{fVar, eVar});
            } catch (Throwable th) {
            }
            if (b != null) {
                a = true;
                return b;
            }
            try {
                b = (c) Class.forName("com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl").getConstructor(new Class[]{f.class, e.class}).newInstance(new Object[]{fVar, eVar});
            } catch (Throwable th2) {
            }
            a = true;
        }
        return b;
    }
}
