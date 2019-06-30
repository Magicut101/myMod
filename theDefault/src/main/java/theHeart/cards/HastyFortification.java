package theHeart.cards;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import static theHeart.DefaultMod.makeCardPath;

public class HastyFortification extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(HastyFortification.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 22;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    public HastyFortification() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = -4;
        baseMagicNumber = magicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom
                (new ApplyPowerAction(p, p, new DexterityPower
                        (p, this.magicNumber), this.magicNumber));

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction (p,p,block));
                }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}