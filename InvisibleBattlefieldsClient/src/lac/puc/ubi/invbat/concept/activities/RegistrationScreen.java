package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invbat.concept.misc.TextDialogFragment;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegistrationScreen extends FragmentActivity {
	
	private Spinner spClan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registry);
		
		spClan = (Spinner) findViewById(R.id.spClans);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.clan_name_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spClan.setAdapter(adapter);
	}

	public void RegistrationRoutine(View v)
	{
		
		
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
