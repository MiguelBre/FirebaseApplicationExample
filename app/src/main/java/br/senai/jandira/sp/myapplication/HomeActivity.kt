package br.senai.jandira.sp.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("Home Page")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    val context = LocalContext.current

    Row(modifier = Modifier.padding(16.dp),horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "$name", fontSize = 40.sp)
        Text(
            text = "Sair",
            color = Color.Red,
        modifier = Modifier.clickable {
            val instance = FirebaseAuth.getInstance()
            instance.signOut()
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    MyApplicationTheme {
        Greeting3("Home Page")
    }
}