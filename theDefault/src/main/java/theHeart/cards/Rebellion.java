package theHeart.cards;

import basemod.devcommands.relic.RelicAdd;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.NeowsLament;
import com.megacrit.cardcrawl.relics.RunicCube;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import java.util.AbstractMap;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.floorNum;
import static theHeart.DefaultMod.makeCardPath;

public class Rebellion extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(Rebellion.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADE_PLUS_COST = -1;

//Okay card idea is this, an evolving replacing card that tells the story of the exile of Neow.
    //Exile -> Rebellion -> Subjugation -> Defeat -> Exile

// /STAT DECLARATION/


    public Rebellion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = floorNum;
        purgeOnUse = true;
        FleetingField.fleeting.set(this, true);
if(upgraded) {
damage =+ 10;
}
}









    public void use (AbstractPlayer p, AbstractMonster m){
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage)));

        AbstractDungeon.player.masterDeck.removeCard(new Rebellion());
        AbstractDungeon.actionManager.addToBottom(new AddCardToDeckAction(new Subjugation()));
    }
    public AbstractDynamicCard makeCopy () {
        return new Rebellion();
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
