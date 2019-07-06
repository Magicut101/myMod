package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class Constrict extends AbstractDynamicCard {
        public static final String ID = DefaultMod.makeID(Constrict.class.getSimpleName());
        public static final String IMG = makeCardPath("Skill.png");

        // /TEXT DECLARATION/

        // STAT DECLARATION

        private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
        private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
        private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
        public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

        private static final int COST = 3;
        private static final int UPGRADE_COST = 1;

        private static final int UPGRADE_PLUS_MAGICNUMBER = 5 ;
        private static final int MAGICNUMBER = 10;

        // /STAT DECLARATION/


        public Constrict() {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

            baseMagicNumber = MAGICNUMBER;
this.exhaust = true;
        }

        // Actions the card should do.
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m,p,new ConstrictedPower(m,p, baseMagicNumber)));

        }
        public AbstractDynamicCard makeCopy() { return new Constrict(); }


        // Upgraded stats.
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
                upgradeBaseCost(UPGRADE_COST);
                initializeDescription();
            }

        }
        }