package com.lm.count.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 整体检测结果
 *
 * @author limin
 * @since 2021-12-28
 */
public class CountResult extends BaseResult {
    private List<FileResult> fileResultList;

    public CountResult() {
        fileResultList = new ArrayList<>();
    }

    /**
     * 添加单个文件的检测结果
     *
     * @param fileResult 单个文件的检测结果
     */
    public void addFileResult(FileResult fileResult) {
        fileResultList.add(fileResult);
    }

    public List<FileResult> getFileResultList() {
        return fileResultList;
    }

    /**
     * 显示检测结果
     */
    public void print() {
        System.out.println("文件检测结果如下：");
        fileResultList.forEach(FileResult::print);
        System.out.println("统计信息如下：");
        System.out.println(String.format(Locale.ROOT, "总行数：%s，代码行数：%s，注释行数：%s，空行数：%s", getSumLine(),
                getCodeLine(), getCommentLine(), getSpaceLine()));
    }
}
