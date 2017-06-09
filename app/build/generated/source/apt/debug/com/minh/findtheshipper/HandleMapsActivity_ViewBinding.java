// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HandleMapsActivity_ViewBinding implements Unbinder {
  private HandleMapsActivity target;

  private View view2131624106;

  @UiThread
  public HandleMapsActivity_ViewBinding(HandleMapsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HandleMapsActivity_ViewBinding(final HandleMapsActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.btnFindDirection, "field 'btnFindDirection' and method 'findDirection'");
    target.btnFindDirection = Utils.castView(view, R.id.btnFindDirection, "field 'btnFindDirection'", Button.class);
    view2131624106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.findDirection();
      }
    });
    target.txtStart = Utils.findRequiredViewAsType(source, R.id.txtStart, "field 'txtStart'", EditText.class);
    target.txtFinish = Utils.findRequiredViewAsType(source, R.id.txtFinish, "field 'txtFinish'", EditText.class);
    target.txtDistance = Utils.findRequiredViewAsType(source, R.id.txtDistance, "field 'txtDistance'", TextView.class);
    target.txtTime = Utils.findRequiredViewAsType(source, R.id.txtTime, "field 'txtTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HandleMapsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.btnFindDirection = null;
    target.txtStart = null;
    target.txtFinish = null;
    target.txtDistance = null;
    target.txtTime = null;

    view2131624106.setOnClickListener(null);
    view2131624106 = null;
  }
}
