package com.lm.count.toolwindow;

import com.intellij.openapi.project.Project;

import com.lm.count.model.DataCenter;
import com.lm.count.utils.UiConstants;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * 代码量统计结果展示窗口
 *
 * @author l00507668
 * @since 2021-12-29
 */
public class ToolWindow {
    private JTable fileResultTable;
    private JTextField sumLines;
    private JTextField codeLines;
    private JTextField commentLines;
    private JTextField spaceLines;
    private JPanel toolWindowPanel;

    public ToolWindow(Project project, com.intellij.openapi.wm.ToolWindow toolWindow) {
        fileResultTable.setModel(DataCenter.getDefaultTableModel());
        fileResultTable.setEnabled(true);
        fileResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // 设置表格列宽
        UiConstants.setTableColumnSize(fileResultTable);
    }

    /**
     * 获取Tool Window内容
     *
     * @return Tool Window展示的内容
     */
    public JPanel getContent() {
        return toolWindowPanel;
    }
}
