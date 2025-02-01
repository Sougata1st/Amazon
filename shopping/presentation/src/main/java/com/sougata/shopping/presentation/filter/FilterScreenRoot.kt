package com.sougata.shopping.presentation.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonRed
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilterScreenRoot(
    onBackClick: () -> Unit,
    viewModel: FilterViewModel = koinViewModel(),
    navigateToFilterResultScreen: (
        category: String,
        sortBy: String,
        sortDir: String,
        priceRange: ClosedFloatingPointRange<Float>
    ) -> Unit
) {
    FilterScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick,
        navigateToFilterResultScreen = navigateToFilterResultScreen
    )
}

@Composable
private fun FilterScreen(
    onBackClick: () -> Unit,
    state: FilterScreenState,
    onAction: (FilterScreenActions) -> Unit,
    navigateToFilterResultScreen: (
        category: String,
        sortBy: String,
        sortDir: String,
        priceRange: ClosedFloatingPointRange<Float>
    ) -> Unit
) {
    AmazonScaffold(
        topBar = {
            AmazonToolbar(
                showBackBtn = true,
                gradientColors = listOf(
                    AmazonGrey.copy(alpha = 0.2f),
                    AmazonGrey.copy(alpha = 0.2f)
                ),
                showSearchBar = false,
                onBackClick = {
                    onBackClick()
                }
            )
        },
        bottomBar = {
            AmazonActionButton(
                text = "Apply",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp),
                isLoading = false,
                isEnabled = true,
                containerColor = AmazonYellow,
                contentColor = AmazonDarkGrey,
                onClick = {
                    navigateToFilterResultScreen(
                        state.selectedCategory,
                        state.selectedSortBy,
                        state.selectedSortDirection,
                        state.priceRange
                    )
                    onAction(FilterScreenActions.ApplyClicked)
                }
            )
        }
    ) {
        var selectedTab by rememberSaveable { mutableIntStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .weight(.3f)
                        .padding(2.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Spacer(Modifier.height(2.dp))
                    ColumnItem(
                        selectedTab = selectedTab,
                        text = "Category",
                        position = 0,
                        onClick = { it -> selectedTab = it })
                    ColumnItem(
                        selectedTab = selectedTab,
                        text = "Sort By",
                        position = 1,
                        onClick = { it -> selectedTab = it })
                    ColumnItem(
                        selectedTab = selectedTab,
                        text = "Price Bound",
                        position = 2,
                        onClick = { it -> selectedTab = it })
                    ColumnItem(
                        selectedTab = selectedTab,
                        text = "Sort Direction",
                        position = 3,
                        onClick = { it -> selectedTab = it })
                }
                VerticalDivider(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Column(modifier = Modifier.weight(.7f)) {

                    when (selectedTab) {
                        0 -> {
                            SelectCategoryTabScreen(
                                state = state,
                                onClick = {
                                    onAction(FilterScreenActions.CategorySelectionTabClicked(it))
                                }
                            )
                        }

                        1 -> SelectSortByTabScreen(
                            state,
                            onClick = {
                                onAction(FilterScreenActions.SortBySelectionTabClicked(it))
                            }
                        )

                        2 -> {
                            PriceRangeSliderTab(state) {
                                onAction(FilterScreenActions.PriceBoundSelectionTabClicked(it))
                            }
                        }

                        3 -> {
                            SelectSortDirectionTabScreen(
                                state,
                                onClick = {
                                    onAction(FilterScreenActions.SortDirectionSelectionTabClicked(it))
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterScreenPreview() {
    AmazonTheme {
        Column {
            FilterScreen(
                state = FilterScreenState(),
                onAction = {},
                onBackClick = {},
                navigateToFilterResultScreen = { selectedCategory, sortBy, sortDir, priceRange ->
                }
            )
        }
    }
}

//category
//sortDir
//priceBound
//sortBy

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectSortByTabScreen(
    state: FilterScreenState,
    onClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        state.sortByList.forEachIndexed { index, item ->
            DataGridCell(
                modifier = Modifier.defaultMinSize(minWidth = 50.dp),
                text = item,
                onClick = {
                    onClick(it)
                },
                selectedCategory = state.selectedSortBy
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectCategoryTabScreen(
    state: FilterScreenState,
    onClick: (String) -> Unit,
) {

    FlowRow(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        state.categoryList.forEachIndexed { index, item ->
            DataGridCell(
                modifier = Modifier.defaultMinSize(minWidth = 50.dp),
                text = item,
                onClick = {
                    onClick(it)
                },
                selectedCategory = state.selectedCategory
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectSortDirectionTabScreen(
    state: FilterScreenState,
    onClick: (String) -> Unit,
) {
    FlowRow(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        state.sortDirectionList.forEachIndexed() { index, item ->
            DataGridCell(
                modifier = Modifier.defaultMinSize(minWidth = 50.dp),
                text = item,
                onClick = {
                    onClick(it)
                },
                selectedCategory = state.selectedSortDirection
            )
        }
    }
}

@Composable
private fun DataGridCell(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    selectedCategory: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(shape = RoundedCornerShape(2.dp), color = AmazonYellow.copy(alpha = 0.5f))
            .then(
                if (selectedCategory == text) {
                    Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(2.dp)
                    )
                } else {
                    Modifier
                }
            )
            .padding(3.dp)
            .clickable {
                onClick(text)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = AmazonBlack,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ColumnItem(selectedTab: Int, text: String, position: Int = 0, onClick: (Int) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                shape = RoundedCornerShape(16.dp),
                color = AmazonYellow.copy(alpha = 0.5f)
            )
            .then(
                if (selectedTab == position) {
                    Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(16.dp)
                    )
                } else {
                    Modifier
                }
            )
            .clickable {
                onClick(position)
            }
    )
    {
        Text(
            text = text,
            color = if (selectedTab == position) Color.Black else AmazonGrey.copy(alpha = 0.5f),
            style = TextStyle(fontWeight = if (selectedTab == position) FontWeight.Bold else FontWeight.Normal)
        )
    }
}


@Composable
fun PriceRangeSliderTab(
    state: FilterScreenState,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Price Range: ${state.priceRange.start.toInt()} - ${state.priceRange.endInclusive.toInt()}")
        RangeSlider(
            colors = SliderColors(
                thumbColor = AmazonRed,
                activeTrackColor = AmazonYellow,
                activeTickColor = AmazonBlack,
                inactiveTrackColor = AmazonGrey,
                inactiveTickColor = AmazonYellow,
                disabledThumbColor = AmazonYellow,
                disabledActiveTrackColor = AmazonYellow,
                disabledActiveTickColor = AmazonYellow,
                disabledInactiveTrackColor = AmazonYellow,
                disabledInactiveTickColor = AmazonYellow
            ),
            value = state.priceRange,
            onValueChange = { newRange ->
                onValueChange(newRange)
            },
            valueRange = 0f..10000f,
            steps = 100
        )
    }
}