package cn.v6.sixrooms.ui.phone.input;

public interface RoomInputListener {
    void changeState(KeyboardState keyboardState);

    void dismiss();

    void show();
}
