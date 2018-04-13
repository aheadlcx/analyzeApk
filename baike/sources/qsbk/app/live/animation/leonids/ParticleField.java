package qsbk.app.live.animation.leonids;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.plattysoft.leonids.Particle;
import java.util.ArrayList;

public class ParticleField extends View {
    private ArrayList<Particle> a;

    public ParticleField(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ParticleField(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ParticleField(Context context) {
        super(context);
    }

    public void setParticles(ArrayList<Particle> arrayList) {
        this.a = arrayList;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (this.a) {
            for (int i = 0; i < this.a.size(); i++) {
                ((Particle) this.a.get(i)).draw(canvas);
            }
        }
    }
}
