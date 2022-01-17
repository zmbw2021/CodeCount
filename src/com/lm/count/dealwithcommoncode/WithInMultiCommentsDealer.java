package com.lm.count.dealwithcommoncode;

import com.lm.count.constants.CountConstants;
import com.lm.count.result.CountResultOfLine;

/**
 * 处理处于多行注释中的场景
 *
 * @author limin
 * @since 2021-12-30
 */
public class WithInMultiCommentsDealer extends CommonCodeDealer {
    @Override
    protected CountResultOfLine countCode(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = new CountResultOfLine();
        if (isMultiple) {
            countResult.setProcess(true);
            if (!codeLine.contains(CountConstants.MULTI_COMMENT_END)) {
                countResult.setMultiComment(true);
                countResult.setCount(0);
            } else {
                if (codeLine.endsWith(CountConstants.MULTI_COMMENT_END)) {
                    countResult.setMultiComment(false);
                    countResult.setCount(0);
                } else {
                    codeLine = codeLine.substring(codeLine.lastIndexOf(CountConstants.MULTI_COMMENT_END) + 2).trim();
                    dealWithRemainCode(countResult, codeLine);
                }
            }
        }
        return countResult;
    }
}
