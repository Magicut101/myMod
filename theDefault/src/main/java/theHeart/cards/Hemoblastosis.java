package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.TheBombPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class Hemoblastosis extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Hemoblastosis.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    private static final int MAGIC_NUMBER = 5;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = -1;
    private static final int DAMAGE = 25;
    private static final int UPGRADE_PLUS_DAMAGE = 5;
    private static final int DEFAULT_SECOND_MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_DEFAULT_SECOND_MAGIC_NUMBER = 1;

    // /STAT DECLARATION/


    public  Hemoblastosis() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        baseDamage = damage = DAMAGE;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = DEFAULT_SECOND_MAGIC_NUMBER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
AbstractDungeon.actionManager.addToBottom
        (new LoseHPAction(p,p,magicNumber));

AbstractDungeon.actionManager.addToBottom
        (new ApplyPowerAction(p, p, new WeakPower
                (m, this.defaultSecondMagicNumber, false)
                        , this.defaultSecondMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new TheBombPower(p, 2, damage), defaultSecondMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }
    public AbstractDynamicCard makeCopy() { return new Hemoblastosis(); }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_DEFAULT_SECOND_MAGIC_NUMBER);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            initializeDescription();
        }

    }
}
