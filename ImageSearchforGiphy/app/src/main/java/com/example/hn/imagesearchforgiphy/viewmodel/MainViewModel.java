package com.example.hn.imagesearchforgiphy.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.hn.imagesearchforgiphy.model.GiphyItem;
import com.example.hn.imagesearchforgiphy.model.GiphyModel;

import java.util.List;

/**
 * Created by hn on 2017-02-08.
 */

public class MainViewModel {
    private Context context;
    private GiphyModel giphyModel;
    private String searchString;
    private DataListener dataListener;
    private Toast toast;
    private InputMethodManager imm;

    public ObservableInt progressVisibility;

    List<GiphyItem> giphyItemList;

    public MainViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
        giphyModel = new GiphyModel();
        progressVisibility = new ObservableInt(View.GONE);
        setImm((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));

        setToast(new Toast(context));

        String trendingUrl = "http://api.giphy.com/v1/gifs/trending?api_key=dc6zaTOxFJmzC&limit=10";
        requestGiphy(trendingUrl);
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    public void setImm(InputMethodManager imm) {
        this.imm = imm;
    }
    public Toast getToast() {return toast;}
    public void setToast(Toast t) {this.toast = t;}

    /**
     * on click search button
     * */
    public void onClickSearchBtn(View view) {
        if(searchString != null) {
            String query = searchString.replace(' ', '+');
            String searchUrl = "http://api.giphy.com/v1/gifs/search?q=" + query + "&api_key=dc6zaTOxFJmzC&limit=100";
            requestGiphy(searchUrl, view);
        }
     }

    /**
    * request data
    * */
    private void requestGiphy(String searchUrl, View view) {
        progressVisibility.set(View.VISIBLE);

        giphyItemList = null;
        giphyItemList = giphyModel.request(searchUrl);

        dataListener.onGiphyItemListChanged(giphyItemList);

        toast.makeText(context,"found " + giphyItemList.size() + " images",Toast.LENGTH_SHORT).show();
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        progressVisibility.set(View.GONE);
    }

    private void requestGiphy(String searchUrl) {
        progressVisibility.set(View.VISIBLE);

        giphyItemList = null;
        giphyItemList = giphyModel.request(searchUrl);

        dataListener.onGiphyItemListChanged(giphyItemList);

        toast.makeText(context, giphyItemList.size() + " trending images",Toast.LENGTH_SHORT).show();
        progressVisibility.set(View.GONE);
    }


    /**
    * set edit text watcher
    */
    public TextWatcher searchEditTextWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setSearchString(s.toString());
                 }

            @Override
            public void afterTextChanged(Editable s) {
           }
        };
    }

    public interface DataListener {
        void onGiphyItemListChanged(List<GiphyItem> giphyItemList);
    }

    /*
    public void onSearchAction(TextView view, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String searchString = view.getText().toString();
            if (searchString.length() > 0) {
                String searchUrl = "http://api.giphy.com/v1/gifs/search?q=" + searchString + "&api_key=dc6zaTOxFJmzC&limit=100";
                requestGiphy(searchUrl, view);
            }
        }
    }
*/

}
