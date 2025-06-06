package qi.projeto.whatev.clickpark.controller.placeholder

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import qi.projeto.whatev.clickpark.databinding.FragmentHomeBinding
import qi.projeto.whatev.clickpark.model.dao.UserDAO
import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO
import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.cos

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment(private val userDAO: UserDAO,
                   private var usuarioDTO: UsuarioDTO) : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        this.binding.btnAdicionarTempo.setOnClickListener {
            var horas = 0
            if (this.binding.edtHoras.text.toString().toIntOrNull()!=null) {
                    horas = this.binding.edtHoras.text.toString().toInt()
                }



            var minutos =0

            if (this.binding.edtMinutos.text.toString().toIntOrNull()!=null) {
                minutos = this.binding.edtMinutos.text.toString().toInt()
            }
            val cost =  ((horas*60)+minutos)*6

            if (cost<this.usuarioDTO.creditos){
                usuarioDTO.creditos -= cost

                userDAO.updateUser(usuarioDTO)

                val total: Long = (horas * 60 + minutos).toLong()

                var tempoatual =
                    Duration.between(LocalDateTime.now(), usuarioDTO.expiracaoDeEstacionamento)
                if (tempoatual.isNegative) {
                    tempoatual = Duration.ZERO
                }
                usuarioDTO!!.expiracaoDeEstacionamento =
                    LocalDateTime.now().plus(tempoatual.plusMinutes(total))

                userDAO.updateUser(usuarioDTO!!)
            }
        }


        binding.edtHoras.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updatecost()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.edtMinutos.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updatecost()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        lifecycleScope.launch {
            updatevalues()
        }
    }

    fun updatecost(){

        var horas = 0
        if (this.binding.edtHoras.text.toString().toIntOrNull()!=null) {
            horas = this.binding.edtHoras.text.toString().toInt()
        }



        var minutos =0

        if (this.binding.edtMinutos.text.toString().toIntOrNull()!=null) {
            minutos = this.binding.edtMinutos.text.toString().toInt()
        }
        val cost =  ((horas*60)+minutos)*6
        val costAsFloat: Double = cost*0.01
        this.binding.txtValorEmReais.setText("$costAsFloat R$")
    }



    suspend fun updatevalues(){
        while (true) {

            if (usuarioDTO != null){



            val currentTime = LocalDateTime.now()


            val parkingtime :Duration= Duration.between(currentTime,usuarioDTO!!.expiracaoDeEstacionamento)

            if (!parkingtime.isNegative) {
                val seconds = parkingtime.seconds
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60


                binding.txtTempoSobrando.setText(String.format("%02d:%02d:%02d", hours, minutes, secs))

            } else {
                binding.txtTempoSobrando.setText("00:00:00")
            }
            binding.txtValorEmReais2.setText((usuarioDTO.creditos.toFloat()*0.01).toString())
        }
            delay(Duration.ofSeconds(1))        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        return binding.root





    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment(
                userDAO = TODO(),
                usuarioDTO = TODO()
            ).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}