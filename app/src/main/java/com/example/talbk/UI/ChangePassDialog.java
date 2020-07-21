package com.example.talbk.UI;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.Model.User;
import com.example.talbk.R;
import com.example.talbk.ViewModel.UpdateViewModel;

public class ChangePassDialog {
    EditText oldpass;
    EditText newpass;
    Button confirm;
    ImageView exit;
    UpdateViewModel updatemodel;
    ProgressDialog pDialog;

    public void showDialog(final Activity activity, User user, String oldphone) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.updaepassword);
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("من فضلك اننتظر...");
        oldpass = dialog.findViewById(R.id.oldpass);
        newpass = dialog.findViewById(R.id.newpass);
        confirm = dialog.findViewById(R.id.confirm);
        exit = dialog.findViewById(R.id.exit);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check_element()) {
                    if (user.getPassword().toString().equals(oldpass.getText().toString())) {
                        user.setPassword(newpass.getText().toString());
                        updatemodel = ViewModelProviders.of((FragmentActivity) activity).get(UpdateViewModel.class);
                        updatemodel.getResult(user, oldphone, activity, pDialog).observe((FragmentActivity) activity, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {

                            }
                        });

                    }
                }
            }
        });

        dialog.show();

    }

    public boolean check_element() {
        boolean result = true;
        String getoldname = oldpass.getText().toString();
        String getnewpassword = newpass.getText().toString();

        if (getoldname.isEmpty()) {
            oldpass.setError("من فضلك ادخل الاسم");
            oldpass.requestFocus();
            result = false;
        }
        if (getnewpassword.isEmpty() || getnewpassword.length() < 3) {
            newpass.setError("من فضلك اضغط هنا لادخال العنوان");
            newpass.requestFocus();
            result = false;
        }
        return result;

    }

}



