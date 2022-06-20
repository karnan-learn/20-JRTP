package com.karnan.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.karnan.entity.UserDtlsEntity;

import lombok.NonNull;

@Component
public class ReadMailBody {

	private Logger logger = LoggerFactory.getLogger(ReadMailBody.class);
	public String readMailBodyContent(@NonNull String filename, @NonNull UserDtlsEntity entity) {
		String mailBody=null;
		try(	FileReader fr =new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
			) {
			StringBuilder sb = new StringBuilder();
			
			String line = br.readLine(); // reading first line data
			while(line != null) {
				sb.append(line);
				line =  br.readLine();
			}
			mailBody =  sb.toString();
			mailBody = mailBody.replace("{FNAME}", entity.getFirstName());
			mailBody = mailBody.replace("{LNAME}", entity.getLastName());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getUserPwd());
			mailBody = mailBody.replace("{EMAIL}", entity.getUserEmail());
			mailBody = mailBody.replace("{PWD}", entity.getUserPwd());
		}catch (Exception  e) {
			logger.error("Exception :: "+e.getMessage(), e);
		}
		return mailBody;
	}
}
