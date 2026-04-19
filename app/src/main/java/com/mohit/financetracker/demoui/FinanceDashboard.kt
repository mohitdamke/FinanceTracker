package com.mohit.financetracker.demoui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinanceDashboard() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF7B4DFF),
                        Color(0xFF5E35B1),
                        Color(0xFF311B92)
                    )
                )
            )
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // HEADER
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Statistics",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .background(
                            Color.White.copy(alpha = 0.2f),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(4.dp)
                ) {

                    ToggleButton("Income", false)
                    ToggleButton("Expenses", true)
                }
            }

            // SUMMARY
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                Text(
                    text = "Total expenses",
                    color = Color.White.copy(alpha = 0.7f)
                )

                Text(
                    text = "$1,643.10",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    listOf("1D", "1W", "1M", "3M", "6M", "1Y").forEach {
                        Text(
                            text = it,
                            color = if (it == "1M") Color.White else Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.padding(end = 12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // BAR CHART
            val data = listOf(100, 60, 80, 50, 120, 70, 90)
            val max = data.maxOrNull() ?: 1
            val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {

                data.forEachIndexed { index, value ->

                    val isSelected = index == 4

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        if (isSelected) {
                            Text(
                                "$235.32",
                                color = Color.White,
                                modifier = Modifier
                                    .background(Color(0xFF7B4DFF), RoundedCornerShape(8.dp))
                                    .padding(6.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height((value / max.toFloat() * 140).dp)
                                .background(
                                    if (isSelected) Color.White else Color.White.copy(alpha = 0.3f),
                                    RoundedCornerShape(6.dp)
                                )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = months[index],
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // BOTTOM CARD
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier.fillMaxSize()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Top Expenses",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ExpenseItem("MetLife", "- $499.00")
                    ExpenseItem("Bank Transfer", "- $423.52")
                    ExpenseItem("Netflix", "- $39.99")
                }
            }
        }
    }
}

@Composable
fun ToggleButton(text: String, selected: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) Color.White else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (selected) Color.Black else Color.White
        )
    }
}

@Composable
fun ExpenseItem(title: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = amount, color = Color.Red)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFinanceDashboard() {
    FinanceDashboard()
}