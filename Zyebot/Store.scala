package Zyebot

// A singleton object which is responsible for the list of Arcanas available Store
object Store extends ArcanaList {
  def enableArcanaStoreAddToCartButtons(): Unit = {
    /* Loop through all available Arcanas by using Store.arcanaList since the store has every Arcanas available.
    // Then, set the Arcana's object property arcanaButton to be available (clickable). */
    for(x <- Store.arcanaList) {
      x.arcanaAddToCartButton.disable_=(false)
    }
  } // End enableArcanaButtons method
}
