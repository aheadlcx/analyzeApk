package com.meizu.cloud.pushsdk.pushtracer.emitter;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.meizu.cloud.pushsdk.networking.http.HttpURLConnectionCall;
import com.meizu.cloud.pushsdk.networking.http.MediaType;
import com.meizu.cloud.pushsdk.networking.http.Request;
import com.meizu.cloud.pushsdk.networking.http.RequestBody;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.constant.TrackerConstants;
import com.meizu.cloud.pushsdk.pushtracer.dataload.DataLoad;
import com.meizu.cloud.pushsdk.pushtracer.dataload.SelfDescribingJson;
import com.meizu.cloud.pushsdk.pushtracer.storage.Store;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public abstract class Emitter {
    protected final MediaType JSON = MediaType.parse(TrackerConstants.POST_CONTENT_TYPE);
    protected int POST_STM_BYTES = 22;
    protected int POST_WRAPPER_BYTES = 88;
    private final String TAG = Emitter.class.getSimpleName();
    protected BufferOption bufferOption;
    protected long byteLimitGet;
    protected long byteLimitPost;
    protected Context context;
    protected int emitterTick;
    protected int emptyLimit;
    protected HostnameVerifier hostnameVerifier;
    protected HttpMethod httpMethod;
    protected AtomicBoolean isRunning = new AtomicBoolean(false);
    protected RequestCallback requestCallback;
    protected RequestSecurity requestSecurity;
    protected int sendLimit;
    protected SSLSocketFactory sslSocketFactory;
    protected TimeUnit timeUnit;
    protected String uri;
    protected Builder uriBuilder;

    public static class EmitterBuilder {
        protected static Class<? extends Emitter> defaultEmitterClass;
        protected BufferOption bufferOption;
        protected long byteLimitGet;
        protected long byteLimitPost;
        protected final Context context;
        private Class<? extends Emitter> emitterClass;
        protected int emitterTick;
        protected int emptyLimit;
        protected HostnameVerifier hostnameVerifier;
        protected HttpMethod httpMethod;
        protected RequestCallback requestCallback;
        protected RequestSecurity requestSecurity;
        protected int sendLimit;
        protected SSLSocketFactory sslSocketFactory;
        protected TimeUnit timeUnit;
        protected final String uri;

        public EmitterBuilder(String str, Context context) {
            this(str, context, defaultEmitterClass);
        }

        public EmitterBuilder(String str, Context context, Class<? extends Emitter> cls) {
            this.requestCallback = null;
            this.httpMethod = HttpMethod.POST;
            this.bufferOption = BufferOption.Single;
            this.requestSecurity = RequestSecurity.HTTPS;
            this.emitterTick = 5;
            this.sendLimit = 250;
            this.emptyLimit = 5;
            this.byteLimitGet = 40000;
            this.byteLimitPost = 40000;
            this.timeUnit = TimeUnit.SECONDS;
            this.uri = str;
            this.context = context;
            this.emitterClass = cls;
        }

        public EmitterBuilder method(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public EmitterBuilder option(BufferOption bufferOption) {
            this.bufferOption = bufferOption;
            return this;
        }

        public EmitterBuilder security(RequestSecurity requestSecurity) {
            this.requestSecurity = requestSecurity;
            return this;
        }

        public EmitterBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
            return this;
        }

        public EmitterBuilder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public EmitterBuilder callback(RequestCallback requestCallback) {
            this.requestCallback = requestCallback;
            return this;
        }

        public EmitterBuilder tick(int i) {
            this.emitterTick = i;
            return this;
        }

        public EmitterBuilder sendLimit(int i) {
            this.sendLimit = i;
            return this;
        }

        public EmitterBuilder emptyLimit(int i) {
            this.emptyLimit = i;
            return this;
        }

        public EmitterBuilder byteLimitGet(long j) {
            this.byteLimitGet = j;
            return this;
        }

        public EmitterBuilder byteLimitPost(long j) {
            this.byteLimitPost = j;
            return this;
        }

        public EmitterBuilder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }
    }

    public abstract void add(DataLoad dataLoad);

    public abstract void add(DataLoad dataLoad, boolean z);

    public abstract void flush();

    public abstract boolean getEmitterStatus();

    public abstract Store getEventStore();

    public abstract void shutdown();

    public Emitter(EmitterBuilder emitterBuilder) {
        this.httpMethod = emitterBuilder.httpMethod;
        this.requestCallback = emitterBuilder.requestCallback;
        this.context = emitterBuilder.context;
        this.bufferOption = emitterBuilder.bufferOption;
        this.requestSecurity = emitterBuilder.requestSecurity;
        this.sslSocketFactory = emitterBuilder.sslSocketFactory;
        this.hostnameVerifier = emitterBuilder.hostnameVerifier;
        this.emitterTick = emitterBuilder.emitterTick;
        this.emptyLimit = emitterBuilder.emptyLimit;
        this.sendLimit = emitterBuilder.sendLimit;
        this.byteLimitGet = emitterBuilder.byteLimitGet;
        this.byteLimitPost = emitterBuilder.byteLimitPost;
        this.uri = emitterBuilder.uri;
        this.timeUnit = emitterBuilder.timeUnit;
        buildEmitterUri();
        Logger.i(this.TAG, "Emitter created successfully!", new Object[0]);
    }

    private void buildEmitterUri() {
        Logger.e(this.TAG, "security " + this.requestSecurity, new Object[0]);
        if (this.requestSecurity == RequestSecurity.HTTP) {
            this.uriBuilder = Uri.parse("http://" + this.uri).buildUpon();
        } else {
            this.uriBuilder = Uri.parse("https://" + this.uri).buildUpon();
        }
        if (this.httpMethod == HttpMethod.GET) {
            this.uriBuilder.appendPath("i");
        } else {
            this.uriBuilder.appendEncodedPath("push_data_report/mobile");
        }
    }

    private void buildHttpsSecurity() {
        if (this.requestSecurity == RequestSecurity.HTTPS) {
            if (this.sslSocketFactory == null) {
                Logger.e(this.TAG, "Https Ensure you have set SSLSocketFactory", new Object[0]);
            }
            if (this.hostnameVerifier == null) {
                Logger.e(this.TAG, "Https Ensure you have set HostnameVerifier", new Object[0]);
            }
        }
    }

    protected LinkedList<RequestResult> performSyncEmit(LinkedList<ReadyRequest> linkedList) {
        LinkedList<RequestResult> linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ReadyRequest readyRequest = (ReadyRequest) it.next();
            int requestSender = requestSender(readyRequest.getRequest());
            if (readyRequest.isOversize()) {
                linkedList2.add(new RequestResult(true, readyRequest.getEventIds()));
            } else {
                linkedList2.add(new RequestResult(isSuccessfulSend(requestSender), readyRequest.getEventIds()));
            }
        }
        return linkedList2;
    }

    protected int requestSender(Request request) {
        int code;
        Response response = null;
        try {
            Logger.d(this.TAG, "Sending request: %s", request);
            response = new HttpURLConnectionCall(request).execute();
            code = response.code();
        } catch (IOException e) {
            Logger.e(this.TAG, "Request sending failed: %s", e.toString());
            code = -1;
        } finally {
            close(response);
        }
        return code;
    }

    protected void close(Response response) {
        if (response != null) {
            try {
                if (response.body() != null) {
                    response.body().close();
                }
            } catch (Exception e) {
                Logger.d(this.TAG, "Unable to close source data", new Object[0]);
            }
        }
    }

    protected LinkedList<ReadyRequest> buildRequests(EmittableEvents emittableEvents) {
        int size = emittableEvents.getEvents().size();
        LinkedList eventIds = emittableEvents.getEventIds();
        LinkedList<ReadyRequest> linkedList = new LinkedList();
        int i;
        DataLoad dataLoad;
        if (this.httpMethod == HttpMethod.GET) {
            for (i = 0; i < size; i++) {
                LinkedList linkedList2 = new LinkedList();
                linkedList2.add(eventIds.get(i));
                dataLoad = (DataLoad) emittableEvents.getEvents().get(i);
                linkedList.add(new ReadyRequest(dataLoad.getByteSize() + ((long) this.POST_STM_BYTES) > this.byteLimitGet, requestBuilderGet(dataLoad), linkedList2));
            }
        } else {
            i = 0;
            while (i < size) {
                LinkedList linkedList3 = new LinkedList();
                ArrayList arrayList = new ArrayList();
                LinkedList linkedList4 = linkedList3;
                long j = 0;
                int i2 = i;
                while (i2 < this.bufferOption.getCode() + i && i2 < size) {
                    dataLoad = (DataLoad) emittableEvents.getEvents().get(i2);
                    long byteSize = dataLoad.getByteSize() + ((long) this.POST_STM_BYTES);
                    if (((long) this.POST_WRAPPER_BYTES) + byteSize > this.byteLimitPost) {
                        ArrayList arrayList2 = new ArrayList();
                        LinkedList linkedList5 = new LinkedList();
                        arrayList2.add(dataLoad);
                        linkedList5.add(eventIds.get(i2));
                        linkedList.add(new ReadyRequest(true, requestBuilderPost(arrayList2), linkedList5));
                    } else if (((j + byteSize) + ((long) this.POST_WRAPPER_BYTES)) + ((long) (arrayList.size() - 1)) > this.byteLimitPost) {
                        linkedList.add(new ReadyRequest(false, requestBuilderPost(arrayList), linkedList4));
                        arrayList = new ArrayList();
                        linkedList4 = new LinkedList();
                        arrayList.add(dataLoad);
                        linkedList4.add(eventIds.get(i2));
                        j = byteSize;
                    } else {
                        j += byteSize;
                        arrayList.add(dataLoad);
                        linkedList4.add(eventIds.get(i2));
                    }
                    i2++;
                }
                if (!arrayList.isEmpty()) {
                    linkedList.add(new ReadyRequest(false, requestBuilderPost(arrayList), linkedList4));
                }
                i += this.bufferOption.getCode();
            }
        }
        return linkedList;
    }

    private Request requestBuilderGet(DataLoad dataLoad) {
        addStmToEvent(dataLoad, "");
        this.uriBuilder.clearQuery();
        HashMap hashMap = (HashMap) dataLoad.getMap();
        for (String str : hashMap.keySet()) {
            this.uriBuilder.appendQueryParameter(str, (String) hashMap.get(str));
        }
        return new Request.Builder().url(this.uriBuilder.build().toString()).get().build();
    }

    private Request requestBuilderPost(ArrayList<DataLoad> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DataLoad dataLoad = (DataLoad) it.next();
            stringBuffer.append(dataLoad.toString());
            arrayList2.add(dataLoad.getMap());
        }
        SelfDescribingJson selfDescribingJson = new SelfDescribingJson("push_group_data", arrayList2);
        Logger.d(this.TAG, "final SelfDescribingJson " + selfDescribingJson, new Object[0]);
        String uri = this.uriBuilder.build().toString();
        return new Request.Builder().url(uri).post(RequestBody.create(this.JSON, selfDescribingJson.toString())).build();
    }

    private void addStmToEvent(DataLoad dataLoad, String str) {
        String str2 = Parameters.SENT_TIMESTAMP;
        if (str.equals("")) {
            str = Util.getTimestamp();
        }
        dataLoad.add(str2, str);
    }

    protected boolean isSuccessfulSend(int i) {
        return i >= 200 && i < 300;
    }

    public void setBufferOption(BufferOption bufferOption) {
        if (!this.isRunning.get()) {
            this.bufferOption = bufferOption;
        }
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        if (!this.isRunning.get()) {
            this.httpMethod = httpMethod;
            buildEmitterUri();
        }
    }

    public void setRequestSecurity(RequestSecurity requestSecurity) {
        if (!this.isRunning.get()) {
            this.requestSecurity = requestSecurity;
            buildEmitterUri();
        }
    }

    public void setEmitterUri(String str) {
        if (!this.isRunning.get()) {
            this.uri = str;
            buildEmitterUri();
        }
    }

    public String getEmitterUri() {
        return this.uriBuilder.clearQuery().build().toString();
    }

    public RequestCallback getRequestCallback() {
        return this.requestCallback;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public BufferOption getBufferOption() {
        return this.bufferOption;
    }

    public RequestSecurity getRequestSecurity() {
        return this.requestSecurity;
    }

    public int getEmitterTick() {
        return this.emitterTick;
    }

    public int getEmptyLimit() {
        return this.emptyLimit;
    }

    public int getSendLimit() {
        return this.sendLimit;
    }

    public long getByteLimitGet() {
        return this.byteLimitGet;
    }

    public long getByteLimitPost() {
        return this.byteLimitPost;
    }
}
