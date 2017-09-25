// Generated code from Butter Knife. Do not modify!
package com.minh.findtheshipper.Shipper;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import at.markushi.ui.CircleButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.minh.findtheshipper.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailOrderShipperActivity_ViewBinding implements Unbinder {
  private DetailOrderShipperActivity target;

  private View view2131755192;

  private View view2131755194;

  private View view2131755193;

  private View view2131755195;

  private View view2131755196;

  @UiThread
  public DetailOrderShipperActivity_ViewBinding(DetailOrderShipperActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailOrderShipperActivity_ViewBinding(final DetailOrderShipperActivity target,
      View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
    target.imgUserImage = Utils.findRequiredViewAsType(source, R.id.imgUserImage, "field 'imgUserImage'", ImageView.class);
    target.txtUserName = Utils.findRequiredViewAsType(source, R.id.txtUserName, "field 'txtUserName'", TextView.class);
    target.txtTimeAgo = Utils.findRequiredViewAsType(source, R.id.txtTimeAgo, "field 'txtTimeAgo'", TextView.class);
    target.txtPrice = Utils.findRequiredViewAsType(source, R.id.txtPrice, "field 'txtPrice'", TextView.class);
    target.txtStartPlace = Utils.findRequiredViewAsType(source, R.id.txtStartPlace, "field 'txtStartPlace'", TextView.class);
    target.txtFinishPlace = Utils.findRequiredViewAsType(source, R.id.txtFinishPlace, "field 'txtFinishPlace'", TextView.class);
    target.txtPhoneNumber = Utils.findRequiredViewAsType(source, R.id.txtPhoneNumber, "field 'txtPhoneNumber'", TextView.class);
    target.txtNote = Utils.findRequiredViewAsType(source, R.id.txtNote, "field 'txtNote'", TextView.class);
    target.txtAdvancedMoney = Utils.findRequiredViewAsType(source, R.id.txtAdvancedMoney, "field 'txtAdvancedMoney'", TextView.class);
    target.txtDistance = Utils.findRequiredViewAsType(source, R.id.txtDistance, "field 'txtDistance'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnGetOrder, "field 'btnGetOrder' and method 'eventClick'");
    target.btnGetOrder = Utils.castView(view, R.id.btnGetOrder, "field 'btnGetOrder'", Button.class);
    view2131755192 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.eventClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnCall, "field 'btnCall' and method 'eventClick'");
    target.btnCall = Utils.castView(view, R.id.btnCall, "field 'btnCall'", CircleButton.class);
    view2131755194 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.eventClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnComment, "field 'btnComment' and method 'eventClick'");
    target.btnComment = Utils.castView(view, R.id.btnComment, "field 'btnComment'", CircleButton.class);
    view2131755193 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.eventClick(p0);
      }
    });
    target.txtTime = Utils.findRequiredViewAsType(source, R.id.txtTime, "field 'txtTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnSave, "field 'btnSave' and method 'eventClick'");
    target.btnSave = Utils.castView(view, R.id.btnSave, "field 'btnSave'", CircleButton.class);
    view2131755195 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.eventClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDelete, "field 'btnDelete' and method 'eventClick'");
    target.btnDelete = Utils.castView(view, R.id.btnDelete, "field 'btnDelete'", CircleButton.class);
    view2131755196 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.eventClick(p0);
      }
    });
    target.tvSaveOrDelete = Utils.findRequiredViewAsType(source, R.id.tvSaveOrDelete, "field 'tvSaveOrDelete'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailOrderShipperActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.imgUserImage = null;
    target.txtUserName = null;
    target.txtTimeAgo = null;
    target.txtPrice = null;
    target.txtStartPlace = null;
    target.txtFinishPlace = null;
    target.txtPhoneNumber = null;
    target.txtNote = null;
    target.txtAdvancedMoney = null;
    target.txtDistance = null;
    target.btnGetOrder = null;
    target.btnCall = null;
    target.btnComment = null;
    target.txtTime = null;
    target.btnSave = null;
    target.btnDelete = null;
    target.tvSaveOrDelete = null;

    view2131755192.setOnClickListener(null);
    view2131755192 = null;
    view2131755194.setOnClickListener(null);
    view2131755194 = null;
    view2131755193.setOnClickListener(null);
    view2131755193 = null;
    view2131755195.setOnClickListener(null);
    view2131755195 = null;
    view2131755196.setOnClickListener(null);
    view2131755196 = null;
  }
}
