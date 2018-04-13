package cn.xiaochuankeji.tieba.background.ad;

import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.List;

public class NewAdvertismentSoftBean extends PostDataBean {
    @JSONField(name = "advert")
    public cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Advert advert;

    public static class Advert implements Serializable {
        @JSONField(name = "adid")
        public long adid;
        @JSONField(name = "ext")
        public cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Ext ext;
        @JSONField(name = "filter_words")
        public List<cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Filter> filters;
        @JSONField(name = "label")
        public String label;
    }

    public static class Ext implements Serializable {
        @JSONField(name = "lp_open_url")
        public String lpOpenUrl;
        @JSONField(name = "small_image")
        public String smallImage;
        @JSONField(name = "title")
        public String title;
    }

    public static class Filter implements Serializable {
        @JSONField(name = "id")
        public int id;
        @JSONField(name = "is_selected")
        public boolean isDefaultSelect;
        @JSONField(name = "name")
        public String name;
    }
}
