package theHeart.cards;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import theHeart.DefaultMod;
import theHeart.characters.TheDefault;

import static theHeart.DefaultMod.makeCardPath;


 public class Debilitate extends AbstractDynamicCard {
     public static final String ID = DefaultMod.makeID(Debilitate.class.getSimpleName());
     public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADE_MAGICNUMBER = 3;
//I am going to change this to apply stun, but for now I will let it be -strength, even if its arguably op.
 public Debilitate() {
  super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

           this.exhaust = true;
           this.baseMagicNumber = 3;
          this.magicNumber = this.baseMagicNumber;
      }
 @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(m, p, 1));

         AbstractDungeon.actionManager.addToBottom(new VFXAction(new HeartMegaDebuffEffect()));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower
                 (m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.FIRE));

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower
                 (m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.FIRE));

  ;

     }

    @Override
     public void upgrade() {
         if (!upgraded) {
             upgradeName();
             upgradeMagicNumber(UPGRADE_MAGICNUMBER);
             initializeDescription();
         }
     }
 }

