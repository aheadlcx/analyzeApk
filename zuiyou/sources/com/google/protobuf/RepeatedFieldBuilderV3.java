package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RepeatedFieldBuilderV3<MType extends AbstractMessage, BType extends AbstractMessage$Builder, IType extends MessageOrBuilder> implements AbstractMessage$BuilderParent {
    private List<SingleFieldBuilderV3<MType, BType, IType>> builders;
    private BuilderExternalList<MType, BType, IType> externalBuilderList;
    private MessageExternalList<MType, BType, IType> externalMessageList;
    private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
    private boolean isClean;
    private boolean isMessagesListMutable;
    private List<MType> messages;
    private AbstractMessage$BuilderParent parent;

    private static class BuilderExternalList<MType extends AbstractMessage, BType extends AbstractMessage$Builder, IType extends MessageOrBuilder> extends AbstractList<BType> implements List<BType> {
        RepeatedFieldBuilderV3<MType, BType, IType> builder;

        BuilderExternalList(RepeatedFieldBuilderV3<MType, BType, IType> repeatedFieldBuilderV3) {
            this.builder = repeatedFieldBuilderV3;
        }

        public int size() {
            return this.builder.getCount();
        }

        public BType get(int i) {
            return this.builder.getBuilder(i);
        }

        void incrementModCount() {
            this.modCount++;
        }
    }

    private static class MessageExternalList<MType extends AbstractMessage, BType extends AbstractMessage$Builder, IType extends MessageOrBuilder> extends AbstractList<MType> implements List<MType> {
        RepeatedFieldBuilderV3<MType, BType, IType> builder;

        MessageExternalList(RepeatedFieldBuilderV3<MType, BType, IType> repeatedFieldBuilderV3) {
            this.builder = repeatedFieldBuilderV3;
        }

        public int size() {
            return this.builder.getCount();
        }

        public MType get(int i) {
            return this.builder.getMessage(i);
        }

        void incrementModCount() {
            this.modCount++;
        }
    }

    private static class MessageOrBuilderExternalList<MType extends AbstractMessage, BType extends AbstractMessage$Builder, IType extends MessageOrBuilder> extends AbstractList<IType> implements List<IType> {
        RepeatedFieldBuilderV3<MType, BType, IType> builder;

        MessageOrBuilderExternalList(RepeatedFieldBuilderV3<MType, BType, IType> repeatedFieldBuilderV3) {
            this.builder = repeatedFieldBuilderV3;
        }

        public int size() {
            return this.builder.getCount();
        }

        public IType get(int i) {
            return this.builder.getMessageOrBuilder(i);
        }

        void incrementModCount() {
            this.modCount++;
        }
    }

    public RepeatedFieldBuilderV3(List<MType> list, boolean z, AbstractMessage$BuilderParent abstractMessage$BuilderParent, boolean z2) {
        this.messages = list;
        this.isMessagesListMutable = z;
        this.parent = abstractMessage$BuilderParent;
        this.isClean = z2;
    }

    public void dispose() {
        this.parent = null;
    }

    private void ensureMutableMessageList() {
        if (!this.isMessagesListMutable) {
            this.messages = new ArrayList(this.messages);
            this.isMessagesListMutable = true;
        }
    }

    private void ensureBuilders() {
        if (this.builders == null) {
            this.builders = new ArrayList(this.messages.size());
            for (int i = 0; i < this.messages.size(); i++) {
                this.builders.add(null);
            }
        }
    }

    public int getCount() {
        return this.messages.size();
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public MType getMessage(int i) {
        return getMessage(i, false);
    }

    private MType getMessage(int i, boolean z) {
        if (this.builders == null) {
            return (AbstractMessage) this.messages.get(i);
        }
        SingleFieldBuilderV3 singleFieldBuilderV3 = (SingleFieldBuilderV3) this.builders.get(i);
        if (singleFieldBuilderV3 == null) {
            return (AbstractMessage) this.messages.get(i);
        }
        return z ? singleFieldBuilderV3.build() : singleFieldBuilderV3.getMessage();
    }

    public BType getBuilder(int i) {
        ensureBuilders();
        SingleFieldBuilderV3 singleFieldBuilderV3 = (SingleFieldBuilderV3) this.builders.get(i);
        if (singleFieldBuilderV3 == null) {
            SingleFieldBuilderV3 singleFieldBuilderV32 = new SingleFieldBuilderV3((AbstractMessage) this.messages.get(i), this, this.isClean);
            this.builders.set(i, singleFieldBuilderV32);
            singleFieldBuilderV3 = singleFieldBuilderV32;
        }
        return singleFieldBuilderV3.getBuilder();
    }

    public IType getMessageOrBuilder(int i) {
        if (this.builders == null) {
            return (MessageOrBuilder) this.messages.get(i);
        }
        SingleFieldBuilderV3 singleFieldBuilderV3 = (SingleFieldBuilderV3) this.builders.get(i);
        if (singleFieldBuilderV3 == null) {
            return (MessageOrBuilder) this.messages.get(i);
        }
        return singleFieldBuilderV3.getMessageOrBuilder();
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> setMessage(int i, MType mType) {
        Internal.checkNotNull(mType);
        ensureMutableMessageList();
        this.messages.set(i, mType);
        if (this.builders != null) {
            SingleFieldBuilderV3 singleFieldBuilderV3 = (SingleFieldBuilderV3) this.builders.set(i, null);
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.dispose();
            }
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> addMessage(MType mType) {
        Internal.checkNotNull(mType);
        ensureMutableMessageList();
        this.messages.add(mType);
        if (this.builders != null) {
            this.builders.add(null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> addMessage(int i, MType mType) {
        Internal.checkNotNull(mType);
        ensureMutableMessageList();
        this.messages.add(i, mType);
        if (this.builders != null) {
            this.builders.add(i, null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> addAllMessages(Iterable<? extends MType> iterable) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Internal.checkNotNull((AbstractMessage) it.next());
        }
        int size;
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() != 0) {
                size = collection.size();
                ensureMutableMessageList();
                if (size >= 0 && (this.messages instanceof ArrayList)) {
                    ((ArrayList) this.messages).ensureCapacity(size + this.messages.size());
                }
                it = iterable.iterator();
                while (it.hasNext()) {
                    addMessage((AbstractMessage) it.next());
                }
                onChanged();
                incrementModCounts();
            }
        } else {
            size = -1;
            ensureMutableMessageList();
            ((ArrayList) this.messages).ensureCapacity(size + this.messages.size());
            it = iterable.iterator();
            while (it.hasNext()) {
                addMessage((AbstractMessage) it.next());
            }
            onChanged();
            incrementModCounts();
        }
        return this;
    }

    public BType addBuilder(MType mType) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilderV3 singleFieldBuilderV3 = new SingleFieldBuilderV3(mType, this, this.isClean);
        this.messages.add(null);
        this.builders.add(singleFieldBuilderV3);
        onChanged();
        incrementModCounts();
        return singleFieldBuilderV3.getBuilder();
    }

    public BType addBuilder(int i, MType mType) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilderV3 singleFieldBuilderV3 = new SingleFieldBuilderV3(mType, this, this.isClean);
        this.messages.add(i, null);
        this.builders.add(i, singleFieldBuilderV3);
        onChanged();
        incrementModCounts();
        return singleFieldBuilderV3.getBuilder();
    }

    public void remove(int i) {
        ensureMutableMessageList();
        this.messages.remove(i);
        if (this.builders != null) {
            SingleFieldBuilderV3 singleFieldBuilderV3 = (SingleFieldBuilderV3) this.builders.remove(i);
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.dispose();
            }
        }
        onChanged();
        incrementModCounts();
    }

    public void clear() {
        this.messages = Collections.emptyList();
        this.isMessagesListMutable = false;
        if (this.builders != null) {
            for (SingleFieldBuilderV3 singleFieldBuilderV3 : this.builders) {
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.dispose();
                }
            }
            this.builders = null;
        }
        onChanged();
        incrementModCounts();
    }

    public List<MType> build() {
        this.isClean = true;
        if (!this.isMessagesListMutable && this.builders == null) {
            return this.messages;
        }
        if (!this.isMessagesListMutable) {
            boolean z;
            for (int i = 0; i < this.messages.size(); i++) {
                Object obj = (Message) this.messages.get(i);
                SingleFieldBuilderV3 singleFieldBuilderV3 = (SingleFieldBuilderV3) this.builders.get(i);
                if (singleFieldBuilderV3 != null && singleFieldBuilderV3.build() != obj) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return this.messages;
            }
        }
        ensureMutableMessageList();
        for (int i2 = 0; i2 < this.messages.size(); i2++) {
            this.messages.set(i2, getMessage(i2, true));
        }
        this.messages = Collections.unmodifiableList(this.messages);
        this.isMessagesListMutable = false;
        return this.messages;
    }

    public List<MType> getMessageList() {
        if (this.externalMessageList == null) {
            this.externalMessageList = new MessageExternalList(this);
        }
        return this.externalMessageList;
    }

    public List<BType> getBuilderList() {
        if (this.externalBuilderList == null) {
            this.externalBuilderList = new BuilderExternalList(this);
        }
        return this.externalBuilderList;
    }

    public List<IType> getMessageOrBuilderList() {
        if (this.externalMessageOrBuilderList == null) {
            this.externalMessageOrBuilderList = new MessageOrBuilderExternalList(this);
        }
        return this.externalMessageOrBuilderList;
    }

    private void onChanged() {
        if (this.isClean && this.parent != null) {
            this.parent.markDirty();
            this.isClean = false;
        }
    }

    public void markDirty() {
        onChanged();
    }

    private void incrementModCounts() {
        if (this.externalMessageList != null) {
            this.externalMessageList.incrementModCount();
        }
        if (this.externalBuilderList != null) {
            this.externalBuilderList.incrementModCount();
        }
        if (this.externalMessageOrBuilderList != null) {
            this.externalMessageOrBuilderList.incrementModCount();
        }
    }
}
