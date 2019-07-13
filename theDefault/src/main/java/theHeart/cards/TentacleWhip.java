package theHeart.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import static theHeart.DefaultMod.makeCardPath;


 public class TentacleWhip extends AbstractDynamicCard {

     public static final String ID = DefaultMod.makeID(TentacleWhip.class.getSimpleName());
     public static final String IMG = makeCardPath("Attack.png");

     private static final CardRarity RARITY = CardRarity.COMMON;
     private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
     private static final CardType TYPE = CardType.ATTACK;
     public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

     private static final int COST = 1;
     private static final int DAMAGE = 4;
     private static final int UPGRADE_PLUS_DAMAGE = 2;
     private static final int STRENGTH_LOSS = 1;
     private static final int UPG_STRENGTH_AMT = 1;
//I added code from piercing wail so this now properly reduces strength to all enemies but now it does an extra attack for every enemy?
     // I moved some code up but that properly didn't fix the issue.

     public TentacleWhip() {
         super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
         baseDamage = DAMAGE;
         isMultiDamage = true;
         baseMagicNumber = STRENGTH_LOSS;
         magicNumber = this.baseMagicNumber;
     }
@Override
     public void use (AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));

    AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));

    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber),
                -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
        if (!mo.hasPower("Artifact")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                    (mo, p, new GainStrengthPower(mo, this.magicNumber), this.magicNumber
                            , true, AbstractGameAction.AttackEffect.NONE));
        }


    }
}
     public AbstractDynamicCard makeCopy () {
         return new TentacleWhip();
     }


     @Override
     public void upgrade() {
         if (!upgraded) {
             upgradeName();
             upgradeDamage(UPGRADE_PLUS_DAMAGE);
             upgradeMagicNumber(UPG_STRENGTH_AMT);
             initializeDescription();
         }
     }
 }
