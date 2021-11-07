package com.appcentcasestudy.business.concretes;

import com.appcentcasestudy.business.abstracts.UserService;
import com.appcentcasestudy.core.dtos.UserUpdateDto;
import com.appcentcasestudy.core.utilities.results.ErrorResult;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.core.utilities.results.SuccessResult;
import com.appcentcasestudy.dataAccess.abstracts.UserDao;
import com.appcentcasestudy.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserManager(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public Result register(User user) {
        if (checkIfUserExist(user.getEmail())) {
            return new ErrorResult(false, "Bu e-posta için kullanıcı zaten var");
        }
        if (!validate(user.getEmail())) {
            return new ErrorResult(false, "Lütfen e-mail formatına uygun şekilde giriniz. (Örn :abc@abc.com)");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return new SuccessResult(true, "Kayıt Başarılı");

    }

    @Override
    public Result login(User user) {
        List<User> users = userDao.findAll();
        for (User other : users) {
            if (other.getEmail().equals(user.getEmail()) && bCryptPasswordEncoder.matches(user.getPassword(), other.getPassword())) {
                return new SuccessResult(true, "Giriş Yapıldı");
            }
        }
        return new ErrorResult(false, "Lütfen E-Mail veya Şifrenizi kontrol ediniz!");
    }

    @Override
    public Result updateUser(Long id, UserUpdateDto userUpdateDto) {
        boolean isExistUser = this.userDao.existsById(id);
        if (!isExistUser) {
            return new ErrorResult(false, "Kullanıcı Bulunamadı !");
        }
        if (!validate(userUpdateDto.getEmail())) {
            return new ErrorResult(false, "Lütfen e-mail formatına uygun şekilde giriniz. (Örn :abc@abc.com)");
        }
        if (userUpdateDto.getEmail() == null || userUpdateDto.getPassword() == null) {
            return new ErrorResult(false, "Kullanıcı Adı ve Şifre bilgilerini girmeniz gerekmektedir !");
        }

        User user = userDao.getById(id);

        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userUpdateDto.getPassword()));

        userDao.save(user);
        return new SuccessResult(true, "Kayıt güncellendi !");
    }

    @Override
    public Result deleteUser(Long id) {
        boolean isExistTask = this.userDao.existsById(id);
        if (!isExistTask) {
            return new ErrorResult(false, "Kullanıcı Bulunamadı !");
        }
        userDao.deleteById(id);
        return new SuccessResult(true, "Kullancı Silindi !");
    }
}
