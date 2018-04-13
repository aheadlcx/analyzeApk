package cn.v6.sixrooms.room.gift;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.RoomGiftChatListAdapter;
import cn.v6.sixrooms.bean.NumberBean;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.bean.RoomPropConfBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.event.EventObserver;
import cn.v6.sixrooms.event.GiftBoxEvent;
import cn.v6.sixrooms.event.LoginEvent;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.gift.GiftTypes.WrapGiftType;
import cn.v6.sixrooms.room.view.CustomViewPager;
import cn.v6.sixrooms.room.view.EditDialog;
import cn.v6.sixrooms.room.view.EditDialog.Callback;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow.PopupWindowControlListener;
import cn.v6.sixrooms.ui.view.indicator.CommonNavigator;
import cn.v6.sixrooms.ui.view.indicator.CommonNavigatorAdapter;
import cn.v6.sixrooms.ui.view.indicator.IPagerIndicator;
import cn.v6.sixrooms.ui.view.indicator.IPagerNavigator;
import cn.v6.sixrooms.ui.view.indicator.IPagerTitleView;
import cn.v6.sixrooms.ui.view.indicator.LinePagerIndicator;
import cn.v6.sixrooms.ui.view.indicator.SimplePagerTitleView;
import cn.v6.sixrooms.ui.view.indicator.ViewPagerHelper;
import cn.v6.sixrooms.utils.CheckRoomTypeUtils;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.FastDoubleClickUtil;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.view.runnable.IChooseGiftsListener;
import cn.v6.sixrooms.widgets.phone.NoShadowListView;
import cn.v6.sixrooms.widgets.phone.indicator.MagicIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GiftBoxDialog extends Dialog implements OnDismissListener, OnClickListener {
    private LinearLayout giftContent;
    private RelativeLayout input_number_layout;
    private boolean isOutside;
    private TextView mAnonymousView;
    private ChatListPopupWindow mChatListPopupWindow;
    private Context mContext;
    private Long mCurrency;
    private TextView mCurrencyView;
    private int mCurrentRoomType;
    private PopupWindow mDesPopWindow;
    private DialogUtils mDialogUtils;
    private List<WrapGiftType> mDisplayWrapTypeList;
    private EditDialog mEditDialog;
    private View mEmptyView;
    private RelativeLayout mGiftBottomLayout;
    private GiftBoxEvent mGiftBoxEvent;
    private RelativeLayout mGiftBoxHeader;
    private ReadGiftEngine mGiftEngine;
    private RelativeLayout mGiftExplainLayout;
    private TextView mGiftExplainView;
    private TextView mGiftNumView;
    private long mGiftNumber;
    private EventObserver mGiftSelectObserver;
    private List<UserInfoBean> mGiftUserConf;
    private Button mGiveGiftView;
    private TextView mGiveTextView;
    private RelativeLayout mGiveUserLayout;
    private TextView mGiveUserView;
    private Handler mHandler;
    private boolean mIsAnonymous;
    private boolean mIsFamilyRoom;
    private final boolean mIsLandscape;
    private final boolean mIsMobileLive;
    private boolean mIsSuperGirlFamilyRoom;
    private boolean mIsSuperGirlRoom;
    private EventObserver mLoginObserver;
    private NoShadowListView mNumberList;
    private PopupWindow mNumberSelector;
    private GiftBoxPageAdapter mPageAdapter;
    private MagicIndicator mPagerIndicator;
    private RelativeLayout mPayLayout;
    private WrapGiftType mRepertoryWrapType;
    private IChooseGiftsListener mRoomCallback;
    private RoominfoBean mRoominfoBean;
    private SelectGiftInfo mSelectGiftInfo;
    private UserInfoEngine mUserInfoEngine;
    private boolean mUserListPopIsShowing;
    private CustomViewPager mViewPager;
    private FrameLayout mViewpagerWrapLayout;
    private WrapRoomInfo mWrapRoomInfo;
    private TextView number_button;
    private EditText number_edit;
    private String toTempUserID;
    private String toTempUserName;
    private String toUserID;
    private String toUserName;

    public class WantGift {
        public String giftId;
        public int giftPos;
        public int typePos;

        public WantGift(String str, int i, int i2) {
            this.giftId = str;
            this.typePos = i;
            this.giftPos = i2;
        }
    }

    public GiftBoxDialog(int i, Context context, IChooseGiftsListener iChooseGiftsListener) {
        int i2;
        if (DisPlayUtil.isLandscape(context)) {
            i2 = R.style.GiftBoxLandscapeStyle;
        } else {
            i2 = R.style.GiftBoxPortraitStyle;
        }
        super(context, i2);
        this.mContext = getContext();
        this.mHandler = new Handler();
        this.mGiftBoxEvent = new GiftBoxEvent();
        this.mGiftEngine = new ReadGiftEngine();
        if (iChooseGiftsListener == null) {
            throw new NullPointerException("IChooseGiftsListener cannot be null!");
        }
        this.mIsLandscape = DisPlayUtil.isLandscape(this.mContext);
        this.mCurrentRoomType = i;
        this.mRoomCallback = iChooseGiftsListener;
        this.mWrapRoomInfo = this.mRoomCallback.obtainWrapRoomInfo();
        if (this.mWrapRoomInfo != null) {
            this.mGiftUserConf = this.mWrapRoomInfo.getGiftUserConf();
            this.mRoominfoBean = this.mWrapRoomInfo.getRoominfoBean();
        }
        this.mIsSuperGirlRoom = isSuperGirlRoom(context);
        boolean z = this.mCurrentRoomType == 3 || this.mCurrentRoomType == 4;
        this.mIsMobileLive = z;
        attachEvent();
    }

    private boolean isSuperGirlRoom(Context context) {
        if (!(context instanceof BaseRoomActivity)) {
            return false;
        }
        BaseRoomActivity baseRoomActivity = (BaseRoomActivity) context;
        this.mIsSuperGirlFamilyRoom = baseRoomActivity.isSuperGirlFamilyRoom().booleanValue();
        return this.mIsSuperGirlFamilyRoom ? true : baseRoomActivity.isSuperGirlRoom().booleanValue();
    }

    private void attachEvent() {
        this.mLoginObserver = new EventObserver() {
            public void onEventChange(Object obj, String str) {
                if (obj instanceof LoginEvent) {
                    GiftBoxDialog.this.loadCurrency();
                }
            }
        };
        this.mGiftSelectObserver = new EventObserver() {
            public void onEventChange(Object obj, String str) {
                if (obj instanceof GiftBoxSelectEvent) {
                    SelectGiftInfo selectGiftInfo = ((GiftBoxSelectEvent) obj).selectGiftInfo;
                    GiftBoxDialog.this.recoverySelectGiftState();
                    GiftBoxDialog.this.mSelectGiftInfo = selectGiftInfo;
                    GiftBoxDialog.this.updataGiftSelectState();
                }
            }
        };
        EventManager.getDefault().attach(this.mLoginObserver, LoginEvent.class);
        EventManager.getDefault().attach(this.mGiftSelectObserver, GiftBoxSelectEvent.class);
    }

    private void recoverySelectGiftState() {
        if (this.mSelectGiftInfo != null && this.mSelectGiftInfo.selectedViewHelp != null) {
            this.mSelectGiftInfo.selectedViewHelp.recoveryGiftState(this.mSelectGiftInfo.selectedGiftId);
        }
    }

    private void detachEvent() {
        EventManager.getDefault().detach(this.mLoginObserver, LoginEvent.class);
        EventManager.getDefault().detach(this.mGiftSelectObserver, GiftBoxSelectEvent.class);
        this.mLoginObserver = null;
        this.mGiftSelectObserver = null;
    }

    private void updataGiftSelectState() {
        updateGiveButtonState();
        updateToUserUI();
        updateDescribe();
    }

    private void updateDescribe() {
        if (this.mSelectGiftInfo == null) {
            cleanGiftDescribe();
            return;
        }
        Gift gift = this.mSelectGiftInfo.gift;
        if (gift != null) {
            showGiftDescribe(gift.getIntro());
        } else {
            cleanGiftDescribe();
        }
    }

    private void showGiftDescribe(String str) {
        if (TextUtils.isEmpty(str)) {
            cleanGiftDescribe();
        } else if (this.mIsSuperGirlRoom) {
            this.mGiftExplainView.setText(str);
        } else {
            showCommonDescribe(str);
        }
    }

    private void showCommonDescribe(String str) {
        View view;
        if (this.mDesPopWindow != null && this.mDesPopWindow.isShowing()) {
            hideDesPopWindow();
        }
        View inflate;
        try {
            inflate = View.inflate(this.mContext, R.layout.popup_gift_describe, null);
            inflate.measure(-2, -2);
            view = inflate;
        } catch (Exception e) {
            inflate = View.inflate(this.mContext, R.layout.popup_gift_describe_compatible, null);
            inflate.measure(-2, -2);
            view = inflate;
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_gift_describe_popup);
        ImageView imageView = (ImageView) view.findViewById(R.id.tv_gift_describe_triangle_popup);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.tv_gift_describe_triangle_up_popup);
        if (this.mIsLandscape) {
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
        } else {
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
        }
        textView.setText(str);
        textView.measure(-2, -2);
        view.measure(-2, -2);
        this.mDesPopWindow = new PopupWindow(view, view.getMeasuredWidth(), view.getMeasuredHeight() + DensityUtil.dip2px(3.0f));
        this.mDesPopWindow.setBackgroundDrawable(new BitmapDrawable());
        this.mDesPopWindow.setTouchable(false);
        this.mDesPopWindow.setOutsideTouchable(true);
        int width = this.mDesPopWindow.getWidth();
        int height = this.mDesPopWindow.getHeight();
        int width2 = this.mSelectGiftInfo.view.getWidth();
        int height2 = this.mSelectGiftInfo.view.getHeight();
        int measuredWidth = imageView.getMeasuredWidth();
        if (this.mIsLandscape) {
            int measuredWidth2 = imageView2.getMeasuredWidth();
            height = (int) this.mSelectGiftInfo.view.getX();
            measuredWidth = (int) this.mSelectGiftInfo.view.getY();
            if (((double) height) > ((double) width2) * 1.5d) {
                imageView2.setX((float) (width - ((measuredWidth2 + width2) / 2)));
            } else {
                imageView2.setX((float) ((width - measuredWidth2) / 2));
            }
            this.mDesPopWindow.showAtLocation(this.mPagerIndicator, 0, (height + this.mEmptyView.getWidth()) - ((width - width2) / 2), ((DensityUtil.dip2px(3.0f) + measuredWidth) + this.mGiftBoxHeader.getHeight()) + height2);
            return;
        }
        int[] iArr = new int[2];
        this.mSelectGiftInfo.view.getLocationInWindow(iArr);
        height2 = iArr[0];
        int i = iArr[1];
        if (height2 == 0) {
            imageView.setX((float) ((width2 - measuredWidth) / 2));
        } else if ((height2 + width2) + (width2 / 4) >= DensityUtil.getScreenWidth()) {
            imageView.setX((float) (width - ((measuredWidth / 2) + (width2 / 2))));
        } else {
            imageView.setX((float) ((width - measuredWidth) / 2));
        }
        this.mDesPopWindow.showAtLocation(this.mPagerIndicator, 0, height2 - ((int) (((float) ((width - width2) / 2)) + 0.5f)), i - height);
    }

    private void cleanGiftDescribe() {
        if (this.mIsSuperGirlRoom) {
            this.mGiftExplainView.setText("");
        } else if (this.mDesPopWindow != null && this.mDesPopWindow.isShowing()) {
            hideDesPopWindow();
        }
    }

    private void hideDesPopWindow() {
        this.mDesPopWindow.dismiss();
    }

    private void cleanSelectGiftState() {
        EventManager.getDefault().nodifyObservers(new GiftBoxSelectEvent(), "");
    }

    private void setDialogInfo() {
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
        setCanceledOnTouchOutside(true);
        if (this.mIsLandscape) {
            window.addFlags(1024);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        setDialogInfo();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_giftbox);
        initNumberOfGifts();
        initView();
        initListener();
        loadData();
    }

    private void initView() {
        this.mGiftBoxHeader = (RelativeLayout) findViewById(R.id.rl_giftbox_header);
        this.mViewPager = (CustomViewPager) findViewById(R.id.vp_giftbox);
        this.mPagerIndicator = (MagicIndicator) findViewById(R.id.magic_indicator2);
        this.mGiveUserLayout = (RelativeLayout) findViewById(R.id.rl_giftbox_give_user);
        this.mPayLayout = (RelativeLayout) findViewById(R.id.rl_pay_boxgift);
        this.mGiftNumView = (TextView) findViewById(R.id.tv_number_giftbox);
        this.mGiveTextView = (TextView) findViewById(R.id.tx_give_tag);
        this.mGiveUserView = (TextView) findViewById(R.id.tx_giftbox_user);
        this.mCurrencyView = (TextView) findViewById(R.id.tv_currency_giftbox);
        this.mAnonymousView = (TextView) findViewById(R.id.tv_anonymous_giftbox);
        this.mGiveGiftView = (Button) findViewById(R.id.bt_give_giftbox);
        this.giftContent = (LinearLayout) findViewById(R.id.ll_giftbox_content);
        this.mGiftBottomLayout = (RelativeLayout) findViewById(R.id.rl_giftbox_bottom_layout);
        this.mGiftExplainView = (TextView) findViewById(R.id.tv_super_girl_gift_explain);
        this.mGiftExplainLayout = (RelativeLayout) findViewById(R.id.rl_super_girl_gift_layout);
        this.mViewpagerWrapLayout = (FrameLayout) findViewById(R.id.fl_viewpage_wraplayout);
        this.input_number_layout = (RelativeLayout) findViewById(R.id.id_choose_gifts_select_numbers_input_layout);
        this.number_edit = (EditText) findViewById(R.id.id_choose_gift_edit_number);
        this.number_button = (TextView) findViewById(R.id.id_choose_gift_button_number);
        if (this.mIsSuperGirlRoom) {
            if (!this.mIsSuperGirlFamilyRoom) {
                this.mGiveUserView.setCompoundDrawables(null, null, null, null);
                this.mGiveUserLayout.setEnabled(false);
            }
            this.mAnonymousView.setVisibility(8);
            this.mGiftExplainLayout.setVisibility(0);
            this.mGiveGiftView.setBackgroundResource(R.drawable.choose_gifts_supergirl_give_button_selector);
            if (this.mIsLandscape) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mGiveGiftView.getLayoutParams();
                layoutParams.width = (int) this.mContext.getResources().getDimension(R.dimen.gift_box_super_girl_send_button_width_land);
                layoutParams.addRule(11);
                layoutParams = (RelativeLayout.LayoutParams) this.mGiftNumView.getLayoutParams();
                layoutParams.height = DensityUtil.dip2px(38.0f);
                layoutParams.addRule(2, 0);
                layoutParams.addRule(11, 0);
                layoutParams.addRule(9);
                ((LinearLayout.LayoutParams) this.mGiftBottomLayout.getLayoutParams()).height = DensityUtil.dip2px(45.0f);
                if (DensityUtil.getScreenDensity() < 2.0f) {
                    this.mViewpagerWrapLayout.setPadding(20, 5, 20, 5);
                } else {
                    this.mViewpagerWrapLayout.setPadding(20, 20, 20, 20);
                }
            } else {
                this.mViewpagerWrapLayout.setPadding(10, 15, 10, 15);
                ((FrameLayout.LayoutParams) this.mViewPager.getLayoutParams()).height = (int) this.mContext.getResources().getDimension(R.dimen.gift_box_viewpage_item_height);
            }
        } else {
            this.mGiftExplainLayout.setVisibility(8);
        }
        this.giftContent.setBackgroundColor(this.mContext.getResources().getColor(R.color.gift_box_bg));
        this.mGiftBottomLayout.setBackgroundColor(-16777216);
        if (this.mIsLandscape) {
            this.mGiftBottomLayout.setBackgroundColor(this.mContext.getResources().getColor(R.color.gift_box_land_bg2));
        } else if (4 != this.mCurrentRoomType) {
            this.giftContent.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                public boolean onPreDraw() {
                    GiftBoxDialog.this.giftContent.getViewTreeObserver().removeOnPreDrawListener(this);
                    int screenWidth = DensityUtil.getScreenWidth();
                    int height = GiftBoxDialog.this.giftContent.getHeight();
                    GiftBoxDialog.this.giftContent.setBackgroundResource(R.drawable.room_chat_common_backgroud);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) GiftBoxDialog.this.giftContent.getLayoutParams();
                    layoutParams.width = screenWidth;
                    layoutParams.height = height;
                    GiftBoxDialog.this.giftContent.setLayoutParams(layoutParams);
                    return true;
                }
            });
        } else if (this.mIsSuperGirlRoom) {
            this.mGiftBottomLayout.setBackgroundColor(this.mContext.getResources().getColor(R.color.gift_box_land_bg2));
        }
    }

    private void initListener() {
        this.mEmptyView = findViewById(R.id.null_boxgift);
        this.mEmptyView.setOnClickListener(this);
        setOnDismissListener(this);
        this.mAnonymousView.setOnClickListener(this);
        this.mGiveGiftView.setOnClickListener(this);
        this.mPayLayout.setOnClickListener(this);
        this.mGiftNumView.setOnClickListener(this);
        this.mGiveUserLayout.setOnClickListener(this);
        this.mNumberList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GiftBoxDialog.this.mNumberSelector.dismiss();
                if (i == adapterView.getCount() - 1) {
                    GiftBoxDialog.this.mNumberList.post(new Runnable() {
                        public void run() {
                            GiftBoxDialog.this.showSystemInput();
                        }
                    });
                    return;
                }
                GiftBoxDialog.this.setGiftNumber(String.valueOf(((NumberBean) adapterView.getItemAtPosition(i)).getNumber()));
            }
        });
        this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (GiftBoxDialog.this.mSelectGiftInfo != null && GiftBoxDialog.this.mIsSuperGirlRoom) {
                    GiftBoxDialog.this.cleanGiftDescribe();
                    if (GiftBoxDialog.this.mSelectGiftInfo.selectedViewHelp.mViewPagePosition == i) {
                        GiftBoxDialog.this.updateDescribe();
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
                if (1 == GiftBoxDialog.this.mCurrentRoomType || 2 == GiftBoxDialog.this.mCurrentRoomType || 3 == GiftBoxDialog.this.mCurrentRoomType) {
                    switch (i) {
                        case 0:
                            GiftBoxDialog.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    GiftBoxDialog.this.mGiftBoxEvent.isUpdateChatList = true;
                                    EventManager.getDefault().nodifyObservers(GiftBoxDialog.this.mGiftBoxEvent, "1");
                                }
                            }, 1000);
                            return;
                        case 1:
                            GiftBoxDialog.this.mGiftBoxEvent.isUpdateChatList = false;
                            GiftBoxDialog.this.mHandler.removeCallbacksAndMessages(null);
                            EventManager.getDefault().nodifyObservers(GiftBoxDialog.this.mGiftBoxEvent, "1");
                            return;
                        default:
                            return;
                    }
                }
            }
        });
        this.number_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GiftBoxDialog.this.hideSystemInput();
            }
        });
    }

    private void setGiftNumber(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mGiftNumber = Long.valueOf(str).longValue();
            this.mGiftNumView.setText(str);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.null_boxgift) {
            dismiss();
        } else if (id == R.id.tv_anonymous_giftbox) {
            updateAnonymousState();
        } else if (id == R.id.tv_number_giftbox) {
            this.mNumberSelector.showAsDropDown(this.mGiftNumView, (this.mGiftNumView.getWidth() - this.mNumberSelector.getWidth()) / 2, DensityUtil.dip2px(5.0f));
        } else if (id == R.id.bt_give_giftbox) {
            if (LoginUtils.isLogin()) {
                if (this.mSelectGiftInfo == null) {
                    if (this.mDialogUtils == null) {
                        this.mDialogUtils = new DialogUtils(this.mContext);
                    }
                    this.mDialogUtils.createDiaglog(this.mContext.getResources().getString(R.string.str_gift_empty)).show();
                    return;
                }
                getGiftNumber();
                if ("0".equals(this.mSelectGiftInfo.gift.getMsgflag())) {
                    sendGift(null);
                    return;
                }
                if (this.mEditDialog == null) {
                    this.mEditDialog = DialogUtils.createEditDialog(getContext(), new Callback() {
                        public void cancle() {
                            GiftBoxDialog.this.sendGift(null);
                            GiftBoxDialog.this.mEditDialog.dismiss();
                        }

                        public void ok() {
                            GiftBoxDialog.this.sendGift(GiftBoxDialog.this.mEditDialog.getInputText());
                            GiftBoxDialog.this.mEditDialog.dismiss();
                        }
                    });
                }
                this.mEditDialog.show();
            } else if (this.mRoomCallback != null) {
                this.mRoomCallback.gotoLogin();
            }
        } else if (id == R.id.rl_pay_boxgift) {
            if (!FastDoubleClickUtil.isFastDoubleClick()) {
                if (LoginUtils.isLogin()) {
                    this.mRoomCallback.gotoRecharge();
                } else {
                    this.mRoomCallback.gotoLogin();
                }
            }
        } else if (id == R.id.rl_giftbox_give_user && this.mGiftUserConf != null && this.mChatListPopupWindow != null && this.mGiftUserConf.size() != 0) {
            boolean z;
            id = (int) this.mContext.getResources().getDimension(R.dimen.gift_box_item_to_user_pop_height);
            if (this.mGiftUserConf.size() >= 4) {
                id *= 4;
            }
            this.mChatListPopupWindow.setHeight(id);
            if (this.mUserListPopIsShowing) {
                z = false;
            } else {
                z = true;
            }
            this.mUserListPopIsShowing = z;
            if (this.mUserListPopIsShowing) {
                this.mChatListPopupWindow.showAsDropDown(this.mGiveTextView, 0, DensityUtil.dip2px(5.0f));
                showToUserArrow(this.mUserListPopIsShowing);
                return;
            }
            this.mChatListPopupWindow.dismiss();
        }
    }

    private void showToUserArrow(boolean z) {
        if (z) {
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.choose_gift_select_number_pop_up);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mGiveUserView.setCompoundDrawables(null, null, drawable, null);
            return;
        }
        drawable = this.mContext.getResources().getDrawable(R.drawable.choose_gift_select_number_pop_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.mGiveUserView.setCompoundDrawables(null, null, drawable, null);
    }

    private void getGiftNumber() {
        if (this.mGiftNumView != null) {
            String trim = this.mGiftNumView.getText().toString().trim();
            if (trim.matches("[0-9]*")) {
                this.mGiftNumber = Long.valueOf(trim).longValue();
            }
        }
    }

    private void initNumberOfGifts() {
        List giftNumList;
        List allGiftNum = PoseConfig.getInstance().getAllGiftNum();
        if (allGiftNum == null || allGiftNum.size() == 0) {
            giftNumList = GiftConfigUtil.getGiftNumList();
        } else {
            giftNumList = allGiftNum;
        }
        this.mNumberList = (NoShadowListView) View.inflate(this.mContext, R.layout.gift_select_number_popwindow_view, null);
        this.mNumberList.setDivider(null);
        this.mNumberList.setVerticalScrollBarEnabled(false);
        this.mNumberSelector = new PopupWindow(this.mNumberList, DensityUtil.dip2px(175.0f), this.mIsLandscape ? DensityUtil.dip2px(200.0f) : DensityUtil.dip2px(240.0f), true);
        this.mNumberSelector.setOutsideTouchable(true);
        this.mNumberSelector.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.giftbox_select_number_background));
        this.mNumberList.setAdapter(new GiftBoxNumberAdapter(this.mContext, giftNumList));
    }

    private void sendGift(String str) {
        int i = 0;
        Gift gift = this.mSelectGiftInfo.gift;
        if ("11".equals(gift.getType())) {
            i = 1;
        }
        RoominfoBean roominfoBean = this.mWrapRoomInfo.getRoominfoBean();
        if (!(roominfoBean == null || this.mRoomCallback == null)) {
            IChooseGiftsListener iChooseGiftsListener;
            String id;
            if (this.mIsAnonymous) {
                iChooseGiftsListener = this.mRoomCallback;
                id = this.isOutside ? this.toTempUserID : TextUtils.isEmpty(this.toUserID) ? roominfoBean.getId() : this.toUserID;
                iChooseGiftsListener.anonymousSend(id, roominfoBean.getId(), gift.getId(), (int) this.mGiftNumber, i, str);
            } else {
                iChooseGiftsListener = this.mRoomCallback;
                id = this.isOutside ? this.toTempUserID : TextUtils.isEmpty(this.toUserID) ? roominfoBean.getId() : this.toUserID;
                iChooseGiftsListener.publicSend(id, roominfoBean.getId(), gift.getId(), (int) this.mGiftNumber, i, str);
            }
        }
        dismiss();
    }

    private void updateGiveButtonState() {
        if (this.mSelectGiftInfo != null) {
            this.mGiveGiftView.setEnabled(true);
        } else {
            this.mGiveGiftView.setEnabled(false);
        }
    }

    private void updateAnonymousState() {
        this.mIsAnonymous = !this.mIsAnonymous;
        updateAnonymousUI();
    }

    private void updateAnonymousUI() {
        if (this.mAnonymousView != null) {
            Drawable drawable;
            if (this.mIsAnonymous) {
                drawable = this.mContext.getResources().getDrawable(R.drawable.giftbox_anonymous_selected);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.mAnonymousView.setCompoundDrawables(null, null, drawable, null);
                return;
            }
            drawable = this.mContext.getResources().getDrawable(R.drawable.giftbox_anonymous_not_selected);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mAnonymousView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    private void loadData() {
        initToUser();
        getUserCurrency();
        getReceiveUser();
        getGiftInfo();
        loadViewPager();
    }

    private void initToUser() {
        if (CheckRoomTypeUtils.isFamilyRoomType(this.mRoominfoBean.getId()) || this.mIsSuperGirlFamilyRoom) {
            if (this.mGiftUserConf == null || this.mGiftUserConf.size() == 0) {
                this.mGiveUserView.setHint("");
            } else {
                this.mGiveUserView.setHint("请选择");
            }
            this.mGiveUserView.setText("");
            this.mIsFamilyRoom = true;
        } else if (this.mRoominfoBean != null) {
            this.mGiveUserView.setText(this.mRoominfoBean.getAlias());
        }
    }

    private void getReceiveUser() {
        if (this.mChatListPopupWindow == null) {
            this.mChatListPopupWindow = new ChatListPopupWindow(this.mRoomCallback.getActivity(), (int) this.mContext.getResources().getDimension(R.dimen.gift_box_item_to_user_pop_width), -2, true, this.mWrapRoomInfo, new PopupWindowControlListener() {
                public void onShow() {
                }

                public void onDismiss() {
                    GiftBoxDialog.this.mUserListPopIsShowing = false;
                    GiftBoxDialog.this.showToUserArrow(GiftBoxDialog.this.mUserListPopIsShowing);
                }

                public List<UserInfoBean> reviseData(WrapUserInfo wrapUserInfo) {
                    return null;
                }

                public void error(int i) {
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    List chatListData = GiftBoxDialog.this.mChatListPopupWindow.getChatListData();
                    GiftBoxDialog.this.toUserID = ((UserInfoBean) chatListData.get(i)).getUid();
                    GiftBoxDialog.this.toUserName = ((UserInfoBean) chatListData.get(i)).getUname();
                    if (GiftBoxDialog.this.isOutside) {
                        GiftBoxDialog.this.resetOutsideState();
                    }
                    GiftBoxDialog.this.updateToUserUI();
                }
            });
            this.mChatListPopupWindow.setBackground(R.drawable.giftbox_select_to_user_bg);
            this.mChatListPopupWindow.setChatListAdapter(new RoomGiftChatListAdapter(this.mGiftUserConf, this.mContext));
            this.mChatListPopupWindow.setRefreshState(true, true, true, true);
        }
    }

    private void resetOutsideState() {
        this.toTempUserID = "";
        this.toTempUserName = "";
        this.isOutside = false;
    }

    private void loadViewPager() {
        if (this.mDisplayWrapTypeList != null) {
            final int gotoPageIndex = getGotoPageIndex();
            this.mPageAdapter = new GiftBoxPageAdapter(this.mContext, this.mCurrentRoomType, this.mIsSuperGirlRoom, this.mDisplayWrapTypeList);
            this.mViewPager.setAdapter(this.mPageAdapter);
            this.mViewPager.post(new Runnable() {
                public void run() {
                    if (GiftBoxDialog.this.mSelectGiftInfo == null) {
                        GiftBoxDialog.this.mViewPager.setCurrentItem(gotoPageIndex, false);
                    }
                }
            });
            IPagerNavigator commonNavigator = new CommonNavigator(this.mContext);
            if (this.mIsSuperGirlRoom) {
                commonNavigator.setAdjustMode(true);
            }
            commonNavigator.setScrollPivotX(0.9f);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                public int getCount() {
                    return GiftBoxDialog.this.mDisplayWrapTypeList.size();
                }

                public IPagerTitleView getTitleView(Context context, final int i) {
                    WrapGiftType wrapGiftType = (WrapGiftType) GiftBoxDialog.this.mDisplayWrapTypeList.get(i);
                    SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                    simplePagerTitleView.setText(wrapGiftType.getTagName());
                    simplePagerTitleView.setTextNormalColor(GiftBoxDialog.this.mContext.getResources().getColor(R.color.gift_box_text6));
                    simplePagerTitleView.setTextSelectedColor(-1);
                    if (GiftBoxDialog.this.mIsLandscape) {
                        simplePagerTitleView.setNormalBackground(R.drawable.giftbox_viewpager_indicator_normal_lbg);
                    } else if (4 == GiftBoxDialog.this.mCurrentRoomType && GiftBoxDialog.this.mIsSuperGirlRoom) {
                        simplePagerTitleView.setNormalBackground(R.drawable.giftbox_viewpager_indicator_normal_lbg);
                    } else {
                        simplePagerTitleView.setNormalBackground(R.drawable.giftbox_viewpager_indicator_normal_pbg);
                    }
                    simplePagerTitleView.setSelectedBackground(GiftBoxDialog.this.mIsSuperGirlRoom ? R.drawable.giftbox_viewpager_supergirl_indicator_selected_bg : R.drawable.giftbox_viewpager_indicator_selected_bg);
                    simplePagerTitleView.setTextSize(12.0f);
                    simplePagerTitleView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            GiftBoxDialog.this.mViewPager.setCurrentItem(i);
                        }
                    });
                    return simplePagerTitleView;
                }

                public IPagerIndicator getIndicator(Context context) {
                    IPagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                    linePagerIndicator.setMode(2);
                    linePagerIndicator.setColors(Integer.valueOf(0));
                    return linePagerIndicator;
                }
            });
            this.mPagerIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(this.mPagerIndicator, this.mViewPager);
        }
    }

    private int getGotoPageIndex() {
        int i = 0;
        for (WrapGiftType wrapGiftType : this.mDisplayWrapTypeList) {
            int i2;
            if (wrapGiftType == null || !"1".equals(wrapGiftType.getTag())) {
                i2 = i;
            } else {
                i2 = this.mDisplayWrapTypeList.indexOf(wrapGiftType);
            }
            i = i2;
        }
        return i;
    }

    private void getGiftInfo() {
        if (this.mIsSuperGirlRoom) {
            getSuperGirlTypeList();
        } else {
            getCommonGiftTypeList();
        }
    }

    private void getSuperGirlTypeList() {
        List superGirlTypeList = this.mGiftEngine.getSuperGirlTypeList(getSuperGirlGiftIDs());
        if (superGirlTypeList == null || superGirlTypeList.size() <= 0) {
            showError();
            return;
        }
        this.mDisplayWrapTypeList = superGirlTypeList;
        addStockGift();
    }

    private List<String> getSuperGirlGiftIDs() {
        List<String> arrayList = new ArrayList();
        if (this.mWrapRoomInfo == null) {
            return arrayList;
        }
        RoomPropConfBean roomPropConf = this.mWrapRoomInfo.getRoomPropConf();
        if (roomPropConf == null) {
            return arrayList;
        }
        List<String> com = roomPropConf.getCom();
        if (com != null) {
            return com;
        }
        return arrayList;
    }

    private void getCommonGiftTypeList() {
        List displayGiftTypeList = this.mGiftEngine.getDisplayGiftTypeList(this.mIsMobileLive);
        if (displayGiftTypeList == null || displayGiftTypeList.size() <= 0) {
            showError();
            return;
        }
        this.mDisplayWrapTypeList = displayGiftTypeList;
        if (this.mWrapRoomInfo != null && this.mWrapRoomInfo.getRoominfoBean() != null) {
            addStockGift();
            addSpecialGifts(this.mGiftEngine.getGiftConfig(), this.mWrapRoomInfo.getRoominfoBean().getId(), LoginUtils.getLoginUID());
        }
    }

    private void addStockGift() {
        if (!this.mDisplayWrapTypeList.contains(this.mRepertoryWrapType)) {
            this.mRepertoryWrapType = new WrapGiftType();
            this.mRepertoryWrapType.setTag("11");
            this.mRepertoryWrapType.setTypeGiftList(new ArrayList());
            if (this.mIsSuperGirlRoom) {
                this.mDisplayWrapTypeList.add(this.mRepertoryWrapType);
            } else {
                this.mDisplayWrapTypeList.add(0, this.mRepertoryWrapType);
            }
        }
        updateStockGift();
    }

    public void updateStockGift() {
        ArrayList arrayList = null;
        if (this.mRoomCallback != null) {
            arrayList = this.mRoomCallback.getStockList();
        }
        if (arrayList != null && this.mGiftEngine != null && this.mDisplayWrapTypeList != null) {
            if (this.mRepertoryWrapType != null) {
                List typeGiftList = this.mRepertoryWrapType.getTypeGiftList();
                typeGiftList.clear();
                HashMap stockMap = this.mGiftEngine.getStockMap();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    RepertoryBean repertoryBean = (RepertoryBean) it.next();
                    if (repertoryBean != null) {
                        String giftID = repertoryBean.getGiftID();
                        String gifTotal = repertoryBean.getGifTotal();
                        if (giftID != null && stockMap.containsKey(giftID)) {
                            try {
                                Gift clone = ((Gift) stockMap.get(giftID)).clone();
                                if (!(this.mSelectGiftInfo == null || TextUtils.isEmpty(this.mSelectGiftInfo.selectedGiftId) || !this.mSelectGiftInfo.selectedGiftId.equals(giftID))) {
                                    clone.setSelected(true);
                                }
                                clone.setNum(gifTotal.matches("[0-9]*") ? Integer.valueOf(gifTotal).intValue() : 0);
                                typeGiftList.add(clone);
                                if (this.mPageAdapter != null) {
                                    this.mPageAdapter.notifyDataSetChanged();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                return;
            }
            addStockGift();
        }
    }

    private void addSpecialGifts(GiftConfig giftConfig, String str, String str2) {
        if (giftConfig != null && this.mDisplayWrapTypeList.size() != 0) {
            int i = -1;
            int i2 = -1;
            for (int i3 = 0; i3 < this.mDisplayWrapTypeList.size(); i3++) {
                WrapGiftType wrapGiftType = (WrapGiftType) this.mDisplayWrapTypeList.get(i3);
                if (wrapGiftType != null) {
                    if ("5".equals(wrapGiftType.getTag())) {
                        i = i3;
                    } else if ("1".equals(wrapGiftType.getTag())) {
                        i2 = i3;
                    }
                }
            }
            List<Gift> giftTypeOther = giftConfig.getGifts().getGiftTypeOther();
            List<Gift> giftTypeRoom = giftConfig.getGifts().getGiftTypeRoom();
            if ("1".equals(this.mWrapRoomInfo.getIsBirth()) && giftTypeOther != null && giftTypeOther.size() > 0) {
                for (Gift gift : giftTypeOther) {
                    if (!(gift == null || !GiftIdStrs.BIRTHDAY_GIFT_ID.equals(gift.getId()) || i == -1)) {
                        try {
                            ((WrapGiftType) this.mDisplayWrapTypeList.get(i)).getTypeGiftList().add(0, gift.clone());
                            break;
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(str) && str.equals(str2) && giftTypeOther != null && giftTypeOther.size() > 0) {
                for (Gift gift2 : giftTypeOther) {
                    if (!(gift2 == null || !GiftIdStrs.WELCOME_GIFT_ID.equals(gift2.getId()) || i2 == -1)) {
                        try {
                            ((WrapGiftType) this.mDisplayWrapTypeList.get(i2)).getTypeGiftList().add(0, gift2.clone());
                            break;
                        } catch (CloneNotSupportedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            if (giftTypeRoom != null && giftTypeRoom.size() != 0) {
                for (Gift gift3 : giftTypeRoom) {
                    if (gift3 != null) {
                        String uids = gift3.getUids();
                        if (uids.contains(str)) {
                            for (String uids2 : uids2.split(",")) {
                                if (uids2.equals(str) && i != -1) {
                                    try {
                                        ((WrapGiftType) this.mDisplayWrapTypeList.get(i)).getTypeGiftList().add(0, gift3.clone());
                                    } catch (CloneNotSupportedException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void getUserCurrency() {
        loadCurrency();
        if (LoginUtils.isLogin()) {
            getUserInfo();
        }
    }

    public void loadCurrency() {
        if (LoginUtils.isLogin()) {
            this.mCurrency = Long.valueOf(Long.parseLong(LoginUtils.getLoginUserBean().getCoin6()));
        } else {
            this.mCurrency = Long.valueOf(0);
        }
        updataCurrencyUI();
    }

    private void updataCurrencyUI() {
        if (this.mCurrencyView != null) {
            this.mCurrencyView.setText(String.valueOf(this.mCurrency));
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        resetOutsideState();
        if (this.mDesPopWindow != null && this.mDesPopWindow.isShowing()) {
            this.mDesPopWindow.dismiss();
        }
        if (this.mRoomCallback != null) {
            this.mRoomCallback.onDismiss();
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    private void showError() {
        this.mHandler.post(new Runnable() {
            public void run() {
                Toast.makeText(GiftBoxDialog.this.mContext, GiftConfigUtil.LOAD_GIFT_ERROR, 0).show();
            }
        });
    }

    private void getUserInfo() {
        if (this.mUserInfoEngine == null) {
            this.mUserInfoEngine = new UserInfoEngine(new CallBack() {
                public void handleInfo(UserBean userBean) {
                    GlobleValue.setUserBean(userBean);
                    GiftBoxDialog.this.loadCurrency();
                }

                public void error(int i) {
                }

                public void handleErrorInfo(String str, String str2) {
                }
            });
        }
        this.mUserInfoEngine.getUserInfo(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), LoginUtils.getLoginUID());
    }

    public void setToUser(String str, String str2) {
        this.toTempUserID = str2;
        this.toTempUserName = str;
        this.isOutside = true;
        updateToUserUI();
    }

    private void updateToUserUI() {
        if (this.mGiveUserView != null) {
            if (this.isOutside) {
                if (!TextUtils.isEmpty(this.toTempUserName)) {
                    this.mGiveUserView.setText(this.toTempUserName);
                }
            } else if (this.toUserName != null && !this.toUserName.equals("")) {
                this.mGiveUserView.setText(this.toUserName);
            } else if (this.mRoominfoBean != null && !this.mIsFamilyRoom) {
                this.mGiveUserView.setText(this.mRoominfoBean.getAlias());
            } else if (this.mIsFamilyRoom) {
                this.mGiveUserView.setText("");
            }
            if (this.mSelectGiftInfo == null) {
                return;
            }
            if (GiftIdStrs.SUPER_FIREWORKS.equals(this.mSelectGiftInfo.selectedGiftId)) {
                this.mGiveUserView.setText("主播及房间注册玩家");
            } else if ("99".equals(this.mSelectGiftInfo.selectedGiftId)) {
                this.mGiveUserView.setText("房间注册玩家");
            } else if (GiftIdStrs.SMALL_FIREWORKS.equals(this.mSelectGiftInfo.selectedGiftId)) {
                this.mGiveUserView.setText("主播和房管");
            }
        }
    }

    public void setGiftPosition(String str) {
        if (str != null) {
            if (this.mSelectGiftInfo != null) {
                cleanSelectGiftState();
            }
            int i = -1;
            for (WrapGiftType typeGiftList : this.mDisplayWrapTypeList) {
                i++;
                int i2 = -1;
                for (Gift id : typeGiftList.getTypeGiftList()) {
                    i2++;
                    if (str.equals(id.getId())) {
                        selectGift(str, i, i2);
                        return;
                    }
                }
            }
            selectGift(str, getGotoPageIndex(), 0);
        }
    }

    private void selectGift(String str, int i, int i2) {
        WantGift wantGift = new WantGift(str, i, i2);
        if (this.mPageAdapter != null) {
            this.mPageAdapter.setSelectedGift(wantGift);
        }
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(i);
        }
    }

    public void show() {
        super.show();
        hideSystemInput();
        updateToUserUI();
        loadCurrency();
    }

    public void cleanDada() {
        detachEvent();
    }

    private void hideSystemInput() {
        String userInputNumbers = getUserInputNumbers();
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        this.number_edit.requestFocus();
        inputMethodManager.hideSoftInputFromWindow(this.number_edit.getWindowToken(), 0);
        this.input_number_layout.setVisibility(8);
        this.giftContent.setVisibility(0);
        setGiftNumber(userInputNumbers);
    }

    protected void showSystemInput() {
        this.giftContent.setVisibility(8);
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        this.number_edit.requestFocus();
        this.number_edit.setText("");
        inputMethodManager.showSoftInput(this.number_edit, 2);
        this.giftContent.post(new Runnable() {
            public void run() {
                GiftBoxDialog.this.input_number_layout.setVisibility(0);
            }
        });
    }

    private String getUserInputNumbers() {
        return this.number_edit.getText().toString().trim();
    }
}
