package com.facebook.stetho.inspector.network;

import android.os.SystemClock;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorHeaders;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorRequest;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketResponse;
import com.facebook.stetho.inspector.protocol.module.Console.CallFrame;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import com.facebook.stetho.inspector.protocol.module.Network.DataReceivedParams;
import com.facebook.stetho.inspector.protocol.module.Network.Initiator;
import com.facebook.stetho.inspector.protocol.module.Network.InitiatorType;
import com.facebook.stetho.inspector.protocol.module.Network.LoadingFailedParams;
import com.facebook.stetho.inspector.protocol.module.Network.LoadingFinishedParams;
import com.facebook.stetho.inspector.protocol.module.Network.Request;
import com.facebook.stetho.inspector.protocol.module.Network.RequestWillBeSentParams;
import com.facebook.stetho.inspector.protocol.module.Network.Response;
import com.facebook.stetho.inspector.protocol.module.Network.ResponseReceivedParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketClosedParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketCreatedParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketFrame;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketFrameErrorParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketFrameReceivedParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketFrameSentParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketHandshakeResponseReceivedParams;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketRequest;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketResponse;
import com.facebook.stetho.inspector.protocol.module.Network.WebSocketWillSendHandshakeRequestParams;
import com.facebook.stetho.inspector.protocol.module.Page.ResourceType;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

public class NetworkEventReporterImpl implements NetworkEventReporter {
    private static NetworkEventReporter sInstance;
    private final AtomicInteger mNextRequestId = new AtomicInteger(0);
    @Nullable
    private ResourceTypeHelper mResourceTypeHelper;

    private NetworkEventReporterImpl() {
    }

    public static synchronized NetworkEventReporter get() {
        NetworkEventReporter networkEventReporter;
        synchronized (NetworkEventReporterImpl.class) {
            if (sInstance == null) {
                sInstance = new NetworkEventReporterImpl();
            }
            networkEventReporter = sInstance;
        }
        return networkEventReporter;
    }

    public boolean isEnabled() {
        return getPeerManagerIfEnabled() != null;
    }

    @Nullable
    private NetworkPeerManager getPeerManagerIfEnabled() {
        NetworkPeerManager instanceOrNull = NetworkPeerManager.getInstanceOrNull();
        return (instanceOrNull == null || !instanceOrNull.hasRegisteredPeers()) ? null : instanceOrNull;
    }

