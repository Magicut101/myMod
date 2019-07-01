package theHeart.cards;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
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
    private static final int BASE_TURNS = 3;

 public Debilitate() {
  super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

           this.exhaust = true;
           this.baseMagicNumber = 3;
          this.magicNumber = this.baseMagicNumber;
      }
 @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower
                 (mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.FIRE));

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower
                 (mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.FIRE));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber), -magicNumber));

     }
 }
    @Override
     public void upgrade() {
         if (!upgraded) {
             upgradeName();
             upgradeMagicNumber(2);
             initializeDescription();
         }
     }
 }

