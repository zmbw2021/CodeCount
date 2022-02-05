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
import java.io.RandomAccessFile;

/**
 * Java、JavaScript等通用代码量统计
 *
 * @author limin
 * @since 2021-12-28
 */
public class CommonCodeCounter implements Counter {
    private static final int NEW_LINE = 0x0A;

    @Override
    public FileResult count(File file) {
        CommonCodeDealer spaceDealer = constructProcessChain();
        int sumLine = 0;
        int codeLine = 0;
        int commentLine = 0;
        int spaceLine = 0;
        boolean isMultiComment = false;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
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
            if (hasNewLine(file)) {
                spaceLine++;
                sumLine++;
            }
            // TODO 异常处理
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getFileResult(file, sumLine, codeLine, commentLine, spaceLine);
    }

    private boolean hasNewLine(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long lastCharIndex = randomAccessFile.length() - 1;
        randomAccessFile.seek(lastCharIndex);
        int readByte = randomAccessFile.readByte();
        return readByte == NEW_LINE;
    }

    private FileResult getFileResult(File file, int sumLine, int codeLine, int commentLine, int spaceLine) {
        FileResult fileResult = new FileResult();
        try {
            fileResult.setFullFileName(file.getCanonicalPath());
            // TODO 异常处理
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileResult.setSumLine(sumLine);
        fileResult.setCodeLine(codeLine);
        fileResult.setCommentLine(commentLine);
        fileResult.setSpaceLine(spaceLine);
        return fileResult;
    }

    private CommonCodeDealer constructProcessChain() {
        CommonCodeDealer spaceDealer = new SpaceDealer();
        SingleLineCommentDealer singleLineCommentDealer = new SingleLineCommentDealer();
        WithInMultiCommentsDealer withInMultiCommentsDealer = new WithInMultiCommentsDealer();
        StartWithMultiCommentsDealer startWithMultiCommentsDealer = new StartWithMultiCommentsDealer();
        ContainsWithMultiCommentsDealer containsWithMultiCommentsDealer = new ContainsWithMultiCommentsDealer();
        NormalDealer normalDealer = new NormalDealer();
        spaceDealer.setNext(singleLineCommentDealer).setNext(withInMultiCommentsDealer)
                .setNext(startWithMultiCommentsDealer).setNext(containsWithMultiCommentsDealer).setNext(normalDealer);
        return spaceDealer;
    }
}
