package com.lm.count.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import org.jetbrains.annotations.NotNull;

/**
 * 展示窗口工厂类
 *
 * @author l00507668
 * @since 2021-12-29
 */
public class CountResultWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 创建出代码量展示的Tool Window
        com.lm.count.toolwindow.ToolWindow countToolWindow = new com.lm.count.toolwindow.ToolWindow(project, toolWindow);
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        // 获取用于toolWindow显示的内容
        Content content = contentFactory.createContent(countToolWindow.getContent(), "", false);
        // 给toolWindow设置内容
        toolWindow.getContentManager().addContent(content);
    }
}
