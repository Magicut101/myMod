package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;

public class NoDrawPowerTurnBased  extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DefaultMod.makeID("NoDrawPowerTurnBased");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theHeartResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theHeartResources/images/powers/placeholder_power32.png");

    public NoDrawPowerTurnBased (final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;


        type = PowerType.DEBUFF;
        isTurnBased = true;
        loadRegion("noDraw");
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

        /*    */   private boolean justApplied;
        /*    */
        /* 30 */   public void onInitialApplication() { AbstractDungeon.player.gameHandSize--; }
        /*    */
        /*    */
        /*    */
         public void atEndOfRound() {
/* 35 */     if (this.justApplied) {
        /* 36 */       this.justApplied = false;
        /*    */
        /*    */       return;
        /*    */     }
    /* 40 */     if (this.amount == 0) {
        /* 41 */       AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "NoDrawPowerTurnBased"));
        /*    */     } else {
        /* 43 */       AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "NoDrawPowerTurnBased", 1));
        /*    */     }
    /*    */   }
        /*    */
        /* 45 */   public void onRemove() { AbstractDungeon.player.gameHandSize++; }
/*

    /*    */   public void stackPower(int stackAmount) {
        /* 41 */     this.fontScale = 8.0F;
        /* 42 */     this.amount += stackAmount;
        /*    */   }

    @Override
    public AbstractPower makeCopy() {
        return new theHeart.powers.NoDrawPowerTurnBased (owner, amount);
    }
   }
