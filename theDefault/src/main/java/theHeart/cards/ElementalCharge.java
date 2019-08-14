package theHeart.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theHeart.DefaultMod;
import theHeart.actions.ElementalChargeAction;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class ElementalCharge extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID( ElementalCharge.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = -2;

    private static final int MAGIC_NUMBER = 1;
    private static final int DAMAGE = 8;

    // /STAT DECLARATION/


    public  ElementalCharge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        baseDamage = damage = DAMAGE;
        this.isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /* 39 */     if (this.energyOnUse < EnergyPanel.totalCount) {
            /* 40 */       this.energyOnUse = EnergyPanel.totalCount;
            /*    */     }
        /*    */
        /* 43 */     AbstractDungeon.actionManager.addToBottom(new ElementalChargeAction(p, this.multiDamage, this.damageTypeForTurn, 1, this.freeToPlayOnce, this.energyOnUse));
        /*    */   }
    /*    */



    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}