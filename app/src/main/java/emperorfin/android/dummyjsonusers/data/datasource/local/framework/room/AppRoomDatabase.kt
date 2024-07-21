package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.dao.UserDao
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ADDRESS
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_AGE
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_BANK
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_BIRTH_DATE
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_BLOODGROUP
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_COMPANY
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_CRYPTO
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_EIN
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_EMAIL
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_EYE_COLOR
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_FIRST_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_GENDER
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_HAIR
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_HEIGHT
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ID
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_IMAGE
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_IP
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_LAST_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_MAC_ADDRESS
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_MAIDEN_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_PASSWORD
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_PHONE
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ROLE
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_SSN
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_UNIVERSITY
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_USERNAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_USER_AGENT
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_WEIGHT
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.TABLE_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters.AddressEntityTypeConverter
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters.BankEntityTypeConverter
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters.CompanyEntityTypeConverter
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters.CoordinatesEntityTypeConverter
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters.CryptoEntityTypeConverter
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters.HairEntityTypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    value = [
        (AddressEntityTypeConverter::class),
        (BankEntityTypeConverter::class),
        (CompanyEntityTypeConverter::class),
        (CoordinatesEntityTypeConverter::class),
        (CryptoEntityTypeConverter::class),
        (HairEntityTypeConverter::class)
    ]
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract val mUserDao: UserDao

    companion object {

        private const val DATABASE_NAME = "database_app"

        private var isDatabaseAlreadyPopulated: Boolean = false

        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        private val TAG: String = AppRoomDatabase::class.java.simpleName

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase{

            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        DATABASE_NAME
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                coroutineScope.launch {
                                    // This is now commented out since real data is being cached
                                    // to the database.
//                                    populateInitialUsersSampleDataUsingSqliteDatabaseWithCoroutineThread(db, SampleData.getEntityUsers())
                                }
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                            }

                            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                                super.onDestructiveMigration(db)
                            }
                        })
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }

        }

        private suspend fun populateInitialUsersSampleDataUsingSqliteDatabaseWithCoroutineThread(db: SupportSQLiteDatabase, users: List<UserEntity>) {
            // Unused at the moment.
            if (isDatabaseAlreadyPopulated)
                return

            db.beginTransaction()

            try {
                val initialUserValues = ContentValues()
                for (user in users){
                    initialUserValues.put(COLUMN_INFO_ID, user.id)
                    initialUserValues.put(COLUMN_INFO_FIRST_NAME, user.firstName)
                    initialUserValues.put(COLUMN_INFO_LAST_NAME, user.lastName)
                    initialUserValues.put(COLUMN_INFO_MAIDEN_NAME, user.maidenName)
                    initialUserValues.put(COLUMN_INFO_AGE, user.age)
                    initialUserValues.put(COLUMN_INFO_GENDER, user.gender)
                    initialUserValues.put(COLUMN_INFO_EMAIL, user.email)
                    initialUserValues.put(COLUMN_INFO_PHONE, user.phone)
                    initialUserValues.put(COLUMN_INFO_USERNAME, user.username)
                    initialUserValues.put(COLUMN_INFO_PASSWORD, user.password)
                    initialUserValues.put(COLUMN_INFO_BIRTH_DATE, user.birthDate)
                    initialUserValues.put(COLUMN_INFO_IMAGE, user.image)
                    initialUserValues.put(COLUMN_INFO_BLOODGROUP, user.bloodGroup)
                    initialUserValues.put(COLUMN_INFO_HEIGHT, user.height)
                    initialUserValues.put(COLUMN_INFO_WEIGHT, user.weight)
                    initialUserValues.put(COLUMN_INFO_EYE_COLOR, user.eyeColor)
                    initialUserValues.put(COLUMN_INFO_IP, user.ip)
                    initialUserValues.put(COLUMN_INFO_MAC_ADDRESS, user.macAddress)
                    initialUserValues.put(COLUMN_INFO_UNIVERSITY, user.university)
                    initialUserValues.put(COLUMN_INFO_EIN, user.ein)
                    initialUserValues.put(COLUMN_INFO_SSN, user.ssn)
                    initialUserValues.put(COLUMN_INFO_USER_AGENT, user.userAgent)
                    initialUserValues.put(COLUMN_INFO_ROLE, user.role)

                    // TODO: Convert objects to JSON string to save to DB. You can use Gson to convert
                    //       from the string back to the objects during retrieval.
//                    initialUserValues.put(COLUMN_INFO_HAIR, user.hair)
//                    initialUserValues.put(COLUMN_INFO_ADDRESS, user.address)
//                    initialUserValues.put(COLUMN_INFO_BANK, user.bank)
//                    initialUserValues.put(COLUMN_INFO_COMPANY, user.company)
//                    initialUserValues.put(COLUMN_INFO_CRYPTO, user.crypto)

                    db.insert(TABLE_NAME, SQLiteDatabase.CONFLICT_REPLACE, initialUserValues)
                }

                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }

    }
}