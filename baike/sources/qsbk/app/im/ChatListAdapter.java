package qsbk.app.im;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.util.LongSparseArray;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.droidsonroids.gif.GifImageView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ApplyForGroupActivity;
import qsbk.app.activity.HighLightQiushiActivity;
import qsbk.app.activity.ImageViewer;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.http.EncryptDecryptUtil;
import qsbk.app.im.ChatMsg.TYPE;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.im.emotion.EmotionShowerHelper;
import qsbk.app.im.image.IMImageSize;
import qsbk.app.im.image.IMImageSizeHelper;
import qsbk.app.im.image.IMImageSizeHelper.Size;
import qsbk.app.im.image.ImageUploader;
import qsbk.app.im.image.ImageUploader.UploadImageCallback;
import qsbk.app.im.image.UploadTask;
import qsbk.app.im.voice.VoiceManager;
import qsbk.app.im.voice.VoiceManager.VoiceCallback;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.DeeplinkInfo;
import qsbk.app.model.EventWindow;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.InviteInfo;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.model.LaiseeImLog;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.CustomHttpClient;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.GroupActionUtil;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.SpringFestivalUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UIHelper$Theme;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.utils.Util;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.BreathTextView;
import qsbk.app.widget.RangedProgressTextView;
import qsbk.app.widget.Recyclable;

public class ChatListAdapter extends BaseAdapter {
    public static final int ITEM_TYPE_CONTENT_SHARE_ME = 15;
    public static final int ITEM_TYPE_CONTENT_SHARE_OTHER = 16;
    public static final int ITEM_TYPE_DEEP_LINK_PUSH = 39;
    public static final int ITEM_TYPE_DIVIDER = 19;
    public static final int ITEM_TYPE_DUOBAO_NOTIFY = 27;
    public static final int ITEM_TYPE_GTOUP_SYSTEM = 12;
    public static final int ITEM_TYPE_IMAGE_ME = 5;
    public static final int ITEM_TYPE_IMAGE_OTHER = 6;
    public static final int ITEM_TYPE_INVITE_OTHER = 11;
    public static final int ITEM_TYPE_LAISEE_LOG = 31;
    public static final int ITEM_TYPE_LAISEE_ME = 29;
    public static final int ITEM_TYPE_LAISEE_NOT_GOT = 32;
    public static final int ITEM_TYPE_LAISEE_OTHER = 30;
    public static final int ITEM_TYPE_LAISEE_VOICE_ME = 37;
    public static final int ITEM_TYPE_LAISEE_VOICE_OTHER = 38;
    public static final int ITEM_TYPE_LIVE_BEGIN_ME = 24;
    public static final int ITEM_TYPE_LIVE_BEGIN_OTHER = 25;
    public static final int ITEM_TYPE_MAX = 40;
    public static final int ITEM_TYPE_MEDAL_NOTIFY = 26;
    public static final int ITEM_TYPE_NONE = 0;
    public static final int ITEM_TYPE_OFFICIAL_MULTIPLE = 10;
    public static final int ITEM_TYPE_OFFICIAL_SINGLE = 9;
    public static final int ITEM_TYPE_OTHER_JOIN_SUCCESS = 13;
    public static final int ITEM_TYPE_PURE_EMOTION_ME = 8;
    public static final int ITEM_TYPE_PURE_EMOTION_OTHER = 7;
    public static final int ITEM_TYPE_QIUSHI_PUSH = 14;
    public static final int ITEM_TYPE_QIUSHI_TOPIC_ME = 35;
    public static final int ITEM_TYPE_QIUSHI_TOPIC_OTHER = 36;
    public static final int ITEM_TYPE_QSJX_SHARE_ME = 33;
    public static final int ITEM_TYPE_QSJX_SHARE_OTHER = 34;
    public static final int ITEM_TYPE_SENDING = 3;
    public static final int ITEM_TYPE_SPRING_FESTIVAL = 28;
    public static final int ITEM_TYPE_SYSTEM = 4;
    public static final int ITEM_TYPE_TXT_ME = 2;
    public static final int ITEM_TYPE_TXT_OTHER = 1;
    public static final int ITEM_TYPE_VOICE_ME = 17;
    public static final int ITEM_TYPE_VOICE_OTHER = 18;
    public static final int ITEM_TYPE_WEB_SHARE_ME = 20;
    public static final int ITEM_TYPE_WEB_SHARE_OTHER = 21;
    static final Map<String, Integer> a = Collections.synchronizedMap(new HashMap());
    static final List<String> b = Collections.synchronizedList(new ArrayList());
    static final Map<String, Integer> c = Collections.synchronizedMap(new HashMap());
    static final List<String> d = Collections.synchronizedList(new ArrayList());
    private static final String f = ChatListAdapter.class.getSimpleName();
    public ChatMsg _toFlash;
    List<ChatMsg> e = new LinkedList();
    private final int g;
    private Activity h = null;
    private String i;
    private String j;
    private ImageUploader k;
    private aw l;
    private UploadedListener m;
    private ArrayList<ImageInfo> n = new ArrayList();
    private List<Rect> o = new ArrayList();
    private boolean p = false;
    private VoiceManager q;
    private ax r;
    private String s = null;
    private String t = null;
    private String[] u;
    private ArrayList<BaseUserInfo> v;
    private OnAvatarClickListener w;
    private h x = null;
    private SparseArray<IChatViewShower> y = new SparseArray();

    public class DuobaoViewHolder {
        RelativeLayout a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        View h;
        final /* synthetic */ ChatListAdapter i;

        public DuobaoViewHolder(ChatListAdapter chatListAdapter) {
            this.i = chatListAdapter;
        }
    }

    public interface IChatViewShower {
        View getView(int i, ChatMsg chatMsg, View view, int i2);
    }

    public static abstract class ImageShower implements IChatViewShower {
        static void a(ImageView imageView, String str) {
            a(imageView, str, UIHelper.getChatImageDefault(), UIHelper.getChatImageFail());
        }

        static void a(ImageView imageView, String str, int i, int i2) {
            if (!TextUtils.isEmpty(str)) {
                FrescoImageloader.displayImage(imageView, str, i, i2);
            }
        }

