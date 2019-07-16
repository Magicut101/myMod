package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import basemod.patches.com.megacrit.cardcrawl.cards.CardGroup.MoveToExhaustPileHook;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.deprecated.DamagePerCardAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;
import static theHeart.DefaultMod.makePowerPath;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

    //All cards cost 0, they transform into wounds when played.
    public class MyocardialRupturePower extends AbstractPower implements CloneablePowerInterface {
public AbstractCreature source;

public static final String POWER_ID = DefaultMod.makeID("MyocardialRupturePower");
private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
public static final String NAME = powerStrings.NAME;
public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

public MyocardialRupturePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
        }





public void onInitialApplication() {
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
        c.modifyCostForCombat(-9);
    }
    for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
        c.modifyCostForCombat(-9);
    }
    for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
        c.modifyCostForCombat(-9);
    }
    for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
        c.modifyCostForCombat(-9);
    }
}

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m) {
    card.purgeOnUse = true;
AbstractDungeon.actionManager.addToBottom(new LoseHPAction(owner, source, amount));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Wound()));
        }
        public AbstractPower makeCopy() {
            return new MyocardialRupturePower(owner, source, amount);
        }

    }

