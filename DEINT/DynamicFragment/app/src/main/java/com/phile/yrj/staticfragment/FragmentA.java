package com.phile.yrj.staticfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


public class FragmentA extends Fragment {
    Button btSet;
    SeekBar sbFragment;
    EditText etText;
    FragmentIterationListener mCallback;
    public static final String TAG_FRAGMENTA = "fragmenta";

    public interface FragmentIterationListener{
        void onFragmentIterationListener(String text, int size);
    }

    boolean higher23 = false;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!higher23) {
            try {
                mCallback = (FragmentIterationListener) context;
            } catch (ClassCastException ex) {
                throw new ClassCastException(context.toString() + " must implement FragmentIterationListener");
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach((Activity)activity);
        higher23 = true;
        try{
            mCallback = (FragmentIterationListener)activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString()+ " must implement FragmentIterationListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_fragment_a,container,false);
        if (rootView != null) {
            etText = (EditText) rootView.findViewById(R.id.etText);
            btSet = (Button) rootView.findViewById(R.id.btSet);
            sbFragment = (SeekBar) rootView.findViewById(R.id.sbFragment);
            btSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onFragmentIterationListener(etText.getText().toString(), (sbFragment.getProgress() + 15));
                }
            });
        }
        return rootView;
    }
}
