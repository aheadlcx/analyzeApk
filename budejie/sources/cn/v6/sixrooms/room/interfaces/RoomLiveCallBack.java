package cn.v6.sixrooms.room.interfaces;

public interface RoomLiveCallBack {
    void changeDefinition();

    int getDefinitionStatus();

    boolean isChangeable();

    boolean isChangeing();
}
