// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper.Shipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.minh.findtheshipper.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FragmentContainerShipper_ViewBinding implements Unbinder {
  private FragmentContainerShipper target;

  @UiThread
  public FragmentContainerShipper_ViewBinding(FragmentContainerShipper target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FragmentContainerShipper_ViewBinding(FragmentContainerShipper target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentContainerShipper target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
  }
}
