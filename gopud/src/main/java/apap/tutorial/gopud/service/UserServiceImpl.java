package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel updatePassword(UserModel user, String newPassword) {
        String password = encrypt(newPassword);
        user.setPassword(password);
        return userDb.save(user);
    }

    @Override
    public UserModel getUserByUsername(String username){
        return userDb.findByUsername(username);
    }

    @Override
    public boolean checkPasswordAgainstSavedDatabase(UserModel user, String checkedString){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(checkedString, user.getPassword());
    }

    @Override
    public boolean checkPasswordValidity(String checkedString) {
        return checkedString.matches("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$") &&
                checkedString.length()>=8;
    }

    @Override
    public boolean checkNewPasswordAgaintConfirmationPassword(String newPassword, String confirmPassword){
        return !newPassword.equals(confirmPassword);
    }
}
