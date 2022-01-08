package com.lm.count.dealwithcommoncode;

import com.lm.count.constants.CountConstants;
import com.lm.count.result.CountResultOfLine;

/**
 * 处理单行注释的场景
 *
 * @author limin
 * @date 2021/12/30
 */
public class SingleLineCommentDealer extends CommonCodeDealer {
    @Override
    protected CountResultOfLine countCode(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = new CountResultOfLine();
        if (codeLine.startsWith(CountConstants.SINGLE_COMMENT_FLAG)) {
            countResult.setProcess(true);
            countResult.setCount(0);
        }
        return countResult;
    }
}
