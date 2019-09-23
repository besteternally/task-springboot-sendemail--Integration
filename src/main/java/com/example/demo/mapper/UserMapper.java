package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")/////后加的
    public User selectUserById(int id);    /////后加的

    List<User> findUserByName(String name);

    List<User> findUserByBirthday(String birthday);

    public List<User> ListUser();
    public int insertUser(User user);
    public int delete(int id);
    public int Update(User user);

}
