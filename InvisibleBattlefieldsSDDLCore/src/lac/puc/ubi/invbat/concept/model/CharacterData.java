package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class CharacterData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;
	public int num_victories;
	public int exp_points;
	public int level;

	public int clanId;
	
	private int base_stren;
	private int base_intel;
	private int base_agili;
	private double mod_stren;
	private double mod_intel;
	private double mod_agili;
	
	//Loaded Character
	public CharacterData(CharacterData _loaded) {
		exp_points = _loaded.exp_points;
		level = _loaded.level;
		num_victories = _loaded.num_victories;
		
		clanId = _loaded.clanId;

		base_stren = _loaded.base_stren;
		base_intel = _loaded.base_intel;
		base_agili = _loaded.base_agili;
		
		initAttributes();
		
		name = _loaded.name;
	}
	
	//New Character
	public CharacterData(String _name, int _id) {
		int stats[];
		
		exp_points = 0;
		level = 1;
		num_victories = 0;
		
		clanId = _id;

		stats = generateRandomBaseStats();
		
		base_stren = stats[0];
		base_intel = stats[1];
		base_agili = stats[2];
		
		initAttributes();
		
		name = _name;
	}

	private int[] generateRandomBaseStats()
	{
		int stats[] = new int[3];
		stats[0] = (new Random()).nextInt(); //TODO: consertar o acesso a esse random depois
		stats[1] = (new Random()).nextInt();
		stats[2] = (new Random()).nextInt();
		
		return stats;
	}
	
	private void initAttributes()
	{
		int highestAtValue = base_stren;
		int highestAtIndex = 0;
		
		if(base_intel > highestAtValue)
		{
			highestAtValue = base_intel;
			highestAtIndex = 1;
		}
		if(base_agili < highestAtValue)
		{
			highestAtValue = base_agili;
			highestAtIndex = 2;
		}
		
		switch(highestAtIndex)
		{
			case 0: //Strength beats Agility
				mod_stren = 1.3;
				mod_intel = 1.0;
				mod_agili = 0.7;
				break;
			case 1: //Intelligence beats Strength
				mod_stren = 0.7;
				mod_intel = 1.3;
				mod_agili = 1.0;
				break;
			case 2: //Agility beats Intelligence
				mod_stren = 0.7;
				mod_intel = 1.0;
				mod_agili = 1.3;
				break;
		}
	}
	
	public int getAttributeStrength()
	{
		return (int) (base_stren * Math.floor(mod_stren * level));
	}
	
	public int getAttributeIntelligence()
	{
		return (int) (base_intel * Math.floor(mod_intel * level));
	}
	
	public int getAttributeAgility()
	{
		return (int) (base_agili * Math.floor(mod_agili * level));
	}
	
	public boolean checkLevelUp() {
		
		//Exp necessária para lvl n + 1 = Ceil[(n^(2.25) + 12*n)*raiz(n*10)]
		boolean leveled = false;
		double needed_exp = expToNextLevel();
		
		if(exp_points > needed_exp)
			leveled = true;
		
		return leveled;
	}
	
	public double expToNextLevel()
	{
		return Math.ceil((Math.pow(level, 2.25) + 12*level)*Math.sqrt(level*10));
	}
	
	/**
     * {@inheritDoc}
     **/
    @Override
    public String toString() {

    	JSONObject charinfo = new JSONObject();
		
		try {			
			charinfo.put("name", name);
			charinfo.put("victories", num_victories);
			charinfo.put("exp", exp_points);	
			charinfo.put("level", level);
			charinfo.put("clanid", clanId);
			charinfo.put("base_stren", base_stren);
			charinfo.put("base_intel", base_intel);	
			charinfo.put("base_agili", base_agili);
			charinfo.put("mod_stren", mod_stren);
			charinfo.put("mod_intel", mod_intel);	
			charinfo.put("mod_agili", mod_agili);

			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return charinfo.toString();
	}
}
