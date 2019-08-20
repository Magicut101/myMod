package theHeart.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theHeart.DefaultMod;
import theHeart.util.TextureLoader;

import static theHeart.DefaultMod.makePowerPath;

public class NoxiousBloodPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("NoxiousBloodPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("NoxiousBlood84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("NoxiousBlood32.png"));

    public NoxiousBloodPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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

    public void atStartOfTurnPostDraw() {
        /* 27 */
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            /* 28 */
            flash();
            /* 29 */
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                /* 30 */
                if (!m.isDead && !m.isDying) {
                    /* 31 */
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount), this.amount));
                }
            }
        }
    }
public void atStartOfTurn(){

    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(source, owner, new PoisonPower(source, this.owner, this.amount), this.amount ));
                            }

    @Override
    public AbstractPower makeCopy() {
        return new BloodClotsPower(owner, source, amount);

    }
}
