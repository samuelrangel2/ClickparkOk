package qi.projeto.whatev.clickpark.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import qi.projeto.whatev.clickpark.RecargaFragment
import qi.projeto.whatev.clickpark.controller.placeholder.HomeFragment
import qi.projeto.whatev.clickpark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var creditos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val homeFragment = HomeFragment()
        val recargaFragment = RecargaFragment()
        val adicionarFragment = AdicionarFragment()
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