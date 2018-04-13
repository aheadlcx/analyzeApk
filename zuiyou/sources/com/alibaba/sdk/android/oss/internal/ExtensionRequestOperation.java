package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExtensionRequestOperation {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);
    private InternalRequestOperation apiOperation;

    class ResumableUploadTask implements Callable<ResumableUploadResult> {
        private OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> completedCallback;
        private ExecutionContext context;
        private long currentUploadLength;
        private long fileLength;
        private List<PartETag> partETags = new ArrayList();
        private File recordFile;
        private ResumableUploadRequest request;
        private String uploadId;

        public ResumableUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext) {
            this.request = resumableUploadRequest;
            this.completedCallback = oSSCompletedCallback;
            this.context = executionContext;
        }

        public ResumableUploadResult call() throws Exception {
            try {
                initUploadId();
                OSSResult doMultipartUpload = doMultipartUpload();
                if (this.completedCallback != null) {
                    this.completedCallback.onSuccess(this.request, doMultipartUpload);
                }
                return doMultipartUpload;
            } catch (ServiceException e) {
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, null, e);
                }
                throw e;
            } catch (ClientException e2) {
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, e2, null);
                }
                throw e2;
            } catch (Throwable e3) {
                ClientException clientException = new ClientException(e3.toString(), e3);
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, clientException, null);
                }
                throw clientException;
            }
        }

        private void initUploadId() throws IOException, ServiceException, ClientException {
            String uploadFilePath = this.request.getUploadFilePath();
            if (this.request.getRecordDirectory() != null) {
                this.recordFile = new File(this.request.getRecordDirectory() + "/" + BinaryUtil.calculateMd5Str((BinaryUtil.calculateMd5Str(uploadFilePath) + this.request.getBucketName() + this.request.getObjectKey() + String.valueOf(this.request.getPartSize())).getBytes()));
                if (this.recordFile.exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(this.recordFile));
                    this.uploadId = bufferedReader.readLine();
                    bufferedReader.close();
                    OSSLog.logD("[initUploadId] - Found record file, uploadid: " + this.uploadId);
                    try {
                        for (PartSummary partSummary : ((ListPartsResult) ExtensionRequestOperation.this.apiOperation.listParts(new ListPartsRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId), null).getResult()).getParts()) {
                            this.partETags.add(new PartETag(partSummary.getPartNumber(), partSummary.getETag()));
                        }
                        return;
                    } catch (ServiceException e) {
                        if (e.getStatusCode() == 404) {
                            this.uploadId = null;
                        } else {
                            throw e;
                        }
                    } catch (ClientException e2) {
                        throw e2;
                    }
                }
                if (!(this.recordFile.exists() || this.recordFile.createNewFile())) {
                    throw new ClientException("Can't create file at path: " + this.recordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
                }
            }
            this.uploadId = ((InitiateMultipartUploadResult) ExtensionRequestOperation.this.apiOperation.initMultipartUpload(new InitiateMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.request.getMetadata()), null).getResult()).getUploadId();
            if (this.recordFile != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.recordFile));
                bufferedWriter.write(this.uploadId);
                bufferedWriter.close();
            }
        }

        private ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException {
            long j = 0;
            if (this.context.getCancellationHandler().isCancelled()) {
                if (this.request.deleteUploadOnCancelling().booleanValue()) {
                    abortThisResumableUpload();
                    if (this.recordFile != null) {
                        this.recordFile.delete();
                    }
                }
                throwOutInterruptClientException();
            }
            long partSize = this.request.getPartSize();
            int size = this.partETags.size() + 1;
            File file = new File(this.request.getUploadFilePath());
            this.fileLength = file.length();
            final OSSProgressCallback progressCallback = this.request.getProgressCallback();
            int i = (this.fileLength % partSize == 0 ? 0 : 1) + ((int) (this.fileLength / partSize));
            if (size <= i) {
                this.currentUploadLength = ((long) (size - 1)) * partSize;
            } else {
                this.currentUploadLength = this.fileLength;
            }
            InputStream fileInputStream = new FileInputStream(file);
            while (j < this.currentUploadLength) {
                long skip = fileInputStream.skip(this.currentUploadLength - j);
                if (skip == -1) {
                    throw new IOException("Skip failed! [fileLength]: " + this.fileLength + " [needSkip]: " + this.currentUploadLength);
                }
                j += skip;
            }
            int i2 = size;
            while (i2 <= i) {
                UploadPartRequest uploadPartRequest = new UploadPartRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId, i2);
                uploadPartRequest.setProgressCallback(new OSSProgressCallback<UploadPartRequest>() {
                    public void onProgress(UploadPartRequest uploadPartRequest, long j, long j2) {
                        if (progressCallback != null) {
                            progressCallback.onProgress(ResumableUploadTask.this.request, ResumableUploadTask.this.currentUploadLength + j, ResumableUploadTask.this.fileLength);
                        }
                    }
                });
                size = (int) Math.min(partSize, this.fileLength - this.currentUploadLength);
                byte[] readStreamAsBytesArray = IOUtils.readStreamAsBytesArray(fileInputStream, size);
                uploadPartRequest.setPartContent(readStreamAsBytesArray);
                uploadPartRequest.setMd5Digest(BinaryUtil.calculateBase64Md5(readStreamAsBytesArray));
                this.partETags.add(new PartETag(i2, ((UploadPartResult) ExtensionRequestOperation.this.apiOperation.uploadPart(uploadPartRequest, null).getResult()).getETag()));
                this.currentUploadLength += (long) size;
                int i3 = i2 + 1;
                if (this.context.getCancellationHandler().isCancelled()) {
                    if (this.request.deleteUploadOnCancelling().booleanValue()) {
                        abortThisResumableUpload();
                        if (this.recordFile != null) {
                            this.recordFile.delete();
                        }
                    }
                    throwOutInterruptClientException();
                }
                i2 = i3;
            }
            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId, this.partETags);
            completeMultipartUploadRequest.setMetadata(this.request.getMetadata());
            if (this.request.getCallbackParam() != null) {
                completeMultipartUploadRequest.setCallbackParam(this.request.getCallbackParam());
            }
            if (this.request.getCallbackVars() != null) {
                completeMultipartUploadRequest.setCallbackVars(this.request.getCallbackVars());
            }
            CompleteMultipartUploadResult completeMultipartUploadResult = (CompleteMultipartUploadResult) ExtensionRequestOperation.this.apiOperation.completeMultipartUpload(completeMultipartUploadRequest, null).getResult();
            if (this.recordFile != null) {
                this.recordFile.delete();
            }
            return new ResumableUploadResult(completeMultipartUploadResult);
        }

        private void abortThisResumableUpload() {
            if (this.uploadId != null) {
                ExtensionRequestOperation.this.apiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId), null).waitUntilFinished();
            }
        }

        private void throwOutInterruptClientException() throws ClientException {
            Throwable iOException = new IOException();
            throw new ClientException(iOException.getMessage(), iOException);
        }
    }

    public ExtensionRequestOperation(InternalRequestOperation internalRequestOperation) {
        this.apiOperation = internalRequestOperation;
    }

    public boolean doesObjectExist(String str, String str2) throws ClientException, ServiceException {
        try {
            this.apiOperation.headObject(new HeadObjectRequest(str, str2), null).getResult();
            return true;
        } catch (ServiceException e) {
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    public void abortResumableUpload(ResumableUploadRequest resumableUploadRequest) throws IOException {
        String uploadFilePath = resumableUploadRequest.getUploadFilePath();
        if (resumableUploadRequest.getRecordDirectory() != null) {
            File file = new File(resumableUploadRequest.getRecordDirectory() + "/" + BinaryUtil.calculateMd5Str((BinaryUtil.calculateMd5Str(uploadFilePath) + resumableUploadRequest.getBucketName() + resumableUploadRequest.getObjectKey() + String.valueOf(resumableUploadRequest.getPartSize())).getBytes()));
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                OSSLog.logD("[initUploadId] - Found record file, uploadid: " + readLine);
                this.apiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(resumableUploadRequest.getBucketName(), resumableUploadRequest.getObjectKey(), readLine), null);
            }
            if (file != null) {
                file.delete();
            }
        }
    }

    public OSSAsyncTask<ResumableUploadResult> resumableUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        ExecutionContext executionContext = new ExecutionContext(this.apiOperation.getInnerClient(), resumableUploadRequest);
        return OSSAsyncTask.wrapRequestTask(executor.submit(new ResumableUploadTask(resumableUploadRequest, oSSCompletedCallback, executionContext)), executionContext);
    }
}
