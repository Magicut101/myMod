package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import theHeart.powers.EndlessHatredPower;
import theHeart.powers.PacemakerPower;

import static theHeart.DefaultMod.makeCardPath;

public class EndlessHatred extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(EndlessHatred.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 2;
    private static final int MAGIC_NUMBER = 4;

    // /STAT DECLARATION/


    public  EndlessHatred() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGIC_NUMBER;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p, new EndlessHatredPower(p,p, MAGIC_NUMBER)));


    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}
