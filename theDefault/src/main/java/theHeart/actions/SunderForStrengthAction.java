package theHeart.actions;


        /*    */
        /*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
        /*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        /*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
        /*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
        /*    */ import com.megacrit.cardcrawl.core.Settings;
        /*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        /*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
        /*    */ import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

    public class SunderForStrengthAction
            extends AbstractGameAction
        /*    */ {
    /*    */   private int getStrAmt;
    /*    */   private DamageInfo info;
    /*    */
    /*    */   public SunderForStrengthAction (AbstractCreature target, DamageInfo info, int StrAmt) {
        /* 20 */     this.info = info;
        /* 21 */     setValues(target, info);
        /* 22 */     this.getStrAmt = StrAmt;

        /* 23 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
        /* 24 */     this.duration = Settings.ACTION_DUR_FASTER;
        /*    */   }
    /*    */
    /*    */
    /*    */   public void update() {
        /* 29 */     if (this.duration == Settings.ACTION_DUR_FASTER &&
                /* 30 */       this.target != null) {
            /* 31 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            /*    */
            /* 33 */       this.target.damage(this.info);
            /*    */
            /* 35 */       if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
                /* 36 */         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, target, new GainStrengthPower(target, getStrAmt), getStrAmt, true));
                /*    */       }
            /*    */
            /* 39 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                /* 40 */         AbstractDungeon.actionManager.clearPostCombatActions();
                /*    */       }
            /*    */     }
        /*    */
        /*    */
        /* 45 */     tickDuration();
        this.isDone =true;
        /*    */   }
    /*    */ }


