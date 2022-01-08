package com.lm.count.dialog;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;

import com.lm.count.model.ConfigModel;

import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * 代码量统计配置对话框
 *
 * @author limin
 * @date 2021/12/20
 */
public class ConfigDialog extends DialogWrapper {
    private ConfigModel model;
    private Project project;

    public ConfigDialog(Project project, ConfigModel model) {
        super(true);
        setTitle("Code Count Check");
        this.project = project;
        this.model = model;
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
            // 点击start按钮，执行代码量统计
            System.out.println("执行Code Count...");
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
}
