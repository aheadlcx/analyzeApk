package com.sprite.ads.third;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.NativeAdData;
import java.io.Serializable;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;

public class ThirdApiAdData extends NativeAdData implements Serializable {
    public ActionType action_type;
    public List<String> clickThroughUrl;
    public List<String> clickTrackingUrl;
    public String desc;
    public List<String> downloadCompleteUrl;
    public List<String> downloadInstalledUrl;
    public List<String> downloadStartUrl;
    public String gdt_act_type;
    public String gdt_crt_type;
    public String gdt_targetid;
    public int height;
    public String movie;
    public String pic;
    public List<String> reportTrackingUrl;
    public ResType res_type;
    public double screenRatio;
    public String title;
    public String url;
    public List<String> videoCompleteUrl;
    public List<String> videoMidPointUrl;
    public List<String> videoStartUrl;
    public int width;

    public enum ActionType {
        WEB,
        DOWNLOAD
    }

    public enum ResType {
        IMAGE,
        VIDEO
    }

    public int getActionType() {
        return this.action_type == ActionType.DOWNLOAD ? 2 : 1;
    }

    public AdItem getAdItem(final String str, final String str2) {
        return new ThirdReportItem() {
            public String getClickid() {
                return str;
            }

            public DataSourceType getDataSourceType() {
                return DataSourceType.API_GDT;
            }

            public String getDesc() {
                return ThirdApiAdData.this.desc;
            }

            public String getDstlink() {
                return str2;
            }

            public String getGdt_targetid() {
                return ThirdApiAdData.this.gdt_targetid;
            }

            public boolean getIsNewApiVersion() {
                return false;
            }

            public String getMovie() {
                return ThirdApiAdData.this.movie;
            }

            public String getPic() {
                return ThirdApiAdData.this.pic;
            }

            public String getResType() {
                return CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
            }

            public String getTitle() {
                return ThirdApiAdData.this.title;
            }

            public String getUrl() {
                return str2;
            }

            public void jsonToObject(JSONObject jSONObject) {
            }
        };
    }

    public List<String> getClickThroughUrl() {
        return this.clickThroughUrl;
    }

    public List<String> getClickTrackingUrl() {
        return this.clickTrackingUrl;
    }

    public String getDesc() {
        return this.desc == null ? "" : this.desc;
    }

    public String getGdt_act_type() {
        return this.gdt_act_type;
    }

    public String getMovie() {
        return this.movie;
    }

    public AdItem getNewAdItem() {
        return new ThirdReportItem() {
            public String getClickid() {
                return null;
            }

            public DataSourceType getDataSourceType() {
                return DataSourceType.API_GDT;
            }

            public String getDesc() {
                return ThirdApiAdData.this.desc;
            }

            public List<String> getDownloadCompleteReport() {
                return ThirdApiAdData.this.downloadCompleteUrl;
            }

            public List<String> getDownloadInstalledReport() {
                return ThirdApiAdData.this.downloadInstalledUrl;
            }

            public List<String> getDownloadStartReport() {
                return ThirdApiAdData.this.downloadStartUrl;
            }

            public String getDstlink() {
                return null;
            }

            public String getGdt_targetid() {
                return ThirdApiAdData.this.gdt_targetid;
            }

            public boolean getIsNewApiVersion() {
                return true;
            }

            public String getMovie() {
                return ThirdApiAdData.this.movie;
            }

            public String getPic() {
                return ThirdApiAdData.this.pic;
            }

            public String getResType() {
                return CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
            }

            public String getTitle() {
                return ThirdApiAdData.this.title;
            }

            public String getUrl() {
                return (String) ThirdApiAdData.this.clickThroughUrl.get(0);
            }

            public void jsonToObject(JSONObject jSONObject) {
            }
        };
    }

    public String getPic() {
        return this.pic;
    }

    public List<String> getReportTrackingUrl() {
        return this.reportTrackingUrl;
    }

    public String getResType() {
        return CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
    }

    public String getTitle() {
        return this.title == null ? "" : this.title;
    }

    public String getUrl() {
        return this.url;
    }
}
