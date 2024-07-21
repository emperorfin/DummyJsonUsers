package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.HairEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class HairEntityTypeConverter {

    @TypeConverter
    fun fromHairEntityJson(hair: HairEntity): String {
        return Gson().toJson(hair)
    }

    @TypeConverter
    fun toHairEntity(jsonHair: String): HairEntity {
        val notesType = object : TypeToken<HairEntity>() {}.type
        return Gson().fromJson<HairEntity>(jsonHair, notesType)
    }

}