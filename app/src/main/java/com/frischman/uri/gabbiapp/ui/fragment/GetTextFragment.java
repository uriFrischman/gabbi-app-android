package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.FragmentGetTextBinding;

import java.util.List;

import static com.frischman.uri.gabbiapp.utility.FontUtil.createFontFromAssets;
import static com.frischman.uri.gabbiapp.utility.IntegerUtil.getRange;
import static com.frischman.uri.gabbiapp.utility.TorahUtil.MAX_NUMBER_OF_PERAKIM_IN_TORAH;
import static com.frischman.uri.gabbiapp.utility.TorahUtil.MAX_NUMBER_OF_PSUKIM_IN_PEREK_IN_TORAH;
import static com.frischman.uri.gabbiapp.utility.TorahUtil.SEFERS;
import static com.frischman.uri.gabbiapp.utility.TorahUtil.getRangeOfText;
import static com.frischman.uri.gabbiapp.utility.ViewUtil.hideOrShow;

public class GetTextFragment extends Fragment {

    private FragmentGetTextBinding mBinding;

    private boolean mHasVowels;
    private String mCurrentSefer;
    private int mCurrentBeginPerek;
    private int mCurrentBeginPasuk;
    private int mCurrentEndPerek;
    private int mCurrentEndPasuk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_text, container, false);



        initializeAdapters();
        initializeFonts();
        initializeOnClickListeners();
        initializeScrolling();

        setFabButtonVisibility(View.GONE);

        if (getArguments() != null) {
            Bundle arguments = getArguments();
            String reading = arguments.getString(getString(R.string.arg_key_reading));
            Toast.makeText(getContext(), reading, Toast.LENGTH_LONG).show();

            String[] elements = reading.split(" ");
            mCurrentSefer = elements[0];
            String[] beginPerekAndPasuk = elements[1].split(":");
            mCurrentBeginPerek = Integer.valueOf(beginPerekAndPasuk[0]);
            mCurrentBeginPasuk =  Integer.valueOf( beginPerekAndPasuk[1]);
            String[] endPerekAndPasuk = elements[3].split(":");
            mCurrentEndPerek = Integer.valueOf(endPerekAndPasuk[0]);
            mCurrentEndPasuk = Integer.valueOf(endPerekAndPasuk[1]);
            setTextViewWithTorahText(mBinding.getTextTextView, mCurrentSefer, mCurrentBeginPerek, mCurrentBeginPasuk, mCurrentEndPerek, mCurrentEndPasuk, mHasVowels);
            hideOrShow(mBinding.getTextSpinnersContainer);
        }

        return mBinding.getRoot();
    }

    private void initializeAdapters() {
        ArrayAdapter<String> sefersAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, SEFERS);
        ArrayAdapter<Integer> perakimAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getRange(1, MAX_NUMBER_OF_PERAKIM_IN_TORAH));
        ArrayAdapter<Integer> psukimAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getRange(1, MAX_NUMBER_OF_PSUKIM_IN_PEREK_IN_TORAH));

        mBinding.seferSpinner.setAdapter(sefersAdapter);

        mBinding.beginPerekSpinner.setAdapter(perakimAdapter);
        mBinding.endPerekSpinner.setAdapter(perakimAdapter);

        mBinding.beginPasukSpinner.setAdapter(psukimAdapter);
        mBinding.endPasukSpinner.setAdapter(psukimAdapter);
    }

    private void initializeScrolling() {
        mBinding.getTextTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initializeFonts() {
        mBinding.getTextTextView.setTypeface(createFontFromAssets(getActivity().getApplicationContext(), "torah.ttf"));
    }

    private void initializeOnClickListeners() {

        mBinding.selectorVisibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideOrShow(mBinding.getTextSpinnersContainer);
            }
        });

        mBinding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewWithTorahText(mBinding.getTextTextView, mBinding.seferSpinner.getSelectedItem().toString(), (int) mBinding.beginPerekSpinner.getSelectedItem(), (int) mBinding.beginPasukSpinner.getSelectedItem(), (int) mBinding.endPerekSpinner.getSelectedItem(), (int) mBinding.endPasukSpinner.getSelectedItem());
            }
        });

    }

    private void setTextViewWithTorahText(TextView textView, String sefer, int beginPerek, int beginPasuk, int endPerek, int endPasuk) {
        int seferDatabaseKey;

        switch(sefer) {
            case "Genesis":
                seferDatabaseKey = R.string.database_key_bereishit;
                break;
            case "Exodus":
                seferDatabaseKey = R.string.database_key_shemot;
                break;
            case "Leviticus":
                seferDatabaseKey = R.string.database_key_vayikra;
                break;
            case "Numbers":
                seferDatabaseKey = R.string.database_key_bamidbar;
                break;
            case "Deuteronomy":
                seferDatabaseKey = R.string.database_key_devarim;
                break;
            default:
                seferDatabaseKey = 0;
                break;
        }

        textView.setText("");
        List<List<String>> fullText = getRangeOfText(getString(seferDatabaseKey), beginPerek, beginPasuk, endPerek, endPasuk);
        if (fullText == null) {
            Toast.makeText(getActivity().getApplicationContext(), "Invalid Range Inputted", Toast.LENGTH_LONG).show();
        } else {
            for (List<String> perek : fullText) {
                for (String pasuk : perek) {
                    textView.append(pasuk);
                }
            }
        }
    }
}
