package qi.projeto.whatev.clickpark.model.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UsuarioDTO {
    var id:Int = 0
    var creditos:Int = 0
    lateinit var expiracaoDeEstacionamento:LocalDateTime



}