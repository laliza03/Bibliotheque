package com.LizaBenkadoum_MonessaPierre.bibliotheque.presentateur;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.activities.LoginActivity;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.dao.UserDao;

public class LoginPresenter {
    private LoginActivity view;
    private UserDao userDao;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
        this.userDao = new UserDao();
    }

    public void login(String email, String password) {
        if (userDao.validateUser(email, password)) {
            view.onLoginSuccess();
        } else {
            view.onLoginFailure();
        }
    }
}
