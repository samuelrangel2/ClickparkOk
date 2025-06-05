package qi.projeto.whatev.clickpark.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import qi.projeto.whatev.clickpark.R
import qi.projeto.whatev.clickpark.model.dao.UserDAO
import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO


class AdicionarFragment(private val userDAO: UserDAO,
                        private var usuarioDTO: UsuarioDTO
) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adicionar, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdicionarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdicionarFragment(
                userDAO = TODO(),
                usuarioDTO = TODO()
            ).apply {
                arguments = Bundle().apply {

                }
            }
    }
}