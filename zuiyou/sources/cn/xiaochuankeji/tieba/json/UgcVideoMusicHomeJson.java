package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;

public class UgcVideoMusicHomeJson {
    @JSONField(name = "cates")
    public ArrayList<UgcVideoMusicCategoryJson> categoryList;
    @JSONField(name = "cateid")
    public long currentCid;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "list")
    public ArrayList<UgcVideoMusicJson> musicList;
    @JSONField(name = "offset")
    public long offset;
}
