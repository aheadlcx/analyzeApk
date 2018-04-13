package cn.xiaochuankeji.tieba.ui.ugcvideodetail.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoFollowListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoPostReviewInfo;
import rx.a.b.a;
import rx.d;
import rx.j;

public class e extends a {
    public static final Creator<e> CREATOR = new Creator<e>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public e[] a(int i) {
            return new e[i];
        }

        public e a(Parcel parcel) {
            return new e(parcel);
        }
    };
    private long d;
    private long e;
    private int f;

    public e(long j) {
        this.d = j;
    }

    public e(long j, long j2, int i) {
        this.d = j;
        this.e = j2;
        this.f = i;
    }

    public e(Parcel parcel) {
        this.d = parcel.readLong();
        this.f = parcel.readInt();
        this.e = parcel.readLong();
    }

    public void b() {
        a(false);
    }

    protected void a(final boolean z) {
        d b;
        if (z) {
            b = this.a.b(this.d);
        } else {
            b = this.a.a(this.d);
        }
        b.a(a.a()).b(new j<UgcVideoPostReviewInfo>(this) {
            final /* synthetic */ e b;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoPostReviewInfo) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
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
                this.b.c.d = ugcVideoPostReviewInfo.more;
                this.b.c.e = ugcVideoPostReviewInfo.offset;
                if (z) {
                    this.b.c.g = 0;
                } else {
                    this.b.c.g = 1;
                }
                if (this.b.b != null) {
                    this.b.b.a();
                    if (this.b.f != 0) {
                        this.b.b.a(this.b.e, this.b.f);
                    }
                }
            }
        });
    }

    public void c() {
        this.a.a(this.c.b.id, this.c.e).a(a.a()).b(new j<UgcVideoFollowListJson>(this) {
            final /* synthetic */ e a;

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
        parcel.writeLong(this.d);
        parcel.writeInt(this.f);
        parcel.writeLong(this.e);
    }
}
