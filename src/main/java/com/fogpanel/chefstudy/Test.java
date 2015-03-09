/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fogpanel.chefstudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author gowtham
 */
public class Test {

    public static void main(String[] args) {
        
        FileInputStream fileInputStream=null;
        
        try {
                        
            File validaterKey = new File("/home/gowtham/Desktop/CGP/chef/gowthamravi.pem");
            byte[] bValidaterKey = new byte[(int) validaterKey.length()];
            
            //convert file into array of bytes
	    fileInputStream = new FileInputStream(validaterKey);
	    fileInputStream.read(bValidaterKey);
	    fileInputStream.close();
            
            File clientKey = new File("/home/gowtham/Desktop/CGP/chef/gowthamravi.pem");
            byte[] bClientKey = new byte[(int) clientKey.length()];
            
            //convert file into array of bytes
	    fileInputStream = new FileInputStream(clientKey);
	    fileInputStream.read(bClientKey);
	    fileInputStream.close();
            
            File clientHostKey = new File("/home/gowtham/Desktop/CGP/prakash1.pem");
            byte[] bClientHostKey = new byte[(int) clientHostKey.length()];
            
            //convert file into array of bytes
	    fileInputStream = new FileInputStream(clientHostKey);
	    fileInputStream.read(bClientHostKey);
	    fileInputStream.close();
                        
            KnifeCommand knifeCommand = new KnifeCommandImpl("testKnife", bValidaterKey, bClientKey, "https://api.opscode.com/organizations/geminisys");
            knifeCommand.execute("8.21.28.160", bClientHostKey, "testKnife");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
