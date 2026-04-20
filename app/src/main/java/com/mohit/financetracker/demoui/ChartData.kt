package com.mohit.financetracker.demoui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ChartData1(
    val label: String,
    val value: Float
)

@Composable
fun ExpenseBarChart(
    data: List<ChartData1>,
    selectedIndex: Int = 4
) {
    val maxValue = data.maxOfOrNull { it.value } ?: 1f

    Column(modifier = Modifier.fillMaxWidth()) {

        // FILTER ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val filters = listOf("1D", "1W", "1M", "3M", "6M", "1Y")
            filters.forEach {
                Text(
                    text = it,
                    color = if (it == "1M") Color.White else Color.White.copy(alpha = 0.5f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BAR CHART
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            data.forEachIndexed { index, item ->

                val isSelected = index == selectedIndex
                val heightRatio = item.value / maxValue

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    if (isSelected) {
                        Text(
                            text = "$${item.value}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(
                                    Color(0xFF7B4DFF),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                    }

                    Box(
                        modifier = Modifier
                            .width(22.dp)
                            .height((heightRatio * 140).dp)
                            .background(
                                if (isSelected) Color.White else Color.White.copy(alpha = 0.3f),
                                RoundedCornerShape(6.dp)
                            )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item.label,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChart1() {

    val sampleData = listOf(
        ChartData1("Jan", 180f),
        ChartData1("Feb", 90f),
        ChartData1("Mar", 140f),
        ChartData1("Apr", 80f),
        ChartData1("May", 235f),
        ChartData1("Jun", 110f),
        ChartData1("Jul", 200f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFF4A2C91)
            )
            .padding(16.dp)
    ) {
        ExpenseBarChart(sampleData)
    }
}