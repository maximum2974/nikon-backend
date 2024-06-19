package com.maximum.nikonbackend.common;

import lombok.Data;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.common
 * @Author: maximum
 * @CreateTime: 2024-06-19
 * @Version: 1.0
 */

@Data
public class PageRequest {
    /**
     *
     */
    private long current = 1;

    /**
     *
     */
    private long pageSize = 10;
}
