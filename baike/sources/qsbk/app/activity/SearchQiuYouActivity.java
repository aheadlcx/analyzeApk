package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.readystatesoftware.systembartint.SystemBarTintHelper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.QiuYouAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.fragments.ShareToImType;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.EventWindow;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class SearchQiuYouActivity extends StatFragmentActivity implements OnItemClickListener, PtrListener {
    public static final String EXTRA_CACHE_DATA = "_cache_data";
    public static final String EXTRA_SHARED = "_shared";
    public static final String EXTRA_START_COVERSATION = "_start_coversation";
    public static final String INTENT_FILTER_SHARED_OK = "_shared_ok";
    private LocalBroadcastManager a = null;
    private BroadcastReceiver b;
    private ProgressBar c;
    private TextView d;
    private EditText e;
    private ImageView f;
    private ImageView g;
    private PtrLayout h;
    private ListView i;
    private ArrayList<BaseUserInfo> j;
    private QiuYouAdapter k;
    private HttpTask l;
    private int m = 1;
    private boolean n = false;
    private Bundle o;
    private ArrayList<BaseUserInfo> p = new ArrayList();
    private boolean q;
    private List<BaseUserInfo> r = new ArrayList();

    public static final void launch(Context context) {
        launch(context, null);
    }

    public static final void launch(Context context, ArrayList<BaseUserInfo> arrayList) {
        launch(context, arrayList, false);
    }

    public static final void launch(Context context, ArrayList<BaseUserInfo> arrayList, boolean z) {
        if (context != null) {
            Intent intent = new Intent(context, SearchQiuYouActivity.class);
            intent.putExtra(EXTRA_CACHE_DATA, arrayList);
            intent.putExtra(EXTRA_START_COVERSATION, z);
            context.startActivity(intent);
        }
    }

    public static final void launch4Shared(Context context, ArrayList<BaseUserInfo> arrayList, Bundle bundle) {
        if (context != null) {
            Intent intent = new Intent(context, SearchQiuYouActivity.class);
            intent.putExtra(EXTRA_CACHE_DATA, arrayList);
            intent.putExtra(EXTRA_SHARED, bundle);
            if (!bundle.getBoolean("fromAt")) {
                context.startActivity(intent);
            } else if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, 87);
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        BaseUserInfo baseUserInfo = (BaseUserInfo) this.k.getItem(i - this.i.getHeaderViewsCount());
        if (this.n) {
            a((Context) this, baseUserInfo);
        } else if (this.o != null) {
            this.q = this.o.getBoolean("isFromCircleTopic", false);
            if (this.o.getBoolean("fromAt")) {
                chooseUserInfo(baseUserInfo);
            } else {
                a(baseUserInfo);
            }
        } else {
            MyInfoActivity.launch(this, baseUserInfo.userId, MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(5, baseUserInfo.userId, "来自糗友搜索"));
        }
    }

    public void chooseUserInfo(BaseUserInfo baseUserInfo) {
        Intent intent = new Intent();
        intent.putExtra(QsbkDatabase.USER_TABLE_NAME, baseUserInfo);
        setResult(-1, intent);
        finish();
    }

    private void a(Context context, BaseUserInfo baseUserInfo) {
        Intent intent = new Intent(context, ConversationActivity.class);
        QsbkApp.getInstance();
        intent.putExtra("user_id", QsbkApp.currentUser.userId);
        intent.putExtra("to_id", baseUserInfo.userId);
        intent.putExtra(IMChatBaseActivity.TO_ICON, baseUserInfo.userIcon);
        intent.putExtra(IMChatBaseActivity.TO_NICK, baseUserInfo.userName);
        intent.putExtra(ConversationActivity.RELATIONSHIP, baseUserInfo.relationship);
        intent.putExtra("source", new IMChatMsgSource(8, baseUserInfo.userId, "来自糗友圈").encodeToJsonObject().toString());
        startActivity(intent);
    }

    private void a(BaseUserInfo baseUserInfo) {
        CharSequence charSequence = "确定发给\n" + baseUserInfo.userName;
        Builder builder = new Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.share_dialog, null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(charSequence);
        builder.setView(inflate);
        builder.setPositiveButton(R.string.confirm, new abr(this, baseUserInfo));
        builder.setNegativeButton(R.string.cancel, new abt(this));
        builder.create().show();
    }

    private void b(BaseUserInfo baseUserInfo) {
        int i = this.o.getInt("share_type", ShareToImType.TYPE_ARTICLE.getValue());
        ContactListItem contactListItem = new ContactListItem();
        contactListItem.id = baseUserInfo.userId;
        contactListItem.name = baseUserInfo.userName;
        contactListItem.icon = baseUserInfo.userIcon;
        String makeMsgData;
        String qiushiShareSummary;
        if (i == ShareToImType.TYPE_CIRCLE_ARTICLE.getValue()) {
            makeMsgData = ShareUtils.makeMsgData(this.o);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.o);
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiuyouCircleShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else if (i == ShareToImType.TYPE_LIVE.getValue()) {
            QsbkCommonVideo qsbkCommonVideo = (QsbkCommonVideo) this.o.getSerializable(EventWindow.JUMP_LIVE_ROOM);
            JSONObject jSONObject = new JSONObject();
            qiushiShareSummary = qsbkCommonVideo.author.name + "正在直播，颜值爆表~快来一起看！" + qsbkCommonVideo.content;
            try {
                jSONObject.put("content", qiushiShareSummary);
                jSONObject.put("live_id", qsbkCommonVideo.live_id);
                jSONObject.put("pic_url", qsbkCommonVideo.getPicUrl());
                jSONObject.put("title", qiushiShareSummary);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            makeMsgData = jSONObject.toString();
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendLiveShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else if (i == ShareToImType.TYPE_WEB.getValue() || i == ShareToImType.TYPE_LIVE_ACTIVITY.getValue() || i == ShareToImType.TYPE_NEWS.getValue()) {
            ShareMsgItem shareMsgItem = (ShareMsgItem) this.o.getSerializable("share_item");
            makeMsgData = shareMsgItem.toJson().toString();
            qiushiShareSummary = "";
            if (i == ShareToImType.TYPE_WEB.getValue()) {
                qiushiShareSummary = shareMsgItem.content;
            } else if (i == ShareToImType.TYPE_LIVE_ACTIVITY.getValue()) {
                qiushiShareSummary = shareMsgItem.title;
            } else {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("title", shareMsgItem.title);
                    jSONObject2.put("image_url", shareMsgItem.imageUrl);
                    jSONObject2.put("news_id", shareMsgItem.news_id);
                    makeMsgData = jSONObject2.toString();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                qiushiShareSummary = shareMsgItem.title;
            }
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendWebShare(contactListItem, makeMsgData, qiushiShareSummary, null, true, i);
        } else if (i == ShareToImType.TYPE_QSJX.getValue()) {
            Qsjx qsjx = (Qsjx) this.o.getSerializable(QYQShareInfo.TYPE_QSJX);
            if (qsjx != null) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQsjxShare(contactListItem, qsjx, null, true);
            }
        } else if (i == ShareToImType.TYPE_QIUSHI_TOPIC.getValue()) {
            QiushiTopic qiushiTopic = (QiushiTopic) this.o.getSerializable(SplashAdItem.AD_QIUSHI_TOPIC);
            if (qiushiTopic != null) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiushiTopicShare(contactListItem, qiushiTopic, null, true);
            }
        } else if (this.q) {
            makeMsgData = ShareUtils.makeShareMsgData(this.o);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.o);
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendCircleTopicShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else {
            makeMsgData = ShareUtils.makeMsgData(this.o);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.o);
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiushiShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.l != null) {
            this.l.cancel(true);
        }
        if (this.a != null) {
            this.a.unregisterReceiver(this.b);
        }
        if (this.i != null) {
            this.h.setPtrListener(null);
            this.i.setOnItemClickListener(null);
        }
    }

    private void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    public void onRefresh() {
    }

    public void onLoadMore() {
        a(this.e.getText().toString());
    }

    protected void onCreate(Bundle bundle) {
        a();
        requestWindowFeature(1);
        super.onCreate(bundle);
        new SystemBarTintHelper(this).setView(R.layout.activity_search_qiuyou).enableSystembarTint();
        b();
    }

    private void b() {
        this.j = (ArrayList) getIntent().getSerializableExtra(EXTRA_CACHE_DATA);
        this.n = getIntent().getBooleanExtra(EXTRA_START_COVERSATION, false);
        this.o = getIntent().getBundleExtra(EXTRA_SHARED);
        this.c = (ProgressBar) findViewById(R.id.loadingbar);
        this.d = (TextView) findViewById(R.id.title);
        this.e = (EditText) findViewById(R.id.input_name);
        this.f = (ImageView) findViewById(R.id.clear_input);
        this.g = (ImageView) findViewById(R.id.search);
        this.h = (PtrLayout) findViewById(R.id.ptr);
        this.i = (ListView) findViewById(R.id.listview);
        this.h.setRefreshEnable(false);
        this.h.setLoadMoreEnable(false);
        this.i.setOnItemClickListener(this);
        this.h.setPtrListener(this);
        this.i.setOnTouchListener(new abu(this));
        this.k = new QiuYouAdapter(this, new ArrayList(0), false, true, true);
        this.i.setAdapter(this.k);
        this.d.setOnClickListener(new abv(this));
        this.e.setOnKeyListener(new abw(this));
        this.e.addTextChangedListener(new abx(this));
        this.f.setOnClickListener(new aby(this));
        this.g.setOnClickListener(new abz(this));
        this.b = new aca(this);
        this.a = LocalBroadcastManager.getInstance(this);
        this.a.registerReceiver(this.b, new IntentFilter("QIU_YOU_RELATION_CHANGED"));
    }

    private void a(String str, boolean z) {
        this.m = 1;
        this.p.clear();
        if (this.r != null) {
            this.r.clear();
        }
        this.k.replaceItem(this.p);
        this.h.setLoadMoreEnable(false);
        if (!TextUtils.isEmpty(str)) {
            List list = null;
            if (this.j != null) {
                list = a(this.j, str);
                if (list != null && list.size() > 0) {
                    this.p.addAll(list);
                    this.k.replaceItem(this.p);
                    StatService.onEvent(this, "search_qiuyou_rel", "from_local");
                    if (!z) {
                        return;
                    }
                }
            }
            if (z) {
                this.r = list;
                UIHelper.hideKeyboard(this);
                if (TextUtils.isEmpty(str)) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请输入糗友昵称：" + this.e.getText(), Integer.valueOf(0)).show();
                } else if (QsbkApp.currentUser.userName.equals(str)) {
                    ToastAndDialog.makeText(this, "您输入的是自己的昵称").show();
                } else {
                    a(str);
                }
            }
        }
    }

    private List<BaseUserInfo> a(List<BaseUserInfo> list, String str) {
        List<BaseUserInfo> list2 = null;
        if (list != null) {
            List<String> allContainsTextUids = RemarkManager.getRemarkManager().getAllContainsTextUids(str);
            for (BaseUserInfo baseUserInfo : list) {
                List<BaseUserInfo> arrayList;
                if (baseUserInfo.userName.toUpperCase().contains(str.toUpperCase())) {
                    if (list2 == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list2;
                    }
                    arrayList.add(baseUserInfo);
                } else {
                    for (String equals : allContainsTextUids) {
                        if (TextUtils.equals(equals, baseUserInfo.userId)) {
                            if (list2 == null) {
                                arrayList = new ArrayList();
                            } else {
                                arrayList = list2;
                            }
                            arrayList.add(baseUserInfo);
                        } else {
                            arrayList = list2;
                        }
                        list2 = arrayList;
                    }
                    arrayList = list2;
                }
                list2 = arrayList;
            }
        }
        return list2;
    }

    private void a(String str) {
        String str2;
        if (this.m == 1) {
            this.c.setVisibility(0);
        }
        try {
            str2 = Constants.URL_SEARCH_QIUYOU_REL + "?q=" + URLEncoder.encode(str, "UTF-8") + "&page=" + this.m;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str2 = null;
        }
        a(str, str2, null);
        StatService.onEvent(this, "search_qiuyou_rel", "from_server");
    }

    private void a(JSONObject jSONObject) {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if ((optJSONArray == null || optJSONArray.length() == 0) && this.m == 1 && (this.r == null || this.r.size() == 0)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "搜索不到糗友：" + this.e.getText(), Integer.valueOf(0)).show();
            return;
        }
        this.h.setLoadMoreEnable(jSONObject.optBoolean("has_more"));
        if (this.m == 1) {
            this.p.clear();
            if (this.r != null && this.r.size() > 0) {
                this.p.addAll(this.r);
            }
        }
        int length = optJSONArray.length();
        Collection arrayList = new ArrayList(length);
        while (i < length) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            BaseUserInfo baseUserInfo = new BaseUserInfo();
            baseUserInfo.parseBaseInfo(optJSONObject);
            if (this.r == null || !this.r.contains(baseUserInfo)) {
                arrayList.add(baseUserInfo);
            }
            i++;
        }
        this.p.addAll(arrayList);
        this.k.replaceItem(this.p);
    }

    private void a(String str, String str2, Map<String, Object> map) {
        this.l = new HttpTask(str, str2, new abs(this));
        if (map != null) {
            this.l.setMapParams(map);
        }
        this.l.execute(new Void[0]);
    }
}
