package com.zhihu.matisse;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import cn.xiaochuan.image.b.c;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.ui.MatisseActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class SelectionCreator {
    private final Matisse mMatisse;
    private final SelectionSpec mSelectionSpec = SelectionSpec.getCleanInstance();

    SelectionCreator(Matisse matisse, @NonNull Set<MimeType> set, boolean z) {
        this.mMatisse = matisse;
        this.mSelectionSpec.mimeTypeSet = set;
        this.mSelectionSpec.mediaTypeExclusive = z;
        this.mSelectionSpec.orientation = -1;
    }

    public SelectionCreator showSingleMediaType(boolean z) {
        this.mSelectionSpec.showSingleMediaType = z;
        return this;
    }

    public SelectionCreator theme(@StyleRes int i) {
        this.mSelectionSpec.themeId = i;
        return this;
    }

    public SelectionCreator nightMode(boolean z) {
        this.mSelectionSpec.isNightMode = z;
        this.mSelectionSpec.themeId = z ? R.style.NightTheme : R.style.DayTheme;
        return this;
    }

    public SelectionCreator thumbnailDir(String str) {
        this.mSelectionSpec.thumbnailDir = str;
        return this;
    }

    public SelectionCreator countable(boolean z) {
        this.mSelectionSpec.countable = z;
        return this;
    }

    public SelectionCreator maxSelectable(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("maxSelectable must be greater than or equal to one");
        }
        this.mSelectionSpec.maxSelectable = i;
        return this;
    }

    public SelectionCreator addFilter(@NonNull Filter filter) {
        if (this.mSelectionSpec.filters == null) {
            this.mSelectionSpec.filters = new ArrayList();
        }
        if (filter == null) {
            throw new IllegalArgumentException("filter cannot be null");
        }
        this.mSelectionSpec.filters.add(filter);
        return this;
    }

    public SelectionCreator capture(boolean z) {
        this.mSelectionSpec.capture = z;
        return this;
    }

    public SelectionCreator restrictOrientation(int i) {
        this.mSelectionSpec.orientation = i;
        return this;
    }

    public SelectionCreator spanCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("spanCount cannot be less than 1");
        }
        this.mSelectionSpec.spanCount = i;
        return this;
    }

    public SelectionCreator gridExpectedSize(int i) {
        this.mSelectionSpec.gridExpectedSize = i;
        return this;
    }

    public SelectionCreator thumbnailScale(float f) {
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Thumbnail scale must be between (0.0, 1.0]");
        }
        this.mSelectionSpec.thumbnailScale = f;
        return this;
    }

    public SelectionCreator imageEngine(c cVar) {
        this.mSelectionSpec.imageEngine = cVar;
        return this;
    }

    public SelectionCreator withSelected(List<ResultItem> list) {
        this.mSelectionSpec.selectedItems = list;
        return this;
    }

    public void forResult(int i) {
        Context activity = this.mMatisse.getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, MatisseActivity.class);
            Fragment fragment = this.mMatisse.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, i);
            } else {
                activity.startActivityForResult(intent, i);
            }
        }
    }
}
