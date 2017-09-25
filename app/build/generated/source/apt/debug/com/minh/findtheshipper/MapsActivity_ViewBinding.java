// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapsActivity_ViewBinding implements Unbinder {
  private MapsActivity target;

  private View view2131755226;

  @UiThread
  public MapsActivity_ViewBinding(MapsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MapsActivity_ViewBinding(final MapsActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.listAction, "field 'listView' and method 'listViewItemClicked'");
    target.listView = Utils.castView(view, R.id.listAction, "field 'listView'", ListView.class);
    view2131755226 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.listViewItemClicked(p0, p1, p2, p3);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MapsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.listView = null;

    ((AdapterView<?>) view2131755226).setOnItemClickListener(null);
    view2131755226 = null;
  }
}
