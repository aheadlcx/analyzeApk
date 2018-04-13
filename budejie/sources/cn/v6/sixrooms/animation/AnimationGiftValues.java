package cn.v6.sixrooms.animation;

import android.content.Context;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.animation.GiftAnimationManager.CallBackGiftBitmap;
import cn.v6.sixrooms.bean.BeanAnimation;
import cn.v6.sixrooms.bean.BeanStarRandom;
import cn.v6.sixrooms.bean.GiftItemBean;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.UtilFile;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R$styleable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnimationGiftValues {
    private static int a = -50;
    private static String[] b = new String[]{"gift/leve1_0.json", "gift/leve2_0.json", "gift/leve3_0.json", "", "gift/leve5_0.json"};
    private static int c = 480;
    private static int d = 505;
    private static int e = 475;
    public static final int[] enterBgY = new int[5];
    public static final int[] enterText1X = new int[5];
    public static final int[] enterText1Y = new int[5];
    public static final int[] enterTimeArray = new int[]{200, 200, 200, 500, R$styleable.Theme_Custom_recyclerview_load_image_background};
    private static int f = 125;
    private static int g = 525;
    public static final int[] giftEnterX = new int[5];
    public static final int[] giftEnterY = new int[5];
    private static float h = 0.0f;
    private static int i = -22;
    private static int j = 20;
    private static int k = -150;
    private static int l = 148;
    public static final int[] level4Bg1ShowTime = new int[]{0, 300};
    public static final int[] level4Bg2ShowTime = new int[]{200, 600};
    public static final int[] level4Bg3ShowTime = new int[]{level4Bg2ShowTime[1], enterTimeArray[3]};
    public static final int[] level4SunShowTime = new int[]{level4Bg3ShowTime[0], level4Bg3ShowTime[1]};
    public static int level5YOffset = 300;
    private static int m = 148;
    public static int maxTextNumWidth = 200;
    public static int maxTextWidth = R$styleable.Theme_Custom_shape_cmt_like4_bg;
    private static boolean n = false;
    private static float[] o = new float[]{-0.34f, -0.34f, -0.36f, -0.41f, -0.34f};
    public static final int[] outTimeArray = new int[]{200, 200, 200, 200, 200};
    public static final int[] runTime = new int[]{SecExceptionCode.SEC_ERROR_SIMULATORDETECT, 2100, 3300, 4500, 5100};
    public static final int scrollTime = 150;
    public static int[] scrollYArray = new int[]{-170, -170, -170, -170, -170};
    public static final int senderFontSize = 13;
    public static final int[] text4OffsetX = new int[5];
    public static final int[] textVerticalMargin = new int[]{6, 6, 13, 13, 13};

    private static int a(Context context) {
        return (d(context) - c(context)) / 2;
    }

    private static int a(Context context, int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return (int) (((float) c(context)) * o[i - 1]);
            default:
                return 0;
        }
    }

    public static int getGiftWidth() {
        return l;
    }

    public static int getGiftHeight() {
        return m;
    }

    private static float b(Context context) {
        return ((float) c(context)) / 720.0f;
    }

    private static int c(Context context) {
        int i = context.getResources().getDisplayMetrics().widthPixels;
        if (context.getResources().getConfiguration().orientation == 2) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        return i;
    }

    private static int d(Context context) {
        int i = context.getResources().getDisplayMetrics().heightPixels;
        if (context.getResources().getConfiguration().orientation == 2) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        return i;
    }

    public static void initAnimationInfo(Context context) {
        if (!n) {
            n = true;
            int playerHeight = DisPlayUtil.getPlayerHeight(context);
            float b = b(context);
            int messureTextWidth = (int) DisPlayUtil.messureTextWidth((float) DensityUtil.sp2px(13.0f), "啊啊啊啊啊啊啊啊啊啊");
            maxTextWidth = messureTextWidth;
            h = (((float) messureTextWidth) / 2.0f) / (100.0f * b);
            l = (int) (130.0f * b);
            m = (int) (130.0f * b);
            a = (int) (-50.0f * b);
            text4OffsetX[0] = (int) (7.0f * b);
            text4OffsetX[1] = (int) (7.0f * b);
            text4OffsetX[2] = (int) (10.0f * b);
            text4OffsetX[3] = (int) (-3.0f * b);
            text4OffsetX[4] = (int) (10.0f * b);
            giftEnterX[0] = (int) (410.0f * b);
            giftEnterX[1] = (int) (410.0f * b);
            giftEnterX[2] = (int) (410.0f * b);
            giftEnterX[3] = (int) (410.0f * b);
            giftEnterX[4] = (int) (410.0f * b);
            giftEnterY[0] = (int) (((float) playerHeight) + (120.0f * b));
            giftEnterY[1] = (int) (((float) playerHeight) + (135.0f * b));
            giftEnterY[2] = (int) (((float) playerHeight) + (135.0f * b));
            giftEnterY[3] = (int) (((float) playerHeight) + (135.0f * b));
            giftEnterY[4] = (int) (((float) playerHeight) + (100.0f * b));
            enterBgY[0] = (int) (((float) playerHeight) + (85.0f * b));
            enterBgY[1] = (int) (((float) playerHeight) + (100.0f * b));
            enterBgY[2] = (int) (((float) playerHeight) + (100.0f * b));
            enterBgY[3] = (int) (((float) playerHeight) + (75.0f * b));
            enterBgY[4] = (int) (((float) playerHeight) + (75.0f * b));
            f = (int) (125.0f * b);
            g = (int) (525.0f * b);
            d = (int) (((float) enterBgY[3]) + (35.0f * b));
            k = (int) (-150.0f * b);
            e = (int) (495.0f * b);
            c = (int) (((float) playerHeight) + (80.0f * b));
            j = (int) (20.0f * b);
            enterText1Y[0] = (int) (((float) playerHeight) + (200.0f * b));
            enterText1Y[1] = (int) (((float) playerHeight) + (220.0f * b));
            enterText1Y[2] = (int) (((float) playerHeight) + (240.0f * b));
            enterText1Y[3] = (int) (((float) playerHeight) + (210.0f * b));
            enterText1Y[4] = (int) (((float) playerHeight) + (180.0f * b));
            enterText1X[0] = (int) (110.0f * b);
            enterText1X[1] = (int) (110.0f * b);
            enterText1X[2] = (int) (110.0f * b);
            enterText1X[3] = (int) (110.0f * b);
            enterText1X[4] = (int) (110.0f * b);
            i = (int) (-22.0f * b);
            textVerticalMargin[0] = (int) (3.0f * b);
            textVerticalMargin[1] = (int) (3.0f * b);
            textVerticalMargin[2] = (int) (3.0f * b);
            textVerticalMargin[3] = (int) (6.0f * b);
            textVerticalMargin[4] = (int) (13.0f * b);
            level5YOffset = (int) (400.0f * b);
            scrollYArray[0] = (int) (-230.0f * b);
            scrollYArray[1] = (int) (-230.0f * b);
            scrollYArray[2] = (int) (-230.0f * b);
            scrollYArray[3] = (int) (-230.0f * b);
            scrollYArray[4] = (int) (-230.0f * b);
            maxTextNumWidth = (int) (100.0f * b);
        }
    }

    public static GiftAnimation getGiftAnimation(Context context, CallBackGiftBitmap callBackGiftBitmap, GiftItemBean giftItemBean) {
        ArrayList arrayList;
        int level = giftItemBean.getLevel();
        GiftAnimation giftAnimation = new GiftAnimation(level);
        float b = b(context);
        if (level == 4) {
            ArrayList arrayList2 = new ArrayList();
            BeanAnimation beanAnimation = new BeanAnimation();
            int[][] iArr = new int[1][];
            iArr[0] = new int[]{R.drawable.leve4_0, R.drawable.leve4_1, R.drawable.leve4_2, R.drawable.leve4_3, R.drawable.leve4_4, R.drawable.leve4_5, R.drawable.leve4_6, R.drawable.leve4_7, R.drawable.leve4_8, R.drawable.leve4_9};
            beanAnimation.drawableArray = iArr;
            beanAnimation.translateX = (int) (50.0f * b);
            beanAnimation.translateY = enterBgY[level - 1];
            beanAnimation.drawableTime = new int[][]{level4Bg1ShowTime};
            beanAnimation.scaleW = b;
            beanAnimation.scaleH = b;
            BeanAnimation beanAnimation2 = new BeanAnimation();
            beanAnimation2.fillAfter = false;
            beanAnimation2.translateY = (int) (((float) enterBgY[level - 1]) + (130.0f * b));
            beanAnimation2.translateX = c(context) / 2;
            beanAnimation2.drawableId = R.drawable.gift_level4_bg;
            beanAnimation2.scaleW = 0.0f;
            beanAnimation2.scaleH = 0.0f;
            beanAnimation2.scaleWInfo = new float[]{b};
            beanAnimation2.scaleWTime = new int[][]{level4Bg2ShowTime};
            beanAnimation2.scaleHInfo = new float[]{b};
            beanAnimation2.scaleHTime = new int[][]{level4Bg2ShowTime};
            BeanAnimation beanAnimation3 = new BeanAnimation();
            beanAnimation3.translateY = enterBgY[level - 1];
            beanAnimation3.showTime = level4Bg3ShowTime;
            beanAnimation3.drawableId = R.drawable.gift_level4_bg;
            beanAnimation3.scaleW = b;
            beanAnimation3.scaleH = b;
            BeanAnimation beanAnimation4 = new BeanAnimation();
            beanAnimation4.translateY = c;
            beanAnimation4.translateYInfo = new int[]{i};
            beanAnimation4.translateYTime = new int[][]{level4SunShowTime};
            beanAnimation4.translateX = e;
            beanAnimation4.showTime = level4SunShowTime;
            beanAnimation4.drawableId = R.drawable.level4_10;
            beanAnimation4.scaleW = b;
            beanAnimation4.scaleH = b;
            arrayList2.add(beanAnimation);
            arrayList2.add(beanAnimation2);
            arrayList2.add(beanAnimation3);
            arrayList2.add(beanAnimation4);
            BeanAnimation[] a = a(giftItemBean);
            for (int i = 0; i < 5; i++) {
                beanAnimation2 = a[i];
                beanAnimation2.showTime = level4Bg3ShowTime;
                arrayList2.add(beanAnimation2);
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                BeanAnimation beanAnimation5 = (BeanAnimation) it.next();
                beanAnimation5.offsetLandX = a(context);
                beanAnimation5.offsetLandY = a(context, level);
            }
            giftAnimation.animationEnter = new SurfaceAnimation(callBackGiftBitmap, arrayList2, enterTimeArray[level - 1], giftAnimation.getModeId());
            arrayList = arrayList2;
        } else {
            arrayList = getEnterAnimations(context, giftItemBean);
            giftAnimation.animationEnter = new SurfaceAnimation(callBackGiftBitmap, arrayList, enterTimeArray[level - 1], giftAnimation.getModeId());
        }
        giftAnimation.animationRunning = new SurfaceAnimation(callBackGiftBitmap, getRunningAnimations(context, arrayList, giftItemBean), runTime[level - 1], giftAnimation.getModeId());
        return giftAnimation;
    }

    private static BeanAnimation[] a(GiftItemBean giftItemBean) {
        BeanAnimation[] beanAnimationArr = new BeanAnimation[5];
        for (int i = 0; i < 5; i++) {
            beanAnimationArr[i] = new BeanAnimation();
        }
        a(beanAnimationArr, giftItemBean);
        return beanAnimationArr;
    }

    private static void a(BeanAnimation[] beanAnimationArr, GiftItemBean giftItemBean) {
        int i;
        int level = giftItemBean.getLevel();
        String[] strArr = new String[]{giftItemBean.getFrom()};
        beanAnimationArr[0].text = strArr;
        beanAnimationArr[0].isSender = true;
        strArr = new String[]{"送给   "};
        beanAnimationArr[1].text = strArr;
        strArr = new String[]{giftItemBean.getTo()};
        beanAnimationArr[2].text = strArr;
        strArr = new String[]{giftItemBean.getNum(), "个"};
        beanAnimationArr[3].text = strArr;
        strArr = new String[]{"#000000"};
        beanAnimationArr[0].color = strArr;
        strArr = new String[]{"#ff0000"};
        beanAnimationArr[1].color = strArr;
        strArr = new String[]{"#000000"};
        beanAnimationArr[2].color = strArr;
        strArr = new String[]{"#000000", "#ff0000"};
        beanAnimationArr[3].color = strArr;
        int[] iArr = new int[]{DensityUtil.sp2px(13.0f)};
        beanAnimationArr[0].fontSize = iArr;
        iArr = new int[]{DensityUtil.sp2px(11.0f)};
        beanAnimationArr[1].fontSize = iArr;
        iArr = new int[]{DensityUtil.sp2px(13.0f)};
        beanAnimationArr[2].fontSize = iArr;
        iArr = new int[]{DensityUtil.sp2px(13.0f), DensityUtil.sp2px(13.0f)};
        beanAnimationArr[3].fontSize = iArr;
        beanAnimationArr[4].drawableId = R.drawable.phone_gift_def_bg;
        beanAnimationArr[4].drawableUrl = giftItemBean.getOriginalName();
        float messureTextWidth = DisPlayUtil.messureTextWidth((float) beanAnimationArr[0].fontSize[0], giftItemBean.getFrom());
        if (messureTextWidth < ((float) maxTextWidth)) {
            i = (int) ((((float) maxTextWidth) - messureTextWidth) / 2.0f);
        } else {
            i = 0;
        }
        beanAnimationArr[0].translateX = i + enterText1X[level - 1];
        beanAnimationArr[0].translateY = enterText1Y[level - 1];
        beanAnimationArr[4].translateX = giftEnterX[level - 1];
        beanAnimationArr[4].translateY = giftEnterY[level - 1];
        beanAnimationArr[4].isGift = true;
        float messureTextWidth2 = DisPlayUtil.messureTextWidth((float) beanAnimationArr[0].fontSize[0], giftItemBean.getTo()) + DisPlayUtil.messureTextWidth((float) beanAnimationArr[1].fontSize[0], beanAnimationArr[1].text[0]);
        float messureTextWidth3 = DisPlayUtil.messureTextWidth((float) beanAnimationArr[1].fontSize[0], beanAnimationArr[1].text[0]);
        int messureTextHeight = DisPlayUtil.messureTextHeight((float) beanAnimationArr[0].fontSize[0]);
        i = (int) (((float) beanAnimationArr[0].translateX) - ((messureTextWidth2 - messureTextWidth) / 2.0f));
        if (((float) i) < ((float) enterText1X[level - 1]) - (messureTextWidth3 / 2.0f)) {
            i = (int) (((float) enterText1X[level - 1]) - (messureTextWidth3 / 2.0f));
        }
        beanAnimationArr[1].translateX = i;
        beanAnimationArr[1].translateY = (beanAnimationArr[0].translateY + messureTextHeight) + textVerticalMargin[level - 1];
        beanAnimationArr[2].translateX = (int) (((float) beanAnimationArr[1].translateX) + messureTextWidth3);
        beanAnimationArr[2].translateY = (beanAnimationArr[0].translateY + messureTextHeight) + textVerticalMargin[level - 1];
        beanAnimationArr[3].translateY = beanAnimationArr[0].translateY + ((beanAnimationArr[2].translateY - beanAnimationArr[0].translateY) / 2);
        float messureTextWidth4 = DisPlayUtil.messureTextWidth((float) beanAnimationArr[3].fontSize[0], giftItemBean.getNum() + "个");
        messureTextWidth2 = 0.0f;
        if (messureTextWidth4 < ((float) maxTextNumWidth)) {
            messureTextWidth2 = (((float) maxTextNumWidth) - messureTextWidth4) / 2.0f;
        }
        beanAnimationArr[3].translateX = (int) (messureTextWidth2 + ((float) ((text4OffsetX[level - 1] + beanAnimationArr[4].translateX) + l)));
    }

    public static ArrayList<BeanAnimation> getRunningAnimations(Context context, ArrayList<BeanAnimation> arrayList, GiftItemBean giftItemBean) {
        ArrayList arrayList2 = new ArrayList();
        BeanAnimation beanAnimation = null;
        BeanAnimation beanAnimation2 = null;
        int i = 0;
        int i2 = 0;
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            int i4;
            BeanAnimation beanAnimation3 = (BeanAnimation) arrayList.get(i3);
            if (beanAnimation3.fillAfter) {
                BeanAnimation beanAnimation4;
                BeanAnimation cloneEndStatus = beanAnimation3.cloneEndStatus();
                if (cloneEndStatus != null) {
                    if (cloneEndStatus.isSender) {
                        i4 = i3 + i2;
                        beanAnimation4 = beanAnimation2;
                        beanAnimation2 = cloneEndStatus;
                    } else if (cloneEndStatus.isGift) {
                        i4 = i;
                        beanAnimation2 = beanAnimation;
                        beanAnimation4 = cloneEndStatus;
                    } else {
                        i4 = i;
                        beanAnimation4 = beanAnimation2;
                        beanAnimation2 = beanAnimation;
                    }
                    arrayList2.add(cloneEndStatus);
                } else {
                    i4 = i;
                    beanAnimation4 = beanAnimation2;
                    beanAnimation2 = beanAnimation;
                }
                beanAnimation = beanAnimation2;
                beanAnimation2 = beanAnimation4;
                i = i4;
                i4 = i2;
            } else {
                i4 = i2 - 1;
            }
            i3++;
            i2 = i4;
        }
        int level = giftItemBean.getLevel();
        float b = b(context);
        if (level > 1) {
            int[][] iArr;
            int[][] iArr2;
            if (level <= 3) {
                beanAnimation3 = new BeanAnimation();
                iArr = new int[1][];
                iArr[0] = new int[]{R.drawable.level2_0, R.drawable.level2_1, R.drawable.level2_2, R.drawable.level2_3, R.drawable.level2_4, R.drawable.level2_5, R.drawable.level2_6, R.drawable.level2_7, R.drawable.level2_7};
                beanAnimation3.drawableArray = iArr;
                beanAnimation3.drawableFps = 5;
                iArr = new int[1][];
                iArr[0] = new int[]{0, runTime[level - 1]};
                beanAnimation3.drawableTime = iArr;
                beanAnimation3.translateY = (int) (((float) beanAnimation2.translateY) + ((((float) m) - (210.0f * b)) / 2.0f));
                beanAnimation3.translateX = (int) (((float) beanAnimation2.translateX) + ((((float) l) - (210.0f * b)) / 2.0f));
                beanAnimation3.scaleW = b;
                beanAnimation3.scaleH = b;
                arrayList2.add(beanAnimation3);
            }
            int[][] iArr3;
            if (level == 3) {
                beanAnimation2 = new BeanAnimation();
                iArr3 = new int[1][];
                iArr3[0] = new int[]{R.drawable.level3_0, R.drawable.level3_1, R.drawable.level3_2, R.drawable.level3_3, R.drawable.level3_4, R.drawable.level3_5, R.drawable.level3_6, R.drawable.level3_7};
                beanAnimation2.drawableArray = iArr3;
                beanAnimation2.drawableFps = 12;
                iArr3 = new int[1][];
                iArr3[0] = new int[]{0, runTime[level - 1]};
                beanAnimation2.drawableTime = iArr3;
                beanAnimation2.translateY = (int) (((float) ((BeanAnimation) arrayList2.get(0)).translateY) + (60.0f * b));
                beanAnimation2.translateX = (int) (102.0f * b);
                beanAnimation2.scaleW = b;
                beanAnimation2.scaleH = b;
                arrayList2.add(0, beanAnimation2);
            } else if (level == 4) {
                beanAnimation2 = new BeanAnimation();
                iArr3 = new int[1][];
                iArr3[0] = new int[]{R.drawable.level4_12, R.drawable.level4_13, R.drawable.level4_14, R.drawable.level4_15, R.drawable.level4_16, R.drawable.level4_17, R.drawable.level4_18, R.drawable.level4_19, R.drawable.level4_20, R.drawable.level4_21};
                beanAnimation2.drawableArray = iArr3;
                beanAnimation2.drawableFps = 10;
                iArr3 = new int[1][];
                iArr3[0] = new int[]{0, runTime[level - 1]};
                beanAnimation2.drawableTime = iArr3;
                beanAnimation2.translateY = (int) (((float) ((BeanAnimation) arrayList2.get(0)).translateY) + (12.0f * b));
                beanAnimation2.translateX = (int) (47.0f * b);
                beanAnimation2.scaleW = b;
                beanAnimation2.scaleH = b;
                beanAnimation3 = new BeanAnimation();
                beanAnimation3.drawableId = R.drawable.level4_11;
                beanAnimation3.showTime = new int[]{0, runTime[level - 1]};
                beanAnimation3.scaleW = b;
                beanAnimation3.scaleH = b;
                beanAnimation3.translateX = g;
                beanAnimation3.translateY = d;
                beanAnimation3.translateXInfo = new int[]{k};
                beanAnimation3.translateXTime = new int[][]{beanAnimation3.showTime};
                beanAnimation3.alphaArray = new int[]{-255};
                iArr = new int[1][];
                iArr[0] = new int[]{runTime[level - 1] - 2000, runTime[level - 1]};
                beanAnimation3.alphaTime = iArr;
                r6 = new BeanAnimation();
                r6.drawableId = R.drawable.level4_11;
                r6.showTime = beanAnimation3.showTime;
                r6.scaleW = b;
                r6.scaleH = b;
                r6.translateX = f;
                r6.translateY = d;
                r6.translateXInfo = new int[]{k * -1};
                r6.translateXTime = beanAnimation3.translateXTime;
                r6.alphaArray = new int[]{-255};
                r6.alphaTime = beanAnimation3.alphaTime;
                arrayList2.add(beanAnimation2);
                arrayList2.add(beanAnimation3);
                arrayList2.add(r6);
            } else if (level == 5) {
                beanAnimation3 = new BeanAnimation();
                beanAnimation3.isSetLandOffset = true;
                beanAnimation3.offsetLandX = 0;
                beanAnimation3.offsetLandY = 0;
                iArr2 = new int[1][];
                iArr2[0] = new int[]{R.drawable.level5_0, R.drawable.level5_1, R.drawable.level5_2, R.drawable.level5_3, R.drawable.level5_4};
                beanAnimation3.drawableArray = iArr2;
                beanAnimation3.drawableFps = 10;
                iArr2 = new int[1][];
                iArr2[0] = new int[]{0, runTime[level - 1]};
                beanAnimation3.drawableTime = iArr2;
                beanAnimation3.scaleW = b;
                beanAnimation3.scaleH = b;
                beanAnimation3.landScaleW = (((float) d(context)) * b) / ((float) c(context));
                beanAnimation2 = new BeanAnimation();
                iArr = new int[1][];
                iArr[0] = new int[]{R.drawable.level5_5, R.drawable.level5_6, R.drawable.level5_7, R.drawable.level5_8, R.drawable.level5_9};
                beanAnimation2.drawableArray = iArr;
                beanAnimation2.isShowInLand = false;
                beanAnimation2.drawableFps = 15;
                beanAnimation2.translateY = enterBgY[level - 1] + j;
                iArr = new int[1][];
                iArr[0] = new int[]{0, runTime[level - 1]};
                beanAnimation2.drawableTime = iArr;
                beanAnimation2.scaleW = b;
                beanAnimation2.scaleH = b;
                r6 = new BeanAnimation();
                int[][] iArr4 = new int[1][];
                iArr4[0] = new int[]{R.drawable.level5_7, R.drawable.level5_8, R.drawable.level5_9, R.drawable.level5_5, R.drawable.level5_6};
                r6.drawableArray = iArr4;
                r6.isShowInLand = false;
                r6.drawableFps = 15;
                r6.degrees = 180.0f;
                r6.translateY = enterBgY[level - 1] + j;
                r6.translateX = (int) (636.0f * b);
                iArr4 = new int[1][];
                iArr4[0] = new int[]{0, runTime[level - 1]};
                r6.drawableTime = iArr4;
                r6.scaleW = b;
                r6.scaleH = b;
                BeanAnimation beanAnimation5 = new BeanAnimation();
                int[][] iArr5 = new int[1][];
                iArr5[0] = new int[]{R.drawable.level5_10, R.drawable.level5_11, R.drawable.level5_12, R.drawable.level5_13, R.drawable.level5_14, R.drawable.level5_15, R.drawable.level5_16, R.drawable.level5_17};
                beanAnimation5.drawableArray = iArr5;
                beanAnimation5.drawableTime = new int[][]{new int[]{0, 360}};
                beanAnimation5.distanceShowInfo = ErrorCode.SERVER_SESSIONSTATUS;
                beanAnimation5.runTime = 360;
                beanAnimation5.translateY = (int) (((float) enterBgY[level - 1]) + (20.0f * b));
                beanAnimation5.translateX = (int) (100.0f * b);
                beanAnimation5.scaleW = b;
                beanAnimation5.scaleH = b;
                arrayList2.add(beanAnimation3);
                arrayList2.add(0, beanAnimation2);
                arrayList2.add(0, r6);
                i += 2;
                arrayList2.add(beanAnimation5);
            }
            if (level > 3) {
                beanAnimation3 = new BeanAnimation();
                beanAnimation3.isCanClone = false;
                beanAnimation3.drawableId = R.drawable.level4_22;
                beanAnimation3.distanceShowInfo = 2200;
                beanAnimation3.runTime = 800;
                beanAnimation3.scaleW = h;
                beanAnimation3.scaleH = 0.0f;
                beanAnimation3.translateY = (int) (((float) beanAnimation.translateY) - (Math.abs(DisPlayUtil.getTextAscent((float) beanAnimation.fontSize[0])) / 2.0f));
                float messureTextWidth = DisPlayUtil.messureTextWidth((float) beanAnimation.fontSize[0], beanAnimation.text[0]);
                beanAnimation3.scaleInCenter = false;
                beanAnimation3.scaleW = 0.0f;
                if (messureTextWidth < ((float) (maxTextWidth / 2))) {
                    beanAnimation3.translateX = (int) ((((float) beanAnimation.translateX) - (100.0f * h)) - ((((float) (maxTextWidth / 2)) - messureTextWidth) / 2.0f));
                    beanAnimation3.translateXInfo = new int[]{(int) r6, (int) ((messureTextWidth + r6) + (196.0f * h))};
                    iArr2 = new int[2][];
                    iArr2[0] = new int[]{0, 360};
                    iArr2[1] = new int[]{360, beanAnimation3.runTime};
                    beanAnimation3.translateXTime = iArr2;
                    beanAnimation3.translateXOffset = new int[]{0, (int) r6};
                } else {
                    beanAnimation3.translateX = (int) (((float) beanAnimation.translateX) - (150.0f * h));
                    beanAnimation3.translateXOffset = new int[]{0, (int) (messureTextWidth - (50.0f * h))};
                    beanAnimation3.translateXInfo = new int[]{(int) (messureTextWidth - (50.0f * h)), (int) (200.0f * h)};
                    iArr2 = new int[2][];
                    iArr2[0] = new int[]{0, beanAnimation3.runTime - 210};
                    iArr2[1] = new int[]{beanAnimation3.runTime - 210, beanAnimation3.runTime};
                    beanAnimation3.translateXTime = iArr2;
                }
                beanAnimation3.alphaArray = new int[]{-255};
                iArr2 = new int[1][];
                iArr2[0] = new int[]{beanAnimation3.runTime - 300, beanAnimation3.runTime};
                beanAnimation3.alphaTime = iArr2;
                beanAnimation3.scaleWInfo = new float[]{h, 0.0f, -h};
                iArr2 = new int[3][];
                iArr2[1] = new int[]{360, beanAnimation3.runTime - 210};
                iArr2[2] = new int[]{beanAnimation3.runTime - 210, beanAnimation3.runTime};
                beanAnimation3.scaleWTime = iArr2;
                beanAnimation3.scaleWOffset = new float[]{0.0f, h, h};
                beanAnimation3.translateYInfo = new int[]{0, 0, 0};
                beanAnimation3.translateYOffset = new int[]{0, (int) (-48.0f * b), (int) (-48.0f * b)};
                iArr2 = new int[3][];
                iArr2[1] = new int[]{360, beanAnimation3.runTime - 210};
                iArr2[2] = new int[]{beanAnimation3.runTime - 210, beanAnimation3.runTime};
                beanAnimation3.translateYTime = iArr2;
                beanAnimation3.scaleHInfo = new float[]{b, 0.0f, -b};
                iArr2 = new int[3][];
                iArr2[1] = new int[]{360, beanAnimation3.runTime - 210};
                iArr2[2] = new int[]{beanAnimation3.runTime - 210, beanAnimation3.runTime};
                beanAnimation3.scaleHTime = iArr2;
                beanAnimation3.scaleHOffset = new float[]{0.0f, b, b};
                beanAnimation3.scaleDy = new float[]{(-b) / 2.0f, b / 2.0f};
                iArr2 = new int[2][];
                iArr2[0] = new int[]{0, 360};
                iArr2[1] = new int[]{beanAnimation3.runTime - 210, beanAnimation3.runTime};
                beanAnimation3.scaleOffsetTime = iArr2;
                beanAnimation3.alphaArray = new int[]{255, 0, -255};
                beanAnimation3.alphaOffset = new int[]{0, 255, 0};
                iArr2 = new int[3][];
                iArr2[1] = new int[]{360, beanAnimation3.runTime - 360};
                iArr2[2] = new int[]{beanAnimation3.runTime - 360, beanAnimation3.runTime};
                beanAnimation3.alphaTime = iArr2;
                arrayList2.add(i, beanAnimation3);
            }
            if (level == 5) {
                beanAnimation3 = new BeanAnimation();
                beanAnimation3.isCanClone = false;
                InterfaceAnimationDraw beanStarRandom = new BeanStarRandom(beanAnimation.translateY + DisPlayUtil.messureTextHeight((float) beanAnimation.fontSize[0]), b, d(context));
                beanAnimation3.drawableId = R.drawable.level5_star;
                beanAnimation3.mInterfaceAnimationDraw = beanStarRandom;
                beanAnimation3.showTime = new int[]{0, runTime[level - 1]};
                arrayList2.add(beanAnimation3);
            }
        }
        return a(context, arrayList2, level);
    }

    public static ArrayList<BeanAnimation> getOutAnimations(Context context, ArrayList<BeanAnimation> arrayList, int i) {
        ArrayList arrayList2 = new ArrayList();
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BeanAnimation cloneEndStatus = ((BeanAnimation) it.next()).cloneEndStatus();
                if (cloneEndStatus != null) {
                    int[][] iArr = new int[1][];
                    iArr[0] = new int[]{0, outTimeArray[i - 1]};
                    cloneEndStatus.translateXTime = iArr;
                    cloneEndStatus.translateXInfo = new int[]{context.getResources().getDisplayMetrics().widthPixels};
                    iArr = new int[1][];
                    iArr[0] = new int[]{0, outTimeArray[i - 1]};
                    cloneEndStatus.alphaTime = iArr;
                    cloneEndStatus.alphaArray = new int[]{cloneEndStatus.alpha * -1};
                    arrayList2.add(cloneEndStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a(context, arrayList2, i);
    }

    public static ArrayList<BeanAnimation> getEnterAnimations(Context context, GiftItemBean giftItemBean) {
        ArrayList arrayList;
        JSONException jSONException;
        int level = giftItemBean.getLevel();
        float b = b(context);
        try {
            int i;
            String str = b[level - 1];
            ArrayList arrayList2 = new ArrayList();
            JSONArray jSONArray = new JSONArray(UtilFile.readAssertResource(context, str));
            int length = jSONArray.length();
            for (i = 0; i < length; i++) {
                String[] split;
                int i2;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                BeanAnimation beanAnimation = new BeanAnimation();
                if (!jSONObject.isNull("picname")) {
                    beanAnimation.drawableId = context.getResources().getIdentifier(jSONObject.getString("picname"), "drawable", context.getPackageName());
                }
                if (!jSONObject.isNull("color")) {
                    beanAnimation.color = jSONObject.getString("color").split(",");
                }
                if (!jSONObject.isNull("fontSize")) {
                    split = jSONObject.getString("fontSize").split(",");
                    beanAnimation.fontSize = new int[split.length];
                    for (i2 = 0; i2 < split.length; i2++) {
                        beanAnimation.fontSize[i2] = DensityUtil.sp2px((float) Integer.parseInt(split[i2]));
                    }
                }
                if (!jSONObject.isNull("alpha")) {
                    beanAnimation.alpha = jSONObject.getInt("alpha");
                }
                if (!jSONObject.isNull("translateX")) {
                    beanAnimation.translateX = jSONObject.getInt("translateX");
                }
                if (!jSONObject.isNull("translateY")) {
                    beanAnimation.translateX = jSONObject.getInt("translateY");
                }
                if (!jSONObject.isNull("scaleW")) {
                    beanAnimation.scaleW = (float) jSONObject.getDouble("scaleW");
                }
                if (!jSONObject.isNull("scaleH")) {
                    beanAnimation.scaleH = (float) jSONObject.getDouble("scaleH");
                }
                if (!jSONObject.isNull("degrees")) {
                    beanAnimation.degrees = (float) jSONObject.getDouble("degrees");
                }
                if (!jSONObject.isNull("scaleWTime")) {
                    split = jSONObject.getString("scaleWTime").split(",");
                    beanAnimation.scaleWTime = (int[][]) Array.newInstance(Integer.TYPE, new int[]{split.length / 2, 2});
                    for (i2 = 0; i2 < split.length / 2; i2 += 2) {
                        beanAnimation.scaleWTime[i2 / 2][0] = Integer.parseInt(split[i2]);
                        beanAnimation.scaleWTime[i2 / 2][1] = Integer.parseInt(split[i2 + 1]);
                    }
                }
                if (!jSONObject.isNull("scaleWInfo")) {
                    split = jSONObject.getString("scaleWInfo").split(",");
                    beanAnimation.scaleWInfo = new float[split.length];
                    for (i2 = 0; i2 < split.length; i2++) {
                        beanAnimation.scaleWInfo[i2] = Float.parseFloat(split[i2]);
                    }
                }
                if (!jSONObject.isNull("scaleHTime")) {
                    split = jSONObject.getString("scaleHTime").split(",");
                    beanAnimation.scaleWTime = (int[][]) Array.newInstance(Integer.TYPE, new int[]{split.length / 2, 2});
                    for (i2 = 0; i2 < split.length / 2; i2 += 2) {
                        beanAnimation.scaleHTime[i2 / 2][0] = Integer.parseInt(split[i2]);
                        beanAnimation.scaleHTime[i2 / 2][1] = Integer.parseInt(split[i2 + 1]);
                    }
                }
                if (!jSONObject.isNull("scaleHInfo")) {
                    split = jSONObject.getString("scaleHInfo").split(",");
                    beanAnimation.scaleHInfo = new float[split.length];
                    for (i2 = 0; i2 < split.length; i2++) {
                        beanAnimation.scaleHInfo[i2] = Float.parseFloat(split[i2]);
                    }
                }
                if (!jSONObject.isNull("translateXTime")) {
                    split = jSONObject.getString("translateXTime").split(",");
                    beanAnimation.translateXTime = (int[][]) Array.newInstance(Integer.TYPE, new int[]{split.length / 2, 2});
                    for (i2 = 0; i2 < split.length / 2; i2 += 2) {
                        beanAnimation.translateXTime[i2 / 2][0] = Integer.parseInt(split[i2]);
                        beanAnimation.translateXTime[i2 / 2][1] = Integer.parseInt(split[i2 + 1]);
                    }
                }
                if (!jSONObject.isNull("translateXInfo")) {
                    split = jSONObject.getString("translateXInfo").split(",");
                    beanAnimation.scaleHInfo = new float[split.length];
                    for (i2 = 0; i2 < split.length; i2++) {
                        beanAnimation.translateXInfo[i2] = Integer.parseInt(split[i2]);
                    }
                }
                if (!jSONObject.isNull("translateYTime")) {
                    split = jSONObject.getString("translateYTime").split(",");
                    beanAnimation.translateYTime = (int[][]) Array.newInstance(Integer.TYPE, new int[]{split.length / 2, 2});
                    for (i2 = 0; i2 < split.length / 2; i2 += 2) {
                        beanAnimation.translateYTime[i2 / 2][0] = Integer.parseInt(split[i2]);
                        beanAnimation.translateYTime[i2 / 2][1] = Integer.parseInt(split[i2 + 1]);
                    }
                }
                if (!jSONObject.isNull("translateYInfo")) {
                    split = jSONObject.getString("translateYInfo").split(",");
                    beanAnimation.translateYInfo = new int[split.length];
                    for (i2 = 0; i2 < split.length; i2++) {
                        beanAnimation.translateYInfo[i2] = Integer.parseInt(split[i2]);
                    }
                }
                if (!jSONObject.isNull("drawableTime")) {
                    split = jSONObject.getString("drawableTime").split(",");
                    beanAnimation.drawableTime = (int[][]) Array.newInstance(Integer.TYPE, new int[]{split.length / 2, 2});
                    for (i2 = 0; i2 < split.length / 2; i2 += 2) {
                        beanAnimation.drawableTime[i2 / 2][0] = Integer.parseInt(split[i2]);
                        beanAnimation.drawableTime[i2 / 2][1] = Integer.parseInt(split[i2 + 1]);
                    }
                }
                if (!jSONObject.isNull("alphaTime")) {
                    split = jSONObject.getString("alphaTime").split(",");
                    beanAnimation.alphaTime = (int[][]) Array.newInstance(Integer.TYPE, new int[]{split.length / 2, 2});
                    for (i2 = 0; i2 < split.length / 2; i2 += 2) {
                        beanAnimation.alphaTime[i2 / 2][0] = Integer.parseInt(split[i2]);
                        beanAnimation.alphaTime[i2 / 2][1] = Integer.parseInt(split[i2 + 1]);
                    }
                }
                if (!jSONObject.isNull("alphaArray")) {
                    String[] split2 = jSONObject.getString("alphaArray").split(",");
                    beanAnimation.alphaArray = new int[split2.length];
                    for (i2 = 0; i2 < split2.length; i2++) {
                        beanAnimation.alphaArray[i2] = Integer.parseInt(split2[i2]);
                    }
                }
                arrayList2.add(beanAnimation);
            }
            try {
                BeanAnimation beanAnimation2;
                i = arrayList2.size();
                for (int i3 = 0; i3 < i; i3++) {
                    beanAnimation2 = (BeanAnimation) arrayList2.get(i3);
                    if (level < 4) {
                        beanAnimation2.alpha = 0;
                        beanAnimation2.alphaArray = new int[]{255};
                        int[][] iArr = new int[1][];
                        iArr[0] = new int[]{0, enterTimeArray[level - 1]};
                        beanAnimation2.alphaTime = iArr;
                        iArr = new int[1][];
                        iArr[0] = new int[]{0, enterTimeArray[level - 1]};
                        beanAnimation2.translateYTime = iArr;
                        beanAnimation2.translateYInfo = new int[]{a};
                    }
                    if (i3 == 0) {
                        beanAnimation2.scaleW = b;
                        beanAnimation2.scaleH = b;
                        beanAnimation2.translateY = enterBgY[level - 1];
                    }
                }
                a(new BeanAnimation[]{(BeanAnimation) arrayList2.get(1), (BeanAnimation) arrayList2.get(2), (BeanAnimation) arrayList2.get(3), (BeanAnimation) arrayList2.get(5), (BeanAnimation) arrayList2.get(4)}, giftItemBean);
                if (level == 5) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        beanAnimation2 = (BeanAnimation) it.next();
                        beanAnimation2.translateY -= level5YOffset;
                        int[][] iArr2 = new int[1][];
                        iArr2[0] = new int[]{0, enterTimeArray[level - 1]};
                        beanAnimation2.translateYTime = iArr2;
                        iArr2 = new int[1][];
                        iArr2[0] = new int[]{0, enterTimeArray[level - 1]};
                        beanAnimation2.alphaTime = iArr2;
                        beanAnimation2.alphaArray = new int[]{255};
                        beanAnimation2.translateYInfo = new int[]{level5YOffset};
                    }
                }
                arrayList = arrayList2;
            } catch (JSONException e) {
                JSONException jSONException2 = e;
                arrayList = arrayList2;
                jSONException = jSONException2;
                jSONException.printStackTrace();
                return a(context, arrayList, level);
            }
        } catch (JSONException e2) {
            jSONException = e2;
            arrayList = null;
            jSONException.printStackTrace();
            return a(context, arrayList, level);
        }
        return a(context, arrayList, level);
    }

    private static ArrayList<BeanAnimation> a(Context context, ArrayList<BeanAnimation> arrayList, int i) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BeanAnimation beanAnimation = (BeanAnimation) it.next();
            if (!beanAnimation.isSetLandOffset) {
                beanAnimation.offsetLandX = a(context);
                beanAnimation.offsetLandY = a(context, i);
            }
        }
        return arrayList;
    }
}
