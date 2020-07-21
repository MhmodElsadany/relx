// Generated code from Butter Knife. Do not modify!
package com.example.talbk.UI;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class SubCategoryActivity_ViewBinding implements Unbinder {
  private SubCategoryActivity target;

  @UiThread
  public SubCategoryActivity_ViewBinding(SubCategoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubCategoryActivity_ViewBinding(SubCategoryActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.pb = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'pb'", ProgressBar.class);
    target.notfound = Utils.findRequiredViewAsType(source, R.id.notfound, "field 'notfound'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SubCategoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.back = null;
    target.recyclerView = null;
    target.pb = null;
    target.notfound = null;
    target.title = null;
  }
}