        protected static void a(SimpleDraweeView simpleDraweeView, String str, ResizeOptions resizeOptions, boolean z) {
            DraweeController build = ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController())).setImageRequest(ImageRequestBuilder.newBuilderWithSource(FrescoImageloader.get(str)).setResizeOptions(resizeOptions).build())).build();
            GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) simpleDraweeView.getHierarchy();
            if (genericDraweeHierarchy == null) {
                genericDraweeHierarchy = new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).build();
            }
            if (z) {
                genericDraweeHierarchy.setPlaceholderImage(UIHelper.getChatImageDefault());
                genericDraweeHierarchy.setFailureImage(UIHelper.getChatImageFail());
            } else {
                genericDraweeHierarchy.setPlaceholderImage(R.color.transparent);
                genericDraweeHierarchy.setFailureImage(R.color.transparent);
            }
            build.setHierarchy(genericDraweeHierarchy);
            simpleDraweeView.setController(build);
        }
    }

    public class ImageShowerMe extends ImageShower {
        final /* synthetic */ ChatListAdapter a;
        private aw b;
        private ImageUploader c;

        public ImageShowerMe(ChatListAdapter chatListAdapter, aw awVar, ImageUploader imageUploader) {
            this.a = chatListAdapter;
            this.c = imageUploader;
            this.b = awVar;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_image_right, null);
            }
            f a = f.a(view);
            this.a.a(chatMsg.msgsrc, a.c);
            a.a(chatMsg.status, i2);
            if (!this.a.a(chatMsg.status)) {
                a.b.setVisibility(8);
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.d.setVisibility(8);
            } else {
                a.d.setText(chatMsg.getTimeStr());
                a.d.setVisibility(0);
            }
            ChatMsgImageData a2 = ChatListAdapter.b(chatMsg.data);
            IMImageSize imageSize = IMImageSizeHelper.getImageSize(Size.Medium, a2.width, a2.height, this.a.h);
            String appendSmallSize = ChatListAdapter.appendSmallSize(a2.url, imageSize.getDstWidth(), imageSize.getDstHeight());
            ChatListAdapter.setLayoutParams(a.a, imageSize.getDstWidth(), imageSize.getDstHeight());
            a.a.setTag(chatMsg);
            if (Util.isLongImage(a2.width, a2.height)) {
                a.a.setScaleType(ScaleType.CENTER_CROP);
            }
            ResizeOptions resizeOptions = new ResizeOptions(imageSize.getDstWidth(), imageSize.getDstHeight());
            if (aw.a(chatMsg.msgid) || !ChatListAdapter.isNetworkUrl(a2.url)) {
                ImageShower.a((SimpleDraweeView) a.a, appendSmallSize, resizeOptions, true);
            } else {
                ImageShower.a((SimpleDraweeView) a.a, appendSmallSize, resizeOptions, false);
            }
            a.e.setOnLongClickListener(new ad(chatMsg));
            a.e.setOnClickListener(new ac(this.a, chatMsg, appendSmallSize, this.a.a(a2)));
            if (aw.a(chatMsg.msgid) || ChatListAdapter.isNetworkUrl(a2.url)) {
                a.h.setVisibility(8);
            } else {
                a.h.setVisibility(0);
                aw.a(chatMsg.dbid, this.c.sendImage(Uri.parse(a2.url), this.b, new av(a, chatMsg, imageSize.getDstWidth(), imageSize.getDstHeight())));
            }
            return view;
        }
    }

    public class ImageShowerOther extends ImageShower {
        final /* synthetic */ ChatListAdapter a;
        private boolean b;

        public ImageShowerOther(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            String str;
            if (view == null || !(view.getTag() instanceof g)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_image_left, null);
            }
            g a = g.a(view);
            this.a.a(chatMsg.msgsrc, a.c);
            this.a.a(chatMsg, a.f);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.d.setVisibility(8);
            } else {
                a.d.setText(chatMsg.getTimeStr());
                a.d.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, a.h);
            ChatMsgImageData a2 = ChatListAdapter.b(chatMsg.data);
            IMImageSize imageSize = IMImageSizeHelper.getImageSize(Size.Medium, a2.width, a2.height, this.a.h);
            String appendSmallSize = ChatListAdapter.appendSmallSize(a2.url, (imageSize.getDstWidth() / 3) + 1, (imageSize.getDstHeight() / 3) + 1);
            String a3 = this.a.a(a2);
            if (FrescoImageloader.isInMemoryCache(a3)) {
                str = a3;
            } else {
                str = appendSmallSize;
            }
            ChatListAdapter.setLayoutParams(a.a, imageSize.getDstWidth(), imageSize.getDstHeight());
            a.a.setTag(chatMsg);
            a.a.setImageResource(UIHelper.getChatImageDefault());
            this.b = Util.isLongImage(a2.width, a2.height);
            if (this.b) {
                a.a.setScaleType(ScaleType.CENTER_CROP);
            }
            ImageShower.a((SimpleDraweeView) a.a, str, new ResizeOptions(imageSize.getDstWidth(), imageSize.getDstHeight()), true);
            a.e.setOnLongClickListener(new ad(chatMsg));
            a.e.setOnClickListener(new ac(this.a, chatMsg, str, a3));
            if (this.a.p) {
                this.a.setRole(chatMsg.from, chatMsg.fromgender, a.g);
            } else {
                a.g.setVisibility(8);
            }
            return view;
        }
    }

    public class InviteShowerOther extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public InviteShowerOther(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            int i3 = -8882028;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_invite_left, null);
            }
            h a = h.a(view);
            InviteInfo inviteInfo = chatMsg.getInviteInfo();
            GroupInfo groupInfo = inviteInfo.group;
            int state = inviteInfo.getState();
            a.l = groupInfo;
            a.n = chatMsg;
            view.setOnClickListener(new v(this, groupInfo));
            a.c.setTextColor(UIHelper.isNightTheme() ? -8882028 : -12894910);
            if (state == -1) {
                a.c.setEnabled(true);
                a.c.setText(a.a() ? "立即加入" : "申请加入");
            } else {
                a.c.setEnabled(false);
                a.c.setText(state == 1 ? "已申请" : "已加入");
            }
            a.m = Integer.valueOf(chatMsg.uid).intValue();
            this.a.a(chatMsg.msgsrc, a.g);
            this.a.a(chatMsg, a.j);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.h.setVisibility(8);
            } else {
                a.h.setText(chatMsg.getTimeStr());
                a.h.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, a.d);
            TextView textView = a.a;
            if (UIHelper.isNightTheme()) {
                i3 = -10263970;
            }
            textView.setTextColor(i3);
            a.b.setTextColor(UIHelper.isNightTheme() ? -9802626 : -6908266);
            a.k.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -3355444);
            a.a.setText(inviteInfo.title);
            a.b.setText(groupInfo.description);
            ImageShower.a(a.e, groupInfo.icon);
            return view;
        }
    }

    public class LiveBeginViewHolder {
        RelativeLayout a;
        TextView b;
        ImageView c;
        ImageView d;
        ImageView e;
        TextView f;
        final /* synthetic */ ChatListAdapter g;

        public LiveBeginViewHolder(ChatListAdapter chatListAdapter) {
            this.g = chatListAdapter;
        }
    }

    public class LiveBeginViewHolderShareMe {
        RelativeLayout a;
        TextView b;
        ImageView c;
        ImageView d;
        TextView e;
        ImageView f;
        final /* synthetic */ ChatListAdapter g;

        public LiveBeginViewHolderShareMe(ChatListAdapter chatListAdapter) {
            this.g = chatListAdapter;
        }

        public void setStatus(int i, boolean z) {
            if (this.f != null) {
                if (z && this.g.a(i)) {
                    this.f.setVisibility(0);
                    int sendStatusRes = UIHelper.getSendStatusRes(i);
                    if (sendStatusRes <= 0) {
                        this.f.setVisibility(8);
                        return;
                    } else {
                        this.f.setImageResource(sendStatusRes);
                        return;
                    }
                }
                this.f.setVisibility(8);
            }
        }
    }

    public class MedalViewHolder {
        RelativeLayout a;
        TextView b;
        ImageView c;
        ImageView d;
        TextView e;
        final /* synthetic */ ChatListAdapter f;

        public MedalViewHolder(ChatListAdapter chatListAdapter) {
            this.f = chatListAdapter;
        }
    }

    public class OfficialViewHolderMultiple {
        TextView a;
        RelativeLayout b;
        ImageView c;
        TextView d;
        ListView e;
        final /* synthetic */ ChatListAdapter f;

        public OfficialViewHolderMultiple(ChatListAdapter chatListAdapter) {
            this.f = chatListAdapter;
        }
    }

    public class OfficialViewHolderSingle {
        LinearLayout a;
        TextView b;
        TextView c;
        ImageView d;
        TextView e;
        TextView f;
        final /* synthetic */ ChatListAdapter g;

        public OfficialViewHolderSingle(ChatListAdapter chatListAdapter) {
            this.g = chatListAdapter;
        }
    }

    public interface OnAvatarClickListener {
        void onAvatarClick(BaseUserInfo baseUserInfo);
    }

    public class OtherJoinedSuccessed extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public OtherJoinedSuccessed(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_other_joined_group_success, null);
            }
            i a = i.a(view);
            a.a.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.border_night : R.drawable.border);
            a.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.drawable_location_night : R.drawable.location);
            Pair newMemberJoinInfo = chatMsg.getNewMemberJoinInfo();
            BaseUserInfo baseUserInfo = (BaseUserInfo) newMemberJoinInfo.first;
            a.l = baseUserInfo;
            a.m = this.a.s;
            a.n = this.a.t;
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.c.setVisibility(8);
            } else {
                a.c.setText(chatMsg.getTimeStr());
                a.c.setVisibility(0);
            }
            this.a.a(baseUserInfo.userId, baseUserInfo.userIcon, baseUserInfo.userName, a.f);
            a.d.setTextColor(UIHelper.isNightTheme() ? -12171437 : -10263970);
            a.d.setText((CharSequence) newMemberJoinInfo.second);
            a.e.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -3355444);
            CharSequence remark = RemarkManager.getRemark(baseUserInfo.userId);
            if (!TextUtils.isEmpty(remark)) {
                a.g.setText(remark);
                a.g.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            } else if (baseUserInfo.userName != null) {
                a.g.setText(baseUserInfo.userName);
                a.g.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            }
            if (!UIHelper.isNightTheme()) {
                if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                    a.j.setBackgroundResource(R.drawable.pinfo_female_bg);
                    a.h.setImageResource(R.drawable.pinfo_female);
                } else {
                    a.j.setBackgroundResource(R.drawable.pinfo_man_bg);
                    a.h.setImageResource(R.drawable.pinfo_male);
                }
                a.i.setTextColor(-1);
            } else if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                a.h.setImageResource(R.drawable.pinfo_female_dark);
                a.i.setTextColor(this.a.h.getResources().getColor(R.color.age_female));
            } else {
                a.h.setImageResource(R.drawable.pinfo_male_dark);
                a.i.setTextColor(this.a.h.getResources().getColor(R.color.age_male));
            }
            if (baseUserInfo.age > 0) {
                a.i.setText(String.valueOf(baseUserInfo.age));
            } else {
                a.i.setText("");
            }
            if (TextUtils.isEmpty(baseUserInfo.baseHaunt)) {
                a.o.setVisibility(8);
            } else {
                a.o.setVisibility(0);
                a.k.setText(baseUserInfo.baseHaunt);
                a.k.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
            }
            a.p.setImageResource(UIHelper.isNightTheme() ? R.drawable.show_info_night : R.drawable.show_info);
            return view;
        }
    }

    public class PureEmotionShower implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        public PureEmotionShower(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_emotion_right, null);
                e eVar = new e();
                eVar.d = (TextView) view.findViewById(R.id.sendDate);
                eVar.b = (ImageView) view.findViewById(R.id.id_status_view);
                eVar.c = (TextView) view.findViewById(R.id.id_msg_source);
                eVar.a = (ImageView) view.findViewById(R.id.image);
                view.setTag(eVar);
            }
            e eVar2 = (e) view.getTag();
            this.a.a(chatMsg.msgsrc, eVar2.c);
            eVar2.a(chatMsg.status, i2);
            if (!this.a.a(chatMsg.status)) {
                eVar2.b.setVisibility(8);
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                eVar2.d.setVisibility(8);
            } else {
                eVar2.d.setText(chatMsg.getTimeStr());
                eVar2.d.setVisibility(0);
            }
            EmotionShowerHelper.show(chatMsg, eVar2.a);
            view.setOnLongClickListener(new ad(chatMsg));
            return view;
        }
    }

    public class PureEmotionShowerOther implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        public PureEmotionShowerOther(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_emotion_left, null);
            }
            ae a = ae.a(view);
            this.a.a(chatMsg.msgsrc, a.c);
            this.a.a(chatMsg, a.f);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.d.setVisibility(8);
            } else {
                a.d.setText(chatMsg.getTimeStr());
                a.d.setVisibility(0);
            }
            EmotionShowerHelper.show(chatMsg, a.a);
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, a.h);
            view.setOnLongClickListener(new ad(chatMsg));
            if (this.a.p) {
                this.a.setRole(chatMsg.from, chatMsg.fromgender, a.g);
            } else {
                a.g.setVisibility(8);
            }
            return view;
        }
    }

    public class QiushiPushShower implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        public QiushiPushShower(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_qiushi_push_msg, null);
                view.setTag(al.a(view));
            }
            al alVar = (al) view.getTag();
            if (!chatMsg.showTime || chatMsg.time == 0) {
                alVar.a.setVisibility(8);
            } else {
                alVar.a.setText(chatMsg.getTimeStr());
                alVar.a.setVisibility(0);
            }
            alVar.b.setData(chatMsg);
            alVar.b.setOnLongClickListener(new ad(chatMsg));
            return view;
        }
    }

    public class QiushiShareViewHolderMe {
        RelativeLayout a;
        TextView b;
        ImageView c;
        ImageView d;
        TextView e;
        ImageView f;
        ImageView g;
        final /* synthetic */ ChatListAdapter h;

        public QiushiShareViewHolderMe(ChatListAdapter chatListAdapter) {
            this.h = chatListAdapter;
        }

        public void setStatus(int i, boolean z) {
            if (this.f != null) {
                if (z && this.h.a(i)) {
                    this.f.setVisibility(0);
                    int sendStatusRes = UIHelper.getSendStatusRes(i);
                    if (sendStatusRes <= 0) {
                        this.f.setVisibility(8);
                        return;
                    } else {
                        this.f.setImageResource(sendStatusRes);
                        return;
                    }
                }
                this.f.setVisibility(8);
            }
        }
    }

    public class QiushiShareViewHolderOther {
        RelativeLayout a;
        TextView b;
        ImageView c;
        ImageView d;
        ImageView e;
        TextView f;
        ImageView g;
        final /* synthetic */ ChatListAdapter h;

        public QiushiShareViewHolderOther(ChatListAdapter chatListAdapter) {
            this.h = chatListAdapter;
        }
    }

    public class SpringFestivalHolder {
        RelativeLayout a;
        TextView b;
        ImageView c;
        View d;
        TextView e;
        TextView f;
        ImageView g;
        View h;
        final /* synthetic */ ChatListAdapter i;

        public SpringFestivalHolder(ChatListAdapter chatListAdapter, View view) {
            this.i = chatListAdapter;
            this.a = (RelativeLayout) view.findViewById(R.id.container);
            this.e = (TextView) view.findViewById(R.id.content);
            this.b = (TextView) view.findViewById(R.id.sendDate);
            this.c = (ImageView) view.findViewById(R.id.coverImage);
            this.d = view.findViewById(R.id.image_container);
            this.f = (TextView) view.findViewById(R.id.link);
            this.g = (ImageView) view.findViewById(R.id.iv_userhead);
            this.h = view.findViewById(R.id.link_container);
        }
    }

    public class TxtViewShower implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        public TxtViewShower(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            ViewHolder viewHolder;
            if (view == null || !(view.getTag() instanceof ViewHolder)) {
                View inflate;
                if (i2 == 2) {
                    inflate = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_text_right, null);
                } else {
                    inflate = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_text_left, null);
                }
                ViewHolder viewHolder2 = new ViewHolder(this.a);
                if (inflate.findViewById(R.id.id_status_view) != null) {
                    viewHolder2.c = (ImageView) inflate.findViewById(R.id.id_status_view);
                }
                viewHolder2.b = (TextView) inflate.findViewById(R.id.tv_chatcontent);
                viewHolder2.a = (ImageView) inflate.findViewById(R.id.iv_userhead);
                viewHolder2.msgsource = (TextView) inflate.findViewById(R.id.id_msg_source);
                viewHolder2.sendTime = (TextView) inflate.findViewById(R.id.sendDate);
                viewHolder2.d = (TextView) inflate.findViewById(R.id.tv_username);
                viewHolder2.e = (TextView) inflate.findViewById(R.id.role);
                inflate.setTag(viewHolder2);
                viewHolder = viewHolder2;
                view = inflate;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            this.a.a(chatMsg, viewHolder.d);
            float textSize = viewHolder.b.getTextSize();
            CharSequence spannableString = new SpannableString(chatMsg.data);
            this.a.matchUrl(spannableString, i2, textSize);
            viewHolder.b.setText(spannableString);
            viewHolder.b.setMovementMethod(LinkMovementMethod.getInstance());
            this.a.a(chatMsg.msgsrc, viewHolder.msgsource);
            if (i2 == 2) {
                viewHolder.a(chatMsg.status, i2);
            } else {
                viewHolder.a(chatMsg.status, i2);
                this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, viewHolder.a);
                LogUtil.d("here is a break");
                if (this.a.p) {
                    this.a.setRole(chatMsg.from, chatMsg.fromgender, viewHolder.e);
                } else {
                    viewHolder.e.setVisibility(8);
                }
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                viewHolder.sendTime.setVisibility(8);
            } else {
                viewHolder.sendTime.setText(chatMsg.getTimeStr());
                viewHolder.sendTime.setVisibility(0);
            }
            viewHolder.b.setLongClickable(true);
            viewHolder.b.setOnLongClickListener(new az(this, chatMsg));
            return view;
        }
    }

    public interface UploadedListener {
        void uploaded(ChatMsg chatMsg);
    }

    public static class UserHeadClickListener implements OnClickListener {
        private String a;
        private String b;
        private OnAvatarClickListener c;
        private Context d;
        public String uid;
        public String userName;

        UserHeadClickListener(String str, String str2, String str3, String str4, OnAvatarClickListener onAvatarClickListener, Context context) {
            this.uid = str;
            this.userName = str2;
            this.d = context;
            this.a = str3;
            this.b = str4;
            this.c = onAvatarClickListener;
        }

        public void onClick(View view) {
            BaseUserInfo baseUserInfo = new BaseUserInfo();
            baseUserInfo.userId = this.uid;
            baseUserInfo.userName = this.userName;
            if (this.c != null) {
                this.c.onAvatarClick(baseUserInfo);
                return;
            }
            MyInfoActivity.launch(this.d, baseUserInfo.userId, this.a, this.b, MyInfoActivity.FANS_ORIGINS[3], new IMChatMsgSource(7, String.valueOf(baseUserInfo.userId), String.valueOf(this.a) + Config.TRACE_TODAY_VISIT_SPLIT + this.b));
        }
    }

    public static class UserHeadLongClickListener implements OnLongClickListener {
        private GroupConversationActivity a;
        public String uid;
        public String userName;

        UserHeadLongClickListener(String str, String str2, String str3, String str4, GroupConversationActivity groupConversationActivity) {
            this.uid = str;
            this.userName = str2;
            this.a = groupConversationActivity;
        }

        public boolean onLongClick(View view) {
            this.a.insertAtUser(this.uid, this.userName);
            return true;
        }
    }

    public class ViewHolder {
        ImageView a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        final /* synthetic */ ChatListAdapter f;
        public TextView msgsource;
        public TextView sendTime;

        public ViewHolder(ChatListAdapter chatListAdapter) {
            this.f = chatListAdapter;
        }

        private void a(int i, int i2) {
            if (this.c != null) {
                if (this.f.a(i) && i2 == 2) {
                    this.c.setVisibility(0);
                    int sendStatusRes = UIHelper.getSendStatusRes(i);
                    if (sendStatusRes <= 0) {
                        this.c.setVisibility(8);
                        return;
                    } else {
                        this.c.setImageResource(sendStatusRes);
                        return;
                    }
                }
                this.c.setVisibility(8);
            }
        }
    }

    public class VoiceShowerMe implements IChatViewShower, VoiceCallback {
        final /* synthetic */ ChatListAdapter a;
        private UploadedListener b;
        private VoiceManager c;

        public VoiceShowerMe(ChatListAdapter chatListAdapter, VoiceManager voiceManager, UploadedListener uploadedListener) {
            this.a = chatListAdapter;
            this.c = voiceManager;
            this.b = uploadedListener;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_voice_right, null);
            }
            ay a = ay.a(view);
            a.l = true;
            this.a.a(chatMsg.msgsrc, a.f);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.g.setVisibility(8);
            } else {
                a.g.setText(chatMsg.getTimeStr());
                a.g.setVisibility(0);
            }
            a.k = chatMsg;
            OnClickListener bbVar = new bb(this, a);
            ChatMsgVoiceData a2 = a(chatMsg);
            a.d.setText(ChatMsgVoiceData.formatDuration(a2.duration));
            a.j.setImageResource(this.a.r.a(a) ? R.drawable.play_white_9 : R.drawable.play_white_0);
            if (TextUtils.isEmpty(a2.path)) {
                a2.path = VoiceManager.getPath(a2.url);
            }
            if (TextUtils.isEmpty(a2.url)) {
                a.d.setEnabled(false);
                if (!a(chatMsg.msgid)) {
                    b(chatMsg.msgid);
                    this.c.send(a2.path, this, a);
                }
            } else {
                a.d.setEnabled(true);
            }
            this.a.a(a.h, a2);
            a.h.setOnClickListener(bbVar);
            a.h.setOnLongClickListener(new ad(chatMsg));
            a.a(chatMsg.status, 17);
            if (!this.a.a(chatMsg.status)) {
                a.e.setVisibility(8);
            }
            return view;
        }

        boolean a(String str) {
            return ChatListAdapter.b.contains(str);
        }

        boolean b(String str) {
            if (a(str)) {
                return false;
            }
            return ChatListAdapter.b.add(str);
        }

        boolean c(String str) {
            return ChatListAdapter.b.remove(str);
        }

        public void onStart(String str, Object obj) {
            ay ayVar = (ay) obj;
            ChatMsgVoiceData a = a(ayVar.k);
            if (a.status == 1 || a.url != null) {
                ayVar.b.setMinProgress(0);
                return;
            }
            Integer num = (Integer) ChatListAdapter.a.get(a.path);
            ayVar.b.setMinProgress(Math.max(num == null ? 0 : num.intValue(), a.progress));
            b(ayVar.k.msgid);
        }

        public void onSuccess(String str, String str2, Object obj) {
            ay ayVar = (ay) obj;
            ChatMsgVoiceData a = a(ayVar.k);
            if (str.equals(a.path)) {
                File file = new File(str);
                a.status = 1;
                a.url = str2;
                a.progress = 100;
                a.path = VoiceManager.getPath(str2);
                VoiceManager.forceRename(file, new File(a.path));
                ayVar.k.data = a.encodeToJsonObject().toString();
                if (this.b != null) {
                    this.b.uploaded(ayVar.k);
                }
                ayVar.d.setEnabled(true);
            }
            c(ayVar.k.msgid);
        }

        public void onFaiure(String str, String str2, Object obj) {
            c(((ay) obj).k.msgid);
            Integer num = (Integer) ChatListAdapter.a.get(str);
        }

        public void onProgress(String str, long j, long j2, Object obj) {
            if (str.equals(a(((ay) obj).k).path)) {
                ChatListAdapter.a.put(str, Integer.valueOf((int) ((100 * j) / j2)));
            }
        }

        private ChatMsgVoiceData a(ChatMsg chatMsg) {
            if (chatMsg.tag != null) {
                return (ChatMsgVoiceData) chatMsg.tag;
            }
            ChatMsgVoiceData chatMsgVoiceData = new ChatMsgVoiceData(chatMsg.data);
            chatMsg.tag = chatMsgVoiceData;
            return chatMsgVoiceData;
        }
    }

    public class VoiceShowerOther implements IChatViewShower, VoiceCallback {
        final /* synthetic */ ChatListAdapter a;
        private VoiceManager b;

        public VoiceShowerOther(ChatListAdapter chatListAdapter, VoiceManager voiceManager) {
            this.a = chatListAdapter;
            this.b = voiceManager;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_voice_left, null);
            }
            ay a = ay.a(view);
            a.l = false;
            this.a.a(chatMsg.msgsrc, a.f);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                a.g.setVisibility(8);
            } else {
                a.g.setText(chatMsg.getTimeStr());
                a.g.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, a.i);
            a.k = chatMsg;
            OnClickListener bcVar = new bc(this, a);
            ChatMsgVoiceData a2 = a(chatMsg);
            a.d.setText(ChatMsgVoiceData.formatDuration(a2.duration));
            a.j.setImageResource(this.a.r.a(a) ? R.drawable.play_blue_9 : R.drawable.play_blue_0);
            if (TextUtils.isEmpty(a2.path)) {
                CharSequence pathIfDownload = this.b.getPathIfDownload(a2.url);
                a2.path = pathIfDownload;
                if (TextUtils.isEmpty(pathIfDownload)) {
                    a.d.setEnabled(false);
                    if (!a(chatMsg.msgid)) {
                        b(chatMsg.msgid);
                        this.b.download(a2.url, this, a);
                    }
                    if (a2.played) {
                        a.m.setVisibility(0);
                        a.m.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.found_item_tips_night : R.drawable.found_item_tips);
                    } else {
                        a.m.setVisibility(8);
                    }
                    a.h.setOnClickListener(bcVar);
                    a.h.setOnLongClickListener(new ad(chatMsg));
                    this.a.a(a.h, a2);
                    return view;
                }
            }
            a.d.setEnabled(true);
            a(a);
            if (a2.played) {
                a.m.setVisibility(0);
                if (UIHelper.isNightTheme()) {
                }
                a.m.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.found_item_tips_night : R.drawable.found_item_tips);
            } else {
                a.m.setVisibility(8);
            }
            a.h.setOnClickListener(bcVar);
            a.h.setOnLongClickListener(new ad(chatMsg));
            this.a.a(a.h, a2);
            return view;
        }

        void a(ay ayVar, int i) {
        }

        void a(ay ayVar) {
            ayVar.a.setVisibility(8);
        }

        boolean a(String str) {
            return ChatListAdapter.d.contains(str);
        }

        boolean b(String str) {
            if (a(str)) {
                return false;
            }
            return ChatListAdapter.d.add(str);
        }

        boolean c(String str) {
            return ChatListAdapter.d.remove(str);
        }

        public void onStart(String str, Object obj) {
            ay ayVar;
            ChatMsg chatMsg;
            if (obj instanceof ay) {
                ay ayVar2 = (ay) obj;
                ayVar = ayVar2;
                chatMsg = ayVar2.k;
            } else {
                chatMsg = (ChatMsg) obj;
                ayVar = null;
            }
            ChatMsgVoiceData a = a(chatMsg);
            if (a.status != 1 && a.path == null) {
                b(chatMsg.msgid);
                if (ayVar != null) {
                    Integer num = (Integer) ChatListAdapter.c.get(str);
                    ayVar.b.setMinProgress(Math.max(num == null ? 0 : num.intValue(), a.progress));
                    a(ayVar, 0);
                }
            } else if (ayVar != null) {
                ayVar.b.setMinProgress(0);
            }
        }

        public void onSuccess(String str, String str2, Object obj) {
            ay ayVar;
            ChatMsg chatMsg;
            if (obj instanceof ay) {
                ay ayVar2 = (ay) obj;
                ayVar = ayVar2;
                chatMsg = ayVar2.k;
            } else {
                ayVar = null;
                chatMsg = (ChatMsg) obj;
            }
            ChatMsgVoiceData a = a(chatMsg);
            if (str.equals(a.url)) {
                a.status = 1;
                a.path = str2;
                a.progress = 100;
                if (ayVar != null) {
                    ayVar.d.setEnabled(true);
                    if (this.a.r.a(ayVar)) {
                        this.a.r.b(ayVar);
                    }
                    a(ayVar);
                } else if (this.a.r.d == chatMsg) {
                    this.a.r.c(chatMsg);
                }
            }
            c(chatMsg.msgid);
        }

        public void onFaiure(String str, String str2, Object obj) {
            ay ayVar;
            if (obj instanceof ay) {
                ay ayVar2 = (ay) obj;
                ayVar = ayVar2;
                obj = ayVar2.k;
            } else {
                ChatMsg chatMsg = (ChatMsg) obj;
                ayVar = null;
            }
            c(obj.msgid);
            Integer num = (Integer) ChatListAdapter.c.get(str);
            if (ayVar != null) {
                a(ayVar, num == null ? 0 : num.intValue());
            }
        }

        public void onProgress(String str, long j, long j2, Object obj) {
            ay ayVar;
            ChatMsg chatMsg;
            if (obj instanceof ay) {
                ay ayVar2 = (ay) obj;
                ayVar = ayVar2;
                chatMsg = ayVar2.k;
            } else {
                ayVar = null;
                chatMsg = (ChatMsg) obj;
            }
            if (str.equals(a(chatMsg).url)) {
                int i = j2 != 0 ? (int) ((100 * j) / j2) : 0;
                ChatListAdapter.c.put(str, Integer.valueOf(i));
                if (ayVar != null) {
                    a(ayVar, i);
                }
            }
        }

        private ChatMsgVoiceData a(ChatMsg chatMsg) {
            if (chatMsg.tag != null) {
                return (ChatMsgVoiceData) chatMsg.tag;
            }
            ChatMsgVoiceData chatMsgVoiceData = new ChatMsgVoiceData(chatMsg.data);
            chatMsg.tag = chatMsgVoiceData;
            return chatMsgVoiceData;
        }
    }

    private class a extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public a(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            DuobaoViewHolder duobaoViewHolder;
            JSONObject jSONObject;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.duobao_im_official_item, null);
                DuobaoViewHolder duobaoViewHolder2 = new DuobaoViewHolder(this.a);
                duobaoViewHolder2.b = (TextView) view.findViewById(R.id.sendDate);
                duobaoViewHolder2.a = (RelativeLayout) view.findViewById(R.id.container);
                duobaoViewHolder2.g = (ImageView) view.findViewById(R.id.iv_userhead);
                duobaoViewHolder2.c = (ImageView) view.findViewById(R.id.coverImage);
                duobaoViewHolder2.d = (TextView) view.findViewById(R.id.tvBrief);
                duobaoViewHolder2.e = (TextView) view.findViewById(R.id.check);
                duobaoViewHolder2.f = (TextView) view.findViewById(R.id.takeTreasure);
                duobaoViewHolder2.h = view.findViewById(R.id.check_view);
                view.setTag(duobaoViewHolder2);
                duobaoViewHolder = duobaoViewHolder2;
            } else {
                duobaoViewHolder = (DuobaoViewHolder) view.getTag();
            }
            duobaoViewHolder.c.setImageDrawable(null);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                duobaoViewHolder.b.setVisibility(8);
            } else {
                duobaoViewHolder.b.setText(chatMsg.getTimeStr());
                duobaoViewHolder.b.setVisibility(0);
            }
            try {
                jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject != null) {
                Object optString = jSONObject.optString("icon");
                CharSequence optString2 = jSONObject.optString("title");
                CharSequence optString3 = jSONObject.optString("left_url");
                String optString4 = jSONObject.optString("right_url");
                jSONObject.optString("action");
                CharSequence optString5 = jSONObject.optString("left_btn");
                CharSequence optString6 = jSONObject.optString("right_btn");
                duobaoViewHolder.d.setText(optString2);
                if (!TextUtils.isEmpty(optString)) {
                    ImageShower.a(duobaoViewHolder.c, optString);
                }
                this.a.a(chatMsg.from, chatMsg.fromicon, chatMsg.fromnick, duobaoViewHolder.g);
                if (TextUtils.isEmpty(optString3)) {
                    duobaoViewHolder.e.setVisibility(8);
                    duobaoViewHolder.f.setVisibility(0);
                    duobaoViewHolder.h.setVisibility(8);
                } else if (!TextUtils.isEmpty(optString3)) {
                    duobaoViewHolder.e.setVisibility(0);
                    duobaoViewHolder.f.setVisibility(0);
                    duobaoViewHolder.h.setVisibility(0);
                }
                if (!TextUtils.isEmpty(optString5)) {
                    duobaoViewHolder.e.setText(optString5);
                }
                if (!TextUtils.isEmpty(optString6)) {
                    duobaoViewHolder.f.setText(optString6);
                }
                duobaoViewHolder.a.setOnLongClickListener(new ad(chatMsg));
                duobaoViewHolder.a.setOnClickListener(null);
                duobaoViewHolder.e.setOnClickListener(new o(this, optString3));
                duobaoViewHolder.f.setOnClickListener(new p(this, optString4));
            }
            return view;
        }
    }

    private class aa extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public aa(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            OfficialViewHolderSingle officialViewHolderSingle;
            JSONObject jSONObject;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_official_msg_single, null);
                OfficialViewHolderSingle officialViewHolderSingle2 = new OfficialViewHolderSingle(this.a);
                officialViewHolderSingle2.b = (TextView) view.findViewById(R.id.sendDate);
                officialViewHolderSingle2.a = (LinearLayout) view.findViewById(R.id.container);
                officialViewHolderSingle2.c = (TextView) view.findViewById(R.id.title);
                officialViewHolderSingle2.d = (ImageView) view.findViewById(R.id.cover);
                officialViewHolderSingle2.e = (TextView) view.findViewById(R.id.brief);
                officialViewHolderSingle2.f = (TextView) view.findViewById(R.id.action);
                view.setTag(officialViewHolderSingle2);
                officialViewHolderSingle = officialViewHolderSingle2;
            } else {
                officialViewHolderSingle = (OfficialViewHolderSingle) view.getTag();
            }
            officialViewHolderSingle.d.setImageDrawable(null);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                officialViewHolderSingle.b.setVisibility(8);
            } else {
                officialViewHolderSingle.b.setText(chatMsg.getTimeStr());
                officialViewHolderSingle.b.setVisibility(0);
            }
            try {
                jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject != null) {
                String optString = jSONObject.optString("msg_id");
                Object optString2 = jSONObject.optString("title");
                Object optString3 = jSONObject.optString("cover");
                String optString4 = jSONObject.optString("url");
                CharSequence optString5 = jSONObject.optString("brief");
                String optString6 = jSONObject.optString("action");
                String optString7 = jSONObject.optString("post_ids");
                jSONObject.optInt("circle_topic", 0);
                officialViewHolderSingle.c.setText(optString2);
                officialViewHolderSingle.e.setText(optString5);
                if (optString6.equalsIgnoreCase(com.alipay.sdk.sys.a.j)) {
                    officialViewHolderSingle.f.setText("去设置");
                } else {
                    officialViewHolderSingle.f.setText("查看全文");
                }
                if (!TextUtils.isEmpty(optString3)) {
                    ImageShower.a(officialViewHolderSingle.d, optString3);
                    officialViewHolderSingle.a.setOnLongClickListener(new ad(chatMsg));
                    if (optString6.equalsIgnoreCase(com.alipay.sdk.sys.a.j)) {
                        officialViewHolderSingle.f.setOnClickListener(new am(this));
                    } else if (optString6.equalsIgnoreCase("open")) {
                        officialViewHolderSingle.f.setClickable(false);
                        officialViewHolderSingle.a.setOnClickListener(new an(this, optString4, optString2, optString, optString7, chatMsg));
                    } else if (optString6.equalsIgnoreCase(EventWindow.JUMP_DUOBAO)) {
                        officialViewHolderSingle.f.setClickable(false);
                        officialViewHolderSingle.a.setOnClickListener(new ao(this, optString4));
                    }
                }
            }
            return view;
        }
    }

    class ab {
        TextView a;
        ImageView b;
        final /* synthetic */ ChatListAdapter c;

        ab(ChatListAdapter chatListAdapter) {
            this.c = chatListAdapter;
        }
    }

    private class ac implements OnClickListener {
        String a;
        String b;
        ChatMsg c;
        int d;
        final /* synthetic */ ChatListAdapter e;

        public ac(ChatListAdapter chatListAdapter, ChatMsg chatMsg, String str, String str2) {
            this.e = chatListAdapter;
            this.a = str;
            this.b = str2;
            this.c = chatMsg;
        }

        public void onClick(View view) {
            ListView listView;
            ViewParent parent = view.getParent();
            while (!(parent instanceof ListView)) {
                parent = parent.getParent();
                if (!(parent instanceof ListView)) {
                    if (parent == null) {
                        listView = null;
                        break;
                    }
                }
                listView = (ListView) parent;
                break;
            }
            listView = null;
            a(listView, this.c);
            view.getContext();
            ImageViewer.launch(Util.getActivityOrContext(view), this.d, this.e.n, (Rect[]) this.e.o.toArray(new Rect[this.e.o.size()]));
        }

        private void a(ListView listView, ChatMsg chatMsg) {
            this.e.n.clear();
            this.e.o.clear();
            this.d = 0;
            int i = 0;
            for (ChatMsg chatMsg2 : this.e.e) {
                if (chatMsg2.type == 3) {
                    String appendSmallSize;
                    Object rectOnScreen;
                    if (TextUtils.equals(chatMsg.msgid, chatMsg2.msgid)) {
                        i = 1;
                    } else if (i == 0) {
                        this.d++;
                    }
                    ChatMsgImageData a = ChatListAdapter.b(chatMsg2.data);
                    IMImageSize imageSize = IMImageSizeHelper.getImageSize(Size.Medium, a.width, a.height, this.e.h);
                    if (chatMsg2.inout == 2) {
                        appendSmallSize = ChatListAdapter.appendSmallSize(a.url, imageSize.getDstWidth(), imageSize.getDstHeight());
                    } else {
                        appendSmallSize = ChatListAdapter.appendSmallSize(a.url, (imageSize.getDstWidth() / 3) + 1, (imageSize.getDstHeight() / 3) + 1);
                    }
                    ImageInfo imageInfo = new ImageInfo(appendSmallSize, this.e.a(a));
                    imageInfo.mediaFormat = Util.isLongImage(a.width, a.height) ? MediaFormat.IMAGE_LONG : MediaFormat.IMAGE_STATIC;
                    if (Util.isLongImage(a.width, a.height)) {
                        imageInfo.mediaFormat = MediaFormat.IMAGE_LONG;
                    }
                    this.e.n.add(imageInfo);
                    if (listView != null) {
                        View findViewWithTag = listView.findViewWithTag(chatMsg2);
                        if (findViewWithTag != null) {
                            rectOnScreen = UIHelper.getRectOnScreen(findViewWithTag);
                            this.e.o.add(rectOnScreen);
                        }
                    }
                    rectOnScreen = null;
                    this.e.o.add(rectOnScreen);
                }
            }
        }
    }

    private static class ad implements OnLongClickListener {
        ChatMsg a;

        public ad(ChatMsg chatMsg) {
            this.a = chatMsg;
        }

        public boolean onLongClick(View view) {
            if (this.a != null) {
                if (view.getContext() instanceof ConversationActivity) {
                    ((ConversationActivity) view.getContext()).onRichMediaContentLongClick(this.a.dbid, this.a);
                } else if (view.getContext() instanceof GroupConversationActivity) {
                    ((GroupConversationActivity) view.getContext()).onRichMediaContentLongClick(this.a.dbid, this.a);
                }
            }
            return true;
        }
    }

    private static class e {
        ImageView a;
        ImageView b;
        TextView c;
        TextView d;
        View e;
        TextView f;
        TextView g;

        private e() {
        }

        void a(int i, int i2) {
            if (this.b != null) {
                if (i2 == 5 || i2 == 8) {
                    this.b.setVisibility(0);
                    int sendStatusRes = UIHelper.getSendStatusRes(i);
                    if (sendStatusRes <= 0) {
                        this.b.setVisibility(8);
                        return;
                    } else {
                        this.b.setImageResource(sendStatusRes);
                        return;
                    }
                }
                this.b.setVisibility(8);
            }
        }
    }

    private static class ae extends e {
        ImageView h;

        private ae() {
            super();
        }

        static ae a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof ae)) {
                return (ae) tag;
            }
            ae aeVar = new ae();
            aeVar.a = (ImageView) view.findViewById(R.id.image);
            aeVar.c = (TextView) view.findViewById(R.id.id_msg_source);
            aeVar.d = (TextView) view.findViewById(R.id.sendDate);
            aeVar.b = (ImageView) view.findViewById(R.id.id_status_view);
            aeVar.h = (ImageView) view.findViewById(R.id.iv_userhead);
            aeVar.e = view.findViewById(R.id.imageContent);
            aeVar.f = (TextView) view.findViewById(R.id.tv_username);
            aeVar.g = (TextView) view.findViewById(R.id.role);
            view.setTag(aeVar);
            return aeVar;
        }
    }

    class af {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        final /* synthetic */ ChatListAdapter f;

        public af(ChatListAdapter chatListAdapter, View view) {
            this.f = chatListAdapter;
            this.a = (TextView) view.findViewById(R.id.content);
            this.b = (TextView) view.findViewById(R.id.btn);
            this.c = (ImageView) view.findViewById(R.id.iv_userhead);
            this.d = (TextView) view.findViewById(R.id.tv_username);
            this.e = (TextView) view.findViewById(R.id.sendDate);
        }
    }

    private class ag implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private ag(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            af afVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_deep_link_other, null);
                afVar = new af(this.a, view);
                view.setTag(afVar);
            } else {
                afVar = (af) view.getTag();
            }
            try {
                this.a.a(chatMsg, afVar.d);
                this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, afVar.c);
                DeeplinkInfo deeplinkInfo = chatMsg.getDeeplinkInfo();
                if (deeplinkInfo != null) {
                    afVar.a.setText(deeplinkInfo.content);
                    afVar.b.setText(deeplinkInfo.btnDesc);
                    afVar.b.setOnClickListener(new ap(this, deeplinkInfo));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return view;
        }
    }

    private class ah implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private ah(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            aj ajVar;
            if (view == null || !(view.getTag() instanceof aj)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qsjx_share_msg_right, null);
                ajVar = new aj(this.a, view);
                view.setTag(ajVar);
            } else {
                ajVar = (aj) view.getTag();
            }
            Qsjx qsjxInfo = chatMsg.getQsjxInfo();
            if (qsjxInfo != null) {
                FrescoImageloader.displayImage(ajVar.c, qsjxInfo.picUrl);
                ajVar.d.setText(qsjxInfo.getShareTitle());
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                ajVar.a.setVisibility(8);
            } else {
                ajVar.a.setText(chatMsg.getTimeStr());
                ajVar.a.setVisibility(0);
            }
            ajVar.b.setOnClickListener(new aq(this, qsjxInfo));
            return view;
        }
    }

    private class ai implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private ai(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            ak akVar;
            if (view == null || !(view.getTag() instanceof QiushiShareViewHolderOther)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qsjx_share_msg_left, null);
                akVar = new ak(this.a, view);
                view.setTag(akVar);
            } else {
                akVar = (ak) view.getTag();
            }
            Qsjx qsjxInfo = chatMsg.getQsjxInfo();
            if (qsjxInfo != null) {
                FrescoImageloader.displayImage(akVar.d, qsjxInfo.picUrl);
                akVar.e.setText(qsjxInfo.getShareTitle());
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                akVar.a.setVisibility(8);
            } else {
                akVar.a.setText(chatMsg.getTimeStr());
                akVar.a.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, akVar.b);
            akVar.c.setOnClickListener(new ar(this, qsjxInfo));
            return view;
        }
    }

    private class aj {
        TextView a;
        RelativeLayout b;
        ImageView c;
        TextView d;
        final /* synthetic */ ChatListAdapter e;

        public aj(ChatListAdapter chatListAdapter, View view) {
            this.e = chatListAdapter;
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.c = (ImageView) view.findViewById(R.id.coverImage);
            this.d = (TextView) view.findViewById(R.id.tvBrief);
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.b = (RelativeLayout) view.findViewById(R.id.msgContainer);
        }
    }

    private class ak {
        TextView a;
        ImageView b;
        RelativeLayout c;
        ImageView d;
        TextView e;
        final /* synthetic */ ChatListAdapter f;

        public ak(ChatListAdapter chatListAdapter, View view) {
            this.f = chatListAdapter;
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.d = (ImageView) view.findViewById(R.id.coverImage);
            this.e = (TextView) view.findViewById(R.id.tvBrief);
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.c = (RelativeLayout) view.findViewById(R.id.msgContainer);
            this.b = (ImageView) view.findViewById(R.id.iv_userhead);
        }
    }

    private static class al {
        TextView a;
        QiushiPushView b;

        private al() {
        }

        static al a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof al)) {
                return (al) tag;
            }
            al alVar = new al();
            alVar.a = (TextView) view.findViewById(R.id.sendDate);
            alVar.b = (QiushiPushView) view.findViewById(R.id.qiushi_push);
            return alVar;
        }
    }

    private class am extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public am(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        @SuppressLint({"NewApi"})
        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            QiushiShareViewHolderMe qiushiShareViewHolderMe;
            boolean z;
            JSONObject jSONObject = null;
            if (view == null || !(view.getTag() instanceof QiushiShareViewHolderMe)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qiushi_share_msg_right, null);
                QiushiShareViewHolderMe qiushiShareViewHolderMe2 = new QiushiShareViewHolderMe(this.a);
                qiushiShareViewHolderMe2.b = (TextView) view.findViewById(R.id.sendDate);
                qiushiShareViewHolderMe2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                qiushiShareViewHolderMe2.c = (ImageView) view.findViewById(R.id.coverImage);
                qiushiShareViewHolderMe2.d = (ImageView) view.findViewById(R.id.videoIndicator);
                qiushiShareViewHolderMe2.e = (TextView) view.findViewById(R.id.tvBrief);
                qiushiShareViewHolderMe2.f = (ImageView) view.findViewById(R.id.iv_status_view);
                qiushiShareViewHolderMe2.g = (ImageView) view.findViewById(R.id.gif_tag);
                view.setTag(qiushiShareViewHolderMe2);
                qiushiShareViewHolderMe = qiushiShareViewHolderMe2;
            } else {
                qiushiShareViewHolderMe = (QiushiShareViewHolderMe) view.getTag();
            }
            if (UIHelper.isNightTheme()) {
                qiushiShareViewHolderMe.c.setImageDrawable(this.a.h.getResources().getDrawable(R.drawable.qiushi_to_im_icon_me_night));
            } else {
                qiushiShareViewHolderMe.c.setImageDrawable(this.a.h.getResources().getDrawable(R.drawable.qiushi_to_im_icon_me));
            }
            if (qiushiShareViewHolderMe.d != null) {
                qiushiShareViewHolderMe.d.setVisibility(8);
            }
            int i3 = chatMsg.status;
            if (chatMsg.type == 5) {
                z = true;
            } else {
                z = false;
            }
            qiushiShareViewHolderMe.setStatus(i3, z);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                qiushiShareViewHolderMe.b.setVisibility(8);
            } else {
                qiushiShareViewHolderMe.b.setText(chatMsg.getTimeStr());
                qiushiShareViewHolderMe.b.setVisibility(0);
            }
            try {
                jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject != null) {
                String optString;
                if (chatMsg.type == 24) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("ext");
                    if (optJSONObject != null) {
                        optString = optJSONObject.optString("topicid");
                    } else {
                        optString = "0";
                    }
                    Object optString2 = jSONObject.optString("previewImageURL");
                    qiushiShareViewHolderMe.e.setText(jSONObject.optString("title"));
                    if (!TextUtils.isEmpty(optString2)) {
                        ImageShower.a(qiushiShareViewHolderMe.c, optString2);
                    }
                    qiushiShareViewHolderMe.a.setOnClickListener(new as(this, optString));
                    qiushiShareViewHolderMe.a.setOnLongClickListener(new ad(chatMsg));
                } else {
                    optString = jSONObject.optString("artId");
                    Object optString3 = jSONObject.optString("cImgUrl");
                    CharSequence optString4 = jSONObject.optString("plainText");
                    String optString5 = jSONObject.optString("type");
                    qiushiShareViewHolderMe.e.setText(optString4);
                    if (!TextUtils.isEmpty(optString3)) {
                        ImageShower.a(qiushiShareViewHolderMe.c, optString3);
                    }
                    if (Integer.parseInt(optString5) == 3) {
                        qiushiShareViewHolderMe.d.setVisibility(0);
                    }
                    if (Integer.parseInt(optString5) == 4) {
                        qiushiShareViewHolderMe.g.setVisibility(0);
                    } else {
                        qiushiShareViewHolderMe.g.setVisibility(8);
                    }
                    qiushiShareViewHolderMe.a.setOnClickListener(new at(this, chatMsg, optString));
                    qiushiShareViewHolderMe.a.setOnLongClickListener(new ad(chatMsg));
                }
            }
            return view;
        }
    }

    private class an extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public an(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            QiushiShareViewHolderOther qiushiShareViewHolderOther;
            JSONObject jSONObject = null;
            if (view == null || !(view.getTag() instanceof QiushiShareViewHolderOther)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qiushi_share_msg_left, null);
                QiushiShareViewHolderOther qiushiShareViewHolderOther2 = new QiushiShareViewHolderOther(this.a);
                qiushiShareViewHolderOther2.b = (TextView) view.findViewById(R.id.sendDate);
                qiushiShareViewHolderOther2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                qiushiShareViewHolderOther2.c = (ImageView) view.findViewById(R.id.iv_userhead);
                qiushiShareViewHolderOther2.d = (ImageView) view.findViewById(R.id.coverImage);
                qiushiShareViewHolderOther2.e = (ImageView) view.findViewById(R.id.videoIndicator);
                qiushiShareViewHolderOther2.g = (ImageView) view.findViewById(R.id.gif_tag);
                qiushiShareViewHolderOther2.f = (TextView) view.findViewById(R.id.tvBrief);
                view.setTag(qiushiShareViewHolderOther2);
                qiushiShareViewHolderOther = qiushiShareViewHolderOther2;
            } else {
                qiushiShareViewHolderOther = (QiushiShareViewHolderOther) view.getTag();
            }
            FrescoImageloader.displayImage(qiushiShareViewHolderOther.d, FrescoImageloader.getFrescoResUrl(UIHelper.getShare2IMIcon()));
            if (qiushiShareViewHolderOther.e != null) {
                qiushiShareViewHolderOther.e.setVisibility(8);
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                qiushiShareViewHolderOther.b.setVisibility(8);
            } else {
                qiushiShareViewHolderOther.b.setText(chatMsg.getTimeStr());
                qiushiShareViewHolderOther.b.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, qiushiShareViewHolderOther.c);
            try {
                jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject != null) {
                if (chatMsg.type == 24) {
                    String optString;
                    JSONObject optJSONObject = jSONObject.optJSONObject("ext");
                    if (optJSONObject != null) {
                        optString = optJSONObject.optString("topicid");
                    } else {
                        optString = "0";
                    }
                    Object optString2 = jSONObject.optString("previewImageURL");
                    CharSequence optString3 = jSONObject.optString("title");
                    jSONObject.optString("type");
                    qiushiShareViewHolderOther.f.setText(optString3);
                    if (!TextUtils.isEmpty(optString2)) {
                        ImageShower.a(qiushiShareViewHolderOther.d, optString2);
                    }
                    qiushiShareViewHolderOther.a.setOnClickListener(new au(this, optString));
                    qiushiShareViewHolderOther.a.setOnLongClickListener(new ad(chatMsg));
                } else {
                    String optString4 = jSONObject.optString("artId");
                    Object optString5 = jSONObject.optString("cImgUrl");
                    CharSequence optString6 = jSONObject.optString("plainText");
                    int i3 = -1;
                    try {
                        i3 = Integer.parseInt(jSONObject.optString("type"));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    qiushiShareViewHolderOther.f.setText(optString6);
                    if (!TextUtils.isEmpty(optString5)) {
                        ImageShower.a(qiushiShareViewHolderOther.d, optString5);
                    }
                    if (i3 == 3) {
                        qiushiShareViewHolderOther.e.setVisibility(0);
                    }
                    if (i3 == 4) {
                        qiushiShareViewHolderOther.g.setVisibility(0);
                    } else {
                        qiushiShareViewHolderOther.g.setVisibility(8);
                    }
                    qiushiShareViewHolderOther.a.setOnClickListener(new av(this, chatMsg, optString4));
                    qiushiShareViewHolderOther.a.setOnLongClickListener(new ad(chatMsg));
                }
            }
            return view;
        }
    }

    private class ao implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private ao(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            aq aqVar;
            if (view == null || !(view.getTag() instanceof aj)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qiushi_topic_share_msg_right, null);
                aqVar = new aq(this.a, view);
                view.setTag(aqVar);
            } else {
                aqVar = (aq) view.getTag();
            }
            QiushiTopic qiushiTopicInfo = chatMsg.getQiushiTopicInfo();
            if (qiushiTopicInfo != null) {
                FrescoImageloader.displayImage(aqVar.c, qiushiTopicInfo.icon);
                aqVar.d.setText(qiushiTopicInfo.content);
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                aqVar.a.setVisibility(8);
            } else {
                aqVar.a.setText(chatMsg.getTimeStr());
                aqVar.a.setVisibility(0);
            }
            aqVar.b.setOnClickListener(new aw(this, qiushiTopicInfo));
            return view;
        }
    }

    private class ap implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private ap(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            ar arVar;
            if (view == null || !(view.getTag() instanceof QiushiShareViewHolderOther)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qiushi_topic_share_msg_left, null);
                arVar = new ar(this.a, view);
                view.setTag(arVar);
            } else {
                arVar = (ar) view.getTag();
            }
            QiushiTopic qiushiTopicInfo = chatMsg.getQiushiTopicInfo();
            if (qiushiTopicInfo != null) {
                FrescoImageloader.displayImage(arVar.d, qiushiTopicInfo.icon);
                arVar.e.setText(qiushiTopicInfo.content);
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                arVar.a.setVisibility(8);
            } else {
                arVar.a.setText(chatMsg.getTimeStr());
                arVar.a.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, arVar.b);
            arVar.c.setOnClickListener(new ax(this, qiushiTopicInfo));
            return view;
        }
    }

    private class aq {
        TextView a;
        RelativeLayout b;
        ImageView c;
        TextView d;
        final /* synthetic */ ChatListAdapter e;

        public aq(ChatListAdapter chatListAdapter, View view) {
            this.e = chatListAdapter;
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.c = (ImageView) view.findViewById(R.id.coverImage);
            this.d = (TextView) view.findViewById(R.id.tvBrief);
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.b = (RelativeLayout) view.findViewById(R.id.msgContainer);
        }
    }

    private class ar {
        TextView a;
        ImageView b;
        RelativeLayout c;
        ImageView d;
        TextView e;
        final /* synthetic */ ChatListAdapter f;

        public ar(ChatListAdapter chatListAdapter, View view) {
            this.f = chatListAdapter;
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.d = (ImageView) view.findViewById(R.id.coverImage);
            this.e = (TextView) view.findViewById(R.id.tvBrief);
            this.a = (TextView) view.findViewById(R.id.sendDate);
            this.c = (RelativeLayout) view.findViewById(R.id.msgContainer);
            this.b = (ImageView) view.findViewById(R.id.iv_userhead);
        }
    }

    private class as implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private as(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        private View a() {
            LogUtil.d("construce convert view");
            View inflate = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_sending, null);
            GifImageView gifImageView = (GifImageView) inflate.findViewById(R.id.sending_gif);
            if (UIHelper.isNightTheme()) {
                gifImageView.setImageResource(R.drawable.im_sending_dark);
                inflate.setTag(UIHelper$Theme.THEME_NIGHT);
            } else {
                gifImageView.setImageResource(R.drawable.im_sending);
                inflate.setTag("day");
            }
            return inflate;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                LogUtil.d("convertView is null");
                return a();
            }
            LogUtil.d("convertView is not null");
            String str = (String) view.getTag();
            LogUtil.d("tag:" + str);
            if (str.equalsIgnoreCase(UIHelper$Theme.THEME_NIGHT) && UIHelper.isNightTheme()) {
                return view;
            }
            if (!str.equalsIgnoreCase("day") || UIHelper.isNightTheme()) {
                return a();
            }
            return view;
        }
    }

    private class at extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        private at(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            SpringFestivalHolder springFestivalHolder;
            JSONObject jSONObject;
            String str;
            String str2;
            String str3;
            Object obj;
            CharSequence charSequence;
            Object obj2;
            CharSequence optString;
            Object optString2;
            String optString3;
            CharSequence charSequence2;
            Object obj3;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_spring_festival_item, null);
                SpringFestivalHolder springFestivalHolder2 = new SpringFestivalHolder(this.a, view);
                view.setTag(springFestivalHolder2);
                springFestivalHolder = springFestivalHolder2;
            } else {
                springFestivalHolder = (SpringFestivalHolder) view.getTag();
            }
            if (!(chatMsg == null || TextUtils.isEmpty(chatMsg.data))) {
                try {
                    jSONObject = new JSONObject(chatMsg.data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                str = "";
                str2 = "";
                str3 = "";
                obj = "";
                charSequence = "";
                obj2 = "";
                if (jSONObject == null && jSONObject.has("t")) {
                    CharSequence optString4 = jSONObject.optString("t");
                    if (TextUtils.equals(optString4, SpringFestivalUtil.HAMMER_QSJX)) {
                        springFestivalHolder.e.setMaxLines(Integer.MAX_VALUE);
                    } else if (TextUtils.equals(optString4, SpringFestivalUtil.HAMMER)) {
                        obj = jSONObject.optString("icon_url");
                        charSequence = jSONObject.optString("btn_cnt");
                        obj2 = jSONObject.optString("btn_url");
                        springFestivalHolder.e.setMaxLines(3);
                    }
                    optString = jSONObject.optString("title");
                    optString2 = jSONObject.optString("subTitle");
                    CharSequence charSequence3 = optString4;
                    optString3 = jSONObject.optString("content");
                    charSequence2 = charSequence3;
                } else {
                    charSequence2 = null;
                    optString3 = str3;
                    str3 = str2;
                    obj3 = str;
                }
                this.a.a(chatMsg.from, chatMsg.fromicon, chatMsg.fromnick, springFestivalHolder.g);
                if (chatMsg.showTime || chatMsg.time == 0) {
                    springFestivalHolder.b.setVisibility(8);
                } else {
                    springFestivalHolder.b.setText(chatMsg.getTimeStr());
                    springFestivalHolder.b.setVisibility(0);
                }
                springFestivalHolder.c.setImageDrawable(null);
                if (TextUtils.equals(charSequence2, SpringFestivalUtil.HAMMER_QSJX)) {
                    springFestivalHolder.e.setText(optString);
                } else {
                    springFestivalHolder.e.setText((TextUtils.isEmpty(optString) ? "" : optString + "，") + (TextUtils.isEmpty(optString2) ? "" : optString2 + "，") + optString3);
                }
                springFestivalHolder.d.setVisibility(TextUtils.isEmpty(obj) ? 8 : 0);
                if (!TextUtils.isEmpty(obj)) {
                    ImageShower.a(springFestivalHolder.c, obj);
                }
                springFestivalHolder.h.setVisibility(TextUtils.isEmpty(obj2) ? 8 : 0);
                springFestivalHolder.f.setText(charSequence);
                springFestivalHolder.f.setOnClickListener(new ay(this, obj2));
                return view;
            }
            jSONObject = null;
            str = "";
            str2 = "";
            str3 = "";
            obj = "";
            charSequence = "";
            obj2 = "";
            if (jSONObject == null) {
            }
            charSequence2 = null;
            optString3 = str3;
            str3 = str2;
            obj3 = str;
            this.a.a(chatMsg.from, chatMsg.fromicon, chatMsg.fromnick, springFestivalHolder.g);
            if (chatMsg.showTime) {
            }
            springFestivalHolder.b.setVisibility(8);
            springFestivalHolder.c.setImageDrawable(null);
            if (TextUtils.equals(charSequence2, SpringFestivalUtil.HAMMER_QSJX)) {
                springFestivalHolder.e.setText(optString);
            } else {
                if (TextUtils.isEmpty(optString)) {
                }
                if (TextUtils.isEmpty(optString2)) {
                }
                springFestivalHolder.e.setText((TextUtils.isEmpty(optString) ? "" : optString + "，") + (TextUtils.isEmpty(optString2) ? "" : optString2 + "，") + optString3);
            }
            if (TextUtils.isEmpty(obj)) {
            }
            springFestivalHolder.d.setVisibility(TextUtils.isEmpty(obj) ? 8 : 0);
            if (TextUtils.isEmpty(obj)) {
                ImageShower.a(springFestivalHolder.c, obj);
            }
            if (TextUtils.isEmpty(obj2)) {
            }
            springFestivalHolder.h.setVisibility(TextUtils.isEmpty(obj2) ? 8 : 0);
            springFestivalHolder.f.setText(charSequence);
            springFestivalHolder.f.setOnClickListener(new ay(this, obj2));
            return view;
        }
    }

    private class au implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private au(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_system_msg, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.sendDate);
            if (textView != null) {
                if (!chatMsg.showTime || chatMsg.time == 0) {
                    textView.setVisibility(8);
                } else {
                    textView.setText(chatMsg.getTimeStr());
                    textView.setVisibility(0);
                }
            }
            textView = (TextView) view.findViewById(R.id.sys_msg);
            Object remark = RemarkManager.getRemark(chatMsg.from);
            if (TextUtils.isEmpty(remark)) {
                textView.setText(chatMsg.data);
            } else {
                textView.setText(RemarkManager.replaceFirst(chatMsg.data, chatMsg.getFromNick(), remark));
            }
            return view;
        }
    }

    private static class av {
        f a;
        ChatMsg b;
        ChatMsgImageData c;
        int d;
        int e;

        public av(f fVar, ChatMsg chatMsg, int i, int i2) {
            this.a = fVar;
            this.b = chatMsg;
            this.c = new ChatMsgImageData(chatMsg.data);
            this.d = i;
            this.e = i2;
        }
    }

    private static class aw implements UploadImageCallback, Recyclable {
        static final Map<String, Integer> a = Collections.synchronizedMap(new HashMap());
        static final List<String> b = Collections.synchronizedList(new ArrayList());
        static final Map<Long, UploadTask> c = Collections.synchronizedMap(new HashMap());
        private UploadedListener d;

        aw(UploadedListener uploadedListener) {
            this.d = uploadedListener;
        }

        static boolean a(String str) {
            return b.contains(str);
        }

        static boolean b(String str) {
            if (a(str)) {
                return false;
            }
            return b.add(str);
        }

        static boolean c(String str) {
            return b.remove(str);
        }

        static void a(long j) {
            c.remove(Long.valueOf(j));
        }

        static void a(long j, UploadTask uploadTask) {
            c.put(Long.valueOf(j), uploadTask);
        }

        static void b(long j) {
            UploadTask uploadTask = (UploadTask) c.get(Long.valueOf(j));
            if (uploadTask != null) {
                uploadTask.cancel(true);
                a(j);
            }
        }

        boolean a(Object obj) {
            return obj != null && (obj instanceof av);
        }

        public void onSuccess(Uri uri, String str, Object obj) {
            if (a(obj)) {
                av avVar = (av) obj;
                if (uri.toString().equals(avVar.c.url)) {
                    File diskCacheFile = FrescoImageloader.getDiskCacheFile(uri);
                    if (diskCacheFile == null) {
                        diskCacheFile = new File(uri.toString().replace("file://", ""));
                    }
                    IMImageSize imageSize = IMImageSizeHelper.getImageSize(Size.Medium, avVar.d, avVar.e, QsbkApp.mContext);
                    File diskCacheFileWithReflection = FrescoImageloader.getDiskCacheFileWithReflection(ChatListAdapter.appendSmallSize(str, imageSize.getDstWidth(), imageSize.getDstHeight()));
                    if (diskCacheFile != null && diskCacheFile.exists()) {
                        FileUtils.copyFileAsync(diskCacheFile, diskCacheFileWithReflection, null);
                    }
                    avVar.c.status = 1;
                    avVar.c.url = str;
                    avVar.c.progress = 100;
                    avVar.b.data = avVar.c.encodeToJsonObject().toString();
                    if (this.d != null) {
                        this.d.uploaded(avVar.b);
                    }
                    c(avVar.b.msgid);
                    a(avVar.b.dbid);
                }
            }
        }

        public void onFaiure(Uri uri, String str, Object obj) {
            if (a(obj)) {
                av avVar = (av) obj;
                c(avVar.b.msgid);
                Integer num = (Integer) a.get(uri.toString());
                a(avVar, num == null ? 0 : num.intValue());
            }
            Log.e(ChatListAdapter.f, "upload failed " + str);
        }

        public void onProgress(Uri uri, long j, long j2, Object obj) {
            if (a(obj)) {
                av avVar = (av) obj;
                String uri2 = uri.toString();
                if (uri2.equals(avVar.c.url)) {
                    int i = (int) ((100 * j) / j2);
                    a.put(uri2, Integer.valueOf(i));
                    a(avVar, i);
                }
            }
            LogUtil.d("upload progress:" + j + " total:" + j2);
        }

        public void onStart(Uri uri, Object obj) {
            if (a(obj)) {
                av avVar = (av) obj;
                if (avVar.c.status == 1 || ChatListAdapter.isNetworkUrl(avVar.c.url)) {
                    avVar.a.i.setMinProgress(0);
                    return;
                }
                Integer num = (Integer) a.get(uri.toString());
                if (avVar.a != null) {
                    int i;
                    if (num == null) {
                        i = 0;
                    } else {
                        i = num.intValue();
                    }
                    avVar.a.i.setMinProgress(Math.max(Math.max(i, avVar.c.progress), 98));
                }
                b(avVar.b.msgid);
                a(avVar, 0);
            }
        }

        void a(av avVar, int i) {
            if (avVar != null) {
                avVar.c.progress = i;
                avVar.c.status = 4;
                avVar.b.data = avVar.c.encodeToJsonObject().toString();
                if (avVar.a != null) {
                    avVar.a.h.setVisibility(0);
                    if (i == 0) {
                        avVar.a.j.setVisibility(0);
                        avVar.a.i.setVisibility(8);
                        avVar.a.j.startBreath(10);
                        avVar.a.j.setText("0%");
                        return;
                    }
                    avVar.a.j.stopBreath();
                    avVar.a.j.setVisibility(8);
                    avVar.a.i.setVisibility(0);
                    avVar.a.i.setProgress(i);
                }
            }
        }

        public void recycle() {
            b.clear();
            a.clear();
        }
    }

    private class ax {
        final /* synthetic */ ChatListAdapter a;
        private MediaPlayer b = new MediaPlayer();
        private ay c = null;
        private ChatMsg d = null;
        private AdapterView<?> e;
        private BaseChatMsgStore f;

        public ax(ChatListAdapter chatListAdapter, AdapterView<?> adapterView) {
            this.a = chatListAdapter;
            this.e = adapterView;
            this.f = ChatMsgStoreProxy.newInstance(QsbkApp.currentUser.userId, chatListAdapter.p ? 3 : 0);
        }

        private boolean a() {
            return this.b.isPlaying();
        }

        private boolean a(ay ayVar) {
            if (this.c == null) {
                if (this.d == ayVar.k) {
                    return true;
                }
                return false;
            } else if (ayVar.k != this.c.k) {
                return false;
            } else {
                return true;
            }
        }

        private ay a(ChatMsg chatMsg) {
            int childCount = this.e.getChildCount();
            for (int i = 0; i < childCount; i++) {
                Object tag = this.e.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ay) && chatMsg == ((ay) tag).k) {
                    return (ay) tag;
                }
            }
            return null;
        }

        private ChatMsg b(ChatMsg chatMsg) {
            List list = this.a.e;
            for (int indexOf = list.indexOf(chatMsg) + 1; indexOf < list.size(); indexOf++) {
                ChatMsg chatMsg2 = (ChatMsg) list.get(indexOf);
                if (chatMsg2.type == 4 && chatMsg2.inout == 1 && !ChatListAdapter.b(chatMsg2).played) {
                    return chatMsg2;
                }
            }
            return null;
        }

        private void c(ChatMsg chatMsg) {
            this.d = chatMsg;
            ChatMsgVoiceData a = ChatListAdapter.b(chatMsg);
            if (TextUtils.isEmpty(a.path)) {
                CharSequence pathIfDownload = this.a.q.getPathIfDownload(a.url);
                a.path = pathIfDownload;
                if (TextUtils.isEmpty(pathIfDownload)) {
                    VoiceShowerOther voiceShowerOther = (VoiceShowerOther) this.a.y.get(18);
                    if (!voiceShowerOther.a(chatMsg.msgid)) {
                        voiceShowerOther.b(chatMsg.msgid);
                        this.a.q.download(a.url, voiceShowerOther, chatMsg);
                        return;
                    }
                    return;
                }
            }
            if (!a.played) {
                a.played = true;
                chatMsg.data = a.encodeToJsonObject().toString();
                this.f.updateMessageData(chatMsg);
            }
            try {
                this.b.setOnCompletionListener(new ba(this));
                this.b.setAudioStreamType(3);
                this.b.setDataSource(a.path);
                this.b.prepare();
                this.b.start();
            } catch (Exception e) {
                e.printStackTrace();
                b();
            }
        }

        private void b(ay ayVar) {
            Drawable drawable;
            if (!(ayVar == null || ayVar.m == null)) {
                ayVar.m.setVisibility(8);
            }
            if (this.b.isPlaying()) {
                if (ayVar != this.c) {
                    b();
                } else {
                    return;
                }
            }
            this.c = ayVar;
            ChatMsgVoiceData a = ChatListAdapter.b(ayVar.k);
            if (TextUtils.isEmpty(a.path)) {
                CharSequence pathIfDownload = this.a.q.getPathIfDownload(a.url);
                a.path = pathIfDownload;
                if (TextUtils.isEmpty(pathIfDownload)) {
                    ChatMsg chatMsg = ayVar.k;
                    VoiceShowerOther voiceShowerOther = (VoiceShowerOther) this.a.y.get(18);
                    if (!voiceShowerOther.a(chatMsg.msgid)) {
                        voiceShowerOther.b(chatMsg.msgid);
                        this.a.q.download(a.url, voiceShowerOther, ayVar);
                        return;
                    }
                    return;
                }
            }
            Resources resources = ayVar.j.getResources();
            if (ayVar.l) {
                drawable = (AnimationDrawable) resources.getDrawable(R.drawable.im_voice_play_me);
            } else {
                AnimationDrawable animationDrawable = (AnimationDrawable) resources.getDrawable(R.drawable.im_voice_play_other);
            }
            ayVar.j.setImageDrawable(drawable);
            drawable.start();
            if (a.played) {
                c(ayVar.k);
            } else {
                c(ayVar.k);
            }
        }

        private void b() {
            if (this.c != null) {
                this.c.d.setText(ChatMsgVoiceData.formatDuration(ChatListAdapter.b(this.c.k).duration));
                this.c.j.setImageResource(this.c.l ? R.drawable.play_white_0 : R.drawable.play_blue_0);
            }
            this.c = null;
            this.d = null;
            try {
                this.b.stop();
                this.b.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ay {
        RelativeLayout a;
        RangedProgressTextView b;
        BreathTextView c;
        TextView d;
        ImageView e;
        TextView f;
        TextView g;
        View h;
        ImageView i;
        ImageButton j;
        ChatMsg k;
        boolean l;
        View m;

        ay() {
        }

        static ay a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof ay)) {
                return (ay) tag;
            }
            ay ayVar = new ay();
            ayVar.i = (ImageView) view.findViewById(R.id.iv_userhead);
            ayVar.d = (TextView) view.findViewById(R.id.voice);
            ayVar.a = (RelativeLayout) view.findViewById(R.id.loadingLayout);
            ayVar.f = (TextView) view.findViewById(R.id.id_msg_source);
            ayVar.b = (RangedProgressTextView) view.findViewById(R.id.progressText);
            ayVar.b.setMaxProgress(98);
            ayVar.c = (BreathTextView) view.findViewById(R.id.breathTextView);
            ayVar.g = (TextView) view.findViewById(R.id.sendDate);
            ayVar.e = (ImageView) view.findViewById(R.id.id_status_view);
            ayVar.h = view.findViewById(R.id.imageContent);
            ayVar.j = (ImageButton) view.findViewById(R.id.playVoice);
            ayVar.m = view.findViewById(R.id.voiceReadState);
            view.setTag(ayVar);
            return ayVar;
        }

        void a(int i, int i2) {
            if (this.e != null) {
                if (i2 != 17) {
                    this.e.setVisibility(8);
                } else if (!this.k.isGroupMsg() || i == 1 || i == 3) {
                    this.e.setVisibility(0);
                    int sendStatusRes = UIHelper.getSendStatusRes(i);
                    if (sendStatusRes <= 0) {
                        this.e.setVisibility(8);
                    } else {
                        this.e.setImageResource(sendStatusRes);
                    }
                } else {
                    this.e.setVisibility(8);
                }
            }
        }
    }

    private class az extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public az(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        @SuppressLint({"NewApi"})
        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            QiushiShareViewHolderMe qiushiShareViewHolderMe;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qiushi_share_msg_right, null);
                QiushiShareViewHolderMe qiushiShareViewHolderMe2 = new QiushiShareViewHolderMe(this.a);
                qiushiShareViewHolderMe2.b = (TextView) view.findViewById(R.id.sendDate);
                qiushiShareViewHolderMe2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                qiushiShareViewHolderMe2.c = (ImageView) view.findViewById(R.id.coverImage);
                qiushiShareViewHolderMe2.d = (ImageView) view.findViewById(R.id.videoIndicator);
                qiushiShareViewHolderMe2.e = (TextView) view.findViewById(R.id.tvBrief);
                qiushiShareViewHolderMe2.f = (ImageView) view.findViewById(R.id.iv_status_view);
                view.setTag(qiushiShareViewHolderMe2);
                qiushiShareViewHolderMe = qiushiShareViewHolderMe2;
            } else {
                qiushiShareViewHolderMe = (QiushiShareViewHolderMe) view.getTag();
            }
            if (UIHelper.isNightTheme()) {
                qiushiShareViewHolderMe.c.setImageDrawable(this.a.h.getResources().getDrawable(R.drawable.qiushi_to_im_icon_me_night));
            } else {
                qiushiShareViewHolderMe.c.setImageDrawable(this.a.h.getResources().getDrawable(R.drawable.qiushi_to_im_icon_me));
            }
            qiushiShareViewHolderMe.setStatus(chatMsg.status, false);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                qiushiShareViewHolderMe.b.setVisibility(8);
            } else {
                qiushiShareViewHolderMe.b.setText(chatMsg.getTimeStr());
                qiushiShareViewHolderMe.b.setVisibility(0);
            }
            try {
                JSONObject jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (chatMsg.type == 23 || chatMsg.type == 29) {
                ShareMsgItem shareMsgItem = chatMsg.getShareMsgItem();
                if (shareMsgItem != null) {
                    CharSequence charSequence = shareMsgItem.title;
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = shareMsgItem.content;
                    }
                    qiushiShareViewHolderMe.e.setText(charSequence);
                    if (!TextUtils.isEmpty(shareMsgItem.imageUrl)) {
                        ImageShower.a(qiushiShareViewHolderMe.c, shareMsgItem.imageUrl);
                    }
                    qiushiShareViewHolderMe.a.setOnClickListener(new bd(this, chatMsg, shareMsgItem));
                    qiushiShareViewHolderMe.a.setOnLongClickListener(new ad(chatMsg));
                }
            } else {
                try {
                    jSONObject = new JSONObject(chatMsg.data);
                    Object optString = jSONObject.optString("image_url");
                    if (!TextUtils.isEmpty(optString)) {
                        ImageShower.a(qiushiShareViewHolderMe.c, optString);
                    }
                    qiushiShareViewHolderMe.e.setText(jSONObject.optString("title"));
                    qiushiShareViewHolderMe.a.setOnClickListener(new be(this, jSONObject.optString("news_id")));
                    qiushiShareViewHolderMe.a.setOnLongClickListener(new ad(chatMsg));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            return view;
        }
    }

    private class b implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private b(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_system_msg, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.sendDate);
            if (textView != null) {
                if (!chatMsg.showTime || chatMsg.time == 0) {
                    textView.setVisibility(8);
                } else {
                    textView.setText(chatMsg.getTimeStr());
                    textView.setVisibility(0);
                }
            }
            textView = (TextView) view.findViewById(R.id.sys_msg);
            Object remark = RemarkManager.getRemark(chatMsg.from);
            if (TextUtils.isEmpty(remark)) {
                textView.setText(chatMsg.getGroupSystemMsg().content);
            } else {
                textView.setText(RemarkManager.replaceFirst(chatMsg.getGroupSystemMsg().content, chatMsg.getFromNick(), remark));
            }
            textView.setTextColor(UIHelper.isNightTheme() ? -12171438 : -1);
            return view;
        }
    }

    private class ba extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public ba(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            QiushiShareViewHolderOther qiushiShareViewHolderOther;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_qiushi_share_msg_left, null);
                QiushiShareViewHolderOther qiushiShareViewHolderOther2 = new QiushiShareViewHolderOther(this.a);
                qiushiShareViewHolderOther2.b = (TextView) view.findViewById(R.id.sendDate);
                qiushiShareViewHolderOther2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                qiushiShareViewHolderOther2.c = (ImageView) view.findViewById(R.id.iv_userhead);
                qiushiShareViewHolderOther2.d = (ImageView) view.findViewById(R.id.coverImage);
                qiushiShareViewHolderOther2.e = (ImageView) view.findViewById(R.id.videoIndicator);
                qiushiShareViewHolderOther2.f = (TextView) view.findViewById(R.id.tvBrief);
                view.setTag(qiushiShareViewHolderOther2);
                qiushiShareViewHolderOther = qiushiShareViewHolderOther2;
            } else {
                qiushiShareViewHolderOther = (QiushiShareViewHolderOther) view.getTag();
            }
            ImageShower.a(qiushiShareViewHolderOther.d, FrescoImageloader.getFrescoResUrl(UIHelper.getShare2IMIcon()));
            if (!chatMsg.showTime || chatMsg.time == 0) {
                qiushiShareViewHolderOther.b.setVisibility(8);
            } else {
                qiushiShareViewHolderOther.b.setText(chatMsg.getTimeStr());
                qiushiShareViewHolderOther.b.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, qiushiShareViewHolderOther.c);
            try {
                JSONObject jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (chatMsg.type == 23 || chatMsg.type == 29) {
                ShareMsgItem shareMsgItem = chatMsg.getShareMsgItem();
                if (shareMsgItem != null) {
                    CharSequence charSequence = shareMsgItem.title;
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = shareMsgItem.content;
                    }
                    qiushiShareViewHolderOther.f.setText(charSequence);
                    ImageShower.a(qiushiShareViewHolderOther.d, shareMsgItem.imageUrl);
                    qiushiShareViewHolderOther.a.setOnClickListener(new bf(this, chatMsg, shareMsgItem));
                    qiushiShareViewHolderOther.a.setOnLongClickListener(new ad(chatMsg));
                }
            } else {
                try {
                    jSONObject = new JSONObject(chatMsg.data);
                    Object optString = jSONObject.optString("image_url");
                    if (!TextUtils.isEmpty(optString)) {
                        ImageShower.a(qiushiShareViewHolderOther.d, optString);
                    }
                    qiushiShareViewHolderOther.f.setText(jSONObject.optString("title"));
                    qiushiShareViewHolderOther.a.setOnClickListener(new bg(this, jSONObject.optString("news_id")));
                    qiushiShareViewHolderOther.a.setOnLongClickListener(new ad(chatMsg));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            return view;
        }
    }

    private class c extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public c(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            LiveBeginViewHolder liveBeginViewHolder;
            if (view == null || !(view.getTag() instanceof LiveBeginViewHolder)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_live_begin, null);
                LiveBeginViewHolder liveBeginViewHolder2 = new LiveBeginViewHolder(this.a);
                liveBeginViewHolder2.b = (TextView) view.findViewById(R.id.sendDate);
                liveBeginViewHolder2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                liveBeginViewHolder2.c = (ImageView) view.findViewById(R.id.iv_userhead);
                liveBeginViewHolder2.d = (ImageView) view.findViewById(R.id.coverImage);
                liveBeginViewHolder2.e = (ImageView) view.findViewById(R.id.videoIndicator);
                liveBeginViewHolder2.f = (TextView) view.findViewById(R.id.tvBrief);
                view.setTag(liveBeginViewHolder2);
                liveBeginViewHolder = liveBeginViewHolder2;
            } else {
                liveBeginViewHolder = (LiveBeginViewHolder) view.getTag();
            }
            ImageShower.a(liveBeginViewHolder.d, FrescoImageloader.getFrescoResUrl(UIHelper.getShare2IMIcon()));
            if (!chatMsg.showTime || chatMsg.time == 0) {
                liveBeginViewHolder.b.setVisibility(8);
            } else {
                liveBeginViewHolder.b.setText(chatMsg.getTimeStr());
                liveBeginViewHolder.b.setVisibility(0);
            }
            try {
                JSONObject jSONObject = new JSONObject(chatMsg.data);
                CharSequence optString = jSONObject.optString("content");
                String optString2 = jSONObject.optString("live_id");
                long optLong = jSONObject.optLong("origin");
                String optString3 = jSONObject.optString("pic_url");
                jSONObject.optString("title");
                String optString4 = jSONObject.optString("host_icon");
                String optString5 = jSONObject.optString("host_id");
                if (TextUtils.isEmpty(optString)) {
                    liveBeginViewHolder.f.setVisibility(0);
                    liveBeginViewHolder.f.setText("我开始新的直播啦，快来围观~");
                } else {
                    liveBeginViewHolder.f.setVisibility(0);
                    liveBeginViewHolder.f.setText(optString);
                }
                if (TextUtils.isEmpty(optString4) || TextUtils.isEmpty(optString5)) {
                    this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, liveBeginViewHolder.c);
                } else {
                    this.a.a(optString5, optString4, chatMsg.fromnick, liveBeginViewHolder.c);
                    liveBeginViewHolder.c.setOnClickListener(new q(this, optString5));
                }
                ImageShower.a(liveBeginViewHolder.d, optString3);
                view.setOnClickListener(new r(this, optString2, optLong));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return view;
        }
    }

    private class d extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public d(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            LiveBeginViewHolderShareMe liveBeginViewHolderShareMe;
            boolean z;
            if (view == null || !(view.getTag() instanceof LiveBeginViewHolderShareMe)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_live_begin_right, null);
                LiveBeginViewHolderShareMe liveBeginViewHolderShareMe2 = new LiveBeginViewHolderShareMe(this.a);
                liveBeginViewHolderShareMe2.b = (TextView) view.findViewById(R.id.sendDate);
                liveBeginViewHolderShareMe2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                liveBeginViewHolderShareMe2.c = (ImageView) view.findViewById(R.id.coverImage);
                liveBeginViewHolderShareMe2.d = (ImageView) view.findViewById(R.id.videoIndicator);
                liveBeginViewHolderShareMe2.e = (TextView) view.findViewById(R.id.tvBrief);
                view.setTag(liveBeginViewHolderShareMe2);
                liveBeginViewHolderShareMe = liveBeginViewHolderShareMe2;
            } else {
                liveBeginViewHolderShareMe = (LiveBeginViewHolderShareMe) view.getTag();
            }
            ImageShower.a(liveBeginViewHolderShareMe.c, FrescoImageloader.getFrescoResUrl(UIHelper.getShare2IMIcon()));
            int i3 = chatMsg.status;
            if (chatMsg.type == 5) {
                z = true;
            } else {
                z = false;
            }
            liveBeginViewHolderShareMe.setStatus(i3, z);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                liveBeginViewHolderShareMe.b.setVisibility(8);
            } else {
                liveBeginViewHolderShareMe.b.setText(chatMsg.getTimeStr());
                liveBeginViewHolderShareMe.b.setVisibility(0);
            }
            try {
                JSONObject jSONObject = new JSONObject(chatMsg.data);
                CharSequence optString = jSONObject.optString("content");
                String optString2 = jSONObject.optString("live_id");
                long optLong = jSONObject.optLong("origin");
                String optString3 = jSONObject.optString("pic_url");
                jSONObject.optString("title");
                if (TextUtils.isEmpty(optString)) {
                    liveBeginViewHolderShareMe.e.setVisibility(0);
                    liveBeginViewHolderShareMe.e.setText("我开始新的直播啦，快来围观~");
                } else {
                    liveBeginViewHolderShareMe.e.setVisibility(0);
                    liveBeginViewHolderShareMe.e.setText(optString);
                }
                view.setOnClickListener(new s(this, optString2, optLong));
                ImageShower.a(liveBeginViewHolderShareMe.c, optString3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return view;
        }
    }

    private static class f extends e {
        RelativeLayout h;
        RangedProgressTextView i;
        BreathTextView j;

        f() {
            super();
        }

        static f a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof f)) {
                return (f) tag;
            }
            f fVar = new f();
            fVar.a = (ImageView) view.findViewById(R.id.image);
            fVar.h = (RelativeLayout) view.findViewById(R.id.loadingLayout);
            fVar.c = (TextView) view.findViewById(R.id.id_msg_source);
            fVar.i = (RangedProgressTextView) view.findViewById(R.id.progressText);
            fVar.i.setMaxProgress(98);
            fVar.j = (BreathTextView) view.findViewById(R.id.breathTextView);
            fVar.d = (TextView) view.findViewById(R.id.sendDate);
            fVar.b = (ImageView) view.findViewById(R.id.id_status_view);
            fVar.e = view.findViewById(R.id.imageContent);
            view.setTag(fVar);
            return fVar;
        }
    }

    private static class g extends e {
        ImageView h;
        ProgressBar i;

        g() {
            super();
        }

        static g a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof g)) {
                return (g) tag;
            }
            g gVar = new g();
            gVar.a = (ImageView) view.findViewById(R.id.image);
            gVar.c = (TextView) view.findViewById(R.id.id_msg_source);
            gVar.d = (TextView) view.findViewById(R.id.sendDate);
            gVar.b = (ImageView) view.findViewById(R.id.id_status_view);
            gVar.h = (ImageView) view.findViewById(R.id.iv_userhead);
            gVar.e = view.findViewById(R.id.imageContent);
            gVar.f = (TextView) view.findViewById(R.id.tv_username);
            gVar.g = (TextView) view.findViewById(R.id.role);
            gVar.i = (ProgressBar) view.findViewById(R.id.progressBar);
            view.setTag(gVar);
            return gVar;
        }
    }

    private static class h {
        TextView a;
        TextView b;
        TextView c;
        ImageView d;
        ImageView e;
        ImageView f;
        TextView g;
        TextView h;
        View i;
        TextView j;
        View k;
        GroupInfo l;
        int m;
        ChatMsg n;

        private h() {
        }

        static h a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof g)) {
                return (h) tag;
            }
            h hVar = new h();
            hVar.a = (TextView) view.findViewById(R.id.title);
            hVar.b = (TextView) view.findViewById(R.id.content);
            hVar.e = (ImageView) view.findViewById(R.id.image);
            hVar.c = (TextView) view.findViewById(R.id.join);
            hVar.k = view.findViewById(R.id.invite_view);
            hVar.c.setOnClickListener(new t(hVar, view));
            hVar.g = (TextView) view.findViewById(R.id.id_msg_source);
            hVar.h = (TextView) view.findViewById(R.id.sendDate);
            hVar.f = (ImageView) view.findViewById(R.id.id_status_view);
            hVar.d = (ImageView) view.findViewById(R.id.iv_userhead);
            hVar.i = view.findViewById(R.id.imageContent);
            hVar.j = (TextView) view.findViewById(R.id.tv_username);
            view.setTag(hVar);
            return hVar;
        }

        boolean a() {
            return this.m == this.l.ownerId;
        }

        private void a(Context context) {
            if (a()) {
                GroupActionUtil.applyForGroup("", this.l.id, this.m, new u(this, context, "申请中...").setCancelable(true));
            } else {
                ApplyForGroupActivity.launchForResult(context, this.l, this.m);
            }
        }
    }

    private static class i {
        LinearLayout a;
        ImageView b;
        TextView c;
        TextView d;
        View e;
        ImageView f;
        TextView g;
        ImageView h;
        TextView i;
        LinearLayout j;
        TextView k;
        BaseUserInfo l;
        String m;
        String n;
        View o;
        ImageView p;

        private i() {
        }

        static i a(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof i)) {
                return (i) tag;
            }
            i iVar = new i();
            view.setOnClickListener(new w(iVar));
            iVar.a = (LinearLayout) view.findViewById(R.id.im_other_joined_group_success_lin);
            iVar.c = (TextView) view.findViewById(R.id.sendDate);
            iVar.d = (TextView) view.findViewById(R.id.joined_group_intro);
            iVar.e = view.findViewById(R.id.joined_group_intro_view);
            iVar.f = (ImageView) view.findViewById(R.id.joined_group_userhead);
            iVar.h = (ImageView) view.findViewById(R.id.gender);
            iVar.i = (TextView) view.findViewById(R.id.age);
            iVar.g = (TextView) view.findViewById(R.id.joined_group_username);
            iVar.j = (LinearLayout) view.findViewById(R.id.gender_age);
            iVar.k = (TextView) view.findViewById(R.id.joined_group_location);
            iVar.o = view.findViewById(R.id.location_container);
            iVar.p = (ImageView) view.findViewById(R.id.joined_group_show);
            iVar.b = (ImageView) view.findViewById(R.id.location_imgview);
            view.setTag(iVar);
            return iVar;
        }
    }

    class j {
        View a;
        TextView b;
        final /* synthetic */ ChatListAdapter c;
        private ImageView d;
        public TextView sendTime;

        public j(ChatListAdapter chatListAdapter, View view) {
            this.c = chatListAdapter;
            this.a = view.findViewById(R.id.laisee_container);
            this.b = (TextView) view.findViewById(R.id.content);
            this.d = (ImageView) view.findViewById(R.id.iv_userhead);
            this.sendTime = (TextView) view.findViewById(R.id.sendDate);
        }

        public void setMsg(ChatMsg chatMsg) {
            LaiseeImInfo laiseeInfo = chatMsg.getLaiseeInfo();
            this.a.setOnClickListener(new x(this, laiseeInfo));
            this.b.setText(laiseeInfo == null ? "" : laiseeInfo.content);
            this.c.a(chatMsg.from, this.c.c(chatMsg), chatMsg.fromnick, this.d);
        }
    }

    class k {
        TextView a;
        View b;
        final /* synthetic */ ChatListAdapter c;

        public k(ChatListAdapter chatListAdapter, View view) {
            this.c = chatListAdapter;
            this.b = view;
            this.a = (TextView) view.findViewById(R.id.laisee_log);
        }

        public void setMsg(ChatMsg chatMsg) {
            LaiseeImLog laiseeLog = chatMsg.getLaiseeLog();
            if (laiseeLog == null || !laiseeLog.isAboutUser(QsbkApp.currentUser.userId)) {
                this.b.setVisibility(8);
                return;
            }
            CharSequence spannableStringBuilder = new SpannableStringBuilder(laiseeLog.getDesc());
            spannableStringBuilder.setSpan(new y(this, laiseeLog), spannableStringBuilder.length() - 2, spannableStringBuilder.length(), 33);
            this.a.setText(spannableStringBuilder);
            this.a.setMovementMethod(LinkMovementMethod.getInstance());
            this.b.setVisibility(0);
        }
    }

    private class l implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private l(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_laisee_log, null);
                view.setTag(new k(this.a, view));
            }
            ((k) view.getTag()).setMsg(chatMsg);
            return view;
        }
    }

    class m {
        View a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        ImageView f;
        final /* synthetic */ ChatListAdapter g;

        public m(ChatListAdapter chatListAdapter, View view) {
            this.g = chatListAdapter;
            this.a = view;
            this.b = (TextView) view.findViewById(R.id.tvBrief);
            this.c = (ImageView) view.findViewById(R.id.coverImage);
            this.d = (TextView) view.findViewById(R.id.btn_left);
            this.e = (TextView) view.findViewById(R.id.btn_right);
            this.f = (ImageView) view.findViewById(R.id.iv_userhead);
        }
    }

    private class n implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private n(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_laisee_not_get, null);
                view.setTag(new m(this.a, view));
            }
            m mVar = (m) view.getTag();
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, mVar.f);
            try {
                String optString;
                JSONObject jSONObject = new JSONObject(EncryptDecryptUtil.processDecrypt(new JSONObject(chatMsg.data).optString("data")));
                CharSequence optString2 = jSONObject.optString("title");
                JSONObject optJSONObject = jSONObject.optJSONObject("left");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("right");
                Object optString3 = jSONObject.optString("icon");
                mVar.b.setText(optString2);
                if (!TextUtils.isEmpty(optString3)) {
                    FrescoImageloader.displayImage(mVar.c, optString3);
                }
                if (optJSONObject != null) {
                    optString2 = optJSONObject.optString("cnt", "参与活动讨论");
                    optString = optJSONObject.optString("circle_topic_id");
                    mVar.d.setText(optString2);
                    mVar.d.setOnClickListener(new z(this, optString));
                }
                if (optJSONObject2 != null) {
                    optString2 = optJSONObject2.optString("cnt", "红包领取详情");
                    optString = optJSONObject2.optString("laisee_id");
                    String optString4 = optJSONObject2.optString("secret");
                    mVar.e.setText(optString2);
                    mVar.e.setOnClickListener(new aa(this, optString, optString4));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mVar.a.setOnLongClickListener(new ad(chatMsg));
            return view;
        }
    }

    class o {
        View a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        TextView f;
        final /* synthetic */ ChatListAdapter g;

        public o(ChatListAdapter chatListAdapter, View view) {
            this.g = chatListAdapter;
            this.a = view.findViewById(R.id.laisee_container);
            this.b = (TextView) view.findViewById(R.id.content);
            this.c = (ImageView) view.findViewById(R.id.iv_userhead);
            this.d = (TextView) view.findViewById(R.id.tv_username);
            this.e = (TextView) view.findViewById(R.id.role);
            this.f = (TextView) view.findViewById(R.id.sendDate);
        }

        public void setMsg(ChatMsg chatMsg) {
            LaiseeImInfo laiseeInfo = chatMsg.getLaiseeInfo();
            this.a.setOnClickListener(new ab(this, laiseeInfo));
            this.b.setText(laiseeInfo == null ? "" : laiseeInfo.content);
            this.g.a(chatMsg, this.d);
            this.g.a(chatMsg.from, this.g.c(chatMsg), chatMsg.fromnick, this.c);
            if (this.g.p) {
                this.g.setRole(chatMsg.from, chatMsg.fromgender, this.e);
            } else {
                this.e.setVisibility(8);
            }
        }
    }

    private class p implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private p(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_laisee_me, null);
                view.setTag(new j(this.a, view));
            }
            j jVar = (j) view.getTag();
            LaiseeImInfo laiseeInfo = chatMsg.getLaiseeInfo();
            jVar.a.setOnClickListener(new ad(this, jVar, laiseeInfo));
            jVar.a.setOnLongClickListener(new ad(chatMsg));
            jVar.b.setText(laiseeInfo == null ? "" : laiseeInfo.content);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                jVar.sendTime.setVisibility(8);
            } else {
                jVar.sendTime.setText(chatMsg.getTimeStr());
                jVar.sendTime.setVisibility(0);
            }
            return view;
        }
    }

    private class q implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private q(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_laisee_other, null);
                view.setTag(new o(this.a, view));
            }
            o oVar = (o) view.getTag();
            oVar.setMsg(chatMsg);
            oVar.a.setOnLongClickListener(new ad(chatMsg));
            if (!chatMsg.showTime || chatMsg.time == 0) {
                oVar.f.setVisibility(8);
            } else {
                oVar.f.setText(chatMsg.getTimeStr());
                oVar.f.setVisibility(0);
            }
            return view;
        }
    }

    class r {
        View a;
        TextView b;
        final /* synthetic */ ChatListAdapter c;
        public TextView sendTime;

        public r(ChatListAdapter chatListAdapter, View view) {
            this.c = chatListAdapter;
            this.a = view.findViewById(R.id.laisee_container);
            this.b = (TextView) view.findViewById(R.id.content);
            this.sendTime = (TextView) view.findViewById(R.id.sendDate);
        }

        public void setMsg(ChatMsg chatMsg) {
            LaiseeImInfo laiseeInfo = chatMsg.getLaiseeInfo();
            this.a.setOnClickListener(new af(this, laiseeInfo));
            this.b.setText(laiseeInfo == null ? "" : laiseeInfo.content);
        }
    }

    class s {
        View a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        TextView f;
        final /* synthetic */ ChatListAdapter g;

        public s(ChatListAdapter chatListAdapter, View view) {
            this.g = chatListAdapter;
            this.a = view.findViewById(R.id.laisee_container);
            this.b = (TextView) view.findViewById(R.id.content);
            this.c = (ImageView) view.findViewById(R.id.iv_userhead);
            this.d = (TextView) view.findViewById(R.id.tv_username);
            this.e = (TextView) view.findViewById(R.id.role);
            this.f = (TextView) view.findViewById(R.id.sendDate);
        }

        public void setMsg(ChatMsg chatMsg) {
            LaiseeImInfo laiseeInfo = chatMsg.getLaiseeInfo();
            this.a.setOnClickListener(new ag(this, laiseeInfo));
            this.b.setText(laiseeInfo == null ? "" : laiseeInfo.content);
            this.g.a(chatMsg, this.d);
            this.g.a(chatMsg.from, this.g.c(chatMsg), chatMsg.fromnick, this.c);
            if (this.g.p) {
                this.g.setRole(chatMsg.from, chatMsg.fromgender, this.e);
            } else {
                this.e.setVisibility(8);
            }
        }
    }

    private class t implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private t(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            r rVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_laisee_voice_me, null);
                rVar = new r(this.a, view);
                view.setTag(rVar);
            } else {
                rVar = (r) view.getTag();
            }
            rVar.setMsg(chatMsg);
            return view;
        }
    }

    private class u implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private u(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            s sVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_laisee_voice_other, null);
                sVar = new s(this.a, view);
                view.setTag(sVar);
            } else {
                sVar = (s) view.getTag();
            }
            sVar.setMsg(chatMsg);
            return view;
        }
    }

    private class v extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public v(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            MedalViewHolder medalViewHolder;
            if (view == null || !(view.getTag() instanceof MedalViewHolder)) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_msg_medal_notify, null);
                MedalViewHolder medalViewHolder2 = new MedalViewHolder(this.a);
                medalViewHolder2.b = (TextView) view.findViewById(R.id.sendDate);
                medalViewHolder2.a = (RelativeLayout) view.findViewById(R.id.msgContainer);
                medalViewHolder2.c = (ImageView) view.findViewById(R.id.iv_userhead);
                medalViewHolder2.d = (ImageView) view.findViewById(R.id.coverImage);
                medalViewHolder2.e = (TextView) view.findViewById(R.id.tvBrief);
                view.setTag(medalViewHolder2);
                medalViewHolder = medalViewHolder2;
            } else {
                medalViewHolder = (MedalViewHolder) view.getTag();
            }
            if (UIHelper.isNightTheme()) {
                medalViewHolder.d.setImageDrawable(this.a.h.getResources().getDrawable(R.drawable.medal_night));
            } else {
                medalViewHolder.d.setImageDrawable(this.a.h.getResources().getDrawable(R.drawable.medal));
            }
            if (!chatMsg.showTime || chatMsg.time == 0) {
                medalViewHolder.b.setVisibility(8);
            } else {
                medalViewHolder.b.setText(chatMsg.getTimeStr());
                medalViewHolder.b.setVisibility(0);
            }
            this.a.a(chatMsg.from, this.a.c(chatMsg), chatMsg.fromnick, medalViewHolder.c);
            try {
                JSONObject jSONObject = new JSONObject(chatMsg.data);
                CharSequence optString = jSONObject.optString("text");
                ImageShower.a(medalViewHolder.d, jSONObject.optString("pic_url"));
                if (!TextUtils.isEmpty(optString)) {
                    medalViewHolder.e.setVisibility(0);
                    medalViewHolder.e.setText(optString);
                }
                medalViewHolder.a.setOnClickListener(new ai(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return view;
        }
    }

    private class w implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private w(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            TextView textView;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.item_msg_divider, null);
                textView = (TextView) view.findViewById(R.id.msg);
                view.setTag(textView);
            } else {
                textView = (TextView) view.getTag();
            }
            textView.setText(chatMsg.data);
            return view;
        }
    }

    class x extends BaseAdapter {
        final /* synthetic */ ChatListAdapter a;
        private Activity b = null;
        private String[] c;
        private String[] d;
        private String[] e;
        private String[] f;

        public x(ChatListAdapter chatListAdapter, Activity activity, JSONArray jSONArray, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
            this.a = chatListAdapter;
            this.b = activity;
            this.c = strArr;
            this.e = strArr2;
            this.f = strArr4;
            int length = jSONArray.length();
            this.d = new String[length];
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    strArr3[i] = jSONObject.optString("msg_id");
                    this.c[i] = jSONObject.optString("title");
                    this.d[i] = jSONObject.optString("cover");
                    this.e[i] = jSONObject.optString("url");
                    this.f[i] = jSONObject.optString("post_ids");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public int getCount() {
            return this.c.length;
        }

        public Object getItem(int i) {
            return this.c[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ab abVar;
            if (view == null) {
                View view2 = (LinearLayout) LayoutInflater.from(this.b).inflate(R.layout.im_official_msg_item, null);
                ab abVar2 = new ab(this.a);
                abVar2.a = (TextView) view2.findViewById(R.id.item_title);
                abVar2.b = (ImageView) view2.findViewById(R.id.item_cover);
                view2.setTag(abVar2);
                view = view2;
                abVar = abVar2;
            } else {
                abVar = (ab) view.getTag();
            }
            abVar.b.setImageDrawable(null);
            abVar.a.setText(this.c[i]);
            ImageShower.a(abVar.b, this.d[i]);
            return view;
        }
    }

    private class y implements IChatViewShower {
        final /* synthetic */ ChatListAdapter a;

        private y(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_chatting_item_none, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.sendDate);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_chatcontent);
            if (textView != null) {
                if (!chatMsg.showTime || chatMsg.time == 0) {
                    textView.setVisibility(8);
                } else {
                    textView.setText(chatMsg.getTimeStr());
                    textView.setVisibility(0);
                }
            }
            this.a.a(chatMsg.from, chatMsg.fromicon, chatMsg.fromnick, (ImageView) view.findViewById(R.id.iv_userhead));
            textView2.setOnLongClickListener(new ad(chatMsg));
            return view;
        }
    }

    private class z extends ImageShower {
        final /* synthetic */ ChatListAdapter a;

        public z(ChatListAdapter chatListAdapter) {
            this.a = chatListAdapter;
        }

        public View getView(int i, ChatMsg chatMsg, View view, int i2) {
            OfficialViewHolderMultiple officialViewHolderMultiple;
            JSONObject jSONObject;
            if (view == null) {
                view = LayoutInflater.from(this.a.h).inflate(R.layout.im_official_msg_multiple, null);
                OfficialViewHolderMultiple officialViewHolderMultiple2 = new OfficialViewHolderMultiple(this.a);
                officialViewHolderMultiple2.a = (TextView) view.findViewById(R.id.sendDate);
                officialViewHolderMultiple2.b = (RelativeLayout) view.findViewById(R.id.first_push_msg);
                officialViewHolderMultiple2.d = (TextView) view.findViewById(R.id.first_title);
                officialViewHolderMultiple2.c = (ImageView) view.findViewById(R.id.first_cover);
                officialViewHolderMultiple2.e = (ListView) view.findViewById(R.id.push_msg_list);
                view.setTag(officialViewHolderMultiple2);
                officialViewHolderMultiple = officialViewHolderMultiple2;
            } else {
                officialViewHolderMultiple = (OfficialViewHolderMultiple) view.getTag();
            }
            officialViewHolderMultiple.c.setImageDrawable(null);
            if (!chatMsg.showTime || chatMsg.time == 0) {
                officialViewHolderMultiple.a.setVisibility(8);
            } else {
                officialViewHolderMultiple.a.setText(chatMsg.getTimeStr());
                officialViewHolderMultiple.a.setVisibility(0);
            }
            try {
                jSONObject = new JSONObject(chatMsg.data);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject != null) {
                String optString = jSONObject.optString("msg_id");
                Object optString2 = jSONObject.optString("title");
                Object optString3 = jSONObject.optString("cover");
                String optString4 = jSONObject.optString("url");
                String optString5 = jSONObject.optString("post_ids");
                jSONObject.optString("action");
                officialViewHolderMultiple.d.setText(optString2);
                if (!TextUtils.isEmpty(optString3)) {
                    ImageShower.a(officialViewHolderMultiple.c, optString3);
                    officialViewHolderMultiple.b.setOnLongClickListener(new ad(chatMsg));
                    officialViewHolderMultiple.b.setOnClickListener(new aj(this, optString4, optString2, optString, optString5, chatMsg));
                }
                JSONArray jSONArray = null;
                try {
                    jSONArray = jSONObject.optJSONArray("others");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (jSONArray != null) {
                    String[] strArr = new String[jSONArray.length()];
                    String[] strArr2 = new String[jSONArray.length()];
                    String[] strArr3 = new String[jSONArray.length()];
                    String[] strArr4 = new String[jSONArray.length()];
                    officialViewHolderMultiple.e.setAdapter(new x(this.a, this.a.h, jSONArray, strArr2, strArr3, strArr, strArr4));
                    Util.setListViewHeightBasedOnChildren(officialViewHolderMultiple.e);
                    officialViewHolderMultiple.e.setOnItemLongClickListener(new ak(this, chatMsg));
                    officialViewHolderMultiple.e.setOnItemClickListener(new al(this, strArr3, strArr2, strArr, strArr4, chatMsg));
                }
            }
            return view;
        }
    }

    ChatListAdapter(Activity activity, String str, UploadedListener uploadedListener) {
        this.h = activity;
        this.i = str;
        this.m = uploadedListener;
        this.g = UIHelper.dip2px(activity, 3.0f);
        init();
    }

    public ChatListAdapter(Activity activity, String str, String str2, UploadedListener uploadedListener, boolean z) {
        this.h = activity;
        this.m = uploadedListener;
        this.p = z;
        this.s = str;
        this.t = str2;
        this.g = UIHelper.dip2px(activity, 3.0f);
        init();
    }

    private static ChatMsgImageData b(String str) {
        return new ChatMsgImageData(str);
    }

    public static boolean isNetworkUrl(String str) {
        return (str.indexOf("http://") == -1 && str.indexOf("https://") == -1) ? false : true;
    }

    private static String a(String str, int i, int i2) {
        if (!isNetworkUrl(str)) {
            return str;
        }
        return str + String.format("?imageView/2/w/%s/h/%s/format/webp", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
    }

    public static String appendSmallSize(String str, int i, int i2) {
        if (!isNetworkUrl(str)) {
            return str;
        }
        return str + String.format("?imageView/1/w/%s/h/%s/format/webp", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
    }

    private static int a(long j) {
        long min = Math.min(j, 60);
        if (min <= 10) {
            return Util.dp((float) ((Math.max(0, min - 2) * 9) + 100));
        }
        return Util.dp((float) ((((min / 10) + 7) * 9) + 100));
    }

    private static ChatMsgVoiceData b(ChatMsg chatMsg) {
        if (chatMsg.tag != null) {
            return (ChatMsgVoiceData) chatMsg.tag;
        }
        ChatMsgVoiceData chatMsgVoiceData = new ChatMsgVoiceData(chatMsg.data);
        chatMsg.tag = chatMsgVoiceData;
        return chatMsgVoiceData;
    }

    public static void setLayoutParams(ImageView imageView, int i, int i2) {
        if (imageView != null) {
            LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = i2;
                layoutParams.width = i;
            } else {
                layoutParams = new LayoutParams(i, i2);
            }
            imageView.setLayoutParams(layoutParams);
        }
    }

    public void setOnAvatarClickListener(OnAvatarClickListener onAvatarClickListener) {
        this.w = onAvatarClickListener;
    }

    public void setTitles(String[] strArr) {
        this.u = strArr;
    }

    public void setMembers(ArrayList<BaseUserInfo> arrayList) {
        this.v = arrayList;
    }

    private String c(ChatMsg chatMsg) {
        return (!this.p || chatMsg == null) ? this.i : chatMsg.fromicon;
    }

    public int getCount() {
        return this.e.size();
    }

    public ChatMsg getItem(int i) {
        return (ChatMsg) this.e.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    private void a(List<ChatMsg> list, boolean z) {
        ChatMsg chatMsg;
        long j;
        long j2;
        int i;
        if (z) {
            chatMsg = (ChatMsg) ArrayUtils.findLast(this.e, new f(this));
            if (chatMsg != null) {
                j = chatMsg.time;
                j2 = j;
                for (i = 0; i < list.size(); i++) {
                    chatMsg = (ChatMsg) list.get(i);
                    if (chatMsg.time - j2 <= ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL) {
                        chatMsg.showTime = true;
                        j2 = chatMsg.time;
                    } else {
                        chatMsg.showTime = false;
                    }
                }
            }
        }
        j = 0;
        j2 = j;
        for (i = 0; i < list.size(); i++) {
            chatMsg = (ChatMsg) list.get(i);
            if (chatMsg.time - j2 <= ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL) {
                chatMsg.showTime = false;
            } else {
                chatMsg.showTime = true;
                j2 = chatMsg.time;
            }
        }
    }

    private void a(ChatMsg chatMsg, boolean z) {
        List linkedList = new LinkedList();
        linkedList.add(chatMsg);
        a(linkedList, z);
    }

    public boolean contains(ChatMsg chatMsg) {
        if (chatMsg == null) {
            return false;
        }
        if (this.e.contains(chatMsg)) {
            return true;
        }
        for (ChatMsg chatMsg2 : this.e) {
            if (!TextUtils.isEmpty(chatMsg2.msgid) && chatMsg2.msgid.equalsIgnoreCase(chatMsg.msgid)) {
                return true;
            }
        }
        return false;
    }

    public void appendItem(ChatMsg chatMsg) {
        if (chatMsg != null && !TextUtils.isEmpty(chatMsg.msgid) && !contains(chatMsg)) {
            a(chatMsg, true);
            this.e.add(chatMsg);
        }
    }

    public void appendItem(List<ChatMsg> list) {
        if (list != null) {
            for (ChatMsg appendItem : list) {
                appendItem(appendItem);
            }
        }
    }

    public Pair<Boolean, List<ChatMsg>> mergeUnExistMsg(List<ChatMsg> list) {
        ChatMsg chatMsg;
        HashMap hashMap = new HashMap();
        for (ChatMsg chatMsg2 : this.e) {
            if (chatMsg2.dbid > 0) {
                hashMap.put(Long.valueOf(chatMsg2.dbid), chatMsg2);
            }
        }
        LinkedList linkedList = new LinkedList();
        boolean z = false;
        for (ChatMsg chatMsg22 : list) {
            boolean z2;
            if (hashMap.get(Long.valueOf(chatMsg22.dbid)) == null) {
                linkedList.add(chatMsg22);
                z2 = z;
            } else if (((ChatMsg) hashMap.get(Long.valueOf(chatMsg22.dbid))).status != chatMsg22.status) {
                ((ChatMsg) hashMap.get(Long.valueOf(chatMsg22.dbid))).status = chatMsg22.status;
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            int i;
            chatMsg22 = (ChatMsg) it.next();
            for (int size = this.e.size() - 1; size >= 0; size--) {
                if (chatMsg22.dbid > ((ChatMsg) this.e.get(size)).dbid && !contains(chatMsg22)) {
                    this.e.add(size + 1, chatMsg22);
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0 && !contains(chatMsg22)) {
                this.e.add(0, chatMsg22);
            }
        }
        return new Pair(Boolean.valueOf(z), linkedList);
    }

    public void removeMsgById(long j) {
        if (this.e != null && !this.e.isEmpty()) {
            if (((ChatMsg) this.e.get(this.e.size() - 1)).dbid == j) {
                ArrayUtils.remove(this.e, new g(this, j));
                aw.b(j);
                notifyDataSetChanged();
            } else {
                ArrayUtils.remove(this.e, new g(this, j));
                aw.b(j);
                notifyDataSetChanged();
            }
        }
    }

    public void insertMsg(List<ChatMsg> list) {
        if (list != null) {
            a((List) list, false);
            for (int size = list.size() - 1; size >= 0; size--) {
                ChatMsg chatMsg = (ChatMsg) list.get(size);
                if (!(chatMsg == null || TextUtils.isEmpty(chatMsg.msgid) || contains(chatMsg))) {
                    this.e.add(0, chatMsg);
                }
            }
        }
    }

    public int getItemViewType(int i) {
        ChatMsg item = getItem(i);
        if (item.type == 1) {
            if (item.inout == 2) {
                return 2;
            }
            return 1;
        } else if (item.type == 102) {
            return 3;
        } else {
            if (item.type == 8) {
                return 4;
            }
            if (item.type == 3) {
                if (item.inout == 2) {
                    return 5;
                }
                return 6;
            } else if (item.type == 5) {
                if (item.inout == 2) {
                    return 8;
                }
                return 7;
            } else if (item.type == 10) {
                try {
                    if (new JSONObject(item.data).opt("others") == null) {
                        return 9;
                    }
                    return 10;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (item.type == 203) {
                return 11;
            } else {
                if (item.type == 301) {
                    return 12;
                }
                if (item.type == 302) {
                    return 13;
                }
                if (item.type == 20) {
                    return 14;
                }
                if (item.type == 4) {
                    if (item.inout == 2) {
                        return 17;
                    }
                    return 18;
                } else if (item.type == 22 || item.type == 24 || item.type == 27) {
                    if (item.inout == 2) {
                        return 15;
                    }
                    return 16;
                } else if (item.type == 23 || item.type == 29 || item.type == 31) {
                    if (item.inout == 2) {
                        return 20;
                    }
                    return 21;
                } else if (item.type == TYPE.TYPE_MSG_DIVIDER) {
                    return 19;
                } else {
                    if (item.type == 26 || item.type == 25) {
                        if (item.inout == 2) {
                            return 24;
                        }
                        return 25;
                    } else if (item.type == 28) {
                        return 26;
                    } else {
                        if (item.type == 30) {
                            return 27;
                        }
                        if (item.type == 32) {
                            return 28;
                        }
                        if (item.type == 33) {
                            if (item.inout == 2) {
                                return 29;
                            }
                            return 30;
                        } else if (item.type == 38) {
                            if (item.inout == 2) {
                                return 37;
                            }
                            return 38;
                        } else if (item.type == 34) {
                            return 31;
                        } else {
                            if (item.type == 35) {
                                return 32;
                            }
                            if (item.type == 36) {
                                if (item.inout == 2) {
                                    return 33;
                                }
                                return 34;
                            } else if (item.type != 37) {
                                if (item.type == 39) {
                                    return 39;
                                }
                                return 0;
                            } else if (item.inout == 2) {
                                return 35;
                            } else {
                                return 36;
                            }
                        }
                    }
                }
            }
        }
    }

    public int getViewTypeCount() {
        return 41;
    }

    private void a(String str, TextView textView) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        IMChatMsgSource msgSourceFromChatMsg = IMChatMsgSource.getMsgSourceFromChatMsg(str);
        if (msgSourceFromChatMsg == null || msgSourceFromChatMsg.getPresentText() == null) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(msgSourceFromChatMsg.getPresentText());
        textView.setOnClickListener(new h(this, msgSourceFromChatMsg));
        textView.setVisibility(8);
    }

    private void a(String str, String str2, String str3, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
        imageView.setOnClickListener(new UserClickDelegate(str, new UserHeadClickListener(str, str3, this.s, this.t, this.w, this.h)));
        if (this.h instanceof GroupConversationActivity) {
            imageView.setOnLongClickListener(new UserHeadLongClickListener(str, str3, this.s, this.t, (GroupConversationActivity) this.h));
            return;
        }
        imageView.setOnLongClickListener(null);
    }

    public void setIcon(String str, String str2, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    public void setRole(String str, String str2, TextView textView) {
        if (this.v == null) {
            textView.setVisibility(4);
            return;
        }
        BaseUserInfo baseUserInfo;
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            BaseUserInfo baseUserInfo2 = (BaseUserInfo) it.next();
            if (baseUserInfo2.userId.equals(str)) {
                baseUserInfo = baseUserInfo2;
                break;
            }
        }
        baseUserInfo = null;
        CharSequence roleTitle = baseUserInfo == null ? null : baseUserInfo.getRoleTitle(this.u);
        if (roleTitle != null) {
            textView.setText(roleTitle);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(4);
        }
        if (textView.getVisibility() != 0) {
            return;
        }
        String str3;
        if (UIHelper.isNightTheme()) {
            str3 = "F";
            if (baseUserInfo != null) {
                str2 = baseUserInfo.gender;
            }
            if (str3.equalsIgnoreCase(str2)) {
                textView.setBackgroundResource(R.drawable.pinfo_female_bg_night);
            } else {
                textView.setBackgroundResource(R.drawable.pinfo_man_bg_night);
            }
            textView.setPadding(this.g, 0, this.g, 0);
            textView.setTextColor(-5066062);
            return;
        }
        str3 = "F";
        if (baseUserInfo != null) {
            str2 = baseUserInfo.gender;
        }
        if (str3.equalsIgnoreCase(str2)) {
            textView.setBackgroundResource(R.drawable.pinfo_female_bg);
        } else {
            textView.setBackgroundResource(R.drawable.pinfo_man_bg);
        }
        textView.setPadding(this.g, 0, this.g, 0);
        textView.setTextColor(-1);
    }

    public void addSystemMsg(ChatMsg chatMsg) {
        this.e.add(chatMsg);
        notifyDataSetChanged();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == ApplyForGroupActivity.REQ_APPLY && this.x != null) {
            ChatMsg chatMsg = this.x.n;
            chatMsg.getInviteInfo().group.status = i2;
            chatMsg.data = chatMsg.getInviteInfo().toJSONString();
            ChatMsgStoreProxy.newInstance(QsbkApp.currentUser.userId, 0).updateMessageData(chatMsg);
            this.x.c.setText(i2 == 2 ? "已加入" : "已申请");
        }
    }

    private void b() {
        this.l = new aw(this.m);
        this.k = new ImageUploader(QsbkApp.currentUser.userId);
    }

    private void c() {
        this.q = new VoiceManager(QsbkApp.currentUser.userId, CustomHttpClient.getInstance().getOKHttpClient());
    }

    public void init() {
        if (this.h instanceof IMChatBaseActivity) {
            this.j = ((IMChatBaseActivity) this.h).getToId();
        }
        b();
        c();
        this.y.put(2, new TxtViewShower(this));
        this.y.put(1, new TxtViewShower(this));
        this.y.put(3, new as());
        this.y.put(0, new y());
        this.y.put(4, new au());
        this.y.put(5, new ImageShowerMe(this, this.l, this.k));
        this.y.put(6, new ImageShowerOther(this));
        this.y.put(8, new PureEmotionShower(this));
        this.y.put(7, new PureEmotionShowerOther(this));
        this.y.put(9, new aa(this));
        this.y.put(10, new z(this));
        this.y.put(11, new InviteShowerOther(this));
        this.y.put(12, new b());
        this.y.put(13, new OtherJoinedSuccessed(this));
        this.y.put(14, new QiushiPushShower(this));
        this.y.put(17, new VoiceShowerMe(this, this.q, this.m));
        this.y.put(18, new VoiceShowerOther(this, this.q));
        this.y.put(15, new am(this));
        this.y.put(16, new an(this));
        this.y.put(20, new az(this));
        this.y.put(21, new ba(this));
        this.y.put(19, new w());
        this.y.put(25, new c(this));
        this.y.put(24, new d(this));
        this.y.put(26, new v(this));
        this.y.put(27, new a(this));
        this.y.put(28, new at());
        this.y.put(30, new q());
        this.y.put(29, new p());
        this.y.put(31, new l());
        this.y.put(37, new t());
        this.y.put(38, new u());
        this.y.put(32, new n());
        this.y.put(34, new ai());
        this.y.put(33, new ah());
        this.y.put(36, new ap());
        this.y.put(35, new ao());
        this.y.put(39, new ag());
    }

    private String a(ChatMsgImageData chatMsgImageData) {
        int i;
        int i2 = 9999;
        if (chatMsgImageData.height > 9999) {
            if (chatMsgImageData.height <= 9999) {
                i2 = chatMsgImageData.height;
            }
            i = (chatMsgImageData.width * 10000) / chatMsgImageData.height;
        } else {
            i = chatMsgImageData.width;
            i2 = chatMsgImageData.height;
        }
        return a(chatMsgImageData.url, i, i2);
    }

    public void onDestroy() {
        clearMomeryCache();
    }

    public void clearMomeryCache() {
        List list = this.e;
        int size = list.size();
        List arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            ChatMsg chatMsg = (ChatMsg) list.get(i);
            if (chatMsg.type == 3) {
                CharSequence appendSmallSize;
                ChatMsgImageData b = b(chatMsg.data);
                IMImageSize imageSize = IMImageSizeHelper.getImageSize(Size.Medium, b.width, b.height, this.h);
                if (chatMsg.inout == 2) {
                    appendSmallSize = appendSmallSize(b.url, imageSize.getDstWidth(), imageSize.getDstHeight());
                } else {
                    appendSmallSize = appendSmallSize(b.url, (imageSize.getDstWidth() / 3) + 1, (imageSize.getDstHeight() / 3) + 1);
                }
                if (!TextUtils.isEmpty(appendSmallSize)) {
                    arrayList.add(appendSmallSize);
                }
                appendSmallSize = a(b);
                if (!TextUtils.isEmpty(appendSmallSize)) {
                    arrayList.add(appendSmallSize);
                }
            }
        }
        FrescoImageloader.evictFromMemoryCache(arrayList);
    }

    public void stopVoice() {
        if (this.r != null) {
            this.r.b();
        }
    }

    public boolean isVoicePlaying() {
        if (this.r == null) {
            return false;
        }
        return this.r.a();
    }

    private void a(View view, ChatMsgVoiceData chatMsgVoiceData) {
        if (view != null && chatMsgVoiceData != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = a((long) chatMsgVoiceData.duration);
            view.setLayoutParams(layoutParams);
        }
    }

    public void matchUrl(SpannableString spannableString, int i, float f) {
        Matcher matcher = Pattern.compile("(((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnrwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eouw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agkmsyz]|v[aceginu]|w[fs]|y[etu]|z[amw]))|(?:(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$))", 2).matcher(spannableString);
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            spannableString.setSpan(new i(this, spannableString.toString().substring(start, end)), start, end, 33);
            if (i == 2) {
                spannableString.setSpan(new TextAppearanceSpan("default", 0, (int) f, this.h.getResources().getColorStateList(R.color.color_list), null), start, end, 33);
            }
        }
    }

    private void a(ChatMsg chatMsg, TextView textView) {
        if (textView != null && chatMsg != null) {
            CharSequence remark = RemarkManager.getRemark(chatMsg.from);
            if (this.p && !TextUtils.isEmpty(remark)) {
                textView.setVisibility(0);
                textView.setText(remark);
            } else if (TextUtils.isEmpty(chatMsg.fromnick) || !this.p) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(chatMsg.fromnick);
            }
        }
    }

    private void a(String str, String str2, String str3, String str4, long j) {
        StatService.onEvent(this.h, "OFFICIAL_PUSH_MSG", "unit_" + str3);
        Logger.getInstance().debug(f, "jumpToDetailUrl", "unit_" + str3);
        if (!str.contains("qiushibaikerole=cafe") || !str.contains("cafe.qiushibaike.com")) {
            if (TextUtils.isEmpty(str4)) {
                String str5;
                if (str.contains("?")) {
                    str5 = str + "&uuid=" + DeviceUtils.getAndroidId();
                } else {
                    str5 = str + "?uuid=" + DeviceUtils.getAndroidId();
                }
                Intent intent = new Intent(this.h, OfficialMsgDetailActivity.class);
                intent.putExtra("url", str5);
                intent.putExtra("title", str2);
                this.h.startActivity(intent);
                return;
            }
            HighLightQiushiActivity.luanchActivity(this.h, str4, str2, j, str3);
        }
    }

    private void c(String str) {
        Intent intent = new Intent(this.h, SingleArticle.class);
        intent.putExtra("article_id", str);
        intent.putExtra("source", "only_article_id");
        DebugUtil.debug("QiushiShare", "jumpToArticle, artId=" + str + ",intent=" + intent);
        this.h.startActivity(intent);
    }

    public void flashView(View view) {
        Animation jVar = new j(this, view);
        jVar.setDuration(500);
        view.startAnimation(jVar);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        IChatViewShower iChatViewShower = (IChatViewShower) this.y.get(itemViewType);
        ChatMsg item = getItem(i);
        if (iChatViewShower == null) {
            return null;
        }
        if (this.r == null) {
            this.r = new ax(this, (AdapterView) viewGroup);
        }
        View view2 = iChatViewShower.getView(i, item, view, itemViewType);
        if (item != this._toFlash) {
            return view2;
        }
        this._toFlash = null;
        flashView(view2);
        return view2;
    }

    public void removeSendingMsg(boolean z) {
        ArrayUtils.remove(this.e, new k(this));
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void moveSendingToLast() {
        ChatMsg chatMsg = (ChatMsg) ArrayUtils.findLast(this.e, new l(this));
        if (chatMsg != null) {
            this.e.remove(chatMsg);
            this.e.add(chatMsg);
        }
    }

    public void addSendingMsg(ChatMsg chatMsg) {
        if (((ChatMsg) ArrayUtils.findLast(this.e, new m(this))) != null) {
            LogUtil.d("sending is in");
            return;
        }
        LogUtil.d("sending is not in");
        this.e.add(chatMsg);
        notifyDataSetChanged();
    }

    public boolean onMsgStateChange(long j, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            ChatMsg chatMsg = (ChatMsg) this.e.get(i2);
            if (chatMsg.status != 5 && chatMsg.dbid == j) {
                z = true;
                LogUtil.d("on msg status changed:" + chatMsg.data + " status:" + i);
                chatMsg.status = i;
            }
        }
        if (z) {
            notifyDataSetChanged();
        }
        return z;
    }

    public boolean onMsgStateChange(LongSparseArray<Integer> longSparseArray) {
        LongSparseArray clone = longSparseArray.clone();
        int i = 0;
        boolean z = false;
        while (i < this.e.size()) {
            ChatMsg chatMsg = (ChatMsg) this.e.get(i);
            if (!(chatMsg.status == 5 || clone.get(chatMsg.dbid) == null)) {
                z = true;
                chatMsg.status = ((Integer) clone.get(chatMsg.dbid)).intValue();
            }
            i++;
            z = z;
        }
        if (z) {
            notifyDataSetChanged();
        }
        return z;
    }

    public boolean onMsgStateChanged(List<String> list, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ChatMsg chatMsg = (ChatMsg) ArrayUtils.findFirst(this.e, new n(this, (String) list.get(i2)));
            if (!(chatMsg == null || chatMsg.status == 5)) {
                z = true;
                LogUtil.d("status changed:" + chatMsg.data + " status:" + i);
                chatMsg.status = i;
            }
        }
        if (z) {
            notifyDataSetChanged();
        }
        return z;
    }

    private boolean a(int i) {
        return !this.p || i == 1 || i == 3;
    }
}
