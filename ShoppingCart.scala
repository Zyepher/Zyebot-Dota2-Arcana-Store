package Zyebot

import scalafx.collections.ObservableBuffer

/* A class for the Shopping Cart which is responsible for the user's shopping cart. It takes User type as
// parameter which means that this shopping cart belongs to that user. This is a composition relationship
// since the shopping cart of a user is dependant to the user. */
class ShoppingCart(user: User) extends ArcanaList {
  var totalPrice: Double = 0

  // Method to update the total price of the shopping cart
  def updateTotalPrice(isAdd: Boolean, arcanaPrice: Double): Unit = {
    if(isAdd) totalPrice = totalPrice + arcanaPrice
    else totalPrice = totalPrice - arcanaPrice
  } // End updateTotalPrice

  // Method to reset the total price of Arcanas in the user's shopping cart
  def resetTotalPrice(): Unit = {
    totalPrice = 0
  } // End resetTotalPrice

  // Method to add Arcana into shopping cart.
  def addArcana(itemObj: Arcana): Unit = {
    arcanaList = arcanaList :+ itemObj
  } // End addToUserShoppingCart method

  // Method to remove Arcana from shopping cart.
  def removeArcana(itemObj: Arcana): Unit = {
    arcanaList -= itemObj
  }

  // Method to clear the Arcanas in the shopping cart
  def clearShoppingCart(): Unit = {
    for (x <- Store.arcanaList) {
      x.arcanaShoppingCartQuantity = 1
    }
    arcanaList = ObservableBuffer[Arcana]()
  } // End clearShoppingCart method

  /* Does not require a parameter with type Shopping Cart because
  // this method is within ShoppingCart class */
  def checkOut(user: User): Boolean = {
    // If user balance is greater or equal to the user's shopping cart total price, perform the following operations.
    if (user.userBalance >= totalPrice) {
      // Loop through all available Arcanas by using Store.arcanaList since the store has every Arcanas available.
      for(x <- Store.arcanaList){
        // Calls addInventoryArcanaQuantity which takes x.arcanaName as a signature where x is every Arcana object.
        addInventoryArcanaQuantity(x.arcanaName)
      }

      // While the user's shopping cart is not empty, keep looping this operation.
      while(arcanaList.nonEmpty) {
        // Add the head element in the user's shopping cart Arcana list and add it to user's inventory.
        user.arcanaList = user.arcanaList :+ arcanaList.head

        for(z <- user.arcanaList if z.arcanaName.equals(arcanaList.head.arcanaName)) {
          z.arcanaInventoryQuantity = z.arcanaInventoryQuantity + z.arcanaShoppingCartQuantity
        }

        // After adding it, remove the head element from the user's shopping cart Arcana list.
        removeArcana(arcanaList.head)
      }

      Store.enableArcanaStoreAddToCartButtons() // Enables all Arcana "Add to Cart" buttons in the Arcana Store.
      user.deductAmount(totalPrice) // Deduct user's balance.
      resetTotalPrice() // Reset the total price in the user's shopping cart to zero.
      clearShoppingCart() // Clears the Arcanas in the user's shopping cart.
      true // Returns true.
    } else {
      false // Returns false.
    }
  } // End checkOut method.

  /* This method is to increment the quantity of the Arcana that the user has in their inventory.
  // To do this, we pass the name of the Arcana in the parameter to check later if this particular Arcana
  // is already exist in the user's inventory. */
  def addInventoryArcanaQuantity(arcanaName: String): Unit = {
    // These two are flag variables
    var a: Boolean = false
    var b: Boolean = false

    /* Firstly, we have to loop through the list of arcanas owned by the user.
    // We find out whether or not the user has that one particular Arcana by checking if the name of that particular
    // Arcana is equal to the name of the Arcana that we passed in as the parameter. If yes, we set the flag
    // variable 'a'which is a boolean to true. */
    for(x <- user.arcanaList if x.arcanaName.equals(arcanaName)) {
      a = true
    }

    /* Now with variable 'a' in true condition, it triggers another if statement which is to
    // check whether or not the name of the Arcana that we passed in as the parameter is in the user's shopping cart.
    // If yes, we set the flag variable 'b' which is a boolean to true. */
    if(a) {
      for(y <- arcanaList if y.arcanaName.equals(arcanaName)) {
        b = true
      }
    } // End if(a)

    /* Now with variable 'b' in true condition, we loop through the user's inventory again and find that one
    // particular Arcana. Once it is found, we increment the Arcana's property which is named arcanaInventoryQuantity
    // by one and then delete that Arcana element in the shopping cart. */
    if(b) {
      for(z <- user.arcanaList if z.arcanaName.equals(arcanaName)) {
        z.arcanaInventoryQuantity = z.arcanaInventoryQuantity + z.arcanaShoppingCartQuantity
        removeArcana(z)
      }
    } // End if(b).
  } // End addInventoryArcanaQuantity method.
} // End class Shopping Cart
