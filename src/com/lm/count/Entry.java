package com.lm.count;

import com.lm.count.constants.CountConstants;
import com.lm.count.counter.CommonCodeCounter;
import com.lm.count.counter.Counter;
import com.lm.count.exception.CountCodeException;
import com.lm.count.model.ConfigModel;
import com.lm.count.result.CountResult;
import com.lm.count.result.FileResult;
import com.lm.count.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 代码量计算入口
 *
 * @author limin
 * @since 2021-12-30
 */
public class Entry {
    private ConfigModel model;

    public Entry(ConfigModel model) {
        this.model = model;
    }

    public CountResult codeCountCalculate() {
        String filePath = model.getCheckPath();
        try {
            // todo 需要修改异常处理方式
            FileUtils.checkFilePath(filePath);
        } catch (CountCodeException e) {
            System.out.println("发生错误：" + e.getLocalizedMessage());
            return null;
        }
        long startTime = System.currentTimeMillis();
        List<String> allSubFilePath = FileUtils.getAllSubFilePath(filePath);
        CountResult countResult = countFiles(allSubFilePath);
        countResult.print();
        long endTime = System.currentTimeMillis();
        System.out.println("执行完成，用时：" + (endTime - startTime) + "ms");
        return countResult;
    }

    private CountResult countFiles(List<String> filePath) {
        CountResult countResult = new CountResult();
        filePath.stream().map(this::countFileCode).filter(Objects::nonNull).forEach(fileResult -> {
            countResult.addFileResult(fileResult);
            countResult.setSumLine(fileResult.getSumLine() + countResult.getSumLine());
            countResult.setCodeLine(fileResult.getCodeLine() + countResult.getCodeLine());
            countResult.setCommentLine(fileResult.getCommentLine() + countResult.getCommentLine());
            countResult.setSpaceLine(fileResult.getSpaceLine() + countResult.getSpaceLine());
        });
        return countResult;
    }

    private FileResult countFileCode(String filePath) {
        String checkType = model.getCheckType();
        String[] supportedFileSuffix = checkType.split(CountConstants.SEMICOLON);
        File file = new File(filePath);
        if (!FileUtils.containsFileSuffix(supportedFileSuffix, FileUtils.getFileSuffix(file))) {
            System.out.println("当前暂不支持不在此后缀列表(" + checkType + ")中的文件，文件名：" + filePath + "，忽略该文件");
            return null;
        }
        Counter counter = new CommonCodeCounter();
        return counter.count(file);
    }
}
