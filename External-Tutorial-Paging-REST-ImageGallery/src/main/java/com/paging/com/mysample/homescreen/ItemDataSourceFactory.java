package com.paging.com.mysample.homescreen;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.paging.com.mysample.pojo.Image;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Image>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Integer, Image> create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Image>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
