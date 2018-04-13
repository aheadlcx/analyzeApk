package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class b {
    private static Method a;

    static final class a {
        private AudioAttributes a;

        private a(AudioAttributes audioAttributes) {
            this.a = audioAttributes;
        }

        public static a wrap(@NonNull AudioAttributes audioAttributes) {
            if (audioAttributes != null) {
                return new a(audioAttributes);
            }
            throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
        }

        public AudioAttributes unwrap() {
            return this.a;
        }
    }

    public static int toLegacyStreamType(a aVar) {
        Throwable e;
        AudioAttributes unwrap = aVar.unwrap();
        try {
            if (a == null) {
                a = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[]{AudioAttributes.class});
            }
            return ((Integer) a.invoke(null, new Object[]{unwrap})).intValue();
        } catch (NoSuchMethodException e2) {
            e = e2;
            Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", e);
            return -1;
        } catch (InvocationTargetException e3) {
            e = e3;
            Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", e);
            return -1;
        } catch (IllegalAccessException e4) {
            e = e4;
            Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", e);
            return -1;
        } catch (ClassCastException e5) {
            e = e5;
            Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", e);
            return -1;
        }
    }
}
