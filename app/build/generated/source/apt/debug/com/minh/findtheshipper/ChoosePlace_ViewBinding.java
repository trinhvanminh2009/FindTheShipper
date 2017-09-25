// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChoosePlace_ViewBinding implements Unbinder {
  private ChoosePlace target;

  private View view2131755179;

  private View view2131755180;

  private View view2131755183;

  private View view2131755182;

  @UiThread
  public ChoosePlace_ViewBinding(ChoosePlace target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChoosePlace_ViewBinding(final ChoosePlace target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnHaNoi, "field 'btnHaHoi' and method 'ChooseHaNoi'");
    target.btnHaHoi = Utils.castView(view, R.id.btnHaNoi, "field 'btnHaHoi'", Button.class);
    view2131755179 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ChooseHaNoi();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnHoChiMinh, "field 'btnHoChiMinh' and method 'ChooseHoChiMinh'");
    target.btnHoChiMinh = Utils.castView(view, R.id.btnHoChiMinh, "field 'btnHoChiMinh'", Button.class);
    view2131755180 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ChooseHoChiMinh();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnShipMode, "field 'btnShipMode' and method 'ChooseShipMode'");
    target.btnShipMode = Utils.castView(view, R.id.btnShipMode, "field 'btnShipMode'", Button.class);
    view2131755183 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ChooseShipMode();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnShopMode, "field 'btnShopMode' and method 'ChooseShopMode'");
    target.btnShopMode = Utils.castView(view, R.id.btnShopMode, "field 'btnShopMode'", Button.class);
    view2131755182 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ChooseShopMode();
      }
    });
    target.chooseMode = Utils.findRequiredViewAsType(source, R.id.chooseMode, "field 'chooseMode'", LinearLayout.class);
    target.choosePlace = Utils.findRequiredViewAsType(source, R.id.choosePlace, "field 'choosePlace'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChoosePlace target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnHaHoi = null;
    target.btnHoChiMinh = null;
    target.btnShipMode = null;
    target.btnShopMode = null;
    target.chooseMode = null;
    target.choosePlace = null;

    view2131755179.setOnClickListener(null);
    view2131755179 = null;
    view2131755180.setOnClickListener(null);
    view2131755180 = null;
    view2131755183.setOnClickListener(null);
    view2131755183 = null;
    view2131755182.setOnClickListener(null);
    view2131755182 = null;
  }
}
