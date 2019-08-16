package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.FlyingKnee;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class Tachycardia extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Tachycardia.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 1;

    private static final int MAGIC_NUMBER = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DAMAGE = 3;


    // /STAT DECLARATION/


    public Tachycardia() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        baseDamage = damage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDiscardAction(new VoidCard(), 1));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergizedPower(p, magicNumber), magicNumber));

    }
    public AbstractDynamicCard makeCopy() { return new Tachycardia(); }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            upgradeDamage(UPGRADE_PLUS_DAMAGE);

            initializeDescription();
        }

    }
}