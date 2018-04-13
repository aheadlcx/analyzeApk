package com.spriteapp.booklibrary.recyclerView.viewholder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.e.b;
import com.spriteapp.booklibrary.model.store.HotSellResponse;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.model.HotSellModel;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.WebViewUtil;
import java.util.List;

public class HotSellViewHolder extends BaseViewHolder<Visitable> {
    private final View divideView;
    private RelativeLayout mChoiceLayout;
    private TextView mChoiceTextView;
    private Context mContext;
    private RelativeLayout mHotSellLayout;
    private TextView mHotSellTextView;
    private RelativeLayout mSerializeLayout;
    private TextView mSerializeTextView;

    public HotSellViewHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        this.mHotSellLayout = (RelativeLayout) view.findViewById(d.book_reader_hot_sell_layout);
        this.mChoiceLayout = (RelativeLayout) view.findViewById(d.book_reader_choice_layout);
        this.mSerializeLayout = (RelativeLayout) view.findViewById(d.book_reader_serialize_layout);
        this.mHotSellTextView = (TextView) view.findViewById(d.book_reader_hot_sell_text_view);
        this.mChoiceTextView = (TextView) view.findViewById(d.book_reader_choice_text_view);
        this.mSerializeTextView = (TextView) view.findViewById(d.book_reader_serialize_text_view);
        this.divideView = view.findViewById(d.book_reader_divide_view);
    }

    public void bindViewData(Visitable visitable) {
        if (visitable instanceof HotSellModel) {
            final List responseList = ((HotSellModel) visitable).getResponseList();
            if (!CollectionUtil.isEmpty(responseList)) {
                switch (responseList.size()) {
                    case 1:
                        this.mChoiceLayout.setVisibility(4);
                        this.mSerializeLayout.setVisibility(4);
                        break;
                    case 2:
                        this.mSerializeLayout.setVisibility(4);
                        this.mChoiceTextView.setText(((HotSellResponse) responseList.get(1)).getName());
                        this.mChoiceLayout.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                WebViewUtil.getInstance().getJumpUrl(HotSellViewHolder.this.mContext, ((HotSellResponse) responseList.get(1)).getUrl());
                            }
                        });
                        break;
                    case 3:
                        this.mChoiceTextView.setText(((HotSellResponse) responseList.get(1)).getName());
                        this.mSerializeTextView.setText(((HotSellResponse) responseList.get(2)).getName());
                        this.mChoiceLayout.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                WebViewUtil.getInstance().getJumpUrl(HotSellViewHolder.this.mContext, ((HotSellResponse) responseList.get(1)).getUrl());
                            }
                        });
                        this.mSerializeLayout.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                WebViewUtil.getInstance().getJumpUrl(HotSellViewHolder.this.mContext, ((HotSellResponse) responseList.get(2)).getUrl());
                            }
                        });
                        break;
                }
                this.mHotSellTextView.setText(((HotSellResponse) responseList.get(0)).getName());
                this.mHotSellLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        WebViewUtil.getInstance().getJumpUrl(HotSellViewHolder.this.mContext, ((HotSellResponse) responseList.get(0)).getUrl());
                    }
                });
                com.spriteapp.booklibrary.e.d a = b.a();
                if (a != null) {
                    this.divideView.setBackgroundColor(a.f());
                }
            }
        }
    }
}
