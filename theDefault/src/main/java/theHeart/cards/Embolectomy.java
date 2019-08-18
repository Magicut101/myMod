package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.actions.ExhaustDrawPileWounds;
import theHeart.actions.ExhaustingReprogramAction;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;


public class Embolectomy  extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Big Slap Deal 10(15)) damage.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Embolectomy.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // /TEXT DECLARATION/

//This card is not added in yet and it does not work, it jsut freezes whenever it is used.
    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_PLUS_COST = 0;



    // /STAT DECLARATION/


    public  Embolectomy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

       exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom( new ExhaustDrawPileWounds());
    }
    public AbstractDynamicCard makeCopy() {
        return new Embolectomy();
    }
    //Upgraded stats.
    @Override
    public void upgrade() {
            if (!this.upgraded) {
                this.upgradeName();
                upgradeBaseCost(UPGRADED_PLUS_COST);
                rawDescription = UPGRADE_DESCRIPTION;
                this.initializeDescription();
            }
    }
}