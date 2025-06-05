package qi.projeto.whatev.clickpark.controller

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import qi.projeto.whatev.clickpark.R
import qi.projeto.whatev.clickpark.databinding.ActivitySignupBinding
import qi.projeto.whatev.clickpark.model.dao.UserDAO
import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnCadastrar.setOnClickListener{
            val objUsuarioDTO = UsuarioDTO()

            objUsuarioDTO.cpf = binding.edtCpf.text.toString()
            objUsuarioDTO.email = binding.edtemail.text.toString()
            objUsuarioDTO.senha = binding.edtSenha.text.toString()

            val objUsusarioDAO :UserDAO= UserDAO(this)
            objUsusarioDAO.addUser(objUsuarioDTO)
            val allusers = objUsusarioDAO.getAllUsers()
            for (user in allusers){


                if (user.cpf == binding.edtCpf.text.toString() && user.senha == binding.edtSenha.text.toString()){

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user",user)
                    startActivity(intent)

                    finish()
                    break

                }



            }





        }
        binding.btnEntrar.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()


        }
    }
}