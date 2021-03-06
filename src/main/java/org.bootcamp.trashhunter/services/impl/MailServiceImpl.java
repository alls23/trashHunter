package org.bootcamp.trashhunter.services.impl;

import org.bootcamp.trashhunter.models.User;
import org.bootcamp.trashhunter.models.token.VerificationToken;
import org.bootcamp.trashhunter.services.abstraction.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    private void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }


    public void sendMessage(User user, VerificationToken token) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет , %s! \n" +
                            "благодарим за регистрацию на нашем сервисе. Пожалуйста, перейдите по ссылке снизу: " +
                            "<a href=http://localhost:8080/activate/%s>" + "Ваша ссылка :)" + "</a>",
                    user.getName(),
                    token.getToken()

            );
            send(user.getEmail(), "Activatation code", message);
        }
    }

    @Override
    public void sendMessage(User user, String message, String subject) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            send(user.getEmail(), subject, message);
        }
    }
}
