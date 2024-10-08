package io.schiar.ruleofthree.view.crossmultiplierscreator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.schiar.ruleofthree.R
import io.schiar.ruleofthree.view.crossmultiplierscreator.uistate.AreTherePastCrossMultipliersUiState
import io.schiar.ruleofthree.view.crossmultiplierscreator.uistate.CurrentCrossMultiplierUiState
import io.schiar.ruleofthree.view.shared.component.CrossMultiplierView
import io.schiar.ruleofthree.view.shared.component.TouchableIcon
import io.schiar.ruleofthree.viewmodel.viewdata.CrossMultiplierViewData

@Composable
fun CrossMultipliersCreatorScreen(
    currentCrossMultiplierUiState: CurrentCrossMultiplierUiState,
    areTherePastCrossMultipliersUiState: AreTherePastCrossMultipliersUiState =
        AreTherePastCrossMultipliersUiState.AreThereCrossMultipliersLoaded(
            areTherePastCrossMultipliers = false
        ),
    pushCharacterToInputAt: (position: Pair<Int, Int>, character: String) -> Unit = {_,_->},
    popCharacterOfInputAt: (position: Pair<Int, Int>) -> Unit = {},
    clearInputOn: (position: Pair<Int, Int>) -> Unit = {},
    changeTheUnknownPositionTo: (position: Pair<Int, Int>) -> Unit = {},
    onSubmitPressed: () -> Unit = {},
    clearAllInputs: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {}
) {
    val iconSize = 30.dp
    val crossMultiplier = when (currentCrossMultiplierUiState) {
        is CurrentCrossMultiplierUiState.Loading -> CrossMultiplierViewData()
        is CurrentCrossMultiplierUiState.CurrentCrossMultiplierLoaded -> {
            currentCrossMultiplierUiState.crossMultiplier
        }
    }

    Box {
        Row {
            Column(modifier = Modifier.weight(1f)) {
                CrossMultiplierView(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = iconSize, top = iconSize),
                    crossMultiplier = crossMultiplier,
                    onCharacterPressedAt = pushCharacterToInputAt,
                    onBackspacePressedAt = popCharacterOfInputAt,
                    onClearPressedAt = clearInputOn,
                    onLongPressedAt = changeTheUnknownPositionTo,
                    onSubmitPressed = onSubmitPressed
                )

                TouchableIcon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(iconSize)
                        .padding(start = iconSize),
                    onClick = clearAllInputs,
                    iconDrawableID = R.drawable.baseline_delete_forever_24,
                    contentDescription = "clear all inputs",
                    colorID = R.color.hashColor,
                    visible = crossMultiplier.isNotEmpty()
                )
            }

            when (areTherePastCrossMultipliersUiState) {
                is AreTherePastCrossMultipliersUiState.Loading -> CircularProgressIndicator()
                is AreTherePastCrossMultipliersUiState.AreThereCrossMultipliersLoaded -> {
                    TouchableIcon(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(iconSize),
                        onClick = onNavigateToHistory,
                        iconDrawableID = R.drawable.baseline_history_24,
                        contentDescription = "history",
                        colorID = R.color.hashColor,
                        visible = areTherePastCrossMultipliersUiState.areTherePastCrossMultipliers
                    )
                }
            }
        }

        if (currentCrossMultiplierUiState is CurrentCrossMultiplierUiState.Loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun CrossMultiplicationScreenEmptyPreview() {
    CrossMultipliersCreatorScreen(
        currentCrossMultiplierUiState = CurrentCrossMultiplierUiState.CurrentCrossMultiplierLoaded(
            CrossMultiplierViewData()
        )
    )
}

@Preview
@Composable
fun CurrentCrossMultiplierScreenWithNumbersPreview() {
    CrossMultipliersCreatorScreen(
        currentCrossMultiplierUiState = CurrentCrossMultiplierUiState.CurrentCrossMultiplierLoaded(
            CrossMultiplierViewData(
                valueAt00 ="10", valueAt01= "345",
                valueAt11 ="15.3",
                unknownPosition = Pair(0, 1)
            )
        )
    )
}

@Preview
@Composable
fun CurrentCrossMultiplierScreenWithNumbersAndHistoryPreview() {
    CrossMultipliersCreatorScreen(
        currentCrossMultiplierUiState = CurrentCrossMultiplierUiState.CurrentCrossMultiplierLoaded(
            CrossMultiplierViewData(
                valueAt00 = "45", valueAt01 = "160",
                valueAt10 = "62", valueAt11 = "${(160 * 62)/45}"
            ),
        ),
        areTherePastCrossMultipliersUiState = AreTherePastCrossMultipliersUiState
            .AreThereCrossMultipliersLoaded(areTherePastCrossMultipliers = true)
    )
}