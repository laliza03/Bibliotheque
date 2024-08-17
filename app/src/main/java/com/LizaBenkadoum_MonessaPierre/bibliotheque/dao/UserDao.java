package com.LizaBenkadoum_MonessaPierre.bibliotheque.dao;

import com.LizaBenkadoum_MonessaPierre.bibliotheque.modele.User;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.util.JsonUtil;

import java.util.List;

public class UserDao {
    private static final String USERS_JSON = "bibliotheque.json";

    public boolean validateUser(String email, String password) {
        // Valider les informations d'identification de l'utilisateur
        return JsonUtil.validateUser(USERS_JSON, email, password);
    }

    public List<User> getUsers() {
        // Récupérer la liste des utilisateurs
        return JsonUtil.loadUsers(USERS_JSON);
    }
}
