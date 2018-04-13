package cn.xiaochuankeji.tieba.api.download;

import okhttp3.ab;
import retrofit2.a.f;
import retrofit2.a.x;
import rx.d;

public interface DownloadApi {
    @f
    d<ab> download(@x String str);
}
