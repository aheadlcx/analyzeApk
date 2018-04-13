package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.LiveRoom;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class qy implements HttpCallBack {
    final /* synthetic */ LiveFollowActivity a;

    qy(LiveFollowActivity liveFollowActivity) {
        this.a = liveFollowActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            if (jSONObject != null) {
                if (this.a.g == 1) {
                    this.a.h.clear();
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("lives");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        try {
                            LiveRoom parse = LiveRoom.parse(optJSONArray.getJSONObject(i));
                            if (!this.a.h.contains(parse)) {
                                this.a.h.add(parse);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.a.a.notifyDataSetChanged();
                if (this.a.g == 1) {
                    this.a.b.refreshDone();
                } else {
                    this.a.b.loadMoreDone(true);
                }
                if (this.a.h.size() == 0) {
                    this.a.d.set(UIHelper.getEmptyImg(), this.a.getResources().getString(R.string.has_no_followed_anchor));
                    this.a.d.show();
                } else {
                    this.a.d.hide();
                }
                if (!jSONObject.optBoolean("has_more")) {
                    this.a.b.setLoadMoreState(0, "已经到底了");
                    this.a.b.loadMoreDone(true);
                    this.a.b.setLoadMoreEnable(false);
                }
                this.a.g = this.a.g + 1;
            }
            this.a.e = false;
        }
    }

    public void onFailure(String str, String str2) {
        if (!this.a.isFinishing()) {
            this.a.e = false;
            ToastAndDialog.makeNegativeToast(this.a, str2).show();
            if (this.a.h.size() == 0) {
                this.a.d.set(UIHelper.getEmptyImg(), "还没有粉过任何主播");
                this.a.d.show();
            } else {
                this.a.d.hide();
            }
            if (this.a.g == 1) {
                this.a.b.refreshDone();
            } else {
                this.a.b.loadMoreDone(false);
            }
        }
    }
}
