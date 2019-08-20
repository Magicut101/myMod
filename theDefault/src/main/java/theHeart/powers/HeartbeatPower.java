package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ReboundPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;

import static theHeart.DefaultMod.makePowerPath;
//Couple things, make this power play a sound effect at the start of the turn of a heartbeat and fix Rebound so it works on the first card played rather than second.
    public class HeartbeatPower extends AbstractPower implements CloneablePowerInterface {
        public AbstractCreature source;

        public static final String POWER_ID = DefaultMod.makeID("HeartbeatPower");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


        private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Heartbeat84.png"));
        private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Heartbeat32.png"));

        public HeartbeatPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
        public void atStartOfTurn() {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner , owner, new ReboundPower(owner)));
        }

    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
        public AbstractPower makeCopy() {
            return new HeartbeatPower(owner, source, amount);
        }
    }