    public void requestWillBeSent(InspectorRequest inspectorRequest) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            Request request = new Request();
            request.url = inspectorRequest.url();
            request.method = inspectorRequest.method();
            request.headers = formatHeadersAsJSON(inspectorRequest);
            request.postData = readBodyAsString(peerManagerIfEnabled, inspectorRequest);
            String friendlyName = inspectorRequest.friendlyName();
            Integer friendlyNameExtra = inspectorRequest.friendlyNameExtra();
            Initiator initiator = new Initiator();
            initiator.type = InitiatorType.SCRIPT;
            initiator.stackTrace = new ArrayList();
            initiator.stackTrace.add(new CallFrame(friendlyName, friendlyName, friendlyNameExtra != null ? friendlyNameExtra.intValue() : 0, 0));
            RequestWillBeSentParams requestWillBeSentParams = new RequestWillBeSentParams();
            requestWillBeSentParams.requestId = inspectorRequest.id();
            requestWillBeSentParams.frameId = "1";
            requestWillBeSentParams.loaderId = "1";
            requestWillBeSentParams.documentURL = inspectorRequest.url();
            requestWillBeSentParams.request = request;
            requestWillBeSentParams.timestamp = ((double) stethoNow()) / 1000.0d;
            requestWillBeSentParams.initiator = initiator;
            requestWillBeSentParams.redirectResponse = null;
            requestWillBeSentParams.type = ResourceType.OTHER;
            peerManagerIfEnabled.sendNotificationToPeers("Network.requestWillBeSent", requestWillBeSentParams);
        }
    }

    @Nullable
    private static String readBodyAsString(NetworkPeerManager networkPeerManager, InspectorRequest inspectorRequest) {
        Object e;
        try {
            byte[] body = inspectorRequest.body();
            if (body != null) {
                return new String(body, Utf8Charset.INSTANCE);
            }
        } catch (IOException e2) {
            e = e2;
            CLog.writeToConsole(networkPeerManager, MessageLevel.WARNING, MessageSource.NETWORK, "Could not reproduce POST body: " + e);
            return null;
        } catch (OutOfMemoryError e3) {
            e = e3;
            CLog.writeToConsole(networkPeerManager, MessageLevel.WARNING, MessageSource.NETWORK, "Could not reproduce POST body: " + e);
            return null;
        }
        return null;
    }

    public void responseHeadersReceived(NetworkEventReporter$InspectorResponse networkEventReporter$InspectorResponse) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            Response response = new Response();
            response.url = networkEventReporter$InspectorResponse.url();
            response.status = networkEventReporter$InspectorResponse.statusCode();
            response.statusText = networkEventReporter$InspectorResponse.reasonPhrase();
            response.headers = formatHeadersAsJSON(networkEventReporter$InspectorResponse);
            String contentType = getContentType(networkEventReporter$InspectorResponse);
            response.mimeType = contentType != null ? getResourceTypeHelper().stripContentExtras(contentType) : OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
            response.connectionReused = networkEventReporter$InspectorResponse.connectionReused();
            response.connectionId = networkEventReporter$InspectorResponse.connectionId();
            response.fromDiskCache = Boolean.valueOf(networkEventReporter$InspectorResponse.fromDiskCache());
            ResponseReceivedParams responseReceivedParams = new ResponseReceivedParams();
            responseReceivedParams.requestId = networkEventReporter$InspectorResponse.requestId();
            responseReceivedParams.frameId = "1";
            responseReceivedParams.loaderId = "1";
            responseReceivedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            responseReceivedParams.response = response;
            responseReceivedParams.type = determineResourceType(initAsyncPrettyPrinterForResponse(networkEventReporter$InspectorResponse, peerManagerIfEnabled), contentType, getResourceTypeHelper());
            peerManagerIfEnabled.sendNotificationToPeers("Network.responseReceived", responseReceivedParams);
        }
    }

    @Nullable
    private static AsyncPrettyPrinter initAsyncPrettyPrinterForResponse(NetworkEventReporter$InspectorResponse networkEventReporter$InspectorResponse, NetworkPeerManager networkPeerManager) {
        AsyncPrettyPrinter createPrettyPrinterForResponse = createPrettyPrinterForResponse(networkEventReporter$InspectorResponse, networkPeerManager.getAsyncPrettyPrinterRegistry());
        if (createPrettyPrinterForResponse != null) {
            networkPeerManager.getResponseBodyFileManager().associateAsyncPrettyPrinterWithId(networkEventReporter$InspectorResponse.requestId(), createPrettyPrinterForResponse);
        }
        return createPrettyPrinterForResponse;
    }

    private static ResourceType determineResourceType(AsyncPrettyPrinter asyncPrettyPrinter, String str, ResourceTypeHelper resourceTypeHelper) {
        if (asyncPrettyPrinter != null) {
            return asyncPrettyPrinter.getPrettifiedType().getResourceType();
        }
        return str != null ? resourceTypeHelper.determineResourceType(str) : ResourceType.OTHER;
    }

    @Nullable
    static AsyncPrettyPrinter createPrettyPrinterForResponse(NetworkEventReporter$InspectorResponse networkEventReporter$InspectorResponse, @Nullable AsyncPrettyPrinterRegistry asyncPrettyPrinterRegistry) {
        if (asyncPrettyPrinterRegistry != null) {
            int headerCount = networkEventReporter$InspectorResponse.headerCount();
            for (int i = 0; i < headerCount; i++) {
                AsyncPrettyPrinterFactory lookup = asyncPrettyPrinterRegistry.lookup(networkEventReporter$InspectorResponse.headerName(i));
                if (lookup != null) {
                    return lookup.getInstance(networkEventReporter$InspectorResponse.headerName(i), networkEventReporter$InspectorResponse.headerValue(i));
                }
            }
        }
        return null;
    }

    public InputStream interpretResponseStream(String str, @Nullable String str2, @Nullable String str3, @Nullable InputStream inputStream, ResponseHandler responseHandler) {
        ChromePeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled == null) {
            return inputStream;
        }
        if (inputStream == null) {
            responseHandler.onEOF();
            return null;
        }
        ResourceType determineResourceType = str2 != null ? getResourceTypeHelper().determineResourceType(str2) : null;
        boolean z = false;
        if (determineResourceType != null && determineResourceType == ResourceType.IMAGE) {
            z = true;
        }
        try {
            return DecompressionHelper.teeInputWithDecompression(peerManagerIfEnabled, str, inputStream, peerManagerIfEnabled.getResponseBodyFileManager().openResponseBodyFile(str, z), str3, responseHandler);
        } catch (IOException e) {
            CLog.writeToConsole(peerManagerIfEnabled, MessageLevel.ERROR, MessageSource.NETWORK, "Error writing response body data for request #" + str);
            return inputStream;
        }
    }

    public void httpExchangeFailed(String str, String str2) {
        loadingFailed(str, str2);
    }

    public void responseReadFinished(String str) {
        loadingFinished(str);
    }

    private void loadingFinished(String str) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            LoadingFinishedParams loadingFinishedParams = new LoadingFinishedParams();
            loadingFinishedParams.requestId = str;
            loadingFinishedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            peerManagerIfEnabled.sendNotificationToPeers("Network.loadingFinished", loadingFinishedParams);
        }
    }

    public void responseReadFailed(String str, String str2) {
        loadingFailed(str, str2);
    }

    private void loadingFailed(String str, String str2) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            LoadingFailedParams loadingFailedParams = new LoadingFailedParams();
            loadingFailedParams.requestId = str;
            loadingFailedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            loadingFailedParams.errorText = str2;
            loadingFailedParams.type = ResourceType.OTHER;
            peerManagerIfEnabled.sendNotificationToPeers("Network.loadingFailed", loadingFailedParams);
        }
    }

    public void dataSent(String str, int i, int i2) {
        dataReceived(str, i, i2);
    }

    public void dataReceived(String str, int i, int i2) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            DataReceivedParams dataReceivedParams = new DataReceivedParams();
            dataReceivedParams.requestId = str;
            dataReceivedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            dataReceivedParams.dataLength = i;
            dataReceivedParams.encodedDataLength = i2;
            peerManagerIfEnabled.sendNotificationToPeers("Network.dataReceived", dataReceivedParams);
        }
    }

    public String nextRequestId() {
        return String.valueOf(this.mNextRequestId.getAndIncrement());
    }

    @Nullable
    private String getContentType(InspectorHeaders inspectorHeaders) {
        return inspectorHeaders.firstHeaderValue("Content-Type");
    }

    public void webSocketCreated(String str, String str2) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            WebSocketCreatedParams webSocketCreatedParams = new WebSocketCreatedParams();
            webSocketCreatedParams.requestId = str;
            webSocketCreatedParams.url = str2;
            peerManagerIfEnabled.sendNotificationToPeers("Network.webSocketCreated", webSocketCreatedParams);
        }
    }

    public void webSocketClosed(String str) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            WebSocketClosedParams webSocketClosedParams = new WebSocketClosedParams();
            webSocketClosedParams.requestId = str;
            webSocketClosedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            peerManagerIfEnabled.sendNotificationToPeers("Network.webSocketClosed", webSocketClosedParams);
        }
    }

    public void webSocketWillSendHandshakeRequest(NetworkEventReporter$InspectorWebSocketRequest networkEventReporter$InspectorWebSocketRequest) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            WebSocketWillSendHandshakeRequestParams webSocketWillSendHandshakeRequestParams = new WebSocketWillSendHandshakeRequestParams();
            webSocketWillSendHandshakeRequestParams.requestId = networkEventReporter$InspectorWebSocketRequest.id();
            webSocketWillSendHandshakeRequestParams.timestamp = ((double) stethoNow()) / 1000.0d;
            webSocketWillSendHandshakeRequestParams.wallTime = ((double) System.currentTimeMillis()) / 1000.0d;
            WebSocketRequest webSocketRequest = new WebSocketRequest();
            webSocketRequest.headers = formatHeadersAsJSON(networkEventReporter$InspectorWebSocketRequest);
            webSocketWillSendHandshakeRequestParams.request = webSocketRequest;
            peerManagerIfEnabled.sendNotificationToPeers("Network.webSocketWillSendHandshakeRequest", webSocketWillSendHandshakeRequestParams);
        }
    }

    public void webSocketHandshakeResponseReceived(InspectorWebSocketResponse inspectorWebSocketResponse) {
        if (getPeerManagerIfEnabled() != null) {
            WebSocketHandshakeResponseReceivedParams webSocketHandshakeResponseReceivedParams = new WebSocketHandshakeResponseReceivedParams();
            webSocketHandshakeResponseReceivedParams.requestId = inspectorWebSocketResponse.requestId();
            webSocketHandshakeResponseReceivedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            WebSocketResponse webSocketResponse = new WebSocketResponse();
            webSocketResponse.headers = formatHeadersAsJSON(inspectorWebSocketResponse);
            webSocketResponse.headersText = null;
            if (inspectorWebSocketResponse.requestHeaders() != null) {
                webSocketResponse.requestHeaders = formatHeadersAsJSON(inspectorWebSocketResponse.requestHeaders());
                webSocketResponse.requestHeadersText = null;
            }
            webSocketResponse.status = inspectorWebSocketResponse.statusCode();
            webSocketResponse.statusText = inspectorWebSocketResponse.reasonPhrase();
            webSocketHandshakeResponseReceivedParams.response = webSocketResponse;
        }
    }

    public void webSocketFrameSent(NetworkEventReporter$InspectorWebSocketFrame networkEventReporter$InspectorWebSocketFrame) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            WebSocketFrameSentParams webSocketFrameSentParams = new WebSocketFrameSentParams();
            webSocketFrameSentParams.requestId = networkEventReporter$InspectorWebSocketFrame.requestId();
            webSocketFrameSentParams.timestamp = ((double) stethoNow()) / 1000.0d;
            webSocketFrameSentParams.response = convertFrame(networkEventReporter$InspectorWebSocketFrame);
            peerManagerIfEnabled.sendNotificationToPeers("Network.webSocketFrameSent", webSocketFrameSentParams);
        }
    }

    public void webSocketFrameReceived(NetworkEventReporter$InspectorWebSocketFrame networkEventReporter$InspectorWebSocketFrame) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            WebSocketFrameReceivedParams webSocketFrameReceivedParams = new WebSocketFrameReceivedParams();
            webSocketFrameReceivedParams.requestId = networkEventReporter$InspectorWebSocketFrame.requestId();
            webSocketFrameReceivedParams.timestamp = ((double) stethoNow()) / 1000.0d;
            webSocketFrameReceivedParams.response = convertFrame(networkEventReporter$InspectorWebSocketFrame);
            peerManagerIfEnabled.sendNotificationToPeers("Network.webSocketFrameReceived", webSocketFrameReceivedParams);
        }
    }

    private static WebSocketFrame convertFrame(NetworkEventReporter$InspectorWebSocketFrame networkEventReporter$InspectorWebSocketFrame) {
        WebSocketFrame webSocketFrame = new WebSocketFrame();
        webSocketFrame.opcode = networkEventReporter$InspectorWebSocketFrame.opcode();
        webSocketFrame.mask = networkEventReporter$InspectorWebSocketFrame.mask();
        webSocketFrame.payloadData = networkEventReporter$InspectorWebSocketFrame.payloadData();
        return webSocketFrame;
    }

    public void webSocketFrameError(String str, String str2) {
        NetworkPeerManager peerManagerIfEnabled = getPeerManagerIfEnabled();
        if (peerManagerIfEnabled != null) {
            WebSocketFrameErrorParams webSocketFrameErrorParams = new WebSocketFrameErrorParams();
            webSocketFrameErrorParams.requestId = str;
            webSocketFrameErrorParams.timestamp = ((double) stethoNow()) / 1000.0d;
            webSocketFrameErrorParams.errorMessage = str2;
            peerManagerIfEnabled.sendNotificationToPeers("Network.webSocketFrameError", webSocketFrameErrorParams);
        }
    }

    private static JSONObject formatHeadersAsJSON(InspectorHeaders inspectorHeaders) {
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < inspectorHeaders.headerCount()) {
            String headerName = inspectorHeaders.headerName(i);
            String headerValue = inspectorHeaders.headerValue(i);
            try {
                if (jSONObject.has(headerName)) {
                    jSONObject.put(headerName, jSONObject.getString(headerName) + "\n" + headerValue);
                } else {
                    jSONObject.put(headerName, headerValue);
                }
                i++;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return jSONObject;
    }

    @Nonnull
    private ResourceTypeHelper getResourceTypeHelper() {
        if (this.mResourceTypeHelper == null) {
            this.mResourceTypeHelper = new ResourceTypeHelper();
        }
        return this.mResourceTypeHelper;
    }

    private static long stethoNow() {
        return SystemClock.elapsedRealtime();
    }
}
