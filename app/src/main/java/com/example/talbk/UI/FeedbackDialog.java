package com.example.talbk.UI;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.Model.Feedback;
import com.example.talbk.R;
import com.example.talbk.ViewModel.FeedbackVM;

public class FeedbackDialog {
    FeedbackVM model;
    Feedback feedback = new Feedback();
    EditText msg;
    ProgressDialog pDialog;


    public void showDialog(final Activity activity, String id) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.feedback);
        msg = dialog.findViewById(R.id.txt_dia);
        dialog.setCanceledOnTouchOutside(true);
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("من فضلك اننتظر...");
        feedback.setId(id);
        feedback.setMessage(msg.getText().toString());
        final EditText text = (EditText) dialog.findViewById(R.id.txt_dia);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msg.getText().toString().equals("")) {
                    Toast.makeText(activity, "من فضلك ادخل طلبك لمساعدتك", Toast.LENGTH_SHORT).show();
                } else {
                    model = ViewModelProviders.of((FragmentActivity) activity).get(FeedbackVM.class);
                    model.getResult(feedback, activity, pDialog).observe((FragmentActivity) activity, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {

                        }
                    });
                }

                dialog.dismiss();

            }
        });
        dialog.show();

    }
}

