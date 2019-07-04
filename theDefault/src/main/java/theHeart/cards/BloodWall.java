package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import jdk.nashorn.internal.ir.Block;
import theHeart.actions.BloodWallAction;
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
//Completely broken eventually I will fix this.
        public int onAttacked;

        public BloodWall() {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseBlock = BLOCK;
        }
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            if (m != null) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, onAttacked));

            }
        }
        public AbstractDynamicCard makeCopy () {
            return new theHeart.cards.BloodWall();
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


