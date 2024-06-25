package com.maximum.nikonbackend.controller;

import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.GithubUploaderUtils;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {
    @Autowired
    private GithubUploaderUtils githubUploaderUtils;


    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "No file uploaded");
        }

        String contentType = file.getContentType();
        if (!("image/jpeg".equals(contentType) || "image/png".equals(contentType))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Invalid file type. Only JPEG and PNG are allowed.");
        }

        if (file.getSize() > 4 * 1024 * 1024) { // 4MB
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "File size exceeds limit of 4MB");
        }

        try {
            String userAvatar = githubUploaderUtils.upload(file);
            return ResultUtils.success(userAvatar);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "An error occurred while uploading the file");
        }
    }
}
