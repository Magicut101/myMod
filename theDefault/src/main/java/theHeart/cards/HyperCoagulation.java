package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theHeart.DefaultMod;
import theHeart.characters.TheDefault;



import static theHeart.DefaultMod.makeCardPath;

public class HyperCoagulation extends AbstractDynamicCard {

    /*
     * ITS SPIRE TIME
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(HyperCoagulation.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    public static final String IMG = makeCardPath("Skill.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADE_PLUS_COST = 1;
    private static final int MAGIC_NUMBER = 3;


    // /STAT DECLARATION/
//I need to add a dynamic description box so that people can know how high the count is
    public HyperCoagulation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = damage;
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
    }

    // Actions the card should do.
    public void applyPowers() {
        /* 58 */

        /* 59 */
        super.applyPowers();

        int count = 0;

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.STATUS) {
                count++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.type == AbstractCard.CardType.STATUS) {
                count++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == AbstractCard.CardType.STATUS) {
                count++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.type == AbstractCard.CardType.STATUS) {
                count++;
            }
       }

this.baseDamage = count * magicNumber;
this.baseBlock = count * magicNumber;

}
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,block));
AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage)));

    }

    public AbstractDynamicCard makeCopy() {
        return new HyperCoagulation();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBaseCost(UPGRADE_PLUS_COST);
            this.initializeDescription();
        }
    }
}

