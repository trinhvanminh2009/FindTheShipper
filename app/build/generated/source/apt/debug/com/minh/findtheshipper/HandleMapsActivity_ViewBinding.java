// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HandleMapsActivity_ViewBinding implements Unbinder {
  private HandleMapsActivity target;

  private View view2131624109;

  private View view2131624116;

  private View view2131624117;

  private View view2131624124;

  private View view2131624125;

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
    view2131624109 = view;
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
    view2131624116 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getAddress();
      }
    });
    target.btnPlace = Utils.findRequiredViewAsType(source, R.id.btnAdd, "field 'btnPlace'", ImageButton.class);
    target.txtCash = Utils.findRequiredViewAsType(source, R.id.txtCash, "field 'txtCash'", TextView.class);
    target.layoutItem = Utils.findRequiredViewAsType(source, R.id.layoutItem, "field 'layoutItem'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnCreateNewOrder, "field 'btnCreateNewOrder' and method 'createNewOrder'");
    target.btnCreateNewOrder = Utils.castView(view, R.id.btnCreateNewOrder, "field 'btnCreateNewOrder'", Button.class);
    view2131624117 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.createNewOrder();
      }
    });
    target.layoutCreateNewOrder = Utils.findRequiredViewAsType(source, R.id.layoutCreateNewOrder, "field 'layoutCreateNewOrder'", LinearLayout.class);
    target.layoutControl = Utils.findRequiredViewAsType(source, R.id.layoutControl, "field 'layoutControl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnCancelOrder, "field 'btnCancelOrder' and method 'cancelOrder'");
    target.btnCancelOrder = Utils.castView(view, R.id.btnCancelOrder, "field 'btnCancelOrder'", Button.class);
    view2131624124 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cancelOrder();
      }
    });
    target.editStartPlace = Utils.findRequiredViewAsType(source, R.id.editStartPlace, "field 'editStartPlace'", EditText.class);
    target.editShipMoney = Utils.findRequiredViewAsType(source, R.id.editShipMoney, "field 'editShipMoney'", EditText.class);
    target.editPhoneNumber = Utils.findRequiredViewAsType(source, R.id.editNumber, "field 'editPhoneNumber'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnConfirmCreateOrder, "field 'btnConfirmCreateOrder' and method 'confirmCreateOrder'");
    target.btnConfirmCreateOrder = Utils.castView(view, R.id.btnConfirmCreateOrder, "field 'btnConfirmCreateOrder'", Button.class);
    view2131624125 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.confirmCreateOrder();
      }
    });
    target.editAdvancedMoney = Utils.findRequiredViewAsType(source, R.id.editAdvancedMoney, "field 'editAdvancedMoney'", EditText.class);
    target.editNote = Utils.findRequiredViewAsType(source, R.id.editNote, "field 'editNote'", EditText.class);
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
    target.layoutItem = null;
    target.btnCreateNewOrder = null;
    target.layoutCreateNewOrder = null;
    target.layoutControl = null;
    target.btnCancelOrder = null;
    target.editStartPlace = null;
    target.editShipMoney = null;
    target.editPhoneNumber = null;
    target.btnConfirmCreateOrder = null;
    target.editAdvancedMoney = null;
    target.editNote = null;

    ((AdapterView<?>) view2131624109).setOnItemClickListener(null);
    view2131624109 = null;
    view2131624116.setOnClickListener(null);
    view2131624116 = null;
    view2131624117.setOnClickListener(null);
    view2131624117 = null;
    view2131624124.setOnClickListener(null);
    view2131624124 = null;
    view2131624125.setOnClickListener(null);
    view2131624125 = null;
  }
}
