package lexicon.tokenParsers

import lexicon.enumerators.CharEnumerator
import lexicon.enumerators.moveNext
import lexicon.tokens.InvalidToken
import lexicon.tokens.Token
import lexicon.tokens.TokenTypes
import lexicon.tokens.chars.CharacterToken

class CharacterLiteralTokenParser : TokenParser {
    override fun parse(enumerator: CharEnumerator): Token? {

        if (!enumerator.moveNext())
            return null;

        if (enumerator.current != '\'')
            return null;

        if (!enumerator.moveNext())
            return InvalidToken(
                TokenTypes.CHARACTER_LITERAL,
                null,
                "Token is not completed"
            );

        var symbol = enumerator.current

        if (symbol == '\\'){
            val escapeCharacter = determineEscapeCharacter(enumerator)
            if (escapeCharacter != null)
                symbol = escapeCharacter;
        }

        val token = CharacterToken(symbol);

        if (!enumerator.moveNext() || enumerator.current != '\'')
            return InvalidToken(
                TokenTypes.CHARACTER_LITERAL,
                "'"+symbol,
                "Token is not completed"
            )

        return token;
    }
}