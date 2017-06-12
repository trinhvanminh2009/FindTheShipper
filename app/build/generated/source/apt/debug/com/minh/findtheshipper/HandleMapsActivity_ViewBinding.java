// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HandleMapsActivity_ViewBinding implements Unbinder {
  private HandleMapsActivity target;

  private View view2131624104;

  private View view2131624110;

  @UiThread
  public HandleMapsActivity_ViewBinding(HandleMapsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HandleMapsActivity_ViewBinding(final HandleMapsActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.listAction, "field 'listPlaces' and method 'listViewClicked'");
    target.listPlaces = Utils.castView(view, R.id.listAction, "field 'listPlaces'", ListView.class);
    view2131624104 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.listViewClicked(p0, p1, p2, p3);
      }
    });
    target.txtDistance = Utils.findRequiredViewAsType(source, R.id.txtDistance, "field 'txtDistance'", TextView.class);
    target.txtTime = Utils.findRequiredViewAsType(source, R.id.txtTime, "field 'txtTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnOK, "field 'btnOk' and method 'getAddress'");
    target.btnOk = Utils.castView(view, R.id.btnOK, "field 'btnOk'", ImageButton.class);
    view2131624110 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getAddress();
      }
    });
    target.btnPlace = Utils.findRequiredViewAsType(source, R.id.btnAdd, "field 'btnPlace'", ImageButton.class);
    target.txtCash = Utils.findRequiredViewAsType(source, R.id.txtCash, "field 'txtCash'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HandleMapsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.listPlaces = null;
    target.txtDistance = null;
    target.txtTime = null;
    target.btnOk = null;
    target.btnPlace = null;
    target.txtCash = null;

    ((AdapterView<?>) view2131624104).setOnItemClickListener(null);
    view2131624104 = null;
    view2131624110.setOnClickListener(null);
    view2131624110 = null;
  }
}
