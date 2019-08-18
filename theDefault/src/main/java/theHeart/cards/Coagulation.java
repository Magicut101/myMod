package theHeart.cards;


import basemod.abstracts.CustomCard;
  import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
    import com.megacrit.cardcrawl.cards.AbstractCard;
     import com.megacrit.cardcrawl.characters.AbstractPlayer;
   import com.megacrit.cardcrawl.core.CardCrawlGame;
   import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
   import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;
import static theHeart.DefaultMod.makeCardPath;

        public class Coagulation extends CustomCard {

            public static final String ID = DefaultMod.makeID(Coagulation.class.getSimpleName());
            private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
            public static final String IMG = makeCardPath( "Skill.png" );
            public static final String NAME = cardStrings.NAME;
            public static final String DESCRIPTION = cardStrings.DESCRIPTION;


            private static final CardRarity RARITY = CardRarity.UNCOMMON;
            private static final CardTarget TARGET = CardTarget.NONE;
            private static final CardType TYPE = CardType.SKILL;
            public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

            private static final int COST = 1;
            private static final int UPGRADED_COST = 0;

           public Coagulation() {
               super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
this.retain = true;
           }


       public void use(AbstractPlayer p, AbstractMonster m) { AbstractDungeon.actionManager.addToBottom(new DrawPileToHandAction(100, CardType.STATUS)); }



    /* 41 */   public AbstractCard makeCopy() { return new Coagulation(); }
    /*    */
    /*    */
    /*    */
    /*    */   public void upgrade() {
        /* 46 */     if (!this.upgraded) {
            /* 47 */       upgradeName();
            /* 48 */       upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
            /*    */     }
        /*    */   }
    /*    */ }
