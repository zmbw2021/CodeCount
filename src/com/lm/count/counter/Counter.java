package com.lm.count.counter;

import com.lm.count.result.FileResult;

import java.io.File;

/**
 * 代码量检测接口
 *
 * @author limin
 * @date 2021/12/28
 */
public interface Counter {
    /**
     * 计算代码量
     *
     * @param file 文件
     * @return 代码量统计结果
     * */
    FileResult count(File file);
}
