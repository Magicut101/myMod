package theHeart.cards;


import com.megacrit.cardcrawl.actions.animations.VFXAction;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import static theHeart.DefaultMod.makeCardPath;

public class Hemochromatosis extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID( Hemochromatosis .class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    // /STAT DECLARATION/

    public  Hemochromatosis() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        AbstractCard f = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        AbstractCard d = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        AbstractCard g = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        AbstractCard b = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();

        c.setCostForTurn(0);
        f.setCostForTurn(0);
        d.setCostForTurn(0);
        g.setCostForTurn(0);
        b.setCostForTurn(0);


        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c ,true ));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(f ,true ));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(d ,true ));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(g ,true ));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(b ,true ));

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new HeartMegaDebuffEffect()));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1,true, true, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Dazed(), 1,true, true, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), 1,true, true, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Wound(), 1,true, true, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Burn(), 1,true, true, false));

            }


    @Override
    public AbstractDynamicCard makeCopy() {
        return new  Hemochromatosis ();

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(
                    UPGRADED_COST
            );
            initializeDescription();
        }
    }
}