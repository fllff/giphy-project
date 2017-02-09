package com.example.hn.imagesearchforgiphy;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hn.imagesearchforgiphy.databinding.ItemGiphyBinding;
import com.example.hn.imagesearchforgiphy.model.GiphyItem;
import com.example.hn.imagesearchforgiphy.viewmodel.ItemGiphyViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by hn on 2017-02-08.
 */

public class GiphyAdapter extends RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder> {

    private List<GiphyItem> giphyItems;
    private static Context context;

    public GiphyAdapter(Context context) {
        this.giphyItems = Collections.emptyList();
        this.context = context;
    }

    public void setGiphyItems(List<GiphyItem> giphyItems) {
        this.giphyItems = giphyItems;
    }

    @Override
    public GiphyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGiphyBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_giphy,
                parent,
                false);
        return new GiphyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GiphyViewHolder holder, int position) {
        //holder.bindItem(giphyItems.get(position));
        holder.bindItem(giphyItems.get(position));

    }

    @Override
    public int getItemCount() {
        return giphyItems.size();
    }

    public static class GiphyViewHolder extends RecyclerView.ViewHolder {
        private final ItemGiphyBinding binding;

        public GiphyViewHolder(ItemGiphyBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        public void bindItem(GiphyItem item) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemGiphyViewModel(itemView.getContext(), item));
            } else {
                binding.getViewModel().setItem(item);
            }
            binding.getViewModel().updateGif(binding.gifImage);

        }
    }
}
