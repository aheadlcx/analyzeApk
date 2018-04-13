package com.alibaba.sdk.android.mns.internal;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import com.alibaba.sdk.android.common.ClientConfiguration;
import com.alibaba.sdk.android.common.HttpMethod;
import com.alibaba.sdk.android.common.auth.CredentialProvider;
import com.alibaba.sdk.android.common.utils.DateUtil;
import com.alibaba.sdk.android.mns.callback.MNSCompletedCallback;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.common.MNSConstants.MNSType;
import com.alibaba.sdk.android.mns.common.MNSHeaders;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.ChangeMessageVisibilityParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.CreateQueueResponseParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.DeleteMessageParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.DeleteQueueResponseParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.GetQueueAttributesResponseParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.ListQueueResponseParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.PeekMessageParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.ReceiveMessageParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers.SendMessageResponseParser;
import com.alibaba.sdk.android.mns.model.request.ChangeMessageVisibilityRequest;
import com.alibaba.sdk.android.mns.model.request.CreateQueueRequest;
import com.alibaba.sdk.android.mns.model.request.DeleteMessageRequest;
import com.alibaba.sdk.android.mns.model.request.DeleteQueueRequest;
import com.alibaba.sdk.android.mns.model.request.GetQueueAttributesRequest;
import com.alibaba.sdk.android.mns.model.request.ListQueueRequest;
import com.alibaba.sdk.android.mns.model.request.PeekMessageRequest;
import com.alibaba.sdk.android.mns.model.request.ReceiveMessageRequest;
import com.alibaba.sdk.android.mns.model.request.SendMessageRequest;
import com.alibaba.sdk.android.mns.model.request.SetQueueAttributesRequest;
import com.alibaba.sdk.android.mns.model.result.ChangeMessageVisibilityResult;
import com.alibaba.sdk.android.mns.model.result.CreateQueueResult;
import com.alibaba.sdk.android.mns.model.result.DeleteMessageResult;
import com.alibaba.sdk.android.mns.model.result.DeleteQueueResult;
import com.alibaba.sdk.android.mns.model.result.GetQueueAttributesResult;
import com.alibaba.sdk.android.mns.model.result.ListQueueResult;
import com.alibaba.sdk.android.mns.model.result.PeekMessageResult;
import com.alibaba.sdk.android.mns.model.result.ReceiveMessageResult;
import com.alibaba.sdk.android.mns.model.result.SendMessageResult;
import com.alibaba.sdk.android.mns.model.result.SetQueueAttributesResult;
import com.alibaba.sdk.android.mns.model.serialize.MessageSerializer;
import com.alibaba.sdk.android.mns.model.serialize.QueueMetaSerializer;
import com.alibaba.sdk.android.mns.network.ExecutionContext;
import com.alibaba.sdk.android.mns.network.MNSRequestTask;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import okhttp3.n;
import okhttp3.w;
import okhttp3.w.a;

public class MNSInternalRequestOperation {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Context applicationContext;
    private ClientConfiguration conf;
    private CredentialProvider credentialProvider;
    private volatile URI endpoint;
    private w innerClient;
    private int maxRetryCount = 2;

    private MNSInternalRequestOperation() {
    }

