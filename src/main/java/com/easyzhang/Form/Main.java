package com.easyzhang.Form;

import com.easyzhang.operate.EzConnection;
import com.easyzhang.operate.EzDownload;
import com.easyzhang.util.EzLog;
import com.easyzhang.util.EzDownloadQueue;
import com.easyzhang.util.EzWaitQueue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by EasyZhang on 2017-12-26.
 */
public class Main {
    private JTextArea textArea;
    private JButton button;
    private JTextField textField;
    private JPanel JPanel;
    private JButton clean;

    public Main() {

        EzLog.createEZLog(textArea);
        EzLog.getInstance().addMessage("启动下载队列。。。.\n");
        EzDownloadQueue.createEzDownloadQueue();
        EzLog.getInstance().addMessage("启动等待扫描队列。。。.\n");
        EzWaitQueue.createEzWaitQueue();
        EzLog.getInstance().addMessage("启动下载线程。。。。\n");
        new Thread(new EzDownload()).start();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baseURL = textField.getText();
                textArea.append(baseURL+"为起点深度优先遍历 \n");
                EzWaitQueue.getInstance().push(baseURL);
                EzLog.getInstance().addMessage("启动扫描线程。。。。\n");
                new Thread(new EzConnection(baseURL)).start();
            }
        });
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("EasySpider");
        frame.setContentPane(new Main().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(680,435);
        frame.setLocation(150,75);
        frame.setVisible(true);

    }
}
