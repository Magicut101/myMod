package theHeart.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.actions.SunderForStrengthAction;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;
//Mostly broken, I want it to give the player 3 strength when it kills an enemy.
public class Devour extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Devour.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 4 ;
    private static int magicNumber = 3;

    public Devour() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

      magicNumber = this.baseMagicNumber;

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

AbstractDungeon.actionManager.addToBottom(new SunderForStrengthAction(m, new DamageInfo(p , damage, damageTypeForTurn), this.magicNumber));

        }

    public AbstractDynamicCard makeCopy() { return new Devour(); }

    @Override
    public void upgrade(){
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }

    }
}
