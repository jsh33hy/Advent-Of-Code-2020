package utils

import java.time.Instant

class JLog(private val classIdentifier: String) {
    fun log(msg: String){
        println("${Instant.now()} :: [$classIdentifier] >> $msg")
    }
    fun debug(msg: String){
        if(DEBUG){
            log(msg)
        }
    }
    companion object{
        private val DEBUG = false
        private val defaultLogger = JLog("")

        fun getLogger(logIdentifier : String) : JLog{
            return JLog(logIdentifier)
        }

        fun log(msg: String){
            defaultLogger.log(msg)
        }
    }
}