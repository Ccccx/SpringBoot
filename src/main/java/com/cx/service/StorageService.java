package com.cx.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author CX
 * @create 2018/1/11
 */
public interface StorageService {
    void init();
    void store(MultipartFile file);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
