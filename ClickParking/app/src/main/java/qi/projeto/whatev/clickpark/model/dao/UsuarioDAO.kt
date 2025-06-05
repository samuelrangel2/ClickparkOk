package qi.projeto.whatev.clickpark.model.dao

import android.content.ContentValues
import android.content.Context
import qi.projeto.whatev.clickpark.controller.MainActivity

import qi.projeto.whatev.clickpark.model.connectdb.ConnectionBD







import qi.projeto.whatev.clickpark.model.dto.UsuarioDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserDAO(context: Context) {

    private val dbHelper = ConnectionBD(context)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    // CREATE
    fun addUser(user: UsuarioDTO): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("creditos", user.creditos)
            put("expiracao_de_estacionamento", LocalDateTime.MIN.toString())
            put("email",user.email)
            put("cpf",user.cpf)
            put("senha",user.senha)
        }
        return db.insert("usuarios", null, values)
    }

    // READ
    fun getAllUsers(): List<UsuarioDTO> {
        val users = mutableListOf<UsuarioDTO>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "usuarios",
            arrayOf("id", "creditos", "expiracao_de_estacionamento","cpf","senha","email"),
            null, null, null, null, null
        )

        while (cursor.moveToNext()) {
            val user = UsuarioDTO().apply {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                creditos = cursor.getInt(cursor.getColumnIndexOrThrow("creditos"))
                expiracaoDeEstacionamento = LocalDateTime.parse(
                    cursor.getString(cursor.getColumnIndexOrThrow("expiracao_de_estacionamento")),
                    formatter
                )
                cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"))
                senha = cursor.getString(cursor.getColumnIndexOrThrow("senha"))
                email = cursor.getString(cursor.getColumnIndexOrThrow("email" ))
            }
            users.add(user)
        }
        cursor.close()
        return users
    }

    // UPDATE
    fun updateUser(user: UsuarioDTO): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("creditos", user.creditos)
            put("expiracao_de_estacionamento", user.expiracaoDeEstacionamento.format(formatter))
        }
        val whereClause = "id = ?"
        val whereArgs = arrayOf(user.id.toString())
        return db.update("usuarios", values, whereClause, whereArgs)
    }

    // DELETE
    fun deleteUser(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("usuarios", "id = ?", arrayOf(id.toString()))
    }
}
