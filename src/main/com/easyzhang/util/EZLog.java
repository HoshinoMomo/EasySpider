package com.easyzhang.util;

import javax.swing.*;

/**
 * Created by EasyZhang on 2017-12-26.
 */
public class EZLog {
    private JTextArea textArea;
    private static EZLog ezLog;
    private EZLog(JTextArea textArea){
        this.textArea = textArea;
    }
    public static void createEZLog(JTextArea textArea){
       ezLog = new EZLog(textArea);
    }
    public void addMessage(String mess){
        textArea.append(mess);
    }
    public static EZLog getInstance(){return ezLog;}
}
