package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class User {

    private int id;
    private String name;
    private String email;
    private String phone;
    private Date birthday;
    private String job;
    private String gender;

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ",gender=" + gender + ", job=" + job + ",email=" + email + "," +
                "birthday=" + birthday + ", phone=" + phone + "]";
    }


}
