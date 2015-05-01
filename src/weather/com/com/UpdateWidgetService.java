package weather.com.com;

import weather.com.com.R;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;



public class UpdateWidgetService extends Service {
	
	readS y = new readS("http://star003.dlinkddns.com/03.php");
	//////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("deprecation")
	@Override
	public  void onStart(Intent intent, int startId) {
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());
		
		int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
		
		y.getDt();
		
		for (int widgetId : allWidgetIds) {
			RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName()
					,R.layout.widget_layout);
			remoteViews.setTextViewText(R.id.update	,y.cur); 
			remoteViews.setTextViewText(R.id.td 	,y.td);
			remoteViews.setTextViewText(R.id.mn 	,y.mn);
			remoteViews.setTextViewText(R.id.mx 	,y.mx);
	        
			Intent clickIntent = new Intent(this.getApplicationContext(),MyWidgetProvider.class);
			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getApplicationContext(), 0, clickIntent,PendingIntent.FLAG_UPDATE_CURRENT);
			
			remoteViews.setOnClickPendingIntent(R.id.update	, pendingIntent);
			remoteViews.setOnClickPendingIntent(R.id.td		, pendingIntent);
			remoteViews.setOnClickPendingIntent(R.id.mn		, pendingIntent);
			remoteViews.setOnClickPendingIntent(R.id.mx		, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		stopSelf();
		super.onStart(intent, startId);
		
	}//public  void onStart(Intent intent, int startId)
	
	//////////////////////////////////////////////////////////////////////////////////////////////////    
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}//public IBinder onBind(Intent intent)
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
}//public class UpdateWidgetService extends Service
