package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;

public class UgcVideoPostReviewInfo {
    @JSONField(name = "grade")
    public boolean grade;
    @JSONField(name = "grade_id")
    public long gradeId;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public String offset;
    @JSONField(name = "post")
    public UgcVideoInfoBean post;
    @JSONField(name = "list")
    public ArrayList<UgcVideoInfoBean> reviewFirstPageist;
}
