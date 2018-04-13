package qsbk.app.image;

import android.content.Context;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.image.OkHttpDnsUtil.DnsInfo;
import qsbk.app.utils.UILDiskCacheCleaner;
import qsbk.app.utils.image.issue.DisplayIssueManager;
import qsbk.app.utils.image.issue.Logger;

final class e extends BaseRequestListener {
    final /* synthetic */ Context a;

    e(Context context) {
        this.a = context;
    }

    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        if (th instanceof IOException) {
            try {
                DnsInfo dnsInfo = OkHttpDnsUtil.getInstance().get(imageRequest.getSourceUri().getHost());
                DisplayIssueManager.getInstance().reportNewIssue(QsbkApp.mContext, imageRequest.getSourceUri().toString(), (IOException) th, dnsInfo != null ? dnsInfo.ip : null, dnsInfo != null ? dnsInfo.type : -1);
                UILDiskCacheCleaner.clearUILIndividualCache(this.a);
            } catch (Throwable th2) {
            }
        }
    }

    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) {
        if (NetworkFetchProducer.PRODUCER_NAME.equals(str2) && (th instanceof IOException)) {
            try {
                Logger.getInstance().debug(ImagePipelineConfigUtils.class.getSimpleName(), "onProducerFinishWithFailure " + str, th.getMessage());
            } catch (Throwable th2) {
            }
        }
    }
}
