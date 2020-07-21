package com.example.talbk.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.Model.User;
import com.example.talbk.R;
import com.example.talbk.ViewModel.GetUserVM;
import com.example.talbk.ViewModel.UpdateViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserEdit extends AppCompatActivity {
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
    GetUserVM model;
    String id;
    @BindView(R.id.progressBar1)
    ProgressBar pb;
    String passworduser;
    UpdateViewModel updatemodel;
    ProgressDialog pDialog;
    @BindView(R.id.text)
    TextView text;
    User user;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    String oldphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("iduser");
        title.setText("تعديل البيانات");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("من فضلك اننتظر...");

        model = ViewModelProviders.of(this).get(GetUserVM.class);
        model.getResult(id, this, pb, builder).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User s) {
                fullname.setText(s.getName());
                mobphone.setText(s.getPhone());
                address.setText(s.getAddress());
                passworduser = s.getPassword();
                oldphone = s.getPhone();
            }


        });
        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    Intent intent = new Intent(UserEdit.this, Address.class);
                    startActivityForResult(intent, 1);

                }
                return false;

            }

        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassDialog alert = new ChangePassDialog();
                user = new User(id, fullname.getText().toString(), mobphone.getText().toString(), address.getText().toString(),
                        passworduser);

                alert.showDialog(UserEdit.this, user, oldphone);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_element()) {
                    if (passworduser.equals(password.getText().toString())) {
                        user = new User(id, fullname.getText().toString(), mobphone.getText().toString(), address.getText().toString(),
                                password.getText().toString());

                        updatemodel = ViewModelProviders.of(UserEdit.this).get(UpdateViewModel.class);
                        updatemodel.getResult(user, oldphone, UserEdit.this, pDialog).observe(UserEdit.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {

                            }
                        });


                    } else {
                        password.setError("كلمة المرور التى ادخلتها غير صحيحة ");
                        password.requestFocus();

                    }

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
        if (getpassword.isEmpty()) {
            password.setError("من فضلك ادخل كلمة المرور القديمة");
            password.requestFocus();
            result = false;
        }

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("address");
                address.setText(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


}
