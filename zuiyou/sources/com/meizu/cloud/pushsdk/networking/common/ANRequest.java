package com.meizu.cloud.pushsdk.networking.common;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.text.TextUtils;
import android.widget.ImageView.ScaleType;
import com.meizu.cloud.pushsdk.networking.core.Core;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Call;
import com.meizu.cloud.pushsdk.networking.http.FormBody;
import com.meizu.cloud.pushsdk.networking.http.Headers;
import com.meizu.cloud.pushsdk.networking.http.HttpUrl;
import com.meizu.cloud.pushsdk.networking.http.HttpUrl.Builder;
import com.meizu.cloud.pushsdk.networking.http.MediaType;
import com.meizu.cloud.pushsdk.networking.http.MultipartBody;
import com.meizu.cloud.pushsdk.networking.http.RequestBody;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.networking.interfaces.AnalyticsListener;
import com.meizu.cloud.pushsdk.networking.interfaces.BitmapRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.DownloadListener;
import com.meizu.cloud.pushsdk.networking.interfaces.DownloadProgressListener;
import com.meizu.cloud.pushsdk.networking.interfaces.JSONArrayRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.JSONObjectRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseAndBitmapRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseAndJSONArrayRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseAndParsedRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseAndStringRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseListener;
import com.meizu.cloud.pushsdk.networking.interfaces.ParsedRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.StringRequestListener;
import com.meizu.cloud.pushsdk.networking.interfaces.UploadProgressListener;
import com.meizu.cloud.pushsdk.networking.internal.ANRequestQueue;
import com.meizu.cloud.pushsdk.networking.internal.SynchronousCall;
import com.meizu.cloud.pushsdk.networking.okio.Okio;
import com.meizu.cloud.pushsdk.networking.utils.Utils;
import com.meizu.cloud.pushsdk.pushtracer.constant.TrackerConstants;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class ANRequest<T extends ANRequest> {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse(TrackerConstants.POST_CONTENT_TYPE);
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final String TAG = ANRequest.class.getSimpleName();
    private static final Object sDecodeLock = new Object();
    private Call call;
    private MediaType customMediaType;
    private Future future;
    private boolean isCancelled;
    private boolean isDelivered;
    private AnalyticsListener mAnalyticsListener;
    private BitmapRequestListener mBitmapRequestListener;
    private HashMap<String, String> mBodyParameterMap;
    private byte[] mByte;
    private Config mDecodeConfig;
    private String mDirPath;
    private DownloadListener mDownloadListener;
    private DownloadProgressListener mDownloadProgressListener;
    private Executor mExecutor;
    private File mFile;
    private String mFileName;
    private HashMap<String, String> mHeadersMap;
    private JSONArrayRequestListener mJSONArrayRequestListener;
    private JSONObjectRequestListener mJSONObjectRequestListener;
    private JSONArray mJsonArray;
    private JSONObject mJsonObject;
    private int mMaxHeight;
    private int mMaxWidth;
    private int mMethod;
    private HashMap<String, File> mMultiPartFileMap;
    private HashMap<String, String> mMultiPartParameterMap;
    private OkHttpResponseAndBitmapRequestListener mOkHttpResponseAndBitmapRequestListener;
    private OkHttpResponseAndJSONArrayRequestListener mOkHttpResponseAndJSONArrayRequestListener;
    private OkHttpResponseAndJSONObjectRequestListener mOkHttpResponseAndJSONObjectRequestListener;
    private OkHttpResponseAndParsedRequestListener mOkHttpResponseAndParsedRequestListener;
    private OkHttpResponseAndStringRequestListener mOkHttpResponseAndStringRequestListener;
    private OkHttpResponseListener mOkHttpResponseListener;
    private ParsedRequestListener mParsedRequestListener;
    private HashMap<String, String> mPathParameterMap;
    private int mPercentageThresholdForCancelling;
    private Priority mPriority;
    private int mProgress;
    private HashMap<String, String> mQueryParameterMap;
    private int mRequestType;
    private ResponseType mResponseType;
    private ScaleType mScaleType;
    private String mStringBody;
    private StringRequestListener mStringRequestListener;
    private Object mTag;
    private Type mType;
    private UploadProgressListener mUploadProgressListener;
    private String mUrl;
    private HashMap<String, String> mUrlEncodedFormBodyParameterMap;
    private String mUserAgent;
    private int sequenceNumber;

    public static class PostRequestBuilder<T extends PostRequestBuilder> implements RequestBuilder {
        private HashMap<String, String> mBodyParameterMap = new HashMap();
        private byte[] mByte = null;
        private String mCustomContentType;
        private Executor mExecutor;
        private File mFile = null;
        private HashMap<String, String> mHeadersMap = new HashMap();
        private JSONArray mJsonArray = null;
        private JSONObject mJsonObject = null;
        private int mMethod = 1;
        private HashMap<String, String> mPathParameterMap = new HashMap();
        private Priority mPriority = Priority.MEDIUM;
        private HashMap<String, String> mQueryParameterMap = new HashMap();
        private String mStringBody = null;
        private Object mTag;
        private String mUrl;
        private HashMap<String, String> mUrlEncodedFormBodyParameterMap = new HashMap();
        private String mUserAgent;

        public PostRequestBuilder(String str) {
            this.mUrl = str;
            this.mMethod = 1;
        }

        public PostRequestBuilder(String str, int i) {
            this.mUrl = str;
            this.mMethod = i;
        }

        public T setPriority(Priority priority) {
            this.mPriority = priority;
            return this;
        }

        public T setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public T addQueryParameter(String str, String str2) {
            this.mQueryParameterMap.put(str, str2);
            return this;
        }

        public T addQueryParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mQueryParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addPathParameter(String str, String str2) {
            this.mPathParameterMap.put(str, str2);
            return this;
        }

        public T addHeaders(String str, String str2) {
            this.mHeadersMap.put(str, str2);
            return this;
        }

        public T addHeaders(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mHeadersMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T doNotCacheResponse() {
            return this;
        }

        public T getResponseOnlyIfCached() {
            return this;
        }

        public T getResponseOnlyFromNetwork() {
            return this;
        }

        public T setMaxAgeCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setMaxStaleCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setExecutor(Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        public T setUserAgent(String str) {
            this.mUserAgent = str;
            return this;
        }

        public T addBodyParameter(String str, String str2) {
            this.mBodyParameterMap.put(str, str2);
            return this;
        }

        public T addUrlEncodeFormBodyParameter(String str, String str2) {
            this.mUrlEncodedFormBodyParameterMap.put(str, str2);
            return this;
        }

        public T addBodyParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mBodyParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addUrlEncodeFormBodyParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mUrlEncodedFormBodyParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addJSONObjectBody(JSONObject jSONObject) {
            this.mJsonObject = jSONObject;
            return this;
        }

        public T addJSONArrayBody(JSONArray jSONArray) {
            this.mJsonArray = jSONArray;
            return this;
        }

        public T addStringBody(String str) {
            this.mStringBody = str;
            return this;
        }

        public T addFileBody(File file) {
            this.mFile = file;
            return this;
        }

        public T addByteBody(byte[] bArr) {
            this.mByte = bArr;
            return this;
        }

        public T setContentType(String str) {
            this.mCustomContentType = str;
            return this;
        }

        public ANRequest build() {
            return new ANRequest(this);
        }
    }

    public static class DeleteRequestBuilder extends PostRequestBuilder {
        public DeleteRequestBuilder(String str) {
            super(str, 3);
        }
    }

    public static class DownloadBuilder<T extends DownloadBuilder> implements RequestBuilder {
        private String mDirPath;
        private Executor mExecutor;
        private String mFileName;
        private HashMap<String, String> mHeadersMap = new HashMap();
        private HashMap<String, String> mPathParameterMap = new HashMap();
        private int mPercentageThresholdForCancelling = 0;
        private Priority mPriority = Priority.MEDIUM;
        private HashMap<String, String> mQueryParameterMap = new HashMap();
        private Object mTag;
        private String mUrl;
        private String mUserAgent;

        public DownloadBuilder(String str, String str2, String str3) {
            this.mUrl = str;
            this.mDirPath = str2;
            this.mFileName = str3;
        }

        public T setPriority(Priority priority) {
            this.mPriority = priority;
            return this;
        }

        public T setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public T addHeaders(String str, String str2) {
            this.mHeadersMap.put(str, str2);
            return this;
        }

        public T addHeaders(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mHeadersMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addQueryParameter(String str, String str2) {
            this.mQueryParameterMap.put(str, str2);
            return this;
        }

        public T addQueryParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mQueryParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addPathParameter(String str, String str2) {
            this.mPathParameterMap.put(str, str2);
            return this;
        }

        public T doNotCacheResponse() {
            return this;
        }

        public T getResponseOnlyIfCached() {
            return this;
        }

        public T getResponseOnlyFromNetwork() {
            return this;
        }

        public T setMaxAgeCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setMaxStaleCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setExecutor(Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        public T setUserAgent(String str) {
            this.mUserAgent = str;
            return this;
        }

        public T setPercentageThresholdForCancelling(int i) {
            this.mPercentageThresholdForCancelling = i;
            return this;
        }

        public ANRequest build() {
            return new ANRequest(this);
        }
    }

    public static class GetRequestBuilder<T extends GetRequestBuilder> implements RequestBuilder {
        private Config mDecodeConfig;
        private Executor mExecutor;
        private HashMap<String, String> mHeadersMap = new HashMap();
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMethod = 0;
        private HashMap<String, String> mPathParameterMap = new HashMap();
        private Priority mPriority = Priority.MEDIUM;
        private HashMap<String, String> mQueryParameterMap = new HashMap();
        private ScaleType mScaleType;
        private Object mTag;
        private String mUrl;
        private String mUserAgent;

        public GetRequestBuilder(String str) {
            this.mUrl = str;
            this.mMethod = 0;
        }

        public GetRequestBuilder(String str, int i) {
            this.mUrl = str;
            this.mMethod = i;
        }

        public T setPriority(Priority priority) {
            this.mPriority = priority;
            return this;
        }

        public T setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public T addQueryParameter(String str, String str2) {
            this.mQueryParameterMap.put(str, str2);
            return this;
        }

        public T addQueryParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mQueryParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addPathParameter(String str, String str2) {
            this.mPathParameterMap.put(str, str2);
            return this;
        }

        public T addHeaders(String str, String str2) {
            this.mHeadersMap.put(str, str2);
            return this;
        }

        public T addHeaders(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mHeadersMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T doNotCacheResponse() {
            return this;
        }

        public T getResponseOnlyIfCached() {
            return this;
        }

        public T getResponseOnlyFromNetwork() {
            return this;
        }

        public T setMaxAgeCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setMaxStaleCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setExecutor(Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        public T setUserAgent(String str) {
            this.mUserAgent = str;
            return this;
        }

        public T setBitmapConfig(Config config) {
            this.mDecodeConfig = config;
            return this;
        }

        public T setBitmapMaxHeight(int i) {
            this.mMaxHeight = i;
            return this;
        }

        public T setBitmapMaxWidth(int i) {
            this.mMaxWidth = i;
            return this;
        }

        public T setImageScaleType(ScaleType scaleType) {
            this.mScaleType = scaleType;
            return this;
        }

        public ANRequest build() {
            return new ANRequest(this);
        }
    }

    public static class HeadRequestBuilder extends GetRequestBuilder {
        public HeadRequestBuilder(String str) {
            super(str, 4);
        }
    }

    public static class MultiPartBuilder<T extends MultiPartBuilder> implements RequestBuilder {
        private String mCustomContentType;
        private Executor mExecutor;
        private HashMap<String, String> mHeadersMap = new HashMap();
        private HashMap<String, File> mMultiPartFileMap = new HashMap();
        private HashMap<String, String> mMultiPartParameterMap = new HashMap();
        private HashMap<String, String> mPathParameterMap = new HashMap();
        private int mPercentageThresholdForCancelling = 0;
        private Priority mPriority = Priority.MEDIUM;
        private HashMap<String, String> mQueryParameterMap = new HashMap();
        private Object mTag;
        private String mUrl;
        private String mUserAgent;

        public MultiPartBuilder(String str) {
            this.mUrl = str;
        }

        public T setPriority(Priority priority) {
            this.mPriority = priority;
            return this;
        }

        public T setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public T addQueryParameter(String str, String str2) {
            this.mQueryParameterMap.put(str, str2);
            return this;
        }

        public T addQueryParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mQueryParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addPathParameter(String str, String str2) {
            this.mPathParameterMap.put(str, str2);
            return this;
        }

        public T addHeaders(String str, String str2) {
            this.mHeadersMap.put(str, str2);
            return this;
        }

        public T addHeaders(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mHeadersMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T doNotCacheResponse() {
            return this;
        }

        public T getResponseOnlyIfCached() {
            return this;
        }

        public T getResponseOnlyFromNetwork() {
            return this;
        }

        public T setMaxAgeCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setMaxStaleCacheControl(int i, TimeUnit timeUnit) {
            return this;
        }

        public T setExecutor(Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        public T setUserAgent(String str) {
            this.mUserAgent = str;
            return this;
        }

        public T addMultipartParameter(String str, String str2) {
            this.mMultiPartParameterMap.put(str, str2);
            return this;
        }

        public T addMultipartParameter(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mMultiPartParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T addMultipartFile(String str, File file) {
            this.mMultiPartFileMap.put(str, file);
            return this;
        }

        public T addMultipartFile(HashMap<String, File> hashMap) {
            if (hashMap != null) {
                for (Entry entry : hashMap.entrySet()) {
                    this.mMultiPartFileMap.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public T setPercentageThresholdForCancelling(int i) {
            this.mPercentageThresholdForCancelling = i;
            return this;
        }

        public T setContentType(String str) {
            this.mCustomContentType = str;
            return this;
        }

        public ANRequest build() {
            return new ANRequest(this);
        }
    }

    public static class PatchRequestBuilder extends PostRequestBuilder {
        public PatchRequestBuilder(String str) {
            super(str, 5);
        }
    }

    public static class PutRequestBuilder extends PostRequestBuilder {
        public PutRequestBuilder(String str) {
            super(str, 2);
        }
    }

    public ANRequest(GetRequestBuilder getRequestBuilder) {
        this.mHeadersMap = new HashMap();
        this.mBodyParameterMap = new HashMap();
        this.mUrlEncodedFormBodyParameterMap = new HashMap();
        this.mMultiPartParameterMap = new HashMap();
        this.mQueryParameterMap = new HashMap();
        this.mPathParameterMap = new HashMap();
        this.mMultiPartFileMap = new HashMap();
        this.mJsonObject = null;
        this.mJsonArray = null;
        this.mStringBody = null;
        this.mByte = null;
        this.mFile = null;
        this.customMediaType = null;
        this.mPercentageThresholdForCancelling = 0;
        this.mExecutor = null;
        this.mUserAgent = null;
        this.mType = null;
        this.mRequestType = 0;
        this.mMethod = getRequestBuilder.mMethod;
        this.mPriority = getRequestBuilder.mPriority;
        this.mUrl = getRequestBuilder.mUrl;
        this.mTag = getRequestBuilder.mTag;
        this.mHeadersMap = getRequestBuilder.mHeadersMap;
        this.mDecodeConfig = getRequestBuilder.mDecodeConfig;
        this.mMaxHeight = getRequestBuilder.mMaxHeight;
        this.mMaxWidth = getRequestBuilder.mMaxWidth;
        this.mScaleType = getRequestBuilder.mScaleType;
        this.mQueryParameterMap = getRequestBuilder.mQueryParameterMap;
        this.mPathParameterMap = getRequestBuilder.mPathParameterMap;
        this.mExecutor = getRequestBuilder.mExecutor;
        this.mUserAgent = getRequestBuilder.mUserAgent;
    }

    public ANRequest(PostRequestBuilder postRequestBuilder) {
        this.mHeadersMap = new HashMap();
        this.mBodyParameterMap = new HashMap();
        this.mUrlEncodedFormBodyParameterMap = new HashMap();
        this.mMultiPartParameterMap = new HashMap();
        this.mQueryParameterMap = new HashMap();
        this.mPathParameterMap = new HashMap();
        this.mMultiPartFileMap = new HashMap();
        this.mJsonObject = null;
        this.mJsonArray = null;
        this.mStringBody = null;
        this.mByte = null;
        this.mFile = null;
        this.customMediaType = null;
        this.mPercentageThresholdForCancelling = 0;
        this.mExecutor = null;
        this.mUserAgent = null;
        this.mType = null;
        this.mRequestType = 0;
        this.mMethod = postRequestBuilder.mMethod;
        this.mPriority = postRequestBuilder.mPriority;
        this.mUrl = postRequestBuilder.mUrl;
        this.mTag = postRequestBuilder.mTag;
        this.mHeadersMap = postRequestBuilder.mHeadersMap;
        this.mBodyParameterMap = postRequestBuilder.mBodyParameterMap;
        this.mUrlEncodedFormBodyParameterMap = postRequestBuilder.mUrlEncodedFormBodyParameterMap;
        this.mQueryParameterMap = postRequestBuilder.mQueryParameterMap;
        this.mPathParameterMap = postRequestBuilder.mPathParameterMap;
        this.mJsonObject = postRequestBuilder.mJsonObject;
        this.mJsonArray = postRequestBuilder.mJsonArray;
        this.mStringBody = postRequestBuilder.mStringBody;
        this.mFile = postRequestBuilder.mFile;
        this.mByte = postRequestBuilder.mByte;
        this.mExecutor = postRequestBuilder.mExecutor;
        this.mUserAgent = postRequestBuilder.mUserAgent;
        if (postRequestBuilder.mCustomContentType != null) {
            this.customMediaType = MediaType.parse(postRequestBuilder.mCustomContentType);
        }
    }

    public ANRequest(DownloadBuilder downloadBuilder) {
        this.mHeadersMap = new HashMap();
        this.mBodyParameterMap = new HashMap();
        this.mUrlEncodedFormBodyParameterMap = new HashMap();
        this.mMultiPartParameterMap = new HashMap();
        this.mQueryParameterMap = new HashMap();
        this.mPathParameterMap = new HashMap();
        this.mMultiPartFileMap = new HashMap();
        this.mJsonObject = null;
        this.mJsonArray = null;
        this.mStringBody = null;
        this.mByte = null;
        this.mFile = null;
        this.customMediaType = null;
        this.mPercentageThresholdForCancelling = 0;
        this.mExecutor = null;
        this.mUserAgent = null;
        this.mType = null;
        this.mRequestType = 1;
        this.mMethod = 0;
        this.mPriority = downloadBuilder.mPriority;
        this.mUrl = downloadBuilder.mUrl;
        this.mTag = downloadBuilder.mTag;
        this.mDirPath = downloadBuilder.mDirPath;
        this.mFileName = downloadBuilder.mFileName;
        this.mHeadersMap = downloadBuilder.mHeadersMap;
        this.mQueryParameterMap = downloadBuilder.mQueryParameterMap;
        this.mPathParameterMap = downloadBuilder.mPathParameterMap;
        this.mPercentageThresholdForCancelling = downloadBuilder.mPercentageThresholdForCancelling;
        this.mExecutor = downloadBuilder.mExecutor;
        this.mUserAgent = downloadBuilder.mUserAgent;
    }

    public ANRequest(MultiPartBuilder multiPartBuilder) {
        this.mHeadersMap = new HashMap();
        this.mBodyParameterMap = new HashMap();
        this.mUrlEncodedFormBodyParameterMap = new HashMap();
        this.mMultiPartParameterMap = new HashMap();
        this.mQueryParameterMap = new HashMap();
        this.mPathParameterMap = new HashMap();
        this.mMultiPartFileMap = new HashMap();
        this.mJsonObject = null;
        this.mJsonArray = null;
        this.mStringBody = null;
        this.mByte = null;
        this.mFile = null;
        this.customMediaType = null;
        this.mPercentageThresholdForCancelling = 0;
        this.mExecutor = null;
        this.mUserAgent = null;
        this.mType = null;
        this.mRequestType = 2;
        this.mMethod = 1;
        this.mPriority = multiPartBuilder.mPriority;
        this.mUrl = multiPartBuilder.mUrl;
        this.mTag = multiPartBuilder.mTag;
        this.mHeadersMap = multiPartBuilder.mHeadersMap;
        this.mQueryParameterMap = multiPartBuilder.mQueryParameterMap;
        this.mPathParameterMap = multiPartBuilder.mPathParameterMap;
        this.mMultiPartParameterMap = multiPartBuilder.mMultiPartParameterMap;
        this.mMultiPartFileMap = multiPartBuilder.mMultiPartFileMap;
        this.mPercentageThresholdForCancelling = multiPartBuilder.mPercentageThresholdForCancelling;
        this.mExecutor = multiPartBuilder.mExecutor;
        this.mUserAgent = multiPartBuilder.mUserAgent;
        if (multiPartBuilder.mCustomContentType != null) {
            this.customMediaType = MediaType.parse(multiPartBuilder.mCustomContentType);
        }
    }

    public void getAsJSONObject(JSONObjectRequestListener jSONObjectRequestListener) {
        this.mResponseType = ResponseType.JSON_OBJECT;
        this.mJSONObjectRequestListener = jSONObjectRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsJSONArray(JSONArrayRequestListener jSONArrayRequestListener) {
        this.mResponseType = ResponseType.JSON_ARRAY;
        this.mJSONArrayRequestListener = jSONArrayRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsString(StringRequestListener stringRequestListener) {
        this.mResponseType = ResponseType.STRING;
        this.mStringRequestListener = stringRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsOkHttpResponse(OkHttpResponseListener okHttpResponseListener) {
        this.mResponseType = ResponseType.OK_HTTP_RESPONSE;
        this.mOkHttpResponseListener = okHttpResponseListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsBitmap(BitmapRequestListener bitmapRequestListener) {
        this.mResponseType = ResponseType.BITMAP;
        this.mBitmapRequestListener = bitmapRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsOkHttpResponseAndJSONObject(OkHttpResponseAndJSONObjectRequestListener okHttpResponseAndJSONObjectRequestListener) {
        this.mResponseType = ResponseType.JSON_OBJECT;
        this.mOkHttpResponseAndJSONObjectRequestListener = okHttpResponseAndJSONObjectRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsOkHttpResponseAndJSONArray(OkHttpResponseAndJSONArrayRequestListener okHttpResponseAndJSONArrayRequestListener) {
        this.mResponseType = ResponseType.JSON_ARRAY;
        this.mOkHttpResponseAndJSONArrayRequestListener = okHttpResponseAndJSONArrayRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsOkHttpResponseAndString(OkHttpResponseAndStringRequestListener okHttpResponseAndStringRequestListener) {
        this.mResponseType = ResponseType.STRING;
        this.mOkHttpResponseAndStringRequestListener = okHttpResponseAndStringRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void getAsOkHttpResponseAndBitmap(OkHttpResponseAndBitmapRequestListener okHttpResponseAndBitmapRequestListener) {
        this.mResponseType = ResponseType.BITMAP;
        this.mOkHttpResponseAndBitmapRequestListener = okHttpResponseAndBitmapRequestListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void startDownload(DownloadListener downloadListener) {
        this.mDownloadListener = downloadListener;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public void prefetch() {
        this.mResponseType = ResponseType.PREFETCH;
        ANRequestQueue.getInstance().addRequest(this);
    }

    public ANResponse executeForJSONObject() {
        this.mResponseType = ResponseType.JSON_OBJECT;
        return SynchronousCall.execute(this);
    }

    public ANResponse executeForJSONArray() {
        this.mResponseType = ResponseType.JSON_ARRAY;
        return SynchronousCall.execute(this);
    }

    public ANResponse executeForString() {
        this.mResponseType = ResponseType.STRING;
        return SynchronousCall.execute(this);
    }

    public ANResponse executeForOkHttpResponse() {
        this.mResponseType = ResponseType.OK_HTTP_RESPONSE;
        return SynchronousCall.execute(this);
    }

    public ANResponse executeForBitmap() {
        this.mResponseType = ResponseType.BITMAP;
        return SynchronousCall.execute(this);
    }

    public ANResponse executeForDownload() {
        return SynchronousCall.execute(this);
    }

    public T setDownloadProgressListener(DownloadProgressListener downloadProgressListener) {
        this.mDownloadProgressListener = downloadProgressListener;
        return this;
    }

    public T setUploadProgressListener(UploadProgressListener uploadProgressListener) {
        this.mUploadProgressListener = uploadProgressListener;
        return this;
    }

    public T setAnalyticsListener(AnalyticsListener analyticsListener) {
        this.mAnalyticsListener = analyticsListener;
        return this;
    }

    public AnalyticsListener getAnalyticsListener() {
        return this.mAnalyticsListener;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Priority getPriority() {
        return this.mPriority;
    }

    public String getUrl() {
        String str = this.mUrl;
        String str2 = str;
        for (Entry entry : this.mPathParameterMap.entrySet()) {
            str2 = str2.replace("{" + ((String) entry.getKey()) + "}", String.valueOf(entry.getValue()));
        }
        Builder newBuilder = HttpUrl.parse(str2).newBuilder();
        for (Entry entry2 : this.mQueryParameterMap.entrySet()) {
            newBuilder.addQueryParameter((String) entry2.getKey(), (String) entry2.getValue());
        }
        return newBuilder.build().toString();
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setProgress(int i) {
        this.mProgress = i;
    }

    public void setResponseAs(ResponseType responseType) {
        this.mResponseType = responseType;
    }

    public ResponseType getResponseAs() {
        return this.mResponseType;
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getRequestType() {
        return this.mRequestType;
    }

    public void setUserAgent(String str) {
        this.mUserAgent = str;
    }

    public String getUserAgent() {
        return this.mUserAgent;
    }

    public Type getType() {
        return this.mType;
    }

    public void setType(Type type) {
        this.mType = type;
    }

    public DownloadProgressListener getDownloadProgressListener() {
        return new DownloadProgressListener() {
            public void onProgress(long j, long j2) {
                if (ANRequest.this.mDownloadProgressListener != null && !ANRequest.this.isCancelled) {
                    ANRequest.this.mDownloadProgressListener.onProgress(j, j2);
                }
            }
        };
    }

    public void updateDownloadCompletion() {
        this.isDelivered = true;
        if (this.mDownloadListener == null) {
            ANLog.d("Prefetch done : " + toString());
            finish();
        } else if (this.isCancelled) {
            deliverError(new ANError());
            finish();
        } else if (this.mExecutor != null) {
            this.mExecutor.execute(new Runnable() {
                public void run() {
                    if (ANRequest.this.mDownloadListener != null) {
                        ANRequest.this.mDownloadListener.onDownloadComplete();
                    }
                    ANLog.d("Delivering success : " + toString());
                    ANRequest.this.finish();
                }
            });
        } else {
            Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() {
                public void run() {
                    if (ANRequest.this.mDownloadListener != null) {
                        ANRequest.this.mDownloadListener.onDownloadComplete();
                    }
                    ANLog.d("Delivering success : " + toString());
                    ANRequest.this.finish();
                }
            });
        }
    }

    public UploadProgressListener getUploadProgressListener() {
        return new UploadProgressListener() {
            public void onProgress(long j, long j2) {
                ANRequest.this.mProgress = (int) ((100 * j) / j2);
                if (ANRequest.this.mUploadProgressListener != null && !ANRequest.this.isCancelled) {
                    ANRequest.this.mUploadProgressListener.onProgress(j, j2);
                }
            }
        };
    }

    public String getDirPath() {
        return this.mDirPath;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void cancel(boolean z) {
        if (!z) {
            try {
                if (this.mPercentageThresholdForCancelling != 0 && this.mProgress >= this.mPercentageThresholdForCancelling) {
                    ANLog.d("not cancelling request : " + toString());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ANLog.d("cancelling request : " + toString());
        this.isCancelled = true;
        if (this.call != null) {
            this.call.cancel();
        }
        if (this.future != null) {
            this.future.cancel(true);
        }
        if (!this.isDelivered) {
            deliverError(new ANError());
        }
    }

    public boolean isCanceled() {
        return this.isCancelled;
    }

    public Call getCall() {
        return this.call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public Future getFuture() {
        return this.future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public void destroy() {
        this.mJSONArrayRequestListener = null;
        this.mJSONArrayRequestListener = null;
        this.mStringRequestListener = null;
        this.mBitmapRequestListener = null;
        this.mParsedRequestListener = null;
        this.mDownloadProgressListener = null;
        this.mUploadProgressListener = null;
        this.mDownloadListener = null;
        this.mAnalyticsListener = null;
    }

    public void finish() {
        destroy();
        ANRequestQueue.getInstance().finish(this);
    }

    public ANResponse parseResponse(Response response) {
        switch (this.mResponseType) {
            case JSON_ARRAY:
                try {
                    return ANResponse.success(new JSONArray(Okio.buffer(response.body().source()).readUtf8()));
                } catch (Throwable e) {
                    return ANResponse.failed(Utils.getErrorForParse(new ANError(e)));
                }
            case JSON_OBJECT:
                try {
                    return ANResponse.success(new JSONObject(Okio.buffer(response.body().source()).readUtf8()));
                } catch (Throwable e2) {
                    return ANResponse.failed(Utils.getErrorForParse(new ANError(e2)));
                }
            case STRING:
                try {
                    return ANResponse.success(Okio.buffer(response.body().source()).readUtf8());
                } catch (Throwable e22) {
                    return ANResponse.failed(Utils.getErrorForParse(new ANError(e22)));
                }
            case BITMAP:
                ANResponse decodeBitmap;
                synchronized (sDecodeLock) {
                    try {
                        decodeBitmap = Utils.decodeBitmap(response, this.mMaxWidth, this.mMaxHeight, this.mDecodeConfig, this.mScaleType);
                    } catch (Throwable e222) {
                        return ANResponse.failed(Utils.getErrorForParse(new ANError(e222)));
                    }
                }
                return decodeBitmap;
            case PREFETCH:
                return ANResponse.success(ANConstants.PREFETCH);
            default:
                return null;
        }
    }

    public ANError parseNetworkError(ANError aNError) {
        try {
            if (!(aNError.getResponse() == null || aNError.getResponse().body() == null || aNError.getResponse().body().source() == null)) {
                aNError.setErrorBody(Okio.buffer(aNError.getResponse().body().source()).readUtf8());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aNError;
    }

    public synchronized void deliverError(ANError aNError) {
        try {
            if (!this.isDelivered) {
                if (this.isCancelled) {
                    aNError.setCancellationMessageInError();
                    aNError.setErrorCode(0);
                }
                deliverErrorResponse(aNError);
                ANLog.d("Delivering anError : " + toString());
            }
            this.isDelivered = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deliverResponse(final ANResponse aNResponse) {
        try {
            this.isDelivered = true;
            if (this.isCancelled) {
                ANError aNError = new ANError();
                aNError.setCancellationMessageInError();
                aNError.setErrorCode(0);
                deliverErrorResponse(aNError);
                finish();
                ANLog.d("Delivering cancelled : " + toString());
                return;
            }
            if (this.mExecutor != null) {
                this.mExecutor.execute(new Runnable() {
                    public void run() {
                        ANRequest.this.deliverSuccessResponse(aNResponse);
                    }
                });
            } else {
                Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() {
                    public void run() {
                        ANRequest.this.deliverSuccessResponse(aNResponse);
                    }
                });
            }
            ANLog.d("Delivering success : " + toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deliverSuccessResponse(ANResponse aNResponse) {
        if (this.mJSONObjectRequestListener != null) {
            this.mJSONObjectRequestListener.onResponse((JSONObject) aNResponse.getResult());
        } else if (this.mJSONArrayRequestListener != null) {
            this.mJSONArrayRequestListener.onResponse((JSONArray) aNResponse.getResult());
        } else if (this.mStringRequestListener != null) {
            this.mStringRequestListener.onResponse((String) aNResponse.getResult());
        } else if (this.mBitmapRequestListener != null) {
            this.mBitmapRequestListener.onResponse((Bitmap) aNResponse.getResult());
        } else if (this.mParsedRequestListener != null) {
            this.mParsedRequestListener.onResponse(aNResponse.getResult());
        } else if (this.mOkHttpResponseAndJSONObjectRequestListener != null) {
            this.mOkHttpResponseAndJSONObjectRequestListener.onResponse(aNResponse.getOkHttpResponse(), (JSONObject) aNResponse.getResult());
        } else if (this.mOkHttpResponseAndJSONArrayRequestListener != null) {
            this.mOkHttpResponseAndJSONArrayRequestListener.onResponse(aNResponse.getOkHttpResponse(), (JSONArray) aNResponse.getResult());
        } else if (this.mOkHttpResponseAndStringRequestListener != null) {
            this.mOkHttpResponseAndStringRequestListener.onResponse(aNResponse.getOkHttpResponse(), (String) aNResponse.getResult());
        } else if (this.mOkHttpResponseAndBitmapRequestListener != null) {
            this.mOkHttpResponseAndBitmapRequestListener.onResponse(aNResponse.getOkHttpResponse(), (Bitmap) aNResponse.getResult());
        } else if (this.mOkHttpResponseAndParsedRequestListener != null) {
            this.mOkHttpResponseAndParsedRequestListener.onResponse(aNResponse.getOkHttpResponse(), aNResponse.getResult());
        }
        finish();
    }

    private void deliverErrorResponse(ANError aNError) {
        if (this.mJSONObjectRequestListener != null) {
            this.mJSONObjectRequestListener.onError(aNError);
        } else if (this.mJSONArrayRequestListener != null) {
            this.mJSONArrayRequestListener.onError(aNError);
        } else if (this.mStringRequestListener != null) {
            this.mStringRequestListener.onError(aNError);
        } else if (this.mBitmapRequestListener != null) {
            this.mBitmapRequestListener.onError(aNError);
        } else if (this.mParsedRequestListener != null) {
            this.mParsedRequestListener.onError(aNError);
        } else if (this.mOkHttpResponseAndJSONObjectRequestListener != null) {
            this.mOkHttpResponseAndJSONObjectRequestListener.onError(aNError);
        } else if (this.mOkHttpResponseAndJSONArrayRequestListener != null) {
            this.mOkHttpResponseAndJSONArrayRequestListener.onError(aNError);
        } else if (this.mOkHttpResponseAndStringRequestListener != null) {
            this.mOkHttpResponseAndStringRequestListener.onError(aNError);
        } else if (this.mOkHttpResponseAndBitmapRequestListener != null) {
            this.mOkHttpResponseAndBitmapRequestListener.onError(aNError);
        } else if (this.mOkHttpResponseAndParsedRequestListener != null) {
            this.mOkHttpResponseAndParsedRequestListener.onError(aNError);
        } else if (this.mDownloadListener != null) {
            this.mDownloadListener.onError(aNError);
        }
    }

    public void deliverOkHttpResponse(final Response response) {
        try {
            this.isDelivered = true;
            if (this.isCancelled) {
                ANError aNError = new ANError();
                aNError.setCancellationMessageInError();
                aNError.setErrorCode(0);
                if (this.mOkHttpResponseListener != null) {
                    this.mOkHttpResponseListener.onError(aNError);
                }
                finish();
                ANLog.d("Delivering cancelled : " + toString());
                return;
            }
            if (this.mExecutor != null) {
                this.mExecutor.execute(new Runnable() {
                    public void run() {
                        if (ANRequest.this.mOkHttpResponseListener != null) {
                            ANRequest.this.mOkHttpResponseListener.onResponse(response);
                        }
                        ANRequest.this.finish();
                    }
                });
            } else {
                Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() {
                    public void run() {
                        if (ANRequest.this.mOkHttpResponseListener != null) {
                            ANRequest.this.mOkHttpResponseListener.onResponse(response);
                        }
                        ANRequest.this.finish();
                    }
                });
            }
            ANLog.d("Delivering success : " + toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestBody getRequestBody() {
        if (this.mJsonObject != null) {
            if (this.customMediaType != null) {
                return RequestBody.create(this.customMediaType, this.mJsonObject.toString());
            }
            return RequestBody.create(JSON_MEDIA_TYPE, this.mJsonObject.toString());
        } else if (this.mJsonArray != null) {
            if (this.customMediaType != null) {
                return RequestBody.create(this.customMediaType, this.mJsonArray.toString());
            }
            return RequestBody.create(JSON_MEDIA_TYPE, this.mJsonArray.toString());
        } else if (this.mStringBody != null) {
            if (this.customMediaType != null) {
                return RequestBody.create(this.customMediaType, this.mStringBody);
            }
            return RequestBody.create(MEDIA_TYPE_MARKDOWN, this.mStringBody);
        } else if (this.mFile != null) {
            if (this.customMediaType != null) {
                return RequestBody.create(this.customMediaType, this.mFile);
            }
            return RequestBody.create(MEDIA_TYPE_MARKDOWN, this.mFile);
        } else if (this.mByte == null) {
            FormBody.Builder builder = new FormBody.Builder();
            try {
                for (Entry entry : this.mBodyParameterMap.entrySet()) {
                    if (!(TextUtils.isEmpty((CharSequence) entry.getKey()) || TextUtils.isEmpty((CharSequence) entry.getValue()))) {
                        builder.add((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                for (Entry entry2 : this.mUrlEncodedFormBodyParameterMap.entrySet()) {
                    if (!(TextUtils.isEmpty((CharSequence) entry2.getKey()) || TextUtils.isEmpty((CharSequence) entry2.getValue()))) {
                        builder.addEncoded((String) entry2.getKey(), (String) entry2.getValue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return builder.build();
        } else if (this.customMediaType != null) {
            return RequestBody.create(this.customMediaType, this.mByte);
        } else {
            return RequestBody.create(MEDIA_TYPE_MARKDOWN, this.mByte);
        }
    }

    public RequestBody getMultiPartRequestBody() {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        try {
            for (Entry key : this.mMultiPartParameterMap.entrySet()) {
                String[] strArr = new String[]{"Content-Disposition", "form-data; name=\"" + ((String) key.getKey()) + "\""};
                type.addPart(Headers.of(strArr), RequestBody.create(null, (String) ((Entry) r3.next()).getValue()));
            }
            for (Entry key2 : this.mMultiPartFileMap.entrySet()) {
                if (key2.getValue() != null) {
                    type.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + ((String) key2.getKey()) + "\"; filename=\"" + r4 + "\""), RequestBody.create(MediaType.parse(Utils.getMimeType(((File) key2.getValue()).getName())), (File) key2.getValue()));
                    if (this.customMediaType != null) {
                        type.setType(this.customMediaType);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type.build();
    }

    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder();
        try {
            for (Entry entry : this.mHeadersMap.entrySet()) {
                builder.add((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    public String toString() {
        return "ANRequest{sequenceNumber='" + this.sequenceNumber + ", mMethod=" + this.mMethod + ", mPriority=" + this.mPriority + ", mRequestType=" + this.mRequestType + ", mUrl=" + this.mUrl + '}';
    }
}
