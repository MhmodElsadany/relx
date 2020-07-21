// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.btn = Utils.findRequiredViewAsType(source, R.id.loginbuton, "field 'btn'", Button.class);
    target.userName = Utils.findRequiredViewAsType(source, R.id.usertxt, "field 'userName'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.passwardtxt, "field 'password'", EditText.class);
    target.textSignup = Utils.findRequiredViewAsType(source, R.id.text, "field 'textSignup'", TextView.class);
    target.remember = Utils.findRequiredViewAsType(source, R.id.remember, "field 'remember'", TextView.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'spinner'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn = null;
    target.userName = null;
    target.password = null;
    target.textSignup = null;
    target.remember = null;
    target.spinner = null;
  }
}
