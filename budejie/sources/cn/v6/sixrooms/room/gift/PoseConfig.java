package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import cn.v6.sixrooms.bean.NumberBean;
import cn.v6.sixrooms.utils.FileUtil;
import cn.v6.sixrooms.utils.LogUtils;
import com.tencent.open.SocialConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PoseConfig {
    private static final String TAG = "PoseConfig";
    private static PoseConfig mPoserConfig;
    private SparseArray<PoseBean> mPoseList;
    private long version;

    public static class PoseBean {
        private int compoundt;
        private String image;
        private int num;
        private String num_suffix;
        private int totalt;
        private String x;
        private String y;

        public int getNum() {
            return this.num;
        }

        public void setNum(int i) {
            this.num = i;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String getX() {
            return this.x;
        }

        public void setX(String str) {
            this.x = str;
        }

        public String getY() {
            return this.y;
        }

        public void setY(String str) {
            this.y = str;
        }

        public int getCompoundt() {
            return this.compoundt;
        }

        public void setCompoundt(int i) {
            this.compoundt = i;
        }

        public int getTotalt() {
            return this.totalt;
        }

        public void setTotalt(int i) {
            this.totalt = i;
        }

        public String getNum_suffix() {
            return this.num_suffix;
        }

        public void setNum_suffix(String str) {
            this.num_suffix = str;
        }
    }

    private PoseConfig() {
        init();
    }

    public static PoseConfig getInstance() {
        if (mPoserConfig == null) {
            synchronized (PoseConfig.class) {
                if (mPoserConfig == null) {
                    mPoserConfig = new PoseConfig();
                }
            }
        }
        return mPoserConfig;
    }

    private void init() {
        Object readFile;
        IOException e;
        JSONObject jSONObject;
        JSONArray optJSONArray;
        int i;
        PoseBean poseBean;
        JSONObject jSONObject2;
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        int optInt;
        if (this.mPoseList == null) {
            this.mPoseList = new SparseArray();
        }
        try {
            readFile = FileUtil.readFile(GiftNumConfigPresenter.getGiftNumFileName());
            try {
                LogUtils.i(TAG, readFile);
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                if (!TextUtils.isEmpty(readFile)) {
                    try {
                        jSONObject = new JSONObject(readFile);
                        this.version = jSONObject.optLong("ver");
                        optJSONArray = jSONObject.optJSONArray("props");
                        for (i = 0; i < optJSONArray.length(); i++) {
                            poseBean = new PoseBean();
                            jSONObject2 = (JSONObject) optJSONArray.get(i);
                            optJSONObject = jSONObject2.optJSONObject("pic");
                            optJSONObject2 = jSONObject2.optJSONObject("down");
                            optInt = jSONObject2.optInt("num");
                            poseBean.setNum(optInt);
                            poseBean.setImage(optJSONObject.optString(SocialConstants.PARAM_IMG_URL));
                            poseBean.setX(optJSONObject2.optString("x"));
                            poseBean.setY(optJSONObject2.optString("y"));
                            poseBean.setCompoundt(jSONObject2.optInt("compoundt", 2));
                            poseBean.setTotalt(jSONObject2.optInt("totalt", 4));
                            poseBean.setNum_suffix(jSONObject2.optString("num_suffix", ""));
                            this.mPoseList.put(optInt, poseBean);
                        }
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
                Log.e(TAG, "PoseConfig size :" + this.mPoseList.size());
            }
        } catch (IOException e4) {
            IOException iOException = e4;
            readFile = null;
            e = iOException;
            e.printStackTrace();
            if (TextUtils.isEmpty(readFile)) {
                jSONObject = new JSONObject(readFile);
                this.version = jSONObject.optLong("ver");
                optJSONArray = jSONObject.optJSONArray("props");
                for (i = 0; i < optJSONArray.length(); i++) {
                    poseBean = new PoseBean();
                    jSONObject2 = (JSONObject) optJSONArray.get(i);
                    optJSONObject = jSONObject2.optJSONObject("pic");
                    optJSONObject2 = jSONObject2.optJSONObject("down");
                    optInt = jSONObject2.optInt("num");
                    poseBean.setNum(optInt);
                    poseBean.setImage(optJSONObject.optString(SocialConstants.PARAM_IMG_URL));
                    poseBean.setX(optJSONObject2.optString("x"));
                    poseBean.setY(optJSONObject2.optString("y"));
                    poseBean.setCompoundt(jSONObject2.optInt("compoundt", 2));
                    poseBean.setTotalt(jSONObject2.optInt("totalt", 4));
                    poseBean.setNum_suffix(jSONObject2.optString("num_suffix", ""));
                    this.mPoseList.put(optInt, poseBean);
                }
            }
            Log.e(TAG, "PoseConfig size :" + this.mPoseList.size());
        }
        if (TextUtils.isEmpty(readFile)) {
            jSONObject = new JSONObject(readFile);
            this.version = jSONObject.optLong("ver");
            optJSONArray = jSONObject.optJSONArray("props");
            for (i = 0; i < optJSONArray.length(); i++) {
                poseBean = new PoseBean();
                jSONObject2 = (JSONObject) optJSONArray.get(i);
                optJSONObject = jSONObject2.optJSONObject("pic");
                optJSONObject2 = jSONObject2.optJSONObject("down");
                optInt = jSONObject2.optInt("num");
                poseBean.setNum(optInt);
                poseBean.setImage(optJSONObject.optString(SocialConstants.PARAM_IMG_URL));
                poseBean.setX(optJSONObject2.optString("x"));
                poseBean.setY(optJSONObject2.optString("y"));
                poseBean.setCompoundt(jSONObject2.optInt("compoundt", 2));
                poseBean.setTotalt(jSONObject2.optInt("totalt", 4));
                poseBean.setNum_suffix(jSONObject2.optString("num_suffix", ""));
                this.mPoseList.put(optInt, poseBean);
            }
        }
        Log.e(TAG, "PoseConfig size :" + this.mPoseList.size());
    }

    public PoseBean getPose(int i) {
        return (PoseBean) this.mPoseList.get(i);
    }

    public long getVersion() {
        return this.version;
    }

    public void refreshConfig() {
        if (this.mPoseList != null) {
            this.mPoseList.clear();
        }
        init();
    }

    public List<NumberBean> getAllGiftNum() {
        List<NumberBean> arrayList = new ArrayList();
        try {
            if (this.mPoseList != null && this.mPoseList.size() > 0) {
                for (int i = 0; i < this.mPoseList.size(); i++) {
                    PoseBean poseBean = (PoseBean) this.mPoseList.valueAt(i);
                    arrayList.add(new NumberBean(poseBean.num, poseBean.getNum_suffix()));
                }
                NumberBean numberBean = new NumberBean(1, "");
                NumberBean numberBean2 = new NumberBean(5, "");
                NumberBean numberBean3 = new NumberBean(10, "");
                arrayList.add(numberBean);
                arrayList.add(numberBean2);
                arrayList.add(numberBean3);
                Collections.sort(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            arrayList.clear();
        }
        return arrayList;
    }
}
