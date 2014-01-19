package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AuthScreen extends Activity {

	private InvBatApplication ap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		
		ap = (InvBatApplication) getApplication();
	}
	
	public void AuthRoutine(View v) {

		//Toast.makeText(getBaseContext(), "Login", Toast.LENGTH_SHORT).show();
		
		//TODO Mandar objeto de autentica��o, setar servi�o de conex�o e servi�o do jogo (que fica checando localidades)
		
		boolean firstTimer = false; //um dos outputs da autentica��o aceita
		
		if(firstTimer)
		{
			
		}
		else
		{
			ap.validateCharData(); //esse m�todo provavelmente ser� um 'postExecute(true)' da autentica��o
		    Intent i = new Intent(AuthScreen.this, MainMenuScreen.class);
			startActivity(i);
		}
	}
	
	public void RegisterRoutine(View v) {
		Toast.makeText(getBaseContext(), "Cadastro!", Toast.LENGTH_SHORT).show();
	    Intent i = new Intent(AuthScreen.this, RegistrationScreen.class);
		startActivity(i);
	}
}