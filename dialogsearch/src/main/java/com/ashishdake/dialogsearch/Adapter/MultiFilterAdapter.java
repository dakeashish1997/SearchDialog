package com.ashishdake.dialogsearch.Adapter;

import android.view.View;

import com.ashishdake.dialogsearch.Holder.FilterHolder;
import com.ashishdake.dialogsearch.Model.FilterItem;
import com.ashishdake.dialogsearch.R;

import java.util.List;

public class MultiFilterAdapter extends FilterAdapter{

    public MultiFilterAdapter(List<FilterItem> cacheData) {
        super(cacheData);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_filter_item;
    }

    @Override
    public void onCustomBindViewHolder(FilterHolder holder, FilterItem model, int position) {
        holder.bind(model);
    }

    @Override
    public FilterHolder onCustomCreateViewHolder(View view) {
        return new FilterHolder(view);
    }

    @Override
    public boolean isMainListenerDisable() {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void onClickListener(View view, int position) {
        selectEvent(position);
    }
}