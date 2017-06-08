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

public class HandleMapsActivity_ViewBinding implements Unbinder {
  private HandleMapsActivity target;

  @UiThread
  public HandleMapsActivity_ViewBinding(HandleMapsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HandleMapsActivity_ViewBinding(HandleMapsActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listAction, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HandleMapsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.listView = null;
  }
}
