package qi.projeto.whatev.clickpark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import qi.projeto.whatev.clickpark.databinding.FragmentHomeBinding
import qi.projeto.whatev.clickpark.databinding.FragmentRecargaBinding
import qi.projeto.whatev.clickpark.model.dao.UserDAO
import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO


class RecargaFragment(private val userDAO: UserDAO,
                      private var usuarioDTO: UsuarioDTO
) : Fragment() {
    lateinit var binding: FragmentRecargaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRecargaBinding.inflate(layoutInflater)
        binding.btnAdicionarFundos.setOnClickListener {
            if (binding.edtValor.text.toString().toIntOrNull() != null){
                usuarioDTO.creditos += binding.edtValor.text.toString().toInt()*100
              userDAO.updateUser(usuarioDTO)

        }
        }
        binding.btnAdicionarMetodoDePagamento.setOnClickListener {
            Toast.makeText(requireContext(), "em construção \n fundos ilimitados", Toast.LENGTH_SHORT).show()

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }
}