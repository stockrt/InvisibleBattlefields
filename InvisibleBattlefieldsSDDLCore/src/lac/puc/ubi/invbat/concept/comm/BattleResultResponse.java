package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import lac.puc.ubi.invbat.concept.model.BattleResultData;

public class BattleResultResponse implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        
        private BattleResultData battleResultData;
        
        public BattleResultResponse(BattleResultData _battleResultData)
        {
                battleResultData = _battleResultData;
        }
        
        public BattleResultData getBattleResultData() {
                return battleResultData;
        }

        public void setBattleResultData(BattleResultData battleResultData) {
                this.battleResultData = battleResultData;
        }
}