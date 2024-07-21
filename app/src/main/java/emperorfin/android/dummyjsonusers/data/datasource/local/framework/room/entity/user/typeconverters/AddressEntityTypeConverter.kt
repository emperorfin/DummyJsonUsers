package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.AddressEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class AddressEntityTypeConverter {

    @TypeConverter
    fun fromAddressEntityJson(address: AddressEntity): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddressEntity(jsonAddress: String): AddressEntity {
        val notesType = object : TypeToken<AddressEntity>() {}.type
        return Gson().fromJson<AddressEntity>(jsonAddress, notesType)
    }

}