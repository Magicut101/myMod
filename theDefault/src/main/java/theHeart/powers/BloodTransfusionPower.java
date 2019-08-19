package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;

import static theHeart.DefaultMod.makePowerPath;

public class BloodTransfusionPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("BloodTransfusion");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public BloodTransfusionPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    // When self damaged, apply 1 poison to Self and Enemies. (Everyone)..

    public int onLoseHp (int damageAmount) {

        if (damageAmount > 0 && owner == this.owner) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flash();
                for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                    /* 30 */
                    if (!m.isDead && !m.isDying) {
                        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, amount));
                    }
                }

            }

        }      return damageAmount;
    }
    @Override
    public AbstractPower makeCopy() {
        return new BloodTransfusionPower(owner, amount);
    }


     }
