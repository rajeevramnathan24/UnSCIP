package coreUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import customException.CustomException;

public class ConfigReader {
	
	//Setting config file path name
		private static final String configFilePath = System.getProperty("user.dir") 
				+ "/src/test/resources/config/config.properties";


		/**
		 * loadPropertiesFile
		 * @throws CustomException
		 */
		public static Properties loadPropertiesFile() throws CustomException{
			
			Properties config = null;
			HashMap<String, String> propValue = new HashMap<String, String>();

			//Below code is to check for config file exist or not
			File file1 = new File(configFilePath);
			if (!file1.exists()) {
				throw new CustomException("Configuration properties file does not exists in the mentioned location : " 
			+ configFilePath);
			}
			
			//read config file
			InputStream input;
			try {
				input = new FileInputStream(configFilePath);
			} catch (FileNotFoundException e) {
				throw new CustomException("Unable to read Config Properties file :" + e.getMessage());
			}
			
			//load config file
			if (input != null) {
				try {
					config = new Properties();
					config.load(input);
					
					for (String key : config.stringPropertyNames()) {
					   
					    propValue.clear();
					    propValue.put(key, String.valueOf(input));				    				    				    
					}				
					
				} catch (IOException e) {
					throw new CustomException("Unable to load Config Properties file :" + e.getMessage());
				}
			}
			return config;
		}

}
