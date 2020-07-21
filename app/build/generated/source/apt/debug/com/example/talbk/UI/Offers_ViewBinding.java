// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Offers_ViewBinding implements Unbinder {
  private Offers target;

  @UiThread
  public Offers_ViewBinding(Offers target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Offers_ViewBinding(Offers target, View source) {
    this.target = target;

    target.total = Utils.findRequiredViewAsType(source, R.id.total, "field 'total'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.number = Utils.findRequiredViewAsType(source, R.id.number, "field 'number'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.cart = Utils.findRequiredViewAsType(source, R.id.check, "field 'cart'", Button.class);
    target.pb = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'pb'", ProgressBar.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.binre, "field 'relativeLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Offers target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.total = null;
    target.toolbar = null;
    target.back = null;
    target.number = null;
    target.title = null;
    target.cart = null;
    target.pb = null;
    target.relativeLayout = null;
  }
}
