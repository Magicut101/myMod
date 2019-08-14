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
    public GainDrawEnergyPerNonAttackAction(int EnergyDrawAmount) {
        this.EnergyDrawAmount = 0;

        this.EnergyDrawAmount = EnergyDrawAmount;
        setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = AbstractGameAction.ActionType.BLOCK;
    }

    private int EnergyDrawAmount;

    /*    */
    /*    */
    public void update() {
        if (!this.isDone) {
            /* 21 */
            this.isDone = true;
            int total = 0;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }


                total += this.EnergyDrawAmount;
            }
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, EnergyDrawAmount, false));
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction((EnergyDrawAmount)));
        }
    }
}