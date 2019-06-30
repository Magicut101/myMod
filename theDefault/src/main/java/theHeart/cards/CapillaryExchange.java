
package theHeart.cards;

        import com.megacrit.cardcrawl.actions.utility.ConditionalDrawAction;
        import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.actions.common.DrawCardAction;
        import com.megacrit.cardcrawl.characters.AbstractPlayer;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import theHeart.DefaultMod;
        import theHeart.characters.TheDefault;
        import static theHeart.DefaultMod.makeCardPath;

public class CapillaryExchange extends AbstractDynamicCard{

    public static final String ID = DefaultMod.makeID(SpireGrowth.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DRAW = 2;

    public CapillaryExchange () {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = 1;
        baseMagicNumber = magicNumber;
    }

    @Override
    public void use (AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));



    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}