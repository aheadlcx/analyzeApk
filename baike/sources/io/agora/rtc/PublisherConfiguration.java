package io.agora.rtc;

import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import io.agora.rtc.internal.Logging;
import org.json.JSONException;
import org.json.JSONObject;

public class PublisherConfiguration {
    private JSONObject jsonObject;

    public static class Builder {
        private PublisherParameters params = new PublisherParameters();

        public Builder owner(boolean z) {
            this.params.owner = z;
            return this;
        }

        public Builder streamLifeCycle(int i) {
            this.params.lifecycle = i;
            return this;
        }

        public Builder size(int i, int i2) {
            this.params.width = i;
            this.params.height = i2;
            return this;
        }

        public Builder frameRate(int i) {
            this.params.framerate = i;
            return this;
        }

        public Builder biteRate(int i) {
            this.params.bitrate = i;
            return this;
        }

        public Builder defaultLayout(int i) {
            this.params.defaultLayout = i;
            return this;
        }

        public Builder publishUrl(String str) {
            this.params.publishUrl = str;
            return this;
        }

        public Builder rawStreamUrl(String str) {
            this.params.rawStreamUrl = str;
            return this;
        }

        public Builder extraInfo(String str) {
            this.params.extraInfo = str;
            return this;
        }

        public PublisherConfiguration build() {
            return new PublisherConfiguration();
        }
    }

    public boolean validate() {
        return this.jsonObject != null;
    }

    public String toJsonString() {
        if (validate()) {
            return this.jsonObject.toString();
        }
        return null;
    }

    private PublisherConfiguration(Builder builder) {
        try {
            this.jsonObject = new JSONObject().put("owner", builder.params.owner).put("lifecycle", builder.params.lifecycle).put("defaultLayout", builder.params.defaultLayout).put(IndexEntry.COLUMN_NAME_WIDTH, builder.params.width).put(IndexEntry.COLUMN_NAME_HEIGHT, builder.params.height).put("framerate", builder.params.framerate).put("bitrate", builder.params.bitrate).put("mosaicStream", builder.params.publishUrl).put("rawStream", builder.params.rawStreamUrl).put("extraInfo", builder.params.extraInfo);
        } catch (JSONException e) {
            this.jsonObject = null;
            Logging.e("failed to create PublisherConfiguration");
        }
    }
}
