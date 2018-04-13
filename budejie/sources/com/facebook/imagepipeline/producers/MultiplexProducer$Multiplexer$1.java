package com.facebook.imagepipeline.producers;

import android.util.Pair;
import java.util.List;

class MultiplexProducer$Multiplexer$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ MultiplexProducer$Multiplexer this$1;
    final /* synthetic */ Pair val$consumerContextPair;

    MultiplexProducer$Multiplexer$1(MultiplexProducer$Multiplexer multiplexProducer$Multiplexer, Pair pair) {
        this.this$1 = multiplexProducer$Multiplexer;
        this.val$consumerContextPair = pair;
    }

    public void onCancellationRequested() {
        List list;
        List list2;
        BaseProducerContext baseProducerContext;
        List list3 = null;
        synchronized (this.this$1) {
            boolean remove = MultiplexProducer$Multiplexer.access$200(this.this$1).remove(this.val$consumerContextPair);
            if (!remove) {
                list = null;
                list2 = null;
                baseProducerContext = null;
            } else if (MultiplexProducer$Multiplexer.access$200(this.this$1).isEmpty()) {
                list2 = null;
                baseProducerContext = MultiplexProducer$Multiplexer.access$300(this.this$1);
                list = null;
            } else {
                List access$400 = MultiplexProducer$Multiplexer.access$400(this.this$1);
                list2 = MultiplexProducer$Multiplexer.access$500(this.this$1);
                list = list2;
                list2 = access$400;
                baseProducerContext = null;
                list3 = MultiplexProducer$Multiplexer.access$600(this.this$1);
            }
        }
        BaseProducerContext.callOnIsPrefetchChanged(list2);
        BaseProducerContext.callOnPriorityChanged(list);
        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(list3);
        if (baseProducerContext != null) {
            baseProducerContext.cancel();
        }
        if (remove) {
            ((Consumer) this.val$consumerContextPair.first).onCancellation();
        }
    }

    public void onIsPrefetchChanged() {
        BaseProducerContext.callOnIsPrefetchChanged(MultiplexProducer$Multiplexer.access$400(this.this$1));
    }

    public void onIsIntermediateResultExpectedChanged() {
        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(MultiplexProducer$Multiplexer.access$600(this.this$1));
    }

    public void onPriorityChanged() {
        BaseProducerContext.callOnPriorityChanged(MultiplexProducer$Multiplexer.access$500(this.this$1));
    }
}
