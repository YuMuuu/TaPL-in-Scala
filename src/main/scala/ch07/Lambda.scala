package ch07

sealed trait Term
case class TmVar(i: Int) extends Term
case class TmAbs(t: Term) extends Term
case class TmApp(t1: Term, t2: Term) extends  Term

