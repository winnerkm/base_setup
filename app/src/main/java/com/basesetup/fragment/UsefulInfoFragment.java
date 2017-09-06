package com.basesetup.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basesetup.R;

import butterknife.ButterKnife;

/**
 * Created by kuliza-234 on 06/09/17.
 */

public class UsefulInfoFragment extends Fragment {

    private static final String TAG = UsefulInfoFragment.class.getSimpleName();

    private View mRootView;

    public UsefulInfoFragment() {
        // Required empty public constructor
    }

    public static UsefulInfoFragment newInstance() {
        UsefulInfoFragment fragment = new UsefulInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.pager_fragment, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }

}
