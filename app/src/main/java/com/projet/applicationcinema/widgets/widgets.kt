package com.projet.applicationcinema.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.projet.applicationcinema.model.Movie

@Composable
fun MovieRow(
    movieName: Movie,
    onItemClick :(String) -> Unit = {}
){
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movieName.id)
            },
        elevation = 6.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape= CircleShape,
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movieName.images[0],
                        builder = {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = "Movie poster")
             }

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = movieName.title,
                    style = MaterialTheme.typography.h6)

                Text(
                    text = movieName.director,
                    style = MaterialTheme.typography.caption)

                Text(
                    text = movieName.year,
                    style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                            fontSize = 13.sp)){
                                append("Plot : ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)){
                                append(movieName.plot)
                            }
                        })
                        Divider( modifier = Modifier.padding(3.dp))
                        Text(
                            text = "Director : ${movieName.director}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Actors : ${movieName.actors}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Rating : ${movieName.rating}",
                            style = MaterialTheme.typography.caption
                        )

                    }
                }


                Icon(
                    imageVector = if(expanded) Icons.Default.KeyboardArrowUp
                                    else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Developper description",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }
}