package com.example.filmindo.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.filmindo.R

@Composable
fun FilmContent(
    title: String,
    description: String,
    image: Int,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(DefaultShadowColor.alpha.dp)
            .fillMaxWidth()
            .clickable {

        }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .width(150.dp)
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Column (
            modifier = Modifier
                .align(Alignment.Top)
                .padding(8.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp
            )

            Text(
                text = description,
                maxLines = 7,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun FilmContentPreview() {
    FilmContent(
        title = "Dilan",
        description = "Masa-masa baru jadian memang selalu menjadi masa paling indah dalam menjalin sebuah hubungan. Namun di tengah hubungan yang sedang manis-manisnya muncul satu masalah yang mengharuskan pasangan serasi itu untuk mencari solusi dari masalah tersebut.\n" +
                "\n" +
                "Pada Dilan 1991, masalah mulai muncul saat Dilan dipercaya untuk memimpin geng motor. Tentunya hal ini mendapatkan larangan keras dari Milea dengan alasan keselamatan Dilan.\n" +
                "\n" +
                "Kemudian cerita berlanjut pada kondisi dimana Milea mendapatkan kabar bahwa Dilan sedang dikeroyok oleh sekelompok orang tak dikenal. Atas kejadian itu Milea sadar bahwa masalah tersebut akan berujung panjang.\n" +
                "\n" +
                "Milea sempat mengancam untuk memutuskan Dilan jika ia tidak segera keluar dari geng motor itu, dan benar saja apa yang ditakutkan Milea terbukti.\n" +
                "\n" +
                "Dilan dan geng motornya sudah menyiapkan rencana untuk balas dendam. Terlebih Dilan sudah mengetahui siapa dalang dibalik pengeroyokannya tersebut. Berangkat dari rasa cinta dan khawatir yang Milea rasakan, ia memberanikan diri untuk bertemu dengan Dilan ditemani dengan sang sepupu Yugo (Jerome Kurnia).\n" +
                "\n" +
                "Rupanya kekhawatiran Milea terbukti, Dilan benar-benar melancarkan balas dendam sesuai dengan yang ia rencanakan dan hasilnya adalah ia ditangkap oleh polisi.",
        image = R.drawable.dilan
    )
}