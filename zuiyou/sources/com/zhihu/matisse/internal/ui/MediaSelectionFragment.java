package com.zhihu.matisse.internal.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zhihu.matisse.R;
import com.zhihu.matisse.ResultItem;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.AlbumMediaCollection;
import com.zhihu.matisse.internal.model.AlbumMediaCollection.AlbumMediaCallbacks;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter.CheckStateListener;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter.OnMediaClickListener;
import com.zhihu.matisse.internal.ui.widget.MediaGridInset;
import com.zhihu.matisse.internal.utils.UIUtils;
import com.zhihu.matisse.thumbnail.ThumbnailManager;
import java.util.List;

public class MediaSelectionFragment extends Fragment implements AlbumMediaCallbacks, CheckStateListener, OnMediaClickListener {
    public static final String EXTRA_ALBUM = "extra_album";
    private AlbumMediaAdapter mAdapter;
    private final AlbumMediaCollection mAlbumMediaCollection = new AlbumMediaCollection();
    private AssistantProvider mAssistantProvider;
    private CheckStateListener mCheckStateListener;
    private OnMediaClickListener mOnMediaClickListener;
    private RecyclerView mRecyclerView;

    public interface AssistantProvider {
        SelectedItemCollection provideSelectedItemCollection();

        ThumbnailManager provideThumbnailManager();
    }

    public static class MessageEvent {
        public long providerId;

        public MessageEvent(long j) {
            this.providerId = j;
        }
    }

    public static MediaSelectionFragment newInstance(Album album) {
        MediaSelectionFragment mediaSelectionFragment = new MediaSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_album", album);
        mediaSelectionFragment.setArguments(bundle);
        return mediaSelectionFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AssistantProvider) {
            this.mAssistantProvider = (AssistantProvider) context;
            if (context instanceof CheckStateListener) {
                this.mCheckStateListener = (CheckStateListener) context;
            }
            if (context instanceof OnMediaClickListener) {
                this.mOnMediaClickListener = (OnMediaClickListener) context;
                return;
            }
            return;
        }
        throw new IllegalStateException("Context must implement AssistantProvider.");
    }

    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_media_selection, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        int spanCount;
        super.onActivityCreated(bundle);
        Album album = (Album) getArguments().getParcelable("extra_album");
        this.mAdapter = new AlbumMediaAdapter(getContext(), this.mAssistantProvider.provideSelectedItemCollection(), this.mAssistantProvider.provideThumbnailManager(), this.mRecyclerView);
        this.mAdapter.registerCheckStateListener(this);
        this.mAdapter.registerOnMediaClickListener(this);
        this.mRecyclerView.setHasFixedSize(true);
        SelectionSpec instance = SelectionSpec.getInstance();
        if (instance.gridExpectedSize > 0) {
            spanCount = UIUtils.spanCount(getContext(), instance.gridExpectedSize);
        } else if (instance.spanCount > 0) {
            spanCount = instance.spanCount;
        } else {
            spanCount = 3;
        }
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        this.mRecyclerView.addItemDecoration(new MediaGridInset(spanCount, getResources().getDimensionPixelSize(R.dimen.media_grid_spacing), false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAlbumMediaCollection.onCreate(getActivity(), this);
        this.mAlbumMediaCollection.load(album, instance.capture);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mAlbumMediaCollection.onDestroy();
    }

    public void refreshMediaGrid() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void refreshSelection() {
        this.mAdapter.refreshSelection();
    }

    public void onAlbumMediaLoad(Cursor cursor) {
        ThumbnailManager provideThumbnailManager = this.mAssistantProvider.provideThumbnailManager();
        Album album = (Album) getArguments().getParcelable("extra_album");
        if (!(provideThumbnailManager == null || album == null || !album.isAll())) {
            provideThumbnailManager.onAllMediaItemLoaded(cursor);
        }
        List<ResultItem> list = SelectionSpec.getInstance().selectedItems;
        if (list != null && list.size() > 0) {
            for (ResultItem resultItem : list) {
                cursor.moveToFirst();
                while (!cursor.isLast()) {
                    Item valueOf = Item.valueOf(cursor);
                    if (resultItem.path.equalsIgnoreCase(valueOf.path)) {
                        this.mAssistantProvider.provideSelectedItemCollection().add(valueOf);
                        break;
                    }
                    cursor.moveToNext();
                }
            }
            cursor.moveToFirst();
            onUpdate();
        }
        SelectionSpec.getInstance().selectedItems = null;
        this.mAdapter.swapCursor(cursor);
    }

    public void onAlbumMediaReset() {
        this.mAdapter.swapCursor(null);
    }

    public void onUpdate() {
        if (this.mCheckStateListener != null) {
            this.mCheckStateListener.onUpdate();
        }
    }

    public void onMediaClick(Album album, Item item, int i) {
        if (this.mOnMediaClickListener != null) {
            this.mOnMediaClickListener.onMediaClick((Album) getArguments().getParcelable("extra_album"), item, i);
        }
    }
}
