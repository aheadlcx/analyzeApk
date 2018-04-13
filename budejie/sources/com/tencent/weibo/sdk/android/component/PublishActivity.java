package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PublishActivity extends Activity implements OnClickListener, HttpCallback {
    private String accessToken;
    private ImageButton button_camera;
    private ImageButton button_conversation;
    private Button button_esc;
    private ImageButton button_friend;
    private ImageButton button_location;
    private Button button_send;
    private Context context;
    private ProgressDialog dialog;
    private EditText editText_text;
    private String edstring = "";
    private FrameLayout frameLayout_big;
    private FrameLayout frameLayout_icon;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 5) {
                PublishActivity.this.frameLayout_big.setVisibility(0);
                PublishActivity.this.frameLayout_icon.setVisibility(0);
                return;
            }
            if (i == 0) {
                PublishActivity.this.popupWindow.showAsDropDown(PublishActivity.this.layout_set);
                InputMethodManager inputMethodManager = (InputMethodManager) PublishActivity.this.getSystemService("input_method");
                Log.d("alive", new StringBuilder(String.valueOf(inputMethodManager.isActive())).toString());
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(PublishActivity.this.editText_text.getWindowToken(), 0);
                    Log.d("alive", new StringBuilder(String.valueOf(inputMethodManager.isActive())).toString());
                }
            }
            if (i == 10) {
                PublishActivity.this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon_hover2x", PublishActivity.this));
            }
            if (i == 15) {
                Toast.makeText(PublishActivity.this, "定位失败", 0).show();
                PublishActivity.this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon2x", PublishActivity.this));
            }
        }
    };
    private ImageView imageView_big;
    private ImageView imageView_bound;
    private ImageView imageView_delete;
    private ImageView imageView_icon;
    private LinearLayout layout_big_delete;
    private LinearLayout layout_imagebound;
    private LinearLayout layout_set;
    private Map<String, String> location;
    private int[] lyout = new int[2];
    private Bitmap mBitmap = null;
    private Location mLocation;
    private PopupWindow popupWindow;
    private TextView textView_num;
    private LinearLayout viewroot;
    private WeiboAPI weiboAPI;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.accessToken = Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN");
        if (this.accessToken == null || "".equals(this.accessToken)) {
            Toast.makeText(this, "请先授权", 0).show();
            finish();
            return;
        }
        this.context = getApplicationContext();
        this.weiboAPI = new WeiboAPI(new AccountModel(this.accessToken));
        this.lyout[0] = BackGroudSeletor.getdrawble("test2x", this).getMinimumWidth();
        this.lyout[1] = BackGroudSeletor.getdrawble("test2x", this).getMinimumHeight();
        LinearLayout linearLayout = (LinearLayout) initview();
        this.dialog = new ProgressDialog(this);
        this.dialog.setMessage("正在发送请稍后......");
        setContentView(linearLayout);
        setonclick();
        new Timer().schedule(new TimerTask() {
            public void run() {
                ((InputMethodManager) PublishActivity.this.getSystemService("input_method")).showSoftInput(PublishActivity.this.editText_text, 2);
            }
        }, 400);
    }

    protected void onResume() {
        super.onResume();
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (this.popupWindow == null || !this.popupWindow.isShowing()) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    inputMethodManager.showSoftInput(PublishActivity.this.editText_text, 2);
                }
            }, 400);
        } else {
            Log.d("mkl", new StringBuilder(String.valueOf(inputMethodManager.isActive())).toString());
            inputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
        }
        if (this.location != null) {
            this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon_hover2x", this));
        } else {
            this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon2x", this));
        }
    }

    private View initview() {
        this.viewroot = new LinearLayout(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        this.viewroot.setLayoutParams(layoutParams);
        this.viewroot.setOrientation(1);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams4);
        relativeLayout.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", getApplication()));
        relativeLayout.setGravity(0);
        this.button_esc = new Button(this);
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.topMargin = 10;
        layoutParams3.leftMargin = 10;
        layoutParams3.bottomMargin = 10;
        this.button_esc.setLayoutParams(layoutParams3);
        this.button_esc.setText("取消");
        this.button_esc.setClickable(true);
        this.button_esc.setId(ReaderCallback.HIDDEN_BAR);
        this.button_esc.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"quxiao_btn2x", "quxiao_btn_hover"}, this));
        this.button_send = new Button(this);
        layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.topMargin = 10;
        layoutParams3.rightMargin = 10;
        layoutParams3.bottomMargin = 10;
        this.button_send.setLayoutParams(layoutParams3);
        LinearLayout linearLayout = new LinearLayout(this);
        layoutParams4 = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        linearLayout.setLayoutParams(layoutParams4);
        this.button_send.setText("发送");
        this.button_send.setClickable(true);
        this.button_send.setId(ReaderCallback.SHOW_BAR);
        this.button_send.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"sent_btn_22x", "sent_btn_hover"}, this));
        relativeLayout.addView(this.button_esc);
        relativeLayout.addView(this.button_send);
        View linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(-1);
        linearLayout2.requestFocus();
        this.editText_text = new EditText(this);
        this.editText_text.setBackgroundColor(-1);
        this.editText_text.setMaxLines(4);
        this.editText_text.setMinLines(4);
        this.editText_text.setMinEms(4);
        this.editText_text.setMaxEms(4);
        this.editText_text.setFocusable(true);
        this.editText_text.requestFocus();
        this.editText_text.setText(this.edstring);
        this.editText_text.setSelection(this.edstring.length());
        this.editText_text.setScrollbarFadingEnabled(true);
        this.editText_text.setGravity(48);
        this.editText_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.editText_text.setId(ReaderCallback.COPY_SELECT_TEXT);
        this.frameLayout_icon = new FrameLayout(this);
        this.frameLayout_icon.setLayoutParams(layoutParams2);
        View linearLayout3 = new LinearLayout(this);
        linearLayout3.setGravity(21);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(54, 45));
        linearLayout3.setPadding(0, 0, 2, 0);
        this.imageView_icon = new ImageView(this);
        this.imageView_icon.setId(ReaderCallback.SEARCH_SELECT_TEXT);
        this.imageView_bound = new ImageView(this);
        this.imageView_bound.setId(ReaderCallback.READER_TOAST);
        this.imageView_bound.setLayoutParams(new LinearLayout.LayoutParams(54, 45));
        this.imageView_icon.setLayoutParams(new LinearLayout.LayoutParams(33, 33));
        this.imageView_bound.setImageDrawable(BackGroudSeletor.getdrawble("composeimageframe", this));
        this.frameLayout_icon.setVisibility(8);
        linearLayout3.addView(this.imageView_icon);
        this.frameLayout_icon.addView(linearLayout3);
        this.frameLayout_icon.addView(this.imageView_bound);
        linearLayout2.addView(this.editText_text);
        linearLayout2.addView(this.frameLayout_icon);
        this.layout_set = new LinearLayout(this);
        this.layout_set.setLayoutParams(layoutParams);
        this.layout_set.setBackgroundDrawable(BackGroudSeletor.getdrawble("icon_bg2x", this));
        this.layout_set.setOrientation(0);
        this.layout_set.setGravity(16);
        this.layout_set.setPadding(10, 0, 30, 0);
        View linearLayout4 = new LinearLayout(this);
        linearLayout4.setOrientation(0);
        linearLayout4.setLayoutParams(layoutParams4);
        linearLayout3 = new LinearLayout(this);
        linearLayout3.setGravity(1);
        linearLayout3.setLayoutParams(layoutParams4);
        View linearLayout5 = new LinearLayout(this);
        linearLayout5.setGravity(1);
        linearLayout5.setLayoutParams(layoutParams4);
        View linearLayout6 = new LinearLayout(this);
        linearLayout6.setGravity(1);
        linearLayout6.setLayoutParams(layoutParams4);
        View linearLayout7 = new LinearLayout(this);
        linearLayout7.setGravity(1);
        linearLayout7.setLayoutParams(layoutParams4);
        this.button_friend = new ImageButton(this);
        this.button_friend.setLayoutParams(layoutParams2);
        this.button_friend.setId(ReaderCallback.SHOW_DIALOG);
        this.button_conversation = new ImageButton(this);
        this.button_conversation.setLayoutParams(layoutParams2);
        this.button_conversation.setId(5007);
        this.button_camera = new ImageButton(this);
        this.button_camera.setLayoutParams(layoutParams2);
        this.button_camera.setId(ReaderCallback.READER_PDF_LIST);
        this.button_location = new ImageButton(this);
        this.button_location.setLayoutParams(layoutParams2);
        this.button_location.setId(ReaderCallback.READER_PPT_PLAY_MODEL);
        this.button_friend.setBackgroundDrawable(BackGroudSeletor.getdrawble("haoyou_icon2x", this));
        this.button_conversation.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"huati_icon2x", "huati_icon_hover2x"}, this));
        this.button_camera.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"pic_icon2x", "pic_icon_hover2x"}, this));
        this.button_location.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"dingwei_icon2x", "dingwei_icon_hover2x"}, this));
        linearLayout3.addView(this.button_friend);
        linearLayout4.addView(linearLayout3);
        linearLayout5.addView(this.button_conversation);
        linearLayout4.addView(linearLayout5);
        linearLayout6.addView(this.button_camera);
        linearLayout4.addView(linearLayout6);
        linearLayout7.addView(this.button_location);
        linearLayout4.addView(linearLayout7);
        this.textView_num = new TextView(this);
        this.textView_num.setText("140");
        this.textView_num.setTextColor(Color.parseColor("#999999"));
        this.textView_num.setGravity(5);
        this.textView_num.setLayoutParams(layoutParams4);
        this.textView_num.setId(ReaderCallback.READER_TXT_READING_MODEL);
        this.textView_num.setWidth(40);
        View linearLayout8 = new LinearLayout(this);
        linearLayout8.setLayoutParams(layoutParams4);
        linearLayout8.setGravity(21);
        linearLayout8.addView(this.textView_num);
        this.layout_set.addView(linearLayout4);
        this.layout_set.addView(linearLayout8);
        linearLayout4 = new LinearLayout(this);
        linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        linearLayout4.setGravity(17);
        linearLayout4.setBackgroundDrawable(BackGroudSeletor.getdrawble("bg", this));
        this.frameLayout_big = new FrameLayout(this);
        this.frameLayout_big.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        this.frameLayout_big.setPadding(10, 10, 0, 0);
        this.layout_imagebound = new LinearLayout(this);
        this.layout_imagebound.setPadding(2, 2, 2, 2);
        this.layout_imagebound.setBackgroundDrawable(BackGroudSeletor.getdrawble("pic_biankuang2x", this));
        this.layout_big_delete = new LinearLayout(this);
        this.layout_big_delete.setLayoutParams(new LinearLayout.LayoutParams(getarea(this.lyout)[0] + 10, getarea(this.lyout)[1] + 10));
        this.layout_imagebound.setGravity(17);
        this.layout_imagebound.setId(ReaderCallback.INSTALL_QB);
        this.layout_imagebound.setLayoutParams(new LinearLayout.LayoutParams(getarea(this.lyout)[0], getarea(this.lyout)[1]));
        this.imageView_big = new ImageView(this);
        this.imageView_big.setId(ReaderCallback.READER_PLUGIN_STATUS);
        this.layout_imagebound.addView(this.imageView_big);
        this.imageView_delete = new ImageView(this);
        this.imageView_delete.setId(ReaderCallback.READER_PLUGIN_CANLOAD);
        this.imageView_delete.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.imageView_delete.setImageDrawable(BackGroudSeletor.getdrawble(BoxingVoteBean.BOXING_VOTE_STATE_CLOSE, this));
        this.layout_big_delete.addView(this.imageView_delete);
        this.frameLayout_big.addView(this.layout_imagebound);
        this.frameLayout_big.addView(this.layout_big_delete);
        this.frameLayout_big.setVisibility(8);
        linearLayout4.addView(this.frameLayout_big);
        this.viewroot.addView(relativeLayout);
        this.viewroot.addView(linearLayout2);
        this.viewroot.addView(this.layout_set);
        this.viewroot.addView(linearLayout4);
        return this.viewroot;
    }

    private void setonclick() {
        this.button_esc.setOnClickListener(this);
        this.button_send.setOnClickListener(this);
        this.editText_text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                try {
                    Log.d("contentafter", new StringBuilder(String.valueOf(charSequence.toString().getBytes("gbk").length)).toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                try {
                    PublishActivity.this.edstring = editable.toString();
                    CharSequence stringBuilder = new StringBuilder(String.valueOf(140 - (editable.toString().getBytes("gbk").length / 2))).toString();
                    Log.d("contentafter", stringBuilder);
                    PublishActivity.this.textView_num.setText(stringBuilder);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        this.imageView_bound.setOnClickListener(this);
        this.imageView_delete.setOnClickListener(this);
        this.button_friend.setOnClickListener(this);
        this.button_conversation.setOnClickListener(this);
        this.button_camera.setOnClickListener(this);
        this.button_location.setOnClickListener(this);
    }

    public void onClick(View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        Intent intent;
        switch (view.getId()) {
            case ReaderCallback.HIDDEN_BAR /*5001*/:
                inputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
                finish();
                return;
            case ReaderCallback.SHOW_BAR /*5002*/:
                String editable = this.editText_text.getText().toString();
                if ("".equals(editable) && this.frameLayout_icon.getVisibility() == 8) {
                    Toast.makeText(this, "无内容发送", 0).show();
                    return;
                }
                if (!(this.dialog == null || this.dialog.isShowing())) {
                    this.dialog.show();
                }
                if (Integer.parseInt(this.textView_num.getText().toString()) < 0) {
                    Toast.makeText(this, "请重新输入少于140个字的内容", 0).show();
                    return;
                }
                double d = 0.0d;
                double d2 = 0.0d;
                if (this.mLocation != null) {
                    d = this.mLocation.getLongitude();
                    d2 = this.mLocation.getLatitude();
                }
                if (!this.frameLayout_icon.isShown()) {
                    this.weiboAPI.addWeibo(this.context, editable, "json", d, d2, 0, 0, this, null, 4);
                    return;
                } else if (this.frameLayout_icon.getVisibility() == 0) {
                    this.weiboAPI.addPic(this.context, editable, "json", d, d2, this.mBitmap, 0, 0, this, null, 4);
                    return;
                } else {
                    return;
                }
            case ReaderCallback.READER_TOAST /*5005*/:
                inputMethodManager.toggleSoftInput(0, 2);
                return;
            case ReaderCallback.SHOW_DIALOG /*5006*/:
                inputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
                intent = new Intent();
                intent.setClass(this, FriendActivity.class);
                startActivityForResult(intent, ReaderCallback.SHOW_DIALOG);
                return;
            case 5007:
                inputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
                intent = new Intent();
                intent.setClass(this, ConversationActivity.class);
                startActivityForResult(intent, 5007);
                return;
            case ReaderCallback.READER_PDF_LIST /*5008*/:
                if (this.popupWindow == null || !this.popupWindow.isShowing()) {
                    this.popupWindow = new PopupWindow(showView(), -1, -1);
                    this.popupWindow.setTouchable(true);
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            Message obtainMessage = PublishActivity.this.handler.obtainMessage();
                            obtainMessage.what = 0;
                            PublishActivity.this.handler.sendMessage(obtainMessage);
                        }
                    }, 500);
                    return;
                }
                this.popupWindow.dismiss();
                if (inputMethodManager.isActive()) {
                    inputMethodManager.toggleSoftInput(0, 2);
                    return;
                }
                return;
            case ReaderCallback.READER_PPT_PLAY_MODEL /*5009*/:
                new Thread(new Runnable() {
                    public void run() {
                        Looper.prepare();
                        Message obtainMessage = PublishActivity.this.handler.obtainMessage();
                        obtainMessage.what = 15;
                        if (PublishActivity.this.mLocation == null) {
                            PublishActivity.this.mLocation = Util.getLocation(PublishActivity.this.context);
                            if (PublishActivity.this.mLocation != null) {
                                obtainMessage.what = 10;
                            }
                        }
                        PublishActivity.this.handler.sendMessage(obtainMessage);
                        Looper.loop();
                    }
                }).start();
                return;
            case ReaderCallback.READER_PLUGIN_CANLOAD /*5013*/:
                this.frameLayout_icon.setVisibility(4);
                this.frameLayout_big.setVisibility(8);
                return;
            case ReaderCallback.READER_PLUGIN_DOWNLOADING /*5014*/:
                this.edstring = this.editText_text.getText().toString();
                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), ErrorCode.SERVER_SESSIONSTATUS);
                return;
            case ReaderCallback.READER_PLUGIN_COMMAND_FIXSCREEN /*5015*/:
                this.edstring = this.editText_text.getText().toString();
                startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), 1000);
                return;
            case ReaderCallback.READER_PLUGIN_RES_FIXSCREEN_NORMAL /*5016*/:
                if (this.popupWindow != null && this.popupWindow.isShowing()) {
                    this.popupWindow.dismiss();
                    this.editText_text.requestFocus();
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            if (inputMethodManager.isActive()) {
                                inputMethodManager.toggleSoftInput(0, 2);
                            }
                        }
                    }, 100);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private View showView() {
        View linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setBackgroundDrawable(BackGroudSeletor.getdrawble("bg", this));
        linearLayout.setOrientation(1);
        linearLayout.setPadding(50, 50, 50, 50);
        linearLayout.setGravity(17);
        linearLayout.requestFocus();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setPadding(0, 0, 0, 0);
        View linearLayout3 = new LinearLayout(this);
        linearLayout3.setLayoutParams(layoutParams);
        linearLayout3.setPadding(0, 10, 0, 30);
        LinearLayout linearLayout4 = new LinearLayout(this);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        View button = new Button(this);
        button.setId(ReaderCallback.READER_PLUGIN_DOWNLOADING);
        button.setOnClickListener(this);
        button.setLayoutParams(layoutParams);
        button.setText("拍照");
        String[] strArr = new String[]{"btn1_", "btn1_hover_"};
        button.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(strArr, this));
        View button2 = new Button(this);
        button2.setId(ReaderCallback.READER_PLUGIN_COMMAND_FIXSCREEN);
        button2.setOnClickListener(this);
        button2.setLayoutParams(layoutParams);
        button2.setText("相册");
        button2.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(strArr, this));
        View button3 = new Button(this);
        button3.setId(ReaderCallback.READER_PLUGIN_RES_FIXSCREEN_NORMAL);
        button3.setOnClickListener(this);
        button3.setLayoutParams(layoutParams);
        button3.setText("取消");
        button3.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"btn2_", "btn1_hover_"}, this));
        linearLayout3.addView(button2);
        linearLayout.addView(button);
        linearLayout.addView(linearLayout3);
        linearLayout.addView(button3);
        return linearLayout;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Bitmap decodeStream;
        if (i == 1000 && i2 == -1 && intent != null) {
            String[] strArr = new String[]{"_data"};
            Cursor query = getContentResolver().query(intent.getData(), strArr, null, null, null);
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex(strArr[0]));
            Log.d("path", new StringBuilder(String.valueOf(string)).toString());
            int[] iArr = new int[2];
            try {
                InputStream fileInputStream = new FileInputStream(string);
                Options options = new Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = 6;
                decodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                decodeStream.compress(CompressFormat.JPEG, 100, new ByteArrayOutputStream());
                this.mBitmap = decodeStream;
                this.lyout[0] = decodeStream.getWidth();
                this.lyout[1] = decodeStream.getHeight();
                setContentView(initview());
                setonclick();
                this.imageView_icon.setImageDrawable(new BitmapDrawable(decodeStream));
                this.imageView_big.setImageDrawable(new BitmapDrawable(decodeStream));
                this.frameLayout_icon.setVisibility(0);
                this.frameLayout_big.setVisibility(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            query.close();
            if (this.popupWindow != null && this.popupWindow.isShowing()) {
                this.popupWindow.dismiss();
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        InputMethodManager inputMethodManager = (InputMethodManager) PublishActivity.this.getSystemService("input_method");
                        Log.d("mks", new StringBuilder(String.valueOf(inputMethodManager.isActive())).toString());
                        inputMethodManager.toggleSoftInput(0, 2);
                        Message obtainMessage = PublishActivity.this.handler.obtainMessage();
                        obtainMessage.what = 5;
                        PublishActivity.this.handler.sendMessage(obtainMessage);
                    }
                }, 100);
            }
        } else if (i == ErrorCode.SERVER_SESSIONSTATUS && i2 == -1 && intent != null) {
            if (this.popupWindow != null && this.popupWindow.isShowing()) {
                this.popupWindow.dismiss();
            }
            decodeStream = (Bitmap) intent.getExtras().get("data");
            sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStorageDirectory())));
            decodeStream.compress(CompressFormat.PNG, 100, new ByteArrayOutputStream());
            this.mBitmap = decodeStream;
            this.lyout[0] = decodeStream.getWidth();
            this.lyout[1] = decodeStream.getHeight();
            setContentView(initview());
            setonclick();
            this.imageView_icon.setImageDrawable(new BitmapDrawable(decodeStream));
            this.imageView_big.setImageDrawable(new BitmapDrawable(decodeStream));
            this.frameLayout_icon.setVisibility(0);
            this.frameLayout_big.setVisibility(0);
        } else if (i == 5007 && i2 == -1 && intent != null) {
            this.edstring += intent.getStringExtra("conversation");
            this.editText_text.setText(this.edstring);
            this.editText_text.setSelection(this.edstring.length());
        } else if (i == ReaderCallback.SHOW_DIALOG && i2 == -1 && intent != null) {
            this.edstring += "@" + intent.getStringExtra("firend");
            this.editText_text.setText(this.edstring);
            this.editText_text.setSelection(this.edstring.length());
        }
    }

    public void onResult(Object obj) {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (obj != null) {
            ModelResult modelResult = (ModelResult) obj;
            if (modelResult.isExpires()) {
                Toast.makeText(this, modelResult.getError_message(), 0).show();
            } else if (modelResult.isSuccess()) {
                Toast.makeText(this, "发送成功", 4000).show();
                Log.d("发送成功", obj.toString());
                finish();
            } else {
                Toast.makeText(this, ((ModelResult) obj).getError_message(), 4000).show();
            }
        }
    }

    private int[] getarea(int[] iArr) {
        int[] iArr2 = new int[2];
        if (iArr != null) {
            if (iArr[0] > iArr[1] && iArr[0] >= 300) {
                iArr2[0] = 300;
                iArr2[1] = (int) ((((float) iArr[1]) / ((float) iArr[0])) * 300.0f);
            } else if (iArr[0] > iArr[1] && iArr[0] < 300) {
                iArr2[0] = iArr[0];
                iArr2[1] = iArr[1];
            } else if (iArr[0] < iArr[1] && iArr[1] >= 300) {
                iArr2[0] = (int) ((((float) iArr[0]) / ((float) iArr[1])) * 300.0f);
                iArr2[1] = 300;
            } else if (iArr[0] < iArr[1] && iArr[0] < 300) {
                iArr2[0] = iArr[0];
                iArr2[1] = iArr[1];
            } else if (iArr[0] == iArr[1] && iArr[0] >= 300) {
                iArr2[0] = 300;
                iArr2[1] = 300;
            } else if (iArr[0] == iArr[1] && iArr[0] < 300) {
                iArr2[0] = iArr[0];
                iArr2[1] = iArr[1];
            }
        }
        Log.d("myarea", iArr2[0] + "....." + iArr2[1]);
        return iArr2;
    }

    public Bitmap zoomImage(Bitmap bitmap, double d, double d2) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) d) / width, ((float) d2) / height);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
    }
}
