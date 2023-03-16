package br.senai.jandira.sp.myapplication

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.jandira.sp.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

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
            accountCreate(emailState, passwordState)
        }) {
            Text(text = "Criar usuário")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting()
    }
}

fun accountCreate(email: String, password: String) {
    //Obter uma instancia do FireBaseAuth

    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            Log.i(
                "ds3m", it.user!!.uid
            )
        }
        .addOnFailureListener { error ->
            try {
                throw error
            } catch (e: FirebaseAuthUserCollisionException) {
                Log.i("ds3m", "Esse email já existe!")
                Log.i("ds3m", "${e.message}")
            } catch (e: FirebaseAuthWeakPasswordException) {
                Log.i("ds3m", "Senha fraca!")
                Log.i("ds3m", "${e.message}")
            } catch (e: FirebaseAuthInvalidUserException) {
                Log.i("ds3m", "Usuário inválido!")
                Log.i("ds3m", "${e.message}")
            } catch (e: Exception) {
                Log.i("ds3m", "Algo deu errado ;-;!")
                Log.i("ds3m", "${e.message}")
            }
        }
}