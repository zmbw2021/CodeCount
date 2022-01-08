package com.lm.count;

import com.lm.count.counter.CommonCodeCounter;
import com.lm.count.counter.Counter;
import com.lm.count.exception.CountCodeException;
import com.lm.count.result.CountResult;
import com.lm.count.result.FileResult;
import com.lm.count.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * 描述
 *
 * @author limin
 * @date 2021/12/30
 */
public class Entry {
    public static void main(String[] args) {
        try {
            FileUtils.checkFilePath(args[0]);
        } catch (CountCodeException e) {
            System.out.println("发生错误：" + e.getLocalizedMessage());
            return;
        }
        long startTime = System.currentTimeMillis();
        List<String> allSubFilePath = FileUtils.getAllSubFilePath(args[0]);
        CountResult countResult = Entry.countFiles(allSubFilePath);
        countResult.print();
        long endTime = System.currentTimeMillis();
        System.out.println("执行完成，用时：" + (endTime - startTime) + "ms");
    }

    private static CountResult countFiles(List<String> filePath) {
        CountResult countResult = new CountResult();
        filePath.stream().map(Entry::countFileCode).forEach(fileResult -> {
            countResult.addFileResult(fileResult);
            countResult.setSumLine(fileResult.getSumLine() + countResult.getSumLine());
            countResult.setCodeLine(fileResult.getCodeLine() + countResult.getCodeLine());
            countResult.setCommentLine(fileResult.getCommentLine() + countResult.getCommentLine());
            countResult.setSpaceLine(fileResult.getSpaceLine() + countResult.getSpaceLine());
        });
        return countResult;
    }

    private static FileResult countFileCode(String filePath) {
        File file = new File(filePath);
        if (!FileUtils.getFileSuffix(file).equals("java")) {
            System.out.println("当前暂不支持非Java文件，文件名：" + filePath + "，忽略该文件");
        }
        Counter counter = new CommonCodeCounter();
        return counter.count(file);
    }
}
