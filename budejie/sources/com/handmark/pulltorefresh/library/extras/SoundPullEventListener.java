package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import java.util.HashMap;

public class SoundPullEventListener<V extends View> implements OnPullEventListener<V> {
    private final Context a;
    private final HashMap<State, Integer> b = new HashMap();
    private MediaPlayer c;

    public SoundPullEventListener(Context context) {
        this.a = context;
    }

    public final void onPullEvent(PullToRefreshBase<V> pullToRefreshBase, State state, Mode mode) {
        Integer num = (Integer) this.b.get(state);
        if (num != null) {
            int intValue = num.intValue();
            if (this.c != null) {
                this.c.stop();
                this.c.release();
            }
            this.c = MediaPlayer.create(this.a, intValue);
            if (this.c != null) {
                this.c.start();
            }
        }
    }

    public void addSoundEvent(State state, int i) {
        this.b.put(state, Integer.valueOf(i));
    }

    public void clearSounds() {
        this.b.clear();
    }

    public MediaPlayer getCurrentMediaPlayer() {
        return this.c;
    }
}
