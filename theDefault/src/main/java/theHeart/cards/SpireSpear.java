package theHeart.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

//Main idea for this card is that it costs 5 energy, and costs less for every X number of status cards in combat.
//Perhaps it should similarly scale as with other cards to deal damage based on number of status cards or have a flat effect for more versatility.
public class SpireSpear extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(SpireSpear.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 4;
    private static final int DAMAGE = 9;
    private static final int UPGRADED_PLUS_DAMAGE = 2;
//I added code from piercing wail so this now properly reduces strength to all enemies but now it does an extra attack for every enemy?
    // I moved some code up but that properly didn't fix the issue.

    public SpireSpear() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber;
defaultBaseSecondMagicNumber = defaultSecondMagicNumber;
        }




    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.NONE));


    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard card) {

        int statuscards = 4;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.STATUS) {
                --statuscards;
            }
        }

        setCostForTurn(statuscards);
    }

    @Override
    public void triggerWhenDrawn() {

        int statuscards = 4;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.STATUS) {
                --statuscards;
            }
        }

        setCostForTurn(statuscards);
    }
    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();


        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new SpireSpear(), 1));
    }




    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADED_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}

