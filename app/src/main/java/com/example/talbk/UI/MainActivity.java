package com.example.talbk.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.R;
import com.example.talbk.ViewModel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.loginbuton)
    Button btn;
    @BindView(R.id.usertxt)
    EditText userName;
    @BindView(R.id.passwardtxt)
    EditText password;
    @BindView(R.id.text)
    TextView textSignup;
    @BindView(R.id.remember)
    TextView remember;
    UserViewModel model;
    SharedPreferences sharedPreferences, mSharedPreferencesID;
    SharedPreferences.Editor editor, mEditorID;
    @BindView(R.id.progressBar1)
    ProgressBar spinner;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        spinner.setVisibility(View.GONE);
        pDialog = new ProgressDialog(this);

        pDialog.setMessage("من فضلك اننتظر...");
        toCategory();
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgetPassword.class));


            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registration()) {
                    spinner.setVisibility(View.VISIBLE);
                    model = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
                    model.getResult(userName.getText().toString(), password.getText().toString(), MainActivity.this, pDialog).observe(MainActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {

                            if (!s.equals("Failed")) {
                                spinner.setVisibility(View.VISIBLE);

                                mSharedPreferencesID = getSharedPreferences("DataID", MODE_PRIVATE);
                                mEditorID = mSharedPreferencesID.edit();
                                mEditorID.putString("id_user", s);
                                mEditorID.commit();
                                spinner.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this, Main.class);
                                intent.putExtra("iduser", s);
                                startActivity(intent);
                                finish();


                            }
                        }

                    });

                }
                spinner.setVisibility(View.GONE);

            }
        });
        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
                finish();
            }
        });

    }

    public Boolean registration() {


        String usertxt = userName.getText().toString();
        String passtxt = password.getText().toString();

        if (TextUtils.isEmpty(usertxt) || usertxt.length() != 11) {
            userName.setError("من فضلك ادخل رقم الهاتف مكون من 11 رقم ");

            userName.requestFocus();
            return false;

        }
        if (TextUtils.isEmpty(passtxt)) {
            password.setError("ادخل كلمة المرور من فضلك ");
            password.requestFocus();
            return false;
        }

        return true;

    }

    public void toCategory() {
        sharedPreferences = getSharedPreferences("DataID", MODE_PRIVATE);
        if (!sharedPreferences.getString("id_user", "").equals("")) {
            Intent intent = new Intent(MainActivity.this, Main.class);
            intent.putExtra("iduser", sharedPreferences.getString("id_user", "mahmoud"));
            startActivity(intent);
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
