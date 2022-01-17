package com.lm.count.result;

/**
 * 单行代码计算结果
 *
 * @author limin
 * @since 2021-12-28
 */
public class CountResultOfLine {
    /**
     * -1表示空行，0表示注释行，1表示代码行
     */
    private int count;
    private boolean isMultiComment;
    private boolean isProcess;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isMultiComment() {
        return isMultiComment;
    }

    public void setMultiComment(boolean multiComment) {
        isMultiComment = multiComment;
    }

    public boolean isProcess() {
        return isProcess;
    }

    public void setProcess(boolean process) {
        isProcess = process;
    }
}
