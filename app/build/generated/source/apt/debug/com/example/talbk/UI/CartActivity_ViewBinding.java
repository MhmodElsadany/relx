// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CartActivity_ViewBinding implements Unbinder {
  private CartActivity target;

  @UiThread
  public CartActivity_ViewBinding(CartActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CartActivity_ViewBinding(CartActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.total = Utils.findRequiredViewAsType(source, R.id.total, "field 'total'", TextView.class);
    target.notfound = Utils.findRequiredViewAsType(source, R.id.notfound, "field 'notfound'", TextView.class);
    target.check = Utils.findRequiredViewAsType(source, R.id.check, "field 'check'", Button.class);
    target.number = Utils.findRequiredViewAsType(source, R.id.number, "field 'number'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CartActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.toolbar = null;
    target.back = null;
    target.title = null;
    target.total = null;
    target.notfound = null;
    target.check = null;
    target.number = null;
  }
}
