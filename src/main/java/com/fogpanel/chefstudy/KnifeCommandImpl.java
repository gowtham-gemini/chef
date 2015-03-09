/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fogpanel.chefstudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gowtham
 */
public class KnifeCommandImpl implements KnifeCommand {
        
    public KnifeCommandImpl(String messageID, byte[] validaterKey, byte[] clientKey, String chefServerUrl) {
    
        try {
        
            File chefDir = new File(KnifeConstants.CHEF_CONF_PATH_DIR + messageID);
            
            if(!chefDir.exists()) {
                
                chefDir.mkdir();
                           
                //Creating A Knife.rb conf file
                File kniferb = new File(chefDir.getAbsolutePath()+"/knife.rb");

                Writer outputWri;

                outputWri = new OutputStreamWriter(new FileOutputStream(kniferb, true), "UTF-8");  //clears file every time

                BufferedWriter output = new BufferedWriter(outputWri);

                // Add config info to knife.rb file
                output.append(KnifeConstants.CURRENT_DIR);
                output.newLine();
                output.append(KnifeConstants.LOG_LEVEL);
                output.newLine();
                output.append(KnifeConstants.LOG_LOCATION);
                output.newLine();
                output.append(KnifeConstants.NODE_NAME+"\""+messageID+"\"");
                output.newLine();
                output.append(KnifeConstants.CLIENT_KEY);
                output.newLine();
                output.append(KnifeConstants.VALIDATION_CLIENT_NAME);
                output.newLine();
                output.append(KnifeConstants.VALIDATION_KEY);
                output.newLine();
                output.append(KnifeConstants.CHEF_SERVER_URL+"\"" + chefServerUrl + "\"");
                output.newLine();
                output.append(KnifeConstants.SYNTAX_CHECK_CACHE_PATH);
                output.newLine();
                output.append(KnifeConstants.COOKBOOK_PATH);
                output.newLine();
                output.close();


                FileOutputStream validatorKeyOuputStream = 
                      new FileOutputStream(chefDir.getAbsolutePath()+"/validatorKey.pem"); 
                validatorKeyOuputStream.write(validaterKey);
                validatorKeyOuputStream.close();

                FileOutputStream clientKeyOuputStream = 
                      new FileOutputStream(chefDir.getAbsolutePath()+"/clientKey.pem"); 
                clientKeyOuputStream.write(clientKey);
                clientKeyOuputStream.close();
            }
            
        }  catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(KnifeCommandImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
    
    @Override
    public void execute(String chefClientIP, byte[] key, String messageID) {
        
        try {
            
            File keyFile = new File(KnifeConstants.CHEF_CONF_PATH_DIR + messageID + "/" + chefClientIP + ".pem");
                        
            if(!keyFile.exists()) {
                FileOutputStream clientKeyOuputStream = new FileOutputStream(KnifeConstants.CHEF_CONF_PATH_DIR + messageID + "/" + chefClientIP + ".pem"); 
                clientKeyOuputStream.write(key);
                clientKeyOuputStream.close();
            }            
                        
            //Knife boot strap command to instal chef client using ssh key

            //Runtime.getRuntime().exec("knife bootstrap 192.168.1.28 -x root -P l3tm3in");
            Process p = Runtime.getRuntime().exec("knife bootstrap -i " + KnifeConstants.CHEF_CONF_PATH_DIR + messageID + "/" + chefClientIP + ".pem" + " " + chefClientIP + " -x root");

            System.out.println("SDFsd" + "knife bootstrap -i " + KnifeConstants.CHEF_CONF_PATH_DIR + messageID + "/" + chefClientIP + ".pem" + " " + chefClientIP + " -x root");
            
            String chefout;
                        
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((chefout = in.readLine()) != null) {
                
                System.out.println(chefout);
            }
            in.close();

            // remove chef config file directory after installation
//            Runtime.getRuntime().exec("rm -rf " + KnifeConstants.CHEF_CONF_PATH_DIR + messageID);
            
        } catch (IOException ex) {
            Logger.getLogger(KnifeCommandImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
