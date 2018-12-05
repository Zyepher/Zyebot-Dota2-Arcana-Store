package Assignment

class User() extends ArcanaList {
  var userBalance: Double = 0 // Set user's initial balance to zero.

  // Method to add the user's balance which takes amount as parameter.
  def userAddFund(amount: Double): Unit = {
    userBalance = userBalance + amount
  } //End userAddFund method.

  // Method to deduct the user's balance when they checkout.
  def deductAmount(amount: Double): Unit = {
    userBalance = userBalance - amount
  }//End deductAmount method.
} // End class User.
