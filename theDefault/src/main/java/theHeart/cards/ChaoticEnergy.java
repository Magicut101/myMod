package theHeart.cards;


 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import theHeart.DefaultMod;
 import theHeart.characters.TheDefault;

 import static theHeart.DefaultMod.makeCardPath;



public class ChaoticEnergy extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(ChaoticEnergy.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = -2;
    public static final int BLOCK = 10;
    public static final int UPGRADED_PLUS_BLOCK = 5;

    public ChaoticEnergy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }
//This card crashes the game when played

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
        
        @Override
    public void triggerWhenDrawn() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(mo, AbstractDungeon.player, baseBlock));

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, baseBlock));
    }


    public AbstractDynamicCard makeCopy() {
        return new ChaoticEnergy();
    }
    

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADED_PLUS_BLOCK);
            initializeDescription();
        }
    }
}




