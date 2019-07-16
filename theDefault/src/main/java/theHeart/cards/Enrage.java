package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;


import static theHeart.DefaultMod.makeCardPath;

public class Enrage extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Enrage.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int MAGIC_NUMBER = 5;
    private static final int UPGRADED_PLUS_MAGIC_NUMBER = 1;

    private static final int DAMAGE = 23;
    private static final int UPGRADED_PLUS_DAMAGE = 5;


    // /STAT DECLARATION/


    public Enrage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC_NUMBER;
        baseDamage = damage = DAMAGE;
        this.isMultiDamage = true;

    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
                    }






    public AbstractDynamicCard makeCopy() {
        return new Enrage();
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADED_PLUS_DAMAGE);
            upgradeMagicNumber(UPGRADED_PLUS_MAGIC_NUMBER);
            initializeDescription();
        }

    }
}
