package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R$styleable;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.adapter.GalleryAdapter;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ImageInfo;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.util.ArrayList;
import org.json.JSONObject;

public class ReAddActivity extends Activity {
    private String accessToken;
    private WeiboAPI api;
    private EditText content = null;
    private String contentStr = "";
    private Gallery gallery;
    private RelativeLayout galleryLayout = null;
    private ArrayList<ImageInfo> imageList = new ArrayList();
    private LinearLayout layout = null;
    private PopupWindow loadingWindow = null;
    private HttpCallback mCallBack = new HttpCallback() {
        public void onResult(Object obj) {
            ModelResult modelResult = (ModelResult) obj;
            if (modelResult.isExpires()) {
                Toast.makeText(ReAddActivity.this, modelResult.getError_message(), 0).show();
            } else if (modelResult.isSuccess()) {
                Toast.makeText(ReAddActivity.this, "转播成功", 0).show();
                ReAddActivity.this.finish();
            } else {
                Toast.makeText(ReAddActivity.this, modelResult.getError_message(), 0).show();
                ReAddActivity.this.finish();
            }
        }
    };
    private Handler mHandler = null;
    private String musicAuthor = "";
    private String musicPath = "";
    private String musicTitle = "";
    private String picPath = "";
    private ProgressBar progressBar = null;
    private TextView textView_num;
    private HttpCallback videoCallBack = new HttpCallback() {
        public void onResult(Object obj) {
            ModelResult modelResult = (ModelResult) obj;
            if (modelResult != null) {
                if (!modelResult.isExpires() && modelResult.isSuccess()) {
                    try {
                        JSONObject jSONObject = ((JSONObject) modelResult.getObj()).getJSONObject("data");
                        ImageInfo imageInfo = new ImageInfo();
                        imageInfo.setImagePath(jSONObject.getString("minipic"));
                        imageInfo.setImageName(jSONObject.getString("title"));
                        imageInfo.setPlayPath(jSONObject.getString("real"));
                        ReAddActivity.this.imageList.add(imageInfo);
                        ReAddActivity.this.gallery.setAdapter(new GalleryAdapter(ReAddActivity.this.getApplicationContext(), ReAddActivity.this.loadingWindow, ReAddActivity.this.imageList));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (ReAddActivity.this.loadingWindow != null && ReAddActivity.this.loadingWindow.isShowing()) {
                ReAddActivity.this.loadingWindow.dismiss();
            }
        }
    };
    private String videoPath = "";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        BackGroudSeletor.setPix(displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
        this.accessToken = Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN");
        if (this.accessToken == null || "".equals(this.accessToken)) {
            Toast.makeText(this, "请先授权", 0).show();
            finish();
            return;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contentStr = extras.getString("content");
            this.videoPath = extras.getString("video_url");
            this.picPath = extras.getString("pic_url");
            this.musicPath = extras.getString("music_url");
            this.musicTitle = extras.getString("music_title");
            this.musicAuthor = extras.getString("music_author");
        }
        this.api = new WeiboAPI(new AccountModel(this.accessToken));
        setContentView(initLayout());
    }

    public View initLayout() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        this.layout = new LinearLayout(this);
        this.layout.setLayoutParams(layoutParams);
        this.layout.setOrientation(1);
        this.layout.setBackgroundDrawable(BackGroudSeletor.getdrawble("readd_bg", getApplication()));
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", getApplication()));
        relativeLayout.setGravity(0);
        View button = new Button(this);
        button.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"quxiao_btn2x", "quxiao_btn_hover"}, getApplication()));
        button.setText("取消");
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.topMargin = 10;
        layoutParams3.leftMargin = 10;
        layoutParams3.bottomMargin = 10;
        button.setLayoutParams(layoutParams3);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReAddActivity.this.finish();
            }
        });
        relativeLayout.addView(button);
        button = new TextView(this);
        button.setText("转播");
        button.setTextColor(-1);
        button.setTextSize(24.0f);
        layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        button.setLayoutParams(layoutParams3);
        relativeLayout.addView(button);
        button = new Button(this);
        button.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"sent_btn2x", "sent_btn_hover"}, getApplication()));
        button.setText("转播");
        layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.topMargin = 10;
        layoutParams3.rightMargin = 10;
        layoutParams3.bottomMargin = 10;
        button.setLayoutParams(layoutParams3);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReAddActivity.this.reAddWeibo();
            }
        });
        relativeLayout.addView(button);
        View relativeLayout2 = new RelativeLayout(this);
        relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, R$styleable.Theme_Custom_shape_cmt_like4_bg));
        View relativeLayout3 = new RelativeLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(440, -1);
        layoutParams2.addRule(13);
        layoutParams2.topMargin = 50;
        relativeLayout3.setLayoutParams(layoutParams2);
        relativeLayout3.setBackgroundDrawable(BackGroudSeletor.getdrawble("input_bg", getApplication()));
        this.textView_num = new TextView(this);
        this.textView_num.setText(this.contentStr == null ? "140" : String.valueOf(140 - this.contentStr.length()));
        this.textView_num.setTextColor(Color.parseColor("#999999"));
        this.textView_num.setGravity(5);
        this.textView_num.setTextSize(18.0f);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(11, -1);
        layoutParams2.rightMargin = 10;
        this.textView_num.setLayoutParams(layoutParams2);
        relativeLayout3.addView(this.textView_num);
        this.content = new EditText(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(10);
        this.content.setLayoutParams(layoutParams2);
        this.content.setMaxLines(4);
        this.content.setMinLines(4);
        this.content.setScrollbarFadingEnabled(true);
        this.content.setGravity(48);
        this.content.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.content.setText(this.contentStr);
        this.content.setSelection(this.contentStr.length());
        this.content.setBackgroundDrawable(null);
        this.content.addTextChangedListener(new TextWatcher() {
            private int selectionEnd;
            private int selectionStart;
            private CharSequence temp;

            public void afterTextChanged(Editable editable) {
                this.selectionStart = ReAddActivity.this.content.getSelectionStart();
                this.selectionEnd = ReAddActivity.this.content.getSelectionEnd();
                if (this.temp.length() > R$styleable.Theme_Custom_send_btn_text_color) {
                    Toast.makeText(ReAddActivity.this, "最多可输入140字符", 0).show();
                    editable.delete(this.selectionStart - 1, this.selectionEnd);
                    int i = this.selectionStart;
                    ReAddActivity.this.content.setText(editable);
                    ReAddActivity.this.content.setSelection(i);
                    return;
                }
                ReAddActivity.this.textView_num.setText(String.valueOf(140 - editable.length()));
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.temp = charSequence;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        relativeLayout3.addView(this.content);
        relativeLayout2.addView(relativeLayout3);
        this.galleryLayout = new RelativeLayout(this);
        this.galleryLayout.setLayoutParams(layoutParams);
        this.gallery = new Gallery(this);
        layoutParams2 = new RelativeLayout.LayoutParams(303, 203);
        layoutParams2.addRule(14, -1);
        layoutParams2.addRule(10, -1);
        layoutParams2.topMargin = 50;
        this.gallery.setLayoutParams(layoutParams2);
        this.gallery.setBackgroundDrawable(BackGroudSeletor.getdrawble("pic_biankuang2x", getApplication()));
        requestForGallery();
        this.galleryLayout.addView(this.gallery);
        this.layout.addView(relativeLayout);
        this.layout.addView(relativeLayout2);
        if (!(this.picPath == null || "".equals(this.picPath) || this.videoPath == null || "".equals(this.videoPath))) {
            this.layout.addView(this.galleryLayout);
        }
        return this.layout;
    }

    protected void reAddWeibo() {
        this.contentStr = this.content.getText().toString();
        this.api.reAddWeibo(getApplicationContext(), this.contentStr, this.picPath, this.videoPath, this.musicPath, this.musicTitle, this.musicAuthor, this.mCallBack, null, 4);
    }

    public ArrayList<ImageInfo> requestForGallery() {
        if (this.picPath != null) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImagePath(this.picPath);
            this.imageList.add(imageInfo);
        }
        if (this.videoPath != null) {
            imageInfo = new ImageInfo();
            this.api.getVideoInfo(getApplicationContext(), this.videoPath, this.videoCallBack, null, 4);
        }
        return this.imageList;
    }
}
