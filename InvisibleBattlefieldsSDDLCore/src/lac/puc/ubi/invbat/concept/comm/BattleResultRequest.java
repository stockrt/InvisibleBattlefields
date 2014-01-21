package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

public class BattleResultRequest implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        
        private int battleId;
        private int charId;
        private int clanId;
        
        public BattleResultRequest(int _battleId, int _charId, int _clanId)
        {
                battleId = _battleId;
                charId = _charId;
                clanId = _clanId;
        }

		public int getBattleId() {
			return battleId;
		}

		public void setBattleId(int battleId) {
			this.battleId = battleId;
		}

		public int getCharId() {
			return charId;
		}

		public void setCharId(int charId) {
			this.charId = charId;
		}

		public int getClanId() {
			return clanId;
		}

		public void setClanId(int clanId) {
			this.clanId = clanId;
		}
        

}