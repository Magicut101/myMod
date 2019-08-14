package theHeart.powers;


        import basemod.interfaces.CloneablePowerInterface;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        import com.megacrit.cardcrawl.actions.common.GainBlockAction;
        import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
        import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
        import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
        import com.megacrit.cardcrawl.actions.utility.UseCardAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.localization.PowerStrings;
        import com.megacrit.cardcrawl.powers.AbstractPower;
        import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
        import theHeart.DefaultMod;
        import theHeart.cards.Toil;
        import theHeart.util.TextureLoader;

        import static theHeart.DefaultMod.makePowerPath;


public class LoseEnergyNextTurn extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("LoseEnergyNextTurn");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));


    public LoseEnergyNextTurn(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;


        type = AbstractPower.PowerType.DEBUFF;
        isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();

    }
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.amount));
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner,owner, LoseEnergyNextTurn.POWER_ID, 1));
   }



    public AbstractPower makeCopy() {
        return new LoseEnergyNextTurn(owner, amount);
    }
}

