package io.schiar.ruleofthree.view.shared.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import io.schiar.ruleofthree.viewmodel.viewdata.ResultViewData

@Composable
fun ResultView(displayValue: ResultViewData = ResultViewData(result = "", _result = 0.0)) {
    var dialogShow by remember { mutableStateOf(false) }

    if (dialogShow) {
        Dialog(onDismissRequest = { dialogShow = false }) {
            ResultDialogView(result = displayValue, onClosePressed = { dialogShow = false })
        }
    }

    CrossMultiplierItemView(
        displayValue = displayValue.result,
        onClick = { dialogShow = true },
        isResult = true
    )
}

@Preview
@Composable
fun ResultViewPreview() {
    ResultView(displayValue = ResultViewData(result = "0", _result = 0.0))
}

@Preview
@Composable
fun ResultViewFullPreview() {
    ResultView(displayValue = ResultViewData(result = "238947923", _result = 0.0))
}