    public MNSInternalRequestOperation(Context context, final URI uri, CredentialProvider credentialProvider, ClientConfiguration clientConfiguration) {
        this.applicationContext = context;
        this.endpoint = uri;
        this.credentialProvider = credentialProvider;
        this.conf = clientConfiguration;
        a a = new a().b(false).a(false).c(false).a(null).a(new HostnameVerifier() {
            public boolean verify(String str, SSLSession sSLSession) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(uri.getHost(), sSLSession);
            }
        });
        if (clientConfiguration != null) {
            n nVar = new n();
            nVar.a(clientConfiguration.getMaxConcurrentRequest());
            a.a((long) clientConfiguration.getConnectionTimeout(), TimeUnit.MILLISECONDS).b((long) clientConfiguration.getSocketTimeout(), TimeUnit.MILLISECONDS).c((long) clientConfiguration.getSocketTimeout(), TimeUnit.MILLISECONDS).a(nVar);
            this.maxRetryCount = clientConfiguration.getMaxErrorRetry();
        }
        this.innerClient = a.a();
    }

    public MNSAsyncTask<CreateQueueResult> createQueue(CreateQueueRequest createQueueRequest, MNSCompletedCallback<CreateQueueRequest, CreateQueueResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setQueueName(createQueueRequest.getQueueName());
        requestMessage.setType(MNSType.QUEUE);
        try {
            requestMessage.setContent(new QueueMetaSerializer().serialize(createQueueRequest.getQueueMeta(), "utf-8"));
            addRequiredHeader(requestMessage);
            ExecutionContext executionContext = new ExecutionContext(getInnerClient(), createQueueRequest);
            if (mNSCompletedCallback != null) {
                executionContext.setCompletedCallback(mNSCompletedCallback);
            }
            return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new CreateQueueResponseParser(), executionContext)), executionContext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MNSAsyncTask<DeleteQueueResult> deleteQueue(DeleteQueueRequest deleteQueueRequest, MNSCompletedCallback<DeleteQueueRequest, DeleteQueueResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.DELETE);
        requestMessage.setQueueName(deleteQueueRequest.getQueueName());
        requestMessage.setType(MNSType.QUEUE);
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), deleteQueueRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new DeleteQueueResponseParser(), executionContext)), executionContext);
    }

    public MNSAsyncTask<SetQueueAttributesResult> setQueueAttr(SetQueueAttributesRequest setQueueAttributesRequest, MNSCompletedCallback<SetQueueAttributesRequest, SetQueueAttributesResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setQueueName(setQueueAttributesRequest.getQueueName());
        requestMessage.getParameters().put(MNSHeaders.MNS_META_OVERRIDE, "true");
        requestMessage.setType(MNSType.QUEUE);
        try {
            requestMessage.setContent(new QueueMetaSerializer().serialize(setQueueAttributesRequest.getQueueMeta(), "utf-8"));
            addRequiredHeader(requestMessage);
            ExecutionContext executionContext = new ExecutionContext(getInnerClient(), setQueueAttributesRequest);
            if (mNSCompletedCallback != null) {
                executionContext.setCompletedCallback(mNSCompletedCallback);
            }
            return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new CreateQueueResponseParser(), executionContext)), executionContext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MNSAsyncTask<GetQueueAttributesResult> getQueueAttr(GetQueueAttributesRequest getQueueAttributesRequest, MNSCompletedCallback<GetQueueAttributesRequest, GetQueueAttributesResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setQueueName(getQueueAttributesRequest.getQueueName());
        requestMessage.setType(MNSType.QUEUE);
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), getQueueAttributesRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new GetQueueAttributesResponseParser(), executionContext)), executionContext);
    }

    public MNSAsyncTask<ListQueueResult> listQueue(ListQueueRequest listQueueRequest, MNSCompletedCallback<ListQueueRequest, ListQueueResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setType(MNSType.QUEUE);
        if (!listQueueRequest.getPrefix().isEmpty()) {
            requestMessage.getHeaders().put(MNSHeaders.MNS_PREFIX, listQueueRequest.getPrefix());
        }
        if (!listQueueRequest.getMarker().isEmpty()) {
            requestMessage.getHeaders().put(MNSHeaders.MNS_MARKER, listQueueRequest.getMarker());
        }
        requestMessage.getHeaders().put(MNSHeaders.MNS_RET_NUMBERS, listQueueRequest.getRetNum().toString());
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), listQueueRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new ListQueueResponseParser(), executionContext)), executionContext);
    }

    public MNSAsyncTask<SendMessageResult> sendMessage(SendMessageRequest sendMessageRequest, MNSCompletedCallback<SendMessageRequest, SendMessageResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.POST);
        requestMessage.setQueueName(sendMessageRequest.getQueueName());
        requestMessage.setType(MNSType.MESSAGE);
        try {
            requestMessage.setContent(new MessageSerializer().serialize(sendMessageRequest.getMessage(), "utf-8"));
            addRequiredHeader(requestMessage);
            ExecutionContext executionContext = new ExecutionContext(getInnerClient(), sendMessageRequest);
            if (mNSCompletedCallback != null) {
                executionContext.setCompletedCallback(mNSCompletedCallback);
            }
            return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new SendMessageResponseParser(), executionContext)), executionContext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MNSAsyncTask<ReceiveMessageResult> receiveMessage(ReceiveMessageRequest receiveMessageRequest, MNSCompletedCallback<ReceiveMessageRequest, ReceiveMessageResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setQueueName(receiveMessageRequest.getQueueName());
        requestMessage.setType(MNSType.MESSAGE);
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), receiveMessageRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new ReceiveMessageParser(), executionContext)), executionContext);
    }

    public MNSAsyncTask<DeleteMessageResult> deleteMessage(DeleteMessageRequest deleteMessageRequest, MNSCompletedCallback<DeleteMessageRequest, DeleteMessageResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.DELETE);
        requestMessage.setQueueName(deleteMessageRequest.getQueueName());
        requestMessage.setType(MNSType.MESSAGE);
        requestMessage.getParameters().put(MNSConstants.RECEIPT_HANDLE_TAG, deleteMessageRequest.getReceiptHandle());
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), deleteMessageRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new DeleteMessageParser(), executionContext)), executionContext);
    }

    public MNSAsyncTask<PeekMessageResult> peekMessage(PeekMessageRequest peekMessageRequest, MNSCompletedCallback<PeekMessageRequest, PeekMessageResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setQueueName(peekMessageRequest.getQueueName());
        requestMessage.setType(MNSType.MESSAGE);
        requestMessage.getParameters().put(MNSHeaders.MNS_PEEK_ONLY, "true");
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), peekMessageRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new PeekMessageParser(), executionContext)), executionContext);
    }

    public MNSAsyncTask<ChangeMessageVisibilityResult> changeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest, MNSCompletedCallback<ChangeMessageVisibilityRequest, ChangeMessageVisibilityResult> mNSCompletedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(requestMessage.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setQueueName(changeMessageVisibilityRequest.getQueueName());
        requestMessage.setType(MNSType.MESSAGE);
        requestMessage.getParameters().put(MNSConstants.RECEIPT_HANDLE_TAG, changeMessageVisibilityRequest.getReceiptHandle());
        requestMessage.getParameters().put(MNSConstants.VISIBILITY_TIMEOUT, changeMessageVisibilityRequest.getVisibleTime().toString());
        addRequiredHeader(requestMessage);
        ExecutionContext executionContext = new ExecutionContext(getInnerClient(), changeMessageVisibilityRequest);
        if (mNSCompletedCallback != null) {
            executionContext.setCompletedCallback(mNSCompletedCallback);
        }
        return MNSAsyncTask.wrapRequestTask(executorService.submit(new MNSRequestTask(requestMessage, new ChangeMessageVisibilityParser(), executionContext)), executionContext);
    }

    private boolean checkIfHttpdnsAwailable() {
        boolean z = true;
        if (this.applicationContext == null) {
            return false;
        }
        String property;
        if (VERSION.SDK_INT >= 14) {
            property = System.getProperty("http.proxyHost");
        } else {
            property = Proxy.getHost(this.applicationContext);
        }
        if (property != null) {
            z = false;
        }
        return z;
    }

    public w getInnerClient() {
        return this.innerClient;
    }

    private void addRequiredHeader(RequestMessage requestMessage) {
        Map headers = requestMessage.getHeaders();
        if (headers.get("Date") == null) {
            headers.put("Date", DateUtil.currentFixedSkewedTimeInRFC822Format());
        }
        if (headers.get("Content-Type") == null) {
            headers.put("Content-Type", MNSConstants.DEFAULT_CONTENT_TYPE);
        }
        headers.put(MNSConstants.X_HEADER_MNS_API_VERSION, MNSConstants.X_HEADER_MNS_API_VERSION_VALUE);
        requestMessage.setCredentialProvider(this.credentialProvider);
    }

    public void setCredentialProvider(CredentialProvider credentialProvider) {
        this.credentialProvider = credentialProvider;
    }
}
