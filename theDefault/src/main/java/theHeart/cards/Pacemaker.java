package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

import theHeart.powers.PacemakerPower;

import static theHeart.DefaultMod.makeCardPath;

public class Pacemaker extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(AncientEvil.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int UPGRADE_PLUS_MAGICNUMBER = 1;
    private static final int MAGICNUMBER = 1;

    // /STAT DECLARATION/


    public Pacemaker() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGICNUMBER;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p, new PacemakerPower(p, p, magicNumber), magicNumber));


    }
    public AbstractDynamicCard makeCopy() { return new Pacemaker(); }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
            initializeDescription();
        }
    }
}

