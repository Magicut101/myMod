package theHeart.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
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

public class HighDecibelBeat extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(HighDecibelBeat.class.getSimpleName());
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
    private static final int UPGRADE_PLUS_DAMAGE = 4;
    private static final int DAMAGE = 6;
    private static final int MAGIC_NUMBER = 4;

// /STAT DECLARATION/


    public HighDecibelBeat() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        baseMagicNumber =magicNumber= MAGIC_NUMBER;


    }

    public int countCards() {

    int count = 0;

        for(
    AbstractCard c :AbstractDungeon.player.hand.group)

    {
        if (c.type == AbstractCard.CardType.STATUS) {
            count++;
        }
    }
        return count;
}

    public void use (AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < countCards(); i++) {
            AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this.uuid, this.magicNumber));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p,damage,damageTypeForTurn)));
    }

    public AbstractDynamicCard makeCopy () {
        return new HighDecibelBeat();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}