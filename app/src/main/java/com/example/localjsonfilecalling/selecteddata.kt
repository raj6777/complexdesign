package com.example.localjsonfilecalling

import com.example.localjsonfilecalling.model.DataModel

interface AdapterOnClick {
    fun onChecked(checkedUser: DataModel)
    fun onUnChecked(checkedUser: DataModel)
}