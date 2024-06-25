package com.maximum.nikonbackend.model.dto.feedback;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.feedback
 * @Author: maximum
 * @CreateTime: 2024-06-25
 * @Version: 1.0
 */

@Data
public class AddFeedbackRequest implements Serializable {
    private static final long serialVersionUID = 2476194105024908881L;
    private String email;
    private String subject;
    private String content;
}
