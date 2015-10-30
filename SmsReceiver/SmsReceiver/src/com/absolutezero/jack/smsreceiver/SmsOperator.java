package com.absolutezero.jack.smsreceiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import android.telephony.SmsManager;
import android.util.Log;

public class SmsOperator {

	private String smsText;
	private String smsAdress;
	private Map<String, Integer> actions;
	
	public SmsOperator(String anAdress, String aText){
		
		smsAdress = anAdress;
		smsText = aText;
		
		actions = new HashMap<String, Integer>();
		
		//possible actions setup
		actions.put("toast", 1);
		actions.put("hate", 2);
	}
	
	public int findAction(){
		String action = "";
		Scanner scan = new Scanner(smsText);
		
		while(scan.hasNext()){
			String temp = scan.next();
			if(actions.containsKey(temp))
					return actions.get(temp).intValue();
		}
		
		return -1;	//no action found;
	}
	
	public void operateSms(){
		int action = this.findAction();
		
		if(action > 0){
		
		String message = "";
		
		switch (action){
		case 1: message = "Hello man";
			break;
		case 2: message = "Maledetto!";
			break;
		}
		
		SmsManager manager = SmsManager.getDefault();
		
		manager.sendTextMessage(smsAdress, null, message, null, null);
		
		Log.d("OPERATOR", "sent message");
		}
		
		Log.d("OPERATOR", "nothing to operate on");
	}
	
}
