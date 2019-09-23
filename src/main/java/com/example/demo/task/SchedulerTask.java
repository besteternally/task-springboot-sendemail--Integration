package com.example.demo.task;
/**
 * 定时任务类，实现定时发送邮件
 * Created by JackMa on 2018/07/23
 */

import com.example.demo.email.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class SchedulerTask {
    @Autowired
    private Mail mailController;
    private int count = 1;

    // 表示每天八点发送一次邮件
    // @Scheduled(cron="0 0 8 * * ?")
    @Scheduled(cron = "0/10 * * * * ?"/*每隔3秒触发一次*/)
    private void process() throws ParseException {
        String content = "springboot整合定时器实现定时邮件发送，这是第" + (count++) + "封邮件";
        //mailController.sendEmail();
        String [] fileArray={"C:\\Users\\hyj\\package.json","C:\\Users\\hyj\\package-lock.json"};
        mailController.sendAttachmentsMail(fileArray);
        System.out.println("发送定时邮件成功");

        }
    }
