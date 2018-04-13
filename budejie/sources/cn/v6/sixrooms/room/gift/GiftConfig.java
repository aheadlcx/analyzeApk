package cn.v6.sixrooms.room.gift;

import java.util.Map;

public class GiftConfig {
    private GiftTypes gifts;
    private Map<String, String> shinegift;
    private String ver;

    public GiftTypes getGifts() {
        return this.gifts;
    }

    public Map<String, String> getShinegift() {
        return this.shinegift;
    }
}
