package Zyebot

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{TableColumn, _}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Stops, _}
import scalafx.scene.text.Text
import scala.language.implicitConversions


object Application extends JFXApp {
  // Instantiates the user, user's shopping cart, and add Arcanas into the Store.
  val user: User = new User()
  val userShoppingCart: ShoppingCart = new ShoppingCart(user)
  Store.arcanaList = Store.arcanaList :+ LinaArcana :+ LegionCommanderArcana :+ TerrorbladeArcana :+
    TechiesArcana :+ ShadowFiendArcana :+ PhantomAssassinArcana :+ CrystalMaidenArcana :+ ZeusArcana :+
    MonkeyKingArcana :+ JuggernautArcana :+ PudgeArcana

  // To construct ScalaFX buttons.
  val myShoppingCartButton = new Button("My Shopping Cart")
  val actionLabel = new Label("Action")
  val returnToMainMenuButton = new Button("Return to Main Menu")

  // Returns user to the main menu when "Return to Main Menu" button is clicked.
  returnToMainMenuButton.onAction = (_: ActionEvent) => mainMenuUI()

  mainMenuUI() // Runs the application by calling main menu function.

  // The function for the Main Menu user interface.
  def mainMenuUI(): Unit = {
    stage = new PrimaryStage {
      title = "Zyebot: #1 Dota 2 Arcana Store (Main Menu)"
      scene = new Scene(400, 300) {
        fill = rgb(10, 16, 26)

        // To construct and position ScalaFX buttons.
        val arcanaStoreButton = new Button("Arcana Store")
        arcanaStoreButton.layoutX = 150
        arcanaStoreButton.layoutY = 50

        myShoppingCartButton.layoutX = 133
        myShoppingCartButton.layoutY = 100

        val myInventoryButton = new Button("My Inventory")
        myInventoryButton.layoutX = 150
        myInventoryButton.layoutY = 150

        val addFundsButton = new Button("Add Funds")
        addFundsButton.layoutX = 157
        addFundsButton.layoutY = 200

        // The content for the UI.
        content = List(arcanaStoreButton, myShoppingCartButton, myInventoryButton, addFundsButton)

        // Brings user to the respective UI when clicked.
        arcanaStoreButton.onAction = (_: ActionEvent) => arcanaStoreUI()
        myShoppingCartButton.onAction = (_: ActionEvent) => shoppingCartUI()
        myInventoryButton.onAction = (_: ActionEvent) => inventoryUI()
        addFundsButton.onAction = (_: ActionEvent) => addFundsUI()
      } // End Scene.
    } // End PrimaryStage.
  } // End mainMenuUI().

