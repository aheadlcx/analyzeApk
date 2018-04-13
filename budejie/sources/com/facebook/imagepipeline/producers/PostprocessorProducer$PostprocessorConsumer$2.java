package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;

class PostprocessorProducer$PostprocessorConsumer$2 implements Runnable {
    final /* synthetic */ PostprocessorProducer$PostprocessorConsumer this$1;

    PostprocessorProducer$PostprocessorConsumer$2(PostprocessorProducer$PostprocessorConsumer postprocessorProducer$PostprocessorConsumer) {
        this.this$1 = postprocessorProducer$PostprocessorConsumer;
    }

    public void run() {
        synchronized (this.this$1) {
            CloseableReference access$300 = PostprocessorProducer$PostprocessorConsumer.access$300(this.this$1);
            int access$400 = PostprocessorProducer$PostprocessorConsumer.access$400(this.this$1);
            PostprocessorProducer$PostprocessorConsumer.access$302(this.this$1, null);
            PostprocessorProducer$PostprocessorConsumer.access$502(this.this$1, false);
        }
        if (CloseableReference.isValid(access$300)) {
            try {
                PostprocessorProducer$PostprocessorConsumer.access$600(this.this$1, access$300, access$400);
            } finally {
                CloseableReference.closeSafely(access$300);
            }
        }
        PostprocessorProducer$PostprocessorConsumer.access$700(this.this$1);
    }
}
