package qi.projeto.whatev.clickpark.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import qi.projeto.whatev.clickpark.R
import qi.projeto.whatev.clickpark.databinding.ActivityLoginBinding
import qi.projeto.whatev.clickpark.model.dao.UserDAO

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnEntrar.setOnClickListener{

            val objUserDAO = UserDAO(this)
            val allusers = objUserDAO.getAllUsers()
            var found = false
            for (user in allusers){


                if (user.cpf == binding.edtCpf.text.toString() && user.senha == binding.edtSenha.text.toString()){

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user",user)
                    startActivity(intent)
                    found = true
                    finish()
                    break

                }



            }
            if (!found){
                Toast.makeText(this, "cpf ou senha invalidos", Toast.LENGTH_SHORT).show()


            }







        }
        binding.btnCadastrar.setOnClickListener{

            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()


        }
    }
}