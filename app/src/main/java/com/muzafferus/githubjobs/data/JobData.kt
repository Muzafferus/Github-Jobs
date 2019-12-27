package com.muzafferus.githubjobs.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_table")
data class JobData(

    @PrimaryKey
    var id: String,

    var type: String?,

    var url: String,

    var created_at: String,

    var company: String,

    var company_url: String?,

    var location: String,

    var title: String,

    var description: String,

    var how_to_apply: String,

    var company_logo: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(created_at)
        parcel.writeString(company)
        parcel.writeString(company_url)
        parcel.writeString(location)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(how_to_apply)
        parcel.writeString(company_logo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JobData> {
        override fun createFromParcel(parcel: Parcel): JobData {
            return JobData(parcel)
        }

        override fun newArray(size: Int): Array<JobData?> {
            return arrayOfNulls(size)
        }
    }
}

/*example*/
//id	"8ed00c37-1c03-4962-8e8c-85ba37641e16"
//type	"Full Time"
//url	"https://jobs.github.com/positions/8ed00c37-1c03-4962-8e8c-85ba37641e16"
//created_at	"Tue Dec 10 17:54:33 UTC 2019"
//company	"Blue Clover Devices"
//company_url	null
//location	"San Francisco"
//title	"Sr. Firmware Developer"
//description	"<p>$120k – $160k • No eq… training</li>\n</ul>\n"
//how_to_apply	"<p><a href=\"https://ang…ware-developer</a></p>\n"
//company_logo	"https://jobs.github.com/…bcdCorporateLogoRSVG.png"