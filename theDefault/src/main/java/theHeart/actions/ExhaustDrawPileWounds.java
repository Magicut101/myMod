package theHeart.actions;

/*    */
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

//The class name is a lie, it actually exhausts cards in discard pile AND now I have decided also the Status Pile.
public class ExhaustDrawPileWounds
            /*    */ extends AbstractGameAction
            /*    */ {


        public void update() {

                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    /* 25 */
                    if (c.type == AbstractCard.CardType.STATUS) {
                        /* 26 */
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                        { if (c.upgraded == true ) { AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
                        }
                        }
                        }
                    }

                }
            }


