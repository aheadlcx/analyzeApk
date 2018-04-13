package cn.xiaochuankeji.tieba.background.beans;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class TopicCategory implements Serializable {
    private static final long serialVersionUID = -7118016009633958134L;
    @JSONField(name = "cid")
    private int categoryId;
    @JSONField(name = "cover")
    private long iconId;
    private String name;

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int i) {
        this.categoryId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getIconId() {
        return this.iconId;
    }

    public void setIconId(long j) {
        this.iconId = j;
    }
}
