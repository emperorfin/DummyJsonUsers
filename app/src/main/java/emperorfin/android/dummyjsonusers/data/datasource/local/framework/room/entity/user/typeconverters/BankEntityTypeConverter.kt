package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.BankEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class BankEntityTypeConverter {

    @TypeConverter
    fun fromBankEntityJson(bank: BankEntity): String {
        return Gson().toJson(bank)
    }

    @TypeConverter
    fun toBankEntity(jsonBank: String): BankEntity {
        val notesType = object : TypeToken<BankEntity>() {}.type
        return Gson().fromJson<BankEntity>(jsonBank, notesType)
    }

}