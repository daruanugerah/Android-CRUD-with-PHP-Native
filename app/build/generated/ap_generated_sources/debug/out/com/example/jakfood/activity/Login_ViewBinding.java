// Generated code from Butter Knife. Do not modify!
package com.example.jakfood.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

public class Login_ViewBinding implements Unbinder {
  private Login target;

  private View view7f0800c1;

  private View view7f0800c3;

  private View view7f0800e0;

  private View view7f0800bf;

  @UiThread
  public Login_ViewBinding(Login target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Login_ViewBinding(final Login target, View source) {
    this.target = target;

    View view;
    target.loginUsername = Utils.findRequiredViewAsType(source, R.id.login_username, "field 'loginUsername'", TextInputEditText.class);
    target.loginPassword = Utils.findRequiredViewAsType(source, R.id.login_password, "field 'loginPassword'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.rg_user_admin_sign, "field 'rgUserAdminSign' and method 'onViewClicked'");
    target.rgUserAdminSign = Utils.castView(view, R.id.rg_user_admin_sign, "field 'rgUserAdminSign'", RadioButton.class);
    view7f0800c1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rg_user_biasa_sign, "field 'rgUserBiasaSign' and method 'onViewClicked'");
    target.rgUserBiasaSign = Utils.castView(view, R.id.rg_user_biasa_sign, "field 'rgUserBiasaSign'", RadioButton.class);
    view7f0800c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sign_in, "field 'signIn' and method 'onViewClicked'");
    target.signIn = Utils.castView(view, R.id.sign_in, "field 'signIn'", Button.class);
    view7f0800e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.register, "field 'register' and method 'onViewClicked'");
    target.register = Utils.castView(view, R.id.register, "field 'register'", TextView.class);
    view7f0800bf = view;
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
    Login target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loginUsername = null;
    target.loginPassword = null;
    target.rgUserAdminSign = null;
    target.rgUserBiasaSign = null;
    target.signIn = null;
    target.register = null;

    view7f0800c1.setOnClickListener(null);
    view7f0800c1 = null;
    view7f0800c3.setOnClickListener(null);
    view7f0800c3 = null;
    view7f0800e0.setOnClickListener(null);
    view7f0800e0 = null;
    view7f0800bf.setOnClickListener(null);
    view7f0800bf = null;
  }
}
