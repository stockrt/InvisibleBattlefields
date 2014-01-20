package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Random;

import lac.puc.ubi.invbat.concept.dao.ClanDAO;

import org.json.JSONException;
import org.json.JSONObject;


public class CharacterData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int clanId;
	private ClanData clan;

	private String name;
	private String email;
	private String password;
	private int num_victories;
	private int exp_points;
	private int level;
	private int base_stren;
	private int base_intel;
	private int base_agili;
	private double mod_stren;
	private double mod_intel;
	private double mod_agili;
	
	//Loaded Character
	public CharacterData(CharacterData _loaded) {		
		exp_points = _loaded.getExp_points();
		level = _loaded.getLevel();
		num_victories = _loaded.getNum_victories();
		
		clanId = _loaded.getClanId();

		base_stren = _loaded.getBase_stren();
		base_intel = _loaded.getBase_intel();
		base_agili = _loaded.getBase_agili();
		
		initAttributes();
		
		name = _loaded.getName();
		email = _loaded.getEmail();
		password = _loaded.getPassword();
	}
	
	//New Character
	public CharacterData(String _name, int _clanId) {
		int stats[];
		
		exp_points = 0;
		level = 1;
		num_victories = 0;
		
		clanId = _clanId;
		clan = null;

		stats = generateRandomBaseStats();
		
		base_stren = stats[0];
		base_intel = stats[1];
		base_agili = stats[2];
		
		initAttributes();
		
		name = _name;
	}

	public CharacterData() {
		int stats[];
		
		exp_points = 0;
		level = 1;
		num_victories = 0;
		
		clanId = 0;
		clan = null;

		stats = generateRandomBaseStats();
		
		base_stren = stats[0];
		base_intel = stats[1];
		base_agili = stats[2];
		
		initAttributes();
		
		name = "";
		
	}

	private int[] generateRandomBaseStats()
	{
		Random generator = new Random(); 
		int stats[] = new int[3];
		stats[0] = generator.nextInt(5) + 1;
		stats[1] = generator.nextInt(5) + 1;
		stats[2] = generator.nextInt(5) + 1;
		
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
		if(base_agili > highestAtValue)
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
		return (int) (base_stren * Math.ceil((double) mod_stren * level));
	}
	
	public int getAttributeIntelligence()
	{
		return (int) (base_intel * Math.ceil((double) mod_intel * level));
	}
	
	public int getAttributeAgility()
	{
		return (int) (base_agili * Math.ceil((double) mod_agili * level));
	}
	
	public double getMod_stren() {
		return mod_stren;
	}

	public void setMod_stren(double mod_stren) {
		this.mod_stren = mod_stren;
	}

	public double getMod_intel() {
		return mod_intel;
	}

	public void setMod_intel(double mod_intel) {
		this.mod_intel = mod_intel;
	}

	public double getMod_agili() {
		return mod_agili;
	}

	public void setMod_agili(double mod_agili) {
		this.mod_agili = mod_agili;
	}

	public boolean checkLevelUp() {
		
		//Exp necessaria para lvl n + 1 = Ceil[(n^(2.25) + 12*n)*raiz(n*10)]
		boolean leveled = false;
		double needed_exp = expToNextLevel();
		
		if(exp_points > needed_exp)
			leveled = true;
		
		return leveled;
	}

	public int getMainAttValue() 
	{
		int ret = getAttributeStrength();
		
		if(getAttributeAgility() > ret)
			ret = getAttributeAgility();
		
		if(getAttributeIntelligence() > ret)
			ret = getAttributeIntelligence();
		
		return ret;
	}
	
	public String getMainAttLbl() 
	{
		String ret = "STR";
		int value = getAttributeStrength();
		
		if(getAttributeAgility() > value)
		{
			ret = "AGI";
			value = getAttributeAgility();
		}
		
		if(getAttributeIntelligence() > value)
		{
			ret = "INT";
			value = getAttributeIntelligence();
		}
		
		return ret;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClanId() {
		return clanId;
	}

	public void setClanId(int clanId) {
		this.clanId = clanId;
	}

	public ClanData getClan() {
		if (clan == null) {
			ClanDAO dao = new ClanDAO();
			clan = dao.buscar(clanId); 
		}
			
		return clan;
	}

	public void setClan(ClanData clan) {
		this.clan = clan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNum_victories() {
		return num_victories;
	}

	public void setNum_victories(int num_victories) {
		this.num_victories = num_victories;
	}

	public int getExp_points() {
		return exp_points;
	}

	public void setExp_points(int exp_points) {
		this.exp_points = exp_points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBase_stren() {
		return base_stren;
	}

	public void setBase_stren(int base_stren) {
		this.base_stren = base_stren;
	}

	public int getBase_intel() {
		return base_intel;
	}

	public void setBase_intel(int base_intel) {
		this.base_intel = base_intel;
	}

	public int getBase_agili() {
		return base_agili;
	}

	public void setBase_agili(int base_agili) {
		this.base_agili = base_agili;
	}
	
	
}
