import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maid.cleanhires.data.local.room.ServiceDao

abstract class ServiceDatabase : RoomDatabase() {

    abstract fun getDao() : ServiceDao

    companion object {

        @Volatile
        private var instance : ServiceDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ServiceDatabase::class.java,
                "serviceDB.db"
            ).build()
    }

}