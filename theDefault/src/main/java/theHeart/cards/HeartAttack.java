package theHeart.cards;

        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.animations.VFXAction;
        import com.megacrit.cardcrawl.actions.common.DrawCardAction;
        import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
        import com.megacrit.cardcrawl.actions.common.LoseHPAction;
        import com.megacrit.cardcrawl.actions.utility.SFXAction;
        import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;
        import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.characters.AbstractPlayer;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import theHeart.DefaultMod;
        import theHeart.characters.TheDefault;
        import static theHeart.DefaultMod.makeCardPath;


public class HeartAttack extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(HeartAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("HeartAttack.png");

    public static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 60;
    private static final int HP_LOSS = 20;
    private static final int UPGRADE_PLUS_MAGICNUMBER = 1;

    public HeartAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }
@Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new ImpactSparkEffect(m.hb.cX, m.hb.cY)));
    }

        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }



    public AbstractDynamicCard makeCopy() { return new HeartAttack(); }

@Override
        public void upgrade(){
            if (!upgraded) {
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
                initializeDescription();
            }

        }
    }
