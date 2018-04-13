package qsbk.app.image;

import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import qsbk.app.core.utils.LogUtils;

final class f implements MemoryTrimmable {
    f() {
    }

    public void trim(MemoryTrimType memoryTrimType) {
        double suggestedTrimRatio = memoryTrimType.getSuggestedTrimRatio();
        LogUtils.d(String.format("MemoryTrimmableRegistry suggestedTrimRatio : %s", new Object[]{Double.valueOf(suggestedTrimRatio)}));
        if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio) {
            ImagePipelineFactory.getInstance().getImagePipeline().clearMemoryCaches();
        }
    }
}
