package theHeart.cards;

import basemod.interfaces.OnPlayerDamagedSubscriber;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class BloodWall extends AbstractDynamicCard {

        public static final String ID = DefaultMod.makeID(theHeart.cards.BloodWall.class.getSimpleName());
        public static final String IMG = makeCardPath("Attack.png");

        private static final CardRarity RARITY = CardRarity.UNCOMMON;
        private static final CardTarget TARGET = CardTarget.SELF;
        private static final CardType TYPE = CardType.SKILL;
        public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

        private static final int COST = 1;
        private static final int MAGIC_NUMBER = 6;
        private static final int UPGRADE_PLUS_MAGIC_NUMBER = 3;
//Okay, so local variable B, takes last damage taken as the block value.

        public BloodWall() {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseBlock = block;
            baseMagicNumber = magicNumber = MAGIC_NUMBER;
        }
        @Override

        public void use(AbstractPlayer p, AbstractMonster m) {
baseBlock = Math.abs( AbstractDungeon.player.lastDamageTaken);

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p, MAGIC_NUMBER));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));

            }

        public AbstractDynamicCard makeCopy () {
            return new BloodWall();
        }


        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
                initializeDescription();
            }
        }
    }


