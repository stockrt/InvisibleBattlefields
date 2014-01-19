package lac.puc.ubi.invbat.concept.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

/**
 * AsynchTask para tentativa de conexão
 * 
 * @author andremd
 *
 */
public class ConnectionTask extends AsyncTask<Void, byte[], Boolean> {

	private String ipAddress;
	private NodeConnection myConnection;
	private MyNodeConnectionListener myNodeConnectionListener;
	private Handler messageHandler;
	private Context context;
  
	/**
	 * Which port to attempt a connection.
	 */
	private static final int DEFAULT_SDDL_PORT = 5500;

	public ConnectionTask(String ip, Handler handler, Context apContext)
	{
		ipAddress = ip;
	    messageHandler = handler;
	    context = apContext;
	}

	/**
	 * Overriten methods
	 */
	@Override
	protected Boolean doInBackground(Void... arg0) 
	{
		boolean result = true;
	    
		if (ipAddress.equals(""))
	    	ipAddress = "192.168.0.100";
	
	    try {
	    	myConnection = new MrUdpNodeConnection();
	    	myNodeConnectionListener = new MyNodeConnectionListener(messageHandler);
	    	myConnection.addNodeConnectionListener(myNodeConnectionListener);
	
	    	SocketAddress sc = new InetSocketAddress(ipAddress, DEFAULT_SDDL_PORT);

    		myConnection.connect(sc);

	    }
    	catch (IOException e) {
    		result = false;
    		e.printStackTrace();
    	}
	
	    return result;
	}
	
	@Override
	protected void onPostExecute(Boolean result) 
	{
		if(result)
		{
			RequestTask authTask = new RequestTask(myConnection, authInfo.getUuid(), authInfo.getTypeName(), authInfo.toString());
			authTask.execute();
		}
		else
			Toast.makeText(context, "Impossible to Connect!", Toast.LENGTH_LONG).show();
	}
	  
	public NodeConnection getMyConnection() {
		return myConnection;
	}
}