package br.senai.jandira.sp.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2()
                }
            }
        }
    }
}

@Composable
fun Greeting2() {

    val context = LocalContext.current

    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp)
    ) {
        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "E-mail"
                )
            }
        )
        OutlinedTextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Password"
                )
            }
        )
        Button(onClick = {
            authenticate(emailState, passwordState, context)
        }) {
            Text(text = "Entrar")
        }
    }
}

fun authenticate(email: String, password:String, context: Context) {
    //Obter instância do FireBaseAuth
    val auth = FirebaseAuth.getInstance()

    //Autenticação
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            Log.i("ds3m", it.isSuccessful.toString())
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
        .addOnFailureListener {error ->
            try {
                throw error
            }catch (e: Exception){
                Log.i("ds3m", "Usuário ou senha incorreto!")
            }
        }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyApplicationTheme {
        Greeting2()
    }
}