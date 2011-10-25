package android.parser

import util.parsing.combinator.syntactical.StandardTokenParsers

object AndroidParser extends StandardTokenParsers {
  lexical.reserved +=
    ("install", "on", "all", "emulators", "devices")

  lexical.delimiters +=("(", ")", ",")

  lazy val actions =
    ("install" | "run" | "instrument" | "uninstall") ~> devices

  lazy val devices =
    "on" ~> ("all" | "emulators" | "devices")

  lazy val buy_sell =
    "to" ~> ("buy" | "sell")

  lazy val security_spec =
    numericLit ~ (ident <~ "shares")

  lazy val price_spec =
    "at" ~> (min_max ?) ~ numericLit

  lazy val min_max =
    "min" | "max"

  lazy val account_spec =
    "for" ~> "account" ~> stringLit


  def parse(s: String) = {
    val tokens = new lexical.Scanner(s)
    phrase(actions)(tokens)
  }
}