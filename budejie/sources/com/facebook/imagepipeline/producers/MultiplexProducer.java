package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MultiplexProducer<K, T extends Closeable> implements Producer<T> {
    private final Producer<T> mInputProducer;
    @GuardedBy("this")
    @VisibleForTesting
    final Map<K, MultiplexProducer$Multiplexer> mMultiplexers = new HashMap();

    protected abstract T cloneOrNull(T t);

    protected abstract K getKey(ProducerContext producerContext);

    protected MultiplexProducer(Producer<T> producer) {
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        Object key = getKey(producerContext);
        MultiplexProducer$Multiplexer existingMultiplexer;
        do {
            Object obj = null;
            synchronized (this) {
                existingMultiplexer = getExistingMultiplexer(key);
                if (existingMultiplexer == null) {
                    existingMultiplexer = createAndPutNewMultiplexer(key);
                    obj = 1;
                }
            }
        } while (!existingMultiplexer.addNewConsumer(consumer, producerContext));
        if (obj != null) {
            MultiplexProducer$Multiplexer.access$000(existingMultiplexer);
        }
    }

    private synchronized MultiplexProducer$Multiplexer getExistingMultiplexer(K k) {
        return (MultiplexProducer$Multiplexer) this.mMultiplexers.get(k);
    }

    private synchronized MultiplexProducer$Multiplexer createAndPutNewMultiplexer(K k) {
        MultiplexProducer$Multiplexer multiplexProducer$Multiplexer;
        multiplexProducer$Multiplexer = new MultiplexProducer$Multiplexer(this, k);
        this.mMultiplexers.put(k, multiplexProducer$Multiplexer);
        return multiplexProducer$Multiplexer;
    }

    private synchronized void removeMultiplexer(K k, MultiplexProducer$Multiplexer multiplexProducer$Multiplexer) {
        if (this.mMultiplexers.get(k) == multiplexProducer$Multiplexer) {
            this.mMultiplexers.remove(k);
        }
    }
}
