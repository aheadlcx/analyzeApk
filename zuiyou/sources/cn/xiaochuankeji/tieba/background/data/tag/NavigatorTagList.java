package cn.xiaochuankeji.tieba.background.data.tag;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.ArrayList;

public class NavigatorTagList implements Serializable {
    @JSONField(name = "list")
    public ArrayList<NavigatorTag> list;
}
