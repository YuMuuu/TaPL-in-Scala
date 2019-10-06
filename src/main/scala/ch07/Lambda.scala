package ch07

sealed trait Term
sealed trait Info
case class TmVar(info: Info, i: Int) extends Term
case class TmAbs(info: Info, t: Term) extends Term
case class TmApp(info: Info, t1: Term, t2: Term) extends Term
