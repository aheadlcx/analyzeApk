package qsbk.app.im;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.ImageButton;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditIMImageActivity;
import qsbk.app.activity.ImagesPickerActivity;
import qsbk.app.activity.NoAvailableSpaceActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.ChatListAdapter.UploadedListener;
import qsbk.app.im.CollectEmotion.CollectImageDomain;
import qsbk.app.im.CollectEmotion.CollectionGridView.OnCollectionItemClickListener;
import qsbk.app.im.CollectEmotion.CollectionManager;
import qsbk.app.im.CollectEmotion.CollectionViewPager;
import qsbk.app.im.CollectEmotion.ShowCollectActivity;
import qsbk.app.im.datastore.LatestUsedCollectionStore;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;
import qsbk.app.im.emotion.EmotionManager;
import qsbk.app.im.emotion.EmotionViewPager;
import qsbk.app.im.image.IMImageSize;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.DotView;

public abstract class IMChatBaseActivityEx extends IMChatBaseActivity implements UploadedListener, OnCollectionItemClickListener, IChatMsgListener, IOnConnectHostResp, OnEmotionItemClickListener {
    protected static int C = 1;
    protected static int D = 1;
    protected static int E = 2;
    protected static int F = 3;
    public static final String REMOVE_NOTIFICATION = "REMOVE_NOTIFICATION";
    public static ArrayList<String> downloadUrls = new ArrayList();
    protected Uri Q;
    protected LatestUsedCollectionStore R = null;
    protected String S = null;
    protected long T = 0;
    protected String U;
    protected LocalBroadcastManager V;
    protected int W = 1;
    protected int X = 2;
    protected int Y = 0;
    protected boolean Z = false;
    private final BroadcastReceiver a = new fl(this);
    protected CollectionManager aa;
    protected boolean ab = true;
    protected int ac = 0;
    private final BroadcastReceiver b = new fr(this);
    private ChatMsgListenerWrapper c = null;

    public abstract void hideEmojiAfterClickItem();

    public abstract void hideEmojiAfterDeleteItemOrUploaded();

    public abstract void onDeleteMsg();

    public abstract void onSavePhotoSucc(CollectImageDomain collectImageDomain);

    public abstract void repeatUploaded(ChatMsg chatMsg);

    public abstract ChatMsg sendEmotion(ChatMsgEmotionData chatMsgEmotionData);

