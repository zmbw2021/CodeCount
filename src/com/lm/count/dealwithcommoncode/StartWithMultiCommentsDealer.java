package com.lm.count.dealwithcommoncode;

import com.lm.count.constants.CountConstants;
import com.lm.count.result.CountResultOfLine;

/**
 * 处理以多行注释开头的场景
 *
 * @author limin
 * @date 2021/12/30
 */
public class StartWithMultiCommentsDealer extends CommonCodeDealer {
    @Override
    protected CountResultOfLine countCode(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = new CountResultOfLine();
        if (codeLine.startsWith(CountConstants.MULTI_COMMENT_START)) {
            countResult.setProcess(true);
            codeLine = codeLine.substring(2);
            if (!codeLine.contains(CountConstants.MULTI_COMMENT_END)) {
                countResult.setMultiComment(true);
            } else {
                codeLine = codeLine.substring(codeLine.lastIndexOf(CountConstants.MULTI_COMMENT_START) + 2).trim();
                dealWithRemainCode(countResult, codeLine);
            }
        }
        return countResult;
    }
}
