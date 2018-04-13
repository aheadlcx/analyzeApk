package com.meizu.cloud.pushsdk.pushtracer.event;

import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.dataload.TrackerDataload;

public class PushEvent extends Event {
    private String deviceId;
    private String eventCreateTime;
    private String eventName;
    private String messageSeq;
    private String packageName;
    private String pushsdkVersion;
    private String seqId;
    private String taskId;

    public static abstract class Builder<T extends Builder<T>> extends com.meizu.cloud.pushsdk.pushtracer.event.Event.Builder<T> {
        private String deviceId;
        private String eventCreateTime;
        private String eventName;
        private String messageSeq;
        private String packageName;
        private String pushsdkVersion;
        private String seqId;
        private String taskId;

        public T eventName(String str) {
            this.eventName = str;
            return (Builder) self();
        }

        public T taskId(String str) {
            this.taskId = str;
            return (Builder) self();
        }

        public T deviceId(String str) {
            this.deviceId = str;
            return (Builder) self();
        }

        public T pushsdkVersion(String str) {
            this.pushsdkVersion = str;
            return (Builder) self();
        }

        public T packageName(String str) {
            this.packageName = str;
            return (Builder) self();
        }

        public T seqId(String str) {
            this.seqId = str;
            return (Builder) self();
        }

        public T messageSeq(String str) {
            this.messageSeq = str;
            return (Builder) self();
        }

        public T eventCreateTime(String str) {
            this.eventCreateTime = str;
            return (Builder) self();
        }

        public PushEvent build() {
            return new PushEvent(this);
        }
    }

    private static class Builder2 extends Builder<Builder2> {
        private Builder2() {
        }

        protected Builder2 self() {
            return this;
        }
    }

    public static Builder<?> builder() {
        return new Builder2();
    }

    protected PushEvent(Builder<?> builder) {
        super(builder);
        this.taskId = builder.taskId;
        this.deviceId = builder.deviceId;
        this.eventName = builder.eventName;
        this.pushsdkVersion = builder.pushsdkVersion;
        this.packageName = builder.packageName;
        this.seqId = builder.seqId;
        this.messageSeq = builder.messageSeq;
        this.eventCreateTime = builder.eventCreateTime;
    }

    public TrackerDataload getDataLoad() {
        TrackerDataload trackerDataload = new TrackerDataload();
        trackerDataload.add(Parameters.EVENT_NAME, this.eventName);
        trackerDataload.add(Parameters.TASK_ID, this.taskId);
        trackerDataload.add("di", this.deviceId);
        trackerDataload.add(Parameters.PUSH_SDK_VERSION, this.pushsdkVersion);
        trackerDataload.add(Parameters.PACKAGE_NAME, this.packageName);
        trackerDataload.add(Parameters.SEQ_ID, this.seqId);
        trackerDataload.add(Parameters.MESSAGE_SEQ, this.messageSeq);
        trackerDataload.add(Parameters.EVENT_CREATE_TIME, this.eventCreateTime);
        return putDefaultParams(trackerDataload);
    }
}
