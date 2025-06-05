package qi.projeto.whatev.clickpark.model.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UsuarioDTO :Serializable {
    var id:Int = 0
    var creditos:Int = 0
    lateinit var expiracaoDeEstacionamento:LocalDateTime
    var senha:String = ""
    var cpf :String = ""
    var email:String = ""
    override fun toString(): String {
        return "UsuarioDTO(id=$id, creditos=$creditos, expiracaoDeEstacionamento=$expiracaoDeEstacionamento, senha='$senha', cpf='$cpf', email='$email')"
    }


}