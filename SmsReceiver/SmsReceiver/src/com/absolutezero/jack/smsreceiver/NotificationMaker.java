package com.absolutezero.jack.smsreceiver;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Context;

public class NotificationMaker {
	
	private Notification notif;
	private int id;
	private Context context;
	
	public NotificationMaker(Context mContext, String title, String content){
		context = mContext;
		notif = new Notification.Builder(mContext)
				.setContentTitle(title)
				.setContentText(content)
				.setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher).build();
	}
	
	public void sendNotification(int aId){
		id = aId;
		NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notifManager.notify(id, notif);
	}
	
}
