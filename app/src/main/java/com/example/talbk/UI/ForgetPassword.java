package com.example.talbk.UI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.R;
import com.example.talbk.ViewModel.ForgetVM;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPassword extends AppCompatActivity {
    @BindView(R.id.loginbuton)
    Button btn;
    @BindView(R.id.phone)
    EditText phone;
    ForgetVM model;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("من فضلك اننتظر...");
        model = ViewModelProviders.of(ForgetPassword.this).get(ForgetVM.class);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registration()) {

                    model.getResult(phone.getText().toString(), ForgetPassword.this, pDialog).observe(ForgetPassword.this, new Observer<String>() {
                        @Override
                        public void onChanged(@Nullable String s) {
                            if (s.equals("Phone Number Not Exist")) {
                                Toast.makeText(ForgetPassword.this, "رقم الهاتف الذى ادخلته غير موجود ", Toast.LENGTH_SHORT).show();
                            } else if (s.equals("Waiting For Our Call")) {
                                Toast.makeText(ForgetPassword.this, " تم الطلب من قبل جارى التنفيذ ", Toast.LENGTH_SHORT).show();
                                finish();

                            } else if (s.equals("We Will Call You To Reset Your Password")) {
                                Toast.makeText(ForgetPassword.this, "سوف يتم التواصل معك لتزكيرك كلمة المرور", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                    });
                }
            }
        });
    }

    public Boolean registration() {


        String usertxt = phone.getText().toString();

        if (TextUtils.isEmpty(usertxt)) {
            phone.setError("ادخل رقم الهاتف من فضلك");

            phone.requestFocus();
            return false;

        }

        return true;

    }

}