  // The function for the Arcana Store user interface.
  def arcanaStoreUI(): Unit = {
    stage = new PrimaryStage {
      title = "Zyebot: #1 Dota 2 Arcana Store (Arcana Store)"
      scene = new Scene(608, 370) {
        fill = White

        // To create a table for Arcana store.
        val data = ObservableBuffer(Store.arcanaList)
        val arcanaStoreTable = new TableView(data)
        val col1 = new TableColumn[Arcana, Int]("Arcana ID")
        col1.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaID)
        val col2 = new TableColumn[Arcana, String]("Name")
        col2.cellValueFactory = cdf => StringProperty(cdf.value.arcanaName)
        val col3 = new TableColumn[Arcana, String]("Used by")
        col3.cellValueFactory = cdf => StringProperty(cdf.value.arcanaUsedBy)
        val col4 = new TableColumn[Arcana, Double]("Price ($)")
        col4.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaPrice)

        // To customise the width and height of the Arcana Store table and add the columns into the table.
        arcanaStoreTable.minWidth_=(519)
        arcanaStoreTable.maxHeight_=(303)
        arcanaStoreTable.columns ++= List(col1, col2, col3, col4)

        // To position the buttons on the UI.
        returnToMainMenuButton.layoutX = 110
        returnToMainMenuButton.layoutY = 320

        actionLabel.layoutX = 542
        actionLabel.layoutY = 5

        myShoppingCartButton.layoutX = 350
        myShoppingCartButton.layoutY = 320

        LinaArcana.arcanaAddToCartButton.layoutX = 519
        LinaArcana.arcanaAddToCartButton.layoutY = 25

        LegionCommanderArcana.arcanaAddToCartButton.layoutX = 519
        LegionCommanderArcana.arcanaAddToCartButton.layoutY = 50

        TerrorbladeArcana.arcanaAddToCartButton.layoutX = 519
        TerrorbladeArcana.arcanaAddToCartButton.layoutY = 75

        TechiesArcana.arcanaAddToCartButton.layoutX = 519
        TechiesArcana.arcanaAddToCartButton.layoutY = 100

        ShadowFiendArcana.arcanaAddToCartButton.layoutX = 519
        ShadowFiendArcana.arcanaAddToCartButton.layoutY = 125

        PhantomAssassinArcana.arcanaAddToCartButton.layoutX = 519
        PhantomAssassinArcana.arcanaAddToCartButton.layoutY = 150

        CrystalMaidenArcana.arcanaAddToCartButton.layoutX = 519
        CrystalMaidenArcana.arcanaAddToCartButton.layoutY = 175

        ZeusArcana.arcanaAddToCartButton.layoutX = 519
        ZeusArcana.arcanaAddToCartButton.layoutY = 200

        MonkeyKingArcana.arcanaAddToCartButton.layoutX = 519
        MonkeyKingArcana.arcanaAddToCartButton.layoutY = 225

        JuggernautArcana.arcanaAddToCartButton.layoutX = 519
        JuggernautArcana.arcanaAddToCartButton.layoutY = 250

        PudgeArcana.arcanaAddToCartButton.layoutX = 519
        PudgeArcana.arcanaAddToCartButton.layoutY = 275
        // End positioning buttons.

        // Loops through the available Arcanas in order to find out which button was clicked.
        for(x <- Store.arcanaList) {
          arcanaAddToCartButtonOnAction(x)
        }

        // A function that is executed when the "Add to Cart" button is clicked in the Arcana Store.
        def arcanaAddToCartButtonOnAction(arcana: Arcana): Unit = {
          arcana.arcanaAddToCartButton.onAction = (_: ActionEvent) => {
            userShoppingCart.addArcana(arcana) // Add the respective Arcana to the user's shopping cart.
            userShoppingCart.updateTotalPrice(isAdd = true, arcana.arcanaPrice) // Update the total price.
            arcana.arcanaAddToCartButton.disable_=(true) // Disable the respective Arcana button.
            arcanaStoreUI() // Refresh the UI to update.
          }
        }

        // The content for Arcana Store UI.
        content = List(returnToMainMenuButton, LinaArcana.arcanaAddToCartButton,
          LegionCommanderArcana.arcanaAddToCartButton, TerrorbladeArcana.arcanaAddToCartButton,
          TechiesArcana.arcanaAddToCartButton, ShadowFiendArcana.arcanaAddToCartButton,
          PhantomAssassinArcana.arcanaAddToCartButton, CrystalMaidenArcana.arcanaAddToCartButton,
          ZeusArcana.arcanaAddToCartButton, MonkeyKingArcana.arcanaAddToCartButton,
          JuggernautArcana.arcanaAddToCartButton, PudgeArcana.arcanaAddToCartButton, arcanaStoreTable,
          actionLabel, myShoppingCartButton)
      } // End Scene.
    } // End PrimaryStage.
  } // End ArcanaStoreUI().

  // The function for the Shopping Cart user interface.
  def shoppingCartUI(): Unit = {
    stage = new PrimaryStage {
      title = "Zyebot: #1 Dota 2 Arcana Store (My Shopping Cart)"
      scene = new Scene(595, 500) {
        fill = White

        // To create a table for shopping cart.
        val userShoppingCartTable = new TableView(userShoppingCart.arcanaList)
        val col1 = new TableColumn[Arcana, Int]("Arcana ID")
        col1.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaID)
        val col2 = new TableColumn[Arcana, String]("Name")
        col2.minWidth_=(226)
        col2.cellValueFactory = cdf => StringProperty(cdf.value.arcanaName)
        val col3 = new TableColumn[Arcana, String]("Used by")
        col3.minWidth_=(138)
        col3.cellValueFactory = cdf => StringProperty(cdf.value.arcanaUsedBy)
        val col4 = new TableColumn[Arcana, Double]("Price ($)")
        col4.minWidth_=(76)
        col4.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaPrice)
        val col5: TableColumn[Arcana, Int] = new TableColumn[Arcana, Int]("Quantity")
        col5.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaShoppingCartQuantity)

        // To customise the width and height of the Shopping Cart table and add the columns into the table.
        userShoppingCartTable.minWidth_=(595)
        userShoppingCartTable.maxHeight_=(303)
        userShoppingCartTable.columns ++= List(col1, col2, col3, col4, col5)

        // To display the total price of the user's shopping cart.
        val totalPriceLabel: HBox = new HBox {
          padding = Insets(0, 0, 0, 0)
          children = Seq(
            new Text {
              text = "Total Price: $" + s"${(userShoppingCart.totalPrice * 100).round / 100.toDouble}"
              style = "-fx-font: normal bold 11pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Black, Black))
            })
        }

        // To display the user's balance.
        val userBalanceLabel: HBox = new HBox {
          padding = Insets(0, 0, 0, 0)
          children = Seq(
            new Text {
              text = "Current Balance: $" + s"${(user.userBalance * 100).round / 100.toDouble}"
              style = "-fx-font: normal bold 11pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Black, Black))
            })
        }

        // To create and position the buttons on the UI.
        val clearShoppingCartButton = new Button("Clear Shopping Cart")
        clearShoppingCartButton.layoutX = 40
        clearShoppingCartButton.layoutY = 400

        val checkoutButton = new Button("Checkout")
        checkoutButton.layoutX = 250
        checkoutButton.layoutY = 400

        val incrementQuantityButton = new Button("Increment")
        incrementQuantityButton.layoutX = 390
        incrementQuantityButton.layoutY = 400
        incrementQuantityButton.disable_=(true)

        val decrementQuantityButton = new Button("Decrement")
        decrementQuantityButton.layoutX = 470
        decrementQuantityButton.layoutY = 400
        decrementQuantityButton.disable_=(true)

        // To position the buttons on the UI.
        totalPriceLabel.layoutX = 238
        totalPriceLabel.layoutY = 320

        userBalanceLabel.layoutX = 220
        userBalanceLabel.layoutY = 350

        returnToMainMenuButton.layoutX = 215
        returnToMainMenuButton.layoutY = 450

        // When a row is clicked in the Shopping Cart table, enables "Increment" and "Decrement" buttons.
        userShoppingCartTable.selectionModel.apply.selectedItem.onChange {
          incrementQuantityButton.disable_=(false)
          decrementQuantityButton.disable_=(false)

        // When "Increment" button is clicked, we increment the quantity of that particular Arcana and update the
        // user's shopping cart total price. After that, we refresh the UI to update. */
          incrementQuantityButton.onAction = (_: ActionEvent) => {
            userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaShoppingCartQuantity += 1
            userShoppingCart.updateTotalPrice(isAdd = true,
              userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaPrice)
            shoppingCartUI()
          } // End incrementQuantityButton.onAction.

          /* Similarly for "Decrement" button where it decrements the the quantity of that particular Arcana does
          // somewhat similar operation accordingly. */
          decrementQuantityButton.onAction = (_: ActionEvent) => {
            userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaShoppingCartQuantity -= 1
            userShoppingCart.updateTotalPrice(isAdd = false,
              userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaPrice)

            /* When the quantity reaches zero, to avoid negative number in the table, we set the quantity of that Arcana
            // to 1 but removes that row from the table. The reason we set it back to 1 is so that when the user adds it
            // back to the shopping cart, the quantity will be 1 because otherwise it would become zero. */
            if(userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaShoppingCartQuantity == 0) {
              userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaShoppingCartQuantity += 1
              userShoppingCartTable.selectionModel.apply.selectedItem.value.arcanaAddToCartButton.disable_=(false)
              userShoppingCart.arcanaList -= userShoppingCartTable.selectionModel.apply.selectedItem.value
            }

            /* If the user remove everything from the table manually, we want to enable back all the "Add to Cart"
            // buttons in the Store. Also, we need to reset the total price back to zero. Finally, we refresh the UI */
            if(userShoppingCart.arcanaList.isEmpty){
              Store.enableArcanaStoreAddToCartButtons()
              userShoppingCart.resetTotalPrice()
            }
            shoppingCartUI()
          } // End decrementQuantityButton.onAction.
        } // End userShoppingCartTable.selectionModel.apply.selectedItem.onChange.

        /* If the user visit their shopping cart when it is empty, we disable the "Clear Shopping Cart" button and the
        // "checkout" button. */
        if(userShoppingCart.arcanaList.isEmpty) {
          clearShoppingCartButton.disable_=(true)
          checkoutButton.disable_=(true)
        }

        // When the "Clear Shopping Cart" button, we prompts the user for confirmation.
        clearShoppingCartButton.onAction = (_: ActionEvent) => {
          val clearShoppingCartAlert = new Alert(AlertType.Confirmation) {
            title = "Clearing Shopping Cart"
            headerText = "Are you sure that you want to clear your shopping cart?"
            contentText = "You may cancel or proceed by clicking 'OK'."
            buttonTypes = Seq(ButtonType.OK, ButtonType.Cancel)
          }

          val result = clearShoppingCartAlert.showAndWait()
          // Performs pattern matching for the result.
          result match {
            // When the user chose to clear shopping cart.
            case Some(ButtonType.OK) => {
              userShoppingCart.clearShoppingCart()
              Store.enableArcanaStoreAddToCartButtons()
              userShoppingCart.resetTotalPrice()
              shoppingCartUI()
            }
            case _ => // When the user chose to cancel, do nothing.
          } // End pattern matching.
        } // End clearShoppingCartButton.onAction.

        // When "Checkout" button is clicked, we check if the user has enough balance. If yes, then we display
        // "Checkout Succeed" alert. If no, then we display "Checkout Failed" alert.
        checkoutButton.onAction = (_: ActionEvent) => {
          if(userShoppingCart.checkOut(user)){
            shoppingCartUI()
            new Alert(AlertType.Information) {
              title = "Checkout Succeed"
              headerText = "Thank you for shopping with us!"
              contentText = "The Arcanas has been added to your inventory."
            }.showAndWait()
          } else {
            new Alert(AlertType.Error) {
              title = "Checkout Failed"
              headerText = "Your balance is insufficient to checkout."
              contentText = "Please update the shopping cart or add funds."
            }.showAndWait()
          }
        } // End checkoutButton.onAction.

        // The content for Shopping Cart UI.
        content = List(userShoppingCartTable, returnToMainMenuButton, totalPriceLabel, checkoutButton, userBalanceLabel,
          clearShoppingCartButton, incrementQuantityButton, decrementQuantityButton)

      } // End Scene.
    } // End PrimaryStage.
  } // End ShoppingCartUI().

  // The function for the Inventory user interface.
  def inventoryUI(): Unit = {
    stage = new PrimaryStage {
      title = "Zyebot: #1 Dota 2 Arcana Store (My Inventory)"
      scene = new Scene(515, 380) {
        fill = White

        // To create a table for user's inventory
        val userInventoryTable = new TableView(user.arcanaList)
        val col1 = new TableColumn[Arcana, String]("Name")
        col1.cellValueFactory = cdf => StringProperty(cdf.value.arcanaName)
        col1.minWidth_=(226)
        val col2 = new TableColumn[Arcana, String]("Used by")
        col2.cellValueFactory = cdf => StringProperty(cdf.value.arcanaUsedBy)
        col2.minWidth_=(138)
        val col3 = new TableColumn[Arcana, Double]("Value ($)")
        col3.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaPrice)
        col3.minWidth_=(76)
        val col4 = new TableColumn[Arcana, Int]("Quantity")
        col4.cellValueFactory = cdf => ObjectProperty(cdf.value.arcanaInventoryQuantity)

        // To customise the width and height of the Inventory table and add the columns into the table.
        userInventoryTable.minWidth_=(514)
        userInventoryTable.maxHeight_=(303)
        userInventoryTable.columns ++= List(col1, col2, col3, col4)

        // To position "Return to Main Menu" button on the UI.
        returnToMainMenuButton.layoutX = 190
        returnToMainMenuButton.layoutY = 320

        // The content for Inventory UI.
        content = List(returnToMainMenuButton, userInventoryTable)

      } // End Scene.
    } // End PrimaryStage.
  } // End inventoryUI().

  // The function for the Add Funds user interface.
  def addFundsUI(): Unit = {
    stage = new PrimaryStage {
      title = "Zyebot: #1 Dota 2 Arcana Store (Add Funds)"
      scene = new Scene(360, 360) {
        fill = rgb(10, 16, 26)

        // To display the text to guide the user.
        val addFundsLabel: HBox = new HBox {
          padding = Insets(30, 20, 10, 20)
          children = Seq(
            new Text {
              text = "Select The Amount of Funds You Wish To Add"
              style = "-fx-font: normal bold 11pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(White, White))
            })
        }

        // To display the user's balance.
        val userBalanceLabel: HBox = new HBox {
          padding = Insets(0, 0, 0, 0)
          children = Seq(
            new Text {
              text = "Current balance: $" + s"${(user.userBalance * 100).round / 100.toDouble}"
              style = "-fx-font: normal bold 11pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(White, White))
            })
        }

        // To create and position the buttons on the UI.
        val add10DollarButton = new Button("$10.00 ")
        add10DollarButton.layoutX = 45
        add10DollarButton.layoutY = 100

        val add25DollarButton = new Button(" $25.00")
        add25DollarButton.layoutX = 145
        add25DollarButton.layoutY = 100

        val add50DollarButton = new Button(" $50.00")
        add50DollarButton.layoutX = 245
        add50DollarButton.layoutY = 100

        val add100DollarButton = new Button("$100.00")
        add100DollarButton.layoutX = 45
        add100DollarButton.layoutY = 150

        val add250DollarButton = new Button("$250.00")
        add250DollarButton.layoutX = 145
        add250DollarButton.layoutY = 150

        val add500DollarButton = new Button("$500.00")
        add500DollarButton.layoutX = 245
        add500DollarButton.layoutY = 150

        // To position the buttons on the UI.
        returnToMainMenuButton.layoutX = 100
        returnToMainMenuButton.layoutY = 280

        userBalanceLabel.layoutX = 100
        userBalanceLabel.layoutY = 220

        // When the respective button is clicked, call the addFundsButtonClicked(amount) function with appropriate
        // amount passed as a parameter for the function signature. */
        add10DollarButton.onAction = (_: ActionEvent) => addFundsButtonClicked(10)
        add25DollarButton.onAction = (_: ActionEvent) => addFundsButtonClicked(25)
        add50DollarButton.onAction = (_: ActionEvent) => addFundsButtonClicked(50)
        add100DollarButton.onAction = (_: ActionEvent) => addFundsButtonClicked(100)
        add250DollarButton.onAction = (_: ActionEvent) => addFundsButtonClicked(250)
        add500DollarButton.onAction = (_: ActionEvent) => addFundsButtonClicked(500)

        // The function that is executed when the user click one of the add funds button.
        def addFundsButtonClicked(amount: Double): Unit = {
          user.userAddFund(amount) // Adds user's balance using userAddFund method in case class User.
          addFundsUI() // Calls addFundsUI() to refresh and display the updated user interface for Add Funds UI.
          displayFundsAddedAlert() // Calls displayFundsAddedAlert() function.
        }

        // A function to display an alert when user added funds.
        def displayFundsAddedAlert(): Unit = {
          new Alert(AlertType.Information) {
            title = "Funds Are Successfully Added"
            headerText = "Thank you for adding funds!"
            contentText = "You can spend your balance to buy Arcana."
          }.showAndWait()
        } // End displayFundsAddedAlert().

        // The content for Add Funds UI.
        content = List(add10DollarButton, add25DollarButton, add50DollarButton, add100DollarButton, add250DollarButton,
          add500DollarButton, returnToMainMenuButton, addFundsLabel, userBalanceLabel)

      } // End Scene.
    } // End PrimaryStage.
  } // End addFundsUI().
}
