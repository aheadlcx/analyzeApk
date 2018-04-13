package cn.xiaochuankeji.tieba.push.a;

import android.support.v7.util.DiffUtil.DiffResult;
import java.util.List;

public class a<T> {
    public DiffResult a;
    public List<T> b;

    public a(DiffResult diffResult, List<T> list) {
        this.a = diffResult;
        this.b = list;
    }
}
