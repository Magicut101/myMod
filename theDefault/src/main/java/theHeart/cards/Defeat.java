package theHeart.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static theHeart.DefaultMod.makeCardPath;

public class Defeat extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(Defeat.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int DAMAGE = 20;
    private static final int UPGRADE_PLUS_DAMAGE = 10;
    private static final int MAGIC_NUMBER = 99;

//Okay card idea is this, an evolving replacing card that tells the story of the exile of Neow.
    //Exile -> Rebellion -> Subjugation -> Defeat -> Exile

// /STAT DECLARATION/


    public Defeat() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        baseDamage = damage = DAMAGE;
        purgeOnUse = true;
        FleetingField.fleeting.set(this, true);

    }



    public void use (AbstractPlayer p, AbstractMonster m){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new VulnerablePower(p, magicNumber, false)));
                  AbstractDungeon.actionManager.addToBottom(new AddCardToDeckAction(new Exile()));
        AbstractDungeon.player.masterDeck.removeCard(new Defeat());
        actionManager.addToBottom(new DamageAction(m, new DamageInfo(m,damage, damageTypeForTurn)));
    }
    public AbstractDynamicCard makeCopy () {
        return new Defeat();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgrade();
            initializeDescription();
        }
    }
}