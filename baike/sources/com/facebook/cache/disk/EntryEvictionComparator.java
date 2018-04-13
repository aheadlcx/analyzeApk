package com.facebook.cache.disk;

import com.facebook.cache.disk.DiskStorage.Entry;
import java.util.Comparator;

public interface EntryEvictionComparator extends Comparator<Entry> {
}
