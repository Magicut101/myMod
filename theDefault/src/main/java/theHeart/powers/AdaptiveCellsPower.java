package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;

import static theHeart.DefaultMod.makePowerPath;

public class AdaptiveCellsPower extends AbstractPower implements CloneablePowerInterface {
    private final int damaging;
    private final int drawing;
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("AdaptiveCellsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public AdaptiveCellsPower(final AbstractCreature owner, final AbstractCreature source, final int amount, final int drawing, final int damaging) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.damaging = damaging;
        this.drawing = drawing;


        type = AbstractPower.PowerType.BUFF;
        isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }


    // On use card, apply (amount) of Dexterity. (Go to the actual power card for the amount.)


    @Override
    public void onCardDraw(AbstractCard card) {


            if (card == new Wound()) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction
                        (this.owner, this.owner, this.amount));
            }
            if (card == new Slimed()) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction
                        (this.owner, this.owner, this.amount));
            }        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner,  drawing));
            if (card == new Burn()) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(owner, new DamageInfo(owner, damaging, DamageInfo.DamageType.NORMAL),
                                AbstractGameAction.AttackEffect.NONE));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction
                        (this.owner, this.owner, this.amount));
            }
            if (card == new Dazed()) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner,  drawing));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction
                        (this.owner, this.owner, this.amount));
            }
            if (card == new VoidCard()) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, drawing));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction
                        (this.owner, this.owner, this.amount));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
            }

    }

    @Override
    public void atStartOfTurn() {
        flash();
       AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner,owner, AdaptiveCellsPower.POWER_ID, 1));
        }



    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new AdaptiveCellsPower(owner, source, amount, drawing, damaging);
    }
}
