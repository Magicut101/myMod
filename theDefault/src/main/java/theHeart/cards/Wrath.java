package theHeart.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;
import static theHeart.DefaultMod.makeCardPath;

public class Wrath extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(Wrath.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_PLUS_COST = 1;

    private static final int DAMAGE = 0;
    private static final int UPGRADE_PLUS_DMG = 0;
    private static float WrathDamage;


// /STAT DECLARATION/


    public Wrath() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;



    }


    @Override
    public void tookDamage() {
        WrathDamage = 0;
        
    }

    //Get current hp at the start of combat, then make it compare that value to damage taken while in combat, by looking at Current hp - Hp in Room.




    public void use (AbstractPlayer p, AbstractMonster m){

        int IntWrathDamage = Math.round(WrathDamage);
    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p,IntWrathDamage )));
    }
    public AbstractDynamicCard makeCopy () {
        return new Wrath();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_PLUS_COST);
            initializeDescription();
        }
    }
}