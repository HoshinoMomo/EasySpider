package com.easyzhang.operate;

import com.easyzhang.util.EzQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by EasyZhang 2017-12-25.
 */
public class EzConnection {

     private String resURL = "www.baidu.com";

     public EzConnection(String resURL){
         this.resURL = resURL;
     }

     public String getURLQueue(){
         try {
             //跟网站建立连接
             URL url = new URL(resURL);
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
             connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
             connection.connect();

             InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

             String line;
             StringBuffer sb=new StringBuffer();
             while((line = bufferedReader.readLine())!=null){
                 sb.append(line,0,line.length());
                 sb.append('\n');
             }
             bufferedReader.close();
             inputStreamReader.close();
             return sb.toString();
         }catch (Exception e){
             e.printStackTrace();
         }
         return null;
     }
}
