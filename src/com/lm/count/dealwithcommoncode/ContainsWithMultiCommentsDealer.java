package com.lm.count.dealwithcommoncode;

import com.lm.count.constants.CountConstants;
import com.lm.count.result.CountResultOfLine;

/**
 * 处理包含多行注释的场景
 *
 * @author limin
 * @date 2021/12/30
 */
public class ContainsWithMultiCommentsDealer extends CommonCodeDealer {
    @Override
    protected CountResultOfLine countCode(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = new CountResultOfLine();
        if (codeLine.startsWith(CountConstants.MULTI_COMMENT_START)) {
            countResult.setProcess(true);
            countResult.setCount(1);
            codeLine = codeLine.substring(codeLine.indexOf(CountConstants.MULTI_COMMENT_START) + 2).trim();
            if (!codeLine.contains(CountConstants.MULTI_COMMENT_END)) {
                countResult.setMultiComment(true);
            } else {
                codeLine = codeLine.substring(codeLine.indexOf(CountConstants.MULTI_COMMENT_START) + 2).trim();
                if (codeLine.contains(CountConstants.MULTI_COMMENT_START)) {
                    countResult.setMultiComment(true);
                } else {
                    countResult.setMultiComment(false);
                }
            }
        }
        return countResult;
    }
}
