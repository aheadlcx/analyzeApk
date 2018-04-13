package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.ui.my.favorite.FavoriteBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class MyFavorListJson {
    @JSONField(name = "list")
    public List<FavoriteBean> favorList;
    @JSONField(name = "t")
    public long lastTime;
    @JSONField(name = "more")
    public int more;
}
