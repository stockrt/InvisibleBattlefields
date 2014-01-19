package lac.puc.ubi.invbat.concept.connection;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.puc.ubi.invbat.concept.app.InvBatApplication;
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
public class CommunicationTask extends AsyncTask<Void, byte[], Boolean> {
	
	/**
	 * Which ip and port to attempt a connection.
	 */
	private String ipAddress = "192.168.0.100";
	private static final int DEFAULT_SDDL_PORT = 5500;
	private UUID clientUUID = new UUID(1, 1);
	
	private NodeConnection myConnection;
	private MyNodeConnectionListener myNodeConnectionListener;
	private Handler messageHandler;
	private Context context;
	private InvBatApplication ap;
	
	private ApplicationMessage packagedMessage;
	private Serializable rawInfo;
	private List<String> tags;
	
	public CommunicationTask(Handler _handler, InvBatApplication _ap, String _tag, Serializable _rawInfo)
	{
		this.messageHandler = _handler;
		this.ap = _ap;
		this.context = ap.getBaseContext();
	    
		this.packagedMessage = new ApplicationMessage();
		this.rawInfo = _rawInfo;
		this.tags = new LinkedList<String>();
		this.tags.add(_tag);
	}

	/**
	 * Overriten methods
	 */
	@Override
	protected Boolean doInBackground(Void... arg0) 
	{
		boolean result = true;
	
	    try 
	    {
	    	if(myConnection == null)
	    	{
	    		myConnection = new MrUdpNodeConnection();
	    	}
	    	
	    	if(myNodeConnectionListener == null)
	    	{
	    		myNodeConnectionListener = new MyNodeConnectionListener(messageHandler, ap);
	    	}
	    	
	    	myConnection.addNodeConnectionListener(myNodeConnectionListener);
	
	    	SocketAddress sc = new InetSocketAddress(ipAddress, DEFAULT_SDDL_PORT);

    		myConnection.connect(sc);

	    }
    	catch (IOException e) 
    	{
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
			packagedMessage.setContentObject(rawInfo);
			packagedMessage.setTagList(tags);
			packagedMessage.setSenderID(clientUUID);
	    	
	    	try {
	    		myConnection.sendMessage(packagedMessage);
	    		result = true;
	        }
	        catch (Exception e) {
	        	result = false;
	        	e.printStackTrace();
	        }
		}
		else
			Toast.makeText(context, "Impossible to Send Info!", Toast.LENGTH_LONG).show();
	}
	  
	public NodeConnection getMyConnection() {
		return myConnection;
	}
}