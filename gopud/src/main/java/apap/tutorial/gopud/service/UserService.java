package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel getUserByUsername(String username);
    boolean checkPasswordAgainstSavedDatabase(UserModel userModel, String checkedString);
    boolean checkPasswordValidity(String checkedString);
    boolean checkNewPasswordAgaintConfirmationPassword(String newPassword, String confirmPassword);
    UserModel updatePassword(UserModel user, String newPassword);
    boolean checkUsernameAgainstSavedDatabase(String username);
}
