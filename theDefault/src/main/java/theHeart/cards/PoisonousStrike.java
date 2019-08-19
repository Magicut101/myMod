package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class PoisonousStrike extends AbstractDynamicCard {

/*
 * ITS SPIRE TIME
 */

// TEXT DECLARATION

public static final String ID = DefaultMod.makeID(PoisonousStrike.class.getSimpleName());
private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

public static final String IMG = makeCardPath("Skill.png");

public static final String NAME = cardStrings.NAME;
public static final String DESCRIPTION = cardStrings.DESCRIPTION;

// /TEXT DECLARATION/


// STAT DECLARATION

private static final CardRarity RARITY = CardRarity.UNCOMMON;
private static final CardTarget TARGET = CardTarget.ENEMY;
private static final CardType TYPE = CardType.ATTACK;
public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

private static final int COST = 1;
private static final int DAMAGE = 4;
private static final int UPGRADE_PLUS_DAMAGE = 1;
private static final int MAGIC_NUMBER = 4;
private static final int UPGRADED_PLUS_MAGIC_NUMBER = 1;

// /STAT DECLARATION/

public PoisonousStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
baseMagicNumber = magicNumber = MAGIC_NUMBER;
        }

// Actions the card should do.
@Override
public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p,damage,damageTypeForTurn)));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(p,p,magicNumber)));

        }

public AbstractDynamicCard makeCopy() {
        return new HeartBurn();
        }

// Upgraded stats.
@Override
public void upgrade() {
        if (!this.upgraded) {
        this.upgradeName();
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
        upgradeMagicNumber(UPGRADED_PLUS_MAGIC_NUMBER);
        rawDescription = UPGRADE_DESCRIPTION;
        this.initializeDescription();
        }
        }
        }

