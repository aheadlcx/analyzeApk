package qsbk.app.live;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.Serializable;
import qsbk.app.Constants;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.live.ui.LivePullActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.Live;
import qsbk.app.model.LiveRoom;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.utils.LiveUtil;

public class LivePullLauncher {
    public static final String STSOURCE = "stSource";
    public static final String STSOURCE_discoverlist = "discoverlist";
    public static final String STSOURCE_followlist = "followlist";
    public static final String STSOURCE_im = "im";
    public static final String STSOURCE_livebanner = "livebanner";
    public static final String STSOURCE_livelist = "livelist";
    public static final String STSOURCE_personallist = "personallist";
    public static final String STSOURCE_pushopen = "pushopen";
    public static final String TAB_INDEX = "tabIndex";
    public Context context;
    public Fragment fragment;
    public int from;
    public boolean fromAd;
    public long roomId;
    public String stSource;
    public int tabIndex;
    public QsbkCommonVideo video;
    public String videoId;

    private LivePullLauncher() {
    }

    public static LivePullLauncher from(Context context) {
        LivePullLauncher livePullLauncher = new LivePullLauncher();
        livePullLauncher.context = context;
        return livePullLauncher;
    }

    public static LivePullLauncher from(Fragment fragment) {
        LivePullLauncher livePullLauncher = new LivePullLauncher();
        livePullLauncher.fragment = fragment;
        livePullLauncher.context = fragment.getActivity();
        return livePullLauncher;
    }

    public LivePullLauncher withStSource(String str) {
        this.stSource = str;
        return this;
    }

    public LivePullLauncher withTabIndex(int i) {
        this.tabIndex = i;
        return this;
    }

    public LivePullLauncher with(QsbkCommonVideo qsbkCommonVideo) {
        this.video = qsbkCommonVideo;
        if (!(qsbkCommonVideo == null || qsbkCommonVideo.author == null)) {
            this.from = (int) qsbkCommonVideo.author.origin;
        }
        return this;
    }

    public LivePullLauncher withRoomId(long j) {
        this.roomId = j;
        return this;
    }

    public LivePullLauncher with(LiveRoom liveRoom) {
        this.video = LiveUtil.convert2CommonVideo(liveRoom);
        if (!(this.video == null || this.video.author == null)) {
            this.from = (int) this.video.author.origin;
        }
        return this;
    }

    public LivePullLauncher with(boolean z) {
        this.fromAd = z;
        return this;
    }

    public LivePullLauncher withSource(long j) {
        this.from = (int) j;
        return this;
    }

    public LivePullLauncher withSource(String str) {
        try {
            this.from = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return this;
    }

    public LivePullLauncher with(String str, boolean z) {
        this.fromAd = z;
        this.from = Integer.parseInt(str);
        return this;
    }

    public LivePullLauncher with(String str) {
        this.videoId = str;
        return this;
    }

    public void launch(int i) {
        if (this.video != null) {
            a(i);
        } else if (this.videoId != null) {
            String format;
            if (NetworkUtils.getInstance().isWifiAvailable() || !NetworkUtils.getInstance().isConnection4G()) {
                format = String.format(Constants.LIVE_INFO, new Object[]{this.videoId});
            } else {
                format = String.format(Constants.LIVE_INFO, new Object[]{this.videoId});
            }
            if (this.from > 0) {
                if (format.contains("?")) {
                    format = format + "&origin=" + this.from;
                } else {
                    format = format + "?origin=" + this.from;
                }
            }
            ProgressDialog progressDialog = new ProgressDialog(this.context);
            progressDialog.setCanceledOnTouchOutside(false);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new a(this, i, progressDialog));
            progressDialog.show();
            progressDialog.setOnDismissListener(new b(this, simpleHttpTask));
            simpleHttpTask.execute();
        }
    }

    private void a(Intent intent) {
        if (intent != null) {
            intent.putExtra(ParamKey.LONGITUDE, LocationHelper.getLongitude());
            intent.putExtra(ParamKey.LATITUDE, LocationHelper.getLatitude());
        }
    }

    private void a(int i) {
        Intent intent = new Intent();
        intent.setClass(this.context, LivePullActivity.class);
        intent.putExtra("live", this.video);
        String str = this.video.live_id;
        if (TextUtils.isEmpty(str)) {
            str = this.videoId;
        }
        intent.putExtra("liveUserId", str);
        intent.putExtra("liveUserSource", this.video.author.origin);
        intent.putExtra(STSOURCE, this.stSource);
        intent.putExtra(TAB_INDEX, this.tabIndex);
        a(intent);
        if (this.fragment != null) {
            this.fragment.startActivityForResult(intent, i);
        } else if (this.context instanceof Activity) {
            ((Activity) this.context).startActivityForResult(intent, i);
        } else {
            this.context.startActivity(intent);
        }
    }

    public void doLaunch(int i, String str, int i2) {
        Intent intent = new Intent();
        intent.setClass(this.context, LivePullActivity.class);
        intent.putExtra("liveUserId", str);
        intent.putExtra("liveUserSource", i2);
        intent.putExtra(STSOURCE, this.stSource);
        intent.putExtra(TAB_INDEX, this.tabIndex);
        a(intent);
        if (this.fragment != null) {
            this.fragment.startActivityForResult(intent, i);
        } else if (this.context instanceof Activity) {
            ((Activity) this.context).startActivityForResult(intent, i);
        } else {
            this.context.startActivity(intent);
        }
    }

    public void doLaunch(int i, Live live) {
        Serializable convert2CommonVideo = LiveUtil.convert2CommonVideo(live);
        Intent intent = new Intent();
        intent.setClass(this.context, LivePullActivity.class);
        intent.putExtra("live", convert2CommonVideo);
        intent.putExtra("liveUserId", String.valueOf(live.liveId));
        intent.putExtra("liveUserSource", live.author.origin);
        intent.putExtra(STSOURCE, this.stSource);
        intent.putExtra(TAB_INDEX, this.tabIndex);
        a(intent);
        if (this.fragment != null) {
            this.fragment.startActivityForResult(intent, i);
        } else if (this.context instanceof Activity) {
            ((Activity) this.context).startActivityForResult(intent, i);
        } else {
            this.context.startActivity(intent);
        }
    }

    public void doLaunch(int i, CircleArticle circleArticle) {
        LiveUtil.convert2CommonVideo(circleArticle);
        Intent intent = new Intent();
        intent.setClass(this.context, LivePullActivity.class);
        intent.putExtra("liveUserId", circleArticle.shareLink);
        intent.putExtra("liveUserSource", (long) circleArticle.live_origin);
        intent.putExtra(STSOURCE, this.stSource);
        intent.putExtra(TAB_INDEX, this.tabIndex);
        a(intent);
        if (this.fragment != null) {
            this.fragment.startActivityForResult(intent, i);
        } else if (this.context instanceof Activity) {
            ((Activity) this.context).startActivityForResult(intent, i);
        } else {
            this.context.startActivity(intent);
        }
    }
}
