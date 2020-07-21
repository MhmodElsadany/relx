// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserEdit_ViewBinding implements Unbinder {
  private UserEdit target;

  @UiThread
  public UserEdit_ViewBinding(UserEdit target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserEdit_ViewBinding(UserEdit target, View source) {
    this.target = target;

    target.fullname = Utils.findRequiredViewAsType(source, R.id.name, "field 'fullname'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", EditText.class);
    target.mobphone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'mobphone'", EditText.class);
    target.create = Utils.findRequiredViewAsType(source, R.id.create, "field 'create'", Button.class);
    target.pb = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'pb'", ProgressBar.class);
    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserEdit target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fullname = null;
    target.password = null;
    target.address = null;
    target.mobphone = null;
    target.create = null;
    target.pb = null;
    target.text = null;
    target.toolbar = null;
    target.back = null;
    target.title = null;
  }
}
