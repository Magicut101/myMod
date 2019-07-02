package theHeart.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;
import theHeart.DefaultMod;
import theHeart.actions.QuickTranfusionAction;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;

public class QuickTransfusion extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(QuickTransfusion.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 4 ;
    private static int magicNumber = 3;

    public QuickTransfusion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

      magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

AbstractDungeon.actionManager.addToBottom(new QuickTranfusionAction(m, new DamageInfo(p , damage, damageTypeForTurn), this.magicNumber));

        }

    public AbstractDynamicCard makeCopy() { return new QuickTransfusion(); }

    @Override
    public void upgrade(){
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }

    }
}
