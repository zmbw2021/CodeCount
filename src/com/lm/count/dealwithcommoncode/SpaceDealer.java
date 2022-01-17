package com.lm.count.dealwithcommoncode;

import com.lm.count.result.CountResultOfLine;

/**
 * 处理空行的场景
 *
 * @author limin
 * @since 2021-12-30
 */
public class SpaceDealer extends CommonCodeDealer {
    @Override
    protected CountResultOfLine countCode(String codeLine, boolean isMultiple) {
        CountResultOfLine countResult = new CountResultOfLine();
        if (codeLine.equals("")) {
            countResult.setProcess(true);
            countResult.setCount(-1);
        }
        return countResult;
    }
}
