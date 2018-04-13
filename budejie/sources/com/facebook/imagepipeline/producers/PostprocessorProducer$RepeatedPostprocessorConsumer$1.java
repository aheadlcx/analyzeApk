package com.facebook.imagepipeline.producers;

class PostprocessorProducer$RepeatedPostprocessorConsumer$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ PostprocessorProducer$RepeatedPostprocessorConsumer this$1;
    final /* synthetic */ PostprocessorProducer val$this$0;

    PostprocessorProducer$RepeatedPostprocessorConsumer$1(PostprocessorProducer$RepeatedPostprocessorConsumer postprocessorProducer$RepeatedPostprocessorConsumer, PostprocessorProducer postprocessorProducer) {
        this.this$1 = postprocessorProducer$RepeatedPostprocessorConsumer;
        this.val$this$0 = postprocessorProducer;
    }

    public void onCancellationRequested() {
        if (PostprocessorProducer$RepeatedPostprocessorConsumer.access$1000(this.this$1)) {
            this.this$1.getConsumer().onCancellation();
        }
    }
}
