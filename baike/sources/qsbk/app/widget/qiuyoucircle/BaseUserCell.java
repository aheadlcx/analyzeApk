package qsbk.app.widget.qiuyoucircle;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.adapter.ParticipateAdapter;
import qsbk.app.adapter.ParticipateAdapter.SubscribeIcon;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.RelationshipCacheMgr;
import qsbk.app.im.TimeUtils;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.RoundedDrawable;
import qsbk.app.widget.TouchResponseProgressBar;

public abstract class BaseUserCell extends BaseCell {
    private ProgressDialog a;
    public View actionView;
    public TextView addFriendDesc;
    public TouchResponseProgressBar addFriendProgressBar;
    public View addFriendView;
    public TextView age;
    public TextView auditStatusView;
    public ImageView avatarView;
    public TextView commentCountView;
    public TextView distanceView;
    public View divider;
    public boolean fromCircleTopic = false;
    public ImageView gender;
    public View genderAge;
    public TextView hotComment;
    public ImageView hotCommentImage;
    public ImageView hotCommentLabel;
    public TextView hotView;
    public boolean isDetail;
    public ImageView likeCountBt;
    public TickerView likeCountView;
    public TextView nameView;
    public TextView shareCount;
    public ShareUtils$OnCircleShareStartListener shareListener;
    public TextView timeView;
    public View topLayout;
    public View unTop;

