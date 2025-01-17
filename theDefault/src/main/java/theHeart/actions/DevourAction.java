package theHeart.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class DevourAction
        /*    */   extends AbstractGameAction
        /*    */ {
    private final AbstractCreature owner;
    /*    */   private int GainStrengthInt;
    /*    */   private DamageInfo info;
    /*    */   private static final float DURATION = 0.1F;

    /*    */
    /*    */   public  DevourAction(AbstractCreature target, DamageInfo info, int StrengthAmount, AbstractCreature owner) {
     this.info = info;
     setValues(target, info);
      this.GainStrengthInt =  StrengthAmount;
      this.actionType = AbstractGameAction.ActionType.DAMAGE;
      this.duration = 0.1F;
    this.owner = owner;
 }

   public void update() {

      if (this.duration == 0.1F &&
             this.target != null) {
          AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
          this.target.damage(this.info);
           if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner ,owner,new StrengthPower(owner, GainStrengthInt)));

                }

             if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                 AbstractDungeon.actionManager.clearPostCombatActions();
                }
         }
      tickDuration();
    }
}

