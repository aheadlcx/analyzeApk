package cn.xiaochuankeji.tieba.ui.ugcvideodetail.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoFollowListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoPostReviewInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import rx.a.b.a;
import rx.d;
import rx.j;

public class b extends a {
    public static final Creator<b> CREATOR = new Creator<b>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public b[] a(int i) {
            return new b[i];
        }

        public b a(Parcel parcel) {
            return new b(parcel);
        }
    };
    private UgcVideoInfoBean d;
    private Moment e;

    public b(UgcVideoInfoBean ugcVideoInfoBean, Moment moment) {
        this.d = ugcVideoInfoBean;
        this.e = moment;
    }

    public b(Parcel parcel) {
        this.d = (UgcVideoInfoBean) parcel.readSerializable();
        Serializable readSerializable = parcel.readSerializable();
        if (readSerializable != null) {
            this.e = (Moment) readSerializable;
        }
    }

    public void b() {
        this.c.a = this.d;
        if (this.b != null) {
            this.b.a(this.d, this.e);
        }
        a(false);
    }

    protected void a(final boolean z) {
        d b;
        List list = null;
        if (this.e != null) {
            ArrayList arrayList = new ArrayList();
            for (UgcVideoInfoBean ugcVideoInfoBean : this.e.ugcVideos) {
                arrayList.add(Long.valueOf(ugcVideoInfoBean.id));
            }
            list = arrayList;
        }
        if (z) {
            b = this.a.b(this.d.id, list);
        } else {
            b = this.a.a(this.d.id, list);
        }
        b.a(a.a()).b(new j<UgcVideoPostReviewInfo>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoPostReviewInfo) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.b(th.getMessage());
            }

            public void a(UgcVideoPostReviewInfo ugcVideoPostReviewInfo) {
                if (ugcVideoPostReviewInfo.grade) {
                    this.b.a(true);
                    return;
                }
                if (ugcVideoPostReviewInfo.post != null) {
                    this.b.c.b = ugcVideoPostReviewInfo.post;
                }
                if (ugcVideoPostReviewInfo.reviewFirstPageist != null && ugcVideoPostReviewInfo.reviewFirstPageist.size() > 0) {
                    this.b.c.c.addAll(ugcVideoPostReviewInfo.reviewFirstPageist);
                }
                this.b.c.e = ugcVideoPostReviewInfo.offset;
                this.b.c.d = ugcVideoPostReviewInfo.more;
                if (z) {
                    if (this.b.e == null) {
                        this.b.c.g = 1;
                    } else {
                        for (int i = 0; i < this.b.c.c.size(); i++) {
                            if (((UgcVideoInfoBean) this.b.c.c.get(i)).id == this.b.d.id) {
                                this.b.c.g = i + 1;
                                break;
                            }
                        }
                    }
                }
                if (this.b.b != null) {
                    this.b.b.a();
                }
            }
        });
    }

    public void c() {
        this.a.a(this.c.b.id, this.c.e).a(a.a()).b(new j<UgcVideoFollowListJson>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoFollowListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.b(th.getMessage());
            }

            public void a(UgcVideoFollowListJson ugcVideoFollowListJson) {
                this.a.a(ugcVideoFollowListJson.ugcVideoList);
                this.a.c.e = ugcVideoFollowListJson.offset;
                this.a.c.d = ugcVideoFollowListJson.more;
                if (this.a.b != null) {
                    this.a.b.c();
                }
            }
        });
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.d);
        if (this.e != null) {
            parcel.writeSerializable(this.e);
        }
    }
}
