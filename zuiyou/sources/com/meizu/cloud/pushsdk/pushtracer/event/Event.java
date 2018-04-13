package com.meizu.cloud.pushsdk.pushtracer.event;

import com.meizu.cloud.pushsdk.pushtracer.dataload.SelfDescribingJson;
import com.meizu.cloud.pushsdk.pushtracer.dataload.TrackerDataload;
import com.meizu.cloud.pushsdk.pushtracer.utils.Preconditions;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Event {
    protected final String eventId;
    protected final List<SelfDescribingJson> selfDescribingJsonList;
    protected final long timestamp;

    public static abstract class Builder<T extends Builder<T>> {
        private String eventId = Util.getEventId();
        private List<SelfDescribingJson> selfDescribingJsonList = new LinkedList();
        private long timestamp = System.currentTimeMillis();

        protected abstract T self();

        public T customContext(List<SelfDescribingJson> list) {
            this.selfDescribingJsonList = list;
            return self();
        }

        public T timestamp(long j) {
            this.timestamp = j;
            return self();
        }

        public T eventId(String str) {
            this.eventId = str;
            return self();
        }

        public Event build() {
            return new Event(this);
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

    protected Event(Builder<?> builder) {
        Preconditions.checkNotNull(builder.selfDescribingJsonList);
        Preconditions.checkNotNull(builder.eventId);
        Preconditions.checkArgument(!builder.eventId.isEmpty(), "eventId cannot be empty");
        this.selfDescribingJsonList = builder.selfDescribingJsonList;
        this.timestamp = builder.timestamp;
        this.eventId = builder.eventId;
    }

    public List<SelfDescribingJson> getSelfDescribingJson() {
        return new ArrayList(this.selfDescribingJsonList);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getEventId() {
        return this.eventId;
    }

    protected TrackerDataload putDefaultParams(TrackerDataload trackerDataload) {
        trackerDataload.add("ei", getEventId());
        trackerDataload.add("ts", Long.toString(getTimestamp()));
        return trackerDataload;
    }
}
