package com.example.hn.imagesearchforgiphy.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hn.imagesearchforgiphy.GiphyAdapter;
import com.example.hn.imagesearchforgiphy.R;
import com.example.hn.imagesearchforgiphy.databinding.MainActivityBinding;
import com.example.hn.imagesearchforgiphy.model.GiphyItem;
import com.example.hn.imagesearchforgiphy.viewmodel.MainViewModel;

import java.util.List;

/**
 * Created by hn on 2017-02-08.
 */

public class MainActivity extends AppCompatActivity implements  MainViewModel.DataListener {

    private MainActivityBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        setupRecyclerView(binding.recyclerView);
        setupViewModel();
    }

    private void setupViewModel() {
        viewModel = new MainViewModel(this,this);
        binding.setViewModel(viewModel);
    }
    private void setupRecyclerView(RecyclerView recyclerView) {
        GiphyAdapter adapter = new GiphyAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * for recycler view adapter
     */
    public void onRepositoriesChanged(List<GiphyItem> repositories) {
        GiphyAdapter adapter =
                (GiphyAdapter) binding.recyclerView.getAdapter();
        adapter.setGiphyItems(repositories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGiphyItemListChanged(List<GiphyItem> giphyItemList) {
        GiphyAdapter adapter =
                (GiphyAdapter) binding.recyclerView.getAdapter();
        adapter.setGiphyItems(giphyItemList);
        adapter.notifyDataSetChanged();
    }
}


