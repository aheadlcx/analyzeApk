package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class UgcVideoMusicCategoryJson implements Serializable {
    @JSONField(name = "title")
    public String categoryName;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "img")
    public ServerImgJson img;
    @JSONField(name = "music_num")
    public int musicNumber;
}
