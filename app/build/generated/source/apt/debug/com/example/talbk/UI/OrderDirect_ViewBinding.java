// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderDirect_ViewBinding implements Unbinder {
  private OrderDirect target;

  @UiThread
  public OrderDirect_ViewBinding(OrderDirect target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDirect_ViewBinding(OrderDirect target, View source) {
    this.target = target;

    target.txt_dia = Utils.findRequiredViewAsType(source, R.id.txt_dia, "field 'txt_dia'", EditText.class);
    target.btn_submit = Utils.findRequiredViewAsType(source, R.id.btn_submit, "field 'btn_submit'", Button.class);
    target.exit = Utils.findRequiredViewAsType(source, R.id.exit, "field 'exit'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderDirect target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txt_dia = null;
    target.btn_submit = null;
    target.exit = null;
  }
}
