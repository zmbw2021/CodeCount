package com.lm.count.result;

import java.util.Locale;

/**
 * 单个文件检测结果
 *
 * @author limin
 * @date 2021/12/28
 */
public class FileResult extends BaseResult {
    private String fullFileName;

    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    /**
     * 显示单行文件结果
     */
    public void print() {
        System.out.println(String.format(Locale.ROOT, "文件名：%s，总行数：%s，代码行数：%s，注释行数：%s，空行数：%s",
                fullFileName, getSumLine(), getCodeLine(), getCommentLine(), getSpaceLine()));
    }
}
