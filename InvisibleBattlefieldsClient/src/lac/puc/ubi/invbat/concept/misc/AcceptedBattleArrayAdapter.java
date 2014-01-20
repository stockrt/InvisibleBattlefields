package lac.puc.ubi.invbat.concept.misc;

import java.util.List;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.comm.BattleResultRequest;
import lac.puc.ubi.invbat.concept.connection.CommunicationTask;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AcceptedBattleArrayAdapter extends ArrayAdapter<BattleData> {

	private InvBatApplication ap;
	private final Context context;
	
	private final List<BattleData> values;
	
	private BattleData thisBattle;

	public AcceptedBattleArrayAdapter(Context context, List<BattleData> values, InvBatApplication ap) 
	{
		super(context, R.layout.layout_acceptedbattle, values);
		
		this.ap = ap;
		this.context = context;
		this.values = values;

		if(ap.m_battleManager.attackingClanList.size() != values.size())
		{
			for(int i = 0; i < values.size(); i++)
			{
				ap.m_battleManager.attackingClanList.add(0);
			}
		}
		if(ap.m_battleManager.visibilityList.size() != values.size())
		{
			for(int i = 0; i < values.size(); i++)
			{
				ap.m_battleManager.visibilityList.add(View.INVISIBLE);
			}
		}
	}
	
	public void setVisibilityOfView(int position, boolean btnVisibility)
	{
		int value = View.INVISIBLE;
		
		if(btnVisibility)
		{
			value = View.VISIBLE;
		}
		
		ap.m_battleManager.visibilityList.set(position, value);
	}
	
	public void setClanIconOfView(int position, int clanIconId)
	{	
		ap.m_battleManager.attackingClanList.set(position, clanIconId);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		thisBattle = values.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.layout_acceptedbattle, parent, false);
		TextView tv_RegionName = (TextView) rowView.findViewById(R.id.lblRegionName);
		TextView tv_RemainingTime = (TextView) rowView.findViewById(R.id.lblRemainingTime);
		ImageView imgClanIcon = (ImageView) rowView.findViewById(R.id.imgClanIcon);
		ImageButton imgbtnInfo = (ImageButton) rowView.findViewById(R.id.imgbtn_info);

		tv_RegionName.setText(thisBattle.getRegionData().getName());
		tv_RemainingTime.setText(DateHelper.getTimeLimitFromTimeframeID(thisBattle.getTimeFrameId()));

		imgbtnInfo.setVisibility(ap.m_battleManager.visibilityList.get(position));
		
		switch(ap.m_battleManager.attackingClanList.get(position))
		{
			case 0:
				imgClanIcon.setImageResource(R.drawable.union);
				break;
			case 1:
				imgClanIcon.setImageResource(R.drawable.mercenaries);
				break;
			case 2:
				imgClanIcon.setImageResource(R.drawable.berserk);
				break;
			default:
		}
		
		tv_RegionName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				
			}
		});
		
		imgbtnInfo.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
			    CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "result", new BattleResultRequest(thisBattle.getId(), ap.m_player.getId()));
				task.execute();
			}
		});
		
		return rowView;
	}
	
	public int getPositionFromObj(BattleData btl)
	{
		int ret = -1;
		
		for(int i = 0; i < values.size(); i++)
		{
			if(btl.getRegionId() == values.get(i).getRegionId())
			{
				ret = i;
				break;
			}
		}
		
		return ret;
	}
}