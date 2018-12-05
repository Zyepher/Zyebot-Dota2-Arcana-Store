package Assignment

class User() extends ArcanaList {
  var userBalance: Double = 0 // Set user's initial balance to zero

  // Method to add the user's balance which takes amount as parameter.
  def userAddFund(amount: Double): Unit = {
    userBalance = userBalance + amount
  } //End userAddFund method

  def deductAmount(amount: Double): Unit = {
    userBalance = userBalance - amount
  }
} // End case class User
