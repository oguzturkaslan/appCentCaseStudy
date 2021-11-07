package com.appcentcasestudy.business.concretes;

import com.appcentcasestudy.business.abstracts.ToDoListService;
import com.appcentcasestudy.core.dtos.ToDoListUpdateDto;
import com.appcentcasestudy.core.utilities.results.ErrorResult;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.core.utilities.results.SuccessResult;
import com.appcentcasestudy.dataAccess.abstracts.ToDoListDao;
import com.appcentcasestudy.dataAccess.abstracts.UserDao;
import com.appcentcasestudy.entities.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoListManager implements ToDoListService {

    private UserDao userDao;
    private ToDoListDao toDoListDao;

    @Autowired
    public ToDoListManager(UserDao userDao, ToDoListDao toDoListDao) {
        this.userDao = userDao;
        this.toDoListDao = toDoListDao;
    }

    @Override
    public Result createTask(ToDoList toDoList) {
        boolean isExistUser = this.userDao.existsById(toDoList.getUser().getId());
        if (!isExistUser) {
            return new ErrorResult(false, "Kullanıcı Bulunamadı !");
        }

        boolean isExistDescription = toDoListDao.existsByDescription(toDoList.getDescription());
        if (isExistDescription) {
            return new ErrorResult(false, "Bu task zaten mevcut !");
        }

        if (toDoList.getDescription() == null) {
            return new ErrorResult(false, "Task Açıklaması Boş Bırakılamaz !");
        }
        toDoListDao.save(toDoList);
        return new SuccessResult(true, "Task Oluşturuldu !");
    }

    @Override
    public Result updateTask(Long id, ToDoListUpdateDto toDoListUpdateDto) {
        boolean isExistTask = this.toDoListDao.existsById(id);
        if (!isExistTask) {
            return new ErrorResult(false, "Task Bulunamadı !");
        }

        if (toDoListUpdateDto.getDescription() == null) {
            return new ErrorResult(false, "Task Açıklaması Boş Bırakılamaz !");
        }

        ToDoList toDoList = toDoListDao.getById(id);
        toDoList.setDescription(toDoListUpdateDto.getDescription());
        toDoList.setDone(toDoListUpdateDto.isDone());
        toDoListDao.save(toDoList);

        return new SuccessResult(true, "Task Güncellendi !");
    }

    @Override
    public Result deleteTask(Long id) {
        toDoListDao.deleteById(id);
        return new SuccessResult(true, "Task Silindi !");
    }
}
