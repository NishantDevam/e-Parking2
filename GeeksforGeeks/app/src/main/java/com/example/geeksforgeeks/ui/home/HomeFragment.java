package com.example.geeksforgeeks.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.geeksforgeeks.R;
import com.example.geeksforgeeks.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final WebView homeWebView = binding.idWebViewHome;
        final ProgressBar loadingPB=binding.idPBLoading;

        homeWebView.loadUrl("https://www.geeksforgeeks.org/");
        homeWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingPB.setVisibility(View.VISIBLE);//This is before the starting of the home page the progressBar is visible
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingPB.setVisibility(View.GONE);//After loading of the home page the visibilty of progressBar is gone.
            }
        });

        homeWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN) {
                    switch (keyCode){
                        case KeyEvent.KEYCODE_BACK:
                            if(homeWebView.canGoBack()){//This is used to get back from the home page to the previous page.
                                homeWebView.goBack();
                            }
                    }
                }
                return false;
            }
            });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}