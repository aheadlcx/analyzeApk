package com.budejie.www.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.R;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.bean.ListItemObject;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.umeng.onlineconfig.OnlineConfigAgent;

public class ay {
    public static ListItemObject a = null;
    public static HuodongBean b = null;
    public static String c = "wxGroup";
    public static String d = "http://img.spriteapp.cn/ws/rich_text.jpg";

    public static void a(Context context, ListItemObject listItemObject, int i, IWXAPI iwxapi, boolean z, SharedPreferences sharedPreferences, String str) {
        try {
            String str2;
            String str3;
            IMediaObject iMediaObject;
            String str4;
            String configParams = OnlineConfigAgent.getInstance().getConfigParams(context, "share_weixin_show_header");
            boolean z2 = false;
            a = listItemObject;
            if (z) {
                c = "wxFriends";
            } else {
                c = "wxGroup";
            }
            Bitmap bitmap = null;
            WXMusicObject wXMusicObject = null;
            String imgUrl = listItemObject.getImgUrl();
            CharSequence content = listItemObject.getContent();
            Object voiceUri = listItemObject.getVoiceUri();
            CharSequence videouri = listItemObject.getVideouri();
            boolean z3 = false;
            CharSequence weixin_url = listItemObject.getWeixin_url();
            int height = listItemObject.getHeight();
            listItemObject.getWidth();
            if (!TextUtils.isEmpty(str)) {
                Bitmap a;
                try {
                    a = h.a(str, false);
                } catch (OutOfMemoryError e) {
                    a = null;
                }
                aa.b("ghb", "得到微信分享图片大小：" + ((a.getRowBytes() * a.getHeight()) / 1024) + "k  thumb.getHeight():" + a.getHeight());
                if (((double) a.getHeight()) > ((double) a.getWidth()) * 1.5d) {
                    z2 = true;
                }
                if (z) {
                    bitmap = an.a(a, 10000);
                } else {
                    bitmap = an.a(a, 4000);
                }
                aa.b("ghb", "得到微信分享原图大小：" + ((bitmap.getRowBytes() * bitmap.getHeight()) / 1024) + "k");
                str2 = "";
                str3 = "";
                iMediaObject = null;
                str4 = imgUrl;
            } else if (listItemObject.getIfPP()) {
                str2 = "分享用户";
                bitmap = an.b(context, imgUrl);
                if (Scheme.ofUri(imgUrl) == Scheme.FILE) {
                    r6 = content;
                    iMediaObject = null;
                    str4 = listItemObject.getCnd_img();
                } else {
                    r6 = content;
                    iMediaObject = null;
                    str4 = imgUrl;
                }
            } else if (TextUtils.isEmpty(weixin_url)) {
                str2 = OnlineConfigAgent.getInstance().getConfigParams(context, "微信、微信朋友圈-推荐朋友-标题");
                str4 = OnlineConfigAgent.getInstance().getConfigParams(context, "微信、微信朋友圈-推荐朋友-内容");
                if (TextUtils.isEmpty(str2)) {
                    str2 = "推荐一个搞笑的应用";
                }
                if (TextUtils.isEmpty(str4)) {
                    str4 = "百思不得姐，瞬间戳中你的笑点，试试看吧.";
                }
                if (z) {
                    imgUrl = "www.budejie.com/budejie/?&f=weixin&d=android";
                } else {
                    imgUrl = "www.budejie.com/budejie/?&f=weixin_py&d=android";
                    str2 = str2 + str4;
                }
                if (null == null) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_new);
                }
                if (sharedPreferences == null) {
                    sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
                }
                sharedPreferences.edit().putBoolean("isRecommend", false).commit();
                str3 = str4;
                iMediaObject = null;
                str4 = imgUrl;
            } else {
                String configParams2;
                String configParams3;
                CharSequence configParams4;
                CharSequence charSequence;
                String str5;
                CharSequence charSequence2;
                CharSequence charSequence3;
                Object obj;
                str2 = "";
                str2 = "";
                str2 = "";
                str2 = "";
                str2 = "";
                String configParams5;
                if (z) {
                    configParams5 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_图片_微信");
                    configParams2 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_段子_微信");
                    configParams3 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_声音_微信");
                    str3 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_视频_微信");
                    configParams4 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_长文_微信");
                    charSequence = configParams2;
                    str5 = configParams3;
                    charSequence2 = configParams5;
                    charSequence3 = str3;
                    str3 = str5;
                } else {
                    configParams5 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_图片_微信朋友圈");
                    configParams2 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_段子_微信朋友圈");
                    configParams3 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_声音_微信朋友圈");
                    str3 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_视频_微信朋友圈");
                    configParams4 = OnlineConfigAgent.getInstance().getConfigParams(context, "分享标题_长文_微信朋友圈");
                    Object obj2 = configParams2;
                    str5 = configParams3;
                    Object obj3 = configParams5;
                    obj = str3;
                    str3 = str5;
                }
                if (TextUtils.isEmpty(charSequence2)) {
                    configParams2 = "分享一张搞笑的图片:";
                } else {
                    CharSequence charSequence4 = charSequence2;
                }
                if (TextUtils.isEmpty(charSequence)) {
                    configParams3 = "分享一条好玩的段子:";
                }
                if (TextUtils.isEmpty(str3)) {
                    str3 = "分享语音：";
                }
                if (TextUtils.isEmpty(charSequence3)) {
                    configParams3 = "分享视频：";
                }
                if (TextUtils.isEmpty(configParams4)) {
                    configParams4 = "分享链接：";
                }
                if (listItemObject.getRichObject() != null) {
                    imgUrl = an.D(context);
                    if (TextUtils.isEmpty(imgUrl) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                        bitmap = an.b(context, listItemObject.getRichObject().getImgUrl());
                    } else {
                        bitmap = an.b(context, imgUrl);
                    }
                    if (bitmap == null) {
                        bitmap = an.b(context, d);
                    }
                    if (bitmap == null) {
                        bitmap = an.i(context);
                    }
                    str3 = listItemObject.getRichObject().getTitle();
                    if (!TextUtils.isEmpty(configParams4) && str3.length() > 30) {
                        str3 = str3.substring(0, 30);
                    }
                    imgUrl = listItemObject.getRichObject().getDesc();
                    if (!TextUtils.isEmpty(imgUrl) && imgUrl.length() > 30) {
                        imgUrl = imgUrl.substring(0, 30);
                    }
                    content = imgUrl;
                } else if (!TextUtils.isEmpty(imgUrl) && height != 0 && TextUtils.isEmpty(voiceUri) && TextUtils.isEmpty(videouri)) {
                    Object obj4;
                    str2 = an.D(context);
                    if (!TextUtils.isEmpty(str2) && Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                        bitmap = an.b(context, str2);
                    } else if ("1".equals(listItemObject.getIs_gif())) {
                        bitmap = an.b(context, listItemObject.getGifFistFrame());
                    } else {
                        bitmap = an.b(context, imgUrl.replace("_6.jpg", "_2.jpg"));
                    }
                    if (bitmap == null) {
                        bitmap = an.i(context);
                    }
                    if (z) {
                        imgUrl = "分享自@" + listItemObject.getName();
                    } else {
                        CharSequence charSequence5 = content;
                        obj4 = configParams2 + content;
                        CharSequence charSequence6 = charSequence5;
                    }
                    r6 = content;
                    obj4 = imgUrl;
                } else if (!TextUtils.isEmpty(voiceUri)) {
                    str2 = an.D(context);
                    if (TextUtils.isEmpty(str2) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                        bitmap = an.b(context, imgUrl);
                    } else {
                        bitmap = an.b(context, str2);
                    }
                    if (bitmap == null) {
                        bitmap = an.i(context);
                    }
                    str3 = str3 + content;
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "分享语音:";
                    }
                    if (!listItemObject.getIsDraftBean()) {
                        wXMusicObject = new WXMusicObject();
                        wXMusicObject.musicDataUrl = voiceUri;
                    }
                } else if (TextUtils.isEmpty(videouri)) {
                    Bitmap a2;
                    try {
                        a2 = h.a(str, false);
                    } catch (OutOfMemoryError e2) {
                        a2 = null;
                    }
                    aa.b("ghb", "得到微信分享图片大小：" + ((a2.getRowBytes() * a2.getHeight()) / 1024) + "k  thumb.getHeight():" + a2.getHeight());
                    if (((double) a2.getHeight()) > ((double) a2.getWidth()) * 1.5d) {
                        z2 = true;
                    }
                    if (z) {
                        bitmap = an.a(a2, 10000);
                    } else {
                        bitmap = an.a(a2, 4000);
                    }
                    aa.b("ghb", "得到微信分享原图大小：" + ((bitmap.getRowBytes() * bitmap.getHeight()) / 1024) + "k");
                    str3 = "";
                    content = "";
                } else {
                    String D = an.D(context);
                    if (TextUtils.isEmpty(D) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                        bitmap = an.b(context, imgUrl);
                    } else {
                        bitmap = an.b(context, D);
                    }
                    if (bitmap == null) {
                        bitmap = an.i(context);
                    }
                    if (TextUtils.isEmpty(content)) {
                        str3 = "分享视频:";
                    } else {
                        r6 = content;
                    }
                    if (z) {
                        content = "分享自@" + listItemObject.getName();
                    } else {
                        str3 = content;
                        content = "";
                    }
                    wXMusicObject = new WXVideoObject();
                    z3 = false;
                }
                if (z) {
                    str2 = weixin_url + "&f=weixin&d=android";
                } else {
                    str2 = weixin_url + "&f=weixin_py&d=android";
                }
                if (!((TextUtils.isEmpty(voiceUri) && TextUtils.isEmpty(videouri)) || wXMusicObject == null)) {
                    if (wXMusicObject instanceof WXMusicObject) {
                        wXMusicObject.musicUrl = str2;
                    } else {
                        ((WXVideoObject) wXMusicObject).videoLowBandUrl = str2;
                        ((WXVideoObject) wXMusicObject).videoUrl = str2;
                    }
                }
                obj = wXMusicObject;
                str5 = str2;
                str2 = str3;
                r6 = content;
                str4 = str5;
            }
            a(context, iwxapi, str4, str2, str3, bitmap, i, iMediaObject, z3, z2, str);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static void a(Context context, HuodongBean huodongBean, int i, IWXAPI iwxapi, boolean z, SharedPreferences sharedPreferences) {
        IMediaObject iMediaObject = null;
        try {
            String str;
            Bitmap decodeResource;
            b = huodongBean;
            if (z) {
                c = "wxFriends";
                b.setType("wxFriends");
            } else {
                c = "wxGroup";
                b.setType("wxGroup");
            }
            String picUrl = huodongBean.getPicUrl();
            String content = huodongBean.getContent();
            Object voiceUrl = huodongBean.getVoiceUrl();
            String title = huodongBean.getTitle();
            String shareUrl = huodongBean.getShareUrl();
            if (TextUtils.isEmpty(shareUrl)) {
                CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(context, "微信、微信朋友圈-推荐朋友-标题");
                content = OnlineConfigAgent.getInstance().getConfigParams(context, "微信、微信朋友圈-推荐朋友-内容");
                if (TextUtils.isEmpty(configParams)) {
                    shareUrl = "推荐一个搞笑的应用";
                }
                if (TextUtils.isEmpty(content)) {
                    content = "百思不得姐，瞬间戳中你的笑点，试试看吧.";
                }
                if (z) {
                    str = "www.budejie.com/budejie/?&f=weixin&d=android";
                } else {
                    str = "www.budejie.com/budejie/?&f=weixin_py&d=android";
                }
                if (null == null) {
                    decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.label_share_default);
                } else {
                    decodeResource = null;
                }
                if (sharedPreferences == null) {
                    sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
                }
                sharedPreferences.edit().putBoolean("isRecommend", false).commit();
            } else {
                if (!TextUtils.isEmpty(picUrl) && TextUtils.isEmpty(voiceUrl)) {
                    decodeResource = an.b(context, picUrl);
                    if (decodeResource == null) {
                        decodeResource = an.i(context);
                    }
                } else if (TextUtils.isEmpty(voiceUrl)) {
                    decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.label_share_default);
                } else {
                    decodeResource = an.b(context, picUrl);
                    if (decodeResource == null) {
                        decodeResource = an.i(context);
                    }
                    iMediaObject = new WXMusicObject();
                    iMediaObject.musicDataUrl = voiceUrl;
                }
                if (!shareUrl.contains("?")) {
                    shareUrl = shareUrl + "?";
                }
                if (z) {
                    str = shareUrl + "&f=weixin&d=android";
                } else {
                    str = shareUrl + "&f=weixin_py&d=android";
                }
                if (!(TextUtils.isEmpty(voiceUrl) || iMediaObject == null)) {
                    iMediaObject.musicUrl = str;
                }
            }
            a(context, iwxapi, str, title, content, decodeResource, i, iMediaObject, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(Context context, HuodongBean huodongBean, int i, IWXAPI iwxapi, boolean z, SharedPreferences sharedPreferences) {
        WXMusicObject wXMusicObject = null;
        try {
            Bitmap decodeResource;
            IMediaObject iMediaObject;
            b = huodongBean;
            if (z) {
                c = "wxFriends";
                b.setType("wxFriends");
            } else {
                c = "wxGroup";
                b.setType("wxGroup");
            }
            String picUrl = huodongBean.getPicUrl();
            String content = huodongBean.getContent();
            Object voiceUrl = huodongBean.getVoiceUrl();
            String title = huodongBean.getTitle();
            CharSequence videoUrl = huodongBean.getVideoUrl();
            String shareUrl = huodongBean.getShareUrl();
            if (TextUtils.isEmpty(shareUrl)) {
                CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(context, "微信、微信朋友圈-推荐朋友-标题");
                content = OnlineConfigAgent.getInstance().getConfigParams(context, "微信、微信朋友圈-推荐朋友-内容");
                if (TextUtils.isEmpty(configParams)) {
                    picUrl = "推荐一个搞笑的应用";
                }
                if (TextUtils.isEmpty(content)) {
                    content = "百思不得姐，瞬间戳中你的笑点，试试看吧.";
                }
                if (z) {
                    shareUrl = "www.budejie.com/budejie/?&f=weixin&d=android";
                } else {
                    shareUrl = "www.budejie.com/budejie/?&f=weixin_py&d=android";
                }
                if (null == null) {
                    decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.label_share_default);
                } else {
                    decodeResource = null;
                }
                if (sharedPreferences == null) {
                    sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
                }
                sharedPreferences.edit().putBoolean("isRecommend", false).commit();
                iMediaObject = null;
            } else {
                if (!TextUtils.isEmpty(picUrl) && TextUtils.isEmpty(voiceUrl) && TextUtils.isEmpty(videoUrl)) {
                    decodeResource = an.b(context, picUrl);
                    if (decodeResource == null) {
                        decodeResource = an.i(context);
                    }
                } else if (!TextUtils.isEmpty(voiceUrl)) {
                    decodeResource = an.b(context, picUrl);
                    if (decodeResource == null) {
                        decodeResource = an.i(context);
                    }
                    wXMusicObject = new WXMusicObject();
                    wXMusicObject.musicDataUrl = voiceUrl;
                    title = content;
                } else if (TextUtils.isEmpty(videoUrl)) {
                    decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.label_share_default);
                } else {
                    decodeResource = an.b(context, picUrl);
                    if (decodeResource == null) {
                        decodeResource = an.i(context);
                    }
                    wXMusicObject = new WXVideoObject();
                    title = content;
                    content = "";
                }
                if (shareUrl.contains("?")) {
                    picUrl = shareUrl;
                } else {
                    picUrl = shareUrl + "?";
                }
                if (z) {
                    shareUrl = picUrl + "&f=weixin&d=android";
                } else {
                    shareUrl = picUrl + "&f=weixin_py&d=android";
                }
                Object obj;
                if ((TextUtils.isEmpty(voiceUrl) && TextUtils.isEmpty(videoUrl)) || wXMusicObject == null) {
                    obj = wXMusicObject;
                } else if (wXMusicObject instanceof WXMusicObject) {
                    wXMusicObject.musicUrl = shareUrl;
                    iMediaObject = wXMusicObject;
                } else {
                    ((WXVideoObject) wXMusicObject).videoLowBandUrl = shareUrl;
                    ((WXVideoObject) wXMusicObject).videoUrl = shareUrl;
                    obj = wXMusicObject;
                }
            }
            a(context, iwxapi, shareUrl, title, content, decodeResource, i, iMediaObject, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a(Context context, IWXAPI iwxapi, String str, String str2, String str3, Bitmap bitmap, int i, IMediaObject iMediaObject, boolean z, boolean z2) {
        a(context, iwxapi, str, str2, str3, bitmap, i, iMediaObject, z, z2, "");
    }

    private static void a(Context context, IWXAPI iwxapi, String str, String str2, String str3, Bitmap bitmap, int i, IMediaObject iMediaObject, boolean z, boolean z2, String str4) {
        try {
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
                IMediaObject wXImageObject;
                if (TextUtils.isEmpty(str4) || i != 0) {
                    wXImageObject = new WXImageObject(bitmap);
                    aa.b("ghb", "分享微信原图大小：" + ((bitmap.getRowBytes() * bitmap.getHeight()) / 1024) + "k");
                    wXMediaMessage.mediaObject = wXImageObject;
                } else {
                    wXImageObject = new WXImageObject();
                    wXImageObject.setImagePath(str4);
                    wXMediaMessage.mediaObject = wXImageObject;
                }
                if (bitmap != null) {
                    byte[] a = an.a(bitmap, 30, z2);
                    wXMediaMessage.thumbData = a;
                    aa.b("ghb", "分享微信原图缩略图：" + (a.length / 1024) + "k");
                }
            } else {
                IMediaObject wXWebpageObject = new WXWebpageObject();
                wXWebpageObject.webpageUrl = str;
                wXMediaMessage.mediaObject = wXWebpageObject;
                wXMediaMessage.title = str2;
                wXMediaMessage.description = str3;
                if (iMediaObject != null) {
                    wXMediaMessage.mediaObject = iMediaObject;
                }
                if (bitmap != null) {
                    wXMediaMessage.thumbData = an.a(context, bitmap, true, false);
                }
            }
            BaseReq req = new Req();
            req.transaction = a("webpage");
            req.message = wXMediaMessage;
            req.scene = i;
            aa.a("---", "是否可以打开" + iwxapi.sendReq(req));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String a(String str) {
        return str == null ? String.valueOf(System.currentTimeMillis()) : str + System.currentTimeMillis();
    }
}
