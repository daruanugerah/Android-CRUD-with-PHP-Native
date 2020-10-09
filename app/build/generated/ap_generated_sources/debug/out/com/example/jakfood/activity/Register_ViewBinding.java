// Generated code from Butter Knife. Do not modify!
package com.example.jakfood.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.jakfood.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Register_ViewBinding implements Unbinder {
  private Register target;

  private View view7f0800c0;

  private View view7f0800c2;

  private View view7f0800e1;

  private View view7f080098;

  @UiThread
  public Register_ViewBinding(Register target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Register_ViewBinding(final Register target, View source) {
    this.target = target;

    View view;
    target.regisName = Utils.findRequiredViewAsType(source, R.id.regis_name, "field 'regisName'", TextInputEditText.class);
    target.regisAlamat = Utils.findRequiredViewAsType(source, R.id.regis_alamat, "field 'regisAlamat'", TextInputEditText.class);
    target.regisNoTlp = Utils.findRequiredViewAsType(source, R.id.regis_no_tlp, "field 'regisNoTlp'", TextInputEditText.class);
    target.spinKelamin = Utils.findRequiredViewAsType(source, R.id.spin_kelamin, "field 'spinKelamin'", Spinner.class);
    target.regisUsername = Utils.findRequiredViewAsType(source, R.id.regis_username, "field 'regisUsername'", TextInputEditText.class);
    target.regisPass = Utils.findRequiredViewAsType(source, R.id.regis_pass, "field 'regisPass'", TextInputEditText.class);
    target.regisConfirPass = Utils.findRequiredViewAsType(source, R.id.regis_confir_pass, "field 'regisConfirPass'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.rg_user_admin, "field 'rgUserAdmin' and method 'onViewClicked'");
    target.rgUserAdmin = Utils.castView(view, R.id.rg_user_admin, "field 'rgUserAdmin'", RadioButton.class);
    view7f0800c0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rg_user_biasa, "field 'rgUserBiasa' and method 'onViewClicked'");
    target.rgUserBiasa = Utils.castView(view, R.id.rg_user_biasa, "field 'rgUserBiasa'", RadioButton.class);
    view7f0800c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sign_up, "field 'signUp' and method 'onViewClicked'");
    target.signUp = Utils.castView(view, R.id.sign_up, "field 'signUp'", Button.class);
    view7f0800e1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login, "field 'login' and method 'onViewClicked'");
    target.login = Utils.castView(view, R.id.login, "field 'login'", TextView.class);
    view7f080098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Register target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.regisName = null;
    target.regisAlamat = null;
    target.regisNoTlp = null;
    target.spinKelamin = null;
    target.regisUsername = null;
    target.regisPass = null;
    target.regisConfirPass = null;
    target.rgUserAdmin = null;
    target.rgUserBiasa = null;
    target.signUp = null;
    target.login = null;

    view7f0800c0.setOnClickListener(null);
    view7f0800c0 = null;
    view7f0800c2.setOnClickListener(null);
    view7f0800c2 = null;
    view7f0800e1.setOnClickListener(null);
    view7f0800e1 = null;
    view7f080098.setOnClickListener(null);
    view7f080098 = null;
  }
}
