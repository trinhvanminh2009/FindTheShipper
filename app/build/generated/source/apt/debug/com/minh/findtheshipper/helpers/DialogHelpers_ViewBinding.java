// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper.helpers;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.minh.findtheshipper.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DialogHelpers_ViewBinding implements Unbinder {
  private DialogHelpers target;

  @UiThread
  public DialogHelpers_ViewBinding(DialogHelpers target, View source) {
    this.target = target;

    target.listNotification = Utils.findRequiredViewAsType(source, R.id.listNotification, "field 'listNotification'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DialogHelpers target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listNotification = null;
  }
}
