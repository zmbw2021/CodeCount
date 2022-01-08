package com.lm.count.counter;

import com.lm.count.dealwithcommoncode.CommonCodeDealer;
import com.lm.count.dealwithcommoncode.ContainsWithMultiCommentsDealer;
import com.lm.count.dealwithcommoncode.NormalDealer;
import com.lm.count.dealwithcommoncode.SingleLineCommentDealer;
import com.lm.count.dealwithcommoncode.SpaceDealer;
import com.lm.count.dealwithcommoncode.StartWithMultiCommentsDealer;
import com.lm.count.dealwithcommoncode.WithInMultiCommentsDealer;
import com.lm.count.result.CountResultOfLine;
import com.lm.count.result.FileResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Java、JavaScript等通用代码量统计
 *
 * @author limin
 * @date 2021/12/28
 */
public class CommonCodeCounter implements Counter {
    @Override
    public FileResult count(File file) {
        CommonCodeDealer spaceDealer = new SpaceDealer();
        SingleLineCommentDealer singleLineCommentDealer = new SingleLineCommentDealer();
        WithInMultiCommentsDealer withInMultiCommentsDealer = new WithInMultiCommentsDealer();
        StartWithMultiCommentsDealer startWithMultiCommentsDealer = new StartWithMultiCommentsDealer();
        ContainsWithMultiCommentsDealer containsWithMultiCommentsDealer = new ContainsWithMultiCommentsDealer();
        NormalDealer normalDealer = new NormalDealer();
        spaceDealer.setNext(singleLineCommentDealer).setNext(withInMultiCommentsDealer)
                .setNext(startWithMultiCommentsDealer).setNext(containsWithMultiCommentsDealer).setNext(normalDealer);

        int sumLine = 0;
        int codeLine = 0;
        int commentLine = 0;
        int spaceLine = 0;
        BufferedReader reader = null;
        String line = null;
        boolean isMultiComment = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                sumLine++;
                CountResultOfLine countResult = spaceDealer.processChain(line.trim(), isMultiComment);
                isMultiComment = countResult.isMultiComment();
                if (countResult.getCount() == -1) {
                    spaceLine++;
                } else if (countResult.getCount() == 0) {
                    commentLine++;
                } else {
                    codeLine++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileResult fileResult = new FileResult();
        try {
            fileResult.setFullFileName(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileResult.setSumLine(sumLine);
        fileResult.setCodeLine(codeLine);
        fileResult.setCommentLine(commentLine);
        fileResult.setSpaceLine(spaceLine);
        return fileResult;
    }
}
