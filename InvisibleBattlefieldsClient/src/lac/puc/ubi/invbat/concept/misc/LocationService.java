package lac.puc.ubi.invbat.concept.misc;

import java.util.Timer;
import java.util.TimerTask;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.comm.LocationRequest;
import lac.puc.ubi.invbat.concept.connection.CommunicationTask;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationService extends Service implements LocationListener
{
	private final static String TAG = "LocationLoggerService";
	LocationManager lm;
	
	private MyTimerTask mTimerTask;
	private Timer mTimer;
	
	private LocationRequest rawData;
	private InvBatApplication ap;
	
	public LocationService() 
	{
	 
	}
	
	@Override
	public void onCreate() 
	{
	    mTimer = new Timer();
	    mTimerTask = new MyTimerTask();
	    rawData = new LocationRequest(0, 0);
	    ap = (InvBatApplication) getApplication();
	    
	    subscribeToLocationUpdates();
	} 
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
	    mTimer.schedule(mTimerTask, 0, 5000);
	    
	    return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) 
	{
	    return null;
	}
	
	private class MyTimerTask extends TimerTask 
	{
	    @Override
	    public void run()
	    {
	    	CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "location", rawData);
	    	task.execute();
	    }
	}

	@Override
	public void onLocationChanged(Location loc) 
	{
		rawData.setLatitude(loc.getLatitude());
		rawData.setLongitude(loc.getLongitude());
	}

	@Override
	public void onProviderDisabled(String arg0) 
	{
		
	}	

	@Override
	public void onProviderEnabled(String provider) 
	{
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		
	}
	
	private void subscribeToLocationUpdates() 
	{
        this.lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        this.lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}
}


