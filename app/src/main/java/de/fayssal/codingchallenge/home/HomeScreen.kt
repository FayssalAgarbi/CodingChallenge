package de.fayssal.codingchallenge.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fayssal.codingchallenge.ui.theme.DarkCyan
import de.fayssal.codingchallenge.ui.theme.DarkGrey
import de.fayssal.codingchallenge.ui.theme.LightGrey


@Composable
fun AnimateLine(latestChanges: List<Int>) {

    // For some weird reason the animation only works properly if you tap on the canvas one or two
    // times when launching the app.
    // TODO Fix weird animation behavior
    var clicked by remember {
        mutableStateOf(true)
    }
    val animSize by animateDpAsState(
        targetValue = (if (clicked) 499.dp else 500.dp),
        animationSpec = TweenSpec(durationMillis = 1200000, delay = 600, easing = LinearEasing)
    )
    Canvas(
        modifier = Modifier
            .size(animSize, 400.dp)
            .clickable {
                clicked = !clicked
            },
        onDraw = {
            val path = Path()

            var currentXValue = 0f
            var currentYValue: Float

            val heightSteps = size.height / 32
            val widthSteps = size.width / 8


            for (level in latestChanges) {
                currentYValue = heightSteps * level
                path.lineTo(currentXValue, currentYValue)

                currentXValue += widthSteps
                path.lineTo(currentXValue, currentYValue)

            }

            /* As transitionDefinition is deprecated and everything else was too complicated to
             * deal with in this short timeframe, the "animation" consists of redrawing the graph
             * every x milliseconds (depending on the delay in the LevelDataSource).
             */
            drawPath(path = path, DarkCyan, style = Stroke(8f))
        })
}


@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    // This is essentially the background "canvas"
    Box(modifier = Modifier.fillMaxSize()) {


        // Lots of nested containers that make out the constraints of the final shape
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight()
                        .background(Color.White)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(text = "24", color = DarkGrey, modifier = Modifier.padding(4.dp))
                        Divider(color = LightGrey, thickness = 1.dp)

                        Text(text = "16", color = DarkGrey, modifier = Modifier.padding(4.dp))
                        Divider(color = LightGrey, thickness = 1.dp)

                        Text(text = "8", color = DarkGrey, modifier = Modifier.padding(4.dp))
                        Divider(color = LightGrey, thickness = 1.dp)

                        Text(text = "0", color = DarkGrey, modifier = Modifier.padding(4.dp))
                    }

                    val latestChanges by viewModel.latestLevelChanges.collectAsState()
                    AnimateLine(latestChanges = latestChanges)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Level",
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clickable {
                                    // Do something - or better yet, don't use a Text-Composable
                                    // in the first place
                                }
                                .fillMaxWidth(0.7f)

                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Blue)
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),

                            )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "13",
                                fontSize = 36.sp,
                            )
                            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                                Text(
                                    text = "10",
                                    fontSize = 14.sp,
                                )
                                Text(
                                    text = "17",
                                    fontSize = 14.sp,
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}