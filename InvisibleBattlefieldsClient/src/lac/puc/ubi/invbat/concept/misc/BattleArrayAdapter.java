package lac.puc.ubi.invbat.concept.misc;

import java.util.HashMap;
import java.util.List;

import lac.puc.ubi.invbat.concept.model.BattleData;
import android.content.Context;
import android.widget.ArrayAdapter;

public class BattleArrayAdapter extends ArrayAdapter<BattleData> {

    HashMap<BattleData, Integer> mIdMap = new HashMap<BattleData, Integer>();

    public BattleArrayAdapter(Context context, int textViewResourceId, List<BattleData> objects) 
    {
    	super(context, textViewResourceId, objects);
    	
    	for (int i = 0; i < objects.size(); ++i) {
    		mIdMap.put(objects.get(i), i);
    	}
    }

    @Override
    public long getItemId(int position) {
    	BattleData item = getItem(position);
    	return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
    	return true;
    }

}