package theHeart.actions;


        /*    */
        /*    */ import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
        /*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        /*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
        /*    */ import com.megacrit.cardcrawl.core.Settings;
        /*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        /*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */
/*    */ public class CorruptAction extends AbstractGameAction {
    /*    */   private float startingDuration;
    /*    */
    /*    */   public CorruptAction(AbstractCreature target, AbstractCreature source) {
        /* 14 */     this.target = target;
        /* 15 */     this.source = source;
        /* 16 */     this.startingDuration = Settings.ACTION_DUR_FAST;
        /* 17 */     this.actionType = AbstractGameAction.ActionType.DEBUFF;
        /* 18 */     this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
        /* 19 */     this.duration = this.startingDuration;
        /*    */   }
    /*    */
    /*    */ // This looks VERY VERY ugly,  I will ask in the discord if there is some better way to get debuffs from monsters instead of hard coding Power String names.
    /*    */   public void update() {
        /* 24 */     if (this.duration == this.startingDuration &&
                /* 25 */       this.target.hasPower("Poison")) {
            /* 26 */       AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source,
                    /*    */
                    /*    */
                    /*    */
                    /* 30 */               (this.target.getPower("Poison")).amount * 3),
                    /* 31 */             (this.target.getPower("Poison")).amount * 3));
            /*    */     }
        /*    */  if (this.duration == this.startingDuration &&
                /* 25 */       this.target.hasPower("Vulnerable")) {
            /* 26 */       AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source,
                    /*    */
                    /*    */
                    /*    */
                    /* 30 */               (this.target.getPower("Vulnerable")).amount * 3),
                    /* 31 */             (this.target.getPower("Vulnerable")).amount * 3));
            /*    */     }
        /*    */      if (this.duration == this.startingDuration &&
                /* 25 */       this.target.hasPower("Weakened")) {
            /* 26 */       AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source,
                    /*    */
                    /*    */
                    /*    */
                    /* 30 */               (this.target.getPower("Weakened")).amount * 3),
                    /* 31 */             (this.target.getPower("Weakened")).amount * 3));
            }
        if (this.duration == this.startingDuration &&
                /* 25 */       this.target.hasPower("Constricted")) {
            /* 26 */       AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source,
                    /*    */
                    /*    */
                    /*    */
                    /* 30 */               (this.target.getPower("Constricted")).amount * 3),
                    /* 31 */            (this.target.getPower("Constricted")).amount * 3));
              }


         tickDuration();
        isDone = true;

        /*    */   }
 }