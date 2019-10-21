package ch08

/**
  * Bool は二値を取ることの表現
  * Bool = {true, false}
  */
sealed abstract class Bool

object Bool {
  case object True extends Bool
  case object False extends Bool
}

/**
  * Nat は 0 と，Nat の前後値を取ることの表現．
  * (ただし Church 数の代わりに scala.Int を内部で利用)
  *
  * Nat = {0, pred t1, succ t1 | t1: Nat}
  * Nat = {0, 1, 2,..}
  */
case class Nat(value: Int)
