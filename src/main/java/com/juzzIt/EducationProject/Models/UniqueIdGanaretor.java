package com.juzzIt.EducationProject.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UniqueIdGanaretor {

	
	

	public static String generateUniqueId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMDD");
        String timestamp = dateFormat.format(new Date());

        Random random = new Random();
        int randomNum = random.nextInt(100);

        return timestamp + randomNum;
    }
}
