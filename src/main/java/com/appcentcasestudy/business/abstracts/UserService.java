package com.appcentcasestudy.business.abstracts;

import com.appcentcasestudy.core.dtos.UserUpdateDto;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.entities.User;

public interface UserService {

    boolean checkIfUserExist(String email);

    Result register(User user);

    Result login(User user);

    Result updateUser(Long id, UserUpdateDto userUpdateDto);

    Result deleteUser(Long id);
}
