package com.meizu.cloud.pushsdk.pushtracer.emitter.classic;

import com.meizu.cloud.pushsdk.networking.http.Request;
import com.meizu.cloud.pushsdk.pushtracer.dataload.DataLoad;
import com.meizu.cloud.pushsdk.pushtracer.emitter.Emitter.EmitterBuilder;
import com.meizu.cloud.pushsdk.pushtracer.emitter.ReadyRequest;
import com.meizu.cloud.pushsdk.pushtracer.emitter.RequestResult;
import com.meizu.cloud.pushsdk.pushtracer.storage.EventStore;
import com.meizu.cloud.pushsdk.pushtracer.storage.MemoryStore;
import com.meizu.cloud.pushsdk.pushtracer.storage.Store;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Emitter extends com.meizu.cloud.pushsdk.pushtracer.emitter.Emitter {
    private final String TAG = Emitter.class.getSimpleName();
    private int emptyCount;
    private Store eventStore = new EventStore(this.context, this.sendLimit);

    public Emitter(EmitterBuilder emitterBuilder) {
        super(emitterBuilder);
        if (!this.eventStore.isOpen()) {
            this.eventStore = new MemoryStore(this.sendLimit);
            Logger.e(this.TAG, "init memory store", new Object[0]);
        }
    }

    public void add(DataLoad dataLoad) {
        this.eventStore.add(dataLoad);
        Logger.e(this.TAG, "isRunning " + this.isRunning, new Object[0]);
        if (this.isRunning.compareAndSet(false, true)) {
            attemptEmit();
        }
    }

    public void add(DataLoad dataLoad, boolean z) {
        this.eventStore.add(dataLoad);
        Logger.e(this.TAG, "isRunning " + this.isRunning + " attemptEmit " + z, new Object[0]);
        if (!z) {
            try {
                this.timeUnit.sleep(1);
            } catch (InterruptedException e) {
                Logger.e(this.TAG, "Emitter add thread sleep interrupted: " + e.toString(), new Object[0]);
            }
        }
        if (this.isRunning.compareAndSet(false, true)) {
            attemptEmit();
        }
    }

    public void flush() {
        Executor.execute(new Runnable() {
            public void run() {
                if (Emitter.this.isRunning.compareAndSet(false, true)) {
                    Emitter.this.attemptEmit();
                }
            }
        });
    }

    private void attemptEmit() {
        if (!Util.isOnline(this.context)) {
            Logger.e(this.TAG, "Emitter loop stopping: emitter offline.", new Object[0]);
            this.isRunning.compareAndSet(true, false);
        } else if (this.eventStore.getSize() > 0) {
            this.emptyCount = 0;
            LinkedList performAsyncEmit = performAsyncEmit(buildRequests(this.eventStore.getEmittableEvents()));
            Logger.i(this.TAG, "Processing emitter results.", new Object[0]);
            LinkedList linkedList = new LinkedList();
            Iterator it = performAsyncEmit.iterator();
            int i = 0;
            int i2 = 0;
            while (it.hasNext()) {
                int size;
                int i3;
                RequestResult requestResult = (RequestResult) it.next();
                if (requestResult.getSuccess()) {
                    Iterator it2 = requestResult.getEventIds().iterator();
                    while (it2.hasNext()) {
                        linkedList.add((Long) it2.next());
                    }
                    size = i2 + requestResult.getEventIds().size();
                    i3 = i;
                } else {
                    i3 = requestResult.getEventIds().size() + i;
                    Logger.e(this.TAG, "Request sending failed but we will retry later.", new Object[0]);
                    size = i2;
                }
                i = i3;
                i2 = size;
            }
            performAsyncEventRemoval(linkedList);
            Logger.d(this.TAG, "Success Count: %s", Integer.valueOf(i2));
            Logger.d(this.TAG, "Failure Count: %s", Integer.valueOf(i));
            if (this.requestCallback != null) {
                if (i != 0) {
                    this.requestCallback.onFailure(i2, i);
                } else {
                    this.requestCallback.onSuccess(i2);
                }
            }
            if (i <= 0 || i2 != 0) {
                attemptEmit();
                return;
            }
            if (Util.isOnline(this.context)) {
                Logger.e(this.TAG, "Ensure collector path is valid: %s", getEmitterUri());
            }
            Logger.e(this.TAG, "Emitter loop stopping: failures.", new Object[0]);
            this.isRunning.compareAndSet(true, false);
        } else if (this.emptyCount >= this.emptyLimit) {
            Logger.e(this.TAG, "Emitter loop stopping: empty limit reached.", new Object[0]);
            this.isRunning.compareAndSet(true, false);
        } else {
            this.emptyCount++;
            Logger.e(this.TAG, "Emitter database empty: " + this.emptyCount, new Object[0]);
            try {
                this.timeUnit.sleep((long) this.emitterTick);
            } catch (InterruptedException e) {
                Logger.e(this.TAG, "Emitter thread sleep interrupted: " + e.toString(), new Object[0]);
            }
            attemptEmit();
        }
    }

    private LinkedList<RequestResult> performAsyncEmit(LinkedList<ReadyRequest> linkedList) {
        LinkedList<RequestResult> linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            linkedList3.add(Executor.futureCallable(getRequestCallable(((ReadyRequest) it.next()).getRequest())));
        }
        Logger.d(this.TAG, "Request Futures: %s", Integer.valueOf(linkedList3.size()));
        for (int i = 0; i < linkedList3.size(); i++) {
            int i2 = -1;
            try {
                i2 = ((Integer) ((Future) linkedList3.get(i)).get(5, TimeUnit.SECONDS)).intValue();
            } catch (InterruptedException e) {
                Logger.e(this.TAG, "Request Future was interrupted: %s", e.getMessage());
            } catch (ExecutionException e2) {
                Logger.e(this.TAG, "Request Future failed: %s", e2.getMessage());
            } catch (TimeoutException e3) {
                Logger.e(this.TAG, "Request Future had a timeout: %s", e3.getMessage());
            }
            if (((ReadyRequest) linkedList.get(i)).isOversize()) {
                linkedList2.add(new RequestResult(true, ((ReadyRequest) linkedList.get(i)).getEventIds()));
            } else {
                linkedList2.add(new RequestResult(isSuccessfulSend(i2), ((ReadyRequest) linkedList.get(i)).getEventIds()));
            }
        }
        return linkedList2;
    }

    private LinkedList<Boolean> performAsyncEventRemoval(LinkedList<Long> linkedList) {
        LinkedList<Boolean> linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            linkedList3.add(Executor.futureCallable(getRemoveCallable((Long) it.next())));
        }
        Logger.d(this.TAG, "Removal Futures: %s", Integer.valueOf(linkedList3.size()));
        for (int i = 0; i < linkedList3.size(); i++) {
            boolean booleanValue;
            try {
                booleanValue = ((Boolean) ((Future) linkedList3.get(i)).get(5, TimeUnit.SECONDS)).booleanValue();
            } catch (InterruptedException e) {
                Logger.e(this.TAG, "Removal Future was interrupted: %s", e.getMessage());
                booleanValue = false;
            } catch (ExecutionException e2) {
                Logger.e(this.TAG, "Removal Future failed: %s", e2.getMessage());
                booleanValue = false;
            } catch (TimeoutException e3) {
                Logger.e(this.TAG, "Removal Future had a timeout: %s", e3.getMessage());
                booleanValue = false;
            }
            linkedList2.add(Boolean.valueOf(booleanValue));
        }
        return linkedList2;
    }

    private Callable<Integer> getRequestCallable(final Request request) {
        return new Callable<Integer>() {
            public Integer call() throws Exception {
                return Integer.valueOf(Emitter.this.requestSender(request));
            }
        };
    }

    private Callable<Boolean> getRemoveCallable(final Long l) {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(Emitter.this.eventStore.removeEvent(l.longValue()));
            }
        };
    }

    public void shutdown() {
        Logger.d(this.TAG, "Shutting down emitter.", new Object[0]);
        this.isRunning.compareAndSet(true, false);
        Executor.shutdown();
    }

    public Store getEventStore() {
        return this.eventStore;
    }

    public boolean getEmitterStatus() {
        return this.isRunning.get();
    }
}
