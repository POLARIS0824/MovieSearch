package com.example.movie.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// (String) -> Unit 表示接受 String ,并且没有返回值
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    onQueryClick: () -> Unit
) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .height(80.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 6.dp
//        )
    OutlinedTextField(
        value = query,  // 文本框里的文本内容
        onValueChange = onQueryChanged,  // 文本框输入内容时候的回调
        modifier = modifier,
        placeholder = { Text("Search movies...") },  // 没有输入内容的时候显示
        /*
        leadingIcon = {
            Image(painterResource(id = R.drawable.your_icon))
        },  // 输入框前面的图标
        trailingIcon = {
            Image(painterResource(id = R.drawable.your_icon))
        },  // 输入框后面的图标
        */
        singleLine = true,
        shape = RoundedCornerShape(4.dp),  // 较小的圆角，适合 TextField 内部
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = slideInHorizontally(),
                exit = slideOutHorizontally()
            ) {
                IconButton({
                    onQueryClick()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Search for movies"
                    )
                }
            }
        }
    )
//    }
}

@Preview
@Composable
fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SearchBar(modifier = Modifier, query, { query = it }) {

    }
}