package lac.puc.ubi.invbat.concept.misc;

import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TextDialogFragment extends DialogFragment {
	
	private int clanId = -1;
	String clanName= "clan";
	String clanDescription = "description";
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	Bundle arguments = getArguments();
    	clanId = arguments.getInt("clanid");
    	
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        if(clanId != -1)
        {
        	clanName = getResources().getStringArray(R.array.clan_name_array)[clanId];
        	clanDescription = getResources().getStringArray(R.array.clan_description_array)[clanId];
        }
        
        builder.setMessage(clanDescription)
        	   .setTitle(clanName)
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dismiss();
                   }
               });
        
        return builder.create();
    }
}
