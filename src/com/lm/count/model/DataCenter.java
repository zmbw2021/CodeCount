package com.lm.count.model;

import javax.swing.table.DefaultTableModel;

/**
 * 代码量数据
 *
 * @author l00507668
 * @since 2022-01-13
 */
public class DataCenter {
    private static final String[] columnName = {"文件名", "总行数", "代码行数", "注释行数", "空行数"};

    private static DefaultTableModel defaultTableModel = new DefaultTableModel(null, columnName) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public static DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    /**
     * 清除表格中的数据
     */
    public static void clear() {
        defaultTableModel.setDataVector(null, columnName);
    }
}
