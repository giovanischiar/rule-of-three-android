package io.schiar.ruleofthree.view.shared.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import io.schiar.ruleofthree.R

@Composable
fun CharacterButton(
    modifier: Modifier = Modifier,
    name: String = "",
    onClick: (value: String) -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = { onClick(name) }
    ) {
        when(name) {
            "clear" -> {
                Icon(
                    modifier = Modifier.padding(5.dp),
                    painter = painterResource(R.drawable.baseline_delete_forever_24),
                    contentDescription = name,
                    tint = colorResource(id = R.color.hashColor)
                )
            }

            "enter" -> {
                Icon(
                    modifier = Modifier.padding(5.dp),
                    painter = painterResource(android.R.drawable.ic_menu_send),
                    contentDescription = name,
                    tint = colorResource(id = R.color.hashColor)

                )
            }

            "backspace" -> {
                Icon(
                    modifier = Modifier.padding(5.dp),
                    painter = painterResource(android.R.drawable.ic_input_delete),
                    contentDescription = name,
                    tint = colorResource(id = R.color.hashColor)
                )
            }

            else -> {
                Text(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    text = name,
                    color = colorResource(id = R.color.hashColor)
                )
            }
        }
    }
}

@Preview
@Composable
fun CharacterButtonDigitPreview() {
    CharacterButton(name = "0")
}

@Preview
@Composable
fun CharacterButtonDecimalPointPreview() {
    CharacterButton(name = ".")
}

@Preview
@Composable
fun CharacterButtonClearPreview() {
    CharacterButton(name = "clear")
}

@Preview
@Composable
fun CharacterButtonBackspacePreview() {
    CharacterButton(name = "backspace")
}

@Preview
@Composable
fun CharacterButtonEnterPreview() {
    CharacterButton(name = "enter")
}