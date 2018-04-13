package com.emilsjolander.components.stickylistheaders;

import android.content.Context;
import android.widget.SectionIndexer;

class SectionIndexerAdapterWrapper extends AdapterWrapper implements SectionIndexer {
    final SectionIndexer mSectionIndexerDelegate;

    SectionIndexerAdapterWrapper(Context context, StickyListHeadersAdapter stickyListHeadersAdapter) {
        super(context, stickyListHeadersAdapter);
        this.mSectionIndexerDelegate = (SectionIndexer) stickyListHeadersAdapter;
    }

    public int getPositionForSection(int i) {
        return this.mSectionIndexerDelegate.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.mSectionIndexerDelegate.getSectionForPosition(i);
    }

    public Object[] getSections() {
        return this.mSectionIndexerDelegate.getSections();
    }
}
