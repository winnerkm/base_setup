package com.basesetup.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basesetup.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class HelperFragment extends Fragment {

    private static final String TAG = HelperFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    private OnFragmentInteractionListener mListener;
    private Context mContext;
    private View mRootView;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.sliding_tabs)
    TabLayout mTabLayout;

    public HelperFragment() {
        // Required empty public constructor
    }

    public static HelperFragment newInstance(String param1, String param2) {
        HelperFragment fragment = new HelperFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_helper, container, false);
        ButterKnife.bind(this,mRootView);

        setupPager();

        return mRootView;
    }

    private void setupPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new UsefulInfoFragment().newInstance(), "Frag1");
        adapter.addFragment(UsefulInfoFragment.newInstance(), "Frag4");

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onResume() {
        super.onResume();
//            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
//            EventBus.getDefault().unregister(this);
    }


    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
    * Deprecated on API 23
    * Use onAttachToContext instead
    */


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        mContext = context;
        Activity activity = (Activity) context;
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
