package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CryptoEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class CryptoEntityTypeConverter {

    @TypeConverter
    fun fromCryptoEntityJson(crypto: CryptoEntity): String {
        return Gson().toJson(crypto)
    }

    @TypeConverter
    fun toCryptoEntity(jsonCrypto: String): CryptoEntity {
        val notesType = object : TypeToken<CryptoEntity>() {}.type
        return Gson().fromJson<CryptoEntity>(jsonCrypto, notesType)
    }

}