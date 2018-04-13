package com.meizu.cloud.pushsdk.handler.impl.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.meizu.cloud.a.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadLogMessage implements Parcelable {
    public static final Creator<UploadLogMessage> CREATOR = new Creator<UploadLogMessage>() {
        public UploadLogMessage createFromParcel(Parcel parcel) {
            return new UploadLogMessage(parcel);
        }

        public UploadLogMessage[] newArray(int i) {
            return new UploadLogMessage[i];
        }
    };
    public static final String MAX_SIZE = "max_size";
    private static final String TAG = "UploadLogMessage";
    public static final String UPLOAD_FILES = "upload_files";
    public static final String WIFI_UPLOAD = "wifi_upload";
    private ControlMessage controlMessage;
    private List<String> fileList;
    private int maxSize;
    private String uploadMessage;
    private boolean wifiUpload;

    public UploadLogMessage(String str, String str2, String str3, String str4) {
        this.uploadMessage = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(MAX_SIZE)) {
                this.maxSize = jSONObject.getInt(MAX_SIZE);
            }
            if (!jSONObject.isNull(WIFI_UPLOAD)) {
                this.wifiUpload = jSONObject.getBoolean(WIFI_UPLOAD);
            }
            if (!jSONObject.isNull(UPLOAD_FILES)) {
                JSONArray jSONArray = jSONObject.getJSONArray(UPLOAD_FILES);
                this.fileList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.fileList.add(jSONArray.getString(i));
                }
            }
        } catch (JSONException e) {
            a.d(TAG, "parse upload message error " + e.getMessage());
        }
        this.controlMessage = new ControlMessage(str2, str3, str4);
    }

    protected UploadLogMessage(Parcel parcel) {
        this.maxSize = parcel.readInt();
        this.wifiUpload = parcel.readByte() != (byte) 0;
        this.fileList = parcel.createStringArrayList();
        this.controlMessage = (ControlMessage) parcel.readParcelable(ControlMessage.class.getClassLoader());
        this.uploadMessage = parcel.readString();
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(int i) {
        this.maxSize = i;
    }

    public boolean isWifiUpload() {
        return this.wifiUpload;
    }

    public void setWifiUpload(boolean z) {
        this.wifiUpload = z;
    }

    public List<String> getFileList() {
        return this.fileList;
    }

    public void setFileList(List<String> list) {
        this.fileList = list;
    }

    public ControlMessage getControlMessage() {
        return this.controlMessage;
    }

    public void setControlMessage(ControlMessage controlMessage) {
        this.controlMessage = controlMessage;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.maxSize);
        parcel.writeByte((byte) (this.wifiUpload ? 1 : 0));
        parcel.writeStringList(this.fileList);
        parcel.writeParcelable(this.controlMessage, i);
        parcel.writeString(this.uploadMessage);
    }

    public String toString() {
        return "UploadLogMessage{maxSize=" + this.maxSize + ", wifiUpload=" + this.wifiUpload + ", fileList=" + this.fileList + ", controlMessage=" + this.controlMessage + ", uploadMessage='" + this.uploadMessage + '\'' + '}';
    }
}
