package cn.xiaochuankeji.tieba.ui.picker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private a a;
    private List<a> b;
    private String c;
    @BindView
    RecyclerView mRecyclerView;
    @BindView
    TextView mTvNoResult;

    private class a extends Adapter<RegionHolder> implements Filterable {
        final /* synthetic */ SearchFragment a;
        private List<a> b = new ArrayList();

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((RegionHolder) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        a(SearchFragment searchFragment) {
            this.a = searchFragment;
            this.b.clear();
            this.b.addAll(searchFragment.b);
        }

        public RegionHolder a(ViewGroup viewGroup, int i) {
            final RegionHolder regionHolder = new RegionHolder(LayoutInflater.from(this.a.getActivity()).inflate(R.layout.item_region, viewGroup, false));
            regionHolder.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    a aVar = (a) this.b.b.get(regionHolder.getAdapterPosition());
                    if (aVar != null && !TextUtils.isEmpty(aVar.b)) {
                        int indexOf = aVar.b.indexOf(" ");
                        if (indexOf > 0) {
                            Intent intent = new Intent();
                            intent.putExtra("kRegionCode", aVar.b.substring(indexOf));
                            this.b.a.getActivity().setResult(-1, intent);
                            this.b.a.getActivity().finish();
                        }
                    }
                }
            });
            return regionHolder;
        }

        public int getItemCount() {
            return this.b.size();
        }

        public void a(RegionHolder regionHolder, int i) {
            a aVar = (a) this.b.get(i);
            regionHolder.name.setText(aVar.b);
            regionHolder.flag.setImageResource(aVar.c);
        }

        public Filter getFilter() {
            return new Filter(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                protected FilterResults performFiltering(CharSequence charSequence) {
                    ArrayList arrayList = new ArrayList();
                    for (a aVar : this.a.a.b) {
                        if (aVar.d.startsWith(charSequence.toString()) || aVar.b.contains(charSequence)) {
                            arrayList.add(aVar);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.count = arrayList.size();
                    filterResults.values = arrayList;
                    return filterResults;
                }

                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    ArrayList arrayList = (ArrayList) filterResults.values;
                    this.a.b.clear();
                    this.a.b.addAll(arrayList);
                    if (filterResults.count == 0) {
                        this.a.a.mTvNoResult.setVisibility(0);
                    } else {
                        this.a.a.mTvNoResult.setVisibility(4);
                    }
                    this.a.notifyDataSetChanged();
                }
            };
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_search_prefecture, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void a(List<a> list) {
        this.b = list;
        this.a = new a(this);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseApplication.getAppContext()));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(this.a);
        if (this.c != null) {
            this.a.getFilter().filter(this.c);
        }
    }

    public void a(String str) {
        if (this.b == null) {
            this.c = str.toLowerCase();
        } else if (!TextUtils.isEmpty(str)) {
            this.a.getFilter().filter(str.toLowerCase());
        }
    }
}
