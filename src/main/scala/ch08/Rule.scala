package ch08

/**
  * Bool, Nat に関する型付け規則．
  * ch08 の時点では実装については触れていない．
  */
trait Rule {
  def myIf[T](t1: Bool, t2: T, t3: T): T
  def succ(t1: Nat): Nat
  def pred(t1: Nat): Nat
}

/**
  * 動かすためにそれっぽく実装．
  * この実装自体は ch08 の内容から外れる．
  */
object RuleImpl extends Rule {
  def myIf[T](t1: Bool, t2: T, t3: T): T = t1 match {
    case Bool.True  => t2
    case Bool.False => t3
  }
  def succ(t1: Nat): Nat = Nat(t1.value + 1)
  def pred(t1: Nat): Nat = Nat(math.max(t1.value - 1, 0))
}

/**
  * おまけ
  */
object BadRuleImpl {
  // 静的な情報を用いた構文解析なので，第一引数が Bool.True に見えたとしても第二引数の型で型付けできない．
  def myIf(t1: Bool = Bool.True, t2: Bool, t3: Nat): Any = t1 match {
    case Bool.True  => t2
    case Bool.False => t3
  }

  // 明示的にキャストすれば型付けできるが，型の目的 `評価不能にならないことを保証する` に反する場合が多い．
  def myIf2(t1: Bool, t2: Bool, t3: Nat): Bool = t1 match {
    case Bool.True  => t2
    case Bool.False => t3.asInstanceOf[Bool]
  }

  // pred 0 を 0 ではなく未定義としてしまうと，進行定理 (t: T and t -> t' => t': T) に反するので型の安全性が失われる． (演習 8.3.4)
  def pred(t1: Nat): Any = t1 match {
    case Nat(v: Int) if (v != 0) => Nat(v - 1)
    case _                       => None // undefined
  }
}
