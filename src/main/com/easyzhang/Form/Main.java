package com.easyzhang.Form;

import com.easyzhang.operate.EzConnection;
import com.easyzhang.operate.EzDownload;
import com.easyzhang.operate.EzFilter;
import com.easyzhang.util.EZLog;
import com.easyzhang.util.EzQueue;

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

    public Main() {

        EZLog.createEZLog(textArea);
        EZLog.getInstance().addMessage("启动下载队列。。。.\n");
        EzQueue.createEzQueue();
        EZLog.getInstance().addMessage("启动下载线程。。。。\n");
        new Thread(new EzDownload()).start();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baseURL = textField.getText();
                textArea.append(baseURL+"扫描中。。。。。。。。、 \n");
                EzConnection ezConnection = new EzConnection(baseURL);
                EzFilter.getQueue(ezConnection.getURLQueue());
                textArea.append(baseURL+"扫描结束,已加入下载队列！\n");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("EasySpider");
        frame.setContentPane(new Main().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,600);
        frame.setLocation(150,75);
        frame.setVisible(true);

    }
}
