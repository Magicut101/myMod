package theHeart.actions;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */
public class GainDrawEnergyPerNonAttackAction extends AbstractGameAction {
    /*    */   public  GainDrawEnergyPerNonAttackAction(int EnergyDrawAmount) {
        /* 10 */     this.BLOCKGAIN_AMOUNT = 0;
        /*    */
        /*    */
        /* 13 */     this.BLOCKGAIN_AMOUNT =  EnergyDrawAmount;
        /* 14 */     setValues(AbstractDungeon.player, AbstractDungeon.player);
        /* 15 */     this.actionType = AbstractGameAction.ActionType.BLOCK;
        /*    */   }
    /*    */   private int BLOCKGAIN_AMOUNT;
    /*    */
    /*    */   public void update() {
        /* 20 */     if (!this.isDone) {
            /* 21 */       this.isDone = true;
            /* 22 */       int total = 0;
            /* 23 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
                /* 24 */         if (c.type == AbstractCard.CardType.STATUS) {
                    /* 25 */           AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                    /*    */
                    /* 27 */           total += this.BLOCKGAIN_AMOUNT;
                    /*    */         }
                /*    */       }
                AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, total, true));
                AbstractDungeon.actionManager.addToTop(new GainEnergyAction((total)));


            /*    */     }
        /*    */   }
    /*    */ }

