package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AuthScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
	}
	
	public void AuthRoutine(View v) {

		Toast.makeText(getBaseContext(), "Opa", Toast.LENGTH_SHORT).show();
		
		//TODO Mandar objeto de autenticação, setar serviço de conexão e serviço do jogo (que fica checando localidades)
		
		boolean firstTimer = true; //um dos outputs da autenticação aceita
		
		if(firstTimer)
		{
			
		}
		else
		{
			fetchPlayerData(); //esse método provavelmente será um 'postExecute(true)' da autenticação
		    Intent i = new Intent(AuthScreen.this, MainMenuScreen.class);
			startActivity(i);
		}
	}

	/* Temporário */
	private void fetchPlayerData() {
		InvBatApplication ap = (InvBatApplication) getApplication();
	}
}