package lexicon.tokens.keywords

import lexicon.tokenParsers.IdentifierOrKeywordTokenParser

@JvmInline
value class KeywordNames (
    val flag: Int
) {
    companion object{
        val VAR = KeywordNames(0b10)
        val VAL = KeywordNames(0b100)
        val IF = KeywordNames(0b1000)
    }

    operator fun contains(names: KeywordNames): Boolean {
        return hasFlag(names);
    }

    infix fun and(names: KeywordNames) : KeywordNames {
        return KeywordNames(this.flag and names.flag)
    }

    infix fun or(names: KeywordNames) : KeywordNames{
        return KeywordNames(this.flag or names.flag)
    }

    fun hasFlag(names: KeywordNames): Boolean {
        return (this and names).flag != 0;
    }

    override fun toString(): String {
        val keywords = IdentifierOrKeywordTokenParser.keywords;
        val selectedKeywords = keywords.filterValues {
                value -> value.hasFlag(this)
        }

        return selectedKeywords.keys.joinToString(" | ")
    }
}