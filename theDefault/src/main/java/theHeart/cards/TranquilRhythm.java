
    package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

import static theHeart.DefaultMod.makeCardPath;

    // public class ${NAME} extends AbstractDynamicCard
    public class TranquilRhythm extends AbstractDynamicCard {



        // TEXT DECLARATION

        // public static final String ID = DefaultMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
        public static final String ID = DefaultMod.makeID("TranquilRhythm"); // DELETE THIS ONE.
        public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");
        // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


        // /TEXT DECLARATION/


        // STAT DECLARATION

        private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
        private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
        private static final CardType TYPE = CardType.SKILL;       //
        public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

        private static final int COST = 0;

        private static final int MAGIC_NUMBER = 4;
        private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;
        private static final int BLOCK = 6;
        private static final int UPGRADED_PLUS_BLOCK = 2;
        private static final int DEFAULT_SECOND_MAGIC_NUMBER = 1;
        private static final int UPGRADE_PLUS_DEFAULT_SECOND_MAGIC_NUMBER = 1;

        // /STAT DECLARATION/


        public TranquilRhythm() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseMagicNumber= magicNumber = MAGIC_NUMBER;
            baseBlock = block = BLOCK;
            defaultBaseSecondMagicNumber = defaultSecondMagicNumber = DEFAULT_SECOND_MAGIC_NUMBER;
        }


        // Actions the card should do.
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(p, p, block));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
                    p, new RegenPower(m, magicNumber)
                    , magicNumber, true, AbstractGameAction.AttackEffect.NONE));

            AbstractDungeon.actionManager.addToBottom
                    (new ApplyPowerAction(m, p, new WeakPower
                                    (m, this.defaultSecondMagicNumber, false)
                            , this.defaultSecondMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }

        // Upgraded stats.
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
                upgradeBlock(UPGRADED_PLUS_BLOCK);
                upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_DEFAULT_SECOND_MAGIC_NUMBER);
                initializeDescription();
            }
        }
    }


