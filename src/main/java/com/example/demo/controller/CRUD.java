package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/CRUD", method = {RequestMethod.GET, RequestMethod.POST})
public class CRUD {

    @RequestMapping("/ListUser")
    @ResponseBody
    public List<User> ListUser() {
        return userservice.ListUser();
    }

    @RequestMapping("/ListUserByname")
    @ResponseBody
    public List<User> ListUserByname(String name) {
        return userservice.findByName(name);
    }

    @Autowired
    private UserService userservice;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = {"/selectUserById"}, method = RequestMethod.GET)
    public User selectUserById(String id) {
        User user = userMapper.selectUserById(Integer.parseInt(id));
        return user;
    }

    @RequestMapping(value = {"/selectUserByBirthday"}, method = RequestMethod.GET)
    public List selectUserByBirthday() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//日期格式转换
        Date birthday = new Date();
        String birth=simpleDateFormat.format(birthday);
        List<User> userList = userMapper.findUserByBirthday(birth);
        System.out.println(userList.size());
        return userList;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userservice.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {
        return userservice.insertUser(user);
    }
}
