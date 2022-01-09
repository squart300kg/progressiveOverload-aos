package com.progressive.overload.model.model

import java.io.Serializable

data class AppUpdateModel(
    val forceUpdateVersion: String = "",
    val latestVersion: String = "",
    val maintenanceMessage: String = "",
    val maintenanceMode: Boolean = false,
) : Serializable
