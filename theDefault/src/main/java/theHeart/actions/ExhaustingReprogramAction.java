 package theHeart.actions;
        /*    */
        /*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
        /*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
        /*    */ import com.megacrit.cardcrawl.cards.CardGroup;
        /*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
        /*    */ import com.megacrit.cardcrawl.core.Settings;
        /*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        /*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */
/*    */ public class ExhaustingReprogramAction
        /*    */   extends AbstractGameAction {
    /* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ReprogramAction");
    /* 14 */   public static final String[] TEXT = uiStrings.TEXT;
    /*    */   private float startingDuration;
    /*    */
    /*    */   public ExhaustingReprogramAction(int numCards) {
        /* 18 */     this.amount = numCards;
        /* 19 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        /* 20 */     this.startingDuration = Settings.ACTION_DUR_FAST;
        /* 21 */     this.duration = this.startingDuration;
        /*    */   }
    /*    */
    /*    */
    /*    */   public void update() {
        /* 26 */     if (this.duration == this.startingDuration) {
            /* 27 */       if (AbstractDungeon.player.drawPile.isEmpty()) {
                /* 28 */         this.isDone = true;
                /*    */         return;
                /*    */       }
            /* 31 */       CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            /* 32 */       for (int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); i++) {
                /* 33 */         tmpGroup.addToTop((AbstractCard)AbstractDungeon.player.drawPile.group
/* 34 */             .get(AbstractDungeon.player.drawPile.size() - i - 1));
                /*    */       }
            /*    */
            /* 37 */       AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, TEXT[0]);
            /*    */     }
        /* 39 */     else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            /* 40 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                /* 41 */         AbstractDungeon.player.exhaustPile.moveToExhaustPile(c);
                /*    */       }
            /* 43 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
            /*    */     }
        /* 45 */     tickDuration();
        /*    */   }
    /*    */ }