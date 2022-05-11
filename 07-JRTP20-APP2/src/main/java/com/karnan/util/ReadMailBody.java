package com.karnan.util;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import com.karnan.entity.UserDtlsEntity;

@Component
public class ReadMailBody {

	public String readMailBodyContent(String filename, UserDtlsEntity entity) {
		String mailBody=null;
		try {
			StringBuffer sb = new StringBuffer();
			FileReader fr =new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
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
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
}
