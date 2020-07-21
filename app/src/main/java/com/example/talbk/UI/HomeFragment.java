package com.example.talbk.UI;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.CustomAdapter;
import com.example.talbk.Adapter.ProductCustomAdapter;
import com.example.talbk.Model.CateogryModel;
import com.example.talbk.R;
import com.example.talbk.ViewModel.CategoryVM;

import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    CategoryVM model;
    CustomAdapter mCustomAdapter;
    ArrayList<CateogryModel> s = new ArrayList<>();
    ProgressBar pb;
    ImageView order;
    ImageView bin;
    ImageView searchView;
    ImageView offer, help;
    TextView number;
    RelativeLayout relativeLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {

        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(getActivity());
        pb = (ProgressBar) view.findViewById(R.id.progressBar1);
        order = view.findViewById(R.id.fab);
        searchView = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recycler_view);
        bin = view.findViewById(R.id.bin);
        number = view.findViewById(R.id.number);
        relativeLayout = view.findViewById(R.id.binre);
        offer = view.findViewById(R.id.offer);
        help = view.findViewById(R.id.help);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Offers.class));

            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CartActivity.class));

            }
        });


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > 20) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(getActivity());
                    startActivity(new Intent(getActivity(), SearchActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(getActivity(), SearchActivity.class));
                }

            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        model = ViewModelProviders.of(this).get(CategoryVM.class);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), OrderDirect.class));
            }
        });
        model.getResult(getActivity(), pb, builder).observe(getActivity(), new Observer<ArrayList<CateogryModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CateogryModel> s) {
                mCustomAdapter = new CustomAdapter(getActivity(), s);
                recyclerView.setAdapter(mCustomAdapter);
            }


        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        number.setText(ProductCustomAdapter.oreders.size() + "");

    }


}
