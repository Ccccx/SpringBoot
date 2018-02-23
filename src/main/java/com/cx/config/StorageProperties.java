package com.cx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CX
 * @create 2018/1/12
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
