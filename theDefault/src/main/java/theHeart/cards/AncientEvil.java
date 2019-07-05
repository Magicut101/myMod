package theHeart.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class AncientEvil extends AbstractDynamicCard {
        public static final String ID = DefaultMod.makeID(AncientEvil.class.getSimpleName());
        public static final String IMG = makeCardPath("Power.png");
        // /TEXT DECLARATION/

        // STAT DECLARATION

        private static final CardRarity RARITY = CardRarity.UNCOMMON;
        private static final CardTarget TARGET = CardTarget.SELF;
        private static final CardType TYPE = CardType.POWER;
                public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

                private static final int COST = 1;

                private static final int UPGRADE_PLUS_MAGICNUMBER = 1;
                private static final int MAGICNUMBER = 2;

                // /STAT DECLARATION/


                public AncientEvil() {
                    super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
                    baseMagicNumber = MAGICNUMBER;

                }

                // Actions the card should do.
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p,p, new ArtifactPower(p,magicNumber), magicNumber));


        }
        public AbstractDynamicCard makeCopy() { return new AncientEvil(); }


        // Upgraded stats.
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
                initializeDescription();
            }
        }
}

