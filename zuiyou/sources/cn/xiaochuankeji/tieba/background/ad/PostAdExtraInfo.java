package cn.xiaochuankeji.tieba.background.ad;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.LinkedHashMap;
import java.util.List;

public class PostAdExtraInfo {
    @JSONField(name = "audit_type")
    public int auditType;
    @JSONField(name = "next_display_position")
    public int displayPosition;
    @JSONField(name = "filter_words")
    public List<FilterWord> filterWords;
    @JSONField(name = "label")
    public String[] label;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "source")
    public Source source;

    public static class FilterWord {
        @JSONField(name = "is_selected")
        public boolean defaultSelect;
        @JSONField(name = "id")
        public long id;
        @JSONField(name = "name")
        public String name;
    }

    public static class Source {
        @JSONField(name = "appid")
        public String appId;
        @JSONField(name = "adslot")
        public String slotId;
        @JSONField(name = "type")
        public String type;
    }

    public LinkedHashMap<String, String> createTediumReason() {
        if (this.filterWords == null || this.filterWords.size() <= 0) {
            return null;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < this.filterWords.size(); i++) {
            linkedHashMap.put(((FilterWord) this.filterWords.get(i)).id + "", ((FilterWord) this.filterWords.get(i)).name);
        }
        return linkedHashMap;
    }
}