    protected static ChatMsgImageData b(String str) {
        return new ChatMsgImageData(str);
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night.Conversation.Collect);
        } else {
            setTheme(R.style.Day.Conversation.Collect);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f();
        if (this.z != null) {
            this.z.addObserver(this.c);
        }
    }

    private void f() {
        if (getIntent().getBooleanExtra(REMOVE_NOTIFICATION, false)) {
            IMNotifyManager.instance().cleanNotification();
        }
    }

    public void startImagePicker(int i) {
        Intent prepareIntent = ImagesPickerActivity.prepareIntent(this, 6);
        prepareIntent.putExtra("KEY_PICK_IAMGE", i);
        startActivityForResult(prepareIntent, 16);
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        DeviceUtils.checkExternalStorageAvailability(this);
        this.V = LocalBroadcastManager.getInstance(this);
        this.V.registerReceiver(this.a, new IntentFilter(NoAvailableSpaceActivity.ACTION_NO_AVAILABLE_SPACE_EXIT));
        this.V.registerReceiver(this.b, new IntentFilter(Constants.ACTION_GET_VOICE_LAISEE_SUCCESS));
        this.c = new ChatMsgListenerWrapper(this, this.y);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.V != null) {
            if (this.a != null) {
                this.V.unregisterReceiver(this.a);
            }
            if (this.b != null) {
                this.V.unregisterReceiver(this.b);
            }
        }
        if (this.z != null) {
            this.z.removeObserver(this.c);
        }
        if (this.g != null) {
            this.g.onDestroy();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Logger.getInstance().debug(getClass().getSimpleName(), " onActivityResult ", String.format("onActivityResult(int requestCode : %s, int resultCode : %s, Intent data : %s)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), intent}));
        if (i == C) {
            if (i2 == D) {
                if (this.S != null) {
                    StringUtils.copyToClipboard(this.S, this);
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "对话内容已复制到粘贴板", Integer.valueOf(0)).show();
                }
                LogUtil.d("copy success");
            } else if (i2 == E) {
                onDeleteMsg();
            } else if (i2 == F && !TextUtils.isEmpty(this.U)) {
                saveImageToLocal(this.U);
            }
        } else if ((i2 != -1 && i2 != 2) || !DeviceUtils.checkExternalStorageAvailability(this)) {
        } else {
            ContactListItem newContactItem;
            if (i == 5) {
                if (i2 == -1) {
                    IMImageSize iMImageSize = (IMImageSize) intent.getParcelableExtra(EditIMImageActivity.EXTRA_OUTPUT_SIZE_INFO_ORIGIN);
                    LogUtil.d("imageSize:" + iMImageSize);
                    ChatMsgImageData chatMsgImageData = new ChatMsgImageData(((Uri) intent.getParcelableExtra(EditIMImageActivity.EXTRA_IMAGE_URI)).toString(), iMImageSize.getOriginWidth(), iMImageSize.getOriginHeight());
                    newContactItem = newContactItem();
                    this.g.appendItem(newContactItem.isGroupMsg() ? this.z.newGroupImageChatMsg(newContactItem, chatMsgImageData, this.s) : this.z.newImageChatMsg(newContactItem, chatMsgImageData, this.s));
                    this.g.notifyDataSetChanged();
                    this.y.postDelayed(new ft(this), 100);
                } else if (i2 == 2) {
                    startImagePicker(this.Y);
                }
            } else if (i == 16) {
                if (i2 == -1) {
                    List<ImageInfo> list = (List) intent.getSerializableExtra("paths");
                    if (list != null) {
                        for (ImageInfo bigImageUrl : list) {
                            ChatMsg newGroupImageChatMsg;
                            Uri parse = Uri.parse(bigImageUrl.getBigImageUrl());
                            Options options = new Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeFile(UriUtil.getRealPathFromUri(getContentResolver(), parse), options);
                            ChatMsgImageData chatMsgImageData2 = new ChatMsgImageData(parse.toString(), options.outWidth, options.outHeight);
                            newContactItem = newContactItem();
                            if (newContactItem.isGroupMsg()) {
                                newGroupImageChatMsg = this.z.newGroupImageChatMsg(newContactItem, chatMsgImageData2, this.s);
                            } else {
                                newGroupImageChatMsg = this.z.newImageChatMsg(newContactItem, chatMsgImageData2, this.s);
                            }
                            this.g.appendItem(newGroupImageChatMsg);
                            this.g.notifyDataSetChanged();
                            this.y.postDelayed(new fu(this), 100);
                        }
                    }
                }
            } else if (i == 300) {
                this.aa.getAll();
                hideEmojiAfterDeleteItemOrUploaded();
            }
        }
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
    }

    public void onMessageReceived(ChatMsg chatMsg) {
    }

    public void onChatMsgStatusChanged(long j, int i) {
        onMsgStateChange(j, i);
    }

    public void onDuplicateConnect(ChatMsg chatMsg) {
        finish();
    }

    public void onConnectStatusChange(int i) {
        try {
            if (this.h != null) {
                this.h.setText(IChatMsgListener.connectString[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onHostCallback(String str) {
        if (!TextUtils.isEmpty(str)) {
            onConnectStatusChange(this.z.getConnectStatus());
        }
    }

    public void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData) {
        this.Z = true;
        LatestUsedCollectionData latestUsedCollectionData = new LatestUsedCollectionData(chatMsgEmotionData);
        latestUsedCollectionData.usedTime = System.currentTimeMillis();
        int check = this.aa.check(latestUsedCollectionData);
        if (check > 0) {
            this.R.update(check, System.currentTimeMillis());
        } else {
            this.R.insert(latestUsedCollectionData);
        }
        ChatMsg sendEmotion = sendEmotion(chatMsgEmotionData);
        if (sendEmotion != null) {
            this.g.appendItem(sendEmotion);
            this.g.notifyDataSetChanged();
            this.y.postDelayed(new fv(this), 200);
        }
    }

    public void onCollectItemClick(int i, LatestUsedCollectionData latestUsedCollectionData) {
        if (latestUsedCollectionData.type == 4) {
            ShowCollectActivity.launch(this, 300);
            return;
        }
        this.Z = true;
        if (latestUsedCollectionData.id > 0) {
            this.R.update(latestUsedCollectionData.id, System.currentTimeMillis());
        }
        if (latestUsedCollectionData.type == 2) {
            ChatMsg sendEmotion = sendEmotion(latestUsedCollectionData.chatMsgEmotionData);
            if (sendEmotion != null) {
                this.g.appendItem(sendEmotion);
                this.g.notifyDataSetChanged();
                this.y.postDelayed(new fw(this), 200);
            }
        } else if (latestUsedCollectionData.type == 1) {
            CollectImageDomain collectImageDomain = latestUsedCollectionData.collectImageDomain;
            r0 = DeviceUtils.getCollectSDPath() + File.separator + collectImageDomain.url;
            Uri fromFile = Uri.fromFile(new File(r0));
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r0, options);
            if (TextUtils.isEmpty(collectImageDomain.netUrl) || this.aa.check(collectImageDomain.netUrl) <= 0) {
                if (options.outWidth <= 0 || options.outHeight <= 0) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "图片保存有误，请删除该图片", Integer.valueOf(1)).show();
                    return;
                }
                r0 = new ChatMsgImageData(fromFile.toString(), options.outWidth, options.outHeight);
            } else if (options.outWidth <= 0 || options.outHeight <= 0) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "图片保存有误，请删除该图片", Integer.valueOf(1)).show();
                return;
            } else {
                r0 = new ChatMsgImageData(collectImageDomain.netUrl, options.outWidth, options.outHeight, true);
            }
            r1 = newContactItem();
            if (r1.isGroupMsg()) {
                r1 = this.z.newGroupImageChatMsg(r1, r0, this.s);
            } else {
                r1 = this.z.newImageChatMsg(r1, r0, this.s);
            }
            if (r0.hasUploaded) {
                repeatUploaded(r1);
            }
            this.g.appendItem(r1);
            this.g.notifyDataSetChanged();
            this.y.postDelayed(new fx(this), 100);
        } else if (latestUsedCollectionData.type == 3) {
            r0 = latestUsedCollectionData.collectImageLocal.localUrl;
            Object obj = latestUsedCollectionData.collectImageLocal.netUrl;
            int i2 = latestUsedCollectionData.collectImageLocal.width;
            int i3 = latestUsedCollectionData.collectImageLocal.height;
            if (r0.startsWith("file://")) {
                r0 = r0.substring(8);
            }
            Uri fromFile2 = Uri.fromFile(new File(r0));
            Options options2 = new Options();
            options2.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r0, options2);
            if (TextUtils.isEmpty(obj)) {
                if (options2.outWidth <= 0 || options2.outHeight <= 0) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "图片保存有误，请删除该图片", Integer.valueOf(1)).show();
                    return;
                }
                r0 = new ChatMsgImageData(fromFile2.toString(), options2.outWidth, options2.outHeight);
            } else if (i2 <= 0 || i3 <= 0) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "图片保存有误，请删除该图片", Integer.valueOf(1)).show();
                return;
            } else {
                r0 = new ChatMsgImageData(obj, i2, i3, true);
            }
            r1 = newContactItem();
            r1 = r1.isGroupMsg() ? this.z.newGroupImageChatMsg(r1, r0, this.s) : this.z.newImageChatMsg(r1, r0, this.s);
            if (r0.hasUploaded) {
                repeatUploaded(r1);
            }
            this.g.appendItem(r1);
            this.g.notifyDataSetChanged();
            this.y.postDelayed(new fy(this), 100);
        }
    }

    public void onCollectItemLongClick(int i, LatestUsedCollectionData latestUsedCollectionData) {
        if (latestUsedCollectionData.id >= 0) {
            new Builder(this).setMessage("确认删除该图片？").setNegativeButton("删除", new fm(this, latestUsedCollectionData)).setPositiveButton("取消", new fz(this)).show();
        }
    }

    public void saveImageToLocal(String str) {
        int i = (hasExits(str) || downloadUrls.contains(str)) ? 1 : 0;
        if (i == 0) {
            new fn(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        } else if (downloadUrls.contains(str)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "图片正在保存...！", Integer.valueOf(0)).show();
        } else {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "图片已保存到收藏！", Integer.valueOf(0)).show();
        }
    }

    public boolean compareToUrl(String str) {
        String MD5 = Md5.MD5(str);
        String collectSDPath = DeviceUtils.getCollectSDPath();
        File file = new File(collectSDPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.isDirectory()) {
            return true;
        }
        String[] list = file.list();
        int i = 0;
        while (i < list.length) {
            if (!new File(collectSDPath + File.separator + list[i]).isDirectory() && list[i].split("\\.")[0].equals(MD5)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean hasExits(String str) {
        for (LatestUsedCollectionData latestUsedCollectionData : this.aa.feachAll()) {
            if (latestUsedCollectionData.type == 1 && latestUsedCollectionData.collectImageDomain.netUrl.equals(str)) {
                return true;
            }
        }
        return false;
    }

    protected void w() {
        l();
        this.j = findViewById(R.id.more);
        this.j.setOnClickListener(new fo(this));
        this.q = (ImageButton) findViewById(R.id.latest);
        this.r = (ImageButton) findViewById(R.id.tuotuo);
        this.q.setBackgroundColor(UIHelper.isNightTheme() ? -15724269 : -1973791);
        this.r.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -328966);
        this.r.setImageResource(UIHelper.isNightTheme() ? R.drawable.emoji_tuotuo_night : R.drawable.emoji_tuotuo);
        this.q.setOnClickListener(new fp(this));
        this.r.setOnClickListener(new fq(this));
        this.n = findViewById(R.id.collecion_viewpager_container);
        this.m = (DotView) findViewById(R.id.collect_dotview);
        this.o = (CollectionViewPager) findViewById(R.id.collect_viewpager);
        this.o.setOnCollectionClickListener(this);
        this.k = findViewById(R.id.emotion_viewpager_container);
        this.l = (EmotionViewPager) findViewById(R.id.emotion_viewpager);
        this.p = (DotView) findViewById(R.id.emotion_dotview);
        this.l.setOnEmotionClickListener(this);
    }

    protected void x() {
        this.O.performClick();
        this.i.setVisibility(0);
        this.n.setVisibility(8);
        this.k.setVisibility(8);
        this.O.setVisibility(0);
        this.K.setVisibility(8);
    }

    protected void y() {
        this.l.setDatas(EmotionManager.getInstance().getAll());
        this.l.setDotView(this.p);
        this.i.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        n();
    }

    protected void z() {
        this.aa.getAll();
        Object feachAll = this.aa.feachAll();
        this.o.setDatas(feachAll);
        this.o.getAdapter().notifyDataSetChanged();
        this.o.setDotView(this.m);
        if (feachAll.size() == 1) {
            this.m.setVisibility(4);
        } else {
            this.m.setVisibility(0);
        }
    }

    protected void A() {
        z();
        this.i.setVisibility(8);
        this.n.setVisibility(0);
        this.k.setVisibility(8);
        n();
    }

    protected void n() {
        super.n();
        if (this.n.getVisibility() != 0 && this.k.getVisibility() != 0) {
            A();
        }
    }
}
