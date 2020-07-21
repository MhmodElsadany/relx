// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignUp_ViewBinding implements Unbinder {
  private SignUp target;

  @UiThread
  public SignUp_ViewBinding(SignUp target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignUp_ViewBinding(SignUp target, View source) {
    this.target = target;

    target.fullname = Utils.findRequiredViewAsType(source, R.id.name, "field 'fullname'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", EditText.class);
    target.mobphone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'mobphone'", EditText.class);
    target.create = Utils.findRequiredViewAsType(source, R.id.create, "field 'create'", Button.class);
    target.textSignin = Utils.findRequiredViewAsType(source, R.id.text, "field 'textSignin'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignUp target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fullname = null;
    target.password = null;
    target.address = null;
    target.mobphone = null;
    target.create = null;
    target.textSignin = null;
  }
}
