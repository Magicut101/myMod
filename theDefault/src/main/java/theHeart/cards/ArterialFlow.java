package theHeart.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

    public class ArterialFlow  extends AbstractDynamicCard{

        public static final String ID = DefaultMod.makeID(theHeart.cards.ArterialFlow.class.getSimpleName());
        public static final String IMG = makeCardPath("Attack.png");

        private static final CardRarity RARITY = CardRarity.COMMON;
        private static final CardTarget TARGET = CardTarget.SELF;
        private static final CardType TYPE = CardType.ATTACK;
        public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

        private static final int COST = 1;
        private static final int DAMAGE = 6;
        private static final int UPGRADED_PLUS_DAMAGE = 4;
        private static final int MAGIC_NUMBER = 2;
        private static final int UPGRADED_PLUS_MAGICNUMBER = 1;



        public ArterialFlow () {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseDamage = DAMAGE;
            magicNumber = MAGIC_NUMBER;
            baseMagicNumber = magicNumber;
        }

        @Override
        public void use (AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
           AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
           AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(
                   new Wound(),1, true,true,false));
        }
        public AbstractDynamicCard makeCopy() {
            return new theHeart.cards.ArterialFlow();
        }

        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADED_PLUS_MAGICNUMBER);
                upgradeDamage(UPGRADED_PLUS_DAMAGE);
                initializeDescription();
            }
        }
    }