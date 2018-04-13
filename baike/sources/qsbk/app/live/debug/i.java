package qsbk.app.live.debug;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.utils.AppUtils;

class i implements OnClickListener {
    final /* synthetic */ LiveGameDebugActivity a;

    i(LiveGameDebugActivity liveGameDebugActivity) {
        this.a = liveGameDebugActivity;
    }

    public void onClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toLive(this.a.getActivity(), (CommonVideo) AppUtils.fromJson("{\nstatus: 1,\nstream_id: \"z1.remix.5719ec95fb16df4e47027001\",\nlive_type: 1,\ncreated_at: 1467015757,\nauthor: {\norigin: 2,\nis_followed: false,\norigin_id: 4,\nis_follow: false,\nintro: \"微笑一下吧～\",\nnick_id: 1000057,\nid: 4,\nname: \"Claire的小屋~\",\ngender: \"F\",\nplatform_id: 4,\nheadurl: \"http://avatar.app-remix.com/SPYOFVAYLKIXFS7S.jpg?imageMogr2/format/jpg/thumbnail/300x300/auto-orient\"\n},\nupdate_at: 1467015757,\nvisitors_count: 40,\nrtmp_live_url: \"rtmp://pili-live-rtmp.app-remix.com/remix/5719ec95fb16df4e47027001\",\naccumulated_count: 40,\nroom_id: 53254842898698240,\nlocation: \"\",\nhdl_live_url: \"http://pili-live-hdl.app-remix.com/remix/5719ec95fb16df4e47027001.flv\",\nshare: {\nurl: \"http://www.app-remix.com/share/live/4\",\ncaption: \"Claire的小屋~在热猫开直播，destiny！厉害了，我的宝宝们。\",\nwb_info: \"直播传送门猛戳\",\ntitle: \"热猫\"\n},\ncontent: \"destiny\",\nthumbnail_url: \"http://avatar.app-remix.com/SPYOFVAYLKIXFS7S.jpg?imageMogr2/format/jpg/thumbnail/480x480/auto-orient\"\n}", CommonVideo.class));
    }
}
