// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  @UiThread
  public ProfileFragment_ViewBinding(ProfileFragment target, View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", TextView.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", TextView.class);
    target.face = Utils.findRequiredViewAsType(source, R.id.face, "field 'face'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.phone = null;
    target.address = null;
    target.face = null;
  }
}
