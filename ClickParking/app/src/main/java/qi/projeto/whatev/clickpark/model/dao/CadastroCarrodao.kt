package qi.projeto.whatev.clickpark.model.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import qi.projeto.whatev.clickpark.model.connectdb.ConnectionBD
import qi.projeto.whatev.clickpark.model.dto.CarPlateDTO



class CarPlateDAO(context: Context) {

    private val dbHelper = ConnectionBD(context)



    // Função para inserir uma nova placa (CREATE)
    fun addCarPlate(carPlate: CarPlateDTO): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {  // Prepara os valores para inserir na tabela
            put("plate", carPlate.plate)
        }
        return db.insert("car_plates", null, values)  // Insere os dados e retorna o ID da nova linha
    }



    // Função para ler todas as placas (READ)
    fun getAllCarPlates(): List<CarPlateDTO> {
        val carPlates = mutableListOf<CarPlateDTO>()
        val db = dbHelper.readableDatabase
        val s2 = "car_plates"                 
            arrayOf("id", "plate")        
        
        // Se o cursor retornar dados, percorre cada linha
        val objCursor = db.query(false , "carros", arrayOf("id","plate","id_usuario"), null, null, null, null, null, null,)

        while (objCursor.moveToNext()) {
            val objCarPlateDTO =CarPlateDTO()

            objCarPlateDTO.id= objCursor.getInt(objCursor.getColumnIndexOrThrow("id"))


            objCarPlateDTO.plate= objCursor.getString(objCursor.getColumnIndexOrThrow("plate"))

            carPlates.add(objCarPlateDTO)
        }

        objCursor.close()  // Fecha o cursor para liberar recursos
        return carPlates
    }




    fun updateCarPlate(carPlate: CarPlateDTO): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("plate", carPlate.plate)
            put("id_usuario",carPlate.idUsuario)

        }

        // Make sure the whereArgs contains the ID as a string
        val whereClause = "id = ?"
        val whereArgs = arrayOf(carPlate.id.toString())

        return db.update("car_plates", values, whereClause, whereArgs)
    }

    // Função para excluir uma placa pelo ID (DELETE)
    fun deleteCarPlate(id: Int): Int {
        val db = dbHelper.writableDatabase
        // Remove a linha cujo id corresponda ao informado
        return db.delete("car_plates", "id=?", arrayOf(id.toString()))
    }
}