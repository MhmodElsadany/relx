package com.example.talbk.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.TalbkAdapter;
import com.example.talbk.Model.UserOrder;
import com.example.talbk.R;
import com.example.talbk.ViewModel.OrderUsersVM;

import java.util.ArrayList;


public class TalbkFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    TalbkAdapter mCustomAdapter;
    ArrayList<UserOrder> s = new ArrayList<>();
    ProgressBar pb;
    String id;
    OrderUsersVM model;
    TextView notfound;


    public TalbkFragment() {
        // Required empty public constructor
    }


    public static TalbkFragment newInstance(String param1, String param2) {
        TalbkFragment fragment = new TalbkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_talbk, container, false);
        pb = (ProgressBar) view.findViewById(R.id.progressBar1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        id = getActivity().getIntent().getStringExtra("iduser");
        notfound = view.findViewById(R.id.notfound);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        int resId = R.anim.layout_animation;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
        recyclerView.setAdapter(mCustomAdapter);
        model = ViewModelProviders.of(this).get(OrderUsersVM.class);
        model.getResult(getActivity(), pb, builder, id).observe(getActivity(), new Observer<ArrayList<UserOrder>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserOrder> s) {

                if (s.size() > 0) {
                    mCustomAdapter = new TalbkAdapter(getActivity(), s);
                    recyclerView.setAdapter(mCustomAdapter);
                    recyclerView.setLayoutAnimation(animation);

                } else {
                    notfound.setVisibility(View.VISIBLE);

                }
            }
        });
        return view;
    }


}
