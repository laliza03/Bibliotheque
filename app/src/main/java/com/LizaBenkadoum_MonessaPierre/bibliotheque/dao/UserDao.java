package com.LizaBenkadoum_MonessaPierre.bibliotheque.dao;

import android.util.Log;

import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.User;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.JsonUtil;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.NetworkUtil;


import java.util.List;

public class UserDao {
    private static final String USER_ENDPOINT = "comptes";
    private static final String TAG = "UserDao";
    public boolean validateUser(String email, String password) {
        try {
            String response = NetworkUtil.get(USER_ENDPOINT);
            Log.d(TAG, "Response: " + response); // Log de la réponse du serveur
            List<User> users = JsonUtil.parseUsers(response);
            for (User user : users) {
                Log.d(TAG, "Checking user: " + user.getCompte()); // Log de chaque utilisateur vérifié
                Log.d(TAG, "Password expected: etsMtl, Password received: " + password);
                if (user.getCompte().equals(email) && "etsMtl".equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
