package weather.com.com;



import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

//////////////////////////////////////////////////////////////////////////////////////////////////
//	описание:
//		главная форма виджета

public class MyWidgetProvider extends AppWidgetProvider {
	
	String this_marker = "MyWidgetProvider"; //** зададим имя маркера для логов
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.i(this_marker, "Start MyWidgetProvider");
		ComponentName thisWidget = new ComponentName(context,MyWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		Intent intent = new Intent(context.getApplicationContext(),UpdateWidgetService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
		context.startService(intent);
	}//public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds)
	
}//public class MyWidgetProvider extends AppWidgetProvider

