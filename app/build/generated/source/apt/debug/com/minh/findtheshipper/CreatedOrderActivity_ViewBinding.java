// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreatedOrderActivity_ViewBinding implements Unbinder {
  private CreatedOrderActivity target;

  @UiThread
  public CreatedOrderActivity_ViewBinding(CreatedOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreatedOrderActivity_ViewBinding(CreatedOrderActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
    target.listViewOrder = Utils.findRequiredViewAsType(source, R.id.listOrder, "field 'listViewOrder'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CreatedOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.listViewOrder = null;
  }
}
