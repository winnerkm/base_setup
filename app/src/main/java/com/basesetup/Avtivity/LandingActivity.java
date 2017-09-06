package com.basesetup.Avtivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.basesetup.R;
import com.basesetup.fragment.HelperFragment;
import com.basesetup.model.ResponseModel;
import com.basesetup.network.ApiClient;
import com.basesetup.network.ErrorModel;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class LandingActivity extends Activity implements HelperFragment.OnFragmentInteractionListener{

    private static final String TAG = LandingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);


        openFragment();

    }

    private void openFragment() {
        Fragment fragment = new HelperFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.addToBackStack(null).add(R.id.container,fragment,fragment.getTag()).commit();

    }

    @OnClick(R.id.api_call_btn)
    void onBtnClick() {
        apiCall();
    }

    private void apiCall() {
        ApiClient.getInstance(this).apiCall("Sports", 1);
    }

    public void onEvent(ResponseModel responseModel) {
        Toast.makeText(this, responseModel.getQuery(), Toast.LENGTH_SHORT).show();
    }

    public void onEvent(ErrorModel errorModel) {
        Toast.makeText(this, errorModel.getError(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
