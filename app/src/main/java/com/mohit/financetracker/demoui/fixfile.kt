//package com.mohit.financetracker.demoui
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.Person
//import androidx.compose.material3.Card
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.launch
//import java.util.Calendar
//
//// -------------------- DATA --------------------
//
//data class ChartData(val label: String, val value: Float)
//
//// -------------------- MAIN HOME --------------------
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NewHome() {
//
//    var selectedTab by remember { mutableStateOf("Expenses") }
//    val isIncome = selectedTab == "Income"
//    // 🔥 Chart State (HOISTED HERE)
//    var selectedFilter by remember { mutableStateOf("1M") }
//    var selectedIndex by remember { mutableIntStateOf(0) }
//
//    val listState = rememberLazyListState()
//    val scope = rememberCoroutineScope()
//
//    val data = remember(selectedFilter) {
//        generateChartData(selectedFilter)
//    }
//
//    val maxValue = data.maxOfOrNull { it.value } ?: 1f
//
//    // Auto select current date
//    LaunchedEffect(selectedFilter) {
//        selectedIndex = getDefaultSelectedIndex(selectedFilter, data.size)
//        scope.launch { listState.animateScrollToItem(selectedIndex) }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Hey John",
//                        fontSize = 30.sp,
//                        fontWeight = FontWeight.W600
//                    )
//                },
//                actions = {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            imageVector = Icons.Rounded.Person,
//                            contentDescription = null,
//                            tint = Color.Red
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = if (isIncome) "Total Income" else "Total Expenses",
//                color = Color.Black.copy(0.7f),
//                fontSize = 14.sp
//            )
//
//            Text(
//                text = if (isIncome) "$52,000.00" else "$87,143.00", // replace with real data later
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // SEGMENTED CONTROL
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(24.dp))
//                    .background(Color.Black.copy(0.1f))
//                    .padding(4.dp)
//            ) {
//                TabItem(
//                    text = "Income",
//                    isSelected = selectedTab == "Income",
//                    onClick = { selectedTab = "Income" },
//                    modifier = Modifier.weight(1f)
//                )
//
//                TabItem(
//                    text = "Expenses",
//                    isSelected = selectedTab == "Expenses",
//                    onClick = { selectedTab = "Expenses" },
//                    modifier = Modifier.weight(1f)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // -------------------- CHART --------------------
//            ChartSection(
//                selectedFilter = selectedFilter,
//                selectedIndex = selectedIndex,
//                data = data,
//                maxValue = maxValue,
//                listState = listState,
//                onFilterChange = { selectedFilter = it },
//                onBarClick = { selectedIndex = it }
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // -------------------- LIST --------------------
//            Card(
//                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Column(Modifier.padding(16.dp)) {
//
//                    Text(
//                        text = if (isIncome) "Top Income" else "Top Expenses",
//                        fontWeight = FontWeight.Bold
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    if (isIncome) {
//                        repeat(3) {
//                            ExpenseRowItem(
//                                title = "Salary",
//                                date = "May 29",
//                                amount = "+ $10,000.00",
//                                isIncome = true
//                            )
//                        }
//                    } else {
//                        repeat(3) {
//                            ExpenseRowItem(
//                                title = "MetLife",
//                                date = "May 29",
//                                amount = "- $499.00",
//                                isIncome = false
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// -------------------- CHART SECTION --------------------
//
//@Composable
//fun ChartSection(
//    selectedFilter: String,
//    selectedIndex: Int,
//    data: List<ChartData>,
//    maxValue: Float,
//    listState: LazyListState,
//    onFilterChange: (String) -> Unit,
//    onBarClick: (Int) -> Unit
//) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                Brush.verticalGradient(
//                    listOf(Color(0xFF2E1A5A), Color(0xFF1A1036))
//                ),
//                RoundedCornerShape(24.dp)
//            )
//            .padding(16.dp)
//    ) {
//
//        val filters = listOf("1D", "1W", "1M", "1Y")
//
//        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
//            filters.forEach { filter ->
//                val isSelected = filter == selectedFilter
//
//                Box(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(50))
//                        .background(
//                            if (isSelected) Color.White
//                            else Color.White.copy(0.1f)
//                        )
//                        .clickable { onFilterChange(filter) }
//                        .padding(horizontal = 14.dp, vertical = 6.dp)
//                ) {
//                    Text(
//                        filter,
//                        color = if (isSelected) Color.Black else Color.White,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        LazyRow(
//            state = listState,
//            modifier = Modifier.height(220.dp),
//            verticalAlignment = Alignment.Bottom
//        ) {
//
//            itemsIndexed(data) { index, item ->
//
//                val isSelected = index == selectedIndex
//                val heightRatio = item.value / maxValue
//
//                Column(
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp)
//                        .clickable { onBarClick(index) },
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
//                    if (isSelected) {
//                        Text(
//                            "$${item.value.toInt()}",
//                            color = Color.White,
//                            modifier = Modifier
//                                .background(Color(0xFF7B4DFF), RoundedCornerShape(12.dp))
//                                .padding(8.dp)
//                        )
//                        Spacer(Modifier.height(6.dp))
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .width(if (isSelected) 26.dp else 22.dp)
//                            .height((heightRatio * 150).dp)
//                            .background(
//                                if (isSelected) Color.White
//                                else Color.White.copy(0.3f),
//                                RoundedCornerShape(12.dp)
//                            )
//                    )
//
//                    Spacer(Modifier.height(6.dp))
//
//                    Text(
//                        item.label,
//                        color = if (isSelected) Color.White else Color.White.copy(0.6f)
//                    )
//                }
//            }
//        }
//    }
//}
//
//// -------------------- HELPERS --------------------
//
//fun getDefaultSelectedIndex(filter: String, size: Int): Int {
//    val cal = Calendar.getInstance()
//    return when (filter) {
//        "1D" -> (cal.get(Calendar.DAY_OF_WEEK) - 2).coerceIn(0, size - 1)
//        "1W" -> (cal.get(Calendar.WEEK_OF_MONTH) - 1).coerceIn(0, size - 1)
//        "1M" -> (cal.get(Calendar.DAY_OF_MONTH) - 1).coerceIn(0, size - 1)
//        "1Y" -> cal.get(Calendar.MONTH).coerceIn(0, size - 1)
//        else -> 0
//    }
//}
//
//fun generateChartData(filter: String): List<ChartData> {
//    return when (filter) {
//        "1D" -> listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
//        "1W" -> (1..5).map { "W$it" }
//        "1M" -> (1..30).map { "$it" }
//        "1Y" -> listOf(
//            "Jan",
//            "Feb",
//            "Mar",
//            "Apr",
//            "May",
//            "Jun",
//            "Jul",
//            "Aug",
//            "Sep",
//            "Oct",
//            "Nov",
//            "Dec"
//        )
//
//        else -> emptyList()
//    }.map {
//        ChartData(it, (50..300).random().toFloat())
//    }
//}
//
//// -------------------- UI ITEMS --------------------
//@Composable
//fun TabItem(
//    text: String,
//    isSelected: Boolean,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Box(
//        modifier = modifier
//            .clip(RoundedCornerShape(20.dp))
//            .background(if (isSelected) Color.White else Color.Transparent)
//            .clickable { onClick() }
//            .padding(vertical = 10.dp, horizontal = 16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = text,
//            color = if (isSelected) Color.Black else Color.Black.copy(0.5f)
//        )
//    }
//}
//
//@Composable
//fun ExpenseRowItem(
//    title: String,
//    date: String,
//    amount: String,
//    isIncome: Boolean
//) {
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .padding(vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Box(
//            modifier = Modifier
//                .size(40.dp)
//                .background(
//                    if (isIncome) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
//                    CircleShape
//                ),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = title.first().toString(),
//                fontWeight = FontWeight.Bold,
//                color = if (isIncome) Color(0xFF2E7D32) else Color(0xFFC62828)
//            )
//        }
//
//        Spacer(Modifier.width(12.dp))
//
//        Column(Modifier.weight(1f)) {
//            Text(title, fontWeight = FontWeight.SemiBold)
//            Text(date, fontSize = 12.sp, color = Color.Gray)
//        }
//
//        Text(
//            text = amount,
//            color = if (isIncome) Color(0xFF2E7D32) else Color.Red,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}
//// -------------------- PREVIEW --------------------
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewHome() {
//    NewHome()
//}