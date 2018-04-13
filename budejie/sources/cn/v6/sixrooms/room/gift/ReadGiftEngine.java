package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.gift.GiftTypes.WrapGiftType;
import cn.v6.sixrooms.surfaceanim.specialframe.util.SpecialSceneConfig;
import cn.v6.sixrooms.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ReadGiftEngine {
    public static final String VERSION = "ver";
    private static List<Gift> mGiftBeanList = new ArrayList();
    private static GiftConfig mGiftConfig;
    private static HashMap<String, Gift> mStockGiftMap = new HashMap();
    private List<WrapGiftType> mGiftWrapTypeList = new ArrayList();
    private boolean mIsMobileLive;
    private WrapGiftType mShineGiftWrapType;
    private List<String> mShineIdList;

    public ReadGiftEngine() {
        analysisJson();
    }

    private void analysisJson() {
        if (mGiftConfig == null) {
            InputStreamReader inputStreamReader = null;
            try {
                File file = new File(GiftConfigPresenter.GIFT_FILE_PATH);
                if (file.exists()) {
                    inputStreamReader = new InputStreamReader(new FileInputStream(file));
                } else {
                    inputStreamReader = new InputStreamReader(V6Coop.getInstance().getContext().getAssets().open(GiftConfigPresenter.GIFT_FILE_NAME));
                }
                JsonReader jsonReader = new JsonReader(new StringReader(FileUtil.inputStream2String(inputStreamReader)));
                jsonReader.setLenient(true);
                mGiftConfig = (GiftConfig) new Gson().fromJson(jsonReader, GiftConfig.class);
                V6Coop.firstGetGift = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public List<WrapGiftType> getDisplayGiftTypeList(boolean z) {
        this.mIsMobileLive = z;
        List<WrapGiftType> arrayList = new ArrayList();
        if (this.mGiftWrapTypeList == null || this.mGiftWrapTypeList.size() <= 0) {
            if (mGiftConfig == null) {
                analysisJson();
            }
            if (mGiftConfig != null) {
                try {
                    GiftTypes gifts = mGiftConfig.getGifts();
                    if (gifts == null) {
                        return arrayList;
                    }
                    gifts = gifts.clone();
                    if (gifts == null) {
                        return arrayList;
                    }
                    getWrapGiftType(gifts.getGiftTypeJunior());
                    getWrapGiftType(gifts.getGiftTypeIntermediate());
                    getWrapGiftType(gifts.getGiftTypeAdvanced());
                    getWrapGiftType(gifts.getGiftTypeLuxury());
                    getWrapGiftType(gifts.getGiftTypeSpecial());
                    getWrapGiftType(gifts.getGiftTypeInteresting());
                    getWrapGiftType(gifts.getGiftTypeDance());
                    getWrapGiftType(gifts.getGiftTypeNobility());
                    getWrapGiftType(gifts.getGiftTypeSuite());
                    getWrapGiftType(gifts.getGiftTypeGuard());
                    getWrapGiftType(gifts.getGiftTypeOther());
                    this.mGiftWrapTypeList.add(0, this.mShineGiftWrapType);
                    for (WrapGiftType wrapGiftType : this.mGiftWrapTypeList) {
                        if (wrapGiftType != null) {
                            arrayList.add(wrapGiftType.clone());
                        }
                    }
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            return arrayList;
        }
        try {
            for (WrapGiftType wrapGiftType2 : this.mGiftWrapTypeList) {
                if (wrapGiftType2 != null) {
                    arrayList.add(wrapGiftType2.clone());
                }
            }
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private void getWrapGiftType(List<Gift> list) {
        if (list != null && list.size() != 0) {
            String type = ((Gift) list.get(0)).getType();
            shineFilter(list);
            if (!type.equals("12")) {
                WrapGiftType wrapGiftType = new WrapGiftType();
                wrapGiftType.setTag(type);
                wrapGiftType.setTypeGiftList(list);
                this.mGiftWrapTypeList.add(wrapGiftType);
            }
        }
    }

    private void shineFilter(List<Gift> list) {
        if (mGiftConfig != null) {
            List arrayList;
            if (this.mShineGiftWrapType == null) {
                this.mShineGiftWrapType = new WrapGiftType();
                this.mShineGiftWrapType.setTag("99");
                arrayList = new ArrayList();
            } else {
                arrayList = this.mShineGiftWrapType.getTypeGiftList();
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Gift gift = (Gift) it.next();
                if (("5".equals(gift.getGtype()) && this.mIsMobileLive) || ("2".equals(gift.getGtype()) && SpecialSceneConfig.isNativeSpecialScene(Integer.parseInt(gift.getId())))) {
                    it.remove();
                    arrayList.add(gift);
                }
            }
            if (arrayList.size() > 0) {
                this.mShineGiftWrapType.setTypeGiftList(arrayList);
            } else {
                this.mShineGiftWrapType = null;
            }
        }
    }

    public HashMap<String, Gift> getStockMap() {
        HashMap<String, Gift> hashMap = new HashMap();
        if (mStockGiftMap.size() > 0) {
            try {
                for (Entry entry : mStockGiftMap.entrySet()) {
                    hashMap.put((String) entry.getKey(), ((Gift) entry.getValue()).clone());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return hashMap;
        }
        if (mGiftConfig == null) {
            analysisJson();
        }
        if (mGiftConfig != null) {
            for (Gift gift : mGiftConfig.getGifts().getGiftTypeStock()) {
                if (gift != null) {
                    mStockGiftMap.put(gift.getId(), gift);
                }
            }
            try {
                for (Entry entry2 : mStockGiftMap.entrySet()) {
                    hashMap.put((String) entry2.getKey(), ((Gift) entry2.getValue()).clone());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    public Gift getGiftBeanById(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Gift gift : getGiftBeanList()) {
            Gift gift2;
            if (gift2 != null) {
                try {
                    gift2 = gift2.clone();
                    if (str.equals(gift2.getId())) {
                        if (!"2".equals(gift2.getGtype()) || !SpecialSceneConfig.isNativeSpecialScene(Integer.parseInt(gift2.getId()))) {
                            return gift2;
                        }
                        gift2.setShowCoolEffect(true);
                        return gift2;
                    }
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Gift> getGiftBeanList() {
        List<Gift> arrayList = new ArrayList();
        if (mGiftBeanList == null || mGiftBeanList.size() <= 0) {
            if (mGiftConfig == null) {
                analysisJson();
            }
            if (mGiftConfig != null) {
                GiftTypes gifts = mGiftConfig.getGifts();
                if (gifts == null) {
                    return arrayList;
                }
                try {
                    gifts = gifts.clone();
                    if (gifts == null) {
                        return arrayList;
                    }
                    Collection giftTypeDance = gifts.getGiftTypeDance();
                    Collection giftTypeGuard = gifts.getGiftTypeGuard();
                    Collection giftTypeInteresting = gifts.getGiftTypeInteresting();
                    Collection giftTypeIntermediate = gifts.getGiftTypeIntermediate();
                    Collection giftTypeAdvanced = gifts.getGiftTypeAdvanced();
                    Collection giftTypeJunior = gifts.getGiftTypeJunior();
                    Collection giftTypeLuxury = gifts.getGiftTypeLuxury();
                    Collection giftTypeNobility = gifts.getGiftTypeNobility();
                    Collection giftTypeOther = gifts.getGiftTypeOther();
                    Collection giftTypeRoom = gifts.getGiftTypeRoom();
                    Collection giftTypeSuite = gifts.getGiftTypeSuite();
                    Collection giftTypeStock = gifts.getGiftTypeStock();
                    Collection giftTypeSpecial = gifts.getGiftTypeSpecial();
                    mGiftBeanList.addAll(giftTypeDance);
                    mGiftBeanList.addAll(giftTypeGuard);
                    mGiftBeanList.addAll(giftTypeInteresting);
                    mGiftBeanList.addAll(giftTypeIntermediate);
                    mGiftBeanList.addAll(giftTypeAdvanced);
                    mGiftBeanList.addAll(giftTypeJunior);
                    mGiftBeanList.addAll(giftTypeLuxury);
                    mGiftBeanList.addAll(giftTypeNobility);
                    mGiftBeanList.addAll(giftTypeOther);
                    mGiftBeanList.addAll(giftTypeRoom);
                    mGiftBeanList.addAll(giftTypeSuite);
                    mGiftBeanList.addAll(giftTypeStock);
                    mGiftBeanList.addAll(giftTypeSpecial);
                    try {
                        for (Gift gift : mGiftBeanList) {
                            if (gift != null) {
                                arrayList.add(gift.clone());
                            }
                        }
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                } catch (CloneNotSupportedException e2) {
                    e2.printStackTrace();
                }
            }
            return arrayList;
        }
        try {
            for (Gift gift2 : mGiftBeanList) {
                if (gift2 != null) {
                    arrayList.add(gift2.clone());
                }
            }
        } catch (CloneNotSupportedException e22) {
            e22.printStackTrace();
        }
        return arrayList;
    }

    public GiftConfig getGiftConfig() {
        return mGiftConfig;
    }

    public List<WrapGiftType> getSuperGirlTypeList(List<String> list) {
        List<WrapGiftType> arrayList = new ArrayList();
        if (mGiftConfig == null) {
            analysisJson();
        }
        if (mGiftConfig != null) {
            try {
                GiftTypes gifts = mGiftConfig.getGifts();
                if (gifts == null) {
                    return arrayList;
                }
                List<Gift> giftTypeOther = gifts.getGiftTypeOther();
                if (giftTypeOther == null) {
                    return arrayList;
                }
                WrapGiftType wrapGiftType = new WrapGiftType();
                wrapGiftType.setTag(GiftConfigUtil.SUPER_GIRL_GIFT_TAG);
                List arrayList2 = new ArrayList();
                wrapGiftType.setTypeGiftList(arrayList2);
                for (Gift gift : giftTypeOther) {
                    if (gift != null && list.contains(gift.getId())) {
                        arrayList2.add(gift.clone());
                    }
                }
                arrayList.add(wrapGiftType);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
