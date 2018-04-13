package com.alibaba.sdk.android.mns.internal;

import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.common.MNSHeaders;
import com.alibaba.sdk.android.mns.model.deserialize.ChangeVisibilityDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.ErrorMessageListDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.MessageDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.QueueArrayDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.QueueMetaDeserializer;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.aa;
import okhttp3.s;

public class ResponseParsers {

    public static final class ChangeMessageVisibilityParser implements ResponseParser<ChangeMessageVisibilityResult> {
        public ChangeMessageVisibilityResult parse(aa aaVar) throws IOException {
            try {
                ChangeMessageVisibilityResult changeMessageVisibilityResult = new ChangeMessageVisibilityResult();
                changeMessageVisibilityResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                changeMessageVisibilityResult.setStatusCode(aaVar.b());
                changeMessageVisibilityResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                changeMessageVisibilityResult.setChangeVisibleResponse(new ChangeVisibilityDeserializer().deserialize(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return changeMessageVisibilityResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class CreateQueueResponseParser implements ResponseParser<CreateQueueResult> {
        public CreateQueueResult parse(aa aaVar) throws IOException {
            try {
                CreateQueueResult createQueueResult = new CreateQueueResult();
                createQueueResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                createQueueResult.setStatusCode(aaVar.b());
                createQueueResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                createQueueResult.setQueueLocation((String) createQueueResult.getResponseHeader().get("Location"));
                ResponseParsers.safeCloseResponse(aaVar);
                return createQueueResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class DeleteMessageParser implements ResponseParser<DeleteMessageResult> {
        public DeleteMessageResult parse(aa aaVar) throws IOException {
            try {
                DeleteMessageResult deleteMessageResult = new DeleteMessageResult();
                deleteMessageResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                deleteMessageResult.setStatusCode(aaVar.b());
                deleteMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return deleteMessageResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class DeleteQueueResponseParser implements ResponseParser<DeleteQueueResult> {
        public DeleteQueueResult parse(aa aaVar) throws IOException {
            try {
                DeleteQueueResult deleteQueueResult = new DeleteQueueResult();
                deleteQueueResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                deleteQueueResult.setStatusCode(aaVar.b());
                deleteQueueResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return deleteQueueResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class GetQueueAttributesResponseParser implements ResponseParser<GetQueueAttributesResult> {
        public GetQueueAttributesResult parse(aa aaVar) throws IOException {
            try {
                GetQueueAttributesResult getQueueAttributesResult = new GetQueueAttributesResult();
                getQueueAttributesResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                getQueueAttributesResult.setStatusCode(aaVar.b());
                getQueueAttributesResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                getQueueAttributesResult.setQueueMeta(new QueueMetaDeserializer().deserialize(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return getQueueAttributesResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class ListQueueResponseParser implements ResponseParser<ListQueueResult> {
        public ListQueueResult parse(aa aaVar) throws IOException {
            try {
                ListQueueResult listQueueResult = new ListQueueResult();
                listQueueResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                listQueueResult.setStatusCode(aaVar.b());
                listQueueResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                listQueueResult.setQueueLists(new QueueArrayDeserializer().deserialize(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return listQueueResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class PeekMessageParser implements ResponseParser<PeekMessageResult> {
        public PeekMessageResult parse(aa aaVar) throws IOException {
            try {
                PeekMessageResult peekMessageResult = new PeekMessageResult();
                peekMessageResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                peekMessageResult.setStatusCode(aaVar.b());
                peekMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                peekMessageResult.setMessage(new MessageDeserializer().deserialize(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return peekMessageResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class ReceiveMessageParser implements ResponseParser<ReceiveMessageResult> {
        public ReceiveMessageResult parse(aa aaVar) throws IOException {
            try {
                ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
                receiveMessageResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                receiveMessageResult.setStatusCode(aaVar.b());
                receiveMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                receiveMessageResult.setMessage(new MessageDeserializer().deserialize(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return receiveMessageResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class SendMessageResponseParser implements ResponseParser<SendMessageResult> {
        public SendMessageResult parse(aa aaVar) throws IOException {
            try {
                SendMessageResult sendMessageResult = new SendMessageResult();
                sendMessageResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                sendMessageResult.setStatusCode(aaVar.b());
                sendMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                sendMessageResult.setMessageResponse(new MessageDeserializer().deserialize(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return sendMessageResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class SetQueueAttributesResponseParser implements ResponseParser<SetQueueAttributesResult> {
        public SetQueueAttributesResult parse(aa aaVar) throws IOException {
            try {
                SetQueueAttributesResult setQueueAttributesResult = new SetQueueAttributesResult();
                setQueueAttributesResult.setRequestId(aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID));
                setQueueAttributesResult.setStatusCode(aaVar.b());
                setQueueAttributesResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return setQueueAttributesResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static Map<String, String> parseResponseHeader(aa aaVar) {
        Map<String, String> hashMap = new HashMap();
        s f = aaVar.f();
        for (int i = 0; i < f.a(); i++) {
            hashMap.put(f.a(i), f.b(i));
        }
        return hashMap;
    }

    public static void safeCloseResponse(aa aaVar) {
        try {
            aaVar.g().close();
        } catch (Exception e) {
        }
    }

    public static ServiceException parseResponseErrorXML(aa aaVar) throws IOException {
        try {
            ServiceException deserialize = new ErrorMessageListDeserializer().deserialize(aaVar);
            safeCloseResponse(aaVar);
            return deserialize;
        } catch (Throwable e) {
            throw new IOException(e.getMessage(), e);
        } catch (Throwable th) {
            safeCloseResponse(aaVar);
        }
    }
}
