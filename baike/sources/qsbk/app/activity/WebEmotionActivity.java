package qsbk.app.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.R;
import qsbk.app.activity.base.BaseEmotionActivity;
import qsbk.app.cafe.Jsnativeinteraction.ui.WebActivity;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;
import qsbk.app.im.emotion.EmotionViewPager;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.DotView;
import qsbk.app.widget.QiushiEmotionHandler;
import qsbk.app.widget.QiushiEmotionHandler$EmotionData;
import qsbk.app.widget.SizeNotifierRelativeLayout;
import qsbk.app.widget.SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;

public abstract class WebEmotionActivity extends WebActivity implements OnEmotionItemClickListener, SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    protected View a;
    protected EditText b;
    protected View c;
    protected SizeNotifierRelativeLayout d;
    protected InputMethodManager e;
    protected ImageButton f;
    protected ImageButton g;
    protected EmotionViewPager h;
    protected DotView i;
    protected int j;
    private boolean p = false;
    private OnClickListener q = new agk(this);

    private static Map<String, List<ChatMsgEmotionData>> u() {
        Collection<QiushiEmotionHandler$EmotionData> values = QiushiEmotionHandler.getInstance().getAll().values();
        Map<String, List<ChatMsgEmotionData>> linkedHashMap = new LinkedHashMap(1);
        List arrayList = new ArrayList();
        int i = 0;
        for (QiushiEmotionHandler$EmotionData qiushiEmotionHandler$EmotionData : values) {
            int i2 = i + 1;
            ChatMsgEmotionData chatMsgEmotionData = new ChatMsgEmotionData();
            if (i2 % 28 == 0) {
                chatMsgEmotionData.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
            } else {
                chatMsgEmotionData.name = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.key = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.localResource = qiushiEmotionHandler$EmotionData.getResId();
            }
            arrayList.add(chatMsgEmotionData);
            i = i2;
        }
        ChatMsgEmotionData chatMsgEmotionData2 = new ChatMsgEmotionData();
        chatMsgEmotionData2.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
        arrayList.add(chatMsgEmotionData2);
        linkedHashMap.put("emotion_small", arrayList);
        return linkedHashMap;
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        this.a = findViewById(R.id.root);
        a(this.a);
    }

    protected void i() {
        k();
        a(this.j);
        m();
        this.c.setVisibility(0);
        this.f.setVisibility(0);
        this.g.setVisibility(8);
    }

    protected void a(View view) {
        this.e = (InputMethodManager) getSystemService("input_method");
        this.j = SharePreferenceUtils.getSharePreferencesIntValue(BaseEmotionActivity.KEYBOARD_HEIGHT);
        this.c = view.findViewById(R.id.emotions);
        this.f = (ImageButton) view.findViewById(R.id.sendKeyboard);
        this.f.setOnClickListener(this.q);
        this.f.setImageResource(UIHelper.getSendTextResource());
        this.g = (ImageButton) view.findViewById(R.id.sendEmotion);
        this.g.setOnClickListener(this.q);
        this.g.setImageResource(UIHelper.getSendEmotionResource());
        this.h = (EmotionViewPager) view.findViewById(R.id.emotion_viewpager);
        this.i = (DotView) view.findViewById(R.id.emotion_dotview);
        this.h.setOnEmotionClickListener(this);
        this.h.setEmotionType(1);
        this.h.setPerPageCount(28);
        this.h.setRowCount(4);
        this.h.setDatas(u());
        this.h.setDotView(this.i);
        this.b = (EditText) view.findViewById(R.id.addCmtEditText);
        this.b.setOnClickListener(this.q);
        this.b.setOnTouchListener(new agl(this));
        this.d = (SizeNotifierRelativeLayout) view.findViewById(R.id.root);
        this.d.setSizeNotifierRelativeLayoutDelegate(this);
        l();
    }

    protected void j() {
        this.b.requestFocus();
        this.e.showSoftInput(this.b, 1);
    }

    protected void k() {
        getWindow().setSoftInputMode(32);
    }

    protected void l() {
        getWindow().setSoftInputMode(16);
    }

    protected void m() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            this.e.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
        }
    }

    protected boolean n() {
        return this.c.getVisibility() == 0 && this.c.getHeight() > 0;
    }

    protected void a(int i) {
        LayoutParams layoutParams = this.c.getLayoutParams();
        if (i < 0) {
            i = 0;
        }
        layoutParams.height = i;
        this.c.setLayoutParams(layoutParams);
    }

    public void onSizeChanged(int i) {
        if (i > Util.dp(50.0f) && this.j != i) {
            this.j = i;
            SharePreferenceUtils.setSharePreferencesValue(BaseEmotionActivity.KEYBOARD_HEIGHT, this.j);
        }
        if (n() && i > 0 && !this.p) {
            this.c.post(new agm(this));
        }
    }

    public void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData) {
        if (QiushiEmotionHandler$EmotionData.DELETE.getName().equals(chatMsgEmotionData.name)) {
            this.b.dispatchKeyEvent(new KeyEvent(0, 67));
        } else {
            this.b.append(chatMsgEmotionData.name);
        }
    }
}
