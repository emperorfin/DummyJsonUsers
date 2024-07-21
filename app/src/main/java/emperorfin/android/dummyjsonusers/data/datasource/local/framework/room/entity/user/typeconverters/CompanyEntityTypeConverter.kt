package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CompanyEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class CompanyEntityTypeConverter {

    @TypeConverter
    fun fromCompanyEntityJson(company: CompanyEntity): String {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun toCompanyEntity(jsonCompany: String): CompanyEntity {
        val notesType = object : TypeToken<CompanyEntity>() {}.type
        return Gson().fromJson<CompanyEntity>(jsonCompany, notesType)
    }

}