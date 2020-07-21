package com.example.talbk.UI;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.Model.User;
import com.example.talbk.R;
import com.example.talbk.ViewModel.GetUserVM;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    String id;
    GetUserVM model;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.face)
    TextView face;
    LinearLayout change;
    Button logout;
    TextView help;
    AlertDialog.Builder builder;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    ProgressBar pb;


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(getActivity());
        logout = view.findViewById(R.id.logout);
        change = view.findViewById(R.id.change);
        pb = (ProgressBar) view.findViewById(R.id.progressBar1);
        name = view.findViewById(R.id.name);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        face = view.findViewById(R.id.face);
         builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        id = getActivity().getIntent().getStringExtra("iduser");
        help = view.findViewById(R.id.help);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackDialog alert = new FeedbackDialog();
                alert.showDialog(getActivity(), id);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserEdit.class);
                intent.putExtra("iduser", id);
                intent.putExtra("edit", "profile");
                startActivity(intent);
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/214910755309064"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/214910755309064")));
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
        return view;

    }

    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("هل أنت متأكد أنك تريد الخروج؟")
                .setCancelable(false)
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mSharedPreferences = getActivity().getSharedPreferences("DataID", MODE_PRIVATE);
                        mEditor = mSharedPreferences.edit();
                        mEditor.putString("id_user", "");
                        mEditor.commit();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();

                    }
                })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }

    @Override
    public void onStart() {
        super.onStart();
        model = ViewModelProviders.of(this).get(GetUserVM.class);
        model.getResult(id, getActivity(), pb, builder).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User s) {
                name.setText(s.getName());
                phone.setText(s.getPhone());
                address.setText(s.getAddress());
            }


        });

    }
}