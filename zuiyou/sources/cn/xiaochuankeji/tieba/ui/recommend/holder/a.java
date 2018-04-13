package cn.xiaochuankeji.tieba.ui.recommend.holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.recommend.c;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import com.iflytek.cloud.SpeechConstant;
import java.util.HashMap;
import rx.b.b;

public abstract class a extends ViewHolder {
    protected a a;
    protected HashMap<Long, Boolean> b;
    protected HashMap<Long, f> c;

    public interface a {
        void a(long j);
    }

    public abstract void a(a aVar, c cVar);

    public a(View view) {
        super(view);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(HashMap<Long, Boolean> hashMap) {
        this.b = hashMap;
    }

    public void b(HashMap<Long, f> hashMap) {
        this.c = hashMap;
    }

    public EntranceType a(NavigatorTag navigatorTag) {
        if (navigatorTag == null) {
            return EntranceType.Post_RecommendIndex;
        }
        String str = navigatorTag.action_info.filter;
        if (SpeechConstant.PLUS_LOCAL_ALL.equalsIgnoreCase(str)) {
            return EntranceType.Post_RecommendIndex;
        }
        if ("video".equalsIgnoreCase(str)) {
            return EntranceType.Post_RecommendVideo;
        }
        if ("imgtxt".equalsIgnoreCase(str)) {
            return EntranceType.Post_RecommendImgTxt;
        }
        return EntranceType.Post_RecommendIndex;
    }

    public EntranceType b(NavigatorTag navigatorTag) {
        if (navigatorTag == null) {
            return EntranceType.Review_RecommendIndex;
        }
        String str = navigatorTag.action_info.filter;
        if (SpeechConstant.PLUS_LOCAL_ALL.equalsIgnoreCase(str)) {
            return EntranceType.Review_RecommendIndex;
        }
        if ("video".equalsIgnoreCase(str)) {
            return EntranceType.Review_RecommendVideo;
        }
        if ("imgtxt".equalsIgnoreCase(str)) {
            return EntranceType.Review_RecommendImgTxt;
        }
        return EntranceType.Review_RecommendIndex;
    }

    protected void a(final View view, final b<Void> bVar) {
        final GestureDetector gestureDetector = new GestureDetector(view.getContext(), new SimpleOnGestureListener(this) {
            final /* synthetic */ a c;

            public void onLongPress(MotionEvent motionEvent) {
                if (bVar != null) {
                    bVar.call(null);
                }
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (view != null) {
                    view.performClick();
                }
                return true;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }
        });
        view.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ a b;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }
}
