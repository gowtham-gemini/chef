/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fogpanel.chefstudy;

/**
 *
 * @author gowtham
 */
public interface KnifeCommand {
    
    public void execute(String chefClientIP, byte[] key, String messageID);
    
}
