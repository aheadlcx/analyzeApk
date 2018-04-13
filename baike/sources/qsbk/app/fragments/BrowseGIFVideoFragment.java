package qsbk.app.fragments;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.open.SocialConstants;
import java.io.File;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.ImageSize;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;
import qsbk.app.video.SimpleVideoPlayerView;

public class BrowseGIFVideoFragment extends BrowseBaseFragment implements OnVideoEventListener {
    private static final String d = BrowseGIFVideoFragment.class.getSimpleName();
    ProgressBar a;
    View b;
    View c;
    private ImageInfo e;
    private MediaScannerConnection f;
    public View gifTag;
    public View imageLayout;
    public SimpleDraweeView imageView;
    public SimpleVideoPlayerView videoPlayer;

    public static BrowseGIFVideoFragment newInstance(ImageInfo imageInfo) {
        BrowseGIFVideoFragment browseGIFVideoFragment = new BrowseGIFVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SocialConstants.PARAM_IMG_URL, imageInfo);
        browseGIFVideoFragment.setArguments(bundle);
        return browseGIFVideoFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = (ImageInfo) getArguments().getSerializable(SocialConstants.PARAM_IMG_URL);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragmenet_browse_gif_video, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        ImageSize imageSize;
        Uri parse;
        super.onViewCreated(view, bundle);
        this.a = (ProgressBar) view.findViewById(R.id.video_progress);
        this.videoPlayer = (SimpleVideoPlayerView) view.findViewById(R.id.videoView);
        this.imageView = (SimpleDraweeView) view.findViewById(R.id.image_pre);
        this.c = view.findViewById(R.id.imageLoading);
        this.b = view.findViewById(R.id.play_video);
        this.gifTag = view.findViewById(R.id.gif_tag);
        this.a.setVisibility(0);
        this.videoPlayer.setWidget(null, this.b, null);
        this.videoPlayer.setOnVideoEventListener(this);
        this.videoPlayer.setLoop(true);
        this.videoPlayer.setDisplayMode(1);
        this.b.setOnClickListener(new u(this));
        this.videoPlayer.setOnClickListener(new v(this));
        int[] requestWidthAndMaxPixcel = getRequestWidthAndMaxPixcel();
        int i = requestWidthAndMaxPixcel[0];
        int i2 = requestWidthAndMaxPixcel[1];
        if (this.e.width == 0 || this.e.height == 0) {
            imageSize = new ImageSize(i, i2);
        } else {
            imageSize = new ImageSize(i, (this.e.height * i) / this.e.width);
            this.videoPlayer.setAspectRatio(this.e.width, this.e.height);
        }
        setImageLayoutParams(this.videoPlayer, imageSize, i, i2);
        setImageLayoutParams(this.imageView, imageSize, i, i2);
        ControllerListener wVar = new w(this, i, i2);
        Uri uri = Uri.EMPTY;
        try {
            parse = Uri.parse(this.e.getImageUrl());
            uri = Uri.parse(this.e.getBigImageUrl());
            if (parse != null || uri == null) {
                uri = parse;
            }
            parse = uri;
        } catch (Exception e) {
            parse = Uri.EMPTY;
        }
        if (this.e.width == 0 || this.e.height == 0) {
            this.imageView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setControllerListener(wVar)).setUri(parse).setOldController(this.imageView.getController())).build());
        } else {
            FrescoImageloader.displayImage(this.imageView, parse.toString());
        }
        this.videoPlayer.setVisibility(0);
        if (this.e.width != 0 && this.e.height != 0) {
            this.videoPlayer.post(new x(this));
        }
    }

    private void a() {
        if (this.e != null) {
            this.imageView.setVisibility(0);
            this.videoPlayer.setVideo(this.e.getVideoUrl());
            if (getUserVisibleHint()) {
                this.videoPlayer.play();
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f != null && this.f.isConnected()) {
            this.f.disconnect();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.videoPlayer == null) {
            return;
        }
        if (z) {
            this.videoPlayer.play();
        } else {
            this.videoPlayer.stop();
        }
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
        if (!isDetached()) {
            alphaView(this.imageView);
            this.a.setVisibility(8);
        }
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (!isDetached()) {
            this.a.setVisibility(i >= 100 ? 8 : 0);
        }
    }

    public int[] getRequestWidthAndMaxPixcel() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        return new int[]{i, i2};
    }

    public void alphaView(View view) {
        view.animate().alpha(0.0f).setListener(new y(this, view)).start();
    }

    public int setImageLayoutParams(View view, ImageSize imageSize, int i, int i2) {
        LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        int width = (int) ((((float) i) / ((float) imageSize.getWidth())) * ((float) imageSize.getHeight()));
        float aspectRatio = imageSize == null ? 1.0f : imageSize.getAspectRatio();
        if (i2 == -1 || width <= i2) {
            i2 = width;
        } else {
            i = (int) (((float) i2) * aspectRatio);
        }
        if (layoutParams != null) {
            layoutParams.width = i;
            layoutParams.height = i2;
        } else {
            layoutParams = new RelativeLayout.LayoutParams(i, i2);
        }
        layoutParams.addRule(13);
        view.setLayoutParams(layoutParams);
        return i2;
    }

    public boolean isMediaSaved() {
        if (this.e == null) {
            return false;
        }
        File a = a(this.e);
        if (a == null || !a.exists()) {
            return false;
        }
        return true;
    }

    public void saveMedia() {
        if (this.e != null && this.videoPlayer != null) {
            File a = a(this.e);
            this.videoPlayer.downloadTo(a, new z(this, a));
        }
    }

    private File a(ImageInfo imageInfo) {
        return new File(DeviceUtils.getSDPath(), "qsbk/video/" + (imageInfo.getVideoUrl().hashCode() + ".mp4"));
    }
}
