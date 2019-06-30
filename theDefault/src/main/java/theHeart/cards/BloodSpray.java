package theHeart.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import static theHeart.DefaultMod.makeCardPath;



public class BloodSpray extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(BloodSpray.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DAMAGE = 5;
    private static final int HP_LOSS = 4;


    public BloodSpray() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.isMultiDamage = true;
    }
@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    if (m != null) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new DieDieDieEffect()));
    }   AbstractDungeon.actionManager.addToBottom(new SFXAction("SLS_SFX_HeartBeat_Simple_v1.ogg"));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractDynamicCard makeCopy () {
        return new BloodSpray();
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}
