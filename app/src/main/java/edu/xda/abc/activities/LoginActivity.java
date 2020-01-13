package edu.xda.abc.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import edu.xda.abc.R;
import edu.xda.abc.db.ShPreferences;
import edu.xda.abc.dialogs.AlertDialog;
import edu.xda.abc.models.ResponseLoginModel;
import edu.xda.abc.models.UserModel;
import edu.xda.abc.presenters.UserPresenter;
import edu.xda.abc.presenters.impl.PSUserPresenter;
import edu.xda.abc.views.UserRegisterView;
import edu.xda.abc.views.UserView;


public class LoginActivity extends AppCompatActivity implements UserView, UserRegisterView {
    private SweetAlertDialog alertDialog;
    @BindView(R.id.edtGmail)
    EditText gmail;
    @BindView(R.id.edtPassWord)
    EditText password;
    @BindView(R.id.btnDangNhap)
    Button btnLogin;
    @BindView(R.id.btnDangKy)
    Button btnRegister;
    @BindView(R.id.edtGmailRegister)
    EditText edtGmailRegister;
    @BindView(R.id.edtpassRegister)
    EditText edtpass1;
    @BindView(R.id.edt_hoten)
    EditText edtHoten;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        ShPreferences shPreferences = new ShPreferences(this);
        ResponseLoginModel model = shPreferences.getModel();
        if(model!=null){
            changeActivity();
        }

        changeChildFlipper();
        UserPresenter userPresenter = new PSUserPresenter(this,this);
        alertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        btnLogin.setOnClickListener(v -> {
            if (gmail.getText().length() != 0 && password.getText().length() != 0)
                userPresenter.checkLogin(gmail.getText().toString(), password.getText().toString());
            else if (gmail.getText().length() == 0) {
                gmail.setError("Không được để trống!");
            } else if (password.getText().length() == 0) {
                password.setError("Không được để trống!");
            }
        });
        btnRegister.setOnClickListener(v -> {
            if (edtGmailRegister.getText().length() != 0 && edtpass1.getText().length() != 0 && edtHoten.getText().length() != 0) {
                UserModel userModel = new UserModel();
                userModel.setFullname(edtHoten.getText().toString());
                userModel.setGmail(edtGmailRegister.getText().toString());
                userModel.setUserpassword(edtpass1.getText().toString());
                userPresenter.registerUser(userModel);

            } else {
                if (edtGmailRegister.getText().length() == 0) {
                    edtGmailRegister.setError("Không được để trống!");
                } else if (edtpass1.getText().length() == 0) {
                    edtpass1.setError("Không được để trống!");
                } else if (edtHoten.getText().length() == 0) {
                    edtHoten.setError("Không được để trống!");
                }
            }
        });
    }


    private void changeChildFlipper() {
        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        TextView tvDangNhap = findViewById(R.id.tvDangNhap);
        TextView tvDangKy = findViewById(R.id.tvDangKy);
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(LoginActivity.this, R.anim.view_transition_in_right);
                viewFlipper.setDisplayedChild(0);
            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(LoginActivity.this, R.anim.view_transition_in_left);
                viewFlipper.setDisplayedChild(1);
            }
        });
    }

    private void clearTextRegister() {
        edtHoten.setText("");
        edtGmailRegister.setText("");
        edtpass1.setText("");
    }

    @Override
    public void loginFailure(String message) {
        new AlertDialog(this).alertDialogError("Login Failure", message);
    }

    @Override
    public void loginSuccess(ResponseLoginModel model) {
        ShPreferences preferences = new ShPreferences(this);
        preferences.setModel(model);
        changeActivity();
    }

    @Override
    public void onError(String err) {
        new AlertDialog(this).alertDialogError("Error", err);
    }

    @Override
    public void loading() {
        alertDialog.getProgressHelper().setBarColor(Color.parseColor("#09d6df"));
        alertDialog.setTitleText("Loading,please wait . . .");
        alertDialog.setCancelable(true);
        alertDialog.show();

    }

    @Override
    public void hideLoading() {
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public void changeActivity() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void registerSuccess(String message) {
        clearTextRegister();
        Toast.makeText(this, "Đăng ký " + message + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFailure(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
