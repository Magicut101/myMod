
package theHeart.powers;

    import basemod.interfaces.CloneablePowerInterface;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.TextureAtlas;
    import com.megacrit.cardcrawl.actions.common.DamageAction;
    import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
    import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
    import com.megacrit.cardcrawl.cards.AbstractCard;
    import com.megacrit.cardcrawl.cards.DamageInfo;
    import com.megacrit.cardcrawl.characters.AbstractPlayer;
    import com.megacrit.cardcrawl.core.AbstractCreature;
    import com.megacrit.cardcrawl.core.CardCrawlGame;
    import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
    import com.megacrit.cardcrawl.localization.PowerStrings;
    import com.megacrit.cardcrawl.powers.AbstractPower;
    import theHeart.DefaultMod;

    import theHeart.cards.HyperTrophy;
    import theHeart.util.TextureLoader;

    public class HyperTrophyPower extends AbstractPower implements CloneablePowerInterface {
        public AbstractCreature source;
        //Originally I wanted this to multiply your max hp, so as to stimulate how high the Heart's hp can go, but I think I am just going to make the power give you x3 of your max hp as temporary hp,
        // while multiplying all incoming  damage by a slightly lower ratio. x3 hp, 2.75 damage received. At least for now. Ideally I want there to be a cost to increasing your hp, primarily
        //So for example, you have 50/100 hp, you play this power multiply your hp x3, 150/300. You take x2 or x2.5. So a 10 damage hit hits for 25.
        // If you lose hp by the end of combat your hp/max hp is reduced by 3.

        public static final String POWER_ID = DefaultMod.makeID("HyperTrophyPower");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        int damagedInCombat;
        private static final Texture tex84 = TextureLoader.getTexture("HyperTrophy84.png");
        private static final Texture tex32 = TextureLoader.getTexture("HyperTrophy32.png");

        public HyperTrophyPower(final AbstractCreature owner, final AbstractCreature source, final int amount, int damagedInCombat) {
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

        private static final float EFFECTIVENESS = 3.00F;
        private static final int EFFECTIVENESS_STRING = 300;


        @Override
        public void onInitialApplication() {

            final int healthAmount = 200;
            AbstractDungeon.player.increaseMaxHp(healthAmount, false);
        }

        public void onVictory() {
            int damaged = AbstractDungeon.player.damagedThisCombat;

            final int ReduceHealthAmount = 200;
            AbstractDungeon.player.decreaseMaxHealth(ReduceHealthAmount);
            AbstractDungeon.actionManager.addToBottom(new DamageAction(owner,new DamageInfo(owner, damagedInCombat), damagedInCombat));

        }




        @Override
        public float atDamageReceive(float damage, DamageInfo.DamageType type) {

            if (type == DamageInfo.DamageType.NORMAL) {
            }
            return damage * 3.00F;
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
            return new HyperTrophyPower(owner, source, amount, damagedInCombat);
        }
        }