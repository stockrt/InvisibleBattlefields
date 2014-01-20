package lac.puc.ubi.invbat.concept.misc;

import java.util.List;

import lac.puc.ubi.invbat.concept.activities.BattleScreen;
import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PendingBattleArrayAdapter extends ArrayAdapter<BattleData> {

	private InvBatApplication ap;
	private final Context context;
	private final List<BattleData> values;
	private BattleData thisBattle;
	
	public PendingBattleArrayAdapter(Context context, List<BattleData> values, InvBatApplication ap) 
	{
		super(context, R.layout.layout_pendingbattle, values);
		
		this.ap = ap;
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		thisBattle = values.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.layout_pendingbattle, parent, false);
		TextView tv_RegionName = (TextView) rowView.findViewById(R.id.lblRegionName);
		TextView tv_RemainingTime = (TextView) rowView.findViewById(R.id.lblRemainingTime);
		ImageButton imgbtn_Yes = (ImageButton) rowView.findViewById(R.id.imgbtnYes);
		ImageButton imgbtn_No = (ImageButton) rowView.findViewById(R.id.imgbtnNo);

		tv_RegionName.setText(thisBattle.getRegionData().getName());
		tv_RemainingTime.setText(DateHelper.getTimeLimitFromTimeframeID(thisBattle.getTimeFrameId()));
		
		tv_RegionName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: abrir mapa com a região
			}
		});
		
		imgbtn_Yes.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{   
			    Intent i = new Intent(context, BattleScreen.class);
			    i.putExtra("battledata", values.get(position));
			    i.putExtra("clanID", ap.m_player.getClanId());
			    ((Activity) context).startActivityForResult(i, 1);
			}
		});

		imgbtn_No.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				ap.m_battleManager.removePendingBattle(values.get(position));
				notifyDataSetChanged();
				Toast.makeText(getContext(), "Batalha removida!", Toast.LENGTH_SHORT).show();
			}
		});
		
		return rowView;
	}
}