package com.org.max.shortnews1.ui.login;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.org.max.shortnews1.R;
import com.org.max.shortnews1.databinding.ActivityLoginBinding;
import com.org.max.shortnews1.ui.home.HomeActivity;
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private LoginPresenter presenter;
    ActivityLoginBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.username.setText("Test");
        activityMainBinding.password.setText("Test");
        activityMainBinding.username.getText().toString();
        activityMainBinding.button.setOnClickListener(this);
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        activityMainBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        activityMainBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        activityMainBinding.username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        activityMainBinding.password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                presenter.validateCredentials(activityMainBinding.username.getText().toString(), activityMainBinding.password.getText().toString());
                break;
        }
    }
}
