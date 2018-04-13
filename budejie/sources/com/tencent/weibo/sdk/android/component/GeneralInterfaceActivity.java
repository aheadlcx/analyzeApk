package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;
import cn.v6.sixrooms.constants.CommonInts;
import com.tencent.weibo.sdk.android.api.FriendAPI;
import com.tencent.weibo.sdk.android.api.LbsAPI;
import com.tencent.weibo.sdk.android.api.TimeLineAPI;
import com.tencent.weibo.sdk.android.api.UserAPI;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;

public class GeneralInterfaceActivity extends Activity implements OnClickListener {
    private String accessToken;
    private Button addPic;
    private Button addPicUrl;
    private Button addWeibo;
    private Context context = null;
    private Button deviceStatus;
    private Button errorReport;
    private FriendAPI friendAPI;
    private Button friendAdd;
    private Button friendCheck;
    private Button friendFunsList;
    private Button friendGetIntimateFriend;
    private Button friendIdolList;
    private Button friendMutualList;
    private Button homeTimeLine;
    private Button htTimeLine;
    private double latitude = 0.0d;
    private LbsAPI lbsAPI;
    private Button lbsGetAroundNew;
    private Button lbsGetAroundPeople;
    private PopupWindow loadingWindow = null;
    private double longitude = 0.0d;
    private HttpCallback mCallBack;
    private Location mLocation;
    private ProgressBar progressBar = null;
    private String requestFormat = "json";
    private ScrollView scrollView = null;
    private Button tReList;
    private TimeLineAPI timeLineAPI;
    private UserAPI userAPI;
    private Button userInfo;
    private Button userInfos;
    private Button userOtherInfo;
    private Button userTimeLine;
    private WeiboAPI weiboAPI;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.accessToken = Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN");
        if (this.accessToken == null || "".equals(this.accessToken)) {
            Toast.makeText(this, "请先授权", 0).show();
            finish();
            return;
        }
        AccountModel accountModel = new AccountModel(this.accessToken);
        this.friendAPI = new FriendAPI(accountModel);
        this.timeLineAPI = new TimeLineAPI(accountModel);
        this.weiboAPI = new WeiboAPI(accountModel);
        this.userAPI = new UserAPI(accountModel);
        this.lbsAPI = new LbsAPI(accountModel);
        this.mCallBack = new HttpCallback() {
            public void onResult(Object obj) {
                ModelResult modelResult = (ModelResult) obj;
                if (GeneralInterfaceActivity.this.loadingWindow != null && GeneralInterfaceActivity.this.loadingWindow.isShowing()) {
                    GeneralInterfaceActivity.this.loadingWindow.dismiss();
                }
                if (modelResult != null) {
                    Toast.makeText(GeneralInterfaceActivity.this, "成功", 0).show();
                    Intent intent = new Intent(GeneralInterfaceActivity.this, GeneralDataShowActivity.class);
                    intent.putExtra("data", modelResult.getObj().toString());
                    GeneralInterfaceActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(GeneralInterfaceActivity.this, "发生异常", 0).show();
            }
        };
        this.progressBar = new ProgressBar(this);
        this.loadingWindow = new PopupWindow(this.progressBar, 100, 100);
        this.context = getApplicationContext();
        this.mLocation = Util.getLocation(this.context);
        if (this.mLocation != null) {
            this.longitude = this.mLocation.getLongitude();
            this.latitude = this.mLocation.getLatitude();
        }
        initInterface();
    }

