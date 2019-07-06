package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Headbutt;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static theHeart.DefaultMod.makeCardPath;


public class MindScape extends AbstractDynamicCard {


public static final String ID = DefaultMod.makeID(MindScape.class.getSimpleName());
public static final String IMG = makeCardPath("Attack.png");


// /TEXT DECLARATION/
private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// STAT DECLARATION

private static final CardRarity RARITY = CardRarity.UNCOMMON;
private static final CardTarget TARGET = CardTarget.ENEMY;
private static final CardType TYPE = CardType.ATTACK;
public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

private static final int COST = 2;

private static final int DAMAGE = 14;
private static final int UPGRADE_PLUS_DMG = 3 ;

// /STAT DECLARATION/


public MindScape() {
    super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    baseDamage = DAMAGE;


}
@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /* 39 */
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        /*    */
        /*    */
        /*    */
        /*    */
        /* 44 */
        AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
            /*    */
        }
        /*    */
        /*    */}
        /*    */
        /* 49 */
        public AbstractDynamicCard makeCopy () {
            return new MindScape();
        }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}