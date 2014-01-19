package lac.puc.ubi.invbat.concept.connection;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.List;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.sddl.serialization.Serialization;
import modellibrary.RequestInfo;
import modellibrary.ResponseInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Listener para mensagens do Controlador. 
 * Separa as mensagens e envia para tratamento no handler registrado.
 * 
 * @author andremd
 *
 */
public class MyNodeConnectionListener implements NodeConnectionListener {

	private Handler handler;

	public MyNodeConnectionListener(Handler handler) {
		this.handler = handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	private void handleNewStatus(String status) {
		Bundle bund = new Bundle();
		bund.putString("status", status);
		Message msg = new Message();
		msg.setData(bund);
		handler.sendMessage(msg);
	}

	@SuppressWarnings("unused")
	private void handleNewMessage(String message) {
		Bundle bund = new Bundle();
		bund.putString("status", "message");
		bund.putString("message", message);
		Message msg = new Message();
		msg.setData(bund);
		handler.sendMessage(msg);
	}

	private void handleNewResponse(ResponseInfo obj) {
		Bundle bund = new Bundle();
		bund.putString("status", "response");
		bund.putString("type", obj.getType());
		bund.putString("data", obj.getPayload());
		Message msg = new Message();
		msg.setData(bund);
		handler.sendMessage(msg);
	}
	
	private void handleNewRequest(RequestInfo obj) {
		Bundle bund = new Bundle();
		bund.putString("status", "request");
		bund.putString("type", obj.getType());
		bund.putString("data", obj.getPayload());
		Message msg = new Message();
		msg.setData(bund);
		handler.sendMessage(msg);
	}

	public void connected(NodeConnection remoteCon) {
		handleNewStatus("connected");
	}

	public void reconnected(NodeConnection remoteCon, SocketAddress endPoint,
			boolean wasHandover, boolean wasMandatory) {
		handleNewStatus("reconnected - newend=" + endPoint + " handover="
				+ wasHandover + " mandatory=" + wasMandatory);
	}

	public void disconnected(NodeConnection remoteCon) {
		handleNewStatus("disconnected");
	}

	public void newMessageReceived(NodeConnection remoteCon, lac.cnclib.sddl.message.Message message) 
	{
		String className = message.getContentObject().getClass().getCanonicalName();
		Serializable s = Serialization.fromJavaByteStream(message.getContent());
		if (className != null) 
		{
			if (className.equals(RequestInfo.class.getCanonicalName())) 
				handleNewRequest((RequestInfo) s);
			else if(className.equals(ResponseInfo.class.getCanonicalName()))
				handleNewResponse((ResponseInfo) s);
		}
		else 
		{
			handleNewStatus("new object received: " + message.getContentObject().toString());
		}
	}

	public void unsentMessages(NodeConnection remoteCon, List<lac.cnclib.sddl.message.Message> unsentMessages) 
	{
		handleNewStatus("objects not sent: " + unsentMessages.size());
	}

	public void internalException(NodeConnection remoteCon, Exception e) 
	{
		handleNewStatus("connection internal exception " + e);
	}

}
