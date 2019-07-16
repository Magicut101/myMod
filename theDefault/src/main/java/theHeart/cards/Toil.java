package theHeart.cards;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import theHeart.actions.ExhaustingReprogramAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

import theHeart.powers.LoseEnergyNextTurn;
import theHeart.powers.PacemakerPower;


public class Toil extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Toil.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;

    private static final int UPGRADE_PLUS_ENERGY = 2;
    private static final int ENERGY = 2;


    // /STAT DECLARATION/


    public Toil() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = ENERGY;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p, new LoseEnergyNextTurn(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new GainEnergyAction(magicNumber));



    }
    public AbstractDynamicCard makeCopy() { return new Toil(); }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ENERGY);
            initializeDescription();
        }
    }
}
