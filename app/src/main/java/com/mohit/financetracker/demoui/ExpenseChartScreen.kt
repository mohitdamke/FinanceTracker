package com.mohit.financetracker.demoui

// -------------------- DATA --------------------
//
//data class ChartData(
//    val label: String,
//    val value: Float
//)
//
//// -------------------- MAIN SCREEN --------------------
//
//@Composable
//fun ExpenseChartScreen() {
//
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
//    // 🔥 Auto-select current time data
//    LaunchedEffect(selectedFilter) {
//        selectedIndex = getDefaultSelectedIndex(selectedFilter, data.size)
//
//        // Auto scroll to selected item
//        scope.launch {
//            listState.animateScrollToItem(selectedIndex)
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .background(
//                Brush.verticalGradient(
//                    listOf(Color(0xFF2E1A5A), Color(0xFF1A1036))
//                )
//            )
//            .padding(16.dp)
//    ) {
//
//        Surface(
//            shape = RoundedCornerShape(24.dp),
//            color = Color.White.copy(alpha = 0.05f),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//
//            Column(modifier = Modifier.padding(16.dp)) {
//
//                // -------------------- FILTERS --------------------
//                val filters = listOf("1D", "1W", "1M", "1Y")
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    filters.forEach { filter ->
//
//                        val isSelected = filter == selectedFilter
//
//                        Box(
//                            modifier = Modifier
//                                .clip(RoundedCornerShape(50))
//                                .background(
//                                    if (isSelected) Color.White
//                                    else Color.White.copy(alpha = 0.1f)
//                                )
//                                .clickable {
//                                    selectedFilter = filter
//                                }
//                                .padding(horizontal = 14.dp, vertical = 6.dp)
//                        ) {
//                            Text(
//                                text = filter,
//                                color = if (isSelected) Color.Black else Color.White,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // -------------------- CHART --------------------
//                LazyRow(
//                    state = listState,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(220.dp),
//                    verticalAlignment = Alignment.Bottom
//                ) {
//
//                    itemsIndexed(data) { index, item ->
//
//                        val isSelected = index == selectedIndex
//                        val heightRatio = item.value / maxValue
//
//                        Column(
//                            modifier = Modifier
//                                .padding(horizontal = 8.dp)
//                                .clickable {
//                                    selectedIndex = index
//                                },
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Bottom
//                        ) {
//
//                            // VALUE
//                            if (isSelected) {
//                                Text(
//                                    text = "$${item.value.toInt()}",
//                                    color = Color.White,
//                                    fontWeight = FontWeight.Bold,
//                                    modifier = Modifier
//                                        .background(
//                                            Color(0xFF7B4DFF),
//                                            RoundedCornerShape(12.dp)
//                                        )
//                                        .padding(horizontal = 10.dp, vertical = 5.dp)
//                                )
//
//                                Spacer(modifier = Modifier.height(8.dp))
//                            }
//
//                            // BAR
//                            Box(
//                                modifier = Modifier
//                                    .width(if (isSelected) 26.dp else 22.dp)
//                                    .height((heightRatio * 150).dp)
//                                    .background(
//                                        brush = if (isSelected) {
//                                            Brush.verticalGradient(
//                                                listOf(
//                                                    Color(0xFF9C7BFF),
//                                                    Color(0xFF6A3BFF)
//                                                )
//                                            )
//                                        } else {
//                                            Brush.verticalGradient(
//                                                listOf(
//                                                    Color.White.copy(0.3f),
//                                                    Color.White.copy(0.1f)
//                                                )
//                                            )
//                                        },
//                                        shape = RoundedCornerShape(12.dp)
//                                    )
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            // LABEL
//                            Text(
//                                text = item.label,
//                                color = if (isSelected)
//                                    Color.White
//                                else
//                                    Color.White.copy(alpha = 0.6f),
//                                fontWeight = if (isSelected)
//                                    FontWeight.Bold
//                                else
//                                    FontWeight.Normal
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// -------------------- AUTO SELECTION LOGIC --------------------
//
//fun getDefaultSelectedIndex(filter: String, size: Int): Int {
//    val cal = Calendar.getInstance()
//
//    return when (filter) {
//
//        "1D" -> {
//            val day = cal.get(Calendar.DAY_OF_WEEK)
//            val index = when (day) {
//                Calendar.MONDAY -> 0
//                Calendar.TUESDAY -> 1
//                Calendar.WEDNESDAY -> 2
//                Calendar.THURSDAY -> 3
//                Calendar.FRIDAY -> 4
//                Calendar.SATURDAY -> 5
//                else -> 0
//            }
//            index.coerceIn(0, size - 1)
//        }
//
//        "1W" -> {
//            (cal.get(Calendar.WEEK_OF_MONTH) - 1).coerceIn(0, size - 1)
//        }
//
//        "1M" -> {
//            (cal.get(Calendar.DAY_OF_MONTH) - 1).coerceIn(0, size - 1)
//        }
//
//        "1Y" -> {
//            cal.get(Calendar.MONTH).coerceIn(0, size - 1)
//        }
//
//        else -> 0
//    }
//}
//
//// -------------------- DATA --------------------
//
//fun generateChartData(filter: String): List<ChartData> {
//    return when (filter) {
//
//        "1D" -> listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat").map {
//            ChartData(it, (50..200).random().toFloat())
//        }
//
//        "1W" -> (1..5).map {
//            ChartData("W$it", (100..300).random().toFloat())
//        }
//
//        "1M" -> (1..30).map {
//            ChartData("$it", (50..250).random().toFloat())
//        }
//
//        "1Y" -> listOf(
//            "Jan","Feb","Mar","Apr","May","Jun",
//            "Jul","Aug","Sep","Oct","Nov","Dec"
//        ).map {
//            ChartData(it, (100..500).random().toFloat())
//        }
//
//        else -> emptyList()
//    }
//}
//
//// -------------------- PREVIEW --------------------
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewChart() {
//    ExpenseChartScreen()
//}