package theHeart.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class Betablocker extends AbstractDynamicCard {

        public static final String ID = DefaultMod.makeID(Betablocker.class.getSimpleName());
        public static final String IMG = makeCardPath("Skill.png");

        // /TEXT DECLARATION/

        // STAT DECLARATION

        private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
        private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
        private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
        public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

        private static final int COST = 1;


        private static final int DAMAGE = 10;
        private static final int UPGRADE_PLUS_DAMAGE = 3;
        private static final int UPGRADE_PLUS_MAGICNUMBER = 1;
        private static final int MAGICNUMBER = 1;

        // /STAT DECLARATION/


        public Betablocker () {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseDamage = DAMAGE;
            baseMagicNumber = MAGICNUMBER;
            this.exhaust= true;
        }

        // Actions the card should do.
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p,p,new ArtifactPower(p,baseMagicNumber)));

        }
        public AbstractDynamicCard makeCopy() { return new Betablocker(); }


        // Upgraded stats.
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
                upgradeDamage(UPGRADE_PLUS_DAMAGE);
                initializeDescription();
            }
        }
}
