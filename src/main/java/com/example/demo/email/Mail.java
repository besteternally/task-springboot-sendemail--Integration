package com.example.demo.email;

import com.example.demo.controller.CRUD;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.ParseException;
import java.util.List;

@RestController

public class Mail {

    @Autowired
    private CRUD crud;
    @Autowired
    JavaMailSender jms;

    @GetMapping("/sendEmail")
    public String sendEmail() throws ParseException {
        List<User> userList = crud.selectUserByBirthday();
        for (User user : userList) {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom("1215038665@qq.com");
            smm.setSubject("祝" + user.getName() + "生日快乐");
            smm.setText("祝"+user.getName() +constructSentence(user.getName(), user.getGender())+"生日快乐-----来自JackMa");
            smm.setTo(user.getEmail());
            try {
                jms.send(smm);
                System.out.println("恭喜：发送成功!");

            } catch (Exception e) {
                System.out.println("发送失败"+ e.getMessage());
            }
        }
        return "发送结束";

    }

    //带附件
    @GetMapping("/sendFile")
    public void sendAttachmentsMail(String[] filePathArray)throws ParseException {
        List<User> userList = crud.selectUserByBirthday();
        for (User user : userList) {
            MimeMessage message = jms.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom("1215038665@qq.com");
                helper.setTo(user.getEmail());
                helper.setSubject("祝" + user.getName() + "生日快乐");
                helper.setText("祝"+user.getName() +constructSentence(user.getName(), user.getGender())+"生日快乐-----来自JackMa");
                //验证文件数据是否为空
                if (null != filePathArray) {
                    FileSystemResource file = null;
                    for (int i = 0; i < filePathArray.length; i++) {
                        //添加附件
                        file = new FileSystemResource(filePathArray[i]);
                        helper.addAttachment(filePathArray[i].substring(filePathArray[i].lastIndexOf(File.separator)), file);
                    }
                }
                jms.send(message);
                System.out.println("带附件的邮件发送成功");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发送带附件的邮件失败");
            }
        }
    }







    private String constructSentence(String name, String gender) {
        StringBuffer sb = new StringBuffer();
//        sb.append("djaoiq");
        if (gender.equals("0")) {
            //
            sb.append("大美女");
        } else {
            sb.append("大帅哥");
        }
//        sb.append("fhiuwe");
        return sb.toString();
    }
}
