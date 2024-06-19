package com.maximum.nikonbackend;

import com.maximum.nikonbackend.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend
 * @Author: maximum
 * @CreateTime: 2024-06-19
 * @Version: 1.0
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class SendEmailTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendmail() {
        emailService.sendSimpleMail("swe2209541@xmu.edu.my","主题：你好普通邮件","内容：第一封邮件");
    }

}
