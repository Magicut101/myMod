package theHeart.actions;

/*    */
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class ExhaustDrawPileWounds
            /*    */ extends AbstractGameAction
            /*    */ {
        /* 17 */   private float startingDuration = Settings.ACTION_DUR_FAST;

        /*    */
        /*    */
        public void update() {
            /* 23 */
            if (this.duration == this.startingDuration) {
                /* 24 */
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    /* 25 */
                    if (c.type == AbstractCard.CardType.STATUS) {
                        /* 26 */
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                        /*    */
                    }

                    /*    */
                }
            }
            }
    }
