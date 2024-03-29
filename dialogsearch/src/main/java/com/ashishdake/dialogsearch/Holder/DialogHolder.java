package com.ashishdake.dialogsearch.Holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishdake.dialogsearch.Adapter.FilterAdapter;
import com.ashishdake.dialogsearch.Adapter.MultiFilterAdapter;
import com.ashishdake.dialogsearch.Adapter.SingleFilterAdapter;
import com.ashishdake.dialogsearch.Model.AdapterListener;
import com.ashishdake.dialogsearch.Model.DialogListener;
import com.ashishdake.dialogsearch.Model.FilterItem;
import com.ashishdake.dialogsearch.Model.FilterType;
import com.ashishdake.dialogsearch.R;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DialogHolder extends RecyclerView.ViewHolder implements AdapterListener, View.OnClickListener {
    private EditText searchEdt;
    private RecyclerView filterRecycler;
    private TextView toolbar_clear;
    private TextView toolbat_title;
    private TextView selectBtn;
    private LinearLayout toolbar_back;
    private DialogListener.Single listenerSingle;
    private DialogListener.Multiple listenerMultiple;
    private FilterType filterType;
    private int selectableCount;

    @SuppressLint("CheckResult")
    public DialogHolder(View itemView) {
        super(itemView);

        initUi(itemView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.scrollToPosition(0);

        filterRecycler.setLayoutManager(layoutManager);
        filterRecycler.setHasFixedSize(true);
        filterRecycler.setItemAnimator(new DefaultItemAnimator());

        RxTextView.textChanges(searchEdt)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    getAdapter().filter(charSequence.toString());
                });
    }

    private void initUi(View itemView) {
        searchEdt = itemView.findViewById(R.id.searchEdt);
        filterRecycler = itemView.findViewById(R.id.filterList);
        toolbar_clear = itemView.findViewById(R.id.toolbar_clear);
        toolbar_back = itemView.findViewById(R.id.toolbar_back);
        toolbat_title = itemView.findViewById(R.id.toolbat_title);
        selectBtn = itemView.findViewById(R.id.selectBtn);

        toolbar_clear.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
    }

    public void setListenerSingle(DialogListener.Single listenerSingle) {
        this.listenerSingle = listenerSingle;
    }

    public DialogListener.Single getListenerSingle() {
        return listenerSingle;
    }

    public void setFilterList(List<FilterItem> filterList) {
        filterRecycler.setAdapter(createFilterAdapter(filterList));
    }

    private FilterAdapter createFilterAdapter(List<FilterItem> filterList) {
        selectBtn.setVisibility(View.GONE);
        if (filterType == FilterType.Single){
            SingleFilterAdapter adapter = new SingleFilterAdapter(filterList);
            adapter.setListener(this);
            adapter.setSelectableCount(selectableCount);
            return adapter;
        }else{
            MultiFilterAdapter adapter = new MultiFilterAdapter(filterList);
            adapter.setListener(this);
            adapter.setSelectableCount(selectableCount);
            selectBtn.setVisibility(View.VISIBLE);
            return adapter;
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        if(listenerSingle != null && filterType == FilterType.Single)
            listenerSingle.onResult(getAdapter().getItemFromPosition(position));
    }

    public void setOnCloseListener(View.OnClickListener onCloseListener) {
        toolbar_back.setOnClickListener(onCloseListener);
    }

    public DialogHolder setToolbarTitle(String text) {
        toolbat_title.setText(text);
        return this;
    }

    public DialogHolder setSearchBoxHint(String text) {
        searchEdt.setHint(text);
        return this;
    }

    public DialogHolder setSelectableCount(int selectableCount) {
        this.selectableCount = selectableCount;
        return this;
    }

    public void setListenerMultiple(DialogListener.Multiple listenerMultiple) {
        this.listenerMultiple = listenerMultiple;
    }

    private FilterAdapter getAdapter(){
        if (filterType == FilterType.Multiple)
            return (MultiFilterAdapter) filterRecycler.getAdapter();
        else
            return (SingleFilterAdapter) filterRecycler.getAdapter();
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_clear) {
            searchEdt.setText("");
        }else if (view.getId() == R.id.selectBtn){
            onSelect();
        }
    }

    private void onSelect() {
        if(listenerMultiple != null && filterType == FilterType.Multiple)
            listenerMultiple.onResult(getAdapter().getSelectedData());
    }

    public DialogHolder setSelectButton(String selectButtonText) {
        selectBtn.setText(selectButtonText);
        return this;
    }

    public void setBackButtonVisible(boolean value){
        toolbar_back.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
    }
}
