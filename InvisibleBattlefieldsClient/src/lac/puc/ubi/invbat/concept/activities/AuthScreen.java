package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AuthScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
	}
	
	public void AuthRoutine(View v) {

		//TODO: enviar obj de loginrequest
	}
	
	public void RegisterRoutine(View v) {
	    Intent i = new Intent(AuthScreen.this, RegistrationScreen.class);
		startActivity(i);
	}
}