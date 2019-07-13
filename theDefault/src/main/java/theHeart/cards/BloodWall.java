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
        private static final int BLOCK = 6;
        private static final int UPGRADE_PLUS_BLOCK = 3;
//Okay, so local variable B, takes last damage taken as the block value.

        public BloodWall() {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseBlock = BLOCK;
        }
        @Override

        public void use(AbstractPlayer p, AbstractMonster m) {
final int e = Math.abs( AbstractDungeon.player.damagedThisCombat);

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,baseBlock));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, e));

            }

        public AbstractDynamicCard makeCopy () {
            return new BloodWall();
        }


        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeBlock(UPGRADE_PLUS_BLOCK);
                initializeDescription();
            }
        }
    }


