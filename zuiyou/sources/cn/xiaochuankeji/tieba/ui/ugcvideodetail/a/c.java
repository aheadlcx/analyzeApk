package cn.xiaochuankeji.tieba.ui.ugcvideodetail.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoFollowListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoPostReviewInfo;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import rx.a.b.a;
import rx.d;
import rx.j;

public class c extends a {
    public static final Creator<c> CREATOR = new Creator<c>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public c[] a(int i) {
            return new c[i];
        }

        public c a(Parcel parcel) {
            return new c(parcel);
        }
    };
    private long d;
    private long e;
    private int f;

    public c(long j) {
        this.d = j;
    }

    public c(long j, long j2, int i) {
        this.d = j;
        this.e = j2;
        this.f = i;
    }

    public c(Parcel parcel) {
        this.d = parcel.readLong();
        this.f = parcel.readInt();
        this.e = parcel.readLong();
    }

    public void b() {
        a(false);
    }

    protected void a(final boolean z) {
        d c;
        if (z) {
            c = this.a.c(this.d);
        } else {
            c = this.a.b(this.d);
        }
        c.a(a.a()).b(new j<UgcVideoPostReviewInfo>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoPostReviewInfo) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                } else {
                    g.b("服务器内部错误");
                }
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
            final /* synthetic */ c a;

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
                this.a.c.d = ugcVideoFollowListJson.more;
                this.a.c.e = ugcVideoFollowListJson.offset;
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
