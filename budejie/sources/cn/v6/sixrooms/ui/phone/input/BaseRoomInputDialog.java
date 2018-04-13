package cn.v6.sixrooms.ui.phone.input;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.presenter.PropListPresenter;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.widgets.phone.ExpressionKeyboard;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public abstract class BaseRoomInputDialog extends AbRoomInputDialog implements OnKeyListener, OnClickListener {
    public static final int KEYBOARD_HIDE = 0;
    public static final int KEYBOARD_PRE_SHOW_HIDE = 2;
    public static final int KEYBOARD_SHOW = 1;
    private RoomInputListener a;
    private View b;
    private TextView c;
    private Dialog d;
    private View e;
    private String f = this.mActivity.getResources().getString(R.string.str_chat_loading_data);
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private BaseRoomInputDialog$a j = new BaseRoomInputDialog$a(this);
    private Handler k = new a(this);
    private OnGlobalLayoutListener l = new i(this);
    private View m = null;
    protected boolean mAutoDismiss = true;
    protected View mBgLayout;
    protected ImageView mExpressionBtn;
    public ExpressionKeyboard mExpressionKeyboard;
    protected String mInputEditHint = this.mActivity.getResources().getString(R.string.str_chat_hint);
    public EditText mInputEditText;
    protected IRoomInputLayoutFactory mInputLayoutFactory;
    protected int mKeyboardStatus = 0;
    private String n = RoomInputDialog.class.getSimpleName();
    private CopyOnWriteArrayList<OnKeyBoardLister> o = new CopyOnWriteArrayList();

    public interface OnKeyBoardLister {
        void OnKeyBoardChange(boolean z, int i);
    }

    public abstract boolean sendChat();

    public void setAutoDismiss(boolean z) {
        this.mAutoDismiss = z;
    }

    public void receiveChatList(String str) {
    }

    public void receiveAllChatList(WrapUserInfo wrapUserInfo) {
    }

    public BaseRoomInputDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity);
    }

    public View initContentView() {
        if (!setContentView()) {
            this.mInputLayoutFactory = new LiveRoomInputLayoutFactory(this.mActivity, RoomInputTheme.COMMON_THEME);
        }
        return this.mInputLayoutFactory.generateLayout();
    }

    public int getKeyboardStatus() {
        return this.mKeyboardStatus;
    }

    public boolean setContentView() {
        return false;
    }

    @SuppressLint({"NewApi"})
    public void initView() {
        this.b = findViewById(R.id.fl_input_dialog_root);
        this.b.setFitsSystemWindows(true);
        this.b.setVisibility(4);
        this.mInputEditText = (EditText) findViewById(R.id.et_chat_info);
        this.mExpressionBtn = (ImageView) findViewById(R.id.iv_expression);
        this.mExpressionBtn.setEnabled(false);
        this.c = (TextView) findViewById(R.id.sendChat);
        this.mExpressionKeyboard = (ExpressionKeyboard) findViewById(R.id.rl_expression_page);
        this.mExpressionKeyboard.setBaseFragmentActivity(this.mActivity);
        this.mBgLayout = findViewById(R.id.v_input_dialog_bg);
        this.e = findViewById(R.id.bt_fly_msg);
        setOnDismissListener(new b(this));
        setOnShowListener(new c(this));
    }

    public void initListener() {
        this.mInputEditText.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.mExpressionBtn.setOnClickListener(this);
        this.mBgLayout.setOnClickListener(this);
        if (this.e != null) {
            this.e.setOnClickListener(this);
        }
        setOnKeyListener(this);
        this.mInputEditText.addTextChangedListener(new d(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.o != null && this.o.size() > 0) {
            Iterator it = this.o.iterator();
            while (it.hasNext()) {
                ((OnKeyBoardLister) it.next()).OnKeyBoardChange(false, 0);
            }
        }
    }

    public void onClick(View view) {
        boolean z = true;
        int id = view.getId();
        if (id == R.id.bt_fly_msg) {
            if (LoginUtils.getLoginUserBean() == null) {
                this.mActivity.showLoginDialog();
                return;
            }
            Object obj = this.mInputEditText.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                this.mActivity.showToast(this.mActivity.getString(R.string.str_chat_empty));
            } else if (obj.length() > 40) {
                this.mActivity.showToast(this.mActivity.getString(R.string.fly_msg_overlength));
            } else {
                showFlyTextDialog();
            }
        } else if (id == R.id.v_input_dialog_bg) {
            dismiss();
        } else if (id == R.id.et_chat_info) {
            if (!setInputEditTextClick(view)) {
                if (LoginUtils.getLoginUserBean() == null) {
                    this.mActivity.showLoginDialog();
                    return;
                }
                if (this.mExpressionKeyboard.getVisibility() == 0) {
                    this.g = false;
                    this.mExpressionKeyboard.setVisibility(8);
                    this.mExpressionBtn.setBackgroundResource(this.mInputLayoutFactory.getExpressionImg());
                }
                this.mInputManager.showSoftInput(this.mInputEditText, 0);
            }
        } else if (id == R.id.sendChat) {
            if (LoginUtils.getLoginUserBean() == null) {
                this.mActivity.showLoginDialog();
            } else if (this.f.equals(this.mInputEditText.getHint().toString())) {
                this.mActivity.showToast(this.f);
            } else {
                z = sendChat();
                this.k.sendEmptyMessageDelayed(17, 1000);
                if (z && this.mAutoDismiss) {
                    dismiss();
                }
                if (!this.mActivity.isChatQuietly && z) {
                    this.mActivity.isCanSpeak = false;
                    if (!TextUtils.isEmpty(LoginUtils.getLoginUserBean().getCoin6all())) {
                        if (Long.valueOf(Long.parseLong(LoginUtils.getLoginUserBean().getCoin6all())).longValue() >= 10) {
                            this.k.sendEmptyMessageDelayed(6, 1500);
                        } else {
                            this.k.sendEmptyMessageDelayed(6, 6000);
                        }
                    }
                }
            }
        } else if (id == R.id.iv_expression) {
            this.mExpressionBtn.setOnClickListener(null);
            this.h = true;
            if (LoginUtils.getLoginUserBean() == null) {
                this.mActivity.showLoginDialog();
                return;
            }
            if (1 == this.mActivity.mRoomType) {
                this.mExpressionKeyboard.disableGuardExpress();
            }
            this.mExpressionKeyboard.disableFinishButton();
            this.mExpressionKeyboard.setOnPermissionListener(new e(this));
            this.mExpressionKeyboard.setOnOperateListener(new h(this));
            if (this.g) {
                z = false;
            }
            this.g = z;
            LogUtils.d(this.n, "mExpressionKeyboard-iv_expression--000" + this.mExpressionKeyboard.getVisibility() + "====isExpressionKeyboard===" + this.g);
            if (this.g) {
                if (this.a != null) {
                    this.a.changeState(KeyboardState.EXPRESSION_KEYBOARD);
                }
                if (8 == this.mExpressionKeyboard.getVisibility()) {
                    this.mInputManager.hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
                } else {
                    this.mExpressionKeyboard.setVisibility(0);
                    this.mExpressionBtn.setBackgroundResource(this.mInputLayoutFactory.getKeyboardImg());
                    this.mExpressionBtn.setOnClickListener(this);
                }
            } else {
                this.mExpressionKeyboard.setVisibility(8);
                this.mExpressionBtn.setBackgroundResource(this.mInputLayoutFactory.getExpressionImg());
                this.mInputManager.showSoftInput(this.mInputEditText, 0);
                this.h = false;
                this.mExpressionBtn.setOnClickListener(this);
            }
            LogUtils.d(this.n, "mExpressionKeyboard-iv_expression--111" + this.mExpressionKeyboard.getVisibility());
        }
    }

    public boolean setInputEditTextClick(View view) {
        return false;
    }

    public void setInputListener(RoomInputListener roomInputListener) {
        this.a = roomInputListener;
    }

    public void show() {
        super.show();
        this.mKeyboardStatus = 2;
        if (this.a != null) {
            this.a.show();
        }
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(this.j);
        this.mInputEditText.setFocusable(true);
        this.mInputEditText.setFocusableInTouchMode(true);
        this.mInputEditText.requestFocus();
        setChatRule();
        updateExpressionKeyboard(PropListPresenter.getInstance().getGuardLocalData(), PropListPresenter.getInstance().getVipLocalData());
        this.mInputEditText.performClick();
        if (this.m == null) {
            this.m = getWindow().getDecorView();
        }
        this.m.getViewTreeObserver().addOnGlobalLayoutListener(this.l);
    }

    public void updateExpressionKeyboard(int[] iArr, int[] iArr2) {
        this.mExpressionKeyboard.setGuardPermissonGroup(iArr);
        this.mExpressionKeyboard.setVipPermissonGroup(iArr2);
    }

    public void updateSpeakState() {
        if (this.mActivity.mSpeakState == 1) {
            this.mActivity.isCanSpeak = false;
            this.mInputEditText.setHintTextColor(this.mActivity.getResources().getColor(this.mInputLayoutFactory.getUnSpeakEditHintColor()));
            this.mInputEditText.setHint(this.mActivity.getResources().getString(R.string.str_speak_state_no));
            this.mInputEditText.setInputType(1);
            this.mInputEditText.setCursorVisible(false);
            this.mExpressionBtn.setEnabled(false);
            this.c.setEnabled(false);
            if (this.e != null) {
                this.e.setEnabled(false);
            }
            this.mInputEditText.requestFocus();
            return;
        }
        this.mActivity.isCanSpeak = true;
        this.mInputEditText.setHintTextColor(this.mActivity.getResources().getColor(this.mInputLayoutFactory.getSpeakEditHintColor()));
        this.mInputEditText.setInputType(1);
        this.mExpressionBtn.setEnabled(true);
        this.c.setEnabled(true);
        this.mInputEditText.requestFocus();
        this.mInputEditText.setCursorVisible(true);
        if (this.e != null) {
            this.e.setEnabled(true);
        }
    }

    public void updatePublicSpeakPermission() {
        if ("0".equals(this.mActivity.pubchat)) {
            this.mInputEditText.setHint(this.mInputEditHint);
        } else if ("1".equals(this.mActivity.pubchat)) {
            this.mInputEditText.setHint(R.string.str_chat_hint_manager);
        } else if ("2".equals(this.mActivity.pubchat)) {
            if (LoginUtils.getLoginUserBean() == null) {
                this.mInputEditText.setHint(R.string.str_chat_hint_newuser);
            } else if (LoginUtils.getLoginUserBean().getCoin6rank().equals("0")) {
                this.mInputEditText.setHint(R.string.str_chat_hint_newuser);
            } else {
                this.mInputEditText.setHint(R.string.str_chat_hint);
            }
        }
        updateSpeakState();
    }

    public void disableGuardExpress() {
        this.mExpressionKeyboard.disableGuardExpress();
    }

    public void disableExpress() {
        this.mExpressionKeyboard.disableExpress();
    }

    public void setChatRule() {
        if (LoginUtils.getLoginUserBean() == null || TextUtils.isEmpty(this.mActivity.pubchat)) {
            this.mInputEditText.setInputType(0);
            return;
        }
        this.mInputEditText.setInputType(1);
        updatePublicSpeakPermission();
    }

    public void updateState() {
        setChatRule();
    }

    public void dismiss() {
        super.dismiss();
        this.mKeyboardStatus = 2;
        this.i = false;
        if (this.a != null) {
            this.a.dismiss();
        }
        if (this.mInputEditText != null) {
            this.mInputManager.hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
        }
        if (this.j != null) {
            this.b.getViewTreeObserver().removeGlobalOnLayoutListener(this.j);
        }
        LogUtils.i(this.n, "OnKeyBoardChange----dismiss---mOnKeyBoardListers---" + this.o.size());
        if (this.o != null && this.o.size() > 0) {
            Iterator it = this.o.iterator();
            while (it.hasNext()) {
                ((OnKeyBoardLister) it.next()).OnKeyBoardChange(false, 0);
            }
        }
        if (this.m != null) {
            if (VERSION.SDK_INT >= 16) {
                this.m.getViewTreeObserver().removeOnGlobalLayoutListener(this.l);
            } else {
                this.m.getViewTreeObserver().removeGlobalOnLayoutListener(this.l);
            }
        }
        if (this.o != null) {
            this.o.clear();
        }
    }

    public void addOnGlobalLayoutListener(OnKeyBoardLister onKeyBoardLister) {
        if (!this.o.contains(onKeyBoardLister)) {
            this.o.add(onKeyBoardLister);
        }
    }

    public void removeOnGlobalLayoutListener(OnKeyBoardLister onKeyBoardLister) {
        if (this.o != null && this.o.size() > 0 && onKeyBoardLister != null) {
            this.o.remove(onKeyBoardLister);
        }
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 4) {
            dismiss();
        }
        return false;
    }

    public void setInputEditHint(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mInputEditHint = str;
        }
        if (this.mInputEditText != null) {
            this.mInputEditText.setHint(this.mInputEditHint);
        }
    }

    public boolean sendFlyText() {
        if (this.mActivity == null || InroomPresenter.getInstance().getLocalRoomInfo() == null) {
            return false;
        }
        this.mActivity.sendFlyText(InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getRid(), this.mInputEditText.getText().toString());
        this.mInputEditText.setText("");
        return true;
    }

    public boolean sendSmallFlyText() {
        if (this.mActivity == null || InroomPresenter.getInstance().getLocalRoomInfo() == null) {
            return false;
        }
        this.mActivity.sendSmallFltText(InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getRid(), this.mInputEditText.getText().toString());
        this.mInputEditText.setText("");
        return true;
    }

    public void showFlyTextDialog() {
        this.mActivity.mDialogUtils.createConfirmDialog(1000, this.mActivity.getResources().getString(R.string.fly_msg_dialog_text), new j(this)).show();
    }

    public String filtrationString(String str) {
        return Pattern.compile("^\\s*|\\s*$").matcher(str).replaceAll("");
    }
}
