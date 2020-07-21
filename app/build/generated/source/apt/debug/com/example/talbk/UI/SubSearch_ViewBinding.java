// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubSearch_ViewBinding implements Unbinder {
  private SubSearch target;

  @UiThread
  public SubSearch_ViewBinding(SubSearch target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubSearch_ViewBinding(SubSearch target, View source) {
    this.target = target;

    target.total = Utils.findRequiredViewAsType(source, R.id.total, "field 'total'", TextView.class);
    target.cart = Utils.findRequiredViewAsType(source, R.id.check, "field 'cart'", Button.class);
    target.number = Utils.findRequiredViewAsType(source, R.id.number, "field 'number'", TextView.class);
    target.pb = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'pb'", ProgressBar.class);
    target.search = Utils.findRequiredViewAsType(source, R.id.search, "field 'search'", EditText.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.binre, "field 'relativeLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SubSearch target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.total = null;
    target.cart = null;
    target.number = null;
    target.pb = null;
    target.search = null;
    target.relativeLayout = null;
  }
}
