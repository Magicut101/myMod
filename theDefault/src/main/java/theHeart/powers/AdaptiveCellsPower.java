package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.CURSE;
import static theHeart.DefaultMod.makePowerPath;

public class AdaptiveCellsPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("AdaptiveCellsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public AdaptiveCellsPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;


        type = AbstractPower.PowerType.BUFF;
        isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }


    // On use card, apply (amount) of Dexterity. (Go to the actual power card for the amount.)



//I imagine this counts as hard coded. If I wanted to unhardcode this I would want to look at if a card is a status, exhausts, and has some extra effect.
    //I changed my mind so this is just a glorified evolve.
        @Override
        public void onCardDraw (AbstractCard card){

            if (card.type == AbstractCard.CardType.STATUS) {
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, amount));

        }
        }



    @Override
    public void atStartOfTurn() {
        flash();
       AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner,owner, AdaptiveCellsPower.POWER_ID, 1));
        }




    @Override
    public AbstractPower makeCopy() {
        return new AdaptiveCellsPower(owner, source, amount);
    }
}


