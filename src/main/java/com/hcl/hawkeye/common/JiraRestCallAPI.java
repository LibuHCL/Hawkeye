package com.hcl.hawkeye.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.hcl.hawkeye.Exceptions.NoProjectDetailsException;

@Component
public class JiraRestCallAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(JiraRestCallAPI.class);
	
	@Autowired
	MessageSource messageSource;
		
	public String callRestAPI(String url) {
		logger.info("Requested to get the details with url - {}", url);
		try {
			HttpURLConnection.setFollowRedirects(false);
			URL restUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) restUrl.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", "Basic YWRtaW46SmlyYUAxMjM=");
	        int responseCode = conn.getResponseCode();
	        if(responseCode == HttpURLConnection.HTTP_OK)
		    {
	            String str = "";
	            StringBuffer strBuf = new StringBuffer();
	            DataInputStream input = null;
	            try {
	            	 input = new DataInputStream (conn.getInputStream());
                     while (null != ((str = input.readLine()))) {
                                     strBuf.append(str);
                                     strBuf.append("\n");
                     }
                     input.close ();
	            }
	            catch (IOException ex) {
	            	Locale locale=new Locale("en", "IN");
	    			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
	    			logger.error(errorMsg, ex);
	    			throw new NoProjectDetailsException(errorMsg, ex);
	            }
	            finally {
	                if(conn != null) {
	                	conn.disconnect();
	                }
	            }
	            return strBuf.toString();
		    }       
		}
		catch (IOException e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return null;
		
	}

}
