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
public interface KnifeConstants {
        
    public String CURRENT_DIR = "current_dir = File.dirname(__FILE__)";
    
    public String LOG_LEVEL = "log_level                :info";
    
    public String LOG_LOCATION = "log_location             STDOUT";
    
    public String SYNTAX_CHECK_CACHE_PATH = "syntax_check_cache_path  \"#{ENV['HOME']}/.chef/syntaxcache\"";
    
    public String COOKBOOK_PATH = "cookbook_path            [\"#{current_dir}/../cookbooks\"]";
    
    public String NODE_NAME = "node_name                ";
    
    public String CLIENT_KEY = "client_key               \"#{current_dir}/clientKey.pem\"";
    
    public String VALIDATION_CLIENT_NAME = "validation_client_name  \"validatorKey\"";
    
    public String VALIDATION_KEY = "validation_key            \"#{current_dir}/validatorKey.pem\"";
    
    public String CHEF_SERVER_URL = "chef_server_url          ";
    
    public String CHEF_CONF_PATH_DIR = "/etc/";
    
    public String KNIFE_RB_NAME = "knife.rb";
}
