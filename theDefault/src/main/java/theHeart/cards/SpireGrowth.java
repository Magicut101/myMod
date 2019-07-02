package theHeart.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import static theHeart.DefaultMod.makeCardPath;

public class SpireGrowth extends AbstractDynamicCard{

public static final String ID = DefaultMod.makeID(SpireGrowth.class.getSimpleName());
public static final String IMG = makeCardPath("Attack.png");

private static final CardRarity RARITY = CardRarity.COMMON;
private static final CardTarget TARGET = CardTarget.SELF;
private static final CardType TYPE = CardType.SKILL;
public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

private static final int COST = 1;
private static final int BLOCK = 5;
private static final int UPGRADED_PLUS_BLOCK = 3;



public SpireGrowth () {
    super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
 baseBlock = BLOCK;
magicNumber = 1;
baseMagicNumber = magicNumber;
}

@Override
public void use (AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
    AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p, p, BLOCK));

}
    public AbstractDynamicCard makeCopy() {
        return new SpireGrowth();
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