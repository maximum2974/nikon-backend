package com.maximum.nikonbackend.controller;

import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.dto.feedback.AddFeedbackRequest;
import com.maximum.nikonbackend.model.entity.Feedback;
import com.maximum.nikonbackend.model.entity.User;
import com.maximum.nikonbackend.service.EmailService;
import com.maximum.nikonbackend.service.FeedbackService;
import com.maximum.nikonbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.controller
 * @Author: maximum
 * @CreateTime: 2024-06-25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/add")
    public BaseResponse<Boolean> addFeedback(@RequestBody AddFeedbackRequest addFeedbackRequest, HttpServletRequest request){
        String email = addFeedbackRequest.getEmail();
        String subject = addFeedbackRequest.getSubject();
        String content = addFeedbackRequest.getContent();
        String emailRegex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "invalid email format");
        }
        if(StringUtils.isBlank(subject)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "subject cannot be blank");
        }
        if(StringUtils.isBlank(content)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "content cannot be blank");
        }
        User user = userService.getLoginUser(request);
        Feedback feedback = new Feedback();
        feedback.setUserId(user.getId());
        feedback.setUuid(UUID.randomUUID().toString());
        feedback.setEmail(email);
        feedback.setSubject(subject);
        feedback.setContent(content);
        boolean result = feedbackService.save(feedback);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        String userName = user.getUserName();
        scheduleEmail(email, userName, subject);
        return ResultUtils.success(true);
    }

    @Async
    public void scheduleEmail(String email, String userName, String subject){
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            String content = String.format(
                    "Dear %s,\n\n" +
                            "Thank you for your feedback. We appreciate you taking the time to share your thoughts with us.\n\n" +
                            "Your feedback is invaluable as it helps us to improve our services and provide a better experience for all our users.\n\n" +
                            "If you have any additional comments or suggestions, please feel free to reach out to us at any time.\n\n" +
                            "Best regards,\n" +
                            "The Maximum Company",
                    userName
            );
            emailService.sendSimpleMail(email, subject, content);
        }, 10, TimeUnit.SECONDS);
    }

}
