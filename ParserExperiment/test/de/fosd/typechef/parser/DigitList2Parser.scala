package de.fosd.typechef.parser
import scala.util.parsing.input.Reader
import de.fosd.typechef.featureexpr.FeatureExpr

case class DigitList2(list: List[Opt[AST]]) extends AST

class DigitList2Parser extends MultiFeatureParser {

    def parse(tokens: List[Token]): ParseResult[AST] = digitList(new TokenReader(tokens, 0), FeatureExpr.base).forceJoin[AST](Alt.join)

    def digitList: MultiParser[AST] =
        (t("(") ~ digits ~ t(")")) ^^! { case (~(~(b1, e), b2)) => e }

    def digits: MultiParser[AST] =
        rep(digitList | digit) ^^! { //List(Opt(AST)) -> DigitList[List[Opt[Lit]]
            DigitList2(_)
        } 

    def t(text: String) = textToken(text)

    def digit: MultiParser[AST] = token("digit", ((x) => x.t == "1" | x.t == "2" | x.t == "3" | x.t == "4" | x.t == "5")) ^^ { t => Lit(t.text.toInt) }

}