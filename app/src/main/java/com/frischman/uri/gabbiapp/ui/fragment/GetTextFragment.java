package com.frischman.uri.gabbiapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class GetTextFragment extends Fragment {

    FragmentGetTextBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_text, container, false);

        ArrayAdapter<String> sefersAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, SEFERS);
        mBinding.seferSpinner.setAdapter(sefersAdapter);

        ArrayAdapter<Integer> perakimAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getRange(1, MAX_NUMBER_OF_PERAKIM_IN_TORAH));
        ArrayAdapter<Integer> psukimAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getRange(1, MAX_NUMBER_OF_PSUKIM_IN_PEREK_IN_TORAH));
        mBinding.beginPerekSpinner.setAdapter(perakimAdapter);
        mBinding.endPerekSpinner.setAdapter(perakimAdapter);

        mBinding.beginPasukSpinner.setAdapter(psukimAdapter);
        mBinding.endPasukSpinner.setAdapter(psukimAdapter);

        Typeface font = createFontFromAssets(getActivity().getApplicationContext(), "torah.ttf");
        mBinding.getTextTextView.setTypeface(font);

        mBinding.getTextTextView.setMovementMethod(new ScrollingMovementMethod());

        mBinding.selectorVisibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.getTextRelativeLayout.getVisibility() == View.VISIBLE) {
                    mBinding.getTextRelativeLayout.setVisibility(View.GONE);
                } else {
                    mBinding.getTextRelativeLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        mBinding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int seferDatabaseKey;

                switch(mBinding.seferSpinner.getSelectedItem().toString()) {
                    case "Bereishit":
                        seferDatabaseKey = R.string.database_key_bereishit;
                        break;
                    case "Shmot":
                        seferDatabaseKey = R.string.database_key_shemot;
                        break;
                    case "Vayikra":
                        seferDatabaseKey = R.string.database_key_vayikra;
                        break;
                    case "Bamidbar":
                        seferDatabaseKey = R.string.database_key_bamidbar;
                        break;
                    case "Devarim":
                        seferDatabaseKey = R.string.database_key_devarim;
                        break;
                    default:
                        seferDatabaseKey = 0;
                        break;
                }

                mBinding.getTextTextView.setText("");
                List<List<String>> fullText = getRangeOfText(getString(seferDatabaseKey), (int) mBinding.beginPerekSpinner.getSelectedItem(), (int) mBinding.beginPasukSpinner.getSelectedItem(), (int) mBinding.endPerekSpinner.getSelectedItem(), (int) mBinding.endPasukSpinner.getSelectedItem());
                if (fullText == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Range Inputted", Toast.LENGTH_LONG).show();
                } else {
                    for (List<String> perek : fullText) {
                        for (String pasuk : perek) {
                            mBinding.getTextTextView.append(pasuk);
                        }
                    }
                }
            }
        });


        return mBinding.getRoot();
    }
}
