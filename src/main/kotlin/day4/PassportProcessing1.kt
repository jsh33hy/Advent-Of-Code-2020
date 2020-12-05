package day4

import base.AocProblemSolver

class PassportProcessing1 : AocProblemSolver() {
    override fun getInputFileName(): String {
        return "problem4Input.txt"
    }

    override fun solve(): String {
        val fileInput = getRawFileInput()
        //  group passportData for each passport together into 1 line, separate passports by ;
        val parsedInput = fileInput.split("\n\n").map {
            //  replace spaces and new lines with ;
            it.replace("\\s+".toRegex(), ";")
        }
        //  at this point each passport entry should be grouped together into 1 item and look like
        //  hcl:#d125e3;iyr:2016;byr:1982;eyr:2027;hgt:154cm;pid:365548961
        return parsedInput.filter {
            validatePassportData(it)
        }.count().toString()
    }

    private fun validatePassportData(passportData: String): Boolean{
        //  this function is very specific to the input data
        val numberOfKeyValues = passportData.split(";").count()
        return (numberOfKeyValues == 8
                || numberOfKeyValues == 7 && !passportData.contains("cid"))
    }
}