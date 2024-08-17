package com.LizaBenkadoum_MonessaPierre.bibliotheque.dao;

import android.util.Log;

import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.User;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.JsonUtil;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.NetworkUtil;


import java.util.List;

public class UserDao {
    private static final String USER_ENDPOINT = "comptes";
    private static final String TAG = "UserDao";
    public boolean validateUser(String email) {
        try {
            String response = NetworkUtil.get(USER_ENDPOINT);
            List<User> users = JsonUtil.parseUsers(response);
            for (User user : users) {
                if (user.getCompte().equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
