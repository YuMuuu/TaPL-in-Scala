package ch09

/**
  * P76: 型構築子は右結合的である．について
  */
trait AssociativityStudy {

  /*
  Double => Int => String は
    Double => (Int => String) であって，
    (Double => Int) => String ではない．
   */

  // Scala はこれを右結合として解釈できる．
  val f: (Double => Int => String) = { x: Double =>
    { y: Int =>
      x * y toString
    }
  }

  /*
  つまり，andThen チェーン的に関数を適用しているわけではないので混同しない．(関数適用は左結合的)
    andThen や compose で合成して作る関数は，型としては 始点 -> 終点 に畳み込まれたものとして解釈される．
   */

  val g: (Double => Int) = (x: Double) => x.toInt
  val h: (Int => String) = (x: Int) => x.toString

  val chained: (Double => String) = g andThen h
}
