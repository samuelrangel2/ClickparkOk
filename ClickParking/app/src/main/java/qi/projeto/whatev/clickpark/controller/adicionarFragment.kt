package qi.projeto.whatev.clickpark.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import qi.projeto.whatev.clickpark.R
import qi.projeto.whatev.clickpark.databinding.FragmentAdicionarBinding
import qi.projeto.whatev.clickpark.model.dao.CarDAO
import qi.projeto.whatev.clickpark.model.dao.UserDAO
import qi.projeto.whatev.clickpark.model.dto.CarDTO
import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO


class AdicionarFragment(private val userDAO: UserDAO,
                        private var usuarioDTO: UsuarioDTO
) : Fragment() {
    lateinit var binding: FragmentAdicionarBinding
    var carrosLista: MutableList<CarDTO> = arrayListOf<CarDTO>()
    lateinit var adapter : ArrayAdapter<CarDTO>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAdicionarBinding.inflate(layoutInflater)





        adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,carrosLista)

        binding.listveiculo.adapter = adapter

        updateCarros()
        binding.btnAdicionarcarro.setOnClickListener {
            val carro = CarDTO()
            carro.plate = this.binding.edtPlaca1.text.toString()
            carro.idUsuario = usuarioDTO.id
            carro.apelido = this.binding.edtApelido.text.toString()

            val carDAO = CarDAO(requireContext())
            carDAO.addCar(carro)
            updateCarros()

        }


    }
    fun updateCarros(){
        carrosLista.clear()


        val carDAO = CarDAO(requireContext())
        for (car in carDAO.getAllCars()){
            if (car.idUsuario==usuarioDTO.id){
                carrosLista.add(car)

            }


        }





        binding.listveiculo.invalidate()
    }











    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
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