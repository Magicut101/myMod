package theHeart.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import theHeart.powers.AdaptiveCellsPower;
import theHeart.powers.GrayPlateletsPower;

import static theHeart.DefaultMod.makeCardPath;

public class AdaptiveCells extends AbstractDynamicCard{

    public static final String ID = DefaultMod.makeID(AdaptiveCells.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 2;


    public AdaptiveCells() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseMagicNumber = magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use (AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom
                (new ApplyPowerAction(p, p, new AdaptiveCellsPower(p,p, magicNumber, 1, 5)));

    }
    public AbstractDynamicCard makeCopy() {
        return new AdaptiveCells();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(magicNumber);

            initializeDescription();
        }
    }
}
