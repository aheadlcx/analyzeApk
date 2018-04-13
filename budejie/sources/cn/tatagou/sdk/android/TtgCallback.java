package cn.tatagou.sdk.android;

import java.util.Map;

public abstract class TtgCallback {
    public void getApConfig(Map<String, String> map) {
    }

    public void refreshFinish() {
    }

    public void countUnReadFeedback(int i) {
    }

    public void onInitSuccess(Map<String, String> map) {
    }

    public void onBcSuccess() {
    }

    public void onBcFial(int i, String str) {
    }
}
