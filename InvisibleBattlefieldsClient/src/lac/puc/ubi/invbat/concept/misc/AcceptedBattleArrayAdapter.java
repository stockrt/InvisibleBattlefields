package lac.puc.ubi.invbat.concept.misc;

import java.util.List;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.comm.BattleResultRequest;
import lac.puc.ubi.invbat.concept.connection.CommunicationTask;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
	}
	
	public void setVisibilityOfView(int position, boolean btnVisibility)
	{
		//TODO: setar corretamente o imgClanIcon e a visibilidade
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
		
		imgbtnInfo.setVisibility(4);
		
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
				Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
			    CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "result", new BattleResultRequest(thisBattle.getId(), ap.m_player.getId()));
				task.execute();
			}
		});
		
		return rowView;
	}
}