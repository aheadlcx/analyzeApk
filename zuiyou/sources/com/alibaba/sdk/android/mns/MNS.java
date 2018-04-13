package com.alibaba.sdk.android.mns;

import com.alibaba.sdk.android.common.ClientException;
import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.callback.MNSCompletedCallback;
import com.alibaba.sdk.android.mns.internal.MNSAsyncTask;
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

public interface MNS {
    MNSAsyncTask<ChangeMessageVisibilityResult> asyncChangeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest, MNSCompletedCallback<ChangeMessageVisibilityRequest, ChangeMessageVisibilityResult> mNSCompletedCallback);

    MNSAsyncTask<CreateQueueResult> asyncCreateQueue(CreateQueueRequest createQueueRequest, MNSCompletedCallback<CreateQueueRequest, CreateQueueResult> mNSCompletedCallback);

    MNSAsyncTask<DeleteMessageResult> asyncDeleteMessage(DeleteMessageRequest deleteMessageRequest, MNSCompletedCallback<DeleteMessageRequest, DeleteMessageResult> mNSCompletedCallback);

    MNSAsyncTask<DeleteQueueResult> asyncDeleteQueue(DeleteQueueRequest deleteQueueRequest, MNSCompletedCallback<DeleteQueueRequest, DeleteQueueResult> mNSCompletedCallback);

    MNSAsyncTask<GetQueueAttributesResult> asyncGetQueueAttributes(GetQueueAttributesRequest getQueueAttributesRequest, MNSCompletedCallback<GetQueueAttributesRequest, GetQueueAttributesResult> mNSCompletedCallback);

    MNSAsyncTask<ListQueueResult> asyncListQueue(ListQueueRequest listQueueRequest, MNSCompletedCallback<ListQueueRequest, ListQueueResult> mNSCompletedCallback);

    MNSAsyncTask<PeekMessageResult> asyncPeekMessage(PeekMessageRequest peekMessageRequest, MNSCompletedCallback<PeekMessageRequest, PeekMessageResult> mNSCompletedCallback);

    MNSAsyncTask<ReceiveMessageResult> asyncReceiveMessage(ReceiveMessageRequest receiveMessageRequest, MNSCompletedCallback<ReceiveMessageRequest, ReceiveMessageResult> mNSCompletedCallback);

    MNSAsyncTask<SendMessageResult> asyncSendMessage(SendMessageRequest sendMessageRequest, MNSCompletedCallback<SendMessageRequest, SendMessageResult> mNSCompletedCallback);

    MNSAsyncTask<SetQueueAttributesResult> asyncSetQueueAttributes(SetQueueAttributesRequest setQueueAttributesRequest, MNSCompletedCallback<SetQueueAttributesRequest, SetQueueAttributesResult> mNSCompletedCallback);

    ChangeMessageVisibilityResult changeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest) throws ClientException, ServiceException;

    CreateQueueResult createQueue(CreateQueueRequest createQueueRequest) throws ClientException, ServiceException;

    DeleteMessageResult deleteMessage(DeleteMessageRequest deleteMessageRequest) throws ClientException, ServiceException;

    DeleteQueueResult deleteQueue(DeleteQueueRequest deleteQueueRequest) throws ClientException, ServiceException;

    GetQueueAttributesResult getQueueAttributes(GetQueueAttributesRequest getQueueAttributesRequest) throws ClientException, ServiceException;

    ListQueueResult listQueue(ListQueueRequest listQueueRequest) throws ClientException, ServiceException;

    PeekMessageResult peekMessage(PeekMessageRequest peekMessageRequest) throws ClientException, ServiceException;

    ReceiveMessageResult receiveMessage(ReceiveMessageRequest receiveMessageRequest) throws ClientException, ServiceException;

    SendMessageResult sendMessage(SendMessageRequest sendMessageRequest) throws ClientException, ServiceException;

    SetQueueAttributesResult setQueueAttributes(SetQueueAttributesRequest setQueueAttributesRequest) throws ClientException, ServiceException;
}
