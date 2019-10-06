package ch07

sealed trait Term
sealed trait Info
case class TmVar(info: Info, index: Int, contextLength: Int) extends Term
case class TmAbs(info: Info, v: String, t: Term) extends Term
case class TmApp(info: Info, t1: Term, t2: Term) extends Term

sealed trait Binding
case object NameBind extends Binding

case class Context(l: List[(String, Binding)]) {
  def pickFreshName(s: String): (Context, String) = {
    if (isNameBound(s)) {
      pickFreshName(s + "'")
    } else {
//      Context((s, NameBind) :: l)
      ???
    }
  }

  def index2Name(i: Int): String = get(i)._1

  private def isNameBound(x: String): Boolean = l.exists(_._1 == x)

  @throws
  private def get(i: Int): (String, Binding) = l(i)
}

object Eval {
  def rec(printtm: Info, ctx: Context, t: Term): String = t match {
    case TmAbs(fi, x, t1) => {
      val (ctx1, x1) = ctx.pickFreshName(x)
      s"(lambda $x1. ${rec(fi, ctx, t1)})"
    }
    case TmApp(fi, t1, t2) => s"(${rec(fi, ctx, t1)} ${rec(fi, ctx, t2)})"
    case TmVar(fi, x, n) => {
      if (ctx.l.length == n) { ctx.index2Name(x) } else { "[bad index]" }
    }
  }
}

object Util {
  def isVal(ctx: Context, t: Term): Boolean = t match {
    case TmAbs(_, _, _) => true
    case _              => false
  }
}
