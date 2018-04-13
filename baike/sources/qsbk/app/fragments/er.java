package qsbk.app.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.adapter.AnchorFlipAdapter;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.LiveRoom;

class er implements SimpleCallBack {
    final /* synthetic */ LiveTabsFragment a;

    er(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.a.q != null && this.a.getActivity() != null && jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("lives");
            if (optJSONArray != null) {
                this.a.q.stopFlipping();
                this.a.q.removeAllViews();
                this.a.w.clear();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        LiveRoom parse = LiveRoom.parse(optJSONArray.getJSONObject(i));
                        if (parse.isLiveBegin()) {
                            this.a.w.add(parse);
                            if (i < this.a.x) {
                                this.a.q.addView(AnchorFlipAdapter.getView(this.a.getActivity(), parse));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                this.a.t.setLiveRooms(this.a.w);
                if (this.a.w.size() <= 0) {
                    this.a.s.setVisibility(0);
                    this.a.t.setVisibility(8);
                    this.a.p.setVisibility(8);
                } else if (this.a.v || this.a.x <= 0) {
                    this.a.t.setVisibility(0);
                    this.a.p.setVisibility(8);
                    this.a.s.setVisibility(8);
                } else {
                    this.a.p.setVisibility(0);
                    if (this.a.q.getChildCount() > 1) {
                        this.a.q.startFlipping();
                    }
                    this.a.s.setVisibility(8);
                    this.a.t.setVisibility(8);
                }
            }
        }
    }

    public void onFailure(int i, String str) {
    }
}
