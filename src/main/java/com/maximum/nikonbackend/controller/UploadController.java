package com.maximum.nikonbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.GithubUploaderUtils;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.entity.UploadImage;
import com.maximum.nikonbackend.model.vo.UploadImageVO;
import com.maximum.nikonbackend.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UploadController {
    @Autowired
    private GithubUploaderUtils githubUploaderUtils;
    @Autowired
    private UploadService uploadService;


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
            UploadImage uploadImage = new UploadImage();
            uploadImage.setImage(userAvatar);
            uploadService.save(uploadImage);
            return ResultUtils.success(userAvatar);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "An error occurred while uploading the file");
        }
    }

    @GetMapping("/upload/list")
    public BaseResponse<Page<UploadImageVO>> getUploadImage(@RequestParam long current,
                                                            @RequestParam long size){
        if(current < 0 || size <= 0 || size > 50){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UploadImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<UploadImage> uploadImagePage = uploadService.page(new Page<>(current, size), queryWrapper);
        List<UploadImage> uploadImageList = uploadImagePage.getRecords();

        if (uploadImageList.isEmpty()) {
            return ResultUtils.success(new Page<>());
        }

        List<UploadImageVO> uploadImageVOList = uploadImageList.stream().map(uploadImage -> {
            UploadImageVO uploadImageVo = new UploadImageVO();
            uploadImageVo.setImage(uploadImage.getImage());
            return uploadImageVo;
        }).collect(Collectors.toList());

        Page<UploadImageVO> uploadImageVoPage = new Page<>(current, size, uploadImagePage.getTotal());
        uploadImageVoPage.setRecords(uploadImageVOList);

        return ResultUtils.success(uploadImageVoPage);
    }
}
