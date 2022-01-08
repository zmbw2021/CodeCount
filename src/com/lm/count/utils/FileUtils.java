package com.lm.count.utils;

import com.lm.count.constants.CountConstants;
import com.lm.count.exception.CountCodeException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件工具类
 *
 * @author limin
 * @date 2021/12/28
 */
public class FileUtils {
    /**
     * 获取目录下所有文件列表
     *
     * @param parentPath 父目录
     * @return 目录下所有文件列表
     */
    public static List<String> getAllSubFilePath(String parentPath) {
        File file = new File(parentPath);
        if (!file.isDirectory()) {
            return Arrays.asList(parentPath);
        }
        List<String> allFilePath = new ArrayList<>();
        String[] list = file.list();
        Arrays.stream(list).map(FileUtils::getAllSubFilePath).forEach(allFilePath::addAll);
        return allFilePath;
    }

    /**
     * 获取文件后缀
     *
     * @param file 文件
     * @return 文件后缀
     */
    public static String getFileSuffix(File file) {
        String filename = file.getName();
        String fileSuffix = null;
        if (filename.indexOf(CountConstants.DOT) != -1) {
            fileSuffix = filename.substring(filename.lastIndexOf(CountConstants.DOT) + 1);
        }
        return fileSuffix;
    }

    /**
     * 检测输入的文件路径
     *
     * @param filePath 文件路径
     * @throws
     */
    public static void checkFilePath(String filePath) throws CountCodeException {
        if (filePath == null) {
            throw new CountCodeException("file path is null.");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new CountCodeException("file path: " + filePath + "is not exist.");
        }
    }
}
