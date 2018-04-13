package com.tencent.weibo.sdk.android.network;

import android.content.Context;
import android.util.Log;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.JsonUtil;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpReq.UTF8PostMethod;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpReqWeiBo extends HttpReq {
    private Context mContext;
    private Integer mResultType = Integer.valueOf(0);
    private Class<? extends BaseVO> mTargetClass;
    private Class<? extends BaseVO> mTargetClass2;

    public HttpReqWeiBo(Context context, String str, HttpCallback httpCallback, Class<? extends BaseVO> cls, String str2, Integer num) {
        this.mContext = context;
        this.mHost = HttpConfig.CRM_SERVER_NAME;
        this.mPort = HttpConfig.CRM_SERVER_PORT;
        this.mUrl = str;
        this.mCallBack = httpCallback;
        this.mTargetClass = cls;
        this.mResultType = num;
        this.mMethod = str2;
    }

    public void setmTargetClass(Class<? extends BaseVO> cls) {
        this.mTargetClass = cls;
    }

    public void setmTargetClass2(Class<? extends BaseVO> cls) {
        this.mTargetClass2 = cls;
    }

    public void setmResultType(Integer num) {
        this.mResultType = num;
    }

    protected Object processResponse(InputStream inputStream) throws Exception {
        int i = 1;
        ModelResult modelResult = new ModelResult();
        if (inputStream != null) {
            String readLine;
            Reader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            bufferedReader.close();
            inputStreamReader.close();
            Log.d("relst", stringBuffer.toString());
            if (stringBuffer.toString().indexOf("errcode") != -1 || stringBuffer.toString().indexOf(Constants.PARAM_ACCESS_TOKEN) == -1) {
                JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                BaseVO baseVO = null;
                if (this.mTargetClass != null) {
                    baseVO = (BaseVO) this.mTargetClass.newInstance();
                }
                String string = jSONObject.getString("errcode");
                readLine = jSONObject.getString("msg");
                if (string != null && "0".equals(string)) {
                    modelResult.setSuccess(true);
                    List arrayList;
                    switch (this.mResultType.intValue()) {
                        case 0:
                            baseVO = JsonUtil.jsonToObject(this.mTargetClass, jSONObject);
                            arrayList = new ArrayList();
                            arrayList.add(baseVO);
                            modelResult.setList(arrayList);
                            break;
                        case 1:
                            int i2;
                            Map analyseHead = baseVO.analyseHead(jSONObject);
                            List jsonToList = JsonUtil.jsonToList(this.mTargetClass, (JSONArray) analyseHead.get("array"));
                            if (analyseHead.get("total") == null) {
                                i2 = 0;
                            } else {
                                i2 = ((Integer) analyseHead.get("total")).intValue();
                            }
                            Integer valueOf = Integer.valueOf(i2);
                            if (analyseHead.get("p") == null) {
                                i2 = 1;
                            } else {
                                i2 = ((Integer) analyseHead.get("p")).intValue();
                            }
                            Integer valueOf2 = Integer.valueOf(i2);
                            if (analyseHead.get(KEYS.PLACEMENTS) != null) {
                                i = ((Integer) analyseHead.get(KEYS.PLACEMENTS)).intValue();
                            }
                            Integer valueOf3 = Integer.valueOf(i);
                            boolean booleanValue = ((Boolean) analyseHead.get("isLastPage")).booleanValue();
                            modelResult.setList(jsonToList);
                            modelResult.setTotal(valueOf.intValue());
                            modelResult.setP(valueOf2.intValue());
                            modelResult.setPs(valueOf3.intValue());
                            modelResult.setLastPage(booleanValue);
                            break;
                        case 2:
                            modelResult.setObj(JsonUtil.jsonToObject(this.mTargetClass, jSONObject));
                            break;
                        case 3:
                            baseVO = JsonUtil.jsonToObject(this.mTargetClass, jSONObject);
                            arrayList = JsonUtil.jsonToList(this.mTargetClass2, jSONObject.getJSONArray("result_list"));
                            modelResult.setObj(baseVO);
                            modelResult.setList(arrayList);
                            break;
                        case 4:
                            modelResult.setObj(jSONObject);
                            break;
                    }
                }
                modelResult.setSuccess(false);
                modelResult.setError_message(readLine);
            } else {
                modelResult.setObj(stringBuffer.toString());
                return modelResult;
            }
        }
        return modelResult;
    }

    protected void setReq(HttpMethod httpMethod) throws Exception {
        if ("POST".equals(this.mMethod)) {
            PostMethod postMethod = (PostMethod) httpMethod;
            this.mParam.toString();
            postMethod.addParameter("Connection", "Keep-Alive");
            postMethod.addParameter("Charset", "UTF-8");
            postMethod.setRequestEntity(new ByteArrayRequestEntity(this.mParam.toString().getBytes("utf-8")));
        }
    }

    public void setReq(String str) throws Exception {
        if ("POST".equals(this.mMethod)) {
            PostMethod uTF8PostMethod = new UTF8PostMethod(this.mUrl);
            this.mParam.toString();
            uTF8PostMethod.setRequestEntity(new ByteArrayRequestEntity(this.mParam.toString().getBytes("utf-8")));
        }
    }
}
