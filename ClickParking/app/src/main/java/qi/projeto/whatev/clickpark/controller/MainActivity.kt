package qi.projeto.whatev.clickpark.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import qi.projeto.whatev.clickpark.RecargaFragment
import qi.projeto.whatev.clickpark.controller.placeholder.HomeFragment
import qi.projeto.whatev.clickpark.databinding.ActivityMainBinding
import qi.projeto.whatev.clickpark.model.dao.UserDAO
import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userDAO: UserDAO
    lateinit var usuarioDTO :UsuarioDTO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        usuarioDTO = intent.getSerializableExtra("user") as UsuarioDTO
        userDAO = UserDAO(this)
        val homeFragment = HomeFragment(userDAO,usuarioDTO)
        val recargaFragment = RecargaFragment(userDAO,usuarioDTO)
        val adicionarFragment = AdicionarFragment(userDAO,usuarioDTO)




        supportFragmentManager.beginTransaction().apply {
            replace(binding.flfragmentholder.id,homeFragment)
            commit()
        }

        binding.btnHome.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.flfragmentholder.id,homeFragment)
                commit()
            }
        }
        binding.btnRecarga.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.flfragmentholder.id,recargaFragment)
                commit()
            }
        }
        binding.btnAdicionar.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.flfragmentholder.id,adicionarFragment)
                commit()
            }
        }




    }













}