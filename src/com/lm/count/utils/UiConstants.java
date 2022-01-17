package com.lm.count.utils;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Ui工具类
 *
 * @author l00507668
 * @since 2022-01-17
 */
public class UiConstants {
    public static void setTableColumnSize(JTable fileResultTable) {
        TableColumnModel columnModel = fileResultTable.getColumnModel();
        TableColumn fileNameColumn = columnModel.getColumn(0);
        fileNameColumn.setPreferredWidth(600);
        for (int i = 1; i < 5; i++) {
            TableColumn countResultColumn = columnModel.getColumn(i);
            countResultColumn.setPreferredWidth(50);
        }
    }
}
