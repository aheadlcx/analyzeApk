package cn.xiaochuankeji.tieba.ui.recommend;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.json.recommend.RecommendJson;
import cn.xiaochuankeji.tieba.json.recommend.RecommendJson.UgcDataWrapper;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecUgcDataBean;
import cn.xiaochuankeji.tieba.ui.recommend.holder.RecPostViewHolder;
import cn.xiaochuankeji.tieba.ui.recommend.holder.RecUgcViewHolder;
import cn.xiaochuankeji.tieba.ui.recommend.holder.a;
import java.util.ArrayList;
import java.util.List;

public class d {
    private static d a;

    private d() {
    }

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public List<c> a(RecommendJson recommendJson) {
        List<c> arrayList = new ArrayList();
        if (recommendJson.postList != null && recommendJson.postList.size() > 0) {
            arrayList.addAll(recommendJson.postList);
        }
        if (recommendJson.ugcList != null && recommendJson.ugcList.size() > 0) {
            for (UgcDataWrapper ugcDataWrapper : recommendJson.ugcList) {
                arrayList.add(ugcDataWrapper.position, ugcDataWrapper.ugcDataBean);
            }
        }
        return arrayList;
    }

    public a a(int i, LayoutInflater layoutInflater, ViewGroup viewGroup, a.a aVar, NavigatorTag navigatorTag) {
        a aVar2 = null;
        switch (i) {
            case 0:
                aVar2 = new RecPostViewHolder(layoutInflater.inflate(R.layout.recommend_post_layout, viewGroup, false), navigatorTag);
                break;
            case 1:
                aVar2 = new RecUgcViewHolder(layoutInflater.inflate(R.layout.view_item_moment, viewGroup, false));
                break;
        }
        if (aVar2 != null) {
            aVar2.a(aVar);
        }
        return aVar2;
    }

    public int a(c cVar) {
        if (!(cVar instanceof RecPostDataBean) && (cVar instanceof RecUgcDataBean)) {
            return 1;
        }
        return 0;
    }
}
