package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.comm.RegistrationRequest;
import lac.puc.ubi.invbat.concept.connection.CommunicationTask;
import lac.puc.ubi.invbat.concept.misc.TextDialogFragment;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationScreen extends FragmentActivity {

	private InvBatApplication ap;

	private EditText etxtEmail;
	private EditText etxtName;
	private EditText etxtPass;
	private Spinner spClan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registry);

		ap = (InvBatApplication) getApplication();

		spClan = (Spinner) findViewById(R.id.spClans);
		etxtEmail = (EditText) findViewById(R.id.etxt_regemail);
		etxtName = (EditText) findViewById(R.id.etxt_regcharname);
		etxtPass = (EditText) findViewById(R.id.etxt_regpass);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.clan_name_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spClan.setAdapter(adapter);
	}

	public void RegistrationRoutine(View v)
	{
		CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "register", new RegistrationRequest(etxtEmail.getText().toString(), etxtName.getText().toString(), etxtPass.getText().toString(), spClan.getSelectedItemPosition()));
		task.execute();
	}
	
	public void ShowClanDescriptionPopUp(View v)
	{		
		TextDialogFragment dialog = new TextDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("clanid", spClan.getSelectedItemPosition());
		dialog.setArguments(bundle);
		dialog.show(getSupportFragmentManager(), "ClanDescriptionFragment");
	}
}
