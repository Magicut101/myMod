package theHeart.actions;


        /*    */
        /*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
        /*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
        /*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
        /*    */ import com.megacrit.cardcrawl.core.Settings;
        /*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        /*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
        /*    */ import com.megacrit.cardcrawl.relics.BirdFacedUrn;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
        /*    */ import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

//Borrowed Thorton's Fortune X-Cost card Action.
        public class ElementalChargeAction
       extends AbstractGameAction {
    private int[] multiDamage;
    /*    */   private boolean freeToPlayOnce;
    /*    */   private boolean upgraded;
    /*    */   private AbstractPlayer p;
    /*    */   private AbstractMonster m;
    /*    */   private int energyOnUse;
    private int amount;

    /*    */
    public ElementalChargeAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType, int amount, boolean freeToPlayOnce, int energyOnUse) {
        /* 21 */
        this.p = p;
        /* 22 */
        this.m = m;
        /* 23 */
        this.freeToPlayOnce = freeToPlayOnce;
        this.damageType = damageType;
        this.upgraded = upgraded;
        /* 25 */
        this.duration = Settings.ACTION_DUR_XFAST;
        /* 26 */
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        /* 27 */
        this.energyOnUse = energyOnUse;
        this.multiDamage = multiDamage;
        this.amount = amount;
        /*    */
    }

    /*    */
    /*    */
    public void update() {
        /* 31 */
        int effect = EnergyPanel.totalCount;
        /* 32 */
        if (this.energyOnUse != -1) {
            /* 33 */
            effect = this.energyOnUse;
            /*    */
        }
        /*    */
        /* 36 */
        if (this.p.hasRelic("Chemical X")) {
            /* 37 */
            effect += 2;
            /* 38 */
            this.p.getRelic("Chemical X").flash();
            /*    */
        }
        /*    */
        /* 41 */
        if (effect > 0) {
            /* 51 */
            for (int i = 0; i < effect; i++) {
                /* 52 */
                if (i == 0) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new WhirlwindEffect(), 0.0F));
                }
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this.p, new CleaveEffect(), 0.0F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.p, multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, true));
                    AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, amount, true, false, false));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn()));
                }

                if (!this.freeToPlayOnce) {
                    this.p.energy.use(EnergyPanel.totalCount);
                }
                isDone = true;
            }

        }
    }

