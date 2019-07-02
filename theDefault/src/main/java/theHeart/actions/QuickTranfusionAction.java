package theHeart.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

//This card is mostly broken
public class QuickTranfusionAction
        /*    */   extends AbstractGameAction
        /*    */ {
    /*    */   private int StrengthAmount;
    /*    */   private DamageInfo info;
    /*    */   private static final float DURATION = 0.1F;
    /*    */
    /*    */   public QuickTranfusionAction(AbstractCreature target, DamageInfo info, int Strength) {
        /* 23 */     this.info = info;
        /* 24 */     setValues(target, info);
                    int increaseStrengthAmount = Strength;
        /* 26 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
        /* 27 */     this.duration = 0.1F;
        /*    */   }
    /*    */
    /*    */
    /*    */   public void update() {
        /* 32 */     if (this.duration == 0.1F &&
                /* 33 */       this.target != null) {
            /* 34 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            /* 35 */       this.target.damage(this.info);
            /*    */
            /* 37 */       if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead ) {
                /* 39 */         AbstractDungeon.actionManager.addToBottom
                        (new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                new StrengthPower(AbstractDungeon.player, 3), StrengthAmount,  false));
                /*    */
                /*    */
                /* 42 */
                /*    */       }
            /*    */
            /* 47 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                /* 48 */         AbstractDungeon.actionManager.clearPostCombatActions();
                /*    */       }
            /*    */     }
        /*    */
        /*    */
        /* 53 */     tickDuration();
        /*    */   }
    /*    */ }