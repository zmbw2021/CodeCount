package com.lm.count.dealwithcommoncode;

import com.lm.count.result.CountResultOfLine;

/**
 * 处理正常情况
 *
 * @author limin
 * @since 2021-12-30
 */
public class NormalDealer extends CommonCodeDealer {
    @Override
    protected CountResultOfLine countCode(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = new CountResultOfLine();
        countResult.setProcess(true);
        countResult.setCount(1);
        return countResult;
    }
}
