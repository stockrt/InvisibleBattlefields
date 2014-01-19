package lac.puc.ubi.invbat.concept.misc;

import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ConfirmationDialogFragment extends DialogFragment {
	
	String message = "message";
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	Bundle arguments = getArguments();
    	message = arguments.getString("message");
    	
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setMessage(message)
        	   .setTitle("Atenção!")
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dismiss();
                   }
               })
        	   .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dismiss();
                   }
               });
        
        return builder.create();
    }
}
