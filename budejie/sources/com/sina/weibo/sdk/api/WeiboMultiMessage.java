package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.a.d;
import java.io.Serializable;

public final class WeiboMultiMessage implements Serializable {
    public static int NineImageType = 2;
    public static int OneImageType = 1;
    private static final String TAG = "WeiboMultiMessage";
    public ImageObject imageObject;
    public BaseMediaObject mediaObject;
    public int msgType;
    public MultiImageObject multiImageObject;
    public TextObject textObject;
    public VideoSourceObject videoSourceObject;

    public WeiboMultiMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public Bundle toBundle(Bundle bundle) {
        if (this.textObject != null) {
            bundle.putParcelable("_weibo_message_text", this.textObject);
            bundle.putString("_weibo_message_text_extra", this.textObject.b());
        } else {
            bundle.putParcelable("_weibo_message_text", null);
            bundle.putString("_weibo_message_text_extra", null);
        }
        if (this.imageObject != null) {
            bundle.putParcelable("_weibo_message_image", this.imageObject);
            bundle.putString("_weibo_message_image_extra", this.imageObject.b());
        } else {
            bundle.putParcelable("_weibo_message_image", null);
            bundle.putString("_weibo_message_image_extra", null);
        }
        if (this.mediaObject != null) {
            bundle.putParcelable("_weibo_message_media", this.mediaObject);
            bundle.putString("_weibo_message_media_extra", this.mediaObject.b());
        } else {
            bundle.putParcelable("_weibo_message_media", null);
            bundle.putString("_weibo_message_media_extra", null);
        }
        if (this.multiImageObject != null) {
            bundle.putParcelable("_weibo_message_multi_image", this.multiImageObject);
        } else {
            bundle.putParcelable("_weibo_message_multi_image", null);
        }
        if (this.videoSourceObject != null) {
            bundle.putParcelable("_weibo_message_video_source", this.videoSourceObject);
        } else {
            bundle.putParcelable("_weibo_message_video_source", null);
        }
        return bundle;
    }

    public WeiboMultiMessage toObject(Bundle bundle) {
        this.textObject = (TextObject) bundle.getParcelable("_weibo_message_text");
        if (this.textObject != null) {
            this.textObject.a(bundle.getString("_weibo_message_text_extra"));
        }
        this.imageObject = (ImageObject) bundle.getParcelable("_weibo_message_image");
        if (this.imageObject != null) {
            this.imageObject.a(bundle.getString("_weibo_message_image_extra"));
        }
        this.mediaObject = (BaseMediaObject) bundle.getParcelable("_weibo_message_media");
        if (this.mediaObject != null) {
            this.mediaObject.a(bundle.getString("_weibo_message_media_extra"));
        }
        this.multiImageObject = (MultiImageObject) bundle.getParcelable("_weibo_message_multi_image");
        this.videoSourceObject = (VideoSourceObject) bundle.getParcelable("_weibo_message_video_source");
        return this;
    }

    public boolean checkArgs() {
        if (this.textObject != null && !this.textObject.a()) {
            d.c(TAG, "checkArgs fail, textObject is invalid");
            return false;
        } else if (this.imageObject != null && !this.imageObject.a()) {
            d.c(TAG, "checkArgs fail, imageObject is invalid");
            return false;
        } else if (this.mediaObject != null && !this.mediaObject.a()) {
            d.c(TAG, "checkArgs fail, mediaObject is invalid");
            return false;
        } else if (this.textObject != null || this.imageObject != null || this.mediaObject != null) {
            return true;
        } else {
            d.c(TAG, "checkArgs fail, textObject and imageObject and mediaObject is null");
            return false;
        }
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public int getMsgType() {
        return this.msgType;
    }
}
