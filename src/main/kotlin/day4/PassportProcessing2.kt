package day4

import base.AocProblemSolver

class PassportProcessing2 : AocProblemSolver() {
    private val hairRegex = """^#[0-9a-f]{6}${'$'}""".toRegex()
    private val ppIdRegex = """^\d{9}${'$'}""".toRegex()
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
            val isValid = validatePassportData(it)
            logger.debug("isvalid = $isValid, str=$it")
            isValid
        }.count().toString()
    }

    //  this function is very specific to the input data
    private fun validatePassportData(passportData: String): Boolean {
        //  split the passport data into individual stringified key values
        //  each item should look like like hcl:#d125e3
        val stringifiedKeyValues = passportData.split(";")

        //  convert all stringifiedKeyValues for a passport into a proper map
        val ppdMap = stringifiedKeyValues.map {
            val keyValueList = it.split(":")
            keyValueList[0] to keyValueList[1]
        }.toMap()

        //  Note: Could use regex for basically everything here
        return (
                ppdMap.getOrDefault("byr", "1").toInt() in 1920..2002 &&
                ppdMap.getOrDefault("iyr", "1").toInt() in 2010..2020 &&
                ppdMap.getOrDefault("eyr", "1").toInt() in 2020..2030 &&
                isHeightValid(ppdMap.getOrDefault("hgt", "1cm")) &&
                isHairColorValid(ppdMap.getOrDefault("hcl", "")) &&
                isEyeColorValid(ppdMap.getOrDefault("ecl", "")) &&
                isPassportIdValid(ppdMap.getOrDefault("pid", ""))
        )
    }

    private fun isHeightValid(heightStr: String): Boolean {
        return when {
            heightStr.contains("cm") -> {
                heightStr.substringBefore("cm").toInt() in 150..193
            }
            heightStr.contains("in") -> {
                heightStr.substringBefore("in").toInt() in 59..76
            }
            else -> {
                false
            }
        }
    }

    private fun isHairColorValid(hairColorStr: String): Boolean {
        return hairRegex.matches(hairColorStr)
    }

    private fun isEyeColorValid(eyeColorStr: String): Boolean {
        return when (eyeColorStr) {
            "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true
            else -> false
        }
    }

    private fun isPassportIdValid(passportIdStr: String): Boolean {
        return ppIdRegex.matches(passportIdStr)
    }

}