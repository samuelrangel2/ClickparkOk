package qi.projeto.whatev.clickpark.model.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import qi.projeto.whatev.clickpark.model.connectdb.ConnectionBD
import qi.projeto.whatev.clickpark.model.dto.CarDTO



class CarDAO(context: Context) {

    private val dbHelper = ConnectionBD(context)



    // Função para inserir uma nova placa (CREATE)
    fun addCar(carPlate: CarDTO): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {  // Prepara os valores para inserir na tabela
            put("plate", carPlate.plate)
            put("apelido",carPlate.apelido)
            put("fk_id_usuario",carPlate.idUsuario)
        }
        return db.insert("carros", null, values)  // Insere os dados e retorna o ID da nova linha
    }



    // Função para ler todas as placas (READ)
    fun getAllCars(): List<CarDTO> {
        val carPlates = mutableListOf<CarDTO>()
        val db = dbHelper.readableDatabase
        val s2 = arrayOf("id", "plate","fk_id_usuario","apelido")
        
        // Se o cursor retornar dados, percorre cada linha
        val objCursor = db.query(false , "carros", s2, null, null, null, null, null, null,)

        while (objCursor.moveToNext()) {
            val objCarDTO =CarDTO()

            objCarDTO.id= objCursor.getInt(objCursor.getColumnIndexOrThrow("id"))


            objCarDTO.plate= objCursor.getString(objCursor.getColumnIndexOrThrow("plate"))
            objCarDTO.idUsuario = objCursor.getInt(objCursor.getColumnIndexOrThrow("fk_id_usuario"))
            objCarDTO.apelido= objCursor.getString(objCursor.getColumnIndexOrThrow("apelido"))

            carPlates.add(objCarDTO)
        }

        objCursor.close()  // Fecha o cursor para liberar recursos
        return carPlates
    }




    fun updateCar(carPlate: CarDTO): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("plate", carPlate.plate)
            put("apelido",carPlate.apelido)
            put("fk_id_usuario",carPlate.idUsuario)

        }

        // Make sure the whereArgs contains the ID as a string
        val whereClause = "id = ?"
        val whereArgs = arrayOf(carPlate.id.toString())

        return db.update("carros", values, whereClause, whereArgs)
    }

    // Função para excluir uma placa pelo ID (DELETE)
    fun deleteCar(id: Int): Int {
        val db = dbHelper.writableDatabase
        // Remove a linha cujo id corresponda ao informado
        return db.delete("carros", "id=?", arrayOf(id.toString()))
    }
}