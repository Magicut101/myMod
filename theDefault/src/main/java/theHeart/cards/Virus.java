package theHeart.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField.autoplay;
import static theHeart.DefaultMod.makeCardPath;

public class Virus  extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Virus .class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");
    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;


    // /STAT DECLARATION/


    public Virus () {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber;
exhaust = true;
        AutoplayField.autoplay.set(this, Boolean.valueOf(true));
    }// Actions the card should do.
    @Override

        public void use(AbstractPlayer p, AbstractMonster m){
            /* 34 */
            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(
                    /*    */
                    /* 36 */           (AbstractDungeon.getCurrRoom()).monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), true));

        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Virus(), 1, true, true));
        }
        else AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Virus(), 1));


    }


    public AbstractDynamicCard makeCopy() { return new Virus (); }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

