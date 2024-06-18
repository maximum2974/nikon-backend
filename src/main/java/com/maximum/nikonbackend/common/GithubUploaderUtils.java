package com.maximum.nikonbackend.common;

import com.maximum.nikonbackend.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


@Component
public class GithubUploaderUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubUploaderUtils.class);

    public static final String URI_SEPARATOR = "/";

    public static final Set<String> ALLOW_FILE_SUFFIX = new HashSet<>(Arrays.asList("jpg", "png", "jpeg", "gif"));

    @Value("${github.bucket.url}")
    private String url;

    @Value("${github.bucket.api}")
    private String api;

    @Value("${github.bucket.access-token}")
    private String accessToken;

    @Autowired
    RestTemplate restTemplate;

    /**
     *
     * @param multipartFile
     * @return String
     * @throws IOException
     */
    public String upload (MultipartFile multipartFile) throws IOException {

        String suffix = this.getSuffix(multipartFile.getOriginalFilename()).toLowerCase();
        if (!ALLOW_FILE_SUFFIX.contains(suffix)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "unsupported file suffixes: " + suffix);
        }

        // rename file
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;

        // directories are broken up by date
        String[] folders = this.getDateFolder();

        // final file path
        String filePath = new StringBuilder(String.join(URI_SEPARATOR, folders)).append(fileName).toString();

        LOGGER.info("upload file to Github：{}", filePath);

        JsonObject payload = new JsonObject();
        payload.add("message", new JsonPrimitive("file upload"));
        payload.add("content", new JsonPrimitive(Base64.getEncoder().encodeToString(multipartFile.getBytes())));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "token " + this.accessToken);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange(this.api + filePath, HttpMethod.PUT,
                new HttpEntity<String>(payload.toString(), httpHeaders), String.class);

        JsonObject response = JsonParser.parseString(responseEntity.getBody()).getAsJsonObject();

        LOGGER.info("upload success: {}", response.toString());

        return this.url + filePath;
    }

    /**
     *
     * @param fileName
     * @return
     */
    protected String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            String suffix = fileName.substring(index + 1);
            if (!suffix.isEmpty()) {
                return suffix;
            }
        }
        throw new IllegalArgumentException("invalid file name: " + fileName);
    }

    /**
     *
     * yyyy/mmd/dd
     * @return
     */
    protected String[] getDateFolder() {
        String[] retVal = new String[3];

        LocalDate localDate = LocalDate.now();
        retVal[0] = localDate.getYear() + "";

        int month = localDate.getMonthValue();
        retVal[1] = month < 10 ? "0" + month : month + "";

        int day = localDate.getDayOfMonth();
        retVal[2] = day < 10 ? "0" + day : day + "";

        return retVal;
    }
}
