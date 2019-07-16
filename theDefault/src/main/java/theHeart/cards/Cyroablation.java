package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import javax.smartcardio.Card;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.*;
import static theHeart.DefaultMod.makeCardPath;

public class Cyroablation extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Cyroablation.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = SKILL;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int BLOCK = 12;
    private static final int UPGRADED_PLUS_BLOCK = 4;

    // /STAT DECLARATION/


    public Cyroablation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseBlock = block = BLOCK;
    }

    // Actions the card should do.
@Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, false, false));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));

        }

public AbstractDynamicCard makeCopy() { return new Cyroablation (); }



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADED_PLUS_BLOCK);
            initializeDescription();
        }

    }
}


