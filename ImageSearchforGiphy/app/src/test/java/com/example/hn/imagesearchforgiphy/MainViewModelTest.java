package com.example.hn.imagesearchforgiphy;

import android.content.Context;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hn.imagesearchforgiphy.viewmodel.MainViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static  org.junit.Assert.assertEquals;

/**
 * Created by hn on 2017-02-08.
 */

public class MainViewModelTest {

    MainViewModel mainViewModel;
    MainViewModel.DataListener dataListener;
    Context context;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        dataListener = mock(MainViewModel.DataListener.class);
        context = mock(Context.class);
        mainViewModel = spy(new MainViewModel(context,dataListener));

       /* mainViewModel = spy(new MainViewModel(context,dataListener){
            public Toast getToast(){
                return new Toast();
            }
        });*/
    }

    @Test
    public void shouldHideProgressBar() {
        ImageView iv = mock(ImageView.class);
        mainViewModel.setSearchString("abc");
        mainViewModel.setImm(mock(InputMethodManager.class));
        mainViewModel.setToast(mock(Toast.class));
        mainViewModel.onClickSearchBtn(iv);
        assertEquals(mainViewModel.progressVisibility.get(), View.GONE);
    }

    @Test
    public void shouldUpdateString() {
        TextWatcher tw = mock(TextWatcher.class);
        tw.onTextChanged(eq("string"),eq(1),eq(1),eq(1));
        verify(mainViewModel).setSearchString(any(String.class));
    }

    @Test
   public void shouldDatalistenerCallback() {

   }

    @Test
    public void shouldRequestData(){

    }

}
