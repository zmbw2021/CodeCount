package com.lm.count.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;

import com.lm.count.dialog.ConfigDialog;
import com.lm.count.model.ConfigModel;

/**
 * 代码量统计主程序
 *
 * @author limin
 * @date 2021-12-20
 */
public class CodeCountEntry extends AnAction {
    private static final String DEFAULT_SUFFIX = "*.java;*.txt";

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 1.选中Project中的文件和文件夹
        VirtualFile selectedItem = CommonDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (selectedItem == null) {
            // 2.如果没有选中任何文件，通知报错
            NotificationGroup notificationGroup = new NotificationGroup("CodeCount Plugin NotSelectedAnyFile",
                    NotificationDisplayType.BALLOON, false);
            Notification notification = notificationGroup.createNotification("Please at least choose a file",
                    MessageType.ERROR);
            notification.setTitle("CodeCount");
            Notifications.Bus.notify(notification);
            return;
        }

        String selectedPath = selectedItem.getCanonicalPath();
        ConfigModel model = new ConfigModel(DEFAULT_SUFFIX, selectedPath);
        // 3.弹出对话框
        ConfigDialog configDialog = new ConfigDialog(e.getProject(), model);
        configDialog.show();
    }
}
