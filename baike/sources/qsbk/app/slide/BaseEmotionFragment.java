package qsbk.app.slide;

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
import qsbk.app.fragments.BaseFragment;
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

public abstract class BaseEmotionFragment extends BaseFragment implements OnEmotionItemClickListener, SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    protected EditText a;
    protected View b;
    protected SizeNotifierRelativeLayout c;
    protected InputMethodManager d;
    protected ImageButton e;
    protected ImageButton f;
    protected EmotionViewPager g;
    protected DotView h;
    protected int i;
    private boolean j = false;
    private OnClickListener k = new a(this);

    private static Map<String, List<ChatMsgEmotionData>> h() {
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

    protected void a() {
        e();
        this.b.setVisibility(8);
        this.f.setVisibility(0);
        this.e.setVisibility(8);
    }

    protected void b() {
        d();
        a(this.i);
        f();
        this.b.setVisibility(0);
        this.e.setVisibility(0);
        this.f.setVisibility(8);
    }

    protected void a(View view) {
        this.d = (InputMethodManager) getContext().getSystemService("input_method");
        this.i = SharePreferenceUtils.getSharePreferencesIntValue(BaseEmotionActivity.KEYBOARD_HEIGHT);
        this.b = view.findViewById(R.id.emotions);
        this.e = (ImageButton) view.findViewById(R.id.sendKeyboard);
        this.e.setOnClickListener(this.k);
        this.e.setImageResource(UIHelper.getSendTextResource());
        this.f = (ImageButton) view.findViewById(R.id.sendEmotion);
        this.f.setOnClickListener(this.k);
        this.f.setImageResource(UIHelper.getSendEmotionResource());
        this.g = (EmotionViewPager) view.findViewById(R.id.emotion_viewpager);
        this.h = (DotView) view.findViewById(R.id.emotion_dotview);
        this.g.setOnEmotionClickListener(this);
        this.g.setEmotionType(1);
        this.g.setPerPageCount(28);
        this.g.setRowCount(4);
        this.g.setDatas(h());
        this.g.setDotView(this.h);
        this.a.setOnClickListener(this.k);
        this.a.setOnTouchListener(new b(this));
        this.c = (SizeNotifierRelativeLayout) view.findViewById(R.id.root);
        this.c.setSizeNotifierRelativeLayoutDelegate(this);
    }

    protected void c() {
        this.a.requestFocus();
        this.d.showSoftInput(this.a, 1);
    }

    protected void d() {
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(32);
        }
    }

    protected void e() {
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(16);
        }
    }

    protected void f() {
        if (getActivity() != null) {
            View currentFocus = getActivity().getCurrentFocus();
            if (currentFocus != null) {
                this.d.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            }
        }
    }

    protected boolean g() {
        return this.b.getVisibility() == 0 && this.b.getHeight() > 0;
    }

    protected void a(int i) {
        LayoutParams layoutParams = this.b.getLayoutParams();
        if (i < 0) {
            i = 0;
        }
        layoutParams.height = i;
        this.b.setLayoutParams(layoutParams);
    }

    public void onSizeChanged(int i) {
        if (i > Util.dp(50.0f) && this.i != i) {
            this.i = i;
            SharePreferenceUtils.setSharePreferencesValue(BaseEmotionActivity.KEYBOARD_HEIGHT, this.i);
        }
        if (g() && i > 0 && !this.j) {
            this.b.post(new c(this));
        }
    }
}
