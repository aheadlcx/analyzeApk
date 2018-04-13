package butterknife.a;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class a implements OnClickListener {
    static boolean a = true;
    private static final Runnable b = new Runnable() {
        public void run() {
            a.a = true;
        }
    };

    public abstract void a(View view);

    public final void onClick(View view) {
        if (a) {
            a = false;
            view.post(b);
            a(view);
        }
    }
}
