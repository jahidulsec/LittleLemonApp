package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Home() {
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
            )
        }

        Hero()
        MenuCat()
        MenuItems()
    }
}


@Composable
fun Hero() {

    var search by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = LittleLemonColor.primary)
            .padding(20.dp)
    ){
        Text(
            text = stringResource(id = R.string.title),
            style = Type.title,
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
                .padding(bottom = 30.dp)
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
                modifier = Modifier.size(140.dp)

            )
        }
        TextField(
            leadingIcon = {Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")},
            value = search,
            onValueChange = {search = it},
            shape = RoundedCornerShape(10.dp),
            textStyle = Type.paragraph,
            placeholder = { Text(text = "Enter search phrase")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)

        )
    }
}


@Composable
fun MenuCat() {
    Column (
        modifier = Modifier
            .padding(20.dp)
            .border(1.dp, LittleLemonColor.dark)
    ) {
        Text(
            text = "ORDER FOR DELIVERY!",
            style = Type.sectionTitle,
            modifier = Modifier.padding(bottom=10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
        ) {
            ButtonCat("Starters")
            ButtonCat("Mains")
            ButtonCat("Desserts")
            ButtonCat("Slides")
            ButtonCat("Starters")
            ButtonCat("Starters")
        }
    }
}


@Composable
fun ButtonCat(title:String) {
    Text(
        text = title,
        style = Type.sectionCat,
        modifier = Modifier
            .padding(end = 20.dp)
            .background(LittleLemonColor.gray)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))

    )
}


@Composable
fun MenuItems() {
    Column (
        modifier = Modifier.padding(20.dp)
    ) {
        Card()
        Card()
        Card()
        Card()
    }
}


@Composable
fun Card() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(0.75f)
        ) {
            Text(
                text = "Greek Salad",
                style = Type.cardTitle,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago",
                style = Type.paragraph,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = "$12.99", style = Type.price)
        }
        Image(
            painter = painterResource(id = R.drawable.greek_salad),
            contentDescription = "Greek Salad",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.size(81.dp)
        )
    }
}
