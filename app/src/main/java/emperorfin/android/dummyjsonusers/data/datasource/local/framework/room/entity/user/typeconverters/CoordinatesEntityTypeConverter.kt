package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CoordinatesEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class CoordinatesEntityTypeConverter {

    @TypeConverter
    fun fromCoordinatesEntityJson(coordinates: CoordinatesEntity): String {
        return Gson().toJson(coordinates)
    }

    @TypeConverter
    fun toCoordinatesEntity(jsonCoordinates: String): CoordinatesEntity {
        val notesType = object : TypeToken<CoordinatesEntity>() {}.type
        return Gson().fromJson<CoordinatesEntity>(jsonCoordinates, notesType)
    }

}