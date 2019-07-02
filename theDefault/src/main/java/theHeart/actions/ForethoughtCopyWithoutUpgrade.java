package theHeart.actions;



         /*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
         /*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
         /*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
         /*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
         /*    */ import com.megacrit.cardcrawl.core.Settings;
         /*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
         /*    */ import com.megacrit.cardcrawl.localization.UIStrings;

/*    */ public class ForethoughtCopyWithoutUpgrade extends AbstractGameAction {
    private AbstractPlayer p;
    /* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ForethoughtAction");
    /* 14 */   public static final String[] TEXT = uiStrings.TEXT;
    /*    */   private boolean chooseAny;

    public ForethoughtCopyWithoutUpgrade() {
        this.p = AbstractDungeon.player;
        /* 19 */     this.duration = Settings.ACTION_DUR_FAST;
        /* 20 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        /* 21 */
        /*    */   }


    /*    */   public void update() {
        /* 26 */     if (this.duration == Settings.ACTION_DUR_FAST) {
            /* 27 */       if (this.p.hand.isEmpty()) {
                /* 28 */         this.isDone = true; return;
                /*    */       }
            /* 30 */       if (this.p.hand.size() == 1 && !this.chooseAny) {
                /* 31 */         AbstractCard c = this.p.hand.getTopCard();
                /* 32 */         if (c.cost > 0) {
                    /* 33 */           c.freeToPlayOnce = true;
                    /*    */         }
                /* 35 */         this.p.hand.moveToBottomOfDeck(c);
                /* 36 */         AbstractDungeon.player.hand.refreshHandLayout();
                /* 37 */         this.isDone = true;
                /*    */         return;
                /*    */    }
                /* 43 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, true, true);
                /*    */       }
            /* 45 */       tickDuration();
            /*    *
            /*    */
            /*    */       return;
            /*    */     }
        /*    */
        /*    *
        /*    */   }
    /*    */