package theHeart.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.cards.DamageInfo;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class BloodWallAction
         extends AbstractGameAction {
    private DamageInfo info;


    public BloodWallAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
        /* 18 */

        // I am going to look at how
        //0 0 Aspiration uses aftershock
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.BLOCK;/* 21 */
        this.attackEffect = effect;

    }

    @Override
    public void update() {

    }

    public int onAttacked(DamageInfo info, int dmgAmount) {
        amount = 1;
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && dmgAmount > 0 && info.type == DamageInfo.DamageType.NORMAL && info.owner != null)
            ;
        return dmgAmount;

    }

}
