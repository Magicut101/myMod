package theHeart.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theHeart.cards.AbstractDefaultCard;

import static theHeart.DefaultMod.makeID;

public class TotalDamageFromStatusCardsNumber extends DynamicVariable {

        //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

        @Override
        public String key() {
            return makeID("TotalDamageFromStatusCardsNumber");
            // This is what you put between "!!" in your card strings to actually display the number.
            // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
            // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
        }

        @Override
        public boolean isModified(AbstractCard card) {
            return ((AbstractDefaultCard) card).isDamageModified;

        }

        @Override
        public int value(AbstractCard card) {
            int statuscards = 0;

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    statuscards++;
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    statuscards++;
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    statuscards++;
                }
            }
            return card.damage =  statuscards * card.magicNumber;
        }


        @Override
        public int baseValue(AbstractCard card) {

            int statuscards = 0;

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    statuscards++;
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    statuscards++;
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    statuscards++;
                }
            }
            return card.baseDamage =  statuscards * card.magicNumber;

        }

        @Override
        public boolean upgraded(AbstractCard card) {
            return  true;
        }
    }
