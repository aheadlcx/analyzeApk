package qsbk.app.video;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.gdtad.GdtVideoManager;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.TupleTwo;

public abstract class VideoInListHelper implements OnScrollListener {
    public static final String ARTICLE = "article";
    public static final String TAG = "tag";
    public static final int TAG_QIUSHI = 0;
    public static final int TAG_QIUYOUCIRCLE = 1;
    public static final String VIEW = "view";
    private final ListView a;
    protected VideoPlayerView b;
    protected NativeMediaADData c;
    SparseArray<Long> d = new SparseArray();
    private final int e;
    private boolean f = false;
    private a g = null;
    private boolean h = true;
    private int i = 0;
    private int j = 0;
    public boolean needCheck = false;

    private enum a {
        NORMAL_VIDEO,
        GDT_AD_VIDEO
    }

    public abstract Map<String, Object> getVideoFromItemView(View view);

    public VideoInListHelper(ListView listView) {
        this.a = listView;
        this.e = (int) (50.0f * listView.getResources().getDisplayMetrics().density);
    }

    public void setEnable(boolean z) {
        this.h = z;
    }

    public TupleTwo<MediaView, NativeMediaADData> getGdtVideoFromItemView(View view) {
        return null;
    }

    private int a(View view) {
        int i = 0;
        while (true) {
            int top = view.getTop() + i;
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof View)) {
                View view2 = (View) parent;
                if (view2 == this.a) {
                    return top;
                }
                view = view2;
                i = top;
            }
        }
        return Integer.MIN_VALUE;
    }

    private void a(boolean z) {
        CircleArticle circleArticle;
        View view;
        TupleTwo tupleTwo = null;
        View view2 = null;
        View view3 = null;
        int i = 0;
        int i2 = 0;
        CircleArticle circleArticle2 = null;
        while (i2 < this.a.getChildCount()) {
            int a;
            int height;
            TupleTwo gdtVideoFromItemView;
            int height2;
            TupleTwo tupleTwo2;
            View childAt = this.a.getChildAt(i2);
            Map videoFromItemView = getVideoFromItemView(childAt);
            View view4 = (VideoPlayerView) videoFromItemView.get(VIEW);
            Integer num = (Integer) videoFromItemView.get(TAG);
            if (num == null || num.intValue() != 1) {
                circleArticle = circleArticle2;
            } else {
                circleArticle = (CircleArticle) videoFromItemView.get("article");
            }
            if (view4 != null && view4.getVisibility() == 0) {
                a = a(view4);
                height = view4.getHeight() + a;
                if (a > 0 && height < this.a.getHeight()) {
                    VideoPlayerView videoPlayerView = view4;
                    view3 = childAt;
                    break;
                }
                a = (a + height) / 2;
                if (a > 0 && a < this.a.getHeight()) {
                    height = Math.min(a, this.a.getHeight() - a);
                    if (height > i) {
                        view2 = view4;
                        view3 = childAt;
                        if (view2 == null) {
                            gdtVideoFromItemView = getGdtVideoFromItemView(childAt);
                            if (gdtVideoFromItemView == null) {
                                view4 = (MediaView) gdtVideoFromItemView.getFirst();
                                NativeMediaADData nativeMediaADData = (NativeMediaADData) gdtVideoFromItemView.getSecond();
                                if (view4 != null && view4.getVisibility() == 0 && nativeMediaADData != null && nativeMediaADData.isVideoAD() && nativeMediaADData.isVideoLoaded()) {
                                    a = a(view4);
                                    height2 = view4.getHeight() + a;
                                    if (a <= 0 && height2 < this.a.getHeight()) {
                                        tupleTwo = gdtVideoFromItemView;
                                        view = view2;
                                        break;
                                    }
                                    height2 = (height2 + a) / 2;
                                    if (height2 > 0 && height2 < this.a.getHeight()) {
                                        height2 = Math.min(height2, this.a.getHeight() - height2);
                                        if (height2 > height) {
                                            tupleTwo2 = gdtVideoFromItemView;
                                        }
                                    }
                                }
                            } else {
                                height2 = height;
                                tupleTwo2 = tupleTwo;
                            }
                            i2++;
                            i = height2;
                            tupleTwo = tupleTwo2;
                            circleArticle2 = circleArticle;
                        }
                        height2 = height;
                        tupleTwo2 = tupleTwo;
                        i2++;
                        i = height2;
                        tupleTwo = tupleTwo2;
                        circleArticle2 = circleArticle;
                    }
                }
            }
            height = i;
            if (view2 == null) {
                gdtVideoFromItemView = getGdtVideoFromItemView(childAt);
                if (gdtVideoFromItemView == null) {
                    view4 = (MediaView) gdtVideoFromItemView.getFirst();
                    NativeMediaADData nativeMediaADData2 = (NativeMediaADData) gdtVideoFromItemView.getSecond();
                    a = a(view4);
                    height2 = view4.getHeight() + a;
                    if (a <= 0) {
                    }
                    height2 = (height2 + a) / 2;
                    height2 = Math.min(height2, this.a.getHeight() - height2);
                    if (height2 > height) {
                        tupleTwo2 = gdtVideoFromItemView;
                    }
                } else {
                    height2 = height;
                    tupleTwo2 = tupleTwo;
                }
                i2++;
                i = height2;
                tupleTwo = tupleTwo2;
                circleArticle2 = circleArticle;
            }
            height2 = height;
            tupleTwo2 = tupleTwo;
            i2++;
            i = height2;
            tupleTwo = tupleTwo2;
            circleArticle2 = circleArticle;
        }
        circleArticle = circleArticle2;
        view = view2;
        VideoPlayerView last = VideoPlayersManager.getLast();
        NativeMediaADData lastRef = GdtVideoManager.getLastRef();
        if (videoPlayerView != null) {
            if (!(last == null || !last.isPlaying() || videoPlayerView.equals(last))) {
                last.stop();
            }
            if (lastRef != null) {
                lastRef.stop();
            }
            if (this.c != null) {
                this.c.stop();
                this.c = null;
            }
            if (view3 != null) {
                onItemFound(view3, videoPlayerView, z);
            }
            if ((QsbkApp.getInstance().isAutoPlayConfiged() && this.h) || ((videoPlayerView instanceof SimpleVideoPlayerView) && ((SimpleVideoPlayerView) videoPlayerView).getDisplayMode() == 1)) {
                if (z) {
                    if (!videoPlayerView.equals(last)) {
                        if (videoPlayerView.e.getStartMs() == 0) {
                            videoPlayerView.setStartMs(getStartTime(videoPlayerView.getVideo()));
                        }
                        videoPlayerView.play();
                        putStartTime(videoPlayerView.getVideo(), 0);
                        this.b = videoPlayerView;
                        if (circleArticle != null) {
                            QiuyouCircleVideoLoopStatistics.getInstance().loopBatch(circleArticle.id, 0, 1);
                        }
                    } else if (!(last == null || last.isPlaying())) {
                        last.playWithOutEnd();
                    }
                    this.g = a.NORMAL_VIDEO;
                    return;
                }
                return;
            }
        }
        if (tupleTwo != null) {
            NativeMediaADData nativeMediaADData3 = (NativeMediaADData) tupleTwo.getSecond();
            if (!(lastRef == null || lastRef.equals(nativeMediaADData3))) {
                lastRef.stop();
            }
            if (last != null && last.isPlaying()) {
                last.stop();
            }
            if (this.b != null) {
                if (this.b.isPlaying()) {
                    this.b.stop();
                }
                this.b = null;
            }
            if (QsbkApp.getInstance().isAutoPlayConfiged() && this.h) {
                if (!nativeMediaADData3.equals(this.c)) {
                    nativeMediaADData3.play();
                    this.c = nativeMediaADData3;
                }
                this.g = a.GDT_AD_VIDEO;
            }
        }
        if (videoPlayerView == null && tupleTwo == null) {
            if (this.b != null && this.b.isPlaying()) {
                this.b.stop();
            }
            if (this.c != null) {
                this.c.stop();
            }
            this.g = null;
        }
    }

    public void onItemFound(View view, VideoPlayerView videoPlayerView, boolean z) {
    }

    private void a() {
        if (this.a.getChildCount() != 0 || (this.a.getAdapter() != null && this.a.getAdapter().getCount() == 0)) {
            a(this.f);
        } else {
            this.a.post(new ax(this));
        }
    }

    public void stopAll() {
        VideoPlayerView last = VideoPlayersManager.getLast();
        if (last != null) {
            last.stop();
            this.b = null;
        }
        NativeMediaADData lastRef = GdtVideoManager.getLastRef();
        if (lastRef != null) {
            lastRef.stop();
            this.c = null;
        }
        this.g = null;
        this.f = false;
    }

    public void pause() {
        VideoPlayerView last = VideoPlayersManager.getLast();
        if (last != null) {
            last.pause();
        }
        NativeMediaADData lastRef = GdtVideoManager.getLastRef();
        if (lastRef != null) {
            lastRef.stop();
            this.c = null;
        }
    }

    public void autoPlay() {
        this.f = true;
        a();
    }

    public void setLastPlayer(VideoPlayerView videoPlayerView) {
        this.b = videoPlayerView;
        this.g = a.NORMAL_VIDEO;
    }

    public void setLastGdtVideoRef(NativeMediaADData nativeMediaADData) {
        this.c = nativeMediaADData;
        this.g = a.GDT_AD_VIDEO;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (absListView.getChildCount() != 0) {
            int top = absListView.getChildAt(0).getTop();
            if (this.i != i) {
                this.i = i;
                this.j = top;
                this.needCheck = true;
                a(false);
            } else if (Math.abs(this.j - top) > this.e) {
                this.j = top;
                this.needCheck = true;
                a(false);
            }
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.needCheck) {
            this.needCheck = false;
            a(this.f);
        }
    }

    public void putStartTime(String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            if (j <= 0) {
                this.d.remove(str.hashCode());
            }
            this.d.put(str.hashCode(), Long.valueOf(j));
        }
    }

    public long getStartTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return ((Long) this.d.get(str.hashCode(), Long.valueOf(0))).longValue();
    }
}
