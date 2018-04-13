package com.spriteapp.booklibrary.recyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.factory.ItemTypeFactory;
import com.spriteapp.booklibrary.recyclerView.viewholder.BaseViewHolder;
import com.spriteapp.booklibrary.util.CollectionUtil;
import java.util.List;

public class MultiAdapter extends Adapter<BaseViewHolder<Visitable>> {
    private Context mContext;
    private List<Visitable> mItems;
    private ItemTypeFactory typeFactory = new ItemTypeFactory();

    public MultiAdapter(Context context, List<Visitable> list) {
        this.mContext = context;
        this.mItems = list;
    }

    public BaseViewHolder<Visitable> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.typeFactory.createViewHolder(i, LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false), this.mContext);
    }

    public void onBindViewHolder(BaseViewHolder<Visitable> baseViewHolder, int i) {
        baseViewHolder.bindViewData(this.mItems.get(i));
    }

    public int getItemViewType(int i) {
        return ((Visitable) this.mItems.get(i)).type(this.typeFactory);
    }

    public int getItemCount() {
        return CollectionUtil.isEmpty(this.mItems) ? 0 : this.mItems.size();
    }
}