    public BaseUserCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        this.shareListener = shareUtils$OnCircleShareStartListener;
    }

    public BaseUserCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        this.shareListener = shareUtils$OnCircleShareStartListener;
        this.isDetail = z;
    }

    public static CharSequence getContent(Context context, CircleArticle circleArticle, boolean z) {
        if (circleArticle.topic == null && (circleArticle.atInfoTexts == null || circleArticle.atInfoTexts.size() <= 0)) {
            return new SpannableString(circleArticle.content);
        }
        int indexOf;
        CharSequence spannableString = new SpannableString(circleArticle.content);
        if (circleArticle.topic != null) {
            indexOf = circleArticle.content.indexOf(circleArticle.topic.content);
            if (indexOf != -1) {
                int length = circleArticle.topic.content.length() + indexOf;
                spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), indexOf, length, 33);
                if (!(context instanceof CircleTopicActivity)) {
                    spannableString.setSpan(new j(z, circleArticle), indexOf, length, 33);
                }
            }
        }
        if (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0) {
            for (indexOf = 0; indexOf < circleArticle.atInfoTexts.size(); indexOf++) {
                AtInfo atInfo = (AtInfo) circleArticle.atInfoTexts.get(indexOf);
                try {
                    Matcher matcher = Pattern.compile("@" + atInfo.name).matcher(circleArticle.content);
                    while (matcher.find()) {
                        int start = matcher.start(0);
                        int end = matcher.end(0);
                        spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), start, end, 33);
                        if (!(context instanceof CircleTopicActivity)) {
                            spannableString.setSpan(new u(atInfo, circleArticle), start, end, 33);
                        }
                    }
                } catch (PatternSyntaxException e) {
                }
            }
        }
        return spannableString;
    }

    public int getLayoutId() {
        return 0;
    }

    public void setCellView(View view) {
        OnClickListener vVar;
        super.setCellView(view);
        this.avatarView = (ImageView) findViewById(R.id.avatar);
        this.nameView = (TextView) findViewById(R.id.nickname);
        this.divider = findViewById(R.id.divider);
        if (QsbkApp.currentUser == null) {
            vVar = new v(this);
        } else {
            Object loginPermissionClickDelegate = new LoginPermissionClickDelegate(new w(this), null);
        }
        OnClickListener userClickDelegate = new UserClickDelegate(((CircleArticle) getItem()).user.userId, vVar);
        this.nameView.setOnClickListener(userClickDelegate);
        this.avatarView.setOnClickListener(userClickDelegate);
        this.timeView = (TextView) findViewById(R.id.time);
        this.actionView = findViewById(R.id.action);
        this.actionView.setOnClickListener(new LoginPermissionClickDelegate(new x(this), null));
        this.distanceView = (TextView) findViewById(R.id.distance);
        this.hotView = (TextView) findViewById(R.id.hot);
        this.likeCountView = (TickerView) findViewById(R.id.like_count);
        this.likeCountView.setCharacterList(UIHelper.getDefaultNumberList(true));
        this.likeCountBt = (ImageView) findViewById(R.id.like_count_bt);
        OnClickListener loginPermissionClickDelegate2 = new LoginPermissionClickDelegate(new y(this), null);
        this.likeCountView.setOnClickListener(loginPermissionClickDelegate2);
        this.likeCountBt.setOnClickListener(loginPermissionClickDelegate2);
        this.commentCountView = (TextView) findViewById(R.id.comment_count);
        this.commentCountView.setOnClickListener(new aa(this));
        this.hotComment = (TextView) findViewById(R.id.hot_comment);
        this.hotCommentLabel = (ImageView) findViewById(R.id.hot_comment_label);
        this.hotCommentImage = (ImageView) findViewById(R.id.hot_comment_img);
        this.genderAge = findViewById(R.id.gender_age);
        this.gender = (ImageView) findViewById(R.id.gender);
        this.age = (TextView) findViewById(R.id.age);
        this.topLayout = findViewById(R.id.top_layout);
        this.unTop = findViewById(R.id.untop);
        if (this.unTop != null) {
            this.unTop.setOnClickListener(new ab(this));
        }
        this.shareCount = (TextView) findViewById(R.id.share_count);
        this.shareCount.setOnClickListener(new LoginPermissionClickDelegate(new ac(this), null));
        this.auditStatusView = (TextView) findViewById(R.id.audit_status);
        this.addFriendView = findViewById(R.id.add_friend);
        this.addFriendDesc = (TextView) findViewById(R.id.personal_fans_tv);
        this.addFriendProgressBar = (TouchResponseProgressBar) findViewById(R.id.add_friend_progress);
    }

    public void untopDialog(Context context, CircleArticle circleArticle) {
        new Builder(context).setMessage("取消置顶，该动态将不会出现在话题顶部").setPositiveButton("确定", new l(this, context, circleArticle)).setNegativeButton("取消", new k(this)).show();
    }

    public void untop(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_UNTOP_ARTICLE, new m(this, context, "处理中", context));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", circleArticle.topic.id);
        hashMap.put("article_id", circleArticle.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void checkJoinTopic() {
        CircleArticle circleArticle = (CircleArticle) getItem();
        if (circleArticle.topic != null) {
            CircleTopicManager.getInstance().insertTopicToLRU(circleArticle.topic);
        }
    }

    public void onUpdate() {
        boolean z;
        int i;
        CircleArticle circleArticle = (CircleArticle) getItem();
        if (circleArticle.user.isAnonymous()) {
            this.avatarView.setImageDrawable(RoundedDrawable.fromDrawable(this.avatarView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
        } else {
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(circleArticle.user.userIcon, circleArticle.user.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.avatarView.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                displayAvatar(this.avatarView, absoluteUrlOfMediumUserIcon);
            }
        }
        CharSequence remark = RemarkManager.getRemark(circleArticle.user.userId);
        TextView textView = this.nameView;
        if (TextUtils.isEmpty(remark)) {
            remark = circleArticle.user.userName;
        }
        textView.setText(remark);
        this.nameView.setTextColor(UIHelper.getNameColor(circleArticle.user.nickStatus));
        this.timeView.setText(TimeUtils.getLastLoginStr(((long) circleArticle.createAt) * 1000));
        this.distanceView.setText(circleArticle.distance);
        this.hotView.setVisibility(8);
        this.commentCountView.setText(String.valueOf(circleArticle.commentCount));
        this.likeCountView.setTypeface(this.commentCountView.getTypeface());
        this.likeCountView.setText(String.valueOf(circleArticle.likeCount), false);
        this.likeCountView.setEnabled(!circleArticle.liked);
        ImageView imageView = this.likeCountBt;
        if (circleArticle.liked) {
            z = false;
        } else {
            z = true;
        }
        imageView.setEnabled(z);
        this.shareCount.setText(circleArticle.shareCount == 0 ? "" : String.valueOf(circleArticle.shareCount));
        this.genderAge.setVisibility(0);
        if (UIHelper.isNightTheme()) {
            this.genderAge.setBackgroundColor(0);
            if ("F".equalsIgnoreCase(circleArticle.user.gender)) {
                this.gender.setImageResource(R.drawable.pinfo_female_dark);
                this.age.setTextColor(getContext().getResources().getColor(R.color.age_female));
            } else if ("M".equalsIgnoreCase(circleArticle.user.gender)) {
                this.gender.setImageResource(R.drawable.pinfo_male_dark);
                this.age.setTextColor(getContext().getResources().getColor(R.color.age_male));
            } else {
                this.genderAge.setVisibility(4);
            }
        } else {
            if ("F".equalsIgnoreCase(circleArticle.user.gender)) {
                this.genderAge.setBackgroundResource(R.drawable.pinfo_female_bg);
                this.gender.setImageResource(R.drawable.pinfo_female);
            } else if ("M".equalsIgnoreCase(circleArticle.user.gender)) {
                this.genderAge.setBackgroundResource(R.drawable.pinfo_man_bg);
                this.gender.setImageResource(R.drawable.pinfo_male);
            } else {
                this.genderAge.setVisibility(4);
            }
            this.age.setTextColor(-1);
        }
        if (circleArticle.user.age <= 0) {
            this.genderAge.setVisibility(4);
        }
        this.age.setText(String.valueOf(circleArticle.user.age));
        if (this.topLayout != null) {
            View view = this.topLayout;
            if (circleArticle.isTop) {
                i = 0;
            } else {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (this.unTop != null) {
            if (QsbkApp.currentUser == null || circleArticle.topic == null || circleArticle.topic.user == null || !QsbkApp.currentUser.userId.equals(circleArticle.topic.user.userId)) {
                this.unTop.setVisibility(8);
            } else {
                this.unTop.setVisibility(0);
            }
        }
        if (this.q == 0 && this.divider != null && !this.fromCircleTopic) {
            this.divider.setVisibility(8);
        } else if (this.divider != null) {
            this.divider.setVisibility(0);
        }
        this.timeView.setVisibility(8);
        if (this.auditStatusView != null) {
            this.auditStatusView.setGravity(17);
            if (circleArticle.auditStatus != 1) {
                this.auditStatusView.setTextColor(-1);
                this.auditStatusView.setVisibility(0);
                this.auditStatusView.setText(circleArticle.getAuditText());
                this.auditStatusView.setBackgroundResource(circleArticle.getAuditIconResource());
                this.auditStatusView.setTextSize(12.0f);
            } else if (TextUtils.isEmpty(circleArticle.label)) {
                this.timeView.setVisibility(0);
                this.auditStatusView.setVisibility(8);
            } else {
                textView = this.auditStatusView;
                if (UIHelper.isNightTheme()) {
                    i = UIHelper.nameColorsNight[0];
                } else {
                    i = UIHelper.nameColors[0];
                }
                textView.setTextColor(i);
                SubscribeIcon subscribIcons = ParticipateAdapter.getSubscribIcons(circleArticle.label);
                if (subscribIcons != null) {
                    this.auditStatusView.setVisibility(0);
                    this.auditStatusView.setTextSize(10.0f);
                    if (!TextUtils.isEmpty(subscribIcons.getIconUrl())) {
                        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(subscribIcons.getIconUrl()), this.auditStatusView.getContext().getApplicationContext()).subscribe(new n(this, subscribIcons), UiThreadImmediateExecutorService.getInstance());
                    }
                }
            }
        }
        this.actionView.setVisibility(0);
        this.divider.setVisibility(0);
        if (this.addFriendView == null) {
            return;
        }
        if (TextUtils.isEmpty(circleArticle.label)) {
            this.addFriendView.setVisibility(8);
            return;
        }
        if (getPosition() == 1) {
            this.divider.setVisibility(8);
        }
        this.actionView.setVisibility(8);
        this.addFriendView.setVisibility(0);
        if (QsbkApp.currentUser != null) {
            Relationship relationship = RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).getRelationship(circleArticle.user.userId);
            if (relationship != null) {
                circleArticle.user.relationship = relationship;
            }
        }
        if (circleArticle.user.relationship == null) {
            circleArticle.user.relationship = Relationship.NO_REL;
        }
        this.addFriendProgressBar.setProgress(0);
        switch (t.a[circleArticle.user.relationship.ordinal()]) {
            case 1:
                this.addFriendDesc.setTextColor(UIHelper.isNightTheme() ? -10657937 : -3947581);
                this.addFriendDesc.setText("互粉");
                this.addFriendDesc.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.mutual_fans_small_night : R.drawable.mutual_fans_small), null, null, null);
                break;
            case 2:
            case 3:
                this.addFriendDesc.setTextColor(UIHelper.isNightTheme() ? -10657937 : -3947581);
                this.addFriendDesc.setText("已粉");
                this.addFriendDesc.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.hava_fans_small_night : R.drawable.hava_fans_small), null, null, null);
                break;
            default:
                this.addFriendDesc.setTextColor(UIHelper.isNightTheme() ? -4486889 : -17899);
                this.addFriendDesc.setText("长按加粉");
                this.addFriendDesc.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.fans_small_night : R.drawable.fans_small), null, null, null);
                break;
        }
        if (circleArticle.user.relationship == Relationship.FRIEND || circleArticle.user.relationship == Relationship.FOLLOW_REPLIED || circleArticle.user.relationship == Relationship.FOLLOW_UNREPLIED) {
            this.addFriendProgressBar.setVisibility(8);
        } else {
            this.addFriendProgressBar.setVisibility(0);
        }
        this.addFriendProgressBar.setOnProgressListener(new o(this, circleArticle));
    }

    public int getContentMaxLine() {
        return 4;
    }

    private void a(int i, int i2, String str) {
        String str2 = Constants.REL_FOLLOW;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        String format = String.format(str2, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", str);
        hashMap.put("shake_time", Integer.valueOf(i));
        hashMap.put("shake_count", Integer.valueOf(i2));
        a(Constants.REL_FOLLOW, format, hashMap, "正在加粉,请稍后...", str);
    }

    private void a(String str, String str2, Map<String, Object> map, String str3, String str4) {
        if (this.a != null) {
            this.a.cancel();
            this.a.dismiss();
        }
        this.a = ProgressDialog.show(getContext(), null, str3, true, true);
        this.a.setCancelable(true);
        this.a.setCanceledOnTouchOutside(true);
        SimpleHttpTask sVar = new s(this, str2, new p(this, str4));
        sVar.setMapParams(map);
        sVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(int i) {
        Intent intent = new Intent(getContext(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, i);
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        getContext().startActivity(intent);
    }

    private void a(Relationship relationship, String str) {
        Intent intent = new Intent("QIU_YOU_RELATION_CHANGED");
        intent.putExtra("userId", str);
        intent.putExtra(ConversationActivity.RELATIONSHIP, relationship);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }
}
