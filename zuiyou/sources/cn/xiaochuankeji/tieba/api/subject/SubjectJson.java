package cn.xiaochuankeji.tieba.api.subject;

import cn.xiaochuankeji.tieba.background.data.post.Subject;
import com.alibaba.fastjson.annotation.JSONField;

public class SubjectJson {
    @JSONField(name = "subject")
    public Subject subject;

    public String toString() {
        return "PostSubjectJson{subject=" + this.subject + '}';
    }
}
