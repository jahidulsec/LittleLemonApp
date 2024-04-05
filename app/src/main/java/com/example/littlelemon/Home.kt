package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, database: AppDatabase) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            Spacer(modifier = Modifier.width(40.dp))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(140.dp, 40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "User",
                modifier = Modifier
                    .height(40.dp)
                    .clip(shape = RoundedCornerShape(50.dp))
                    .clickable {
                        navController.navigate(Profile.route)
                    }
            )
        }

        var search by remember {
            mutableStateOf("")
        }

        Column (
            modifier = Modifier
                .background(color = LittleLemonColor.primary)
                .padding(20.dp)
        ){
            Text(
                text = stringResource(id = R.string.title),
                style = Type.title,
                modifier = Modifier.height(60.dp)
            )
            Text(
                text= stringResource(id = R.string.sub_title),
                style = Type.subTitle
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                ){

                    Text(
                        text= stringResource(id = R.string.desc),
                        style = Type.desc
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(16.dp))

                )
            }
            TextField(
                leadingIcon = {Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")},
                colors = textFieldColors(
                    focusedIndicatorColor = Color(0x00ffffff),
                    containerColor = LittleLemonColor.gray
                ),
                value = search,
                onValueChange = {search = it},
                shape = RoundedCornerShape(10.dp),
                textStyle = Type.paragraph,
                placeholder = { Text(text = "Enter search phrase")},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        var selected by remember {
            mutableStateOf("")
        }

        Column (
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .drawBehind {
                    val borderSize = 1.dp.toPx()
                    drawLine(
                        color = LittleLemonColor.gray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
        ) {
            Text(
                text = "ORDER FOR DELIVERY!",
                style = Type.sectionTitle,
                modifier = Modifier.padding(bottom=10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .padding(bottom = 10.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                ButtonCat("Starters", selected, onSelected = {selected = it})
                ButtonCat("Mains", selected, onSelected = {selected = it})
                ButtonCat("Desserts", selected, onSelected = {selected = it})
                ButtonCat("Slides", selected, onSelected = {selected = it})
            }
        }

        MenuItems(database, search, selected)
    }
}





@Composable
fun ButtonCat(title:String, selected:String, onSelected: (String) -> Unit) {

    val backgroundColor = if (title == selected) LittleLemonColor.primary else LittleLemonColor.gray
    val color = if (title == selected) LittleLemonColor.gray else LittleLemonColor.dark

    Text(
        text = title,
        style = Type.sectionCat,
        color = color,
        modifier = Modifier
            .padding(end = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(10.dp)
            .clickable {
                onSelected(title)
            }
    )
}


@Composable
fun MenuItems(database: AppDatabase, search: String, selected: String) {
    val items by database.menuItemDao().getAll().observeAsState(emptyList())

    val menus: List<MenuItemRoom> = if (search.isNotEmpty()) {
        items.filter { it.title.contains(search, ignoreCase = true) }
    } else if (selected.isNotEmpty()){
        items.filter { it.category == selected.lowercase() }
    } else {
        items
    }

    Column (
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        menus.map { item ->
            Card(item)
        }
    }
}




@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Card(item: MenuItemRoom) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = LittleLemonColor.gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(bottom = 10.dp)

        ) {
            Text(
                text = item.title,
                style = Type.cardTitle,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = item.description,
                style = Type.paragraph,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = "$${item.price}", style = Type.price)
        }
//        Image(
//            painter = painterResource(id = R.drawable.greek_salad),
//            contentDescription = "Greek Salad",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.size(81.dp)
//        )
        GlideImage(
            model = item.image,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(81.dp)
        )
    }
}
