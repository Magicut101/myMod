package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theHeart.DefaultMod;
import theHeart.actions.CorruptAction;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class Corrupt extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(HastyFortification.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public Corrupt() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = -4;
        baseMagicNumber = magicNumber;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new CorruptAction(m,p));
    }
    @Override
    public AbstractDynamicCard makeCopy() {
        return new Corrupt();
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
    }