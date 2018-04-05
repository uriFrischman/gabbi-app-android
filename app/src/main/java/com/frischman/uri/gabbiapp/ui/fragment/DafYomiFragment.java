package com.frischman.uri.gabbiapp.ui.fragment;


import android.app.DownloadManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentDafYomiBinding;

import net.sourceforge.zmanim.hebrewcalendar.Daf;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DafYomiFragment extends Fragment {

    private FragmentDafYomiBinding mBinding;

    private String mDafFileName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_daf_yomi, container, false);

        mBinding.webView.setVisibility(View.GONE);
        mBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        JewishCalendar calendar = new JewishCalendar();
        final Daf currentDat = calendar.getDafYomiBavli();
        mBinding.webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

                File externalPublic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File x = new File(externalPublic, mDafFileName);
                String downloadedFilePath = x.getAbsolutePath();
                if (new File(downloadedFilePath).exists()) {
                    Toast.makeText(getActivity().getApplicationContext(), "File already exists", Toast.LENGTH_SHORT).show();
                    mBinding.pdfViewer.fromFile(x).load();
                } else {
                    mBinding.webView.setVisibility(View.GONE);
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));

                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, mDafFileName);
                    DownloadManager dm = (DownloadManager) getActivity().getApplicationContext().getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Toast.makeText(getActivity().getApplicationContext(), "Downloading File: " + mDafFileName, //To notify the Client that the file is being downloaded
                            Toast.LENGTH_LONG).show();
                    mBinding.webView.stopLoading();
                }
            }

        });

        mBinding.downloadAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDafFileName = String.format("%s-%d-%s.pdf", currentDat.getMasechtaTransliterated(), currentDat.getDaf(), "a");
                mBinding.webView.reload();
                mBinding.webView.loadUrl("http://hebrewbooks.org/shas.aspx?mesechta=" + (currentDat.getMasechtaNumber() + 1) + "&daf=" + currentDat.getDaf() + "&format=pdf");
            }
        });

        mBinding.downloadBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDafFileName = String.format("%s-%d-%s.pdf", currentDat.getMasechtaTransliterated(), currentDat.getDaf(), "b");
                mBinding.webView.reload();
                mBinding.webView.loadUrl("http://hebrewbooks.org/shas.aspx?mesechta=" + (currentDat.getMasechtaNumber() + 1) + "&daf=" + currentDat.getDaf() + "b&format=pdf");
            }
        });

        return mBinding.getRoot();
    }
}
