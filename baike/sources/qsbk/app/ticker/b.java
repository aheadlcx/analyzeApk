package qsbk.app.ticker;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class b {
    final ArrayList<a> a = new ArrayList();
    private final c b;
    private char[] c;
    private Map<Character, Integer> d;

    b(c cVar) {
        this.b = cVar;
    }

    void a(char[] cArr) {
        this.c = cArr;
        this.d = new HashMap(cArr.length);
        for (int i = 0; i < cArr.length; i++) {
            this.d.put(Character.valueOf(cArr[i]), Integer.valueOf(i));
        }
    }

    boolean b(char[] cArr) {
        int length = cArr.length;
        if (length != this.a.size()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (cArr[i] != ((a) this.a.get(i)).b()) {
                return false;
            }
        }
        return true;
    }

    void c(char[] cArr) {
        if (this.c == null) {
            throw new IllegalStateException("Need to call setCharacterList(char[]) first.");
        }
        int i = 0;
        while (i < this.a.size()) {
            int i2;
            if (((a) this.a.get(i)).c() > 0.0f) {
                i2 = i + 1;
            } else {
                this.a.remove(i);
                i2 = i;
            }
            i = i2;
        }
        int[] computeColumnActions = LevenshteinUtils.computeColumnActions(d(), cArr);
        int i3 = 0;
        int i4 = 0;
        for (i = 0; i < computeColumnActions.length; i++) {
            switch (computeColumnActions[i]) {
                case 0:
                    break;
                case 1:
                    this.a.add(i4, new a(this.c, this.d, this.b));
                    break;
                case 2:
                    ((a) this.a.get(i4)).a('\u0000');
                    i4++;
                    continue;
                default:
                    throw new IllegalArgumentException("Unknown action: " + computeColumnActions[i]);
            }
            ((a) this.a.get(i4)).a(cArr[i3]);
            i4++;
            i3++;
        }
    }

    void a() {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((a) this.a.get(i)).e();
        }
    }

    void a(float f) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((a) this.a.get(i)).a(f);
        }
    }

    float b() {
        float f = 0.0f;
        for (int i = 0; i < this.a.size(); i++) {
            f += ((a) this.a.get(i)).d();
        }
        return f;
    }

    float c() {
        float f = 0.0f;
        for (int i = 0; i < this.a.size(); i++) {
            f += ((a) this.a.get(i)).c();
        }
        return f;
    }

    char[] d() {
        int size = this.a.size();
        char[] cArr = new char[size];
        for (int i = 0; i < size; i++) {
            cArr[i] = ((a) this.a.get(i)).a();
        }
        return cArr;
    }

    void a(Canvas canvas, Paint paint) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.a.get(i);
            aVar.a(canvas, paint);
            canvas.translate(aVar.c(), 0.0f);
        }
    }
}
