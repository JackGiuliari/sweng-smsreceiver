package com.absolutezero.jack.smsreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle intentExtra = intent.getExtras();
		
		if(intentExtra != null){
			Object[] extraPdus = (Object[]) intentExtra.get("pdus");
			SmsMessage[] newSms = new SmsMessage[extraPdus.length];
			String smsText = "";
			String smsAddress = "";

			for(int i = 0; i< newSms.length; i++){
				newSms[i] = SmsMessage.createFromPdu((byte[]) extraPdus[i]);
				smsText = smsText + newSms[i].getMessageBody();
				smsAddress = newSms[i].getDisplayOriginatingAddress();
			}
			
			Log.d("MESSAGE", smsText);
			Log.d("ADDRESS", smsAddress);
			
			Notification notif = new Notification.Builder(context)
					.setContentTitle(smsAddress)
					.setContentText(smsText)
					.setAutoCancel(true)
					.setSmallIcon(R.drawable.ic_launcher).build();
			
			NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			notifManager.notify(0, notif);
		}
	}
}