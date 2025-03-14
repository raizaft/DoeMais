package ifpb.edu.br.pdm.doemais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import ifpb.edu.br.pdm.doemais.ui.telas.TelaCadastro
import ifpb.edu.br.pdm.doemais.ui.telas.TelaLogin
import ifpb.edu.br.pdm.doemais.ui.telas.TelaPerfil
import ifpb.edu.br.pdm.doemais.ui.telas.TelaPrincipal
import ifpb.edu.br.pdm.doemais.ui.theme.DoemaisTheme
import ifpb.edu.br.pdm.doemais.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            DoemaisTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            TelaLogin(modifier = Modifier.padding(innerPadding), navController = navController, onSigninClick = {
                                navController.navigate("principal")
                            })
                        }
                        composable("cadastro") {
                            TelaCadastro(navController = navController)
                        }
                        composable("principal/{email}") { backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            TelaPrincipal(navController = navController, email = email)
                        }
                        composable("perfil/{email}") { backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            TelaPerfil(navController = navController, email = email)
                        }
                    }
                }
            }
        }
    }
}