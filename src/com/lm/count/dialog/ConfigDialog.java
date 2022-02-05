package com.lm.count.dialog;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.ContentManager;

import org.jetbrains.annotations.Nullable;

import com.lm.count.model.DataCenter;
import com.lm.count.Entry;
import com.lm.count.model.ConfigModel;
import com.lm.count.result.FileResult;
import com.lm.count.result.CountResult;
import com.lm.count.utils.UiConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.util.List;

/**
 * 代码量统计配置对话框
 *
 * @author limin
 * @since 2021-12-20
 */
public class ConfigDialog extends DialogWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDialog.class);

    private ConfigModel model;
    private Project project;
    private Entry entry;

    public ConfigDialog(Project project, ConfigModel model) {
        super(true);
        setTitle("Code Count Check");
        this.project = project;
        this.model = model;
        entry = new Entry(model);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        JPanel checkPathPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel checkPathLabel = new JLabel("Check Path");
        checkPathPanel.add(checkPathLabel);
        JTextField checkPathTextField = new JTextField(100);
        checkPathTextField.setText(model.getCheckPath());
        checkPathPanel.add(checkPathTextField);
        JButton checkPathButton = new JButton("Browse...");
        checkPathButton.addActionListener(event -> {
            // 打开文件选择框
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(),
                    project, project.getBaseDir());
            if (virtualFile != null) {
                String path = virtualFile.getPath();
                checkPathTextField.setText(path);
                model.setCheckPath(path);
            }
        });
        checkPathPanel.add(checkPathButton);
        centerPanel.add(checkPathPanel);

        JPanel checkTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel checkTypeLabel = new JLabel("Check Type");
        checkTypePanel.add(checkTypeLabel);
        JTextField checkTypeTextField = new JTextField(100);
        checkTypeTextField.setText(model.getCheckType());
        checkTypePanel.add(checkTypeTextField);
        centerPanel.add(checkTypePanel);
        return centerPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        // 流式布局可以存放多个按钮
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton startButton = new JButton("start");
        startButton.addActionListener(event -> {
            // 调用前清空历史数据
            DataCenter.clear();
            // 点击start按钮，执行代码量统计
            LOGGER.info("执行Code Count...");
            CountResult countResult = entry.codeCountCalculate();
            if (countResult != null) {
                // 处理返回结果，在ToolWindow中展示
                showToolWindow(countResult);
            }
            // 退出对话框
            this.close(DialogWrapper.CANCEL_EXIT_CODE);
        });
        JButton cancelButton = new JButton("cancel");
        cancelButton.addActionListener(event -> {
            // 点击cancel按钮，退出对话框
            this.close(DialogWrapper.CANCEL_EXIT_CODE);
        });
        southPanel.add(startButton);
        southPanel.add(cancelButton);
        return southPanel;
    }

    private void showToolWindow(CountResult countResult) {
        // 设置表格数据
        DefaultTableModel defaultTableModel = DataCenter.getDefaultTableModel();
        List<FileResult> fileResultList = countResult.getFileResultList();
        for (FileResult fileResult : fileResultList) {
            Object[] data = new Object[5];
            data[0] = fileResult.getFullFileName();
            data[1] = fileResult.getSumLine();
            data[2] = fileResult.getCodeLine();
            data[3] = fileResult.getCommentLine();
            data[4] = fileResult.getSpaceLine();
            defaultTableModel.addRow(data);
        }
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("CodeCount");
        // 展示窗口，同时会自动注册监听器到defaultTableModel中
        // TODO 升级到java11
        toolWindow.show(() -> {});

        UiConstants.setTableColumnSize((JTable) defaultTableModel.getTableModelListeners()[0]);
        // 设置统计信息
        ContentManager contentManager = toolWindow.getContentManager();
        JPanel jPanel = (JPanel) contentManager.getComponent().getComponent(0);
        JPanel component = (JPanel) jPanel.getComponent(1);
        JTextField sumLines = (JTextField) component.getComponent(1);
        JTextField codeLines = (JTextField) component.getComponent(4);
        JTextField commentLines = (JTextField) component.getComponent(7);
        JTextField spaceLines = (JTextField) component.getComponent(10);
        sumLines.setText(String.valueOf(countResult.getSumLine()));
        codeLines.setText(String.valueOf(countResult.getCodeLine()));
        commentLines.setText(String.valueOf(countResult.getCommentLine()));
        spaceLines.setText(String.valueOf(countResult.getSpaceLine()));
    }
}
