package theHeart.cards;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import theHeart.powers.NoxiousBloodPower;

import static theHeart.DefaultMod.makeCardPath;

public class NoxiousBlood extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(NoxiousBlood.class.getSimpleName());
        public static final String IMG = makeCardPath("Power.png");

        private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

        // /TEXT DECLARATION/

        // STAT DECLARATION

        private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
        private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
        private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
        public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

        private static final int COST = 1;
        private static final int MAGIC_NUMBER = 3;
        private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;


        // /STAT DECLARATION/

        public NoxiousBlood() {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            magicNumber = baseMagicNumber = MAGIC_NUMBER;

        }

        // Actions the card should do.
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {

                AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(p, p, new NoxiousBloodPower(p ,p, magicNumber), magicNumber));
            }

        //Upgraded stats.
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
                initializeDescription();
            }
        }
}

