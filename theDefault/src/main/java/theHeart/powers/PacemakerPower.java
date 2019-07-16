package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Reprogram;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeart.DefaultMod;
import theHeart.actions.ExhaustingReprogramAction;
import theHeart.cards.AbstractDynamicCard;
import theHeart.cards.DefaultBaseCardsOrOtherExamples.DefaultRareAttack;
import theHeart.util.TextureLoader;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.STATUS;

public class PacemakerPower extends AbstractPower implements CloneablePowerInterface {
        public AbstractCreature source;

        public static final String POWER_ID = DefaultMod.makeID("RarePower");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
        private static final Texture tex84 = TextureLoader.getTexture("theHeartResources/images/powers/placeholder_power84.png");
        private static final Texture tex32 = TextureLoader.getTexture("theHeartResources/images/powers/placeholder_power32.png");

        public PacemakerPower (final AbstractCreature owner, final AbstractCreature source, final int amount) {
            name = NAME;
            ID = POWER_ID;

            this.owner = owner;
            this.amount = amount;
            this.source = source;

            type = PowerType.BUFF;

            // We load those textures here.
            this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

            updateDescription();
        }

    @Override
    public void atStartOfTurn() { // At the start of your turn
        AbstractCard playCard = new Reprogram(); // Declare Card - the DefaultRareAttack card. We will name it 'playCard'.
        AbstractMonster targetMonster = AbstractDungeon.getRandomMonster(); // Declare Target - Random Monster. We will name the monster 'targetMonster'.

        playCard.freeToPlayOnce = true; //Self Explanatory

        if (playCard.type != AbstractCard.CardType.POWER) {
            playCard.purgeOnUse = true;
        }

        // Remove completely on use (Not Exhaust). A note - you don't need the '{}' in this if statement,
        // as it's just 1 line directly under. You can remove them, if you want. In fact, you can even put it all on 1 line:
            isTurnBased = false;
            //  if (playCard.type != AbstractCard.CardType.POWER) playCard.purgeOnUse = true; - works identically

            AbstractDungeon.actionManager.addToBottom(new QueueCardAction(playCard, targetMonster)); // Play the card on the target.
        }

        @Override
        public void updateDescription() {
            if (amount == 1) {
                description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
            } else if (amount > 1) {
                description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
            }
        }

        @Override
        public AbstractPower makeCopy() {
            return new theHeart.powers.PacemakerPower (owner, source, amount);
        }
    }
