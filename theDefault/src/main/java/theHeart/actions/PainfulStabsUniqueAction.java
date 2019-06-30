package theHeart.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;


public class PainfulStabsUniqueAction
        extends AbstractGameAction {
    private DamageInfo info;

    public PainfulStabsUniqueAction(AbstractCreature target, DamageInfo info) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.info = info;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
        this.target = target;
    }

    public void update() {
        /* 26 */
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            /* 27 */
            if (c.type == AbstractCard.CardType.STATUS)
                /* 28 */ AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, this.info, true));
            /* 29 */
            if (this.target != null && this.target.hb != null) {
                /* 30 */
                AbstractDungeon.actionManager.addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
                /*    */
            }
            this.isDone =true; /*    */
        }
        /*    */
    }

    /* 35 */
}    /*    */
    /*    */