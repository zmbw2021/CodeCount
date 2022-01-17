package com.lm.count.dealwithcommoncode;

import com.lm.count.result.CountResultOfLine;

/**
 * 处理通用代码的抽象方法，责任链模式
 *
 * @author limin
 * @since 2021-12-28
 */
public abstract class CommonCodeDealer {
    private CommonCodeDealer nextDealer;

    public CommonCodeDealer() {
    }

    /**
     * 设置责任链
     *
     * @param nextDealer 下一个处理器
     * @return 下一个处理器
     */
    public CommonCodeDealer setNext(CommonCodeDealer nextDealer) {
        this.nextDealer = nextDealer;
        return nextDealer;
    }

    /**
     * 责任链处理代码行
     *
     * @param codeLine   代码行
     * @param isMultiple 当前是否处于多行注释中
     * @return 单行计算结果
     */
    public final CountResultOfLine processChain(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = countCode(codeLine, isMultiple);
        if (countResult != null && countResult.isProcess()) {
            return countResult;
        } else if (nextDealer != null) {
            return nextDealer.processChain(codeLine, isMultiple);
        } else {
            processFailed();
        }
        return null;
    }

    /**
     * 处理失败场景
     */
    protected void processFailed() {
        System.out.println("java code process failed.");
    }

    /**
     * 计算代码量
     *
     * @param codeLine   代码行
     * @param isMultiple 当前是否处于多行注释中
     * @return 单行计算结果
     */
    protected abstract CountResultOfLine countCode(String codeLine, boolean isMultiple);

    /**
     * 处理某行代码存在多行注释结束符的情况
     *
     * @param codeLine    代码行
     * @param countResult 当前计算结果
     */
    protected final void dealWithRemainCode(CountResultOfLine countResult, String codeLine) {
        if ("".equals(codeLine) || codeLine.startsWith("//")) {
            return;
        } else if (!codeLine.contains("/*")) {
            countResult.setCount(1);
            countResult.setMultiComment(false);
        } else {
            countResult.setMultiComment(true);
            if (codeLine.startsWith("/*")) {
                countResult.setCount(0);
            } else {
                countResult.setCount(1);
            }
        }
    }
}
