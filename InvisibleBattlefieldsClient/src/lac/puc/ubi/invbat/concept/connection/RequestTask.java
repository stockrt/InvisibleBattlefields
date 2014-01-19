package lac.puc.ubi.invbat.concept.connection;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import modellibrary.RequestInfo;
import android.os.AsyncTask;

/**
 * AsynchTask para envio de requests e responses para o core
 * 
 * @author andremd
 *
 */
public class RequestTask extends AsyncTask<Void, byte[], Boolean> {
	
	private NodeConnection myConnection;
	private RequestInfo rawInfo;
	private ApplicationMessage packagedMessage;
	private List<String> tags;
	
	public RequestTask(NodeConnection con, UUID uuid, String _type, String _payload)
	{
		myConnection = con;

		//Construindo o serializavel a ser enviado
		rawInfo = new RequestInfo(uuid, _type, _payload);
		packagedMessage = new ApplicationMessage();
    	tags = new LinkedList<String>();
        tags.add("authentication");
	}
	
	@Override
	protected Boolean doInBackground(Void... arg0) 
	{		
		Boolean result = false;
		
        //Empacotando o serializavel na mensagem
		packagedMessage.setContentObject(rawInfo);
		packagedMessage.setTagList(tags);
		packagedMessage.setSenderID(rawInfo.getUuid());
    	
    	try {
    		myConnection.sendMessage(packagedMessage);
    		result = true;
        }
        catch (Exception e) {
        	result = false;
        	e.printStackTrace();
        }

		return result;
	}
}
