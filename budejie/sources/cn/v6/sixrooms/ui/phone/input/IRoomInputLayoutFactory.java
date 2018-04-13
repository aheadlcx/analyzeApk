package cn.v6.sixrooms.ui.phone.input;

import android.view.View;

public interface IRoomInputLayoutFactory {
    View generateLayout();

    int getExpressionImg();

    int getKeyboardImg();

    int getSpeakEditHintColor();

    int getUnSpeakEditHintColor();
}
