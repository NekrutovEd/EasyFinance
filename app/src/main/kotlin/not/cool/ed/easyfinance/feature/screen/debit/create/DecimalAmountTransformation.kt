package not.cool.ed.easyfinance.feature.screen.debit.create

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale

class DecimalAmountTransformation(
    locale: Locale = Locale.getDefault()
) : VisualTransformation {

    private val numberFormatter: NumberFormat = NumberFormat.getNumberInstance(locale)

    private val decimalSymbols = arrayOf('.', ',')

    fun filteredDecimalText(inputText: String): String {
        var text = inputText
        if (text.isEmpty()) return text

        val decimalSymbol = text.find { it in decimalSymbols } ?: return text

        if (text.startsWith(decimalSymbol)) text = "0$text"

        var firstDecimalSymbol = false
        var counter = 0
        text = text.takeWhile {
            when {
                it.isDigit() -> if (firstDecimalSymbol) counter++
                else -> {
                    if (!firstDecimalSymbol) firstDecimalSymbol = true
                    else return@takeWhile false
                }
            }
            counter < 3
        }
        return text
    }


    override fun filter(text: AnnotatedString): TransformedText {
        val transformation = reformat(text.text)

        return TransformedText(
            AnnotatedString(transformation.formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = transformation.originalToTransformed[offset]
                override fun transformedToOriginal(offset: Int): Int = transformation.transformedToOriginal[offset]
            },
        )
    }

    private fun reformat(original: String): Transformation {
        if (original.isEmpty()) return Transformation(original, listOf(0), listOf(0))

        var formatted = original

        val decimalSymbol = original.find { it in decimalSymbols }

        if (decimalSymbol == null) {
            formatted = numberFormatter.format(original.toBigInteger())
        } else {
            val parts = original.split(decimalSymbol)
            check(parts.size < 3) { "original text must have only one dot (use filteredDecimalText)" }

            if (original.length > 1 && parts.size == 1) {
                formatted = numberFormatter.format(parts[0].toBigInteger()) + decimalSymbol
            } else if (parts.size == 2) {
                val numberPart = numberFormatter.format(parts[0].toBigInteger())
                val decimalPart = parts[1]

                formatted = "$numberPart$decimalSymbol$decimalPart"
            }
        }

        Log.d("original_tag", original)

        val originalToTransformed = mutableListOf<Int>()
        val transformedToOriginal = mutableListOf<Int>()
        var specialCharsCount = 0

        formatted.forEachIndexed { index, char ->
            if (char.isDigit() || char == decimalSymbol) {
                originalToTransformed.add(index)
            } else {
                specialCharsCount++
            }
            transformedToOriginal.add(index - specialCharsCount)
        }
        originalToTransformed.add(originalToTransformed.maxOrNull()?.plus(1) ?: 0)
        transformedToOriginal.add(transformedToOriginal.maxOrNull()?.plus(1) ?: 0)

        return Transformation(formatted, originalToTransformed, transformedToOriginal)
    }

    private data class Transformation(
        val formatted: String,
        val originalToTransformed: List<Int>,
        val transformedToOriginal: List<Int>,
    )
}
