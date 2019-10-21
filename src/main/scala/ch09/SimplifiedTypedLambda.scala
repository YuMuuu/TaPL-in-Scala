package ch09

/*
単純型付きラムダ計算．変数，ラムダ抽象，関数適用の型付け規則について study.
 */

/**
  * [変数]
  *   Bool は二値を取ることの表現
  *   Bool = {true, false}
  */
sealed abstract class Bool

object Bool {
  case object True extends Bool
  case object False extends Bool
}

/**
  * [ラムダ抽象]
  *   型付き関数型 T -> T の表現．
  *   λx.t を λx:T.t とするとこで，束縛する変数 x の型を T と明示的に注釈する．
  */
trait Lambda[T] {
  def lambda(t1: T): T
}

object SimplifiedTypedLambda extends Lambda[Bool] {

  // 実行用に適当な実装 (Bool 版)
  def lambda(t1: Bool): Bool = t1 match {
    case Bool.True  => Bool.False
    case Bool.False => Bool.True
  }

  /**
    * [関数適用]
    *   Scala のシステムを用いる.
    *
    */
  val result1 = lambda(Bool.True) // result1: Bool = Bool.False
  val result2 = lambda(lambda(Bool.True)) // result2: Bool = Bool.True
}
