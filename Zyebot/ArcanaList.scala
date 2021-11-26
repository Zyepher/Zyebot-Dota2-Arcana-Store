package Zyebot

import scalafx.collections.ObservableBuffer

/* A trait that is applicable for Store object, ShoppingCart class, and User class
// since all these three classes need to have their own list of Arcanas */
trait ArcanaList {
  var arcanaList: ObservableBuffer[Arcana] = ObservableBuffer[Arcana]()
}
