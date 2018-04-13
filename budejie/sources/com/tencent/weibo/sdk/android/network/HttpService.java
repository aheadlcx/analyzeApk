package com.tencent.weibo.sdk.android.network;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HttpService {
    private static HttpService instance = null;
    private final int TAG_RUNNING;
    private final int TAG_WAITTING;
    private List<HttpReq> mRunningReqList;
    private int mThreadNum;
    private List<HttpReq> mWaitReqList;

    public static HttpService getInstance() {
        if (instance == null) {
            instance = new HttpService();
        }
        return instance;
    }

    private HttpService() {
        this.TAG_RUNNING = 1;
        this.TAG_WAITTING = 0;
        this.mWaitReqList = null;
        this.mRunningReqList = null;
        this.mThreadNum = 4;
        this.mWaitReqList = new LinkedList();
        this.mRunningReqList = new LinkedList();
    }

    public void addImmediateReq(HttpReq httpReq) {
        httpReq.setServiceTag(1);
        this.mRunningReqList.add(httpReq);
        httpReq.execute(new Void[0]);
    }

    public void addNormalReq(HttpReq httpReq) {
        if (this.mRunningReqList.size() < this.mThreadNum) {
            httpReq.setServiceTag(1);
            this.mRunningReqList.add(httpReq);
            httpReq.execute(new Void[0]);
            return;
        }
        httpReq.setServiceTag(0);
        this.mWaitReqList.add(httpReq);
    }

    public void cancelReq(HttpReq httpReq) {
        if (httpReq.getServiceTag() == 1) {
            for (HttpReq httpReq2 : this.mRunningReqList) {
                if (httpReq2 == httpReq) {
                    httpReq.cancel(true);
                    this.mRunningReqList.remove(httpReq);
                }
            }
        } else if (httpReq.getServiceTag() == 0) {
            for (HttpReq httpReq3 : this.mWaitReqList) {
                if (httpReq == httpReq3) {
                    this.mWaitReqList.remove(httpReq);
                }
            }
        }
    }

    public void cancelAllReq() {
        for (HttpReq cancel : this.mRunningReqList) {
            cancel.cancel(true);
        }
        this.mWaitReqList.clear();
    }

    public void SetAsynchronousTaskNum(int i) {
    }

    public void onReqFinish(HttpReq httpReq) {
        Iterator it = this.mRunningReqList.iterator();
        while (it.hasNext()) {
            if (httpReq == ((HttpReq) it.next())) {
                it.remove();
                break;
            }
        }
        if (this.mWaitReqList.size() > 0 && this.mRunningReqList.size() < this.mThreadNum) {
            it = this.mWaitReqList.iterator();
            HttpReq httpReq2 = (HttpReq) it.next();
            it.remove();
            httpReq2.setServiceTag(1);
            this.mRunningReqList.add(httpReq2);
            httpReq2.execute(new Void[0]);
        }
    }
}
