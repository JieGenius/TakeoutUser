package com.example.genius.takeoutuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class UserCenterFragment extends Fragment {

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_address_ib)
    ImageButton userAddressIb;
    @BindView(R.id.user_phone)
    TextView userPhone;
    Unbinder unbinder;

    public UserCenterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_center, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.user_address_ib)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(),UserAddressDetailActivity.class));
    }
    public void updateData(UserInfo userInfo){
        if(userName!=null){
            userName.setText(userInfo.getName());
            userPhone.setText(userInfo.getPhone());
        }

    }
}
