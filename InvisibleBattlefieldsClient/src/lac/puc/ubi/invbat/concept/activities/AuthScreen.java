package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.comm.LoginRequest;
import lac.puc.ubi.invbat.concept.connection.CommunicationTask;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AuthScreen extends Activity {

	private InvBatApplication ap;

	private EditText etxtEmail;
	private EditText etxtPass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		
		ap = (InvBatApplication) getApplication();

		etxtEmail = (EditText) findViewById(R.id.etxt_email);
		etxtPass = (EditText) findViewById(R.id.etxt_pass);
	}
	
	public void AuthRoutine(View v) {

		CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "auth", new LoginRequest(etxtEmail.getText().toString(), etxtPass.getText().toString()));
		task.execute();

		/** DEBUG */
		ap.handleLoginAuthorization(true, null);
	}
	
	public void RegisterRoutine(View v) {
	    Intent i = new Intent(AuthScreen.this, RegistrationScreen.class);
		startActivity(i);
	}
}