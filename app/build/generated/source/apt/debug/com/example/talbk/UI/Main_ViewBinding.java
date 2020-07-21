// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.talbk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Main_ViewBinding implements Unbinder {
  private Main target;

  @UiThread
  public Main_ViewBinding(Main target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Main_ViewBinding(Main target, View source) {
    this.target = target;

    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottom_navigation, "field 'bottomNavigation'", BottomNavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Main target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomNavigation = null;
  }
}
