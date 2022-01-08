package com.lm.count.result;

/**
 * 基本结果类型
 *
 * @author limin
 * @date 2021/12/28
 */
public class BaseResult {
    private long sumLine;
    private long codeLine;
    private long commentLine;
    private long spaceLine;

    public void setSumLine(long sumLine) {
        this.sumLine = sumLine;
    }

    public void setCodeLine(long codeLine) {
        this.codeLine = codeLine;
    }

    public void setCommentLine(long commentLine) {
        this.commentLine = commentLine;
    }

    public void setSpaceLine(long spaceLine) {
        this.spaceLine = spaceLine;
    }

    public long getSumLine() {
        return sumLine;
    }

    public long getCodeLine() {
        return codeLine;
    }

    public long getCommentLine() {
        return commentLine;
    }

    public long getSpaceLine() {
        return spaceLine;
    }
}
