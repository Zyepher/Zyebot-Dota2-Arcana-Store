package Zyebot

import scalafx.scene.control.Button

// An abstract class Arcana which is inherited by every arcana objects
abstract class Arcana {
  val arcanaID: Int
  val arcanaName: String
  val arcanaPrice: Double
  val arcanaUsedBy: String
  var arcanaShoppingCartQuantity: Int = 1
  var arcanaInventoryQuantity: Int = 0
  val arcanaAddToCartButton: Button = new Button("Add to Cart")
}

// Object for Lina's Arcana
object LinaArcana extends Arcana {
  val arcanaID: Int = 1
  val arcanaName: String = "Fiery Soul of the Slayer"
  val arcanaUsedBy: String = "Lina"
  val arcanaPrice: Double = 25.55
}

// Object for Legion Commander's Arcana
object LegionCommanderArcana extends Arcana {
  val arcanaID: Int = 2
  val arcanaName: String = "Blades of Voth Domosh"
  val arcanaUsedBy: String = "Legion Commander"
  val arcanaPrice: Double = 24.99
}

// Object for Terrorblade's Arcana
object TerrorbladeArcana extends Arcana {
  val arcanaID: Int = 3
  val arcanaName: String = "Fractal Horns of Inner Abysm"
  val arcanaUsedBy: String = "Terrorblade"
  val arcanaPrice: Double = 25.99
}

// Object for Techies's Arcana
object TechiesArcana extends Arcana {
  val arcanaID: Int = 4
  val arcanaName: String = "Swine of the Sunken Galley"
  val arcanaUsedBy: String = "Techies"
  val arcanaPrice: Double = 22.89
}

// Object for Shadow Fiend's Arcana
object ShadowFiendArcana extends Arcana {
  val arcanaID: Int = 5
  val arcanaName: String = "Demon Eater"
  val arcanaUsedBy: String = "Shadow Fiend"
  val arcanaPrice: Double = 23.49
}

// Object for Phantom Assassin's Arcana
object PhantomAssassinArcana extends Arcana {
  val arcanaID: Int = 6
  val arcanaName: String = "Manifold Paradox"
  val arcanaUsedBy: String = "Phantom Assassin"
  val arcanaPrice: Double = 26.99
}

// Object for Crystal Maiden's Arcana
object CrystalMaidenArcana extends Arcana {
  val arcanaID: Int = 7
  val arcanaName: String = "Frost Avalanche"
  val arcanaUsedBy: String = "Crystal Maiden"
  val arcanaPrice: Double = 24.80
}

// Object for Zeus's Arcana
object ZeusArcana extends Arcana {
  val arcanaID: Int = 8
  val arcanaName: String = "Tempest Helm of the Thundergod"
  val arcanaUsedBy: String = "Zeus"
  val arcanaPrice: Double = 25.18
}

// Object for Monkey King's Arcana
object MonkeyKingArcana extends Arcana {
  val arcanaID: Int = 9
  val arcanaName: String = "Great Sage's Reckoning"
  val arcanaUsedBy: String = "Monkey King"
  val arcanaPrice: Double = 25.99
}

// Object for Juggernaut's Arcana
object JuggernautArcana extends Arcana {
  val arcanaID: Int = 10
  val arcanaName: String = "Bladeform Legacy"
  val arcanaUsedBy: String = "Juggernaut"
  val arcanaPrice: Double = 24.30
}

// Object for Pudge's Arcana
object PudgeArcana extends Arcana {
  val arcanaID: Int = 11
  val arcanaName: String = "Feast of Abscession"
  val arcanaUsedBy: String = "Pudge"
  val arcanaPrice: Double = 23.99
}