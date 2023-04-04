package com.example.composebugs


object GendersDropDown {
    fun getList(): List<DropDownItem> {
        return listOf(
            DropDownItem("Female", "Female"),
            DropDownItem("Male", "Male"),
        )
    }
}
