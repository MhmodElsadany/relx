package com.example.talbk.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.Model.User;
import com.example.talbk.R;
import com.example.talbk.ViewModel.SignupViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.name)
    EditText fullname;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.phone)
    EditText mobphone;
    @BindView(R.id.create)
    Button create;
    @BindView(R.id.text)
    TextView textSignin;
    User user;
    SignupViewModel model;
    ProgressDialog pDialog;
    String addressMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("من فضلك اننتظر...");

        textSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
                finish();

            }
        });

        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    Intent intent = new Intent(SignUp.this, Address.class);
                    startActivityForResult(intent, 1);

                }
                return false;

            }

        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User(fullname.getText().toString(), mobphone.getText().
                        toString(), address.getText().toString(), password.getText().toString());
                if (check_element()) {
                    model = ViewModelProviders.of(SignUp.this).get(SignupViewModel.class);
                    model.getResult(user, SignUp.this, pDialog).observe(SignUp.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {

                        }
                    });
                }
            }
        });
    }


    public boolean check_element() {
        boolean result = true;
        String getfullname = fullname.getText().toString();
        String getpassword = password.getText().toString();
        String getmobphone = mobphone.getText().toString();
        String getaddress = address.getText().toString();

        if (getfullname.isEmpty()) {
            fullname.setError("من فضلك ادخل الاسم");
            fullname.requestFocus();
            result = false;
        }
        if (getaddress.isEmpty() || getaddress.length() < 3) {
            address.setError("من فضلك اضغط هنا لادخال العنوان");
            address.requestFocus();
            result = false;
        }

        if (getmobphone.isEmpty() || getmobphone.length() != 11) {
            mobphone.setError("من فضلك ادخل رقم الهاتف مكون من 11 رقم ");
            mobphone.requestFocus();
            result = false;
        }
        if (getpassword.isEmpty() || getpassword.length() < 6) {
            password.setError("من فضلك ادخل كلمة اكثر من 6 احرف");
            password.requestFocus();
            result = false;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUp.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("address");
                address.setText(result);
            }
        }
    }
}
