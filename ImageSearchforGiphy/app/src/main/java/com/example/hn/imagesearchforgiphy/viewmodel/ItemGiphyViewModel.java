package com.example.hn.imagesearchforgiphy.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hn.imagesearchforgiphy.model.GiphyItem;

/**
 * Created by hn on 2017-02-08.
 */

public class ItemGiphyViewModel {
    private GiphyItem item;
    private String url;
    private Context context;

    public ItemGiphyViewModel(Context context, GiphyItem item) {
        this.item = item;
        this.url = item.getURL();
        this.context = context;
    }

    public void setItem(GiphyItem item) {
        this.item = item;
        this.url = item.getURL();
    }
    public String getUrl(){
        return this.url;
    }

    /**
     * update gif using Glide
     * */
    public void updateGif(ImageView imageView){
        Glide.with(imageView.getContext())
                .load(url)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(0.1f)
                .into(imageView);
    }
}