    public void initInterface() {
        this.scrollView = new ScrollView(this);
        View tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new LayoutParams(-1, -1));
        View tableRow = new TableRow(this);
        this.homeTimeLine = new Button(this);
        this.homeTimeLine.setText("主人页时间线");
        this.homeTimeLine.setId(1001);
        this.homeTimeLine.setOnClickListener(this);
        tableRow.addView(this.homeTimeLine);
        this.userTimeLine = new Button(this);
        this.userTimeLine.setText("客人页时间线");
        this.userTimeLine.setId(1002);
        this.userTimeLine.setOnClickListener(this);
        tableRow.addView(this.userTimeLine);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.addWeibo = new Button(this);
        this.addWeibo.setText("普通发表接口");
        this.addWeibo.setId(1003);
        this.addWeibo.setOnClickListener(this);
        tableRow.addView(this.addWeibo);
        this.addPic = new Button(this);
        this.addPic.setText("发表带图微博");
        this.addPic.setId(1004);
        this.addPic.setOnClickListener(this);
        tableRow.addView(this.addPic);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.addPicUrl = new Button(this);
        this.addPicUrl.setText("发表带网络图片微博");
        this.addPicUrl.setId(1005);
        this.addPicUrl.setOnClickListener(this);
        tableRow.addView(this.addPicUrl);
        this.htTimeLine = new Button(this);
        this.htTimeLine.setText("话题时间线");
        this.htTimeLine.setId(1006);
        this.htTimeLine.setOnClickListener(this);
        tableRow.addView(this.htTimeLine);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.userInfo = new Button(this);
        this.userInfo.setText("获取用户信息");
        this.userInfo.setId(1007);
        this.userInfo.setOnClickListener(this);
        tableRow.addView(this.userInfo);
        this.userOtherInfo = new Button(this);
        this.userOtherInfo.setText("获取他人信息");
        this.userOtherInfo.setId(1008);
        this.userOtherInfo.setOnClickListener(this);
        tableRow.addView(this.userOtherInfo);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.userInfos = new Button(this);
        this.userInfos.setText("获取一批人信息");
        this.userInfos.setId(1009);
        this.userInfos.setOnClickListener(this);
        tableRow.addView(this.userInfos);
        this.friendAdd = new Button(this);
        this.friendAdd.setText("收听某个用户");
        this.friendAdd.setId(1010);
        this.friendAdd.setOnClickListener(this);
        tableRow.addView(this.friendAdd);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.friendIdolList = new Button(this);
        this.friendIdolList.setText("获取偶像列表");
        this.friendIdolList.setId(1011);
        this.friendIdolList.setOnClickListener(this);
        tableRow.addView(this.friendIdolList);
        this.friendFunsList = new Button(this);
        this.friendFunsList.setText("获取粉丝列表");
        this.friendFunsList.setId(CommonInts.SENDMSG_RESULT);
        this.friendFunsList.setOnClickListener(this);
        tableRow.addView(this.friendFunsList);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.friendMutualList = new Button(this);
        this.friendMutualList.setText("获取互听列表");
        this.friendMutualList.setId(CommonInts.REPLY_RESULT);
        this.friendMutualList.setOnClickListener(this);
        tableRow.addView(this.friendMutualList);
        this.friendCheck = new Button(this);
        this.friendCheck.setText("验证好友关系");
        this.friendCheck.setId(1014);
        this.friendCheck.setOnClickListener(this);
        tableRow.addView(this.friendCheck);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.tReList = new Button(this);
        this.tReList.setText("转播获取转播列表");
        this.tReList.setId(CommonInts.NUMBER_FORMAT_EXCEPTION);
        this.tReList.setOnClickListener(this);
        tableRow.addView(this.tReList);
        this.friendGetIntimateFriend = new Button(this);
        this.friendGetIntimateFriend.setText("获取最近联系人");
        this.friendGetIntimateFriend.setId(CommonInts.STRING_OUTOFBOUNDS_EXCEPTION);
        this.friendGetIntimateFriend.setOnClickListener(this);
        tableRow.addView(this.friendGetIntimateFriend);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.lbsGetAroundPeople = new Button(this);
        this.lbsGetAroundPeople.setText("获取附近的人");
        this.lbsGetAroundPeople.setId(1017);
        this.lbsGetAroundPeople.setOnClickListener(this);
        tableRow.addView(this.lbsGetAroundPeople);
        this.lbsGetAroundNew = new Button(this);
        this.lbsGetAroundNew.setText("获取身边最新的微博");
        this.lbsGetAroundNew.setId(1018);
        this.lbsGetAroundNew.setOnClickListener(this);
        tableRow.addView(this.lbsGetAroundNew);
        tableLayout.addView(tableRow);
        tableRow = new TableRow(this);
        this.deviceStatus = new Button(this);
        this.deviceStatus.setText("终端状况");
        this.deviceStatus.setId(1019);
        tableRow.addView(this.deviceStatus);
        this.errorReport = new Button(this);
        this.errorReport.setText("错误反馈");
        this.errorReport.setId(1020);
        tableRow.addView(this.errorReport);
        tableLayout.addView(tableRow);
        this.scrollView.addView(tableLayout);
        setContentView(this.scrollView);
    }

    public void onClick(View view) {
        Looper.myQueue().addIdleHandler(new IdleHandler() {
            public boolean queueIdle() {
                GeneralInterfaceActivity.this.loadingWindow.showAtLocation(GeneralInterfaceActivity.this.scrollView, 17, 0, 80);
                return false;
            }
        });
        switch (view.getId()) {
            case 1001:
                this.timeLineAPI.getHomeTimeLine(this.context, 0, 0, 30, 0, 0, this.requestFormat, this.mCallBack, null, 4);
                return;
            case 1002:
                this.timeLineAPI.getUserTimeLine(this.context, 0, 0, 30, 0, "api_weibo", null, 0, 0, this.requestFormat, this.mCallBack, null, 4);
                return;
            case 1003:
                this.weiboAPI.addWeibo(this.context, "hello world !", this.requestFormat, this.longitude, this.latitude, 0, 0, this.mCallBack, null, 4);
                return;
            case 1004:
                try {
                    this.weiboAPI.addPic(this.context, "call telephone OKK", this.requestFormat, this.longitude, this.latitude, BitmapFactory.decodeStream(this.context.getAssets().open("logo")), 0, 0, this.mCallBack, null, 4);
                    return;
                } catch (Exception e) {
                    return;
                }
            case 1005:
                this.weiboAPI.addPicUrl(this.context, "y phone ", this.requestFormat, this.longitude, this.latitude, "http://t2.qpic.cn/mblogpic/9c7e34358608bb61a696/2000", 0, 0, this.mCallBack, null, 4);
                return;
            case 1006:
                this.timeLineAPI.getHTTimeLine(this.context, this.requestFormat, 30, "0", "0", 0, 0, "加油", "0", 1, 128, this.mCallBack, null, 4);
                return;
            case 1007:
                this.userAPI.getUserInfo(this.context, this.requestFormat, this.mCallBack, null, 4);
                return;
            case 1008:
                this.userAPI.getUserOtherInfo(this.context, this.requestFormat, "api_weibo", null, this.mCallBack, null, 4);
                return;
            case 1009:
                this.userAPI.getUserInfos(this.context, this.requestFormat, "api_weibo", null, this.mCallBack, null, 4);
                return;
            case 1010:
                this.friendAPI.addFriend(this.context, this.requestFormat, "api_weibo", null, this.mCallBack, null, 4);
                return;
            case 1011:
                this.friendAPI.friendIDolList(this.context, this.requestFormat, 30, 0, 1, 0, this.mCallBack, null, 4);
                return;
            case CommonInts.SENDMSG_RESULT /*1012*/:
                this.friendAPI.friendFansList(this.context, this.requestFormat, 30, 0, 1, 0, 0, this.mCallBack, null, 4);
                return;
            case CommonInts.REPLY_RESULT /*1013*/:
                this.friendAPI.getMutualList(this.context, this.requestFormat, "api_weibo", null, 0, 30, 0, this.mCallBack, null, 4);
                return;
            case 1014:
                this.friendAPI.friendCheck(this.context, this.requestFormat, "api_weibo", null, 2, this.mCallBack, null, 4);
                return;
            case CommonInts.NUMBER_FORMAT_EXCEPTION /*1015*/:
                this.weiboAPI.reList(this.context, this.requestFormat, 2, "112714089895346", 0, "0", 30, "0", this.mCallBack, null, 4);
                return;
            case CommonInts.STRING_OUTOFBOUNDS_EXCEPTION /*1016*/:
                this.friendAPI.getIntimateFriends(this.context, this.requestFormat, 30, this.mCallBack, null, 4);
                return;
            case 1017:
                this.lbsAPI.getAroundPeople(this.context, this.requestFormat, this.longitude, this.latitude, "", 20, 0, this.mCallBack, null, 4);
                return;
            case 1018:
                this.lbsAPI.getAroundNew(this.context, this.requestFormat, this.longitude, this.latitude, "", 20, this.mCallBack, null, 4);
                return;
            default:
                return;
        }
    }
}
