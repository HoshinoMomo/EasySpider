package com.easyzhang.util;

import javax.swing.*;

/**
 * Created by EasyZhang on 2017-12-26.
 */
public class EzLog {
    private JTextArea textArea;
    private static EzLog ezLog;
    private EzLog(JTextArea textArea){
        this.textArea = textArea;
    }
    public static void createEZLog(JTextArea textArea){
       ezLog = new EzLog(textArea);
    }
    public void addMessage(String mess){
        textArea.append(mess);
    }
    public static EzLog getInstance(){return ezLog;}
}
