package android.sample.com.movieapp.util

import android.widget.CheckBox

class UIUtils {

    companion object {

        fun getCheckedYears(yearsCheckBoxList: ArrayList<CheckBox>): ArrayList<Int> {
            val checkedYears = ArrayList<Int>()
            yearsCheckBoxList.forEach {
                    checkBox -> if (checkBox.isChecked) {
                        checkedYears.add(checkBox.text.toString().toInt())
                    }
            }

            return checkedYears
        }

        fun getCheckedDirectors(directorsCheckBoxList: ArrayList<CheckBox>): ArrayList<String> {
            val checkedDirectors = ArrayList<String>()
            directorsCheckBoxList.forEach {
                    checkBox -> if (checkBox.isChecked) {
                        checkedDirectors.add(checkBox.text.toString())
                    }
            }

            return checkedDirectors
        }

        fun getCheckedGenres(genresCheckBoxList: ArrayList<CheckBox>): ArrayList<String> {
            val checkedGenres = ArrayList<String>()
            genresCheckBoxList.forEach {
                    checkBox -> if (checkBox.isChecked) {
                        checkedGenres.add(checkBox.text.toString())
                    }
            }

            return checkedGenres
        }
    }
}