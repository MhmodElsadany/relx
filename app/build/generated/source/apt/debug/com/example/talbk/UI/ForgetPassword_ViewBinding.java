// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPassword_ViewBinding implements Unbinder {
  private ForgetPassword target;

  @UiThread
  public ForgetPassword_ViewBinding(ForgetPassword target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPassword_ViewBinding(ForgetPassword target, View source) {
    this.target = target;

    target.btn = Utils.findRequiredViewAsType(source, R.id.loginbuton, "field 'btn'", Button.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPassword target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn = null;
    target.phone = null;
  }
}
