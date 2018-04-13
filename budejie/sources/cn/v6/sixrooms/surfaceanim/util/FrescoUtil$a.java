package cn.v6.sixrooms.surfaceanim.util;

import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.drawee.backends.pipeline.Fresco;

class FrescoUtil$a implements MemoryTrimmable {
    private FrescoUtil$a() {
    }

    public final void trim(MemoryTrimType memoryTrimType) {
        double suggestedTrimRatio = memoryTrimType.getSuggestedTrimRatio();
        if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio) {
            Fresco.getImagePipeline().clearMemoryCaches();
        }
    }
}
