// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Checkout_ViewBinding implements Unbinder {
  private Checkout target;

  @UiThread
  public Checkout_ViewBinding(Checkout target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Checkout_ViewBinding(Checkout target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.totall = Utils.findRequiredViewAsType(source, R.id.totall, "field 'totall'", TextView.class);
    target.check = Utils.findRequiredViewAsType(source, R.id.check, "field 'check'", Button.class);
    target.exit = Utils.findRequiredViewAsType(source, R.id.exit, "field 'exit'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Checkout target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.totall = null;
    target.check = null;
    target.exit = null;
  }
}
