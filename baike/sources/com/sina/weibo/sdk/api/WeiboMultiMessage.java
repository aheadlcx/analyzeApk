package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBConstants.Msg;
import com.sina.weibo.sdk.utils.LogUtil;

public final class WeiboMultiMessage {
    public static int NineImageType = 2;
    public static int OneImageType = 1;
    public BaseMediaObject BusinessLinkCardPageObject;
    public ImageObject imageObject;
    public BaseMediaObject mediaObject;
    public int msgType;
    public TextObject textObject;

    public WeiboMultiMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public Bundle toBundle(Bundle bundle) {
        if (this.textObject != null) {
            bundle.putParcelable(Msg.TEXT, this.textObject);
            bundle.putString(Msg.TEXT_EXTRA, this.textObject.a());
        }
        if (this.imageObject != null) {
            bundle.putParcelable(Msg.IMAGE, this.imageObject);
            bundle.putString(Msg.IMAGE_EXTRA, this.imageObject.a());
        }
        if (this.mediaObject != null) {
            bundle.putParcelable(Msg.MEDIA, this.mediaObject);
            bundle.putString(Msg.MEDIA_EXTRA, this.mediaObject.a());
        }
        return bundle;
    }

    public WeiboMultiMessage toObject(Bundle bundle) {
        this.textObject = (TextObject) bundle.getParcelable(Msg.TEXT);
        if (this.textObject != null) {
            this.textObject.a(bundle.getString(Msg.TEXT_EXTRA));
        }
        this.imageObject = (ImageObject) bundle.getParcelable(Msg.IMAGE);
        if (this.imageObject != null) {
            this.imageObject.a(bundle.getString(Msg.IMAGE_EXTRA));
        }
        this.mediaObject = (BaseMediaObject) bundle.getParcelable(Msg.MEDIA);
        if (this.mediaObject != null) {
            this.mediaObject.a(bundle.getString(Msg.MEDIA_EXTRA));
        }
        return this;
    }

    public boolean checkArgs() {
        if (this.textObject != null && !this.textObject.checkArgs()) {
            LogUtil.e("WeiboMultiMessage", "checkArgs fail, textObject is invalid");
            return false;
        } else if (this.imageObject != null && !this.imageObject.checkArgs()) {
            LogUtil.e("WeiboMultiMessage", "checkArgs fail, imageObject is invalid");
            return false;
        } else if (this.mediaObject != null && !this.mediaObject.checkArgs()) {
            LogUtil.e("WeiboMultiMessage", "checkArgs fail, mediaObject is invalid");
            return false;
        } else if (this.textObject != null || this.imageObject != null || this.mediaObject != null) {
            return true;
        } else {
            LogUtil.e("WeiboMultiMessage", "checkArgs fail, textObject and imageObject and mediaObject is null");
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
