package Assignment

// A singleton object which is responsible for the list of Arcanas available in the Arcana Store.
object Store extends ArcanaList {
  // This method is to enable all the Arcanas' "Add to Cart" button in the Arcana Store.
  def enableArcanaStoreAddToCartButtons(): Unit = {
    /* Loop through all available Arcanas by using Store.arcanaList since the store has every Arcanas available.
    // Then, set the Arcana's object property arcanaButton to be available (clickable). */
    for(x <- Store.arcanaList) {
      x.arcanaAddToCartButton.disable_=(false)
    }
  } // End enableArcanaButtons method.
}
