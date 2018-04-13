package com.sprite.ads.nati.reporter;

import android.view.View;

public interface Reporter {

    public interface OnChangeAdListener {
        void onChange();
    }

    void onClicked(View view);

    void onExposured(View view);

    void onPlay(View view);
}
