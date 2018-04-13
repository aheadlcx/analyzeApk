package cn.v6.sixrooms.ui.phone.input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import cn.v6.sixrooms.R;

public class LiveRoomInputLayoutFactory implements IRoomInputLayoutFactory {
    private RoomInputTheme a;
    private LayoutInflater b;

    public LiveRoomInputLayoutFactory(Context context, RoomInputTheme roomInputTheme) {
        this.a = roomInputTheme;
        this.b = LayoutInflater.from(context);
    }

    public View generateLayout() {
        int i = 0;
        switch (k.a[this.a.ordinal()]) {
            case 1:
                i = R.layout.input_dialog;
                break;
            case 2:
                i = R.layout.input_dialog_full_screen;
                break;
            case 3:
                i = R.layout.input_dialog_full_screen_contact;
                break;
        }
        return this.b.inflate(i, null);
    }

    public int getExpressionImg() {
        switch (k.a[this.a.ordinal()]) {
            case 1:
                return R.drawable.rooms_third_expression_white;
            case 2:
                return R.drawable.rooms_third_expression_black;
            case 3:
                return R.drawable.rooms_third_expression_black;
            default:
                return 0;
        }
    }

    public int getKeyboardImg() {
        switch (k.a[this.a.ordinal()]) {
            case 1:
                return R.drawable.rooms_third_room_keyboard;
            case 2:
                return R.drawable.room_input_keyboard_selector;
            case 3:
                return R.drawable.room_input_keyboard_selector;
            default:
                return 0;
        }
    }

    public int getUnSpeakEditHintColor() {
        switch (k.a[this.a.ordinal()]) {
            case 1:
                return R.color.red_pay_text;
            case 2:
                return R.color.red_pay_text;
            case 3:
                return R.color.red_pay_text;
            default:
                return 0;
        }
    }

    public int getSpeakEditHintColor() {
        switch (k.a[this.a.ordinal()]) {
            case 1:
                return R.color.darkgray;
            case 2:
                return R.color.room_input_private_edit_hint;
            case 3:
                return R.color.room_input_private_edit_hint;
            default:
                return 0;
        }
    }
}
