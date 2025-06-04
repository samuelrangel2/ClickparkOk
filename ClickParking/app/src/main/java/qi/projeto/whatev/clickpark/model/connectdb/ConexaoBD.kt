package qi.projeto.whatev.clickpark.model.connectdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class ConnectionBD(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "bd_park"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Definindo a query para criar a tabela "car_plates" com campo id (autoincrement) e plate (texto)
        val createTableSQL = """
            CREATE TABLE carros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                plate TEXT,
                fk_id_usuario INTEGER
                
            );
        """.trimIndent()
        db?.execSQL(createTableSQL) // Executa a query de criação da tabela
        val createTableUsuarioSQL = """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                expiracao_de_estacionamento DATETIME,
                creditos INTEGER,
                cpf VARCHAR(12),
                email VARCHAR(30),
                senha VARCHAR(30)
            );""".trimIndent()
        db?.execSQL(createTableUsuarioSQL) // Executa a query de criação da tabela



    }
    


















    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Em caso de atualização da versão, a tabela antiga é removida e recriada
        db?.execSQL("DROP TABLE IF EXISTS car_plates")
        onCreate(db)
    }
}


