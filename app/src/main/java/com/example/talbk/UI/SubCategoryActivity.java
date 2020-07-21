package com.example.talbk.UI;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.SubCustomAdapter;
import com.example.talbk.Model.CateogryModel;
import com.example.talbk.Model.SubCatregoryModel;
import com.example.talbk.R;
import com.example.talbk.ViewModel.SubVM;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends AppCompatActivity {
    SubVM model;
    SubCustomAdapter mCustomAdapter;
    ArrayList<CateogryModel> s = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar1)
    ProgressBar pb;
    @BindView(R.id.notfound)
    TextView notfound;
    @BindView(R.id.title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AlertDialog.Builder builder = new AlertDialog.Builder(SubCategoryActivity.this);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        title.setText(getIntent().getStringExtra("title"));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        int resId = R.anim.layout_animation;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);

        recyclerView.setLayoutAnimation(animation);
        model = ViewModelProviders.of(this).get(SubVM.class);
        model.getResult(this, getIntent().getStringExtra("id"), pb, builder).observe(this, new Observer<ArrayList<SubCatregoryModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SubCatregoryModel> s) {
                if (s.size() > 0) {
                    mCustomAdapter = new SubCustomAdapter(SubCategoryActivity.this, s);
                    recyclerView.setAdapter(mCustomAdapter);
                    recyclerView.setLayoutAnimation(animation);
                } else {
                    notfound.setVisibility(View.VISIBLE);

                }
            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
