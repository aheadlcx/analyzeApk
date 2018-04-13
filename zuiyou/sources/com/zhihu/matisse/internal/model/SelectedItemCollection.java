package com.zhihu.matisse.internal.model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.zhihu.matisse.R;
import com.zhihu.matisse.ResultItem;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PathUtils;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SelectedItemCollection {
    public static final int COLLECTION_IMAGE = 1;
    public static final int COLLECTION_MIXED = 3;
    public static final int COLLECTION_UNDEFINED = 0;
    public static final int COLLECTION_VIDEO = 2;
    public static final String STATE_COLLECTION_TYPE = "state_collection_type";
    public static final String STATE_SELECTION = "state_selection";
    private int mCollectionType = 0;
    private final Context mContext;
    private Set<Item> mItems;

    public SelectedItemCollection(Context context) {
        this.mContext = context;
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            this.mItems = new LinkedHashSet();
            return;
        }
        this.mItems = new LinkedHashSet(bundle.getParcelableArrayList(STATE_SELECTION));
        this.mCollectionType = bundle.getInt(STATE_COLLECTION_TYPE, 0);
    }

    public void setDefaultSelection(List<Item> list) {
        this.mItems.addAll(list);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList(this.mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, this.mCollectionType);
    }

    public Bundle getDataWithBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList(this.mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, this.mCollectionType);
        return bundle;
    }

    public boolean add(Item item) {
        if (typeConflict(item)) {
            throw new IllegalArgumentException("Can't select images and videos at the same time.");
        }
        boolean add = this.mItems.add(item);
        if (add) {
            if (this.mCollectionType == 0) {
                if (item.isImage()) {
                    this.mCollectionType = 1;
                } else if (item.isVideo()) {
                    this.mCollectionType = 2;
                }
            } else if (this.mCollectionType == 1) {
                if (item.isVideo()) {
                    this.mCollectionType = 3;
                }
            } else if (this.mCollectionType == 2 && item.isImage()) {
                this.mCollectionType = 3;
            }
        }
        return add;
    }

    public boolean remove(Item item) {
        boolean remove = this.mItems.remove(item);
        if (remove) {
            if (this.mItems.size() == 0) {
                this.mCollectionType = 0;
            } else if (this.mCollectionType == 3) {
                refineCollectionType();
            }
        }
        return remove;
    }

    public void overwrite(ArrayList<Item> arrayList, int i) {
        if (arrayList.size() == 0) {
            this.mCollectionType = 0;
        } else {
            this.mCollectionType = i;
        }
        this.mItems.clear();
        this.mItems.addAll(arrayList);
    }

    public List<Item> asList() {
        return new ArrayList(this.mItems);
    }

    public List<Uri> asListOfUri() {
        List<Uri> arrayList = new ArrayList();
        for (Item contentUri : this.mItems) {
            arrayList.add(contentUri.getContentUri());
        }
        return arrayList;
    }

    public List<String> asListOfString() {
        List<String> arrayList = new ArrayList();
        for (Item item : this.mItems) {
            Object path;
            String str = item.path;
            if (str == null) {
                path = PathUtils.getPath(this.mContext, item.getContentUri());
            } else {
                String str2 = str;
            }
            arrayList.add(path);
        }
        return arrayList;
    }

    public List<ResultItem> asListOfResultItem() {
        List<ResultItem> arrayList = new ArrayList();
        for (Item item : this.mItems) {
            String str = item.path;
            if (str == null) {
                str = PathUtils.getPath(this.mContext, item.getContentUri());
            }
            arrayList.add(new ResultItem(item.id, str, item.mimeType, item.videoThumbnail, item.width, item.height));
        }
        return arrayList;
    }

    public boolean isEmpty() {
        return this.mItems == null || this.mItems.isEmpty();
    }

    public boolean isSelected(Item item) {
        return this.mItems.contains(item);
    }

    public IncapableCause isAcceptable(Item item) {
        if (maxSelectableReached()) {
            int i = SelectionSpec.getInstance().maxSelectable;
            return new IncapableCause(this.mContext.getString(R.string.error_over_count, new Object[]{Integer.valueOf(i)}));
        } else if (typeConflict(item)) {
            return new IncapableCause(this.mContext.getString(R.string.error_type_conflict));
        } else {
            return PhotoMetadataUtils.isAcceptable(this.mContext, item);
        }
    }

    public boolean maxSelectableReached() {
        return this.mItems.size() == SelectionSpec.getInstance().maxSelectable;
    }

    public int getCollectionType() {
        return this.mCollectionType;
    }

    private void refineCollectionType() {
        int i = 0;
        int i2 = 0;
        for (Item item : this.mItems) {
            int i3;
            if (item.isImage() && i2 == 0) {
                i2 = 1;
            }
            if (item.isVideo() && i == 0) {
                i3 = 1;
            } else {
                i3 = i;
            }
            i = i3;
        }
        if (i2 != 0 && i != 0) {
            this.mCollectionType = 3;
        } else if (i2 != 0) {
            this.mCollectionType = 1;
        } else if (i != 0) {
            this.mCollectionType = 2;
        }
    }

    public boolean typeConflict(Item item) {
        if (SelectionSpec.getInstance().mediaTypeExclusive) {
            if (item.isImage() && (this.mCollectionType == 2 || this.mCollectionType == 3)) {
                return true;
            }
            if (item.isVideo() && (this.mCollectionType == 1 || this.mCollectionType == 3)) {
                return true;
            }
        }
        return false;
    }

    public int count() {
        return this.mItems.size();
    }

    public int checkedNumOf(Item item) {
        int indexOf = new ArrayList(this.mItems).indexOf(item);
        return indexOf == -1 ? Integer.MIN_VALUE : indexOf + 1;
    }
}
